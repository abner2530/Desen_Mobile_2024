package com.example.prova;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.prova.DAO.TarefaDAO;
import com.example.prova.Database.AppDatabase;
import com.example.prova.Model.Tarefa;

public class NovaTarefa extends AppCompatActivity {

    private EditText edit_Titulo;
    private EditText edit_Descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nova_tarefa);

        edit_Titulo = findViewById(R.id.edit_titulo);
        edit_Descricao = findViewById(R.id.edit_descricao);
        Button btn_cadastrar = findViewById(R.id.btn_cadastrar);

        MainActivity main = new MainActivity();


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "DB-Tarefas")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        TarefaDAO tarefaDAO = db.tarefaDAO();

        btn_cadastrar.setOnClickListener(v -> {
            String titulo = edit_Titulo.getText().toString().trim();
            String descricao = edit_Descricao.getText().toString().trim();

            if (titulo.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(NovaTarefa.this, "Preencha todos os campos! Algum campo está vazio.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Inserindo novo Estudante no Banco
            Tarefa novaTarefa = new Tarefa(titulo, descricao);

            tarefaDAO.insertAll(novaTarefa);

            edit_Titulo.setText("");
            edit_Descricao.setText("");

            startActivity(new Intent(NovaTarefa.this, MainActivity.class));
        });



    }
}