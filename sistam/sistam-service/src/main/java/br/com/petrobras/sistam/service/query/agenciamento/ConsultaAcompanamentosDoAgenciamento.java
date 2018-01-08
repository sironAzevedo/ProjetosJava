package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaAcompanamentosDoAgenciamento extends BusinessQuery<Acompanhamento> {

    public ConsultaAcompanamentosDoAgenciamento(Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();

        texto.append(" from {Acompanhamento} ac ")
             .append(" join fetch ac.agenciamento ag")
             .append(" join fetch ag.agencia ")
             .append(" join fetch ag.embarcacao ")
             .append(" where ag.id = :idAgenciamento ")
             .append(" order by ac.data desc ");
        
        addParameter("idAgenciamento", agenciamento.getId());
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Acompanhamento}", Acompanhamento.class.getSimpleName())
                 ;
        
        setText(hql);
    }
    
}
