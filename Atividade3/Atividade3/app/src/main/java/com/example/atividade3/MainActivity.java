package com.example.atividade3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ModelCard> cardLists;
    private CardView newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_main);
        cardLists = new ArrayList<>();

        ModelCard card1 = new ModelCard("Quartos:",
                "Um refúgio aconchegante para sonhar e reenergizar em camas confortáveis, envolto em um ambiente relaxante e equipado com tudo que você precisa para uma noite de sono revigorante.",
                R.drawable.fundo1);

        ModelCard card2 = new ModelCard("Piscina:",
                "Um refrescante oásis para se divertir e renovar as energias, seja nadando em águas cristalinas, tomando sol em confortáveis espreguiçadeiras ou simplesmente relaxando à beira da piscina.",
                R.drawable.fundo2);

        ModelCard card3 = new ModelCard("Restaurante:",
                "Uma experiência gastronômica inesquecível com sabores irresistíveis que agradam a todos os paladares. Saboreie um café da manhã completo para começar o dia com energia, desfrute de um almoço ou jantar com pratos da culinária local e internacional, e finalize com deliciosas sobremesas.",
                R.drawable.fundo3);

        ModelCard card4 = new ModelCard("Spa:",
                "Um santuário de paz e bem-estar para cuidar de si mesmo. Desfrute de massagens relaxantes, tratamentos faciais e corporais revigorantes, e renove o corpo e a mente em um ambiente tranquilo e acolhedor.",
                R.drawable.fundo1);

        ModelCard news5 = new ModelCard("Área de Lazer:",
                "Diversão garantida para toda a família em um espaço ideal para relaxar e se conectar com os seus entes queridos. Explore jogos, brincadeiras e atividades ao ar livre, criando memórias inesquecíveis.",
                R.drawable.fundo2);

        cardLists.add(card1);
        cardLists.add(card2);
        cardLists.add(card3);
        cardLists.add(card4);
        cardLists.add(news5);

        newsAdapter = new CardView(cardLists);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);

    }
}