package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoFrota implements Serializable {
    
    CLEAN           (1,"Clean", "c"),
    SPECIAL         (2,"Special", "s"),
    TANKERS          (3,"Tankers", "t"),
    SHUTTLE         (4,"Shuttle", "h"),
    G_e_E           (5,"G&E", "g"),
    NAO_APLICAVEL   (6, "Não Aplicável", "n");


    private Integer id;
    private String porExtenso;
    private String sigla;

    private TipoFrota(Integer id, String porExtenso, String sigla) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.sigla = sigla;
    }
    
    public static TipoFrota[] valuesCleanESpecial() {
        return new TipoFrota[]{CLEAN, SPECIAL};
    }

    public static TipoFrota[] valuesCleanSpecialETanker() {
        return new TipoFrota[]{CLEAN, SPECIAL,TANKERS};
    }
    
    public static List<TipoFrota> listValues() {
        return new ArrayList<TipoFrota>(Arrays.asList(TipoFrota.values()));
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

    public static TipoFrota from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoFrota tipo : values()) {
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
