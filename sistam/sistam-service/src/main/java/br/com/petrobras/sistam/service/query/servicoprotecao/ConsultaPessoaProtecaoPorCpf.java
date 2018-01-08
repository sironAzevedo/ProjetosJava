package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 *
 */
public class ConsultaPessoaProtecaoPorCpf extends BusinessQuery<Pessoa> {

    public ConsultaPessoaProtecaoPorCpf(String cpf) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(p)")
                .append(" from {pessoa} p")
                .append(" left join fetch p.{empresa} e")
                .append(" where p.{cpf} = :cpf");

        addParameter("cpf", cpf);

        String text = hql.toString()
                .replace("{pessoa}", Pessoa.class.getSimpleName())
                .replace("{cpf}", Pessoa.PROP_CPF)
                .replace("{empresa}", Pessoa.PROP_EMPRESA);
        this.setText(text);
    }
}
