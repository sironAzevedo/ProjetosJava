package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.Porto;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;



public class DocumentacaoLongoCursoBuilder {

    private DocumentacaoLongoCurso documentacaoLongoCurso;
    
    private DocumentacaoLongoCursoBuilder(DocumentacaoLongoCurso operacaoLongoCurso) {
        this.documentacaoLongoCurso = operacaoLongoCurso;
    }
    
    public static DocumentacaoLongoCursoBuilder novaOperacaoLongoCurso() {
        return new DocumentacaoLongoCursoBuilder(new DocumentacaoLongoCurso());
    }
    
    public DocumentacaoLongoCurso build() {
        return this.documentacaoLongoCurso;
    }
    
    public DocumentacaoLongoCursoBuilder comId(Long id){
        Field field = ReflectionUtils.getFieldWithName(DocumentacaoLongoCurso.class, "id", false);
        ReflectionUtils.setFieldValue(documentacaoLongoCurso, field, id);
        return this;
    }

    public DocumentacaoLongoCursoBuilder daOperacao(Operacao operacao) {
        this.documentacaoLongoCurso.setOperacao(operacao);
        return this;
    }

    public DocumentacaoLongoCursoBuilder doPorto(Porto porto) {
        this.documentacaoLongoCurso.setPorto(porto);
        return this;
    }

    
    public DocumentacaoLongoCursoBuilder comManifestoEletronico(String manifestoEletronico) {
        this.documentacaoLongoCurso.setManifestoEletronico(manifestoEletronico);
        return this;
    }
    
    public DocumentacaoLongoCursoBuilder comConhecimentoEletronico(String conhecimentoEletronico) {
        this.documentacaoLongoCurso.adicionarCE(conhecimentoEletronico);
        return this;
    }

    public DocumentacaoLongoCursoBuilder comCidade(String cidade) {
        this.documentacaoLongoCurso.setCidade(cidade);
        return this;
    }

    public DocumentacaoLongoCursoBuilder comBlRecebido(Boolean blRecebido) {
        this.documentacaoLongoCurso.setBlRecebido(true);
        return this;
    }
    
    
    
}
