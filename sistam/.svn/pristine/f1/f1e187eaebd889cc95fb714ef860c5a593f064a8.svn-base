package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoTransporteDoAgenciamento extends BusinessQuery<ServicoTransporte> {

    public ConsultaServicoTransporteDoAgenciamento(Agenciamento agenciamento) {

        appendText(" select st from ", ServicoTransporte.class.getSimpleName(), " st ");
        appendText(" join fetch st.servicoProtecao sp  ");
        appendText(" join fetch sp.agenciamento a  ");
        appendText(" join fetch a.agencia  ");
        appendText(" where sp.agenciamento = :agenciamento ");

        addParameter("agenciamento", agenciamento);

    }
}
