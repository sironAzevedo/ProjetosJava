package br.com.petrobras.sistam.service.query.common;

import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicosPorTipo extends BusinessQuery<Servico> {

    public ConsultaServicosPorTipo(TipoServico tipo) {

        appendText(" select s from ",Servico.class.getSimpleName()," s ");
        appendText(" join fetch s.",Servico.PROP_EMPRESA," e ");
        appendText(" where s.",Servico.PROP_TIPO_SERVICO," = :tipo ");
        appendText(" order by e.", EmpresaMaritima.PROP_RAZAO_SOCIAL);

        addParameter("tipo", tipo);

    }
}
