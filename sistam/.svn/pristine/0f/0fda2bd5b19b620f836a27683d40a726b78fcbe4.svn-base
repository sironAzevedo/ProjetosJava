package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaDocumentacaoOperacaoPorDocumentacao extends BusinessQuery<DocumentacaoOperacao> {

    public ConsultaDocumentacaoOperacaoPorDocumentacao(DocumentacaoCabotagem documentacaoCabotagem) {

        Map<String, Object> map = new HashMap<String, Object>();
                 
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select dp from {DocumentacaoOperacao} dp ")
                .append(" join fetch dp.operacao o ")
                .append(" join fetch o.produto p ")
                .append(" where dp.{documentacaoOperacao} = :documentacaoCabotagem ");
        
        map.put("documentacaoCabotagem", documentacaoCabotagem);
        
        String hql = texto.toString();

        hql = hql.replace("{DocumentacaoOperacao}", DocumentacaoOperacao.class.getSimpleName())
                 .replace("{operacao}", DocumentacaoOperacao.PROP_DOCUMENTACAO_OPERACAO)
                 .replace("{produto}", Operacao.PROP_PRODUTO)
                 .replace("{documentacaoOperacao}", DocumentacaoOperacao.PROP_DOCUMENTACAO_OPERACAO);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
