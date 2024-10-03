package aluno.dsmobile.receitasapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import aluno.dsmobile.receitasapp.R;

public class PrincipalFragment extends Fragment {

    private ReceitaAdapter receitaAdapter;
    private List<Receita> receitaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        receitaList = new ArrayList<>();
        receitaAdapter = new ReceitaAdapter(getContext(), receitaList);
        recyclerView.setAdapter(receitaAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("receitas");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                receitaList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Receita receita = snapshot.getValue(Receita.class);
                    receitaList.add(receita);
                }
                receitaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Erro ao carregar receitas.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
