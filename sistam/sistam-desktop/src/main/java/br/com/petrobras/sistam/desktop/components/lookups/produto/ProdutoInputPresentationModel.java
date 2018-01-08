package br.com.petrobras.sistam.desktop.components.lookups.produto;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.Collections;
import java.util.List;

public class ProdutoInputPresentationModel extends AbstractBean {
    private List<Produto> produtos;
    private Produto produtoSelecionado;
    private boolean confirmado;
    private ProdutoInput comboPai;
    private String nome;    

    public ProdutoInputPresentationModel() {
        produtos = Collections.<Produto>emptyList();
    }

    public void consultar() {
            AssertUtils.assertExpression(nome != null && nome.length() >= 3, ConstantesI18N.CONSULTA_LOOKUP);               
            List l =  comboPai.buscarPorNomeProduto(nome);
            setProdutos(l);
            AssertUtils.assertNotNullNotEmpty(l, ConstantesI18N.NENHUM_PRODUTO_ENCONTRADO);                
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {        
        this.produtos = produtos;
        firePropertyChange("produtos", null, this.produtos);
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public ProdutoInput getComboPai() {
        return comboPai;
    }

    public boolean isConfirmado() {
        return confirmado;
    }


    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
        firePropertyChange("produtoSelecionado", null, this.produtoSelecionado);
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
        firePropertyChange("confirmado", null, this.confirmado);
    }

    public void setComboPai(ProdutoInput comboPai) {
        this.comboPai = comboPai;
        firePropertyChange("comboPai", null, this.comboPai);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, this.nome);
    }
    
}