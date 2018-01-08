package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoPasseAcessoPoliciaFederal implements Serializable { 
    
    RECOLHIDA                   (1,"Recolhida" ),
    ISENTO                      (2,"Isento" ),
    RECOLHIDA_EM_JUIZO          (3,"Recolhida em Juízo" ),
    OUTROS                      (4,"Outros");
              
     
    private Integer id;
    private String porExtenso; 

    private TipoPasseAcessoPoliciaFederal(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso; 
    } 
      
    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }  

    public static TipoPasseAcessoPoliciaFederal from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoPasseAcessoPoliciaFederal tipo : values()) {
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
