package aluno.lista.aula_room_db;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import aluno.lista.aula_room_db.Adapter.Item;
import aluno.lista.aula_room_db.Adapter.ItemArrayAdapter;
import aluno.lista.aula_room_db.DAO.StudentDAO;
import aluno.lista.aula_room_db.Database.AppDatabase;
import aluno.lista.aula_room_db.Model.Student;

public class MainActivity extends AppCompatActivity {

    private ItemArrayAdapter itemArrayAdapter;
    private List<Item> studentItems;
    private EditText edit_Name;
    private EditText edit_Course;
    private EditText edit_Age;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializando atributos da view.
        RecyclerView rv_main = findViewById(R.id.rv_main);
        edit_Name = findViewById(R.id.edit_Name);
        edit_Course = findViewById(R.id.edit_Course);
        edit_Age = findViewById(R.id.edit_Age);
        FloatingActionButton fab_Add = findViewById(R.id.fab_add);

        rv_main.setLayoutManager(new LinearLayoutManager(this));

        // Inicializando Adapter e Lista
        studentItems = new ArrayList<>();
        itemArrayAdapter = new ItemArrayAdapter(studentItems);

        // Setando Adapter para RecyclerView
        rv_main.setAdapter(itemArrayAdapter);

        // Inicializando Banco
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-student")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        StudentDAO studentDAO = db.studentDAO();

        loadRecyclerList(studentDAO);

        fab_Add.setOnClickListener(v -> {
            String name = edit_Name.getText().toString().trim();
            String course = edit_Course.getText().toString().trim();
            String ageStr = edit_Age.getText().toString().trim();
            int age = Integer.parseInt(ageStr);

            if (name.isEmpty() || course.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Preencha todos os campos! Algum campo está vazio.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Inserindo novo Estudante no Banco
            Student newStudent = new Student(name, course, age);
            studentDAO.insertAll(newStudent);

            loadRecyclerList(studentDAO);

            edit_Name.setText("");
            edit_Course.setText("");
            edit_Age.setText("");
        });

        // Configuração de SWIP para deletar com END pois gostei mais.
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Item item = itemArrayAdapter.getItem(position);

                Student studentToDelete = new Student(item.getName(), item.getCourse(), item.getAge());
                studentToDelete.uid = item.getUid();

                studentDAO.delete(studentToDelete);

                loadRecyclerList(studentDAO);
            }


        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_main);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadRecyclerList(StudentDAO studentDAO) {
        List<Student> students = studentDAO.getAll();
        studentItems.clear();
        for (Student student : students) {
            studentItems.add(new Item(student.uid, student.Name, student.Course, student.Age));
        }
        itemArrayAdapter.notifyDataSetChanged();
    }

}
