package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaDocumentacaoCabotagemPorId extends BusinessQuery<DocumentacaoCabotagem> {

    public ConsultaDocumentacaoCabotagemPorId(Long id) {

        Map<String, Object> map = new HashMap<String, Object>();
                 
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select doc from {DocumentacaoCabotagem} doc ")
                .append(" join fetch doc.{agenciamento} ag ")
                .append(" left join fetch ag.{agencia} a ")
                .append(" left join fetch doc.{porto} p ")
                .append(" left join fetch doc.{documentacaoProdutos} dp ")
                .append(" left join fetch dp.{operacao} dp ")
                .append(" where doc.{id} = :id ");
        
        map.put("id", id);
        
        String hql = texto.toString();

        hql = hql.replace("{DocumentacaoCabotagem}", DocumentacaoCabotagem.class.getSimpleName())
                 .replace("{agenciamento}", DocumentacaoCabotagem.PROP_AGENCIAMENTO)
                 .replace("{documentacaoProdutos}", DocumentacaoCabotagem.PROP_DOCUMENTACAO_PRODUTOS)
                 .replace("{agencia}", DocumentacaoCabotagem.PROP_AGENCIA)
                 .replace("{operacao}", DocumentacaoOperacao.PROP_OPERACAO)
                 .replace("{porto}", DocumentacaoCabotagem.PROP_PORTO)
                 .replace("{id}", DocumentacaoCabotagem.PROP_ID);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
