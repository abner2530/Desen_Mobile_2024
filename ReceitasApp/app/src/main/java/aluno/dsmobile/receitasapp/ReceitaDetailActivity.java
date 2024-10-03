package aluno.dsmobile.receitasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import aluno.dsmobile.receitasapp.Models.Receita;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReceitaDetailActivity extends AppCompatActivity {

    private ImageView imageViewImagem;
    private TextView textViewTitulo;
    private TextView textViewIngredientesList;
    private TextView textViewInstrucoesList;
    private FloatingActionButton fabFavoritar;

    private DatabaseReference favoritosRef;
    private boolean isFavorito = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_detail);

        imageViewImagem = findViewById(R.id.image_view_imagem);
        textViewTitulo = findViewById(R.id.text_view_titulo);
        textViewIngredientesList = findViewById(R.id.text_view_ingredientes_list);
        textViewInstrucoesList = findViewById(R.id.text_view_instrucoes_list);
        fabFavoritar = findViewById(R.id.fab_favoritar);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("receita_id")) {
            String receitaId = intent.getStringExtra("receita_id");

            assert receitaId != null;
            DatabaseReference receitaRef = FirebaseDatabase.getInstance().getReference("receitas").child(receitaId);
            favoritosRef = FirebaseDatabase.getInstance().getReference("favoritos").child(receitaId);

            receitaRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Receita receita = dataSnapshot.getValue(Receita.class);
                    if (receita != null) {
                        textViewTitulo.setText(receita.getTitulo());
                        Glide.with(ReceitaDetailActivity.this)
                                .load(receita.getImagemUrl())
                                .into(imageViewImagem);

                        StringBuilder ingredientes = new StringBuilder();
                        for (String ingrediente : receita.getIngredientes()) {
                            ingredientes.append(ingrediente).append("\n");
                        }
                        textViewIngredientesList.setText(ingredientes.toString());

                        StringBuilder instrucoes = new StringBuilder();
                        for (String instrucao : receita.getInstrucoes()) {
                            instrucoes.append(instrucao).append("\n");
                        }
                        textViewInstrucoesList.setText(instrucoes.toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            // Verifica se a receita já está favoritada
            favoritosRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    isFavorito = dataSnapshot.exists();
                    updateFabIcon();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            fabFavoritar.setOnClickListener(v -> {
                if (isFavorito) {
                    favoritosRef.removeValue().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            isFavorito = false;
                            updateFabIcon();
                            Toast.makeText(ReceitaDetailActivity.this, "Removido dos Favoritos", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    favoritosRef.setValue(true).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            isFavorito = true;
                            updateFabIcon();
                            Toast.makeText(ReceitaDetailActivity.this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    private void updateFabIcon() {
        if (isFavorito) {
            fabFavoritar.setImageResource(R.drawable.ic_favoritos);
        } else {
            fabFavoritar.setImageResource(R.drawable.ic_star_light);
        }
    }
}
