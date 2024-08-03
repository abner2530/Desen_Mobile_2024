package aluno.lista.aula_room_db.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import aluno.lista.aula_room_db.Model.Student;

@Dao
public interface StudentDAO {

    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Query("SELECT * FROM student WHERE uid IN (:userIds)")
    List<Student> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM student WHERE name LIKE :name LIMIT 1")
    Student findByName(String name);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

}
