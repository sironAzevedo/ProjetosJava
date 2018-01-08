package br.com.petrobras.sistam.service.query.agencia;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaDestinatariosDaAgencia extends BusinessQuery<Destinatario> {

    public ConsultaDestinatariosDaAgencia(Agencia agencia) {
        StringBuilder texto = new StringBuilder();

        Map<String, Object> map = new HashMap<String, Object>();
        
        texto.append(" select d from {Destinatario} d ")                
             .append(" join fetch d.{agencia} a " )
             .append(" where a = :agencia " );
        
         map.put("agencia", agencia);
                
        String hql = texto.toString();     
                

        hql = hql.replace("{Destinatario}", Destinatario.class.getSimpleName())
                 .replace("{agencia}", Destinatario.PROP_AGENCIA);
                         
        
        this.setText(hql);
       setParameters(map);
    }
    
}
