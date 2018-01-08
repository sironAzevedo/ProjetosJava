package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTO", schema = "CRAB")
public class Produto extends AbstractIdBean<String> implements Serializable {
    private static final long serialVersionUID = 1L;
     
    public static final String PROP_ID = "id";
    public static final String PROP_NOME_COMPLETO = "nomeCompleto";
    
    
    public Produto() {
    }
    
    @Id
    @Column(name = "PROD_CD_ID", nullable=false, insertable=false, updatable=false)
    private String id;
    
    @Column(name = "PROD_NM_COMPLETO", nullable=true, insertable=false, updatable=false)
    private String nomeCompleto;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
    }

    public String getNomeCompleto() {
        return nomeCompleto.trim();
    }
   
    @Override
    public String toString() {
        return nomeCompleto.trim();
    }
    
}
