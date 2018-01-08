package br.com.petrobras.sistam.service.query.manobra;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoManobraPorId extends BusinessQuery<ServicoManobra> {

    public ConsultaServicoManobraPorId(Long id) {
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select sm from {ServicoManobra} sm ")
                .append(" join fetch sm.{manobra} m ")
                .append(" join fetch m.{agenciamento} a ")
                .append(" join fetch a.{agencia} ag ")
                .append(" join fetch sm.{empresa}  ")
                .append(" left join fetch sm.{responsaveis} resp ")
                .append(" left join fetch resp.{servico} s ")
                .append(" left join fetch m.{servicos} ser ")
                .append(" left join fetch ser.{empresa} em")
                .append(" where sm.{id} = :id");
        
        addParameter("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{ServicoManobra}", ServicoManobra.class.getSimpleName())
                 .replace("{manobra}", ServicoManobra.PROP_MANOBRA)
                 .replace("{agenciamento}", Manobra.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{empresa}", ServicoManobra.PROP_EMPRESA_MARITIMA)
                 .replace("{responsaveis}", ServicoManobra.PROP_RESPONSAVEIS)
                 .replace("{servico}", ServicoResponsavel.PROP_SERVICO)
                 .replace("{servicos}", Manobra.PROP_SERVICOS)
                 .replace("{id}", Manobra.PROP_ID);
        
        this.setText(hql);
        
    }
    
}
