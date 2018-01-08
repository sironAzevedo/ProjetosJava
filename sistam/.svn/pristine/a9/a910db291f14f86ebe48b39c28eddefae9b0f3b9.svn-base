package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum UnidadeMedida implements Serializable {
    
    TONELADA          ("T","Tonelada", "t"),
    METRO_CUBICO      ("M", "Metro Cúbico", "m3");


    private String id;
    private String porExtenso;
    private String sigla;

    private UnidadeMedida(String id, String porExtenso, String sigla) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.sigla = sigla;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }
    public String getSigla() {
        return sigla;
    }

    public static UnidadeMedida from(String s) {
        if (s == null) {
            return null;
        }
        for (UnidadeMedida tipo : values()) {
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
