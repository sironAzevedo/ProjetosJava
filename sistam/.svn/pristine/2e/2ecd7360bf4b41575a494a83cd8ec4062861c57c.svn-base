package br.com.petrobras.sistam.service.query.empresa;

import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresa;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaEmpresasPorFiltro extends BusinessQuery<EmpresaMaritima> {

    public ConsultaEmpresasPorFiltro(FiltroEmpresa filtro) {
        Map<String, Object> map = new HashMap<String, Object>();

        StringBuilder texto = new StringBuilder();
        texto
                .append(" select distinct e from {Empresa} e ")
                .append(" join fetch e.{agencia} ag ")
                .append(" left join fetch e.{servicos} ")
                .append(" where 1=1 ");


        if (filtro.getAtivo() != null) {
            texto.append(" and e.ativo = :ativo ");
            map.put("ativo", filtro.getAtivo());
        }
        
        if (filtro.getAgencia() != null) {
            texto.append(" and ag = :agencia ");
            map.put("agencia", filtro.getAgencia());
        }

        if (filtro.getCnpj() != null && !filtro.getCnpj().isEmpty()) {
            texto.append(" and e.{cnpj} = :cnpj");
            map.put("cnpj", filtro.getCnpjSemMascara());
        }
        if (filtro.getEmpresa() != null && !filtro.getEmpresa().isEmpty()) {
            texto.append(" and upper(e.{razaoSocial}) like '%'|| :nomeEmpresa ||'%' ");
            map.put("nomeEmpresa", filtro.getEmpresa().toUpperCase());
        }

        texto.append(" order by e.{razaoSocial}");

        String hql = texto.toString();

        hql = hql.replace("{Empresa}", EmpresaMaritima.class.getSimpleName())
                .replace("{agencia}", EmpresaMaritima.PROP_AGENCIA)
                .replace("{servicos}", EmpresaMaritima.PROP_SERVICOS)
                .replace("{cnpj}", EmpresaMaritima.PROP_CNPJ)
                .replace("{razaoSocial}", EmpresaMaritima.PROP_RAZAO_SOCIAL)
                ;

        this.setText(hql);
        setParameters(map);

    }
    
    
}
