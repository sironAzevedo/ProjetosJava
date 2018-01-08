package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoAcessoPessoaDoAgenciamento extends BusinessQuery<ServicoAcessoPessoa> {

    public ConsultaServicoAcessoPessoaDoAgenciamento(Agenciamento agenciamento) {

        appendText(" select sa from ", ServicoAcessoPessoa.class.getSimpleName(), " sa ");
        appendText(" join fetch sa.servicoProtecao sp  ");
        appendText(" join fetch sp.agenciamento a  ");
        appendText(" join fetch a.agencia  ");
        appendText(" where sp.agenciamento = :agenciamento ");

        addParameter("agenciamento", agenciamento);

    }
}
