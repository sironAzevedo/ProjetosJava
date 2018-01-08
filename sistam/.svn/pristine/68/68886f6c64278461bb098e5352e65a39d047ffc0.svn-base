package br.com.petrobras.sistam.service.query.agenciamento.anexo;

import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaArquivoDoAnexo extends BusinessQuery<Arquivo> {

    public ConsultaArquivoDoAnexo(Anexo anexo) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select arq from {Arquivo} arq ")                
             .append(" where arq.{id} = :idAnexo")
             ;
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Arquivo}", Arquivo.class.getSimpleName())
                 .replace("{id}", Arquivo.PROP_ID)
                 ;
        
        this.setText(hql);
        addParameter("idAnexo", anexo.getId());
        
    }
}
