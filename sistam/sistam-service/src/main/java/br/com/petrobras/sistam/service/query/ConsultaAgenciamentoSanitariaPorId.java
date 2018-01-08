package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.AgenciamentoSanitaria;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaAgenciamentoSanitariaPorId extends BusinessQuery<AgenciamentoSanitaria> {

    public ConsultaAgenciamentoSanitariaPorId(Long id) {

        Map<String, Object> map = new HashMap<String, Object>();
                
        StringBuilder texto = new StringBuilder();    
        
        texto
                .append(" select ags from {AgenciamentoSanitaria} ags ")        
                .append(" where ags.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();

        hql = hql.replace("{AgenciamentoSanitaria}", AgenciamentoSanitaria.class.getSimpleName())
                 .replace("{id}", AgenciamentoSanitaria.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
