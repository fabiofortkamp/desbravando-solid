package cotuba.html;

import cotuba.application.GeradorEbook;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;

public class GeradorHTML implements GeradorEbook {

    @Override
    public void gera(Ebook ebook) {
        Path arquivoDeSaida = ebook.arquivoDeSaida();
        try {
            Path diretorioDosHTML = Files.createDirectory(arquivoDeSaida);
            for (Capitulo capitulo : ebook.capitulos()) {
                String html = """
                        <!DOCTYPE html>
                        <html lang="pt-br">
                            <head>
                                <meta charset="utf-8">
                                <title>%s</title>
                            </head>
                        <body>
                        %s
                        </body>
                        </html>
                        """.formatted(capitulo.titulo(), capitulo.conteudoHTML());

                Path arquivoHTMLDoCapitulo = diretorioDosHTML.resolve(nomeDoArquivoHTMLDoCapitulo(capitulo));
                Files.writeString(arquivoHTMLDoCapitulo, html, StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
            throw new IllegalStateException();
        }
    }

    private String nomeDoArquivoHTMLDoCapitulo(Capitulo capitulo) {
        return removeAcentos(capitulo.titulo().toLowerCase())
                .replaceAll("[^\\w]","")+ ".html";
    }

    private String removeAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
