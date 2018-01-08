package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.valueobjects.PrevisualizacaoEmailVO;
import java.util.ArrayList;
import java.util.List;

public class EnvioEmailServicoRetiradaResiduoLixoModel extends BasePresentationModel<SistamService> {
    
    private List<ResponsavelCusto> listaResponsavelCustos;
    private FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro = new FiltroEnvioSolicitacaoRetiradaResiduoLixo();
    private boolean enviado = false;
    
    public EnvioEmailServicoRetiradaResiduoLixoModel(ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo) {
        filtro.setServicoRetiradaResiduoLixo(getService().buscarServicoRetiradaResiduoLixoPorId(servicoRetiradaResiduoLixo.getId()));
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    
    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
    
    public List<ResponsavelCusto> getListaResponsavelCustos() {
        return listaResponsavelCustos;
    }

    public void setListaResponsavelCustos(List<ResponsavelCusto> listaResponsavelCustos) {
        this.listaResponsavelCustos = listaResponsavelCustos;
    }

    public FiltroEnvioSolicitacaoRetiradaResiduoLixo getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro) {
        this.filtro = filtro;
        firePropertyChange("filtro", null, null);
    } 

    //</editor-fold>
    
    public void enviar(){
        getService().enviarFormularioSolicitacaoRetiradaResiduoLixo(filtro);
    }
    
    public PrevisualizacaoEmailVO getPrevisualizar(){
        PrevisualizacaoEmailVO previsualizacaoEmailVO = new PrevisualizacaoEmailVO();
        previsualizacaoEmailVO.setCorpo(getService().montarCorpoEmailSolicitacaoRetiradaResiduoLixo(filtro));
        previsualizacaoEmailVO.setPara(new ArrayList(getService().destinatariosEmailSolicitacaoRetiradaResiduoLixo(filtro)));
        return previsualizacaoEmailVO;
    }
    
}