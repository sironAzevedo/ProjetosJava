package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DESTINATARIO", schema = "STAM")
public class Destinatario extends AbstractIdBean<Long> {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_ID = "id";
    public static final String PROP_NOME="nome";
    public static final String PROP_SIGLA = "email";
    public static final String PROP_AGENCIA = "agencia";
    
    @Id
    @Column(name = "DEST_SQ_DESTINATARIO", nullable=false)
    @GeneratedValue(generator = "SQ_DEST_SQ_DESTINATARIO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DEST_SQ_DESTINATARIO", sequenceName = "STAM.SQ_DEST_SQ_DESTINATARIO", allocationSize = 1)
    private Long id;

    @Column(name = "DEST_NM_DESTINATARIO", nullable=false)
    private String nome;
    
    @Column(name = "DEST_TX_EMAIL", nullable=false)
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEN_SQ_AGENCIA", nullable=false)
    private Agencia agencia;
    
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Agencia getAgencia() {
        return agencia;
    }
    
    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    
    //</editor-fold>

    @Override
    public String toString() {
        return nome;
    }
}
