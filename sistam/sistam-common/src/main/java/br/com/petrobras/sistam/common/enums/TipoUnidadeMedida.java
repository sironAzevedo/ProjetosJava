package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoUnidadeMedida implements Serializable {
    
    TAMBORES_VAZIOS                            (1,"TAMBOR(ES) VAZIO(S)", "TAMBOR(ES) VAZIO(S)"),
    TAMBORES_CINTADOS_CONTENDO_TOALHAS         (2,"TAMBOR(ES) CINTADO(S) CONTENDO TOALHAS INDUSTRIAIS SUJAS DE RESÍDUOS OLEOSOS", "TB's CONTENDO TOALHAS INDUST. SUJAS DE RES. OLEOSOS"),
    TAMBORES_CINTADOS_CONTENDO_RESÍDUOS        (3,"TAMBOR(ES) CINTADO(S) CONTENDO RESÍDUOS OLEOSOS DIVERSOS", "TB's CONTENDO RESÍDUOS OLEOSOS DIVERSOS"),
    TAMBORES_CINTADOS_CONTENDO_VASILHAMES      (4,"TAMBOR(ES) CINTADO(S) CONTENDO VASILHAMES CONTAMINADOS COM RESÍDUOS (LATAS DE TINTAS, BOMBONAS E BALDES)", "TB's CONTENDO VASILHAMES CONTAMINADOS COM RES. (LATAS DE TINTAS, BOMBONAS E BALDES)"),
    METRO_CUBICO                               (5,"METRO CÚBICO", "M³");

    private Integer id;
    private String porExtenso;
    private String abreviado;

    private TipoUnidadeMedida(Integer id, String porExtenso, String abreviado) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.abreviado = abreviado;
    }
    
    public static TipoUnidadeMedida[]valueMetroCubico(){
        return new TipoUnidadeMedida[]{METRO_CUBICO};
    }
    
    public static TipoUnidadeMedida[]valueComClasseI(){
        return new TipoUnidadeMedida[]{TAMBORES_VAZIOS, TAMBORES_CINTADOS_CONTENDO_TOALHAS, TAMBORES_CINTADOS_CONTENDO_RESÍDUOS, TAMBORES_CINTADOS_CONTENDO_VASILHAMES};
    }
    
    public static List<TipoUnidadeMedida> listValues() {
        return new ArrayList<TipoUnidadeMedida>(Arrays.asList(TipoUnidadeMedida.values()));
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public String getAbreviado() {
        return abreviado;
    }

    public void setAbreviado(String abreviado) {
        this.abreviado = abreviado;
    } 
   

    public static TipoUnidadeMedida from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoUnidadeMedida tipo : values()) {
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
