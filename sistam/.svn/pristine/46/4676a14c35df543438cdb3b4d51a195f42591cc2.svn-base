package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class DocumentacaoDescargaCabotagemModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<DocumentacaoCabotagem> listaDocumentacao = new SerializableObservableList<DocumentacaoCabotagem>();
    private List<DocumentacaoOperacao> listaDocumentacaoProduto = new SerializableObservableList<DocumentacaoOperacao>();
    private DocumentacaoCabotagem documentacaoSelecionada;
    private DocumentacaoOperacao documentacaoProdutoSelecionada;
    
    public DocumentacaoDescargaCabotagemModel(Agenciamento agenciamento){
        this.agenciamento = agenciamento;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public List<DocumentacaoCabotagem> getListaDocumentacao() {
        return listaDocumentacao;
    }

    public List<DocumentacaoOperacao> getListaDocumentacaoProduto() {
        return listaDocumentacaoProduto;
    }
    
    public DocumentacaoCabotagem getDocumentacaoSelecionada() {
        return documentacaoSelecionada;
    }
    
    public void setDocumentacaoSelecionada(DocumentacaoCabotagem documentacaoSelecionada) {
        this.documentacaoSelecionada = documentacaoSelecionada;
        firePropertyChange("documentacaoSelecionada", null, null);
        carregarListaDeDocuemtancaoProduto();
    }

    public DocumentacaoOperacao getDocumentacaoProdutoSelecionada() {
        return documentacaoProdutoSelecionada;
    }

    public void setDocumentacaoProdutoSelecionada(DocumentacaoOperacao documentacaoProdutoSelecionada) {
        Object old = this.documentacaoProdutoSelecionada;
        this.documentacaoProdutoSelecionada = documentacaoProdutoSelecionada;
        firePropertyChange("documentacaoProdutoSelecionada", old, this.documentacaoProdutoSelecionada);
    }
    
    //</editor-fold>
    
    public void excluirProduto() {
        getService().excluirDocumentacaoOperacao(getService().buscarDocumentacaoOperacaoPorId(documentacaoProdutoSelecionada.getId()));
        documentacaoSelecionada.removerDocumentacaoProduto(documentacaoProdutoSelecionada);
        listaDocumentacaoProduto.remove(documentacaoProdutoSelecionada);
    }
    
    public void excluirDocumentacao(){
        getService().excluirDocumentacaoCabotagem(documentacaoSelecionada);
        listaDocumentacao.remove(documentacaoSelecionada);
    }
        
    public void carregarListaDeDocumentacao(){
        listaDocumentacao.addAll(getService().buscarDocumentacaoCabotagemPorAgenciamentoETipo(agenciamento, TipoOperacao.DESCARGA_CABOTAGEM));
    }
    
    public void carregarListaDeDocuemtancaoProduto(){
        listaDocumentacaoProduto.clear();
        
        if (documentacaoSelecionada != null){
            listaDocumentacaoProduto.addAll(documentacaoSelecionada.getDocumentacaoProdutos());
        }
    }
    
    public DocumentacaoCabotagem obterNovaDocumentacao(){
        DocumentacaoCabotagem documentacao = new DocumentacaoCabotagem();
        documentacao.setAgenciamento(agenciamento);
        documentacao.setTipoOperacao(TipoOperacao.DESCARGA_CABOTAGEM);
        return documentacao;
    }
    
    public DocumentacaoCabotagem obterDocumetacaoParaEdicao(){
        DocumentacaoCabotagem clone =  (DocumentacaoCabotagem)documentacaoSelecionada.clone();
        
        //atualiza o agenciamento para evitar erro de lazy.
        clone.setAgenciamento(agenciamento);
        return clone;
    }
    
    public void adicionarDocumentacao(DocumentacaoCabotagem documentacao){
        listaDocumentacao.add(documentacao);
    }
    
    public void atualizarDocumentacao(DocumentacaoCabotagem documentacao){
        listaDocumentacao.remove(documentacaoSelecionada);
        listaDocumentacao.add(documentacao);
    }
    
    public void adicionarProduto(DocumentacaoOperacao documentacaoProduto){
        documentacaoSelecionada.adicionarDocumentacaoProduto(documentacaoProduto);
        listaDocumentacaoProduto.add(documentacaoProduto);
    }
    
    public void atualizarProduto(DocumentacaoOperacao documentacaoProduto){
        documentacaoSelecionada.removerDocumentacaoProduto(documentacaoProdutoSelecionada);
        documentacaoSelecionada.adicionarDocumentacaoProduto(documentacaoProduto);
        listaDocumentacaoProduto.remove(documentacaoProdutoSelecionada);
        listaDocumentacaoProduto.add(documentacaoProduto);
    }
    
    public DocumentacaoOperacao obterNovaDocumetacaoProduto(){
        DocumentacaoOperacao novo = new DocumentacaoOperacao();
        novo.setDocumentacaoOperacao(documentacaoSelecionada);
        return novo;
    }
    
    public DocumentacaoOperacao obterDocumentacaoProdutoParaEdicao(){
         DocumentacaoOperacao documentacaoProduto = (DocumentacaoOperacao)documentacaoProdutoSelecionada.clone();
         return documentacaoProduto;
    }
}
