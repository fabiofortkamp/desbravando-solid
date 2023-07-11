package br.com.cognitio.estatisticas;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class ContagemDePalavras implements Iterable<ContagemDePalavras.Contagem>{

    static record Contagem(String palavra, int ocorrencias) {}

    private final Map<String, Integer> map = new TreeMap<>();
    void adicionaPalavra(String palavra) {
        Integer ocorrencias = map.get(palavra);
        if (ocorrencias != null){
            ocorrencias++;
        } else {
            ocorrencias = 1;
        }
        map.put(palavra,ocorrencias);
    }

    public Iterator<Contagem> iterator() {

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

        return new Iterator<Contagem>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Contagem next() {
                Map.Entry<String, Integer> entry = iterator.next();
                String palavra = entry.getKey();
                int ocorrencias = entry.getValue();
                return new Contagem(palavra, ocorrencias);
            }
        };
    }

}
