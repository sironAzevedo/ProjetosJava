package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaDocumentacaoLongoCursoPorOperacao extends BusinessQuery<DocumentacaoLongoCurso> {

    public ConsultaDocumentacaoLongoCursoPorOperacao(Operacao operacao) {

        Map<String, Object> map = new HashMap<String, Object>();
                 
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select doc from {DocumentacaoLongoCurso} doc ")
                .append(" join fetch doc.porto por ")
                .append(" join fetch por.pais ")
                .append(" join fetch doc.operacao oper ")
                .append(" join fetch oper.agenciamento ag ")
                .append(" join fetch ag.agencia ")
                .append(" join fetch oper.produto pro ")
                .append(" where oper = :operacao ");
        
        map.put("operacao", operacao);
        
        String hql = texto.toString();

        hql = hql.replace("{DocumentacaoLongoCurso}", DocumentacaoLongoCurso.class.getSimpleName())
                ;
        this.setText(hql);
        setParameters(map);
        
    }
    
}
