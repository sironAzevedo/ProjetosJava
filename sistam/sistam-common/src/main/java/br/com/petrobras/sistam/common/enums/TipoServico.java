package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoServico implements Serializable {
    
    PRATICOS        ("P", "PRÁTICOS"),
    REBOCADORES     ("R", "REBOCADORES"),   
    LANCHAS         ("L", "LANCHAS") ,
    MOORING_MASTER  ("M", "MOORING MASTER"),
    HOSPEDAGEM      ("H", "HOSPEDAGEM"),
    TRANSPORTE      ("T", "TRANPORTE"),
    AGENCIA_VIAGEM  ("A", "AGÊNCIA DE VIAGEM"),
    SUPRIMENTO      ("S", "SUPRIMENTO");

    private String id;
    private String porExtenso;

    private TipoServico(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoServico from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoServico tipo : values()) {
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
