package br.com.petrobras.sistam.service.query.manobra;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoResponsavelPorId extends BusinessQuery<ServicoResponsavel> {

    public ConsultaServicoResponsavelPorId(Long id) {
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select sr from {ServicoResponsavel} sr ")
                .append(" join fetch sr.{servicoManobra} sm ")
                .append(" join fetch sr.{servico} ")
                .append(" join fetch sm.{manobra} m ")
                .append(" join fetch sm.{empresa} em ")
                .append(" join fetch m.{agenciamento} a ")
                .append(" join fetch a.{agencia} ag ")
                .append(" where sr.{id} = :id");
        
        addParameter("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{ServicoResponsavel}", ServicoResponsavel.class.getSimpleName())
                 .replace("{servicoManobra}", ServicoResponsavel.PROP_SERVICO_MANOBRA)
                 .replace("{servico}", ServicoResponsavel.PROP_SERVICO)
                 .replace("{manobra}", ServicoManobra.PROP_MANOBRA)
                 .replace("{empresa}", ServicoManobra.PROP_EMPRESA_MARITIMA)
                 .replace("{agenciamento}", Manobra.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{id}", ServicoResponsavel.PROP_ID);
        
        this.setText(hql);
        
    }
    
}
