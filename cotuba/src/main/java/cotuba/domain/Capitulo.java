package cotuba.domain;

import cotuba.plugin.CapituloSoParaLeitura;

public final class Capitulo implements CapituloSoParaLeitura {

    public Capitulo(String titulo, String conteudoHTML) {
        this.titulo = titulo;
        this.conteudoHTML = conteudoHTML;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    private final String titulo;

    @Override
    public String getConteudoHTML() {
        return conteudoHTML;
    }

    private final String conteudoHTML;


}
