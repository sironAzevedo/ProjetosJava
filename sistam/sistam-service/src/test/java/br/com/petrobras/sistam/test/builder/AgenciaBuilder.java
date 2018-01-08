package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agencia;
import java.util.TimeZone;

public class AgenciaBuilder {

    private Agencia agencia;
    
    private AgenciaBuilder(Agencia agencia) {
        this.agencia = agencia;
    }
    
    public static AgenciaBuilder novaAgencia() {
        return new AgenciaBuilder(new Agencia());
    }
    
    public Agencia build() {
        return this.agencia;
    }
    
    public AgenciaBuilder comId(Long id) {
        this.agencia.setId(id);
        return this;
    }

    public AgenciaBuilder comSigla(String sigla){
        this.agencia.setSigla(sigla);
        return this;
    }

    public AgenciaBuilder comNome(String nome){
        this.agencia.setNome(nome);
        return this;
    }
    
    public AgenciaBuilder comEmail(String email) {
        this.agencia.setEmail(email);
        return this;
    }
    
    public AgenciaBuilder comEndereco(String endereco){
        this.agencia.setEndereco(endereco);
        return this;
    }
    
    public AgenciaBuilder comTelefone(String telefone){
        this.agencia.setTelefone(telefone);
        return this;
    }

    public AgenciaBuilder comCidade(String cidade){
        this.agencia.setCidade(cidade);
        return this;
    }

    public AgenciaBuilder comEstado(String estado){
        this.agencia.setEstado(estado);
        return this;
    }
    
    
    public AgenciaBuilder comTimeZone(String timeZoString){
        this.agencia.setTimezone(timeZoString);
        return this;
    }
    
    

}
