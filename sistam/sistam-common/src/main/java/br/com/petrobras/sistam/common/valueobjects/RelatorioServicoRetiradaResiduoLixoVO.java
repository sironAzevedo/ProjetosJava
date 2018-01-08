package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class RelatorioServicoRetiradaResiduoLixoVO extends AbstractBean implements Serializable { 
    
    private List<RelatorioRetiradaResiduoLixoVO> retiradaResiduoLixoVOs = new ArrayList<RelatorioRetiradaResiduoLixoVO>();
   
    public RelatorioServicoRetiradaResiduoLixoVO(){}
     
    
    public List<RelatorioRetiradaResiduoLixoVO> getRetiradaResiduoLixoVOs() {
        return retiradaResiduoLixoVOs;
    }

    public void setRetiradaResiduoLixoVOs(List<RelatorioRetiradaResiduoLixoVO> retiradaResiduoLixoVOs) {
        this.retiradaResiduoLixoVOs = retiradaResiduoLixoVOs;
        firePropertyChange("retiradaResiduoLixoVOs", null, null);
    } 
    
    
}
