package com.example.atividade3;

public class ModelCard {
    String titulo;
    String descricao;
    int background;

    ModelCard(String titulo, String descricao, int background) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.background = background;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

}
