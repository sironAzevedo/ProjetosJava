package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @author adzk
 */
public class ConsultaPessoasProtecaoAtivaPorEmpresaNome extends BusinessQuery<Pessoa> {

    public ConsultaPessoasProtecaoAtivaPorEmpresaNome(EmpresaProtecao empresa, String nome) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(p)")
                .append(" from {pessoa} p")
                .append(" left join fetch p.{empresa} e")
                .append(" where p.{ativa} = true");

        if (StringUtils.isNotBlank(nome)) {
            hql.append(" and UPPER(p.{nome}) like '%' || :nome || '%'");
            addParameter("nome", nome.toUpperCase());
        }

        if (empresa != null) {
            hql.append(" and e.{empresaId} = :empresaId");
            addParameter("empresaId", empresa.getId());
        }

        hql.append(" order by p.{nome}");

        String text = hql.toString()
                .replace("{pessoa}", Pessoa.class.getSimpleName())
                .replace("{empresa}", Pessoa.PROP_EMPRESA)
                .replace("{nome}", Pessoa.PROP_NOME)
                .replace("{ativa}", Pessoa.PROP_ATIVA)
                .replace("{empresaId}", EmpresaProtecao.PROP_ID);
        this.setText(text);
    }
}
