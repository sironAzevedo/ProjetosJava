package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaOperacaoPorId extends BusinessQuery<Operacao> {

    public ConsultaOperacaoPorId(Long id) {

        Map<String, Object> map = new HashMap<String, Object>();
                 
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select o from {Operacao} o ")
                .append(" join fetch o.{agenciamento} ag ")
                .append(" join fetch ag.{embarcacao} e ")
                .append(" join fetch ag.{agencia} a ")
                .append(" join fetch o.{produto} ps ")
                .append(" where o.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();

        hql = hql.replace("{Operacao}", Operacao.class.getSimpleName())
                 .replace("{agenciamento}", Operacao.PROP_AGENCIAMENTO)
                 .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{produto}", Operacao.PROP_PRODUTO)
                 .replace("{id}", Operacao.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
