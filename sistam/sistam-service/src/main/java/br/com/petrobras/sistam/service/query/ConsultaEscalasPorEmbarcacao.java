package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.entity.EscalaPk;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaEscalasPorEmbarcacao extends BusinessQuery<Escala> {

    public ConsultaEscalasPorEmbarcacao(Embarcacao embarcacao) {

        Map<String, Object> map = new HashMap<String, Object>();
                         
        StringBuilder texto = new StringBuilder();        
                
        texto
                .append(" select es from {Escala} es ")
                .append(" join fetch es.{id}.{porto} p ") 
                .append(" join fetch p.{pais}") 
                .append(" where es.{id}.{embarcacao} = :embarcacao ")
                .append(" order by es.{id}.{data} desc ");
        
        map.put("embarcacao", embarcacao);
        
        String hql = texto.toString();

        hql = hql.replace("{Escala}", Escala.class.getSimpleName())                
                 .replace("{porto}", EscalaPk.PROP_PORTO)                                                                   
                 .replace("{embarcacao}", EscalaPk.PROP_EMBARCACAO)                                                                   
                 .replace("{data}", EscalaPk.PROP_DATA)                
                 .replace("{id}", Escala.PROP_ID)               
                 .replace("{pais}", Porto.PROP_PAIS);                
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
