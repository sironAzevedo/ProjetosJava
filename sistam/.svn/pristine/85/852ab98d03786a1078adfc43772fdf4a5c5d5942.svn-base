package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoProtecaoDoAgenciamento extends BusinessQuery<ServicoProtecao> {

    public ConsultaServicoProtecaoDoAgenciamento(Agenciamento agenciamento) {

        appendText(" select sp from ", ServicoProtecao.class.getSimpleName(), " sp ");
        appendText(" join fetch sp.agenciamento a  ");
        appendText(" where sp.agenciamento = :agenciamento ");
        
        addParameter("agenciamento", agenciamento);

    }
}
