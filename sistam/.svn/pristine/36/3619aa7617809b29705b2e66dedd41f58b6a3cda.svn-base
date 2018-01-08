package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class FiltroFormularioPoliciaFederalAcessoPessoa extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private RepresentanteLegal representanteLegal;
    private PessoaAcesso pessoa;
    private List<PessoaAcessoVO> pessoasVO = Collections.EMPTY_LIST;
    private ServicoAcessoPessoa servicoAcessoPessoa; 
    
    private List<VisitaEmbarcacaoVO> visitanteVOs = new ArrayList<VisitaEmbarcacaoVO>();
    private VisitaEmbarcacaoVO visitante;
    private VisitaEmbarcacaoVO visitanteSelecionado;
    
    public FiltroFormularioPoliciaFederalAcessoPessoa(){
        visitante = new VisitaEmbarcacaoVO();
    }

    public RepresentanteLegal getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(RepresentanteLegal representanteLegal) {
        this.representanteLegal = representanteLegal;
        firePropertyChange("representanteLegal", null, null);
    }

    public List<PessoaAcessoVO> getPessoasVO() {
        return pessoasVO;
    }

    public void setPessoasVO(List<PessoaAcessoVO> pessoasVO) {
        this.pessoasVO = pessoasVO;
        firePropertyChange("pessoasVO", null, null);
    }

    public ServicoAcessoPessoa getServicoAcessoPessoa() {
        return servicoAcessoPessoa;
    }

    public void setServicoAcessoPessoa(ServicoAcessoPessoa servicoAcessoPessoa) {
        this.servicoAcessoPessoa = servicoAcessoPessoa;
        firePropertyChange("servicoAcessoPessoa", null, null);
    } 
    
    public boolean isRequerimentoEmbarque(){
        return servicoAcessoPessoa.isTipoAcessoEmbarque();
    } 
    
    public boolean isRequerimentoDesembarque(){
        return servicoAcessoPessoa.isTipoAcessoDesembarque();
    }
    
    public boolean isRequerimentoAcessoEmbarcacao(){
        return servicoAcessoPessoa.isTipoAcessoAcesso();
    }
    
    public boolean isRequerimentoVisitaEmbarcacao(){
        return servicoAcessoPessoa.isTipoAcessoVisitante();
    }

    public List<VisitaEmbarcacaoVO> getVisitanteVOs() {
        return visitanteVOs;        
    }

    public void setVisitanteVOs(List<VisitaEmbarcacaoVO> visitanteVOs) {
        this.visitanteVOs = visitanteVOs;
        firePropertyChange("visitanteVOs", null, null);
    }

    public VisitaEmbarcacaoVO getVisitante() {
        return visitante;
    }

    public void setVisitante(VisitaEmbarcacaoVO visitante) {
        this.visitante = visitante;
        firePropertyChange("visitante", null, null);
    } 
    
    public VisitaEmbarcacaoVO getVisitanteSelecionado() {
        return visitanteSelecionado;
    }

    public void setVisitanteSelecionado(VisitaEmbarcacaoVO visitanteSelecionado) {
        this.visitanteSelecionado = visitanteSelecionado;
        firePropertyChange("visitanteSelecionado", null, null);
    }

    public PessoaAcesso getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaAcesso pessoa) {
        this.pessoa = pessoa;
    } 
    
}