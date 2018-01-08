package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamentoAtendimento;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;
/**
 * Consulta os agenciamentos por filtro para o relatório de produtividade
 * @author X4Z0
 */
public class ConsultaAgenciamentosAtendimentos extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentosAtendimentos(FiltroAgenciamentoAtendimento filtro) {
        
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        Long anoMesInicial = Long.valueOf(""+filtro.getAnoInicial()+""+String.format("%02d", filtro.getMesInicial()));
        Long anoMesFinal = Long.valueOf(""+filtro.getAnoFinal()+""+String.format("%02d", filtro.getMesFinal()));
        
        
        texto.append(" select agm from {Agenciamento} agm ")
             .append(" join fetch agm.embarcacao e ")
             .append(" left join agm.{porto} p ")
             .append(" where agm.agencia = :agencia ")
             .append("    and ( ")
             .append("           (to_number(to_char(agm.dataChegada, 'yyyymm')) <= :anoMesFinal and agm.dataSaida is null ) ")
             .append("                   or ")
             .append("           (to_number(to_char(agm.dataChegada, 'yyyymm')) <= :anoMesInicial and :anoMesInicial <= to_number(to_char(agm.dataSaida, 'yyyymm')) ) ")
             .append("                   or ")
             .append("           (:anoMesInicial <= to_number(to_char(agm.dataChegada, 'yyyymm')) and to_number(to_char(agm.dataSaida, 'yyyymm')) <=  :anoMesFinal  ) ")
             .append("                   or ")
             .append("           (to_number(to_char(agm.dataChegada, 'yyyymm')) <= :anoMesFinal and :anoMesFinal <= to_number(to_char(agm.dataSaida, 'yyyymm')) ) ")
             .append("                   or ")
             .append("           (to_number(to_char(agm.dataChegada, 'yyyymm')) <= :anoMesInicial and :anoMesFinal <= to_number(to_char(agm.dataSaida, 'yyyymm'))  ) ")
             .append("        ) ");
        
        map.put("agencia", filtro.getAgencia());
        map.put("anoMesInicial", anoMesInicial);
        map.put("anoMesFinal", anoMesFinal);
        
        if(null != filtro.getTipoContrato()){
            texto.append(" and agm.tipoContrato = :tipoContrato ");
            map.put("tipoContrato", filtro.getTipoContrato());
        }
        
        if (filtro.getPorto()!= null) {
            texto.append(" and agm.{porto} = :porto ");
            map.put("porto", filtro.getPorto());
        }
       
        String hql = texto.toString();
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                 .replace("{porto}", Agenciamento.PROP_PORTO)
                ;
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
