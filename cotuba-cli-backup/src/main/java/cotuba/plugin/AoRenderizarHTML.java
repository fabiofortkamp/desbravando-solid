package cotuba.plugin;

import java.util.ServiceLoader;

public interface AoRenderizarHTML {


    static String renderizou(String html) {
        String htmlModificado = html;
        for (AoRenderizarHTML plugin :
                ServiceLoader.load(AoRenderizarHTML.class)) {
            htmlModificado = plugin.aposRenderizacao(htmlModificado);
        }

        return htmlModificado;
    }


    String aposRenderizacao(String html);
}
