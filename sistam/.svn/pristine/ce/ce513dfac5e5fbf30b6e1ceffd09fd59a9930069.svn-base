package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoEnvioSolicitacao implements Serializable {
    
    EMAIL   ("E", "Email"),
    FAX     ("F", "Fax");

    private String id;
    private String porExtenso;

    private TipoEnvioSolicitacao(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoEnvioSolicitacao from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoEnvioSolicitacao tipo : values()) {
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
