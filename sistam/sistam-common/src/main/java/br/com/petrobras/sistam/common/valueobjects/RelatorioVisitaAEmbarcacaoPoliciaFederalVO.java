package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author adzk
 */
public class RelatorioVisitaAEmbarcacaoPoliciaFederalVO extends AbstractBean {
    private Integer pagina;        
    private List<VisitaEmbarcacaoVO> visitantes = new ArrayList<VisitaEmbarcacaoVO>();  
    
    public static final int MAXIMO_FUNCIONARIOS_POR_PAGINA = 3;     

    public RelatorioVisitaAEmbarcacaoPoliciaFederalVO() {
        
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }    

    public List<VisitaEmbarcacaoVO> getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(List<VisitaEmbarcacaoVO> visitantes) {
        this.visitantes = visitantes;
    }  
}