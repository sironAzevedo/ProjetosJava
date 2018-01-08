package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;

public class DetalheAgenciaOrgaoDespachoModel extends BasePresentationModel<SistamService> {
    private AgenciaOrgaoDespacho agenciaOrgaoDespacho;
    
    public DetalheAgenciaOrgaoDespachoModel(AgenciaOrgaoDespacho agenciaOrgaoDespacho){
        this.agenciaOrgaoDespacho = agenciaOrgaoDespacho;
    }
    
    public void salvar() {
        agenciaOrgaoDespacho = getService().salvarAgenciaOrgaoDespacho(agenciaOrgaoDespacho);
    }

    public AgenciaOrgaoDespacho getAgenciaOrgaoDespacho() {
        return agenciaOrgaoDespacho;
    }

    public void setAgenciaOrgaoDespacho(AgenciaOrgaoDespacho agenciaOrgaoDespacho) {
        this.agenciaOrgaoDespacho = agenciaOrgaoDespacho;
        firePropertyChange("agenciaOrgaoDespacho", null, this.agenciaOrgaoDespacho);
    }
    
}
