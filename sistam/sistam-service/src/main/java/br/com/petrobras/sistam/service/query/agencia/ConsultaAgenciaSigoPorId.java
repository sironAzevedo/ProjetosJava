package br.com.petrobras.sistam.service.query.agencia;

import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaAgenciaSigoPorId extends BusinessQuery<AgenciaSigo> {

    public ConsultaAgenciaSigoPorId(Long id) {
        StringBuilder texto = new StringBuilder();

        Map<String, Object> map = new HashMap<String, Object>();
        
        texto.append(" select a from {AgenciaSigo} a ")                
             .append(" where a.{id} = :id " );
        
         map.put("id", id);
                
        String hql = texto.toString();     
                

        hql = hql.replace("{AgenciaSigo}", AgenciaSigo.class.getSimpleName())
                 .replace("{id}", AgenciaSigo.PROP_ID);
                         
        
        this.setText(hql);
       setParameters(map);
    }
    
}
