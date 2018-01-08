package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaProdutos extends BusinessQuery<Produto> {

    public ConsultaProdutos(String nome) {
        
        Map<String, Object> map = new HashMap<String, Object>();
                 
        
        StringBuilder texto = new StringBuilder();
        texto
                .append(" select p from {Produto} p ")                
                .append(" where 1=1 ");
        
                
            if (nome!=null && !nome.isEmpty()) {
                texto.append(" and upper(p.{nomeCompleto}) like '%'|| upper(:nome) ||'%' ");
                map.put("nome", nome);
            }


       texto.append(" order by p.{nomeCompleto} asc ");
               
        String hql = texto.toString();     
                
        hql = hql.replace("{Produto}", Produto.class.getSimpleName())
                 .replace("{nomeCompleto}", Produto.PROP_NOME_COMPLETO);               
        
        this.setText(hql);
        setParameters(map);
        
        }
    
    
}
