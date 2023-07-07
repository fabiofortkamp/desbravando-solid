package br.com.cognitio.estatisticas;

import java.util.Map;
import java.util.TreeMap;

class ContagemDePalavras {

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

    public Iterable<? extends Map.Entry<String, Integer>> entrySet() {
        return map.entrySet();
    }
}
