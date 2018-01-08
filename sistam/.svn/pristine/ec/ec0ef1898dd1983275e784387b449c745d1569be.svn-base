package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.Collections;
import java.util.List;

public class CadastroAgenciaModel extends BasePresentationModel<SistamService> {
    private List<Agencia> agencias = new SerializableObservableList<Agencia>();
    private Agencia agenciaSelecionada;

    public CadastroAgenciaModel() {
        consultar();
    }
    
    public void consultar() {
        setAgencias(Collections.EMPTY_LIST);
        setAgencias(getService().buscarAgencias());
    }
    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
        firePropertyChange("agencias", null, null);
    }

    public Agencia getAgenciaSelecionada() {
        return agenciaSelecionada;
    }

    public void setAgenciaSelecionada(Agencia agenciaSelecionada) {
        this.agenciaSelecionada = agenciaSelecionada;
        firePropertyChange("agenciaSelecionada", null, null);
    }
    
    
    
    
}