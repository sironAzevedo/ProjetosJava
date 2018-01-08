package br.com.petrobras.sistam.service.query.agencia;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaOrgaosDespachoPorAgencia extends BusinessQuery<AgenciaOrgaoDespacho> {

    public ConsultaOrgaosDespachoPorAgencia(Agencia agencia) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select ao from {AgenciaOrgaoDespacho} ao ")                
                .append(" join fetch ao.agencia a ")
                .append(" where ao.agencia = :agencia ");
        
        map.put("agencia", agencia);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{AgenciaOrgaoDespacho}", AgenciaOrgaoDespacho.class.getSimpleName());
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
