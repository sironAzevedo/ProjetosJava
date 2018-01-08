package br.com.petrobras.sistam.common.enums;

import br.com.petrobras.sistam.common.enums.serializer.EnumSistamIdIntSerializer;
import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using=EnumSistamIdIntSerializer.class)
public enum AreaNavegacao  implements EnumSistamIdInt, Serializable {
    
    CABOTAGEM           (1,"CABOTAGEM"),
    LONGO_CURSO         (2,"LONGO CURSO"),
    TANSITO_ADUANEIRO   (3,"TRÂNSITO ADUANEIRO");

    private Integer id;
    private String porExtenso;

    private AreaNavegacao(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getPorExtenso() {
        return porExtenso;
    }

    public static AreaNavegacao from(Integer s) {
        if (s == null) {
            return null;
        }
        for (AreaNavegacao tipo : values()) {
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
