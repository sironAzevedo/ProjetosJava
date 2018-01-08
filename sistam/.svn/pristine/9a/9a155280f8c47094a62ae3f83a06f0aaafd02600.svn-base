package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;

public class HospedeBuilder {

    private Hospede hospede;
    
    private HospedeBuilder(Hospede hospede) {
        this.hospede = hospede;
    }
    
    public static HospedeBuilder novoHospede() {
        return new HospedeBuilder(new Hospede());
    }
    
    public Hospede build() {
        return this.hospede;
    }
    
    public HospedeBuilder comId(Long id) {
        this.hospede.setId(id);
        return this;
    }

    public HospedeBuilder comNome(String nome){
        this.hospede.setNome(nome);
        return this;
    }

    public HospedeBuilder comCPF(String cpf){
        this.hospede.setCpf(cpf);
        return this;
    }
    
    public HospedeBuilder comDocumento(String documento) {
        this.hospede.setDocumento(documento);
        return this;
    }
    
    public HospedeBuilder comServicoProtecao(ServicoProtecao servicoProtecao) {
        this.hospede.setServicoProtecao(servicoProtecao);
        return this;
    }
    
    

}
