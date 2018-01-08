package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 *
 */
public class ConsultaServicoHospedagemPorId extends BusinessQuery<ServicoHospedagem> {

    public ConsultaServicoHospedagemPorId(Long id) {

        appendText(" select sh from ", ServicoHospedagem.class.getSimpleName(), " sh ");
        appendText(" join fetch sh.servicoProtecao sp ");
        appendText(" join fetch sp.agenciamento a");
        appendText(" join fetch a.embarcacao ");
        appendText(" join fetch a.agencia ");
        appendText(" join fetch sh.hospedes ");
        appendText(" join fetch sh.empresaServico es");
        appendText(" join fetch es.empresa ");
        appendText(" where sh.id = :id");

        addParameter("id", id);


    }
}
