
package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalheDocumentacaoLongoCursoModel extends BasePresentationModel<SistamService> {
    private DocumentacaoLongoCurso documentacaoLongoCurso;
    private List<TipoSimNao> listaSimNao;
    private TipoSimNao blRecebido;
    private String pais;
    private boolean salvo = false;
    private List<String> listaCE = new SerializableObservableList<String>();
    private String CESelecionado;
    private String CENovo;

    public DetalheDocumentacaoLongoCursoModel(DocumentacaoLongoCurso documentacaoLongoCurso) {
        this.documentacaoLongoCurso = documentacaoLongoCurso;
        documentacaoLongoCurso.addPropertyChangeListener(this);
        
        listaSimNao = new ArrayList<TipoSimNao> (Arrays.asList(TipoSimNao.values()));
        
        if (documentacaoLongoCurso.getId() != null){
            pais = documentacaoLongoCurso.getPorto().getPais().getNomeCompleto();
            blRecebido = TipoSimNao.from(documentacaoLongoCurso.isBlRecebido());
        }
        
        listaCE.addAll(documentacaoLongoCurso.getListaConhecimentoEletronicoFormatada());
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public DocumentacaoLongoCurso getDocumentacaoLongoCurso() {
        return documentacaoLongoCurso;
    }
    
    public List<TipoSimNao> getListaSimNao() {
        return listaSimNao;
    }
    
    public TipoSimNao getBlRecebido() {
        return blRecebido;
    }
    
    public void setBlRecebido(TipoSimNao blRecebido) {
        documentacaoLongoCurso.setBlRecebido(blRecebido.getId());
    }
    
    public String getPais() {
        return pais;
    }
    
    public void setPais(String pais) {
        Object old = this.pais;
        this.pais = pais;
        firePropertyChange("pais", old, this.pais);
    }
    
    public List<String> getListaCE() {
        return listaCE;
    }

    public String getCESelecionado() {
        return CESelecionado;
    }

    public void setCESelecionado(String CESelecionado) {
        Object old = this.CESelecionado;
        this.CESelecionado = CESelecionado;
        firePropertyChange("CESelecionado", old, this.CESelecionado);
    }

    public String getCENovo() {
        return CENovo;
    }

    public void setCENovo(String CENovo) {
        Object old = this.CENovo;
        this.CENovo = CENovo;
        firePropertyChange("CENovo", old, this.CENovo);
    }
    
    //</editor-fold>
    
    public void salvar(){
        documentacaoLongoCurso = getService().salvarDocumentacaoLongoCurso(documentacaoLongoCurso);
        salvo = true;
    }

    public void adicionarCE(){
        documentacaoLongoCurso.adicionarCE(CENovo);
        listaCE.add(CENovo);
        
        setCENovo(null);
    }
    
    public void removerCE(){
        documentacaoLongoCurso.removerCE(CESelecionado);
        listaCE.remove(CESelecionado);
    }
    
    public boolean salvou(){
        return salvo;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(DocumentacaoLongoCurso.PROP_PORTO)){
            if (documentacaoLongoCurso.getPorto() != null){
                setPais(documentacaoLongoCurso.getPorto().getPais().getNomeCompleto());
            }
            else{
                setPais(null);
            }
        }
    }
    
    
}
