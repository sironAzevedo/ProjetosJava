package br.com.petrobras.sistam.service.query.agenciamento.anexo;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaAnexosDoAgenciamento extends BusinessQuery<Anexo> {

    public ConsultaAnexosDoAgenciamento(Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select a from {Anexo} a ")                
             .append(" where a.{agenciamento} = :agenciamento")
             ;
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Anexo}", Anexo.class.getSimpleName())
                 .replace("{agenciamento}", Anexo.PROP_AGENCIAMENTO)
                 ;
        
        this.setText(hql);
        addParameter("agenciamento", agenciamento);
        
    }
}
