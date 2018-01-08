package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;
import java.util.TimeZone;

public class VisitasPainelModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<Visita> listaDeVisitas = new SerializableObservableList<Visita>();
    private Visita visitaSelecionada;
    private TimeZone timeZone;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        carregarListaDeVisitas();
        carregarTimeZone();
    }
    
    public List<Visita> getListaDeVisitas() {
        return listaDeVisitas;
    }

    public Visita getVisitaSelecionada() {
        return visitaSelecionada;
    }

    public void setVisitaSelecionada(Visita visitaSelecionada) {
        this.visitaSelecionada = visitaSelecionada;
        firePropertyChange("visitaSelecionada", null, null);
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        firePropertyChange("timeZone", null, null);
    }
    
    //</editor-fold>
    
    public Visita obterNovaVisita(){
        Visita nova = new Visita();
        nova.setAgenciamento(agenciamento);
        return nova; 
    }
    
    public Visita obterVisitaParaEdicaoVisualizacao(){
        Visita clone = (Visita) visitaSelecionada.clone();
        
        //atualiza com o mesmo agenciamento, mas com as propriedades carregadas (evitar problemas de lazy).
        clone.setAgenciamento(agenciamento);
        
        return clone;
    }
    
    public void adicionarVisita(Visita visita){
        agenciamento.adicionarVisita(visita);
        carregarListaDeVisitas();
    }
    
    public void atualizarVisita(Visita visita){
        agenciamento.removerVisita(visita);
        agenciamento.adicionarVisita(visita);
        carregarListaDeVisitas();
    }
    
    public void excluirVisita(){
        //atualiza a visita com o agenciamento com as propriedades carregadas (evitar problemas de lazy).
        visitaSelecionada.setAgenciamento(agenciamento);
        
        getService().excluirVisita(visitaSelecionada);
        agenciamento.removerVisita(visitaSelecionada);
        listaDeVisitas.remove(visitaSelecionada);
    }
    
    private void carregarListaDeVisitas(){
        listaDeVisitas.clear();
        
        if (agenciamento != null){
            listaDeVisitas.addAll(agenciamento.getVisitas());
        }
    }
    
    private void carregarTimeZone(){
        if (agenciamento == null){
            setTimeZone(null);
        }else{
            setTimeZone(TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
        }
        
    }
}
