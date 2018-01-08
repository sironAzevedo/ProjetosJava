package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalheServicoProtecaoHospedagemModel extends BasePresentationModel<SistamService> {

    private ServicoHospedagem servicoHospedagem;
    private Hospede hospedeSelecionado;
    private List<Servico> servicos;
    private DetalheServicoProtecaoHospedagemDialog dialog;
    private boolean editar;
    private boolean gravado;
    
    public DetalheServicoProtecaoHospedagemModel(ServicoHospedagem servicoHospedagem) {
        carregarHoteis();
        if (servicoHospedagem.getId() == null) {
            servicoHospedagem.getServicoProtecao().setDataExecucao(new Date());
            servicoHospedagem.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM);
        }else{
            servicoHospedagem = getService().buscarServicoHospedagemPorId(servicoHospedagem.getId());
            servicoHospedagem.getServicoProtecao().setNovo(false);
        }
        
        setServicoHospedagem(servicoHospedagem);
    }

    public DetalheServicoProtecaoHospedagemDialog getDialog() {
        return dialog;
    }

    public void setDialog(DetalheServicoProtecaoHospedagemDialog dialog) {
        this.dialog = dialog;
        firePropertyChange("dialog", null, null);
    }

    public ServicoHospedagem getServicoHospedagem() {
        return servicoHospedagem;
    }

    public void setServicoHospedagem(ServicoHospedagem servicoHospedagem) {
        this.servicoHospedagem = servicoHospedagem;
        firePropertyChange("servicoHospedagem", null, null);
    }

    public Hospede getHospedeSelecionado() {
        return hospedeSelecionado;
    }

    public void setHospedeSelecionado(Hospede hospedeSelecionado) {
        this.hospedeSelecionado = hospedeSelecionado;
        firePropertyChange("hospedeSelecionado", null, null);
        dialog.ativarDesativar();
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
        firePropertyChange("servicos", null, null);
    }

    public boolean isGravado() {
        return gravado;
    }

    public void setGravado(boolean gravado) {
        this.gravado = gravado;
        firePropertyChange("gravado", null, null);
    }

    public String getServicoExecutado() {
        if (servicoHospedagem == null || servicoHospedagem.getServicoProtecao() == null || servicoHospedagem.getServicoProtecao().getTipoAtendimentoServico() == null) {
            return null;
        }

        return servicoHospedagem.getServicoProtecao().getTipoAtendimentoServico().getPorExtenso();
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
        firePropertyChange("editar", null, null);
    }
    
    public void carregarHoteis(){
        List<Servico> lista = new ArrayList<Servico>();
        lista.add(0, null);
        lista.addAll(1, getService().buscarServicosPorTipo(TipoServico.HOSPEDAGEM));
        
        setServicos(lista);
    }

    public void salvar() {
        servicoHospedagem.getServicoProtecao().setEmailEnviado(false);
        servicoHospedagem.getServicoProtecao().setDataEmailEnviado(null);
        setServicoHospedagem((ServicoHospedagem) getService().salvarServicoExecutado(servicoHospedagem));
        setGravado(true);
    }
    
    public Hospede obterNovoHospede(){
        Hospede novoHospede = new Hospede();
        
        ServicoProtecao servicoProtecao = servicoHospedagem.getServicoProtecao();
        servicoProtecao.setServicoHospedagem(servicoHospedagem);
        
        novoHospede.setServicoProtecao(servicoProtecao);
        novoHospede.setAtivo(Boolean.TRUE);
                
        return novoHospede;
    }
    
    public Hospede obterHospedeParaEdicao(){
        
        servicoHospedagem.getServicoProtecao().setServicoHospedagem(servicoHospedagem);
        
        Hospede hospedeParaEdicao = (Hospede) hospedeSelecionado.clone();
        hospedeParaEdicao.setServicoProtecao(servicoHospedagem.getServicoProtecao());
        
        return (Hospede) hospedeParaEdicao.clone();
    }

    public void adicionarHospede(Hospede hospede){
        servicoHospedagem.adicionarHospede(hospede);
    }

    public void atualizarHospede(Hospede hospede){
        servicoHospedagem.removerHospede(hospedeSelecionado);
        servicoHospedagem.adicionarHospede(hospede);
    }
    
    public void excluirHospede(){
        servicoHospedagem.removerHospede(hospedeSelecionado);
    }
    
    public void ativarCancelarHospede(){
        Hospede hospedeAtualizado = hospedeSelecionado;
        hospedeAtualizado.setAtivo(!hospedeAtualizado.isAtivo());
        
        servicoHospedagem.removerHospede(hospedeSelecionado);
        servicoHospedagem.adicionarHospede(hospedeAtualizado);
    }
}
