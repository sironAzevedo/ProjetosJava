package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 * Classe que consulta o cancelamento de um agenciamento
 */
public class ConsultacCancelamentoDoAgenciamento extends BusinessQuery<CancelamentoAgenciamento> {

    public ConsultacCancelamentoDoAgenciamento(Long idAgenciamento) {
        StringBuilder texto = new StringBuilder();

        texto.append(" from {CancelamentoAgenciamento} can ")
             .append(" join fetch can.{agenciamento} ag")
             .append(" join fetch ag.{agencia} ")
             .append(" where ag.{id} = :idAgenciamento ");
        
        addParameter("idAgenciamento", idAgenciamento);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{CancelamentoAgenciamento}", CancelamentoAgenciamento.class.getSimpleName())
                 .replace("{agenciamento}", CancelamentoAgenciamento.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{id}", Agenciamento.PROP_ID)  
                 ;
        
        setText(hql);
    }
    
}
