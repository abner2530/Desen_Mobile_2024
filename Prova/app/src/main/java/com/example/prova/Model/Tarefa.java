package com.example.prova.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tarefa {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "titulo")
    public String Titulo;

    @ColumnInfo(name = "descricao")
    public String Descricao;

    public Tarefa() {
    }

    public Tarefa(String titulo, String descricao) {
        Titulo = titulo;
        Descricao = descricao;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "Titulo='" + Titulo + '\'' +
                ", Descricao='" + Descricao + '\'' +
                '}';
    }
}
