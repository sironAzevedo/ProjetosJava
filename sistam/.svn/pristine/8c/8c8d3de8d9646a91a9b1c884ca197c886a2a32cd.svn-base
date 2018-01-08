package br.com.petrobras.sistam.desktop.agenciamento.documento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import java.util.ArrayList;
import java.util.List;

public class SelecionarOperacaoModel extends BasePresentationModel<SistamService> {
    
    private List<Operacao> listaOperacoes;
    private Operacao operacaoSelecionada;
    
    public SelecionarOperacaoModel(List<Operacao> listaOperacoes) {
        carregarOperacoesCargaExportacao(listaOperacoes);
    }
            
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Operacao> getListaOperacoes() {
        return listaOperacoes;
    }

    public Operacao getOperacaoSelecionada() {
        return operacaoSelecionada;
    }

    public void setOperacaoSelecionada(Operacao operacaoSelecionada) {
        this.operacaoSelecionada = operacaoSelecionada;
        firePropertyChange("operacaoSelecionada", null, null);
    }

  
    //</editor-fold>
    
    /**
     * Carrega apenas as operações do tipo Carga Exportação
     */
    private void carregarOperacoesCargaExportacao(List<Operacao> listaOperacoes){
        this.listaOperacoes = new ArrayList<Operacao>();
        
        for (Operacao operacao : listaOperacoes){
            if (TipoOperacao.CARGA_EXPORTACAO.equals(operacao.getTipoOperacao())){
                this.listaOperacoes.add(operacao);
            }
        }
    }
    
}
