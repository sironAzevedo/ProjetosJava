package br.com.petrobras.sistam.service.query.agenciamento.documento;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaDocumentosPorId extends BusinessQuery<Documento> {

    public ConsultaDocumentosPorId(Long id) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select d from {Documento} d ")                
             .append(" left join fetch d.{agenciamentoDocumento} agdo ")
             .append(" left join fetch agdo.{agencia} age ")
             .append(" left join fetch age.{representantes} ")
             .append(" left join fetch d.{manobra} man ")
             .append(" left join fetch man.{agenciamentoManobra} agma ")
             .append(" left join fetch agma.{agencia} ")
             .append(" left join fetch  d.{representante} ")
             .append(" left join fetch  d.{operacao} ope ")
             .append(" left join fetch  ope.{produto} pro ")
             .append(" where d.{id} = :id ");
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Documento}", Documento.class.getSimpleName())
                 .replace("{agenciamentoDocumento}", Documento.PROP_AGENCIAMENTO)
                 .replace("{manobra}", Documento.PROP_MANOBRA)
                 .replace("{agenciamentoManobra}", Manobra.PROP_AGENCIAMENTO)
                 .replace("{representante}", Documento.PROP_REPRESENTANTE)
                 .replace("{id}", Documento.PROP_ID)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{representantes}", Agencia.PROP_REPRESENTANTES)
                 .replace("{operacao}", Documento.PROP_OPERACAO)
                 .replace("{produto}", Operacao.PROP_PRODUTO)
                 ;
        
        this.setText(hql);
        addParameter("id", id);
        
    }
}
