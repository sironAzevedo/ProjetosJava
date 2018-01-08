package br.com.petrobras.sistam.service.query.agenciamento.documento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaDocumentoDoAgenciamentoPorTipoSemProtocolo extends BusinessQuery<Documento> {

    public ConsultaDocumentoDoAgenciamentoPorTipoSemProtocolo(TipoDocumento tipo, Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select d from Documento d ")                
             .append(" where d.agenciamento = :agenciamento")
             .append(" and d.tipoDocumento = :tipo ")
             .append(" and d.numeroDocumento is null ");
        
        String hql = texto.toString();     
                
        this.setText(hql);
        addParameter("agenciamento", agenciamento);
        addParameter("tipo", tipo);
    }
}
