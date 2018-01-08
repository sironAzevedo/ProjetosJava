package br.com.petrobras.sistam.desktop.agenciamento.documento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class SelecionarManobraEncerradaModel extends BasePresentationModel<SistamService> {
    
    private List<Manobra> listaManobrasRegistradas = new SerializableObservableList<Manobra>();
    private Manobra manobraSelecionada;
    
    public SelecionarManobraEncerradaModel(List<Manobra> manobras) {
        carregarManobrasRegistradasEncerradas(manobras);
    }
            
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

    public Manobra getManobraSelecionada() {
        return manobraSelecionada;
    }
    
    public void setManobraSelecionada(Manobra manobraSelecionada) {
        this.manobraSelecionada = manobraSelecionada;
        firePropertyChange("manobraSelecionada", null, this.manobraSelecionada);
    }
    
    public List<Manobra> getListaManobrasRegistradas() {
        return listaManobrasRegistradas;
    }
    
    //</editor-fold>
    
    /**
     * Carrega apenas as manobras registradas, desconsiderando as abortadas.
     */
    private void carregarManobrasRegistradasEncerradas(List<Manobra> manobras){
        listaManobrasRegistradas.clear();
        
        for (Manobra manobra : manobras){
            if (StatusManobra.ENCERRADA.equals(manobra.getStatus())){
                listaManobrasRegistradas.add(manobra);
            }
        }
    }
    
}
