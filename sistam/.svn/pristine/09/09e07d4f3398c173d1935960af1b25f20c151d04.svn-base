package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 *
 */
public class ConsultaServicoTransportePorId extends BusinessQuery<ServicoTransporte> {

    public ConsultaServicoTransportePorId(Long id) {

        appendText(" select st from ", ServicoTransporte.class.getSimpleName(), " st ");
        appendText(" join fetch st.servicoProtecao sp ");
        appendText(" left join fetch st.passageiros ");
        appendText(" join fetch sp.agenciamento a");
        appendText(" join fetch a.embarcacao ");
        appendText(" join fetch a.agencia ");
        appendText(" left join fetch st.empresaServico es");
        appendText(" left join fetch es.empresa ");
        appendText(" where st.id = :id");

        addParameter("id", id);


    }
}
