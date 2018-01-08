package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum StatusManobra implements Serializable {
    
    SOLICITADA             (1, "Solicitada"),
    REGISTRADA             (2, "Registrada"),
    CANCELADA              (3, "Cancelada"),
    CANCELADA_FORA_PRAZO   (4, "Cancelada fora do prazo"),
    PLANEJADA                 (5, "Planejada"),
    ENCERRADA              (6, "Encerrada"),
    PRE_REGISTRADA             (7, "Pré-Registrada");
    
    private Integer id;
    private String porExtenso;

    private StatusManobra(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static StatusManobra from(Integer s) {
        for (StatusManobra tipo : values()) {
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
