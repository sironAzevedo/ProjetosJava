package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPontoAtracacaoPorId extends BusinessQuery<PontoAtracacao> {

    public ConsultaPontoAtracacaoPorId(Long id) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select p from {PontoAtracacao} p ")    
                .append(" join fetch p.{portoOperacional} po ")
                .append(" join fetch po.{porto} po ")                    
                .append(" where p.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{PontoAtracacao}", PontoAtracacao.class.getSimpleName())
                 .replace("{portoOperacional}", PontoAtracacao.PROP_PONTO_OPERACIONAL)
                 .replace("{porto}", PontoOperacional.PROP_PORTO)
                 .replace("{id}", PontoAtracacao.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
