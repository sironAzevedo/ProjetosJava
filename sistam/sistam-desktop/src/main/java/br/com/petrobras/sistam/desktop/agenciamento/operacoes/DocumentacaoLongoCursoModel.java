package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class DocumentacaoLongoCursoModel extends BasePresentationModel<SistamService> {
    private Operacao operacao;
    private List<DocumentacaoLongoCurso> listaDocumentacao = new SerializableObservableList<DocumentacaoLongoCurso>();
    private DocumentacaoLongoCurso documentacaoSelecionada;
    
    public DocumentacaoLongoCursoModel(Operacao operacao){
        this.operacao = operacao;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    
    public Operacao getOperacao() {
        return operacao;
    }
    
    public List<DocumentacaoLongoCurso> getListaDocumentacao() {
        return listaDocumentacao;
    }

    public void setDocumentacaoSelecionada(DocumentacaoLongoCurso documentacaoSelecionada) {
        this.documentacaoSelecionada = documentacaoSelecionada;
        firePropertyChange("documentacaoSelecionada", null, null);
    }

    public DocumentacaoLongoCurso getDocumentacaoSelecionada() {
        return documentacaoSelecionada;
    }

    
    //</editor-fold>
    
    public void excluirDocumentacao(){
        getService().excluirDocumentacaoLongoCurso(documentacaoSelecionada);
        listaDocumentacao.remove(documentacaoSelecionada);
    }
    
    public void carregarListaDeDocumentacao(){
        listaDocumentacao.addAll(getService().buscarDocumentacaoLongoCursoPorOperacao(operacao));
    }
    
    public void adicionarDocumentacao(DocumentacaoLongoCurso documentacao){
        listaDocumentacao.add(documentacao);
    }
    
    public void atualizarDocumentacao(DocumentacaoLongoCurso documentacao){
        listaDocumentacao.remove(documentacaoSelecionada);
        listaDocumentacao.add(documentacao);
    }
    
    public DocumentacaoLongoCurso obterNovaDocumentacao(){
        DocumentacaoLongoCurso documentacao = new DocumentacaoLongoCurso();
        documentacao.setOperacao(operacao);
        return documentacao;
    }
    
    public DocumentacaoLongoCurso obterDocumetacaoParaEdicao(){
        DocumentacaoLongoCurso clone =  (DocumentacaoLongoCurso)documentacaoSelecionada.clone();
        return clone;
    }

}
