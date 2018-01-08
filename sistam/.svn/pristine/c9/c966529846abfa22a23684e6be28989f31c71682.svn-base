package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public enum TipoResiduo implements Serializable {

    CLASSE_I ("1", "CLASSE I – RESÍDUOS PERIGOSOS", "Tambores", "Sólido", "Classe I - F130", "2930"),
    CLASSE_II("2", "GRUPO D - CLASSE II B INERTES","Resíduo domiciliar ou equivalente", "Sólido", "Classe II B inertes", "N/A");  
    
    private String id;
    private String porExtenso;
    private String caracterizacao;
    private String estadoFisico;
    private String classificacao;
    private String codigoOnu;

    private TipoResiduo(String id, String porExtenso, String caracterizacao, String estadoFisico, String classificacao, String codigoOnu) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.caracterizacao = caracterizacao;
        this.estadoFisico = estadoFisico;
        this.classificacao = classificacao;
        this.codigoOnu = codigoOnu;
    }
    
     

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public String getCaracterizacao() {
        return caracterizacao;
    }

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public String getCodigoOnu() {
        return codigoOnu;
    } 

    public static TipoResiduo from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoResiduo tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<TipoResiduo> listValues() {
        return new ArrayList<TipoResiduo>(Arrays.asList(TipoResiduo.values()));
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
