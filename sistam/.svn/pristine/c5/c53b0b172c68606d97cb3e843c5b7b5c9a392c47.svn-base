package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.ServicoExecutado;

public class CancelamentoDoServicoProtecaoModel extends BasePresentationModel<SistamService> {

    private ServicoExecutado servicoExecutado;
    
    private String nomeUsuarioLogado;

    public CancelamentoDoServicoProtecaoModel(ServicoExecutado servicoExecutado) {
        this.servicoExecutado = servicoExecutado;
        nomeUsuarioLogado = getService().buscarDadosDeAutenticacao().getUsername();
    }

    public ServicoExecutado getServicoExecutado() {
        return servicoExecutado;
    }

    public void setServicoExecutado(ServicoExecutado servicoExecutado) {
        this.servicoExecutado = servicoExecutado;
        firePropertyChange("servicoExecutado", null, null);
    }

    public String getNomeUsuarioLogado() {
        return nomeUsuarioLogado;
    }

    public void setNomeUsuarioLogado(String nomeUsuarioLogado) {
        this.nomeUsuarioLogado = nomeUsuarioLogado;
        firePropertyChange("nomeUsuarioLogado", null, this.nomeUsuarioLogado);
    }

    public boolean isSituacaoCancelado() {
        return (servicoExecutado != null && servicoExecutado.getServicoProtecao() != null && servicoExecutado.getServicoProtecao().getDataCancelamento() != null) ? true : false;
    }
    
    public void cancelar() {
        getService().cancelarServicoExecutado(servicoExecutado);
    }

}
