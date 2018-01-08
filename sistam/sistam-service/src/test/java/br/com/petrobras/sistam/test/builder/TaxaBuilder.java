package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import java.util.Date;

public class TaxaBuilder {

    private Taxa taxa;
    
    private TaxaBuilder(Taxa taxa) {
        this.taxa = taxa;
        this.taxa.setChaveUsuarioAlteracao("STAM");
        this.taxa.setNomeUsuarioAlteracao("STAM");
        this.taxa.setDataAlteracao(new Date());
    }
    
    public static TaxaBuilder novaTaxa() {
        return new TaxaBuilder(new Taxa());
    }
    
    public Taxa build() {
        return this.taxa;
    }

    public TaxaBuilder comId(Long id) {
        this.taxa.setId(id);
        return this;
    }

    public TaxaBuilder doAgenciamento(Agenciamento agenciamento) {
        this.taxa.setAgenciamento(agenciamento);
        return this;
    }
    
    public TaxaBuilder comValor(Double valor) {
        this.taxa.setValor(valor);
        return this;
    }

    public TaxaBuilder comDataPagamento(Date data) {
        this.taxa.setDataPagamento(data);
        return this;
    }
    
    public TaxaBuilder comResponsavelCusto(ResponsavelCustoEntity responsavelCusto) {
        this.taxa.setResponsavelCusto(responsavelCusto);
        return this;
    }

    public TaxaBuilder comNumeroNecessidadeLiberacaoSap(Long numero) {
        this.taxa.setNumeroNecessidadeLiberacaoSap(numero);
        return this;
    }
    
    public TaxaBuilder doTipo(TipoTaxa tipo) {
        this.taxa.setTipoTaxa(tipo);
        return this;
    }
    
}
