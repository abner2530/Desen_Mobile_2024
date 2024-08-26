package aluno.dspersist.aula_retrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static class Distrito {
        public final int id;
        public final String nome;
        public final Municipio municipio;

        public Distrito(int id, String nome, Municipio municipio) {
            this.id = id;
            this.nome = nome;
            this.municipio = municipio;
        }
    }

    public static class Municipio {
        public final int id;
        public final String nome;

        public Municipio(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }
    }

    private ListView listViewDistritos;
    private ArrayAdapter<String> adapter;
    private List<String> distritosList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listViewDistritos = findViewById(R.id.list_view_distritos);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, distritosList);
        listViewDistritos.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.list_view_distritos), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar o listener do SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> loadData());

        // Carregar os dados iniciais
        swipeRefreshLayout.setRefreshing(true); // Mostrar o indicador de refresh
        loadData();
    }

    /**
     * Função para carregar os dados da API usando Retrofit de forma assíncrona.
     */
    private void loadData() {
        Runnable loadDataRunnable = new Runnable() {
            @Override
            public void run() {
                SimpleService simpleService = new SimpleService();
                try {
                    List<Distrito> distritos = simpleService.doRequest();
                    runOnUiThread(() -> updateUI(distritos)); // Atualiza a UI na thread principal
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("MainActivity", "Erro ao carregar distritos", e);
                    runOnUiThread(() -> {
                        swipeRefreshLayout.setRefreshing(false);
                        distritosList.clear();
                        distritosList.add("Erro ao carregar distritos.");
                        adapter.notifyDataSetChanged();
                    });
                }
            }
        };

        new Thread(loadDataRunnable).start();
    }

    /**
     * Função para atualizar a interface do usuário com os dados recebidos.
     *
     * @param distritos Lista de distritos obtida da API.
     */
    private void updateUI(List<Distrito> distritos) {
        distritosList.clear();
        if (distritos != null && !distritos.isEmpty()) {
            for (Distrito distrito : distritos) {
                distritosList.add(distrito.nome + " - " + distrito.municipio.nome);
            }
        } else {
            distritosList.add("Nenhum distrito encontrado.");
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false); // Parar o indicador de refresh
    }
}
