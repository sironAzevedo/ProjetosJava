package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;

public class ResponsavelCustoBuilder {

    private ResponsavelCustoEntity responsavelCusto;
    

    public ResponsavelCustoBuilder(ResponsavelCustoEntity responsavelCusto) {
        this.responsavelCusto = responsavelCusto;
    } 
    
    public static ResponsavelCustoBuilder novoResponsavelCusto(){
        return new ResponsavelCustoBuilder(new ResponsavelCustoEntity());
    }
    
    public ResponsavelCustoEntity build() {
        return this.responsavelCusto;
    }
    
    public ResponsavelCustoBuilder comId(Long id){
        this.responsavelCusto.setId(id);
        return this;
    }
    
    public ResponsavelCustoBuilder comSigla(String sigla){
        this.responsavelCusto.setSigla(sigla);
        return this;
    }
    
    public ResponsavelCustoBuilder comDescricao(String descricao){
        this.responsavelCusto.setDescricao(descricao);
        return this;
    }
}
