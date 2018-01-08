package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que consulta uma pendência não resolvidas de um agenciamento de acordo com o tipo informado.
 */
public class ConsultaPendenciasNaoResolvidasDoAgenciamentoPorTipo extends BusinessQuery<Pendencia> {

    public ConsultaPendenciasNaoResolvidasDoAgenciamentoPorTipo(Agenciamento agenciamento, TipoPendencia tipo) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        texto.append(" from {Pendencia} p ")
             .append(" where p.{agenciamento} = :agenciamento ")
             .append(" and p.{tipo} = :tipo ")
             .append(" and p.{resolvida} = false");
        
        map.put("agenciamento", agenciamento);
        map.put("tipo", tipo);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Pendencia}", Pendencia.class.getSimpleName())
                 .replace("{agenciamento}", Pendencia.PROP_AGENCIAMENTO)
                 .replace("{tipo}", Pendencia.PROP_TIPO_PENDENCIA)
                 .replace("{resolvida}", Pendencia.PROP_RESOLVIDA);
        
        setText(hql);
        setParameters(map);
    }
    
}
