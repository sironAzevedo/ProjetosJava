package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Produto;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;

public class ProdutoBuilder {

    private Produto produto;
    
    private ProdutoBuilder(Produto produto) {
        this.produto = produto;
    }
    
    public static ProdutoBuilder novoProduto() {
        return new ProdutoBuilder(new Produto());                
    }
        
    public ProdutoBuilder comId(String id){
        Field field = ReflectionUtils.getFieldWithName(Produto.class, "id", false);
        ReflectionUtils.setFieldValue(produto, field, id);
        return this;
    }
    
    public Produto build() {
        return this.produto;
    }
    
    
}
