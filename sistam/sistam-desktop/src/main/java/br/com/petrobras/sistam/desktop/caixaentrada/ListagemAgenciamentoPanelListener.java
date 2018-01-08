package br.com.petrobras.sistam.desktop.caixaentrada;

import br.com.petrobras.sistam.common.entity.Agenciamento;

public interface ListagemAgenciamentoPanelListener {
    public static final String ESCONDER = "esconderListagem";
    public static final String SELECIONAR = "selecionarEmbarcacao";

    public void esconderListagem(ListagemAgenciamentoPanel listagem);
    
    public void selecionarEmbarcacao(Agenciamento movimentacaoSelecionada);

}
