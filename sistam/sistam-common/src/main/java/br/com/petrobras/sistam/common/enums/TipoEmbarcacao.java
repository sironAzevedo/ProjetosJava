package br.com.petrobras.sistam.common.enums;

import br.com.petrobras.sistam.common.enums.serializer.EnumSistamIdStringSerializer;
import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using=EnumSistamIdStringSerializer.class)
public enum TipoEmbarcacao implements EnumSistamIdString, Serializable {
    
    NAVIO      ("N","Navio"),
    PLATAFORMA ("P","Plataforma");

    private String id;
    private String porExtenso;

    private TipoEmbarcacao(String id, String porExtenso) {
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

    public static TipoEmbarcacao from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoEmbarcacao tipo : values()) {
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
