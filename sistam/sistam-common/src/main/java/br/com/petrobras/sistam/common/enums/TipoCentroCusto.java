package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoCentroCusto implements Serializable {
    
    CC1420             (1420,"1420 - Tripulantes"),
    CC1410      (1410,"1410 - Outros");

    private Integer id;
    private String porExtenso;

    private TipoCentroCusto(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoCentroCusto from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoCentroCusto tipo : values()) {
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
