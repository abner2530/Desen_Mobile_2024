package aluno.lista.aula_room_db.Adapter;

public class Item {
    private final int uid;
    private final String name;
    private final String course;
    private final int age;

    public Item(int uid, String name, String course, int age) {
        this.uid = uid;
        this.name = name;
        this.course = course;
        this.age = age;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }
}
