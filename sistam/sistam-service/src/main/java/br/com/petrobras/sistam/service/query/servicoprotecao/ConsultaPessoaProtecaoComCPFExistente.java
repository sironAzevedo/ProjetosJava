package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 * @author adzk
 */
public class ConsultaPessoaProtecaoComCPFExistente extends BusinessQuery<Pessoa> {

    public ConsultaPessoaProtecaoComCPFExistente(Pessoa pessoa) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(p)")
                .append(" from {pessoa} p")
                .append(" where p.{cpf} = :cpf")
                .append(" and p.{id} != :id");

        addParameter("cpf", pessoa.getCpf());
        addParameter("id", pessoa.getId() == null ? 0 : pessoa.getId());

        String text = hql.toString()
                .replace("{pessoa}", Pessoa.class.getSimpleName())
                .replace("{id}", Pessoa.PROP_ID)
                .replace("{cpf}", Pessoa.PROP_CPF);
        this.setText(text);
    }
}
