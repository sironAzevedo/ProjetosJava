package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;



public class DocumentacaoProdutoBuilder {

    private DocumentacaoOperacao documentacaoProduto;
    
    private DocumentacaoProdutoBuilder(DocumentacaoOperacao documentacaoProduto) {
        this.documentacaoProduto = documentacaoProduto;
    }
    
    public static DocumentacaoProdutoBuilder novaDocumentacaoProduto() {
        return new DocumentacaoProdutoBuilder(new DocumentacaoOperacao());
    }
    
    public DocumentacaoOperacao build() {
        return this.documentacaoProduto;
    }
    
    public DocumentacaoProdutoBuilder comId(Long id){
        Field field = ReflectionUtils.getFieldWithName(DocumentacaoOperacao.class, "id", false);
        ReflectionUtils.setFieldValue(documentacaoProduto, field, id);
        return this;
    }

    public DocumentacaoProdutoBuilder daDocumentacao(DocumentacaoCabotagem documentacaoOperacao) {
        this.documentacaoProduto.setDocumentacaoOperacao(documentacaoOperacao);
        return this;
    }

    public DocumentacaoProdutoBuilder daOperacao(Operacao operacao) {
        this.documentacaoProduto.setOperacao(operacao);
        return this;
    }
    
    public DocumentacaoProdutoBuilder comConhecimentoEletronico(String conhecimentoEletronico) {
        this.documentacaoProduto.setConhecimentoEletronico(conhecimentoEletronico);
        return this;
    }
    
}
