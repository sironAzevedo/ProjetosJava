package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import java.util.List;

public class AgenciamentoPendenciaModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<Pendencia> listaDePendencias;
    private List<Pendencia> listaDePendenciasSelecionadas;

    public AgenciamentoPendenciaModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        carregarListaDePendencias();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public List<Pendencia> getListaDePendenciasSelecionadas() {
        return listaDePendenciasSelecionadas;
    }

    public void setListaDePendenciasSelecionadas(List<Pendencia> listaDePendenciasSelecionadas) {
        this.listaDePendenciasSelecionadas = listaDePendenciasSelecionadas;
        firePropertyChange("listaDePendenciasSelecionadas", null, null);
    }

    public List<Pendencia> getListaDePendencias() {
        return listaDePendencias;
    }
    
    //</editor-fold>
    
    public void salvar(){
        //getService().resolverPendenciaManual(listaDePendenciasSelecionadas);
    }
    
    private void carregarListaDePendencias(){
        this.listaDePendencias = getService().buscarPendenciasDoAgenciamento(agenciamento, Boolean.FALSE);
    }

}
