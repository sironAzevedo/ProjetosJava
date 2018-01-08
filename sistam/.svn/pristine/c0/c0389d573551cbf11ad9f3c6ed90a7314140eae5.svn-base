package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class OperacoesModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<Operacao> listaOperacao = new SerializableObservableList<Operacao>();
    private Operacao operacaoSelecionada;

    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
        carregarListaOperacao();
    }
    
    public List<Operacao> getListaOperacao() {
        return listaOperacao;
    }
    
    public Operacao getOperacaoSelecionada() {
        return operacaoSelecionada;
    }
    
    public void setOperacaoSelecionada(Operacao operacaoSelecionada) {
        this.operacaoSelecionada = operacaoSelecionada;
        firePropertyChange("operacaoSelecionada", null, null);
    }
    
    //</editor-fold>
    
    public void carregarListaOperacao(){
        listaOperacao.clear();
        listaOperacao.addAll(agenciamento.getOperacoes());
    }
    
    public void adicioanarNovaOperacao(Operacao operacao){
        agenciamento.adicionarOperacao(operacao);
        carregarListaOperacao();
    }
    
    public void atualizarOperacao(Operacao operacao){
        agenciamento.removerOpecacao(operacaoSelecionada);
        agenciamento.adicionarOperacao(operacao);
        carregarListaOperacao();
    }
    
    public void excluir(){
        getService().excluirOperacao(operacaoSelecionada);
        agenciamento.removerOpecacao(operacaoSelecionada);
        carregarListaOperacao();
    }
    
    public Operacao obterNovaOperacao(){
        Operacao operacao = new Operacao();
        operacao.setAgenciamento(agenciamento);
        return operacao;
    }
    
    public Operacao obterOperacaoParaEdicaoVisualizacao(){
        return (Operacao)operacaoSelecionada.clone();
    }
    
    public boolean temOperacaoCargaCabotagem(){
        for (Operacao operacao : listaOperacao){
            if (TipoOperacao.CARGA_CABOTAGEM.equals(operacao.getTipoOperacao())){
                return true;
            }
        }
        return false;
    }
    
    public boolean temOperacaoDescargaCabotagem(){
        for (Operacao operacao : listaOperacao){
            if (TipoOperacao.DESCARGA_CABOTAGEM.equals(operacao.getTipoOperacao())){
                return true;
            }
        }
        return false;
    }
    
}
