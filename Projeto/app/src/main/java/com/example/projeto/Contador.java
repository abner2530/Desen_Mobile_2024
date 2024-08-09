package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Contador extends AppCompatActivity {

    private int numero = 0;
    private TextView tv_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        tv_numero = findViewById(R.id.tv_contador);
        Button aumentar_contador = findViewById(R.id.btn_contador);

        // Receber o valor passado da MainActivity
        Intent intent = getIntent();

        if (intent != null) {
            String valorRecebido = intent.getStringExtra("valor");
            assert valorRecebido != null;
            numero = Integer.parseInt(valorRecebido);
            tv_numero.setText(String.valueOf(numero));
        }

        aumentar_contador.setOnClickListener(v -> {
            numero++;
            tv_numero.setText(String.valueOf(numero));
        });
    }
}

