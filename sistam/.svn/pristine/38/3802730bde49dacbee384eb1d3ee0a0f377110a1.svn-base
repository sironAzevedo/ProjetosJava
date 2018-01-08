package br.com.petrobras.sistam.desktop;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import javax.swing.Icon;


public class Icones {
    
    public static final Icon iconeAgenciamentoPendencia = new javax.swing.ImageIcon(Icones.class.getResource("/icons/pendencia.gif"));
    public static final Icon iconeTipoExcecao = new javax.swing.ImageIcon(Icones.class.getResource("/icons/error.png"));
    public static final Icon iconePdf = new javax.swing.ImageIcon(Icones.class.getResource("/icons/arquivoTipoPdf.png"));
    public static final Icon iconeXls = new javax.swing.ImageIcon(Icones.class.getResource("/icons/arquivoTipoXls.png"));
    public static final Icon iconeDoc = new javax.swing.ImageIcon(Icones.class.getResource("/icons/arquivoTipoDoc.png"));
    public static final Icon iconeTxt = new javax.swing.ImageIcon(Icones.class.getResource("/icons/arquivoTipoTxt.png"));
    public static final Icon iconeWriter = new javax.swing.ImageIcon(Icones.class.getResource("/icons/arquivoTipoWriter.png"));
    public static final Icon iconeCalc = new javax.swing.ImageIcon(Icones.class.getResource("/icons/arquivoTipoCalc.png"));
    public static final Icon iconeFolderFechado = new javax.swing.ImageIcon(Icones.class.getResource("/icons/folder-close-icon.png"));
    public static final Icon iconeFolderAberto= new javax.swing.ImageIcon(Icones.class.getResource("/icons/folder-documents-icon.png"));
    
    public static Icon atribuirIconeAgenciamento(Agenciamento agenciamento){
        
        Icon iconeRetorno;
        
        if (agenciamento.getPendenciasNaoResolvidas() != null && agenciamento.getPendenciasNaoResolvidas().size() > 0) {
            iconeRetorno = iconeAgenciamentoPendencia;
        } else {
            iconeRetorno = null;
        }

        return iconeRetorno;
    }
    
    public static Icon obterIconeParaAnexo(Anexo anexo){
        if ("pdf".equals(anexo.getExtensao())){
            return iconePdf;
        }
        else if ("txt".equals(anexo.getExtensao())){
            return iconeTxt;
        }
        else if ("xls".equals(anexo.getExtensao())){
            return iconeXls;
        }
        else if ("doc".equals(anexo.getExtensao())){
            return iconeDoc;
        }
        else if ("odt".equals(anexo.getExtensao())){
            return iconeWriter;
        }
        else if ("ods".equals(anexo.getExtensao())){
            return iconeWriter;
        }
        
        return null;
    }
}
