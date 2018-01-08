package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaAgencias extends BusinessQuery<Agencia> {

    public ConsultaAgencias() {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select distinct a from {Agencia} a ")                
                .append(" left join fetch a.{agenciaPortos}  p ")
                .append(" left join fetch p.{porto}  po ")
                .append(" order by a.{nome} ");
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Agencia}", Agencia.class.getSimpleName())
                 .replace("{agenciaPortos}", Agencia.PROP_AGENCIA_PORTOS)
                 .replace("{porto}", AgenciaPorto.PROP_PORTO)
                 .replace("{nome}", Agencia.PROP_NOME);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
