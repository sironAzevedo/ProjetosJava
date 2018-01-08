package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

public class DocumentosPainelModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<Documento> listaDeDocumentos = new SerializableObservableList<Documento>();
    private Documento documentoSelecionado;
    

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        carregarListaDeDocumentos();
        firePropertyChange("timeZone", null, null);
    }
    
    public List<Documento> getListaDeDocumentos() {
        return listaDeDocumentos;
    }

    public Documento getDocumentoSelecionado() {
        return documentoSelecionado;
    }
    
    public void setDocumentoSelecionado(Documento documentoSelecionado) {
        this.documentoSelecionado = documentoSelecionado;
        firePropertyChangeAll();
    }

    public void firePropertyChangeAll() {
        firePropertyChange("documentoSelecionado", null, null);
        firePropertyChange("habilitarBotaoEditar", null, null);
        firePropertyChange("habilitarExcluir", null, null);
        firePropertyChange("habilitarInformarCertificado", null, null);
    }
    
    public TimeZone getTimeZone(){
        if (agenciamento != null){
            return agenciamento.getAgencia().obterTimezone();
        }
        return null;
    }
    //</editor-fold>
    
    public Documento obterNovoDocumento(){
        Documento documento = new Documento();
        documento.setAgenciamento(agenciamento);
        return documento;
    }
    
    public Documento obterDocumentoParaEdicaoVisualizacao(){
        return getService().buscarDocumentoPorId(getDocumentoSelecionado().getId());
    }
    
    public void adicionarDocumento(Documento documento){
        agenciamento.adicionarDocumento(documento);
        carregarListaDeDocumentos();
    }
    
    public void atualizarDocumento(Documento documento){
        agenciamento.removerDocumento(getDocumentoSelecionado());
        agenciamento.adicionarDocumento(documento);
        carregarListaDeDocumentos();
    }
    
    public void atualizarListaDeDocumento(){
        List<Documento> listaDocumento = getService().buscarDocumentosDoAgenciamento(agenciamento);
        agenciamento.setDocumentos(new HashSet<Documento>(listaDocumento));
        carregarListaDeDocumentos();
    }
    
    public void excluirDocumento(){
        //atualiza o documento com o agenciamento com as propriedades carregadas (evitar problemas de lazy).
        getDocumentoSelecionado().setAgenciamento(agenciamento);
        
        getService().excluirDocumento(getDocumentoSelecionado());
        agenciamento.removerDocumento(getDocumentoSelecionado());
        listaDeDocumentos.remove(getDocumentoSelecionado());
    }
    
    public void carregarListaDeDocumentos(){
        listaDeDocumentos.clear();
        
        if (agenciamento != null){
            listaDeDocumentos.addAll(agenciamento.getDocumentos());
        }
        
    }
    
    public boolean isHabilitarInformarCertificado(){
        return getDocumentoSelecionado() != null && TipoDocumento.SOLICITACAO_CERTIFICADO.equals(getDocumentoSelecionado().getTipoDocumento());
    }
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Habilitar componentes da tela">
    
    public boolean isHabilitarExcluir() {
        return getDocumentoSelecionado() != null;
    }
    
    
    //</editor-fold>
    
}
