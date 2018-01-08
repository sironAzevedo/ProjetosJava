package br.com.petrobras.sistam.common.enums;

public enum TipoCategoria {

    PASSAGEIROS("P", "Passageiros", "Passageiro(s)"),
    TRIPULANTES("T", "Tripulantes", "Tripulante(s)"),
    VISITANTES("V", "Visitantes", "Visitante(s)"),
    PRESTADOR_SERVICO("S", "Prestador de Serviço", "Prestador(es) de Serviço");
    
    private String id;
    private String porExtenso;
    private String relatorio;

    private TipoCategoria(String id, String porExtenso, String relatorio) {
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
    
    public static TipoCategoria from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoCategoria tipo : values()) {
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
