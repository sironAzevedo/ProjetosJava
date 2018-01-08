package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPontoAtracacaolPorPontoOperacional extends BusinessQuery<PontoAtracacao> {

    public ConsultaPontoAtracacaolPorPontoOperacional(PontoOperacional pontoOperacional) {

        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();
                                
        texto                
                .append(" select pa from {PontoAtracacao} pa ")                                               
                .append(" where pa.{PontoOperacional} = :pontoOperacional ");
        
        map.put("pontoOperacional", pontoOperacional);
                
        
        String hql = texto.toString();     
                

        hql = hql.replace("{PontoAtracacao}", PontoAtracacao.class.getSimpleName())
                 .replace("{PontoOperacional}", PontoAtracacao.PROP_PONTO_OPERACIONAL);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
