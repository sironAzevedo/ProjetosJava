package br.com.petrobras.sistam.service.query.agencia;

import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaAgenciaSigoPorNome extends BusinessQuery<AgenciaSigo> {

    public ConsultaAgenciaSigoPorNome(String nome) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select a from {AgenciaSigo} a ")                
             .append(" where a.{nome} like  '%' || :nome || '%' ");
        
        addParameter("nome", nome.toUpperCase());
                
          texto.append(" order by a.{nome}");
        String hql = texto.toString();     
                

        hql = hql.replace("{AgenciaSigo}", AgenciaSigo.class.getSimpleName())
                 .replace("{nome}", AgenciaSigo.PROP_NOME);
        
        this.setText(hql);
    }
    
}
