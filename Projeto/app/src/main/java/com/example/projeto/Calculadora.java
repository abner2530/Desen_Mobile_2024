package com.example.projeto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Calculadora extends AppCompatActivity {

    private EditText edt_num1, edt_num2;
    private TextView tv_Resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        edt_num1 = findViewById(R.id.edt_value1);
        edt_num2 = findViewById(R.id.edt_value2);
        tv_Resultado = findViewById(R.id.tv_resultado);

        Button btn_Soma = findViewById(R.id.btn_mais);
        Button btn_Subtracao = findViewById(R.id.btn_subtrair);
        Button btn_Multiplicacao = findViewById(R.id.btn_multiplicar);
        Button btn_Divisao = findViewById(R.id.btn_dividir);

        btn_Soma.setOnClickListener(v -> calcular("+"));

        btn_Subtracao.setOnClickListener(v -> calcular("-"));

        btn_Multiplicacao.setOnClickListener(v -> calcular("*"));

        btn_Divisao.setOnClickListener(v -> calcular("/"));
    }

    private void calcular(String operador) {
        double num1 = Double.parseDouble(edt_num1.getText().toString());
        double num2 = Double.parseDouble(edt_num2.getText().toString());
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "*":
                resultado = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    tv_Resultado.setText("Erro: divisão por zero!");
                    return;
                }
                break;
        }

        tv_Resultado.setText(String.valueOf(resultado));
    }
}
