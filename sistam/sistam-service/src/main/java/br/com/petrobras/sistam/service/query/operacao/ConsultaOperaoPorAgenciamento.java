package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaOperaoPorAgenciamento extends BusinessQuery<Operacao> {

    public ConsultaOperaoPorAgenciamento(Agenciamento agenciamento) {

        Map<String, Object> map = new HashMap<String, Object>();
                 
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select o from {Operacao} o ")
                .append(" join fetch o.{agenciamento} agm ")
                .append(" join fetch agm.{agencia} age ")
                .append(" left join fetch o.{produto} p")
                .append(" where o.{agenciamento} = :agenciamento ");
        
        map.put("agenciamento", agenciamento);
        
        String hql = texto.toString();

        hql = hql.replace("{Operacao}", Operacao.class.getSimpleName())
                 .replace("{agenciamento}", Operacao.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{produto}", Operacao.PROP_PRODUTO);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
