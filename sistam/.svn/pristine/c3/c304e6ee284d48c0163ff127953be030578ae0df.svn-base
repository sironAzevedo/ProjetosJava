package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import java.io.Serializable;
import org.hibernate.Hibernate;

public class TaxaMensalVO extends AbstractBean implements Serializable {
    public static final String PROP_AGENCIA = "agencia";
    
    private static final long serialVersionUID = 1L;
    private ResponsavelCustoEntity responsavelCusto;
    private TipoTaxa tipoTaxa;
    private Double valor;

    public TaxaMensalVO(ResponsavelCustoEntity responsavelCusto, TipoTaxa tipoTaxa, Double valor) {
        Hibernate.initialize(responsavelCusto);
        this.responsavelCusto = responsavelCusto;
        this.tipoTaxa = tipoTaxa;
        this.valor = valor;
    }
    
    public ResponsavelCustoEntity getResponsavelCusto() {
        return responsavelCusto;
    }

    public void setResponsavelCusto(ResponsavelCustoEntity responsavelCusto) {
        this.responsavelCusto = responsavelCusto;
    }

    public TipoTaxa getTipoTaxa() {
        return tipoTaxa;
    }

    public void setTipoTaxa(TipoTaxa tipoTaxa) {
        this.tipoTaxa = tipoTaxa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
