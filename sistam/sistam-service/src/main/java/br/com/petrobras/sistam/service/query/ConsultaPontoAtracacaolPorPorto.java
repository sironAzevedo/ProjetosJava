package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPontoAtracacaolPorPorto extends BusinessQuery<PontoAtracacao> {

    public ConsultaPontoAtracacaolPorPorto(Porto porto) {

        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();
                              
        texto                
                .append(" select distinct pa from {PontoAtracacao} pa ")                                               
                .append(" join fetch pa.{pontoOperacional} po ")                                           
                .append(" join fetch po.{porto} porto ")                                           
                .append(" left join fetch porto.{pontosOperacionais} ")                                           
                .append(" where porto = :porto ");        
        
        texto.append(" order by pa.{nomePontoAtracacao} asc");
        
        map.put("porto", porto);
                        
        String hql = texto.toString();     
                

        hql = hql.replace("{PontoAtracacao}", PontoAtracacao.class.getSimpleName())
                 .replace("{pontoOperacional}", PontoAtracacao.PROP_PONTO_OPERACIONAL)
                 .replace("{porto}", PontoOperacional.PROP_PORTO)
                 .replace("{nomePontoAtracacao}", PontoAtracacao.PROP_NOME) 
                 .replace("{pontosOperacionais}", Porto.PROP_PONTOS_OPERACIONAIS) ;
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
