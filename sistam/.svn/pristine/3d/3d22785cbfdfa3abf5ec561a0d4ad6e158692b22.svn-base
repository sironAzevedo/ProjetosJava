package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoHospedagemDoAgenciamento extends BusinessQuery<ServicoHospedagem> {

    public ConsultaServicoHospedagemDoAgenciamento(Agenciamento agenciamento) {

        appendText(" select sh from ", ServicoHospedagem.class.getSimpleName(), " sh ");
        appendText(" join fetch sh.servicoProtecao sp  ");
        appendText(" join fetch sp.agenciamento a  ");
        appendText(" join fetch a.agencia  ");
        appendText(" where sp.agenciamento = :agenciamento ");

        addParameter("agenciamento", agenciamento);

    }
}
