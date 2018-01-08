package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaOperaoPorAgenciamentoETipo extends BusinessQuery<Operacao> {

    public ConsultaOperaoPorAgenciamentoETipo(Agenciamento agenciamento, TipoOperacao tipo) {
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select o from {Operacao} o ")
                .append(" join o.{agenciamento} agm ")
                .append(" join fetch o.{produto} p")
                .append(" where o.{agenciamento} = :agenciamento ")
                .append(" and o.{tipo} = :tipo ");
        
        
        
        String hql = texto.toString();

        hql = hql.replace("{Operacao}", Operacao.class.getSimpleName())
                 .replace("{agenciamento}", Operacao.PROP_AGENCIAMENTO)
                 .replace("{produto}", Operacao.PROP_PRODUTO)
                 .replace("{tipo}", Operacao.PROP_TIPO_OPERACAO);
        
        this.setText(hql);
        addParameter("agenciamento", agenciamento);
        addParameter("tipo", tipo);
        
    }
    
}
