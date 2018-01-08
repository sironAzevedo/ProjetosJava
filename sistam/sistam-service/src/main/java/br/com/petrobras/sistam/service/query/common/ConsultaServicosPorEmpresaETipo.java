package br.com.petrobras.sistam.service.query.common;

import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicosPorEmpresaETipo extends BusinessQuery<Servico> {
    
    public ConsultaServicosPorEmpresaETipo(EmpresaMaritima empresa, TipoServico tipo) {
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select s from {Servico} s ")
                .append(" where s.{empresa} = :empresa")
                .append(" and s.{tipo} = :tipo")
                .append(" order by s.nomeServico ");
        
        addParameter("empresa", empresa);
        addParameter("tipo", tipo);
        
        String hql = texto.toString();

        hql = hql.replace("{Servico}", Servico.class.getSimpleName())
                 .replace("{empresa}", Servico.PROP_EMPRESA)
                 .replace("{tipo}", Servico.PROP_TIPO_SERVICO)
               ;
        
        this.setText(hql);
    }
    
}
