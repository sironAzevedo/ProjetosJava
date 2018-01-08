package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoSimNao implements Serializable {

    
    SIM      (true, "Sim"),
    NAO      (false, "Não");

    private Boolean id;
    private String porExtenso;

    private TipoSimNao(Boolean id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Boolean getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoSimNao from(Boolean s) {
        if (s == null) {
            return null;
        }
        for (TipoSimNao tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }
    
    public static List<TipoSimNao> listValues() {
        return new ArrayList<TipoSimNao>(Arrays.asList(TipoSimNao.values()));
    }

    @Override
    public String toString() {
        return porExtenso;
    }

}
