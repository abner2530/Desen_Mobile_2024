package com.example.atividade2;

public class Item {
    private String name;
    private String descricao;
    private int imageResource;

    public Item(String name, String descricao, int imageResource) {
        this.name = name;
        this.descricao = descricao;
        this.imageResource = imageResource;
    }
    public String getName() {
        return name;
    }

    public String getDescricao(){ return descricao; }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
