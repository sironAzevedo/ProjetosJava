package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoMedicoOdontologicoDoAgenciamento extends BusinessQuery<ServicoMedicoOdontologico> {

    public ConsultaServicoMedicoOdontologicoDoAgenciamento(Agenciamento agenciamento) {

        appendText(" select smo from ", ServicoMedicoOdontologico.class.getSimpleName(), " smo ");
        appendText(" join fetch smo.servicoProtecao sp  ");
        appendText(" join fetch sp.agenciamento a  ");
        appendText(" join fetch a.agencia  ");
        appendText(" where sp.agenciamento = :agenciamento ");

        addParameter("agenciamento", agenciamento);
    }
}
