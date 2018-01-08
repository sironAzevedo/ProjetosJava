package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum SituacaoLivrePratica implements Serializable {
    
    VCP  ("VCP", "Agenciamento do tipo VCP"),
    PP   ("PP", "Pagamento da taxa de Livre Prática pendente"),
    OK   ("OK", "Pagamento da taxa de Livre Prática OK"),
    ANV  ("ANV", "Em Liberação na ANVISA");

    private String id;
    private String descricao;

    private SituacaoLivrePratica(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }


    public static SituacaoLivrePratica from(String s) {
        if (s == null) {
            return null;
        }
        for (SituacaoLivrePratica tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id;
    }

}
