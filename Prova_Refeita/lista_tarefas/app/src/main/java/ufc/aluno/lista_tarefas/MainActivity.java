package ufc.aluno.lista_tarefas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ufc.aluno.lista_tarefas.Adapter.Item;
import ufc.aluno.lista_tarefas.Adapter.ItemArrayAdapter;
import ufc.aluno.lista_tarefas.DAO.TarefaDAO;
import ufc.aluno.lista_tarefas.Database.AppDatabase;
import ufc.aluno.lista_tarefas.Model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ItemArrayAdapter itemArrayAdapter;
    private List<Item> tarefasItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializar atributos da View
        RecyclerView rv_tarefa = findViewById(R.id.rv_tarefas);
        Button button = findViewById(R.id.btn_novaTarefa);

        rv_tarefa.setLayoutManager(new LinearLayoutManager(this));

        // Inicializando Adapter e Lista
        tarefasItems = new ArrayList<>();
        itemArrayAdapter = new ItemArrayAdapter(tarefasItems);

        // Setando Adapter para RecyclerView
        rv_tarefa.setAdapter(itemArrayAdapter);

        // Inicializando Banco
        // Obtém a instância do banco de dados usando o padrão Singleton
        AppDatabase db = AppDatabase.getInstance(this);
        TarefaDAO tarefaDAO = db.tarefaDAO();

        loadRecyclerList(tarefaDAO);

        button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NovaTarefa.class)));
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadRecyclerList(TarefaDAO tarefasDAO) {
        List<Tarefa> tarefas = tarefasDAO.getAll();
        tarefasItems.clear();
        for (Tarefa tarefa : tarefas) {
            tarefasItems.add(new Item(tarefa.uid, tarefa.Titulo, tarefa.Descricao));
        }
        itemArrayAdapter.notifyDataSetChanged();
    }
}