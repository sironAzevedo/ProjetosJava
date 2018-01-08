package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import java.io.Serializable;
import java.util.Date;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class FiltroFormularioRetiradaResiduoLixo extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo ;
    private RelatorioServicoRetiradaResiduoLixoVO servicoRetiradaResiduoLixoVO;
    private RepresentanteLegal listaRepresentante;
    private Date dataPeriodo;
    
    
    public FiltroFormularioRetiradaResiduoLixo(){
        
    } 
    
    
    public RepresentanteLegal getListaRepresentante() {
        return listaRepresentante;
    }

    public void setListaRepresentante(RepresentanteLegal listaRepresentante) {
        this.listaRepresentante = listaRepresentante;
    } 

    public Date getDataPeriodo() {
        return dataPeriodo;
    }

    public void setDataPeriodo(Date dataPeriodo) {
        this.dataPeriodo = dataPeriodo;
    }
    public ServicoRetiradaResiduoLixo getServicoRetiradaResiduoLixo() {
        return servicoRetiradaResiduoLixo;
        
    }
    
    public void setServicoRetiradaResiduoLixo(ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo) {
            this.servicoRetiradaResiduoLixo = servicoRetiradaResiduoLixo;
            firePropertyChange("servicoRetiradaResiduoLixo", null, null);
        } 
    
    public RelatorioServicoRetiradaResiduoLixoVO getServicoRetiradaResiduoLixoVO() {
        return servicoRetiradaResiduoLixoVO;
    }

    public void setServicoRetiradaResiduoLixoVO(RelatorioServicoRetiradaResiduoLixoVO servicoRetiradaResiduoLixoVO) {
        this.servicoRetiradaResiduoLixoVO = servicoRetiradaResiduoLixoVO;
        firePropertyChange("servicoRetiradaResiduoLixoVO", null, null);
    }
    
    
}
