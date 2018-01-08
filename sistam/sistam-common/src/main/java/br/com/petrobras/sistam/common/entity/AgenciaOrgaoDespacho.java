package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
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
@Table(name = "AGENCIA_ORGAO", schema = "STAM")
public class AgenciaOrgaoDespacho extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIA = "agencia";
    public static final String PROP_NOME = "nome";

    public AgenciaOrgaoDespacho() {
        
    }
    
    @Id
    @Column(name = "AGOR_SQ_AGENCIA_ORGAO", nullable=false)
    @GeneratedValue(generator = "SQ_AGOR_SQ_AGENCIA_ORGAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGOR_SQ_AGENCIA_ORGAO", sequenceName = "STAM.SQ_AGOR_SQ_AGENCIA_ORGAO", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEN_SQ_AGENCIA", nullable=false)
    private Agencia agencia;
    
    @Column(name = "AGOR_NM_ORGAO", nullable = false)
    private String nome;
    
 
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

 
    
    @Override
    public String toString() {
        return nome;
    }
    
}
