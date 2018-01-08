package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que consulta as pendências de um agenciamento tendo de opção de filtrar por resolvidas, 
 * não resolvidas ou ambas.
 * @author X4Z0
 */
public class ConsultaPendenciasDoAgenciamento extends BusinessQuery<Pendencia> {

    public ConsultaPendenciasDoAgenciamento(Agenciamento agenciamento, Boolean resolvida) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        texto.append(" from {AgenciamentoPendencia} p ")
             .append(" join fetch p.{agenciamento} agm ")
             .append(" join fetch agm.{agencia} ag ")
             .append(" where agm = :agenciamento ");
        
        map.put("agenciamento", agenciamento);
        
        if (resolvida != null){
            texto.append(" and p.{resolvida} = :resolvido");
            map.put("resolvido", resolvida);
        }
        
        String hql = texto.toString();     
                
        hql = hql.replace("{AgenciamentoPendencia}", Pendencia.class.getSimpleName())
                 .replace("{agenciamento}", Pendencia.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{resolvida}", Pendencia.PROP_RESOLVIDA);
        
        setText(hql);
        setParameters(map);
        
    }
    
}
