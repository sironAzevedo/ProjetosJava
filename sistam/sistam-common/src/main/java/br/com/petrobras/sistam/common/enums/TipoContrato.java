package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoContrato implements Serializable {
    
    TCP        ("TCP","TCP"),
    VCP        ("VCP","VCP");

    private String id;
    private String porExtenso;

    private TipoContrato(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoContrato from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoContrato tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }
    
    public static List<TipoContrato> listValues() {
        return new ArrayList<TipoContrato>(Arrays.asList(TipoContrato.values()));
    }

    @Override
    public String toString() {
        return porExtenso;
    }

}
