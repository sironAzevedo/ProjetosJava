package br.com.petrobras.sistam.service.query.agenciamento.taxa;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaTaxasPorAgenciamentoETipo extends BusinessQuery<Taxa> {
    
    public ConsultaTaxasPorAgenciamentoETipo(Agenciamento agenciamento, TipoTaxa tipo) {
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select t from {Taxa} t ")
                .append(" where t.{agenciamento} = :agenciamento ")
                .append(" and t.{tipo} = :tipo ")
                ;
        
        addParameter("agenciamento", agenciamento);
        addParameter("tipo", tipo);
        
        String hql = texto.toString();

        hql = hql.replace("{Taxa}", Taxa.class.getSimpleName())
                 .replace("{agenciamento}", Taxa.PROP_AGENCIAMENTO)
                 .replace("{tipo}", Taxa.PROP_TIPO_TAXA)
               ;
        
        this.setText(hql);
    }
    
}
