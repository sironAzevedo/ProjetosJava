package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum SituacaoRetificacao implements Serializable {
    
    EMITIDA   (1,"Emitida"),
    ENVIADA   (2,"Enviada");

    private Integer id;
    private String porExtenso;

    private SituacaoRetificacao(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static SituacaoRetificacao from(Integer s) {
        if (s == null) {
            return null;
        }
        for (SituacaoRetificacao tipo : values()) {
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
