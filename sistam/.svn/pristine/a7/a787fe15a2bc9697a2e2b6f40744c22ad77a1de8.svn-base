package br.com.petrobras.sistam.common.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public enum TipoMercadorias {

    ENTRADA ("E", "Entrada (embarque)", "entrada (embarque)"),
    SAIDA   ("S", "Saída (desembarque)", "saida (desembarque)");
    
    private String id;
    private String porExtenso;
    private String mercadorias;

    private TipoMercadorias(String id, String porExtenso, String mercadorias) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.mercadorias = mercadorias;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoMercadorias from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoMercadorias tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<TipoMercadorias> listValues() {
        return new ArrayList<TipoMercadorias>(Arrays.asList(TipoMercadorias.values()));
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
