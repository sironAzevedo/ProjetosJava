package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum SituacaoProcesso implements Serializable {
    
    CANCELADO      (1, "Cancelado"),
    EM_ANDAMENTO   (2, "Em Andamento"),
    ENCERRADO      (3, "Encerrado");

    private Integer id;
    private String porExtenso;

    private SituacaoProcesso(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static SituacaoProcesso from(Integer s) {
        if (s == null) {
            return null;
        }
        for (SituacaoProcesso tipo : values()) {
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
