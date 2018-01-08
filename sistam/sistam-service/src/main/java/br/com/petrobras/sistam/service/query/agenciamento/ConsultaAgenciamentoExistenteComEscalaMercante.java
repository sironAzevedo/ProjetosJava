package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaAgenciamentoExistenteComEscalaMercante extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentoExistenteComEscalaMercante(Long escalaMercante, Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select agm from {Agenciamento} agm ")
                .append(" where agm <> :agenciamento ")
                .append(" and agm.{numeroEscalaMercante} = :escalaMercante ");


        String hql = texto.toString();
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                .replace("{numeroEscalaMercante}", Agenciamento.PROP_NUMERO_ESCALA_MERCANTE);

        this.setText(hql);
        addParameter("agenciamento", agenciamento);
        addParameter("escalaMercante", escalaMercante);

    }
}
