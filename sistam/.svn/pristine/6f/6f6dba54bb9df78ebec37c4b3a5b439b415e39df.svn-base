package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.AgenciamentoViagem;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaAgenciamentoViagemPorId extends BusinessQuery<AgenciamentoViagem> {

    public ConsultaAgenciamentoViagemPorId(Long id) {

        Map<String, Object> map = new HashMap<String, Object>();
                
        StringBuilder texto = new StringBuilder();    
        
        texto
                .append(" select agv from {AgenciamentoViagem} agv ")        
                .append(" where agv.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();

        hql = hql.replace("{AgenciamentoViagem}", AgenciamentoViagem.class.getSimpleName())
                 .replace("{id}", AgenciamentoViagem.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
