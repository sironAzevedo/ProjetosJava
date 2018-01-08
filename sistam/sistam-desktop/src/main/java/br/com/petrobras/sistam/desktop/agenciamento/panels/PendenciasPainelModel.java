package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.HashSet;
import java.util.List;

public class PendenciasPainelModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<Pendencia> listaDePendencias = new SerializableObservableList<Pendencia>();
    private Pendencia pendenciaSelecionada;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        carregarListaDePendencias();
    }
    
    public List<Pendencia> getListaDePendencias() {
        return listaDePendencias;
    }
    
    public Pendencia getPendenciaSelecionada() {
        return pendenciaSelecionada;
    }
    
    public void setPendenciaSelecionada(Pendencia pendenciaSelecionada) {
        this.pendenciaSelecionada = pendenciaSelecionada;
        firePropertyChange("pendenciaSelecionada", null, null);
        firePropertyChange("habilitarBotaoResolver", null, null);
    }
    
    //</editor-fold>
    
  
    public void carregarListaDePendencias(){
        listaDePendencias.clear();
        
        if (agenciamento != null){
            listaDePendencias.addAll(agenciamento.getPendencias());
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Habilitar componentes da tela">
    public boolean isHabilitarBotaoResolver(){
        return (pendenciaSelecionada != null
                && !pendenciaSelecionada.isResolvida());
    }
    
    //</editor-fold>
    
    public void resolver(){
         Pendencia pendenciaResolvida = getService().resolverPendenciaManual(pendenciaSelecionada);
         agenciamento.removerPendencia(pendenciaSelecionada);
         agenciamento.adicionarPendencia(pendenciaResolvida);
         carregarListaDePendencias();
    }
    
    public void excluir(){
        getService().excluirPendencia(pendenciaSelecionada);
        agenciamento.removerPendencia(pendenciaSelecionada);
        carregarListaDePendencias();
    }
    
    public void atualizar() {
        
    }
    
    public void carregarPendencias(){
        List<Pendencia> pendencias = getService().buscarPendenciasDoAgenciamento(agenciamento, null);
        agenciamento.setPendencias(new HashSet<Pendencia>(pendencias));
        carregarListaDePendencias();
    }
    
    
}
