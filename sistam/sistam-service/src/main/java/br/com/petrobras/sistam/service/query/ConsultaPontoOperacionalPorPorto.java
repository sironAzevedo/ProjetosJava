package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPontoOperacionalPorPorto extends BusinessQuery<PontoOperacional> {

    public ConsultaPontoOperacionalPorPorto(Porto porto) {

        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();
                                
        texto                
                .append(" select po from {PontoOperacional} po ")                
                .append(" join fetch po.{porto} p ")                
                .append(" where po.{porto} = :porto ");
        
        map.put("porto", porto);
                
        
        String hql = texto.toString();     
                

        hql = hql.replace("{PontoOperacional}", PontoOperacional.class.getSimpleName())
                 .replace("{porto}", PontoOperacional.PROP_PORTO);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
