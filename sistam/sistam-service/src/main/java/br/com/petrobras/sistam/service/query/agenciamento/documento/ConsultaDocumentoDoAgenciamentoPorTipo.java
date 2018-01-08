package br.com.petrobras.sistam.service.query.agenciamento.documento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaDocumentoDoAgenciamentoPorTipo extends BusinessQuery<Documento> {

    public ConsultaDocumentoDoAgenciamentoPorTipo(TipoDocumento tipo, Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select d from {Documento} d ")                
             .append(" where d.{agenciamento} = :agenciamento")
             .append(" and d.{tipo} = :tipo ")
             ;
        
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Documento}", Documento.class.getSimpleName())
                 .replace("{tipo}", Documento.PROP_TIPO)
                 .replace("{agenciamento}", Documento.PROP_AGENCIAMENTO)
                 ;
        
        this.setText(hql);
        addParameter("agenciamento", agenciamento);
        addParameter("tipo", tipo);
        
    }

    public ConsultaDocumentoDoAgenciamentoPorTipo(TipoDocumento tipo, Manobra manobra) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select d from {Documento} d ")                
             .append(" where d.{manobra} = :manobra")
             .append(" and d.{tipo} = :tipo ")
             ;
        
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Documento}", Documento.class.getSimpleName())
                 .replace("{tipo}", Documento.PROP_TIPO)
                 .replace("{manobra}", Documento.PROP_MANOBRA)
                 ;
        
        this.setText(hql);
        addParameter("manobra", manobra);
        addParameter("tipo", tipo);
        
    }
    
    public ConsultaDocumentoDoAgenciamentoPorTipo(TipoDocumento tipo, Operacao operacao) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select d from {Documento} d ")                
             .append(" where d.{operacao} = :operacao")
             .append(" and d.{tipo} = :tipo ")
             ;
        
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Documento}", Documento.class.getSimpleName())
                 .replace("{tipo}", Documento.PROP_TIPO)
                 .replace("{operacao}", Documento.PROP_OPERACAO)
                 ;
        
        this.setText(hql);
        addParameter("operacao", operacao);
        addParameter("tipo", tipo);
        
    }
    
}
