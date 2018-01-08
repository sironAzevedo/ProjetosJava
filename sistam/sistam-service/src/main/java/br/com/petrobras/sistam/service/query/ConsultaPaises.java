package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPaises extends BusinessQuery<Pais> {

    public ConsultaPaises(String nome) {
        
        Map<String, Object> map = new HashMap<String, Object>();
                 
        
        StringBuilder texto = new StringBuilder();
        texto
                .append(" select p from {Pais} p ")                
                .append(" where 1=1 ");
        
                
            if (nome!=null && !nome.isEmpty()) {
                texto.append(" and (upper(p.{nomeCompleto}) like '%'|| upper(:nome) ||'%' ");
                texto.append(" or upper(p.{sigla}) like '%'|| upper(:nome) ||'%') ");
                map.put("nome", nome);
            }
            
            texto.append(" order by p.{nomeCompleto}");


       
        String hql = texto.toString();     
                
        hql = hql.replace("{Pais}", Pais.class.getSimpleName())
                 .replace("{nomeCompleto}", Pais.PROP_NOME_COMPLETO)
                 .replace("{sigla}", Pais.PROP_SIGLA);
        
        this.setText(hql);
        setParameters(map);
        
        }
    
    
}
