package br.com.petrobras.sistam.common.enums;

public enum TipoNacionalidade {

    BRASILEIROS("B", "Brasileiros", "BRASILEIRA"),
    ESTRANGEIROS("E", "Estrangeiros","");
    
    private String id;
    private String porExtenso;
    private String nacionalidade;

    private TipoNacionalidade(String id, String porExtenso, String nacionalidade) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.nacionalidade = nacionalidade;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
    
    public static TipoNacionalidade from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoNacionalidade tipo : values()) {
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
