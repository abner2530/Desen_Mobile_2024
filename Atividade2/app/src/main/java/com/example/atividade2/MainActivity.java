package com.example.atividade2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_cidades;
    ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_cidades = findViewById(R.id.rv_restaurante);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Cerveja", "Skoll, Heineken, Devassa, Amstell", R.drawable.cerveja));
        itemList.add(new Item("Petisco", "Batata, Macaxeira, Filezinho, Carne de Sol", R.drawable.pestico));
        itemList.add(new Item("Refrigerante", "Coca, Guaraná, Fanta, Sprite", R.drawable.cerveja));
        itemList.add(new Item("Almoço", "Parmediana, Maminha, Frango, Peixe", R.drawable.pestico));
        itemList.add(new Item("Refrigerante", "Coca, Guaraná, Fanta, Sprite", R.drawable.cerveja));
        itemList.add(new Item("Almoço", "Parmediana, Maminha, Frango, Peixe", R.drawable.pestico));
        itemList.add(new Item("Refrigerante", "Coca, Guaraná, Fanta, Sprite", R.drawable.cerveja));
        itemList.add(new Item("Almoço", "Parmediana, Maminha, Frango, Peixe", R.drawable.pestico));
        itemList.add(new Item("Refrigerante", "Coca, Guaraná, Fanta, Sprite", R.drawable.cerveja));
        itemList.add(new Item("Almoço", "Parmediana, Maminha, Frango, Peixe", R.drawable.pestico));

        itemArrayAdapter = new ItemArrayAdapter(R.layout.activity_item_array_adapter, itemList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_cidades.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        rv_cidades.addItemDecoration(dividerItemDecoration);

        rv_cidades.setAdapter(itemArrayAdapter);
    }
}
