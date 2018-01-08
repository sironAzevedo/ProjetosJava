package br.com.petrobras.sistam.service.query.agenciamento.taxa;

import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class ConsultaTaxasOrdenadasPorTipo extends BusinessQuery<Taxa> {

    public ConsultaTaxasOrdenadasPorTipo(FiltroTaxa filtro) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        TimeZone zone = null;

        texto
                .append(" select t from br.com.petrobras.sistam.common.entity.Taxa t ")
                .append(" join fetch t.agenciamento a ")
                .append(" join fetch a.embarcacao e ")
                .append(" join fetch a.agencia ag ")
                .append(" where 1 = 1 ");

        if (filtro.getAgencia() != null) {
            texto.append(" and a.agencia = :agencia ");
            map.put("agencia", filtro.getAgencia());
            zone = filtro.getAgencia().obterTimezone();
        }

        Date periodoInicio = filtro.getDataPagamentoInicial();
        if (periodoInicio != null) {
            periodoInicio = DateBuilder.on(filtro.getDataPagamentoInicial()).at(0, 0, 0).getTime(zone);
        }

        Date periodoTermino = filtro.getDataPagamentoFinal();
        if (periodoTermino != null) {
            periodoTermino = DateBuilder.on(filtro.getDataPagamentoFinal()).at(23, 59, 59).getTime(zone);
        }

        if (periodoInicio != null && periodoTermino != null) {
            texto.append(" and (")
                    .append(" (t.dataPagamento >= :periodoInicio and t.dataPagamento < :periodoTermino) ")
                    .append(" or (t.dataPagamento > :periodoInicio and t.dataPagamento <= :periodoTermino) ")
                    .append(" or (t.dataPagamento = :periodoInicio and t.dataPagamento = :periodoTermino) ")
                    .append(" ) ");
            map.put("periodoInicio", periodoInicio);
            map.put("periodoTermino", periodoTermino);
        } else if (periodoInicio != null) {
            texto.append(" and t.dataPagamento >= :periodoInicio ");
            map.put("periodoInicio", periodoInicio);
        } else if (periodoTermino != null) {
            texto.append(" and t.dataPagamento <= :periodoTermino ");
            map.put("periodoTermino", periodoTermino);
        }

        if (filtro.getListaTipoTaxa() != null && filtro.getListaTipoTaxa().size() > 0) {
            texto.append(" and t.tipoTaxa in (:tiposTaxa) ");
            map.put("tiposTaxa", filtro.getListaTipoTaxa());
        }

        texto.append(" order by t.tipoTaxa ");

        String hql = texto.toString();

        this.setText(hql);
        setParameters(map);

    }
}
