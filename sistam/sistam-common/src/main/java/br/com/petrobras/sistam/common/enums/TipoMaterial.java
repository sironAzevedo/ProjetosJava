package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public enum TipoMaterial implements Serializable {

    RANCHO("R", "Rancho"),
    MATERIAIS_DIVERSOS_DTAS("M", "Materias diversos/DTA'S"),
    LUBRIFICANTES("L", "Lubrificantes");
    
    private String id;
    private String porExtenso;

    private TipoMaterial(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoMaterial from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoMaterial tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<TipoMaterial> listValues() {
        return new ArrayList<TipoMaterial>(Arrays.asList(TipoMaterial.values()));
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
