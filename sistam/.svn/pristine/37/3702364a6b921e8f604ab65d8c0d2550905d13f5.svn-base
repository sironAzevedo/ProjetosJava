package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;



public class DocumentacaoOperacaoBuilder {

    private DocumentacaoCabotagem documentacaoOperacao;
    
    private DocumentacaoOperacaoBuilder(DocumentacaoCabotagem documentacaoOperacao) {
        this.documentacaoOperacao = documentacaoOperacao;
    }
    
    public static DocumentacaoOperacaoBuilder novaDocumentacaoOperacao() {
        return new DocumentacaoOperacaoBuilder(new DocumentacaoCabotagem());
    }
    
    public DocumentacaoCabotagem build() {
        return this.documentacaoOperacao;
    }
    
    public DocumentacaoOperacaoBuilder comId(Long id){
        Field field = ReflectionUtils.getFieldWithName(DocumentacaoCabotagem.class, "id", false);
        ReflectionUtils.setFieldValue(documentacaoOperacao, field, id);
        return this;
    }

    public DocumentacaoOperacaoBuilder doAgenciamento(Agenciamento agenciamento) {
        this.documentacaoOperacao.setAgenciamento(agenciamento);
        return this;
    }
    
    public DocumentacaoOperacaoBuilder comAgencia(AgenciaSigo agencia) {
        this.documentacaoOperacao.setAgencia(agencia);
        return this;
    }
    
    public DocumentacaoOperacaoBuilder comPorto(Porto porto) {
        this.documentacaoOperacao.setPorto(porto);
        return this;
    }
    
    public DocumentacaoOperacaoBuilder comManifestoEletronico(String manifestoEletronico) {
        this.documentacaoOperacao.setManifestoEletronico(manifestoEletronico);
        return this;
    }
    
    public DocumentacaoOperacaoBuilder comTipoOperacao(TipoOperacao tipoOperacao) {
        this.documentacaoOperacao.setTipoOperacao(tipoOperacao);
        return this;
    }
    
}
