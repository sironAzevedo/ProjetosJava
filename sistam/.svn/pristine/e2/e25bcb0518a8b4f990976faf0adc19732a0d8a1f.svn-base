package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ExcluirPendenciaPorAgenciamento extends BusinessQuery {

    public ExcluirPendenciaPorAgenciamento(Agenciamento agenciamento) {

        Map<String, Object> map = new HashMap<String, Object>();
                 
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" delete from {PendenciaAgenciamento} pa ")
                .append(" where pa.{agenciamento} = :agenciamento ");
        
        map.put("agenciamento", agenciamento);
        
        String hql = texto.toString();

        hql = hql.replace("{PendenciaAgenciamento}", Pendencia.class.getSimpleName())
                 .replace("{agenciamento}", Pendencia.PROP_AGENCIAMENTO);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
