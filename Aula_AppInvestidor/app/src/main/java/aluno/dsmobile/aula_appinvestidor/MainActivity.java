package aluno.dsmobile.aula_appinvestidor;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import aluno.dsmobile.aula_appinvestidor.Adapter.AtivoRecyclerViewAdapter;
import aluno.dsmobile.aula_appinvestidor.Model.Ativo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Ativo> ativoList;
    private RecyclerView ativosRecyclerView;
    private AtivoRecyclerViewAdapter ativoRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ativoList = new ArrayList<>();
        ativosRecyclerView = findViewById(R.id.rv_ativos);

        populateAtivosView();

        FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();

        DatabaseReference dbReference = fbDatabase.getReference();

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot myData : snapshot.getChildren()) {
                    Ativo ativoFromJson = myData.getValue(Ativo.class);
                    ativoList.add(ativoFromJson);

                }
                populateAtivosView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void populateAtivosView() {
        ativoRecyclerViewAdapter = new AtivoRecyclerViewAdapter(ativoList);

        ativosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ativosRecyclerView.setAdapter(ativoRecyclerViewAdapter);
    }
}