package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaEmpresaPorAgenciaTipoServico extends BusinessQuery<EmpresaMaritima> {

    public ConsultaEmpresaPorAgenciaTipoServico(Agencia agencia, TipoServico tipoServico) {
        StringBuilder texto = new StringBuilder();
                              
        texto                
                .append(" select em from {EmpresaMaritima} em ")
                .append(" left join fetch em.{servicos} serv ") 
                .append(" where serv.{tipoServico} = :tipoServico ") 
                .append(" and em.{agencia} = :agencia ")
                .append(" and em.ativo = 'S' ")
                .append(" order by em.{nomeEmpresa} asc")
                ;
        
        addParameter("tipoServico", tipoServico);
        addParameter("agencia", agencia);
                    
        String hql = texto.toString();                     

        hql = hql.replace("{EmpresaMaritima}", EmpresaMaritima.class.getSimpleName())
                 .replace("{servicos}", EmpresaMaritima.PROP_SERVICOS)
                 .replace("{agencia}", EmpresaMaritima.PROP_AGENCIA)
                 .replace("{tipoServico}", Servico.PROP_TIPO_SERVICO)
                 .replace("{nomeEmpresa}", EmpresaMaritima.PROP_RAZAO_SOCIAL)
                ;
        
        this.setText(hql);
        
    }
    
}
