package br.com.petrobras.sistam.common.enums;


public enum TipoAcesso {

    ACESSO("A", "Acesso", "acesso"),
    DESEMBARQUE("D", "Desembarque", "desembarque"),
    EMBARQUE("E", "Embarque", "embarque"),
    VISITA ("V", "Visita", "visita");
    
    private String id;
    private String porExtenso;
    private String relatorio;

    private TipoAcesso(String id, String porExtenso, String relatorio) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.relatorio = relatorio;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public String getRelatorio() {
        return relatorio;
    }
    
    public static TipoAcesso from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoAcesso tipo : values()) {
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
