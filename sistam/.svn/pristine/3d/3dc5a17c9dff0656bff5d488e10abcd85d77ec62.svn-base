package br.com.petrobras.sistam.service.query.agenciamento.documento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaDocumentosDoAgenciamento extends BusinessQuery<Documento> {

    public ConsultaDocumentosDoAgenciamento(Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select d from {Documento} d ")                
             .append(" left join d.{agenciamentoDocumento} ag ")
             .append(" left join fetch d.{porto} ")
             .append(" left join fetch d.{manobra} man ")
             .append(" left join fetch  d.{representante} ")
             .append(" left join fetch d.{operacao} op ")
             .append(" left join fetch op.{produto} pr ")
             .append(" where ag = :agenciamento")
             .append(" or man.{agenciamentoManobra} = :agenciamento")
             ;
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Documento}", Documento.class.getSimpleName())
                 .replace("{agenciamentoDocumento}", Documento.PROP_AGENCIAMENTO)
                 .replace("{manobra}", Documento.PROP_MANOBRA)
                 .replace("{porto}", Documento.PROP_PORTO)
                 .replace("{agenciamentoManobra}", Manobra.PROP_AGENCIAMENTO)
                 .replace("{representante}", Documento.PROP_REPRESENTANTE)
                 .replace("{operacao}", Documento.PROP_OPERACAO)
                 .replace("{produto}", Operacao.PROP_PRODUTO)
                 ;
        
        this.setText(hql);
        addParameter("agenciamento", agenciamento);
        
    }
}
