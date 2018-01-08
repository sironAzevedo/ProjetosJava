package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StatusEmbarcacao implements Serializable {

    NO_PORTO(1, "No Porto", false, 2),
    FUNDEADO_NA_ENTRADA(2, "Fundeado na Entrada", true, 3),
    ATRACADO(3, "Atracado", true, 4),
    FUNDEADO_NA_SAIDA(4, "Fundeado na Saída", true, 5),
    SAIDO(5, "Saído", false, 7),
    ESPERADO(6, "Esperado", false, 1),
    FUNDEADO_PARA_REATRACACAO(7, "Fundeado para Reatracação", false, 8),
    CANCELADO(8, "Cancelado", true, 10),
    FUNDEADO(9, "Fundeado", true, 6),
    DESVIADO(10, "Desviado", true, 9);
    private Integer id;
    private String porExtenso;
    private boolean visivel;
    private Integer ordem;

    private StatusEmbarcacao(Integer id, String porExtenso, boolean visivel, Integer ordem) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.visivel = visivel;
        this.ordem = ordem;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public static StatusEmbarcacao from(Integer s) {
        if (s == null) {
            return null;
        }
        for (StatusEmbarcacao tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<StatusEmbarcacao> listValues() {
        return new ArrayList<StatusEmbarcacao>(Arrays.asList(StatusEmbarcacao.values()));
    }

    public static List<StatusEmbarcacao> listValuesExcetoCancelado() {
        return Arrays.asList(new StatusEmbarcacao[]{
            NO_PORTO, FUNDEADO_NA_ENTRADA, ATRACADO, FUNDEADO_NA_SAIDA, SAIDO, ESPERADO, FUNDEADO_PARA_REATRACACAO, FUNDEADO, DESVIADO
        });
    }

    public static List<StatusEmbarcacao> listValuesCondicoesNavio() {
        return Arrays.asList(new StatusEmbarcacao[]{
            ATRACADO, FUNDEADO
        });
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
