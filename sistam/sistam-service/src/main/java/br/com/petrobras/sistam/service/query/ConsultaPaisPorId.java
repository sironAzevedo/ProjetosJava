package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPaisPorId extends BusinessQuery<Pais> {

    public ConsultaPaisPorId(String id) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select p from {Pais} p ")                
                .append(" where p.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Pais}", Pais.class.getSimpleName())
                 .replace("{id}", Pais.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
