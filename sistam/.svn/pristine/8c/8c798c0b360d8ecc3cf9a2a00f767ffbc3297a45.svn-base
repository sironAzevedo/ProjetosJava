package br.com.petrobras.sistam.service.query.agenciamento.visita;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaVisitasPorAgenciamento extends BusinessQuery<Visita> {

    public ConsultaVisitasPorAgenciamento(Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select distinct(v) from {Visita} v ")
                .append(" left join fetch v.transportes t ")
                .append(" left join fetch t.responsavelCusto r ")
                .append(" where v.agenciamento = :agenciamento ");

        addParameter("agenciamento", agenciamento);

        String hql = texto.toString();

        hql = hql.replace("{Visita}", Visita.class.getSimpleName());

        this.setText(hql);
    }
}
