package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum MotivoDesvio implements Serializable {
    
    REABASTECIMENTO                                        (1,"Reabastecimento"),
    CARREGAMENTO_DESCARREGAMENTO_POR_INTERESSES_COMERCIAIS (2,"Carregamento ou descarregamento por interesses comerciais"),
    PRESTAR_SERVICOS_MEDICOS_HOSPITALARES_A_ENFERMO        (3,"Prestar serviços médico-hospitalares a enfermo"),
    DESEMBARCAR_CORPO_DE_TRIPULANTE_OU_PASSAGEIRO          (4,"Desembarcar corpo de Tripulante ou Passageiro"),
    SOLICITACAO_DE_ABRIGO_EM_FUNCAO_DE_MAU_TEMPO           (5,"Solicitação de abrigo em funão de mau tempo"),
    EMBARCACAO_AVARIADA                                    (6,"Embarcação avariada");

    private Integer id;
    private String porExtenso;

    private MotivoDesvio(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static MotivoDesvio from(Integer s) {
        if (s == null) {
            return null;
        }
        for (MotivoDesvio tipo : values()) {
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
