package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.Collections;
import java.util.List;

public class CadastroAgenciaOrgaoDespachoModel extends BasePresentationModel<SistamService> {
    private List<AgenciaOrgaoDespacho> orgaos = new SerializableObservableList<AgenciaOrgaoDespacho>();
    private AgenciaOrgaoDespacho orgaoSelecionado;
    private Agencia agencia;
    
    public CadastroAgenciaOrgaoDespachoModel(Agencia agencia){
        this.agencia = agencia;
        consultar(agencia);
    }
    
    public void consultar(Agencia agencia) {
        setOrgaos(Collections.EMPTY_LIST);
        setOrgaos(getService().buscarOrgaosDespachoPorAgencia(agencia));
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public List<AgenciaOrgaoDespacho> getOrgaos() {
        return orgaos;
    }

    public void setOrgaos(List<AgenciaOrgaoDespacho> orgaos) {
        this.orgaos = orgaos;
        firePropertyChange("orgaos", null, null);
    }

    public AgenciaOrgaoDespacho getOrgaoSelecionado() {
        return orgaoSelecionado;
    }

    public void setOrgaoSelecionado(AgenciaOrgaoDespacho orgaoSelecionado) {
        this.orgaoSelecionado = orgaoSelecionado;
        firePropertyChange("orgaoSelecionado", null, null);
    }
    
    
    
    
    
    
}
