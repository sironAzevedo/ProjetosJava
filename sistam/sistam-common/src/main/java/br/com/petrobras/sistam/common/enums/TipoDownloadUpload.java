package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;

public enum TipoDownloadUpload implements Serializable {
    
    DOWNLOAD_XML_DUV ("DOWNLOAD_XML_DUV", "Download do XML para Porto Sem Papel")
    ;

    private String id;
    private String porExtenso;

    private TipoDownloadUpload(String id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoDownloadUpload from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoDownloadUpload tipo : values()) {
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
