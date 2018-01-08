package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.UnidadeMedida;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalheOperacaoProdutoDialogModel extends BasePresentationModel<SistamService> {
    private Operacao operacao;
    private List<TipoOperacao> listaTipoOperacao;
    private List<UnidadeMedida> listaUnidadeMedida;
    private boolean salvo;
    
    public DetalheOperacaoProdutoDialogModel(Operacao operacao){
        this.operacao = operacao;
        
        listaTipoOperacao = new ArrayList<TipoOperacao> (Arrays.asList(TipoOperacao.values()));
        listaTipoOperacao.add(0, null);
        
        listaUnidadeMedida = new ArrayList<UnidadeMedida> (Arrays.asList(UnidadeMedida.values()));
        listaUnidadeMedida.add(0, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

    public Operacao getOperacao() {
        return operacao;
    }
    
    public List<TipoOperacao> getListaTipoOperacao() {
        return listaTipoOperacao;
    }

    public List<UnidadeMedida> getListaUnidadeMedida() {
        return listaUnidadeMedida;
    }

    public boolean isSalvo() {
        return salvo;
    }

    //</editor-fold>

    void salvar() {
        operacao = getService().salvarOperacao(operacao);
        salvo = true;
    }
    
}
