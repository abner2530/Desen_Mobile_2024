package ufc.aluno.lista_tarefas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import ufc.aluno.lista_tarefas.DAO.TarefaDAO;
import ufc.aluno.lista_tarefas.Database.AppDatabase;
import ufc.aluno.lista_tarefas.Model.Tarefa;

public class NovaTarefa extends AppCompatActivity {

    private TextInputEditText edit_Titulo;
    private TextInputEditText edit_Descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nova_tarefa);

        edit_Titulo = findViewById(R.id.edit_titulo);
        edit_Descricao = findViewById(R.id.edit_descricao);
        Button btn_cadastrar = findViewById(R.id.btn_cadastrar);

        // Obtém a instância do banco de dados usando o padrão Singleton
        AppDatabase db = AppDatabase.getInstance(this);
        TarefaDAO tarefaDAO = db.tarefaDAO();

        btn_cadastrar.setOnClickListener(v -> {
            String titulo = edit_Titulo.getText().toString().trim();
            String descricao = edit_Descricao.getText().toString().trim();

            if (titulo.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(NovaTarefa.this, "Preencha todos os campos! Algum campo está vazio.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Inserindo nova tarefa no banco
            Tarefa novaTarefa = new Tarefa(titulo, descricao);
            tarefaDAO.insertAll(novaTarefa);

            edit_Titulo.setText("");
            edit_Descricao.setText("");

            startActivity(new Intent(NovaTarefa.this, MainActivity.class));
        });

    }
}
