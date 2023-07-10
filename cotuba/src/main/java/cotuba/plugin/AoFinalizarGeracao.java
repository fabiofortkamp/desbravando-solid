package cotuba.plugin;

import cotuba.domain.Ebook;

import java.util.ServiceLoader;

public interface AoFinalizarGeracao {

    static void gerou(EbookSoParaLeitura ebook) {
        ServiceLoader.load(AoFinalizarGeracao.class)
                .forEach(
                        plugin -> {
                            plugin.aposGeracao(ebook);
                        }
                );
    }


    void aposGeracao(EbookSoParaLeitura ebook);
}
