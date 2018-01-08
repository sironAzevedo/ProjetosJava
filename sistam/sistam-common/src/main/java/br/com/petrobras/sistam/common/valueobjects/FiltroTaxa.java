package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Collections;

public class FiltroTaxa extends AbstractBean implements Serializable {
    public static final String PROP_AGENCIA = "agencia";
    
    private static final long serialVersionUID = 1L;
    private Agencia agencia;
    private Porto porto;
    private Date dataPagamentoInicial;
    private Date dataPagamentoFinal;
    private List<TipoTaxa> listaTipoTaxa;
    private List<ResponsavelCustoEntity> responsaveis = Collections.EMPTY_LIST;

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, this.agencia);
    }

    public Porto getPorto() { 
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
        firePropertyChange("porto", null, this.porto);
    }

    public Date getDataPagamentoInicial() {
        return dataPagamentoInicial;
    }

    public void setDataPagamentoInicial(Date dataPagamentoInicial) {
        this.dataPagamentoInicial = dataPagamentoInicial;
        firePropertyChange("dataPagamentoInicial", null, this.dataPagamentoInicial);
    }

    public Date getDataPagamentoFinal() {
        return dataPagamentoFinal;
    }

    public void setDataPagamentoFinal(Date dataPagamentoFinal) {
        this.dataPagamentoFinal = dataPagamentoFinal;
        firePropertyChange("dataPagamentoFinal", null, this.dataPagamentoFinal);
    }

    public List<TipoTaxa> getListaTipoTaxa() {
        return listaTipoTaxa;
    }

    public void setListaTipoTaxa(List<TipoTaxa> listaTipoTaxa) {
        this.listaTipoTaxa = listaTipoTaxa;
    }
    
     public List<ResponsavelCustoEntity> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<ResponsavelCustoEntity> responsaveis) {
        this.responsaveis = responsaveis;
    } 
    
    public String getResponsaveisDescricao() {
        StringBuilder nomes = new StringBuilder();
        for (ResponsavelCustoEntity responsavel : responsaveis) {
            if (nomes.length() != 0) {
                nomes.append(", ");
            }
            nomes.append(responsavel.getDescricao());
        }
        return nomes.toString();
    }
}
