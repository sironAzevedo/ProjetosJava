package br.com.petrobras.sistam.service.query.porto;

import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaPortoComplementoPorId extends BusinessQuery<PortoComplemento> {

    public ConsultaPortoComplementoPorId(Long id) {
        StringBuilder texto = new StringBuilder();
        texto
                .append(" select p from {PortoComplemento} p ")     
                .append(" join fetch p.{porto} po")
                .append(" join fetch po.{pais} ")
                .append(" where p.{id} = :id ");
        
        String hql = texto.toString();     
        hql = hql.replace("{PortoComplemento}", PortoComplemento.class.getSimpleName())
                 .replace("{porto}", PortoComplemento.PROP_PORTO)
                 .replace("{pais}", Porto.PROP_PAIS)
                 .replace("{id}", PortoComplemento.PROP_ID);
        
        this.setText(hql);
        this.addParameter("id", id);
    }
    
}
