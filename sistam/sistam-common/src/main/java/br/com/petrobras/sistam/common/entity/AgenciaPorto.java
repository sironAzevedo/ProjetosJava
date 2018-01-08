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
@Table(name = "AGENCIA_PORTO", schema = "STAM")
public class AgenciaPorto extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIA = "agencia";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_MUNICIPIO = "municipio";

    public AgenciaPorto() {
        
    }
    
    @Id
    @Column(name = "AGPO_SQ_AGENCIA_PORTO", nullable=false)
    @GeneratedValue(generator = "SQ_AGPO_SQ_AGENCIA_PORTO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGPO_SQ_AGENCIA_PORTO", sequenceName = "STAM.SQ_AGPO_SQ_AGENCIA_PORTO", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEN_SQ_AGENCIA", nullable=false)
    private Agencia agencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PORT_CD_ID", nullable=false)
    private Porto porto;
    
    
    @Column(name = "AGPO_TX_MUNICIPIO", nullable = false)
    private String municipio;
    
    
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

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    public String getEstado(){
        if (municipio == null){
            return null;
        }
        
        String[] municipioEstado = municipio.split("/");
        if (municipioEstado.length <= 1){
            return null;
        }
        
        return municipioEstado[1];
    }
    
    public String getApenasMunicipio(){
        if (municipio == null){
            return null;
        }
        
        String[] municipioEstado = municipio.split("/");
        
        if (municipioEstado.length == 0){
            return null;
        }
        return municipioEstado[0];
    }
    
    
}
