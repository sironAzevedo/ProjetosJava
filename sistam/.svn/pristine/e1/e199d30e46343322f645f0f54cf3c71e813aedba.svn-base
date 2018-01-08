package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;
/**
 * Consulta os agenciamentos por filtro para o relatório de produtividade
 * @author X4Z0
 */
public class ConsultaAgenciamentosProdutividade extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentosProdutividade(FiltroAgenciamento filtro) {
        
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        texto.append(" select distinct agm from {Agenciamento} agm ")
             .append(" join fetch agm.{embarcacao} e ")
             .append(" left join agm.{cancelamento} ca ")
             .append(" left join fetch agm.{acompanhamentos} agac ")
             .append(" left join agm.{porto} p ")
             .append(" where agm.{agencia} = :agencia")
             .append(" and agm.{dataChegada} >= :dataChegadaIni ")
             .append(" and agm.{dataChegada} <= :dataChegadaFim ")
             .append(" and (ca.motivo not in (:abertoDuplicidade, :abertoIndevidamente) or ca.motivo is null) ");
        
        if(null != filtro.getTipoContrato()){
            texto.append(" and agm.{tipoContrato} = :tipoContrato ");
            map.put("tipoContrato", filtro.getTipoContrato());
        }
        
        if (filtro.getPorto()!= null) {
            texto.append(" and agm.{porto} = :porto ");
            map.put("porto", filtro.getPorto());
        }
       
        map.put("agencia", filtro.getAgencia());
        map.put("dataChegadaIni", DateBuilder.on(filtro.getDataChegadaIni()).at(0, 0, 0).getTime(filtro.getAgencia().obterTimezone()));
        map.put("dataChegadaFim", DateBuilder.on(filtro.getDataChegadaFim()).at(23, 59, 59).getTime(filtro.getAgencia().obterTimezone()));
        map.put("abertoDuplicidade", MotivoCancelamento.ABERTO_DUPLICIDADE);
        map.put("abertoIndevidamente", MotivoCancelamento.ABERTO_INDEVIDAMENTE);
        
        String hql = texto.toString();
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                 .replace("{cancelamento}", Agenciamento.PROP_CANCELAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{porto}", Agenciamento.PROP_PORTO)
                 .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                 .replace("{dataChegada}", Agenciamento.PROP_DATA_OFICIAL_CHEGADA)
                 .replace("{tipoContrato}", Agenciamento.PROP_TIPO_CONTRATO)
                 .replace("{acompanhamentos}", Agenciamento.PROP_ACOMPANHAMENTO)
                ;
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
