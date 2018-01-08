package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;

public class AgenciaOrgaoDespachoBuilder {

    private AgenciaOrgaoDespacho agenciaOrgaoDespacho;
    
    private AgenciaOrgaoDespachoBuilder(AgenciaOrgaoDespacho agenciaOrgaoDespacho) {
        this.agenciaOrgaoDespacho = agenciaOrgaoDespacho;
    }
    
    public static AgenciaOrgaoDespachoBuilder novaAgenciaOrgaoDespacho() {
        return new AgenciaOrgaoDespachoBuilder(new AgenciaOrgaoDespacho());
    }
    
    public AgenciaOrgaoDespacho build() {
        return this.agenciaOrgaoDespacho;
    }
    
    public AgenciaOrgaoDespachoBuilder comId(Long id) {
        this.agenciaOrgaoDespacho.setId(id);
        return this;
    }
    
    public AgenciaOrgaoDespachoBuilder daAgencia(Agencia agencia) {
        this.agenciaOrgaoDespacho.setAgencia(agencia);
        return this;
    }

    public AgenciaOrgaoDespachoBuilder comNome(String nome){
        this.agenciaOrgaoDespacho.setNome(nome);
        return this;
    }
    
}
