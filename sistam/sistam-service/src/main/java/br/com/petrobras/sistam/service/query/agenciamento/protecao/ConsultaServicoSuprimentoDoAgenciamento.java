package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoSuprimentoDoAgenciamento extends BusinessQuery<ServicoSuprimento> {

    public ConsultaServicoSuprimentoDoAgenciamento(Agenciamento agenciamento) {

        appendText(" select ss from ", ServicoSuprimento.class.getSimpleName(), " ss ");
        appendText(" join fetch ss.servicoProtecao sp  ");
        appendText(" join fetch sp.agenciamento a  ");
        appendText(" join fetch a.agencia  ");
        appendText(" where sp.agenciamento = :agenciamento ");

        addParameter("agenciamento", agenciamento);

    }
}
