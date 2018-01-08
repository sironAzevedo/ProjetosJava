package br.com.petrobras.sistam.service.query.porto;

import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaPortosComplementosPorPorto extends BusinessQuery<PortoComplemento> {

    public ConsultaPortosComplementosPorPorto(Porto porto) {
                 
        StringBuilder texto = new StringBuilder();        

        texto        
            .append(" select pc from {PortoComplemento} pc ")
            .append(" join fetch pc.{porto} p ")
            .append(" join fetch p.{pais} ")
            .append(" where p.{id} = :idPorto")
            ;
                            
        String hql = texto.toString();

        hql = hql.replace("{PortoComplemento}", PortoComplemento.class.getSimpleName())
                 .replace("{porto}", PortoComplemento.PROP_PORTO)
                 .replace("{pais}", Porto.PROP_PAIS)
                 .replace("{id}", Porto.PROP_ID)
                 ;
        
        this.setText(hql);
        addParameter("idPorto", porto.getId());
    }
    
}
