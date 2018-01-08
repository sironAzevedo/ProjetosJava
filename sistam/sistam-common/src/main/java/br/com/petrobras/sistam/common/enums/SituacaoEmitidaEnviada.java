package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum SituacaoEmitidaEnviada implements Serializable {
    
    EMITIDO   ("EM","Emitido"),
    ENVIADO   ("EN","Enviado");

    private String id;
    private String porExtenso;

    private SituacaoEmitidaEnviada(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static SituacaoEmitidaEnviada from(String s) {
        if (s == null) {
            return null;
        }
        for (SituacaoEmitidaEnviada tipo : values()) {
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
