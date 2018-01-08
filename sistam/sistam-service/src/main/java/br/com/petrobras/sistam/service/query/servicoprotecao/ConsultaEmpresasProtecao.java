package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.EmpresaProtecaoServico;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 * @author adzk
 */
public class ConsultaEmpresasProtecao extends BusinessQuery<EmpresaProtecao> {

    public ConsultaEmpresasProtecao() {
        this(null, false);
    }

    public ConsultaEmpresasProtecao(boolean apenasAtiva) {
        this(null, apenasAtiva);
    }

    public ConsultaEmpresasProtecao(TipoServicoProtecao tipoServicoProtecao) {
        this(tipoServicoProtecao, false);
    }

    private ConsultaEmpresasProtecao(TipoServicoProtecao tipoServicoProtecao, boolean apenasAtiva) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(e)")
                .append(" from {empresa} e")
                .append(" join fetch e.{servicos} s")
                .append(" where 1 = 1");

        if (apenasAtiva) {
            hql.append(" and e.{ativa} = true");
        }

        if (tipoServicoProtecao != null) {
            hql.append(" and s.{tipo} = :tipo");
            addParameter("tipo", tipoServicoProtecao);
        }

        hql.append(" order by e.{razaoSocial}");

        String text = hql.toString()
                .replace("{empresa}", EmpresaProtecao.class.getSimpleName())
                .replace("{razaoSocial}", EmpresaProtecao.PROP_RAZAO_SOCIAL)
                .replace("{servicos}", EmpresaProtecao.PROP_SERVICOS)
                .replace("{ativa}", EmpresaProtecao.PROP_ATIVA)
                .replace("{tipo}", EmpresaProtecaoServico.PROP_TIPO);

        this.setText(text);
    }
}