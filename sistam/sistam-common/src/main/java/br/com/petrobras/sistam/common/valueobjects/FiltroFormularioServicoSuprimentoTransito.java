package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;   
import br.com.petrobras.sistam.common.entity.ServicoSuprimento; 
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class FiltroFormularioServicoSuprimentoTransito extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<ServicoSuprimentoTransitoVO> suprimentoTransitoVO = Collections.EMPTY_LIST;
    private ServicoSuprimento suprimentoTransito = new ServicoSuprimento();

    
    public List<ServicoSuprimentoTransitoVO> getSuprimentoTransitoVO() {
        return suprimentoTransitoVO;
    }

    public void setSuprimentoTransitoVO(List<ServicoSuprimentoTransitoVO> suprimentoTransitoVO) {
         this.suprimentoTransitoVO = suprimentoTransitoVO;
        firePropertyChange("suprimentoTransitoVO", null, null);
    }

    public ServicoSuprimento getSuprimentoTransito ()  {
        return suprimentoTransito;
    }

    public void setSuprimentoTransito (ServicoSuprimento suprimentoTransito ) {
        this.suprimentoTransito = suprimentoTransito;
        firePropertyChange("suprimentoTransito", null, null);
    }     
}
