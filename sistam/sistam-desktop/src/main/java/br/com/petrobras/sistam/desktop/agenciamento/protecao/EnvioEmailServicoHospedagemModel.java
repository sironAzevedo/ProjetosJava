package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.enums.TipoCentroCusto;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;
import br.com.petrobras.sistam.common.valueobjects.PrevisualizacaoEmailVO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnvioEmailServicoHospedagemModel extends BasePresentationModel<SistamService> {
    
    private List<Servico> agenciasViagem;
    private List<TipoCentroCusto> tiposCentroCusto;
    private FiltroEnvioSolicitacaoHospedagem filtro = new FiltroEnvioSolicitacaoHospedagem();
    private boolean enviado = false;
    
    public EnvioEmailServicoHospedagemModel(ServicoHospedagem servicoHospedagem) {
        filtro.setServicoHospedagem(getService().buscarServicoHospedagemPorId(servicoHospedagem.getId()));
        setTiposCentroCusto(new ArrayList<TipoCentroCusto>(Arrays.asList(TipoCentroCusto.values())));
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Servico> getAgenciasViagem() {
        return agenciasViagem;
    }

    public void setAgenciasViagem(List<Servico> agenciasViagem) {
        this.agenciasViagem = agenciasViagem;
        firePropertyChange("agenciasViagem", null, null);
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public List<TipoCentroCusto> getTiposCentroCusto() {
        return tiposCentroCusto;
    }

    public void setTiposCentroCusto(List<TipoCentroCusto> tiposCentroCusto) {
        this.tiposCentroCusto = tiposCentroCusto;
        firePropertyChange("tiposCentroCusto", null, null);
    }
    
    public FiltroEnvioSolicitacaoHospedagem getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroEnvioSolicitacaoHospedagem filtro) {
        this.filtro = filtro;
        firePropertyChange("filtro", null, null);
    }
    
    //</editor-fold>
    
    public void enviar(){
        getService().enviarEmailSolicitacaoHospedagem(filtro);
    }
    
    public PrevisualizacaoEmailVO getPrevisualizar(){
        PrevisualizacaoEmailVO previsualizacaoEmailVO = new PrevisualizacaoEmailVO();
        previsualizacaoEmailVO.setCorpo(getService().montarCorpoEmailSolicitacaoHospedagem(filtro));
        previsualizacaoEmailVO.setPara(new ArrayList(getService().destinatariosEmailSolicitacaoHospedagem(filtro)));
        return previsualizacaoEmailVO;
    }
    
    public void carregarAgenciasViagem(){
        List<Servico> lista = new ArrayList<Servico>();
        lista.add(0, null);
        lista.addAll(1, getService().buscarServicosPorTipo(TipoServico.AGENCIA_VIAGEM));
        
        setAgenciasViagem(lista);
    }
}