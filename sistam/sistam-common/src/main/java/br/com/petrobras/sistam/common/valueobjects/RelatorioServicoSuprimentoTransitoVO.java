package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class RelatorioServicoSuprimentoTransitoVO extends AbstractBean implements Serializable {
    private Integer pagina;
    private List<SuprimentoTransitoFornecedorVO> transitoFornecedorVOs = new ArrayList<SuprimentoTransitoFornecedorVO>();
    private List<SuprimentoTransitoCondutorVO> transitoCondutorVOs = new ArrayList<SuprimentoTransitoCondutorVO>();

    public RelatorioServicoSuprimentoTransitoVO(){}
    
    public RelatorioServicoSuprimentoTransitoVO(Integer pagina){
        this.pagina = pagina;
    }

    public List<SuprimentoTransitoFornecedorVO> getTransitoFornecedorVOs() {
        return transitoFornecedorVOs;
    }

    public void setTransitoFornecedorVOs(List<SuprimentoTransitoFornecedorVO> transitoFornecedorVOs) {
        this.transitoFornecedorVOs = transitoFornecedorVOs;
        firePropertyChange("transitoFornecedorVOs", null, null);
    }

    public List<SuprimentoTransitoCondutorVO> getTransitoCondutorVOs() {
        return transitoCondutorVOs;
    }

    public void setTransitoCondutorVOs(List<SuprimentoTransitoCondutorVO> transitoCondutorVOs) {
        this.transitoCondutorVOs = transitoCondutorVOs;
        firePropertyChange("transitoCondutorVOs", null, null);
    } 
    
    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
        firePropertyChange("pagina", null, null);
    }
}
