package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class RelatorioPessoaPoliciaFederalVO extends AbstractBean implements Serializable {
    private Integer pagina;
    private List<PessoaPoliciaFederalVO> pessoaPoliciaFederalVOs = new ArrayList<PessoaPoliciaFederalVO>(); 

    public RelatorioPessoaPoliciaFederalVO(){}
    
    public RelatorioPessoaPoliciaFederalVO(Integer pagina){
        this.pagina = pagina;
    }

    public List<PessoaPoliciaFederalVO> getPessoaPoliciaFederalVOs() {
        return pessoaPoliciaFederalVOs;
    }

    public void setPessoaPoliciaFederalVOs(List<PessoaPoliciaFederalVO> pessoaPoliciaFederalVOs) {
        this.pessoaPoliciaFederalVOs = pessoaPoliciaFederalVOs;
        firePropertyChange("pessoaPoliciaFederalVOs", null, null);
    }   
    
    public Integer getPagina() {
        return pagina;
    }
    
    public void setPagina(Integer pagina) {
        this.pagina = pagina;
        firePropertyChange("pagina", null, null);
    } 
}
