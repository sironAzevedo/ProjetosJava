package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class RelatorioReceitaFederalVO extends AbstractBean implements Serializable {
    private Integer pagina;
    private List<PessoaReceitaFederalVO> pessoaReceitaFederalVOs = new ArrayList<PessoaReceitaFederalVO>();

    public RelatorioReceitaFederalVO(){}
    
    public RelatorioReceitaFederalVO(Integer pagina){
        this.pagina = pagina;
    }
    
    
    public List<PessoaReceitaFederalVO> getPessoaReceitaFederalVOs() {
        return pessoaReceitaFederalVOs;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
        firePropertyChange("pagina", null, null);
    }

    public void setPessoaReceitaFederalVOs(List<PessoaReceitaFederalVO> pessoaReceitaFederalVOs) {
        this.pessoaReceitaFederalVOs = pessoaReceitaFederalVOs;
        firePropertyChange("pessoaReceitaFederalVOs", null, null);
    }
}
