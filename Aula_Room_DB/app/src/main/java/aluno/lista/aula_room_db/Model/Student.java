package aluno.lista.aula_room_db.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String Name;

    @ColumnInfo(name = "course")
    public String Course;

    @ColumnInfo(name = "age")
    public int Age;

    public Student() {
    }

    public Student(String name, String course, int age) {
        this.Name = name;
        this.Course = course;
        this.Age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", Course='" + Course + '\'' +
                ", Age=" + Age +
                '}';
    }

}
