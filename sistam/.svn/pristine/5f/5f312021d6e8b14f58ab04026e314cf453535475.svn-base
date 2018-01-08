package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresaProtecao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @author adzk
 */
public class ConsultaEmpresasProtecaoPorFiltro extends BusinessQuery<EmpresaProtecao> {

    public ConsultaEmpresasProtecaoPorFiltro(FiltroEmpresaProtecao filtro) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(e)")
                .append(" from {empresa} e")
                .append(" left join fetch e.{servicos} s")
                .append(" where 1 = 1");

        if (StringUtils.isNotBlank(filtro.getRazaoSocial())) {
            hql.append(" and UPPER(e.{razaoSocial}) like '%'|| :razaoSocial || '%'");
            addParameter("razaoSocial", filtro.getRazaoSocial().toUpperCase());
        }

        if (StringUtils.isNotBlank(filtro.getCnpj())) {
            hql.append(" and e.{cnpj} = :cnpj");
            addParameter("cnpj", filtro.getCnpj());
        }

        hql.append(" order by e.{razaoSocial}");

        String text = hql.toString()
                .replace("{empresa}", EmpresaProtecao.class.getSimpleName())
                .replace("{cnpj}", EmpresaProtecao.PROP_CNPJ)
                .replace("{razaoSocial}", EmpresaProtecao.PROP_RAZAO_SOCIAL)
                .replace("{servicos}", EmpresaProtecao.PROP_SERVICOS);
        
        this.setText(text);
    }
}