package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPortoPorId extends BusinessQuery<Porto> {

    public ConsultaPortoPorId(String id) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select p from {Porto} p ")     
                .append(" join fetch p.{pais} ")
                .append(" where p.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Porto}", Porto.class.getSimpleName())
                 .replace("{id}", Porto.PROP_ID)
                 .replace("{pais}", Porto.PROP_PAIS);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
