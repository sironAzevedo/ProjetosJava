package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPortos extends BusinessQuery<Porto> {

    public ConsultaPortos(String nome, Pais pais) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();
        texto
                .append(" select p from {Porto} p ")   
                .append(" join fetch p.{pais} pa ")
                .append(" where 1=1 ");
        
                
            if (nome!=null && !nome.isEmpty()) {
                texto.append(" and (upper(p.{nomeCompleto}) like '%'|| upper(:nome) ||'%' ");
                texto.append(" or upper(p.{apelido}) like '%'|| upper(:nome) ||'%') ");
                map.put("nome", nome);
            }

            if (pais!=null ) {
                texto.append(" and (p.{pais}) = :pais");
                map.put("pais", pais);
            }
            texto.append(" order by p.{nomeCompleto}");
       
        String hql = texto.toString();     
                
        hql = hql.replace("{Porto}", Porto.class.getSimpleName())
                 .replace("{nomeCompleto}", Porto.PROP_NOME_COMPLETO)
                 .replace("{apelido}", Porto.PROP_APELIDO)
                 .replace("{pais}", Porto.PROP_PAIS);
        
        this.setText(hql);
        setParameters(map);
        
   }
    
    
}
