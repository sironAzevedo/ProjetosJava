package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean; 
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import java.io.Serializable;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class ServicoSuprimentoTransitoVO extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ServicoSuprimentoTransito suprimentoTransito;
    private boolean selecionado;

    public ServicoSuprimentoTransito getSuprimentoTransito() {
        return suprimentoTransito;
    }

    public void setSuprimentoTransito(ServicoSuprimentoTransito suprimentoTransito) {
        this.suprimentoTransito = suprimentoTransito;
        firePropertyChange("suprimentoTransito", null, null);
    }
    
    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        firePropertyChange("selecionado", null, null);
    }
}
