package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que consulta pendencias de um agenciamento de acordo com o tipo informado.
 */
public class ConsultaPendenciasDoAgenciamentoPorTipo extends BusinessQuery<Pendencia> {

    public ConsultaPendenciasDoAgenciamentoPorTipo(Agenciamento agenciamento, TipoPendencia tipo) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        texto.append(" select p from Pendencia p ")
             .append(" join fetch p.agenciamento agm")
             .append(" join fetch agm.agencia ag")
             .append(" where p.agenciamento = :agenciamento ")
             .append(" and p.tipoPendencia = :tipo ");
        
        map.put("agenciamento", agenciamento);
        map.put("tipo", tipo);
        
        String hql = texto.toString();     
                
        /*
        hql = hql.replace("{Pendencia}", Pendencia.class.getSimpleName())
                 .replace("{agenciamento}", Pendencia.PROP_AGENCIAMENTO)
                 .replace("{tipo}", Pendencia.PROP_TIPO_PENDENCIA);
        */
        
        setText(hql);
        setParameters(map);
    }
    
}
