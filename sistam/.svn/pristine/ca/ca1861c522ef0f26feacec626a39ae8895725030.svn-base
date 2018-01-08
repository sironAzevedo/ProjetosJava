package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.entity.EscalaPk;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConsultaEscalasDosUltimos30DiasPorEmbarcacao extends BusinessQuery<Escala> {

    public ConsultaEscalasDosUltimos30DiasPorEmbarcacao(Embarcacao embarcacao, Date eta) {

        Map<String, Object> map = new HashMap<String, Object>();
                         
        StringBuilder texto = new StringBuilder();        
                
        texto
                .append(" select es from {Escala} es ")
                .append(" join fetch es.{id}.{porto} p ")                
                .append(" where es.{id}.{embarcacao} = :embarcacao ")
                .append(" and es.{id}.{data} > trunc(:eta) - 30 ")
                .append(" order by es.{id}.{data} desc ");
        
        map.put("embarcacao", embarcacao);
        map.put("eta", eta);
        
        String hql = texto.toString();

        hql = hql.replace("{Escala}", Escala.class.getSimpleName())                
                 .replace("{porto}", EscalaPk.PROP_PORTO)                                                                   
                 .replace("{embarcacao}", EscalaPk.PROP_EMBARCACAO)                                                                   
                 .replace("{data}", EscalaPk.PROP_DATA)                
                 .replace("{id}", Escala.PROP_ID);                
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
