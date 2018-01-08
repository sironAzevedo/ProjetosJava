package br.com.petrobras.sistam.service.query.agenciamento.taxa;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaTaxasPorAgenciamento extends BusinessQuery<Taxa> {
    
    public ConsultaTaxasPorAgenciamento(Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select t from {Taxa} t ")
                .append(" join fetch t.responsavelCusto r ")
                .append(" where t.{agenciamento} = :agenciamento ");
        
        addParameter("agenciamento", agenciamento);
        
        String hql = texto.toString();

        hql = hql.replace("{Taxa}", Taxa.class.getSimpleName())
                 .replace("{agenciamento}", Taxa.PROP_AGENCIAMENTO)
               ;
        
        this.setText(hql);
    }
    
}
