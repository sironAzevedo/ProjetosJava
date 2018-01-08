package br.com.petrobras.sistam.service.query.agencia;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaAgenciasPorPorto extends BusinessQuery<Agencia> {

    public ConsultaAgenciasPorPorto(Porto porto) {
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select distinct ag from {Agencia} ag ")                
                .append(" left join ag.{agenciaPorto} ap ")
                .append(" where ap.{porto} = :porto");
        
        
        String hql = texto.toString()
                .replace("{Agencia}", Agencia.class.getSimpleName())
                .replace("{agenciaPorto}", Agencia.PROP_AGENCIA_PORTOS)
                .replace("{porto}", AgenciaPorto.PROP_PORTO)
                ;
                
        this.setText(hql);
        addParameter("porto", porto);
        
    }
    
}
