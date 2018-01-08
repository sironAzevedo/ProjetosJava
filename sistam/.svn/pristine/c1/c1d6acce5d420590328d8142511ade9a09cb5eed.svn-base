package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum SituacaoCarga implements Serializable {
    
    PENDENTE   ("P", "Pendente"),
    LIBERADA     ("L", "Liberada");

    private String id;
    private String porExtenso;

    private SituacaoCarga(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static SituacaoCarga from(String s) {
        if (s == null) {
            return null;
        }
        for (SituacaoCarga tipo : values()) {
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
