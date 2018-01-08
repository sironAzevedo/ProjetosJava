package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.MotivoDesvio;
import java.util.Date;

public class DesvioBuilder {

    private Desvio desvio;
    
    private DesvioBuilder(Desvio desvio) {
        this.desvio = desvio;
    }
    
    public static DesvioBuilder novoDesvio() {
        return new DesvioBuilder(new Desvio());
    }
    
    public Desvio build() {
        return this.desvio;
    }
    
    public DesvioBuilder doAgenciamento(Agenciamento agenciamento) {
        this.desvio.setAgenciamento(agenciamento);
        return this;
    }
    
    public DesvioBuilder doPortoDestinoAlterado(Porto porto) {
        this.desvio.setPortoDestinoAlterado(porto);
        return this;
    }
    
    public DesvioBuilder comData(Date data) {
        this.desvio.setData(data);
        return this;
    }

    public DesvioBuilder comMotivo(MotivoDesvio motivo) {
        this.desvio.setMotivo(motivo);
        return this;
    }

    public DesvioBuilder comDestinoIntermediarioValido(String destinoIntermediarioValido) {
        this.desvio.setDestinoIntermediarioAlterado(destinoIntermediarioValido);
        return this;
    }
    
    
}
