package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.entity.EscalaPk;
import br.com.petrobras.sistam.common.entity.Porto;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;

public class EscalaBuilder {
    private Escala escala;
    
    private EscalaBuilder(Escala escala) {
        this.escala = escala;
    }
    
    public static EscalaBuilder novaEscala() {
        return new EscalaBuilder(new Escala(new EscalaPk()));
    }
    
    public Escala build() {
        return this.escala;
    }
    
    public EscalaBuilder comPorto(Porto porto) {
        Field field = ReflectionUtils.getFieldWithName(EscalaPk.class, "porto", false);
        ReflectionUtils.setFieldValue(escala.getId(), field, porto);
        return this;
    }
}
