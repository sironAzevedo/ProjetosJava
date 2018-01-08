package br.com.petrobras.sistam.common.enums;

import br.com.petrobras.sistam.common.enums.serializer.EnumSistamIdStringSerializer;
import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using=EnumSistamIdStringSerializer.class)
public enum Autoridade  implements EnumSistamIdString, Serializable {
    
    ANVISA             ("AN", "Anvisa"),
    CAPITANIA_PORTOS   ("CP", "Capitania dos Portos"),
    POLICIA_FEDERAL    ("PF", "Polícia Federal"),
    RECEITA_FEDERAL    ("RF", "Receita Federal")
    ;

    private String id;
    private String porExtenso;

    private Autoridade(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPorExtenso() {
        return porExtenso;
    }

    public static Autoridade from(String s) {
        if (s == null) {
            return null;
        }
        for (Autoridade tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return porExtenso;
    }

}
