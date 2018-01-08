package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum StatusServicoManobra implements Serializable {
    
    NOVO        (1, "Novo"),
    SOLICITADO  (2, "Solicitado"),
    CANCELADO   (3, "Cancelado");
    
    private Integer id;
    private String porExtenso;

    private StatusServicoManobra(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static StatusServicoManobra from(Integer s) {
        for (StatusServicoManobra tipo : values()) {
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
