package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaEmbarcacaoPorId extends BusinessQuery<Embarcacao> {

    public ConsultaEmbarcacaoPorId(String id) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select e from {Embarcacao} e ")                
                .append(" join fetch e.bandeira  ") 
                .append(" left join fetch e.{portoRegistro}  ") 
                .append(" left join fetch e.{paisInspecao}  ") 
                .append(" where e.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Embarcacao}", Embarcacao.class.getSimpleName())
                 .replace("{id}", Embarcacao.PROP_ID)
                 .replace("{portoRegistro}", Embarcacao.PROP_PORTO_REGISTRO)
                 .replace("{paisInspecao}", Embarcacao.PROP_PAIS_INSPECAO);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
