package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Transporte;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.enums.TipoTransporte;

public class TransporteBuilder {

    private Transporte transporte;
    
    private TransporteBuilder(Transporte transporte) {
        this.transporte = transporte;
    }
    
    public static TransporteBuilder novoTransporte() {
        return new TransporteBuilder(new Transporte());
    }
    
    public Transporte build() {
        return this.transporte;
    }

    public TransporteBuilder comId(Long id) {
        this.transporte.setId(id);
        return this;
    }

    public TransporteBuilder daVisita(Visita visita) {
        this.transporte.setVisita(visita);
        return this;
    }

    public TransporteBuilder comTipoTransporte(TipoTransporte tipo) {
        this.transporte.setTipoTransporte(tipo);
        return this;
    }

    public TransporteBuilder comCusto(Double custo) {
        this.transporte.setCusto(custo);
        return this;
    }

    public TransporteBuilder comResponsavelCusto(ResponsavelCustoEntity responsavelCusto) {
        this.transporte.setResponsavelCusto(responsavelCusto);
        return this;
    }
    
}
