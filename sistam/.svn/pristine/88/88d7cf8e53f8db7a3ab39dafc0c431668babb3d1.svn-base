package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum SituacaoAFRM implements Serializable {
    
    ISENCAO      (1, "Isenção"),
    NAO_INCIDENCIA   (2, "Não Incidência"),
    PAGAMENTO      (3, "Pagamento");

    private Integer id;
    private String porExtenso;

    private SituacaoAFRM(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static SituacaoAFRM from(Integer s) {
        if (s == null) {
            return null;
        }
        for (SituacaoAFRM tipo : values()) {
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
