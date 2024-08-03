package aluno.lista.aula_room_db.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import aluno.lista.aula_room_db.DAO.StudentDAO;
import aluno.lista.aula_room_db.Model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
