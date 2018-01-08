package br.com.petrobras.sistam.common.enums;


public enum TipoSolicitacaoTransito{

    AUTORIZACAO("A", "Autorização", "autorizar"),
    CANCELAMENTO("C", "Cancelamento", "cancelar");
    
    private String id;
    private String porExtenso;
    private String relatorio;

    private TipoSolicitacaoTransito(String id, String porExtenso, String relatorio) {
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
    
    public static TipoSolicitacaoTransito from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoSolicitacaoTransito tipo : values()) {
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
