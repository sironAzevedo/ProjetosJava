package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 *
 */
public class ConsultaServicoAcessoPessoaPorId extends BusinessQuery<ServicoAcessoPessoa> {

    public ConsultaServicoAcessoPessoaPorId(Long id) {

        appendText(" select sa from ", ServicoAcessoPessoa.class.getSimpleName(), " sa ");
        appendText(" join fetch sa.servicoProtecao sp ");
        appendText(" join fetch sp.agenciamento a");
        appendText(" join fetch a.embarcacao e");
        appendText(" join fetch a.porto ");
        appendText(" join fetch a.portoOrigem ");
        appendText(" join fetch e.bandeira ");
        appendText(" join fetch a.agencia g ");
        appendText(" left join fetch g.representantes ");
        appendText(" join fetch sa.pessoas pessoas");
        appendText(" where sa.id = :id");

        addParameter("id", id);


    }
}
