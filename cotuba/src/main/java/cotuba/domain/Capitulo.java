package cotuba.domain;

public final class Capitulo {

    public Capitulo(String titulo, String conteudoHTML) {
        this.titulo = titulo;
        this.conteudoHTML = conteudoHTML;
    }

    public String getTitulo() {
        return titulo;
    }

    private final String titulo;

    public String getConteudoHTML() {
        return conteudoHTML;
    }

    private final String conteudoHTML;


}
