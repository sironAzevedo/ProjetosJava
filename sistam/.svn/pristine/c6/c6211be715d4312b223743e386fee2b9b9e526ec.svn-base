package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PONTO_OPERACIONAL_SIGO", schema = "STAM")
public class PontoOperacional extends AbstractIdBean<String> implements Serializable {
    private static final long serialVersionUID = 1L;
     
    public static final String PROP_ID = "id";
    public static final String PROP_NOME = "nome";
    public static final String PROP_PORTO = "porto";
    
    public PontoOperacional() {
        
    }
    
    @Id
    @Column(name = "POOP_CD_ID", nullable=false, insertable=false, updatable=false)
    private String id;
    
    @Column(name = "POOP_NM_COMPLETO", nullable=true, insertable=false, updatable=false)
    private String nome;
    
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PORT_CD_ID", nullable=false)
    private Porto porto;

    
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
       
    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
}
