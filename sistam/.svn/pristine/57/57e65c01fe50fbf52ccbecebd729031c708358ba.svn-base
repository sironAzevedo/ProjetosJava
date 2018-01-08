package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RESPONSAVEL_CUSTO", schema = "STAM")
public class ResponsavelCustoEntity extends AbstractIdBean<Long> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id"; 
    public static final String PROP_SIGLA = "sigla";
    public static final String PROP_DESCRICAO = "descricao";
    
    @Id
    @Column(name = "RECU_SQ_RESP_CUSTO", nullable=false)
    @GeneratedValue(generator = "SQ_RECU_SQ_RESP_CUSTO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_RECU_SQ_RESP_CUSTO", sequenceName = "STAM.SQ_RECU_SQ_RESP_CUSTO", allocationSize = 1)
    private Long id; 

    @Column(name = "RECU_SG_RESP_CUSTO", nullable= true)
    private String sigla;
    
    @Column(name = "RECU_TX_RESP_CUSTO", nullable= true)
    private String descricao;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
       this.id = id; 
    } 

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    } 

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
    
    @Override
    public String toString() {
        return descricao;   
    }  
}
