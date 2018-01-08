package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 * @author adzk
 */
public class ConsultaEmpresaProtecaoPorCnpj  extends BusinessQuery<EmpresaProtecao> {

    public ConsultaEmpresaProtecaoPorCnpj(String cnpj) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(e)")
                .append(" from {empresa} e")
                .append(" join fetch e.{servicos} s")
                .append(" where e.{cnpj} = :cnpj");

        addParameter("cnpj", cnpj);

        String text = hql.toString()
                .replace("{empresa}", EmpresaProtecao.class.getSimpleName())
                .replace("{cnpj}", EmpresaProtecao.PROP_CNPJ)
                .replace("{servicos}", EmpresaProtecao.PROP_SERVICOS);
        this.setText(text);
    }

}
