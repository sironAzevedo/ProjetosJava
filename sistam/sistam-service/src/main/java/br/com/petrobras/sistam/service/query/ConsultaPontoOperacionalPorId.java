package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPontoOperacionalPorId extends BusinessQuery<PontoOperacional> {

    public ConsultaPontoOperacionalPorId(String id) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select p from {PontoOperacional} p ")                
                .append(" where p.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{PontoOperacional}", PontoOperacional.class.getSimpleName())
                 .replace("{id}", PontoOperacional.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
