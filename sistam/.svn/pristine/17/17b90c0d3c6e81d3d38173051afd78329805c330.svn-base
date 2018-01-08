package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;

public class PontoOperacionalBuilder {

    private PontoOperacional pontoOperacional;
    
    private PontoOperacionalBuilder(PontoOperacional pontoOperacional) {
        this.pontoOperacional = pontoOperacional;
    }
    
    public static PontoOperacionalBuilder novoPontoOperacional() {
        return new PontoOperacionalBuilder(new PontoOperacional());                
    }
    
    public PontoOperacional build() {
        return this.pontoOperacional;
    }
    
    public PontoOperacionalBuilder doPorto(Porto porto){
        this.pontoOperacional.setPorto(porto);
        return this;
    }
    
    public PontoOperacionalBuilder comId(String id){
        Field field = ReflectionUtils.getFieldWithName(PontoOperacional.class, "id", false);
        ReflectionUtils.setFieldValue(pontoOperacional, field, id);
        return this;
    }
    
    
}
