package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaDocumentacaoCabotagemPorAgenciamento extends BusinessQuery<DocumentacaoCabotagem> {

    public ConsultaDocumentacaoCabotagemPorAgenciamento(Agenciamento agenciamento, TipoOperacao tipo) {

        Map<String, Object> map = new HashMap<String, Object>();
                 
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select distinct doc from {DocumentacaoCabotagem} doc ")
                .append(" join fetch doc.{agenciamento} agm ")
                .append(" join fetch agm.{agencia} ")
                .append(" left join fetch doc.{porto} por ")
                .append(" left join fetch por.{pais} pais ")
                .append(" left join fetch doc.{agenciaDocumento} ")
                .append(" left join fetch doc.{documentacaoProdutos} dopr ")
                .append(" left join fetch dopr.{operacao} ope ")
                .append(" left join fetch ope.{produto} ")
                .append(" where agm = :agenciamento ")
                .append(" and doc.{tipo} = :tipo ");
        
        map.put("agenciamento", agenciamento);
        map.put("tipo", tipo);
        
        String hql = texto.toString();

        hql = hql.replace("{DocumentacaoCabotagem}", DocumentacaoCabotagem.class.getSimpleName())
                 .replace("{agenciamento}", DocumentacaoCabotagem.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{porto}", DocumentacaoCabotagem.PROP_PORTO)
                 .replace("{documentacaoProdutos}", DocumentacaoCabotagem.PROP_DOCUMENTACAO_PRODUTOS)
                 .replace("{operacao}", DocumentacaoOperacao.PROP_OPERACAO)
                 .replace("{produto}", Operacao.PROP_PRODUTO)
                 .replace("{tipo}", DocumentacaoCabotagem.PROP_TIPO_OPERACAO)
                 .replace("{agenciaDocumento}", DocumentacaoCabotagem.PROP_AGENCIA)
                 .replace("{pais}", Porto.PROP_PAIS)
                ;
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
