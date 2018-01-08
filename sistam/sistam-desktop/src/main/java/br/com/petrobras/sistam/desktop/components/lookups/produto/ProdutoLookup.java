package br.com.petrobras.sistam.desktop.components.lookups.produto;

import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Produto;
import java.util.List;
import javax.swing.ImageIcon;

public class ProdutoLookup extends ProdutoInput {

    public ProdutoLookup() {
        super();
    }

    @Override
    protected List<Produto> buscarPorNomeProduto(String nome) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarProdutos(nome);
    }

    @Override
    protected ImageIcon getValueIcon(Produto aValue) {
        return null;
    }

    @Override
    protected Produto buscarPorId(Produto produto) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarProdutoPorId(produto.getId());
    }

    @Override
    protected String valueToId(Produto value) {
        return value.getId();
    }
}
