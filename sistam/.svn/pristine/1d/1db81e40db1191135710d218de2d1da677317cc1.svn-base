package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VW_AGENCIA_SIGO", schema = "STAM")
public class AgenciaSigo extends AbstractIdBean<Long> {
    private static final long serialVersionUID = 1L;
     
    public static final String PROP_ID = "id";
    public static final String PROP_NOME = "nome";
    public static final String PROP_EMAIL = "email";
    
    @Id
    @Column(name = "AGEN_CD_ID", nullable=false)
    private Long id;

    @Column(name = "AGEN_NM_COMPLETO", nullable=false)
    private String nome;
    
    @Column(name = "AGEN_TX_EMAIL", nullable=false)
    private String email;

    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
