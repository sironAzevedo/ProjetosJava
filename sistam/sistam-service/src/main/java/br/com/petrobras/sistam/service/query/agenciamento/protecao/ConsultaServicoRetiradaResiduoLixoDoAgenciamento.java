package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo; 
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoRetiradaResiduoLixoDoAgenciamento extends BusinessQuery<ServicoRetiradaResiduoLixo> {

    public ConsultaServicoRetiradaResiduoLixoDoAgenciamento(Agenciamento agenciamento) {

        appendText(" select sr from ", ServicoRetiradaResiduoLixo.class.getSimpleName(), " sr ");
        appendText(" join fetch sr.servicoProtecao sp  ");
        appendText(" join fetch sp.agenciamento a  ");
        appendText(" join fetch a.agencia  ");
        appendText(" where sp.agenciamento = :agenciamento ");

        addParameter("agenciamento", agenciamento);

    }
}
