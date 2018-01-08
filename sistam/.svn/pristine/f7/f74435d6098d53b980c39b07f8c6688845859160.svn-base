package br.com.petrobras.sistam.service.query.agenciamento.taxa;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaTaxaPorId extends BusinessQuery<Taxa> {

    public ConsultaTaxaPorId(Long id) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select t from {Taxa} t ")
                .append(" join fetch t.{agenciamento} a ")
                .append(" join fetch a.{agencia} ag ")
                .append(" where t.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Taxa}", Taxa.class.getSimpleName())
                 .replace("{agenciamento}", Taxa.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{id}", Taxa.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
