package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoTaxa implements Serializable {
    
    TUF_TAXA_UTILIZACAO_FAROL_CAPITANIA                (1, "TUF - Taxa de Utilização do Farol / Capitania dos Portos", 1),
    LIVRE_PRATICA_ANVISA                               (2, "Taxa de Livre Prática (CLP) / ANVISA", 2),
    FUNAPOL_POLICIA_FEDERAL_ENTRADA                    (3, "FUNAPOL / Polícia Federal (Entrada)", 3),
    INFRA_ESTRUTURA_PORTUARIA                          (4, "Taxa de Utilização de Infraestrutura Portuária", 5),
    RETIRADA_EXIGENCIA_AJB_CAPITANIA                   (5, "Retirada de Exigências - AJB / Capitania dos Portos", 6),
    SOLICITACAO_CONTROLE_SANITARIO_ANVISA              (6, "Solicitação de Controle Sanitário de Bordo / ANVISA", 7),
    RETIRADA_EXIGENCIA_TRANSPORTE_PETROLEO_CAPITANIA   (7, "Retirada de Exigência - Transportes de Petróleo / Capitania dos Portos", 8),
    RETIRADA_EXIGENCIA_INSPECAO_FLAG_STATE_CAPITANIA   (8, "Retirada de Exigência de Inspeção Flag State / Capitania dos Portos", 9),
    TUM_TAXA_UTILIZACAO_MERCANTE                       (9, "TUM - Taxa de utilização do MERCANTE - TUM  / DMM", 10),
    AIT_CTS_CAPITANIA                                  (10, "AIT e CTS - Capitania dos Portos", 11),
    MULTAS_DIVERSAS_AUTORIDADES                        (11, "Multas / Diversas autoridades", 12),
    EMISSAO_DECLARACAO_CONFORMIDADE_CAPITANIA          (12, "Emissão da declaração de conformidade / Capitania dos -+Portos", 13),
    PERICIA_CONFORMIDADE_OPERACAO_AJB_CAPITANIA        (13, "Perícia de Conformidade para Operação - AJB / Capitania dos Portos", 14),
    PERICIA_LAUDO_EMISSAO_CTS_CAPITANIA                (14, "Perícia para Laudo Emissão de CTS / Capitania dos Portos", 15),
    ANALISE_DOCUMENTAL_SIRE_CAPITANIA                  (15, "Análise Documental SIRE para emissão Decl Conformidade / Capitania dos Portos", 16),
    PERICIA_CONFORMIDADE_TRANSPORTE_PETROLEO_CAPITANIA (16, "Perícia de Conformidade para Transportes de Petróleo / Capitania dos Portos", 17),
    AQUISICAO_ROL_EQUIPAGEM_CAPITANIA                  (17, "Taxa de Aquisição do Rol de Equipagem/Capitania dos Portos", 18),
    OUTRAS_TAXAS                                       (18, "Outras Taxas", 19),
    FUNAPOL_POLICIA_FEDERAL_SAIDA                      (19, "FUNAPOL / Polícia Federal (Saída)", 4);

    private Integer id;
    private String porExtenso;
    private Integer ordenar;

    private TipoTaxa(Integer id, String porExtenso, Integer ordenar) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.ordenar = ordenar;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public Integer getOrdenar() {
        return ordenar;
    }  

    public static TipoTaxa from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoTaxa tipo : values()) {
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
