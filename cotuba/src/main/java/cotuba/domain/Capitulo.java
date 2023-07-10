package cotuba.domain;

import cotuba.plugin.CapituloSoParaLeitura;

public class Capitulo implements CapituloSoParaLeitura {

    @Override
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private String titulo;

    @Override
    public String getConteudoHTML() {
        return conteudoHTML;
    }

    public void setConteudoHTML(String conteudoHTML) {
        this.conteudoHTML = conteudoHTML;
    }

    private String conteudoHTML;


}
