package br.com.petrobras.sistam.common.enums;

import br.com.petrobras.sistam.common.enums.serializer.EnumSistamFinalidadeManobraSerializer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using = EnumSistamFinalidadeManobraSerializer.class)
public enum FinalidadeManobra implements EnumSistamFinalidadeManobra, Serializable {

    FUNDEIO_ENTRADA(1, "FUNDEIO DE ENTRADA", "Manobra Fundeio de Entrada", StatusEmbarcacao.FUNDEADO_NA_ENTRADA),
    ATRACACAO(2, "ATRACAÇÃO", "Manobra Atracação", StatusEmbarcacao.ATRACADO),
    DESATRACACAO_FUNDEIO(3, "DESATRACAÇÃO PARA FUNDEIO", "Manobra Desatracação para Fundeio", StatusEmbarcacao.FUNDEADO),
    DESATRACACAO_SAIDA(4, "DESATRACAÇÃO PARA SAÍDA", "Manobra Desatracação para Saída", StatusEmbarcacao.SAIDO),
    TROCA_PIER(5, "TROCA DE PÍER", "Manobra Troca de Píer", StatusEmbarcacao.ATRACADO),
    SAIDA_FUNDEIO(6, "SAÍDA DE FUNDEIO", "Manobra Saída de Fundeio", StatusEmbarcacao.SAIDO),
    FUNDEIO_SAIDA(7, "FUNDEIO DE SAÍDA", "Manobra Fundeio de Saída", StatusEmbarcacao.FUNDEADO_NA_SAIDA),
    FUNDEIO_REATRACACAO(8, "FUNDEIO PARA REATRACAÇÃO", "Manobra Fundeio para Reatracação", StatusEmbarcacao.FUNDEADO_PARA_REATRACACAO),
    SHIP_TO_SHIP(9, "SHIP TO SHIP", "Manobra Ship To Ship",null),
    TROCA_FUNDEADOURO(10, "TROCA DE FUNDEADOURO", "Manobra Troca de Fundeadouro", StatusEmbarcacao.FUNDEADO),
    TROCA_PONTO_OPERACIONAL(11, "TROCA DE PONTO OPERACIONAL", "Manobra Troca de Ponto Operacional", StatusEmbarcacao.ATRACADO),
    TROCA_PORTO(12, "TROCA DE PORTO", "Manobra Troca de Porto", StatusEmbarcacao.SAIDO),
    NAVEGACAO(13, "NAVEGACAO", "Manobra Navegação", null);
    
    private Integer id;
    private String porExtenso;
    private String porEntensoMinuscolo;
    private StatusEmbarcacao statusEmbarcacao;

    private FinalidadeManobra(Integer id, String porExtenso, String porEntensoMinuscolo, StatusEmbarcacao statusEmbarcacao) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.porEntensoMinuscolo = porEntensoMinuscolo;
        this.statusEmbarcacao = statusEmbarcacao;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public StatusEmbarcacao statusEmbarcacao() {
        return statusEmbarcacao;
    }

    public String getPorEntensoMinuscolo() {
        return porEntensoMinuscolo;
    }
    
    public static FinalidadeManobra from(Integer s) {
        if (s == null) {
            return null;
        }
        for (FinalidadeManobra tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<FinalidadeManobra> listValues() {
        return new ArrayList<FinalidadeManobra>(Arrays.asList(FinalidadeManobra.values()));
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
