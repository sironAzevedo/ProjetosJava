package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;

public class PassageiroBuilder {

    private Passageiro passageiro;
    
    private PassageiroBuilder(Passageiro passageiro) {
        this.passageiro = passageiro;
    }
    
    public static PassageiroBuilder novoPassageiro() {
        return new PassageiroBuilder(new Passageiro());
    }
    
    public Passageiro build() {
        return this.passageiro;
    }
    
    public PassageiroBuilder comId(Long id) {
        this.passageiro.setId(id);
        return this;
    }

    public PassageiroBuilder comNome(String nome){
        this.passageiro.setNome(nome);
        return this;
    }

    public PassageiroBuilder comCPF(String cpf){
        this.passageiro.setCpf(cpf);
        return this;
    }
    
    public PassageiroBuilder comDocumento(String documento) {
        this.passageiro.setDocumento(documento);
        return this;
    }
    
    public PassageiroBuilder comServicoProtecao(ServicoProtecao servicoProtecao) {
        this.passageiro.setServicoProtecao(servicoProtecao);
        return this;
    }
    
    

}
