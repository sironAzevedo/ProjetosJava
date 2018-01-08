package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaProdutoPorId extends BusinessQuery<Produto> {

    public ConsultaProdutoPorId(String id) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select p from {Produto} p ")                
                .append(" where p.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Produto}", Produto.class.getSimpleName())
                 .replace("{id}", Produto.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
