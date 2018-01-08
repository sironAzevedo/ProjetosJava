package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAIS", schema = "STAM")
public class Pais extends AbstractIdBean<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String CODIGO_BRASIL = "019";
     
    public static final String PROP_ID = "id";
    public static final String PROP_NOME_COMPLETO = "nomeCompleto";
    public static final String PROP_SIGLA = "sigla";
    
    
    public Pais() {
    }
    
    @Id
    @Column(name = "PAIS_CD_IBGE", nullable=false, insertable=false, updatable=false)
    private String id;
    
    @Column(name = "PAIS_NM_COMPLETO", nullable=true, insertable=false, updatable=false)
    private String nomeCompleto;

    @Column(name = "PAIS_TX_ISO_ALPHA3", nullable=true, insertable=false, updatable=false)
    private String sigla;
    
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getSigla() {
        return sigla;
    }
   
    @Override
    public String toString() {
        return nomeCompleto;
    }
    
}
