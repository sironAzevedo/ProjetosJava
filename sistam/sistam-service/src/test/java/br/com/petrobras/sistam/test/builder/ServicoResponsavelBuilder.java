package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import java.util.Date;

public class ServicoResponsavelBuilder {

    private ServicoResponsavel servicoResponsavel;
    
    private ServicoResponsavelBuilder(ServicoResponsavel servicoResponsavel) {
        this.servicoResponsavel = servicoResponsavel;
    }
    
    public static ServicoResponsavelBuilder novoServicoResponsavel() {
        return new ServicoResponsavelBuilder(new ServicoResponsavel());
    }
    
    public static ServicoResponsavelBuilder novoServicoResponsavel(ServicoResponsavel responsavel) {
        return new ServicoResponsavelBuilder(responsavel);
    }
    
    public ServicoResponsavel build() {
        return this.servicoResponsavel;
    }
    
    public ServicoResponsavelBuilder comId(Long id){
        this.servicoResponsavel.setId(id);
        return this;
    }
    
    public ServicoResponsavelBuilder doServicoManobra(ServicoManobra servicoManobra) {
        this.servicoResponsavel.setServicoManobra(servicoManobra);
        return this;
    }
    
    public ServicoResponsavelBuilder comServico(Servico servico) {
        this.servicoResponsavel.setServico(servico);
        return this;
    }
    
    public ServicoResponsavelBuilder comDataInicio(Date dataInicio){
        this.servicoResponsavel.setDataInicio(dataInicio);
        return this;
    }

    public ServicoResponsavelBuilder comDataTermino(Date dataTermino){
        this.servicoResponsavel.setDataTermino(dataTermino);
        return this;
    }
    
}
