package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.enums.TipoTaxa;
import java.io.Serializable;

/**
 * Classe TaxaAcumuladoVO
 */
public class TaxaAcumuladoVO implements Serializable{
    TipoTaxa tipoTaxa;
    Double valorTotal;

    public TaxaAcumuladoVO(TipoTaxa tipoTaxa, Double valorTotal) {
        this.tipoTaxa = tipoTaxa;
        this.valorTotal = valorTotal;
    }

    public TipoTaxa getTipoTaxa() {
        return tipoTaxa;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
    
}
