package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 *
 */
public class ConsultaPessoaProtecaoPorId extends BusinessQuery<Pessoa> {

    public ConsultaPessoaProtecaoPorId(long id) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(p)")
                .append(" from {pessoa} p")
                .append(" left join fetch p.{empresa} e")
                .append(" where p.{id} = :id");

        addParameter("id", id);

        String text = hql.toString()
                .replace("{pessoa}", Pessoa.class.getSimpleName())
                .replace("{id}", Pessoa.PROP_ID)
                .replace("{empresa}", Pessoa.PROP_EMPRESA);
        this.setText(text);
    }
}
