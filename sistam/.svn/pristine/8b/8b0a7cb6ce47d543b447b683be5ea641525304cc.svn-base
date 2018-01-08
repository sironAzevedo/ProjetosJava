package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum PastaAnexo implements Serializable {
    
    RECEBIDO_NAVIO_ARMADOR_AFRETADOR            (1, "Recebidos da Embarcação/Armador/Afretador"),
    EMITIDOS_NAVIO_ARMADOR_AFRETADOR_AUTORIDADE (2, "Emitidos da Embarcação/Armador/Afretador/Autoridades"),
    RECEBIDOS_CARGA_DESCARGA                    (3, "Recebidos Carga/Descarga"),
    EMITIDOS_CARGA_DESCARGA                     (4, "Emitidos Carga/Descarga"),
    SOLICITACAO_NAVIO_TRIPULACAO                (5, "Solicitação da Embarcação/Tripulação"),
    ATENDIMENTO_SOLICITACAO_NAVIO_TRIPULACAO    (6, "Atendimento a solicitação da Embarcação/Tripulação"),
    SUPRIMENTOS                                 (7, "Suprimentos"),
    ;

    private Integer id;
    private String porExtenso;
    private Autoridade autoridade;
    private boolean automatico;

    private PastaAnexo(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public Autoridade getAutoridade() {
        return autoridade;
    }

    public boolean isAutomatico() {
        return automatico;
    }

    public static PastaAnexo from(Integer s) {
        for (PastaAnexo tipo : values()) {
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
