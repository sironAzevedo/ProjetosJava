package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import java.util.Comparator;

/**
 *
 */
public class AgenciamentoComparator implements Comparator<Agenciamento> {

    @Override
    public int compare(Agenciamento agenciamento1, Agenciamento agenciamento2) {
        String nomeEmbarcacao1 = agenciamento1.getNomeComposto();
        String nomeEmbarcacao2 = agenciamento2.getNomeComposto();

   
        return nomeEmbarcacao1.compareTo(nomeEmbarcacao2);
    }
}