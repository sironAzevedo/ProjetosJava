package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaEmbarcacoes extends BusinessQuery<Embarcacao> {

    public ConsultaEmbarcacoes(String nome) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();
        texto
                .append(" select e from {Embarcacao} e ")                
                .append(" where 1=1 ");
        
                
            if (nome!=null && !nome.isEmpty()) {        
                texto.append(" and upper(e.{nomeCompleto}) like '%'|| upper(:nome) ||'%' ");
                map.put("nome", nome);
            }
            texto.append(" order by e.{nomeCompleto}");
            
            String hql = texto.toString();     
                
        hql = hql.replace("{Embarcacao}", Embarcacao.class.getSimpleName())
                 .replace("{nomeCompleto}", Embarcacao.PROP_NOME_COMPLETO)
                ;
        
        this.setText(hql);
        setParameters(map);
        
        }
    
    
}
