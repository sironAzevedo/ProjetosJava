package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Pais;
import static br.com.petrobras.sistam.test.builder.PaisBuilder.novoPais;
import br.com.petrobras.sistam.common.entity.Porto;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;

public class PortoBuilder {

    private Porto porto;
    
    private PortoBuilder(Porto porto) {
        this.porto = porto;
    }
    
    public static PortoBuilder novoPorto() {
        return new PortoBuilder(new Porto());
    }
    
    public Porto build() {
        return this.porto;
    }
    
    public PortoBuilder comId(String id) {
        Field field = ReflectionUtils.getFieldWithName(Porto.class, "id", false);
        ReflectionUtils.setFieldValue(porto, field, id);
        return this;
    }
    
    public PortoBuilder comPais(String codPais  ) {
        Pais pais = novoPais().comId(codPais).build() ;
        Field field = ReflectionUtils.getFieldWithName(Porto.class, "pais", false);
        ReflectionUtils.setFieldValue(porto, field, pais);
        return this;
    }
    
    public PortoBuilder comNomeCompleto(String nomeCompleto) {
        Field field = ReflectionUtils.getFieldWithName(Porto.class, "nomeCompleto", false);
        ReflectionUtils.setFieldValue(porto, field, nomeCompleto);
        return this;
    }
    

}
