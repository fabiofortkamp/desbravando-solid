package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.plugin.AoFinalizarGeracao;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public void executa(ParametrosCotuba parametros){

        Path diretorioDosMD = parametros.getDiretorioDosMD();
        FormatoEbook formato = parametros.getFormato();
        Path arquivoDeSaida = parametros.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        GeradorEbook geradorEbook = formato.getGerador();

        geradorEbook.gera(ebook);

        AoFinalizarGeracao.gerou(ebook);
    }
}
