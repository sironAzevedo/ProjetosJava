package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import java.util.Date;

public class AcompanhamentoBuilder {

    private Acompanhamento acompanhamento;
    
    private AcompanhamentoBuilder(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
    
    public static AcompanhamentoBuilder novoAcompanhamento() {
        return new AcompanhamentoBuilder(new Acompanhamento());
    }
    
    public Acompanhamento build() {
        return this.acompanhamento;
    }
    
    public AcompanhamentoBuilder doAgenciamento(Agenciamento agenciamento) {
        this.acompanhamento.setAgenciamento(agenciamento);
        return this;
    }
    
    public AcompanhamentoBuilder comData(Date data) {
        this.acompanhamento.setData(data);
        return this;
    }

    public AcompanhamentoBuilder comChaveCadastrador(String chaveCadastrador) {
        this.acompanhamento.setChaveCadastrador(chaveCadastrador);
        return this;
    }

    public AcompanhamentoBuilder comNomeCadastrador(String nomeCadastrador) {
        this.acompanhamento.setNomeCadastrador(nomeCadastrador);
        return this;
    }
    
    public AcompanhamentoBuilder comTexto(String textoAcompanhamento) {
        this.acompanhamento.setTexto(textoAcompanhamento);
        return this;
    }
    
    public AcompanhamentoBuilder comId(Long id) {
        this.acompanhamento.setId(id);
        return this;
    }
    
}
