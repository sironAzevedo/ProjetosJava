package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum TipoAtendimento implements Serializable {

    ATENDIMENTO_TRIPULACAO  (1, "Atendimento a tripulação", false),
    SUPRIMENTO_EMBARCACAO   (2, "Suprimento da Embarcação", true),
    SERVICOS_SUPLEMENTARES  (3, "Serviços suplementares", true);
    
    private Integer id;
    private String porExtenso;
    private boolean ativo;

    private TipoAtendimento(Integer id, String porExtenso, boolean ativo) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public boolean isAtivo() {
        return ativo;
    }
    
    public static List<TipoAtendimento> valuesActive() {
        return Arrays.asList(new TipoAtendimento[]{SUPRIMENTO_EMBARCACAO, SERVICOS_SUPLEMENTARES});
    }

    public static TipoAtendimento from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoAtendimento tipo : values()) {
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
