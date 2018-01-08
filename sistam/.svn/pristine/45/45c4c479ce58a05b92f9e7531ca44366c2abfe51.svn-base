package br.com.petrobras.sistam.service.query.agenciamento.taxa;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultaTaxasDaAgenciaEMesAnoOrdenadasPorTipo extends BusinessQuery<Taxa> {

    public ConsultaTaxasDaAgenciaEMesAnoOrdenadasPorTipo(Agencia agencia, Porto porto, List<TipoTaxa> tiposTaxa, Integer mes, Integer ano) {
        Map<String, Object> map = new HashMap<String, Object>();  
        StringBuilder texto = new StringBuilder(); 
        texto
                .append(" select t from br.com.petrobras.sistam.common.entity.Taxa t ")
                .append(" join fetch t.agenciamento a ")
                .append(" left join a.cancelamento ca ")
                .append(" join fetch a.embarcacao e ")
                .append(" join fetch a.agencia ag ")
                .append(" join fetch a.porto p")
                .append(" where a.agencia = :agencia and month(t.dataPagamento)= :mes and  year(t.dataPagamento)= :ano and t.tipoTaxa in (:tiposTaxa)")
                .append(" and (ca.motivo not in (:abertoDuplicidade, :abertoIndevidamente) or ca.motivo is null) ");
        
            map.put("agencia", agencia); 
            map.put("tiposTaxa", tiposTaxa);
            map.put("mes", mes);
            map.put("ano", ano);
            map.put("abertoDuplicidade", MotivoCancelamento.ABERTO_DUPLICIDADE);
            map.put("abertoIndevidamente", MotivoCancelamento.ABERTO_INDEVIDAMENTE);
            
        if (porto != null) {
            texto.append(" and a.porto = :porto ");
            map.put("porto", porto);
        }
        
        texto.append(" order by t.tipoTaxa, t.dataPagamento ");
        
        String hql = texto.toString();     
                
        this.setText(hql);
        setParameters(map);
        
    }
    
}
