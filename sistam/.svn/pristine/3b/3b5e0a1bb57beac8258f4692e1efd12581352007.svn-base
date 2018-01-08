package br.com.petrobras.sistam.service.query.agenciamento.taxa;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.TaxaMensalVO;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class ConsultaTaxaMensalPorFiltro extends BusinessQuery<TaxaMensalVO> {

    public ConsultaTaxaMensalPorFiltro(FiltroTaxa filtro) {
        List<Long> responsaveisId = new ArrayList<Long>();
        for (ResponsavelCustoEntity responsavelCustoEntity : filtro.getResponsaveis()) {
            if (responsavelCustoEntity.getId() != null) {
                responsaveisId.add(responsavelCustoEntity.getId());
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        TimeZone zone = null;

        texto
                .append(" select new br.com.petrobras.sistam.common.valueobjects.TaxaMensalVO(t.{responsavelCusto}, t.{tipoTaxa}, sum(t.{valor})) ")
                .append(" from {Taxa} t ")
                .append(" join t.{responsavelCusto} rc ")
                .append(" join t.{agenciamento} a ")
                .append(" left join a.{cancelamento} ca ")
                .append(" join a.{agencia} ag ")
                .append(" left join a.{porto} p ")
                .append(" where 1 = 1 ")
                .append(" and (ca.motivo not in (:abertoDuplicidade, :abertoIndevidamente) or ca.motivo is null) ");

        map.put("abertoDuplicidade", MotivoCancelamento.ABERTO_DUPLICIDADE);
        map.put("abertoIndevidamente", MotivoCancelamento.ABERTO_INDEVIDAMENTE);

        if (filtro.getAgencia() != null) {
            texto.append(" and a.{agencia} = :agencia ");
            map.put("agencia", filtro.getAgencia());
            zone = filtro.getAgencia().obterTimezone();
        }

        if (filtro.getPorto() != null) {
            texto.append(" and a.{porto} = :porto ");
            map.put("porto", filtro.getPorto());
        }

        if (filtro.getDataPagamentoInicial() != null) {
            texto.append(" and t.{dataPagamento} >= :dataInicial ");
            map.put("dataInicial", DateBuilder.on(filtro.getDataPagamentoInicial()).at(0, 0, 0).getTime(zone));
        }

        if (filtro.getDataPagamentoFinal() != null) {
            texto.append(" and t.{dataPagamento} <= :dataFinal ");
            map.put("dataFinal", DateBuilder.on(filtro.getDataPagamentoFinal()).at(23, 59, 59).getTime(zone));
        }

        if (!filtro.getResponsaveis().isEmpty()&& !responsaveisId.isEmpty()) {
            texto.append(" and t.{respCusto}.{idRespCusto} in (:responsaveis)");
            map.put("responsaveis", responsaveisId);
        } 

        texto.append(" group by t.{responsavelCusto}, t.{tipoTaxa} ")
                .append(" order by t.{responsavelCusto}");

        String hql = texto.toString();

        hql = hql.replace("{Taxa}", Taxa.class.getSimpleName())
                .replace("{agenciamento}", Taxa.PROP_AGENCIAMENTO)
                .replace("{cancelamento}", Agenciamento.PROP_CANCELAMENTO)
                .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                .replace("{porto}", Agenciamento.PROP_PORTO)
                .replace("{dataPagamento}", Taxa.PROP_DATA_PAGAMENTO)
                .replace("{responsavelCusto}", Taxa.PROP_RESPONSAVEL_CUSTO)
                .replace("{respCusto}", Taxa.PROP_RESPONSAVEL_CUSTO)
                .replace("{idRespCusto}", ResponsavelCustoEntity.PROP_ID)
                .replace("{tipoTaxa}", Taxa.PROP_TIPO_TAXA)
                .replace("{valor}", Taxa.PROP_VALOR);

        this.setText(hql);
        setParameters(map);

    }
}
