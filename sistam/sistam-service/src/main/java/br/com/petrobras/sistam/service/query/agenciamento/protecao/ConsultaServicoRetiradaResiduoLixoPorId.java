package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo; 
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 *
 */
public class ConsultaServicoRetiradaResiduoLixoPorId extends BusinessQuery<ServicoRetiradaResiduoLixo> {

    public ConsultaServicoRetiradaResiduoLixoPorId(Long id) {

        appendText(" select sr from ", ServicoRetiradaResiduoLixo.class.getSimpleName(), " sr ");
        appendText(" join fetch sr.servicoProtecao sp ");
        appendText(" join fetch sp.agenciamento a");
        appendText(" join fetch a.embarcacao e");
        appendText(" join fetch e.bandeira ");
        appendText(" join fetch a.porto ");
        appendText(" join fetch a.portoOrigem ");
        appendText(" join fetch a.agencia g");
        appendText(" left join fetch g.representantes ");
        appendText(" join fetch sr.empresaMaritima em "); 
        appendText(" join fetch sr.empresaMaritimaRodoviaria er "); 
        appendText(" join fetch sr.empresaServico es ");
        appendText(" where sr.id = :id");

        addParameter("id", id);


    }
} 