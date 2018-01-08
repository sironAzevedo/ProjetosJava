package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.EmpresaProtecaoServico;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @author adzk
 */
public class ConsultaEmpresasProtecaoAtivaPorTipoNomeCnpj extends BusinessQuery<EmpresaProtecao> {

    public ConsultaEmpresasProtecaoAtivaPorTipoNomeCnpj(TipoServicoProtecao tipo, String razaoSocial, String cnpj) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(e)")
                .append(" from {empresa} e")
                .append(" join fetch e.{servicos} s")
                .append(" where e.{ativa} = true ");

        if (tipo != null) {
            hql.append(" and s.{tipo} = :tipo");
            addParameter("tipo", tipo);
        }

        if (StringUtils.isNotBlank(razaoSocial)) {
            hql.append(" and UPPER(e.{razaoSocial}) like '%'|| :razaoSocial || '%'");
            addParameter("razaoSocial", razaoSocial.toUpperCase());
        }

        if (StringUtils.isNotBlank(cnpj)) {
            hql.append(" and e.{cnpj} = :cnpj");
            addParameter("cnpj", cnpj);
        }

        hql.append(" order by e.{razaoSocial}");

        String text = hql.toString()
                .replace("{empresa}", EmpresaProtecao.class.getSimpleName())
                .replace("{ativa}", EmpresaProtecao.PROP_ATIVA)
                .replace("{cnpj}", EmpresaProtecao.PROP_CNPJ)
                .replace("{razaoSocial}", EmpresaProtecao.PROP_RAZAO_SOCIAL)
                .replace("{servicos}", EmpresaProtecao.PROP_SERVICOS)
                .replace("{tipo}", EmpresaProtecaoServico.PROP_TIPO);

        this.setText(text);
    }
}