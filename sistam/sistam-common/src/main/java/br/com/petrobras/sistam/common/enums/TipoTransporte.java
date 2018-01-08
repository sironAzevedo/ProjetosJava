package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum TipoTransporte implements Serializable {
    
    TERRESTRE        (1,"Terrestre"),
    MARITIMO         (2,"Marítimo"),
    AQUAVIARIO       (3,"Aquaviário"),
    AEREO            (4,"Aéreo"),
    FLUVIAL          (5,"Fluvial");

    private Integer id;
    private String porExtenso;

    private TipoTransporte(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoTransporte from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoTransporte tipo : values()) {
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

    
    public static List<TipoTransporte> obter() {
        List<TipoTransporte> lista = new ArrayList<TipoTransporte>();
        for (TipoTransporte tipo : values()) {
            lista.add(tipo);
        }
        return lista;
    }
        
    
}
