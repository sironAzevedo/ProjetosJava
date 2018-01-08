package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoControleFiscalizacaoUnicoEntradaSaida implements Serializable {
    
   ENTRADA                                             (1,"ENTRADA"),
   SAIDA                                               (2,"SAÍDA");
              
     
    private Integer id;
    private String porExtenso; 

    private TipoControleFiscalizacaoUnicoEntradaSaida(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso; 
    }
    

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }  

    public static TipoControleFiscalizacaoUnicoEntradaSaida from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoControleFiscalizacaoUnicoEntradaSaida tipo : values()) {
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

    
//    public static List<TipoUnidadeMedida> obter() {
//        List<TipoUnidadeMedida> lista = new ArrayList<TipoUnidadeMedida>();
//        for (TipoUnidadeMedida tipo : values()) {
//            lista.add(tipo);
//        }
//        return lista;
//    }
        
    
}
