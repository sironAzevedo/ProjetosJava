package br.com.petrobras.sistam.desktop.components.lookups.produto;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamLookupTextfield;
import java.util.List;

public abstract class ProdutoInput extends SistamLookupTextfield<Produto> {

    private ProdutoInputDialog dialog;

    public ProdutoInput() {
        setText("");
    }

    @Override
    public void showDialog() {
        showDialog(null, null);
    }

    public void showDialog(List<Produto> lista, String nome) {
        dialog = new ProdutoInputDialog(BaseApp.getApplication().getMainFrame());
        if (null == lista) {
            if (nome != null && !nome.isEmpty()) {
                lista = this.buscarPorNomeProduto(nome);
            }
        }
        dialog.getModel().setProdutos(lista);
        dialog.getModel().setNome(nome);
        dialog.getModel().setComboPai(this);
        dialog.setVisible(true);
        if (dialog.getModel().isConfirmado()) {
            setValue(dialog.getModel().getProdutoSelecionado());
        } else {
            setValue(null);
        }
        dialog = null;
    }

    @Override
    public void guessValue() {
        // Isso evita ficar reabrindo o diálogo ao perder o foco
        if ((dialog != null && dialog.isVisible()) || !this.isVisible()) {
            return;
        }

        String texto = this.getText().trim().toUpperCase();

        AssertUtils.assertExpression(texto != null && texto.length() >= 3, ConstantesI18N.CONSULTA_LOOKUP);
        List<Produto> produtos = buscarPorNomeProduto(texto);
        
        AssertUtils.assertNotNullNotEmpty(produtos, ConstantesI18N.NENHUM_PRODUTO_ENCONTRADO);
        if (produtos.size() == 1) {
            setValue(produtos.get(0));
        } else {
            showDialog(produtos, texto);
        }
    }

    protected abstract List<Produto> buscarPorNomeProduto(String nome);
}
