package br.com.petrobras.sistam.service.query.agenciamento.documento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaDocumentoPorTipoAgenciamentoCtacPortoDestino extends BusinessQuery<Documento> {

    public ConsultaDocumentoPorTipoAgenciamentoCtacPortoDestino(TipoDocumento tipo, Agenciamento agenciamento, String numeroCTAC, Porto portoDestino) {
        StringBuilder texto = new StringBuilder();
        texto.append(" select d from {Documento} d ")
                .append(" where d.{agenciamento} = :agenciamento")
                .append(" and d.{tipo} = :tipo ")
                .append(" and d.{ctac} = :ctac ")
                .append(" and d.{porto} = :porto ")
                .append(" order by d.{dataFormulario} desc, d.{id} desc");

        addParameter("agenciamento", agenciamento);
        addParameter("tipo", tipo);
        addParameter("ctac", numeroCTAC);
        addParameter("porto", portoDestino);

        String hql = texto.toString();
        hql = hql.replace("{Documento}", Documento.class.getSimpleName())
                .replace("{id}", Documento.PROP_ID)
                .replace("{dataFormulario}", Documento.PROP_DATA_FORMULARIO)
                .replace("{tipo}", Documento.PROP_TIPO)
                .replace("{agenciamento}", Documento.PROP_AGENCIAMENTO)
                .replace("{ctac}", Documento.PROP_NUMERO_CTAC)
                .replace("{porto}", Documento.PROP_PORTO);

        this.setText(hql);

    }
}
