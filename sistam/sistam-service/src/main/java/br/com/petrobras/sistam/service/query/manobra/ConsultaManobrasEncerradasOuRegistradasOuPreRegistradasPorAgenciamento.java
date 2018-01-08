package br.com.petrobras.sistam.service.query.manobra;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Arrays;

public class ConsultaManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento extends BusinessQuery<Manobra> {

    public ConsultaManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento(Agenciamento agenciamento) {
        StringBuilder hql = new StringBuilder();

        hql.append(" select distinct m from {Manobra} m")
                .append(" join fetch m.{agenciamento} agm ")
                .append("left join fetch m.{pontoAtracacaoOrigem} ori")
                .append("left join fetch m.{pontoAtracacaoDestino} des")
                .append(" where m.{status} in (:status)");

        addParameter("status", Arrays.asList(new StatusManobra[]{StatusManobra.ENCERRADA, StatusManobra.REGISTRADA, StatusManobra.PRE_REGISTRADA}));

        hql.append(" and agm = :agenciamento");
        addParameter("agenciamento", agenciamento);

        hql.append(" order by m.{dataInicio}");

        String text = hql.toString().replace("{Manobra}", Manobra.class.getSimpleName())
                .replace("{status}", Manobra.PROP_STATUS)
                .replace("{agenciamento}", Manobra.PROP_AGENCIAMENTO)
                .replace("{pontoAtracacaoOrigem}", Manobra.PROP_PONTO_ATRACACAO_ORIGEM)
                .replace("{pontoAtracacaoDestino}", Manobra.PROP_PONTO_ATRACACAO_DESTINO)
                .replace("{dataInicio}", Manobra.PROP_DATA_INICIO);

        this.setText(text);
    }
}
