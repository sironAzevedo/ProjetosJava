package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoArmador implements Serializable {
    
    TRASNPETRO        ("T","Transpetro"),
    PETROBRAS        ("P","Petrobras"),
    OUTROS          ("O", "Outros")
    ;

    private String id;
    private String porExtenso;

    private TipoArmador(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoArmador from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoArmador tipo : values()) {
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
