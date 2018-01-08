package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultaPontoAtracacaolPorAgencia extends BusinessQuery<PontoAtracacao> {

    public ConsultaPontoAtracacaolPorAgencia(Agencia agencia, List<Porto> portosDaAgencia) {
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();
                              
        texto                
                .append(" select pa from {PontoAtracacao} pa ")                                               
                .append(" join fetch pa.{pontoOperacional} po ")                                           
                .append(" join fetch po.{porto} p ")                                           
                .append(" where p in (:portos) ");        
        
        texto.append(" order by pa.{nomePontoAtracacao} asc");
        
        map.put("portos", portosDaAgencia);
                        
        String hql = texto.toString();     
                

        hql = hql.replace("{PontoAtracacao}", PontoAtracacao.class.getSimpleName())
                 .replace("{pontoOperacional}", PontoAtracacao.PROP_PONTO_OPERACIONAL)
                 .replace("{porto}", PontoOperacional.PROP_PORTO)
                 .replace("{nomePontoAtracacao}",PontoAtracacao.PROP_NOME) ;
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
