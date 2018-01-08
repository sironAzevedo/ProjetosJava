package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 * @author adzk
 */
public class ConsultaEmpresaProtecaoComCnpjExistente extends BusinessQuery<EmpresaProtecao> {

    public ConsultaEmpresaProtecaoComCnpjExistente(EmpresaProtecao empresa) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(e)")
                .append(" from {empresa} e")
                .append(" join fetch e.{servicos} s")
                .append(" where e.{cnpj} = :cnpj")
                .append(" and e.{id} != :id");

        addParameter("cnpj", empresa.getCnpj());
        addParameter("id", empresa.getId() == null ? 0 : empresa.getId());

        String text = hql.toString()
                .replace("{empresa}", EmpresaProtecao.class.getSimpleName())
                .replace("{id}", EmpresaProtecao.PROP_ID)
                .replace("{cnpj}", EmpresaProtecao.PROP_CNPJ)
                .replace("{servicos}", EmpresaProtecao.PROP_SERVICOS);
        this.setText(text);
    }
}