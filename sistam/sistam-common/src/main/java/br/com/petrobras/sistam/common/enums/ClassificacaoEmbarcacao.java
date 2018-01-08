package br.com.petrobras.sistam.common.enums;

import br.com.petrobras.sistam.common.enums.serializer.EnumSistamIdIntSerializer;
import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using=EnumSistamIdIntSerializer.class)
public enum ClassificacaoEmbarcacao  implements EnumSistamIdInt, Serializable{
    
    GAS         (1,"GASEIRO"),
    GRANELEIRO  (2,"GRANELEIRO"),
    TUNKERS     (3,"TANQUE");

    private Integer id;
    private String porExtenso;

    private ClassificacaoEmbarcacao(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static ClassificacaoEmbarcacao from(Integer s) {
        if (s == null) {
            return null;
        }
        for (ClassificacaoEmbarcacao tipo : values()) {
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
