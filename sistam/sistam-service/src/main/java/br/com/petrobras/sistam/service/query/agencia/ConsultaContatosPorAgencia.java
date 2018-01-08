package br.com.petrobras.sistam.service.query.agencia;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaContatosPorAgencia extends BusinessQuery<RepresentanteLegal> {

    public ConsultaContatosPorAgencia(Agencia agencia, Boolean ativo) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select ac from {AgenciaContato} ac ")                
                .append(" join fetch ac.agencia a ")
                .append(" where ac.agencia = :agencia ");
        
        map.put("agencia", agencia);
        
        if (ativo != null) {
            texto.append(" and ac.ativo = :ativo ");
            map.put("ativo", ativo);
        }
        
        String hql = texto.toString();     
                
        hql = hql.replace("{AgenciaContato}", RepresentanteLegal.class.getSimpleName());
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
