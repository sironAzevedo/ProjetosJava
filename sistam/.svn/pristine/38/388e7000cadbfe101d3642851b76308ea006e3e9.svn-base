package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoRequerimentoPasseSaidaPoliciaFederal implements Serializable { 
    
    EMISSAO          (1,"Emissão", "emissão" ),
    REEMISSAO        (2,"Reemissão", "reemissão" ),
    CANCELAMENTO     (3,"Cancelamento", "cancelamento" ); 
     
    private Integer id;
    private String nome; 
    private String porExtenso; 

    private TipoRequerimentoPasseSaidaPoliciaFederal(Integer id, String nome, String porExtenso) {
        this.id = id;
        this.nome = nome;
        this.porExtenso = porExtenso; 
    } 
      
    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }  

    public String getNome() {
        return nome;
    } 

    public static TipoRequerimentoPasseSaidaPoliciaFederal from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoRequerimentoPasseSaidaPoliciaFederal tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return nome;
    } 
    
}
