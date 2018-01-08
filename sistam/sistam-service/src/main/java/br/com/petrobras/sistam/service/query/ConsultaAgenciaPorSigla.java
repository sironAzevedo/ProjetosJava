package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaAgenciaPorSigla extends BusinessQuery<Agencia> {

    public ConsultaAgenciaPorSigla(String sigla) {
        
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select a from {Agencia} a ")                
                .append(" left join fetch a.{agenciaPortos}  p ")
                .append(" where a.{sigla} = :sigla ");
        
        map.put("sigla", sigla);
                
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Agencia}", Agencia.class.getSimpleName())
                 .replace("{agenciaPortos}", Agencia.PROP_AGENCIA_PORTOS)
                 .replace("{sigla}", Agencia.PROP_SIGLA);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
