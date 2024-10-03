package aluno.dsmobile.receitasapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import aluno.dsmobile.receitasapp.Adapter.ReceitaAdapter;
import aluno.dsmobile.receitasapp.Models.Receita;

public class FavoriteActivity extends AppCompatActivity {

    private ReceitaAdapter adapter;
    private final List<Receita> receitasFavoritas = new ArrayList<>();

    private DatabaseReference favoritosRef;
    private DatabaseReference receitasRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReceitaAdapter(this, receitasFavoritas);
        recyclerView.setAdapter(adapter);

        favoritosRef = FirebaseDatabase.getInstance().getReference("favoritos");
        receitasRef = FirebaseDatabase.getInstance().getReference("receitas");

        carregarFavoritos();
    }

    private void carregarFavoritos() {
        favoritosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                receitasFavoritas.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String receitaId = snapshot.getKey();
                    if (receitaId != null) {
                        receitasRef.child(receitaId).addListenerForSingleValueEvent(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Receita receita = dataSnapshot.getValue(Receita.class);
                                if (receita != null) {
                                    receitasFavoritas.add(receita);
                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
