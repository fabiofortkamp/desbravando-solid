package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AoFinalizarGeracao;
import cotuba.plugin.CapituloSoParaLeitura;
import cotuba.plugin.EbookSoParaLeitura;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {


    @Override
    public void aposGeracao(EbookSoParaLeitura ebook) {

        ContagemDePalavras contagemDePalavras = new ContagemDePalavras();

        for (CapituloSoParaLeitura capitulo : ebook.getCapitulos()) {
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
