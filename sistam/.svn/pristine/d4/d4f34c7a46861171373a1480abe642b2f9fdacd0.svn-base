package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;

public class AgenciaContatoBuilder {

    private RepresentanteLegal agenciaContato;
    
    private AgenciaContatoBuilder(RepresentanteLegal agenciaContato) {
        this.agenciaContato = agenciaContato;
    }
    
    public static AgenciaContatoBuilder novaAgenciaContato() {
        return new AgenciaContatoBuilder(new RepresentanteLegal());
    }
    
    public RepresentanteLegal build() {
        return this.agenciaContato;
    }
    
    public AgenciaContatoBuilder comId(Long id) {
        this.agenciaContato.setId(id);
        return this;
    }
    
    public AgenciaContatoBuilder daAgencia(Agencia agencia) {
        this.agenciaContato.setAgencia(agencia);
        return this;
    }

    public AgenciaContatoBuilder comNome(String nome){
        this.agenciaContato.setNome(nome);
        return this;
    }
    
    public AgenciaContatoBuilder comCpf(String cpf) {
        this.agenciaContato.setCpf(cpf);
        return this;
    }
}
