package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;

public class PontoAtracacaoBuilder {

    private PontoAtracacao pontoAtracacao;
    
    private PontoAtracacaoBuilder(PontoAtracacao pontoAtracacao) {
        this.pontoAtracacao = pontoAtracacao;
    }
    
    public static PontoAtracacaoBuilder novoPontoAtracacao() {
        return new PontoAtracacaoBuilder(new PontoAtracacao());                
    }
    
    public PontoAtracacao build() {
        return this.pontoAtracacao;
    }
    
    public PontoAtracacaoBuilder comId(Long id){
        this.pontoAtracacao.setId(id);
        return this;
    }
    
    public PontoAtracacaoBuilder comNome(String nome){
        this.pontoAtracacao.setNome(nome);
        return this;
    }

    public PontoAtracacaoBuilder doPontoOperacional(PontoOperacional pontoOperacional){
        this.pontoAtracacao.setPontoOperacional(pontoOperacional);
        return this;
    }
    
}
