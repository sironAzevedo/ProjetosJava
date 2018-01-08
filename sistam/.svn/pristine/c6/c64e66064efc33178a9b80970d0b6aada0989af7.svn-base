package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

/**
 *
 */
public enum TipoVeiculo implements Serializable{

    AUTOMOVEL   ("A", "Automóvel"),
    PICKUP      ("P", "Pick-up"),
    FURGAO      ("F", "Furgão/Van"),
    CAMINHAO    ("C", "Caminhão");
    
    private String id;
    private String porExtenso; 

    private TipoVeiculo(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public void setPorExtenso(String porExtenso) {
        this.porExtenso = porExtenso;
    }
    
    public static TipoVeiculo from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoVeiculo tipo : values()) {
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
