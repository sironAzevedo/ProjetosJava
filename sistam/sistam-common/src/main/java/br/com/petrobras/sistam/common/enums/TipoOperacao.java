package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoOperacao implements Serializable {

    CARGA_CABOTAGEM(1, "Carga Cabotagem", "CG"),
    DESCARGA_CABOTAGEM(2, "Descarga Cabotagem", "DG"),
    ABASTECIMENTO(3, "Abastecimento", "AB"),
    CARGA_EXPORTACAO(4, "Carga Exportação", "CG"),
    DESCARGA_IMPORTACAO(5, "Descarga Importação", "DG"),
    SHIPTOSHIP(6, "Ship to ship", "SS"),
    SEM_OPERACAO_COMERCIAL(7, "Sem Operação Comercial", "SO");
    
    private Integer id;
    private String porExtenso;
    private String sigla;

    private TipoOperacao(Integer id, String porExtenso, String sigla) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.sigla = sigla;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public String getSigla() {
        return sigla;
    }

    public static List<TipoOperacao> listValues() {
        return new ArrayList<TipoOperacao>(Arrays.asList(TipoOperacao.values()));
    }
    
    public static List<TipoOperacao> listValuesToSiscomex() {
        return new ArrayList<TipoOperacao>(Arrays.asList(new TipoOperacao[] {CARGA_CABOTAGEM, DESCARGA_CABOTAGEM, CARGA_EXPORTACAO, DESCARGA_IMPORTACAO, SEM_OPERACAO_COMERCIAL}));
    }

    public static TipoOperacao from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoOperacao tipo : values()) {
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
