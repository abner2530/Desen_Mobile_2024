package ufc.aluno.lista_tarefas.Adapter;

public class Item {
    private final int uid;
    private final String titulo;
    private final String descricao;

    public Item(int uid, String titulo, String descricao) {
        this.uid = uid;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getUid() {
        return uid;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
