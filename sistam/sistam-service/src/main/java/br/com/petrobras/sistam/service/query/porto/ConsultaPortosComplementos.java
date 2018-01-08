package br.com.petrobras.sistam.service.query.porto;

import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaPortosComplementos extends BusinessQuery<PortoComplemento> {

    public ConsultaPortosComplementos() {
                 
        StringBuilder texto = new StringBuilder();        

        texto        
            .append(" select pc from {PortoComplemento} pc ")
            .append(" join fetch pc.{porto} p ")
            .append(" join fetch p.{pais} ")
            ;
                            
        String hql = texto.toString();

        hql = hql.replace("{PortoComplemento}", PortoComplemento.class.getSimpleName())
                 .replace("{porto}", PortoComplemento.PROP_PORTO)
                 .replace("{pais}", Porto.PROP_PAIS)
                 ;
        
        this.setText(hql);
    }
    
}
