package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaDocumentacaoOperacaoPorId extends BusinessQuery<DocumentacaoOperacao> {

    public ConsultaDocumentacaoOperacaoPorId(Long id) {

        Map<String, Object> map = new HashMap<String, Object>();
                 
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select do from {DocumentacaoOperacao} do ")
                .append(" join fetch do.{operacao} o ")
                .append(" join fetch do.{documentacaoOperacao} dop ")
                .append(" join fetch o.{agenciamento} agm ")
                .append(" join fetch agm.{agencia} a ")
                .append(" join fetch o.{produto} p ")
                .append(" where do.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();

        hql = hql.replace("{DocumentacaoOperacao}", DocumentacaoOperacao.class.getSimpleName())
                 .replace("{operacao}", DocumentacaoOperacao.PROP_OPERACAO)
                 .replace("{documentacaoOperacao}", DocumentacaoOperacao.PROP_DOCUMENTACAO_OPERACAO)
                 .replace("{agenciamento}", Operacao.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{produto}", Operacao.PROP_PRODUTO)
                 .replace("{id}", DocumentacaoOperacao.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
