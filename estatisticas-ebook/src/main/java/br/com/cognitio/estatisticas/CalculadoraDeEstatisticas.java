package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CalculadoraDeEstatisticas implements Plugin {

    @Override
    public String aposRenderizacao(String html) {
        return html;
    }

    @Override
    public void aposGeracao(Ebook ebook) {

        ContagemDePalavras contagemDePalavras = new ContagemDePalavras();

        for (Capitulo capitulo : ebook.getCapitulos()) {
            String conteudoHTML = capitulo.getConteudoHTML();
            Document document = Jsoup.parse(conteudoHTML);

            String textoDoCapitulo = document.body().text();
            String textoDoCapituloSemPontuacao = textoDoCapitulo.replaceAll("\\p{Punct}", " ");

            String textoDecomposto = Normalizer.normalize(textoDoCapituloSemPontuacao, Normalizer.Form.NFD);
            String textoDoCapituloSemAcentos = textoDecomposto.replaceAll("[^\\p{ASCII}]", "");
            String textoDoCapituloEmMaiusculas = textoDoCapituloSemAcentos.toUpperCase();

            String[] palavras = textoDoCapituloEmMaiusculas.split("\\s+");
            for (String palavra : palavras) {
                contagemDePalavras.adicionaPalavra(palavra);
            }
        }

        for (Map.Entry<String, Integer> contagem : contagemDePalavras.entrySet()) {
            String palavra = contagem.getKey();
            Integer ocorrencias = contagem.getValue();
            System.out.println(palavra + ": " + ocorrencias);
        }


    }
}
