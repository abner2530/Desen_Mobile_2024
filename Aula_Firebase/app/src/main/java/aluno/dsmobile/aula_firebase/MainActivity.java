package aluno.dsmobile.aula_firebase;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView tv_title;

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

        tv_title = findViewById(R.id.tv_title);
        Usuario usuario1 = new Usuario("Abner E.", "abnerenoque@alu.ufc.br");

        FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();

        DatabaseReference dbNomeRef = fbDatabase.getReference("chave");
        DatabaseReference dbUserRef = fbDatabase.getReference("usuario");
        dbUserRef.setValue(usuario1);

        dbNomeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String meuDado = snapshot.getValue(String.class);
                tv_title.setText(meuDado);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}