package com.example.atividade1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.lv_custom_adapter);

        String[] agentes = {"JETT", "SAGE", "OMEN","ASTRA", "KAYO"};
        String[] classe = {"Duelista", "Sentinela", "Controlador","Controlador", "Iniciador"};

        ListViewCustomAdapter adapter = new ListViewCustomAdapter(this, agentes, classe);

        listview.setAdapter(adapter);
        adapter.setOnItemClickListener(listview);
    }
}