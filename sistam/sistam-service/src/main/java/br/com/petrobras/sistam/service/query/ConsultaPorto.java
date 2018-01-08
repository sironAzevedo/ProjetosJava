package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPorto extends BusinessQuery<Porto> {

    public ConsultaPorto(Porto porto) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select p from {Porto} p ")     
                .append(" join fetch p.{pais}")
                .append(" left join fetch p.{complementos} c")
                .append(" where p = :porto ");
        
        map.put("porto", porto);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Porto}", Porto.class.getSimpleName())
                 .replace("{pais}", Porto.PROP_PAIS)
                 .replace("{complementos}", Porto.PROP_COMPLEMENTOS);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
