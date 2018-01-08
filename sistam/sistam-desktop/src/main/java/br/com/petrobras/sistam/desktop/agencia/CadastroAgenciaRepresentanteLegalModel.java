package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.Collections;
import java.util.List;

public class CadastroAgenciaRepresentanteLegalModel extends BasePresentationModel<SistamService> {
    private List<RepresentanteLegal> representantes = new SerializableObservableList<RepresentanteLegal>();
    private RepresentanteLegal representanteSelecionado;
    private Agencia agencia;
    
    public CadastroAgenciaRepresentanteLegalModel(Agencia agencia){
        this.agencia = agencia;
        consultar(agencia);
    }
    
    public void consultar(Agencia agencia) {
        setRepresentantes(Collections.EMPTY_LIST);
        setRepresentantes(getService().buscarContatosPorAgencia(agencia, null));
    }

    public List<RepresentanteLegal> getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(List<RepresentanteLegal> representantes) {
        this.representantes = representantes;
        firePropertyChange("representantes", null, null);
    }

    public RepresentanteLegal getRepresentanteSelecionado() {
        return representanteSelecionado;
    }

    public void setRepresentanteSelecionado(RepresentanteLegal representanteSelecionado) {
        this.representanteSelecionado = representanteSelecionado;
        firePropertyChange("representanteSelecionado", null, null);
    }

    public Agencia getAgencia() {
        return agencia;
    }
    
    public void excluir() {
        getService().excluirAgenciaContato(representanteSelecionado);
    }
    
}
