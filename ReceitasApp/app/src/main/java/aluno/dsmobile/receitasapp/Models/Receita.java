package aluno.dsmobile.receitasapp.Models;

import java.util.List;

public class Receita {
    private String id;
    private String titulo;
    private List<String> ingredientes;
    private List<String> instrucoes;
    private String imagemUrl;
    private List<String> tiposDeAlimentacao;

    // Construtor padrão
    public Receita() {
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<String> getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(List<String> instrucoes) {
        this.instrucoes = instrucoes;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public List<String> getTiposDeAlimentacao() {
        return tiposDeAlimentacao;
    }

    public void setTiposDeAlimentacao(List<String> tiposDeAlimentacao) {
        this.tiposDeAlimentacao = tiposDeAlimentacao;
    }
}
