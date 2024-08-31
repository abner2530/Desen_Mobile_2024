package aluno.dsmobile.aula_appinvestidor.Model;

public class Ativo {

    String nomeAtivo;
    String noticia;
    String data;

    public Ativo() {
    }

    public Ativo(String nomeAtivo, String noticia, String data) {
        this.nomeAtivo = nomeAtivo;
        this.noticia = noticia;
        this.data = data;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public void setNomeAtivo(String nomeAtivo) {
        this.nomeAtivo = nomeAtivo;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "nomeAtivo='" + nomeAtivo + '\'' +
                ", noticia='" + noticia + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
