package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Pais;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;

public class PaisBuilder {

    private Pais pais;
    
    private PaisBuilder(Pais pais) {
        this.pais = pais;
    }
    
    public static PaisBuilder novoPais() {
        return new PaisBuilder(new Pais());
    }
    
    public Pais build() {
        return this.pais;
    }
    
    public PaisBuilder comId(String id) {
        Field field = ReflectionUtils.getFieldWithName(Pais.class, "id", false);
        ReflectionUtils.setFieldValue(pais, field, id);
        return this;
    }
    
}
