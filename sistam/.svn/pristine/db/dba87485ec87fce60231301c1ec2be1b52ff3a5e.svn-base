package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaAgenciaPorId extends BusinessQuery<Agencia> {

    public ConsultaAgenciaPorId(Long id) {
        
        appendText(" select a from ", Agencia.class.getSimpleName() ," a ");
        appendText(" where a.", Agencia.PROP_ID , " = :id ");
        addParameter("id", id);
        
    }
    
}
