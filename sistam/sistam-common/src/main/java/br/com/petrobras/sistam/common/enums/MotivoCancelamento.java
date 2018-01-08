package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum MotivoCancelamento implements Serializable {
    
    DESVIO                  ("D","Desvio de Rota"),
    PROGRAMACAO             ("P","Mudança Programação"),
    ABERTO_DUPLICIDADE      ("A","Aberto em Duplicidade"),
    ABERTO_INDEVIDAMENTE    ("I","Aberto Indevidamente"),
    OUTROS                  ("O", "Outros");

    private String id;
    private String porExtenso;

    private MotivoCancelamento(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static MotivoCancelamento from(String s) {
        if (s == null) {
            return null;
        }
        for (MotivoCancelamento tipo : values()) {
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
