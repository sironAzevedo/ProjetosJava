package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoCertificado implements Serializable {
    
    CLP             (1,"CLP"),
    CCSB_CICSB      (2,"CCSB/CICSB"),
    CNCSB_CNICSB    (3,"CNCSB/CNICSB");

    private Integer id;
    private String porExtenso;

    private TipoCertificado(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoCertificado from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoCertificado tipo : values()) {
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
