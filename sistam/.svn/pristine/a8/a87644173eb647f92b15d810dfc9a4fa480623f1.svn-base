package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 *
 */
public class ConsultaServicoSuprimentoPorId extends BusinessQuery<ServicoSuprimento> {

    public ConsultaServicoSuprimentoPorId(Long id) {

        appendText(" select ss from ", ServicoSuprimento.class.getSimpleName(), " ss ");
        appendText(" join fetch ss.servicoProtecao sp ");
        appendText(" join fetch sp.agenciamento a");
        appendText(" join fetch a.embarcacao e");
        appendText(" join fetch a.porto ");
        appendText(" join fetch a.portoOrigem ");
        appendText(" join fetch e.bandeira ");
        appendText(" join fetch a.agencia g ");
        appendText(" left join fetch g.representantes ");
        appendText(" join fetch ss.empresaMaritima em "); 
        appendText(" join fetch ss.empresaServico es ");
        appendText(" left join fetch ss.transitos t");
        appendText(" left join fetch t.transitosEmpresas te");
        appendText(" left join fetch t.transitosVeiculos tv");
        appendText(" where ss.id = :id");

        addParameter("id", id);


    }
}
