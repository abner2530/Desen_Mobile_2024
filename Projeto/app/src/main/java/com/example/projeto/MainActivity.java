package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edt_valor);

        Button mudar_para_contador = findViewById(R.id.btn_contador);
        Button mudar_para_calculadora = findViewById(R.id.btn_calculadora);

        mudar_para_contador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor = editText.getText().toString();

                Intent intent = new Intent(MainActivity.this, Contador.class);
                intent.putExtra("valor", valor);
                startActivity(intent);
            }
        });

        mudar_para_calculadora.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Calculadora.class);
            startActivity(intent);
        });
    }
}
