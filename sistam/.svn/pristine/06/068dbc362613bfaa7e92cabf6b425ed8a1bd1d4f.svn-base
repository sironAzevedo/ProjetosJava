package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import java.lang.reflect.Field;
import java.util.Date;
import org.unitils.util.ReflectionUtils;

public class DocumentoBuilder {

    private Documento documento;
    
    private DocumentoBuilder(Documento documento) {
        this.documento = documento;
    }
    
    public static DocumentoBuilder novoDocumento() {
        return new DocumentoBuilder(new Documento());
    }
    
    public static DocumentoBuilder novoDocumento(Documento documento) {
        return new DocumentoBuilder(documento);
    }
    
    public Documento build() {
        return this.documento;
    }
    
    public DocumentoBuilder comId(Long id){
        Field field = ReflectionUtils.getFieldWithName(Documento.class, "id", false);
        ReflectionUtils.setFieldValue(documento, field, id);
        return this;
    }
    
    public DocumentoBuilder comTipo(TipoDocumento tipo){
        documento.setTipoDocumento(tipo);
        return this;
    }
    
    public DocumentoBuilder comAgenciamento(Agenciamento agenciamento){
        documento.setAgenciamento(agenciamento);
        return this;
    }
    
    public DocumentoBuilder comDataProtocolo(Date dataProtocolo){
        documento.setDataProtocolo(dataProtocolo);
        return this;
    }
    
    public DocumentoBuilder comNumeroDocumento(String numeroDocumento){
        documento.setNumeroDocumento(numeroDocumento);
        return this;
    }
    
    public DocumentoBuilder comDataDeEmissao(Date dataDeEmissao){
        documento.setDataEmissao(dataDeEmissao);
        return this;
    }
    
    public DocumentoBuilder comChaveEmitente(String chaveEmitente){
        documento.setChaveEmitente(chaveEmitente);
        return this;
    }
    
    public DocumentoBuilder comNomeEmitente(String nomeEmitente){
        documento.setNomeEmitente(nomeEmitente);
        return this;
    }
    
    public DocumentoBuilder comRepresentante(RepresentanteLegal representante){
        documento.setRepresentante(representante);
        return this;
    }
    
}
