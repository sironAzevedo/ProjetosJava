package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Table(name = "VW_PRNA_NAVIO", schema = "STAM")
public class Programacao extends AbstractIdBean<ProgramacaoPk> implements Serializable {
    private static final long serialVersionUID = 1L;
     
    public static final String PROP_ID = "id";
    public static final String PROP_VGM_SAIDA = "vgmSaida";
    public static final String PROP_ETA = "eta";
    public static final String PROP_PONTO_OPERACIONAL = "pontoOperacional";
    public static final String PROP_ESCALA = "escala";
    
    
    public Programacao(ProgramacaoPk id) {
        this.id = id;
    }
    
    @EmbeddedId
    protected ProgramacaoPk id;
    
    @Column(name = "VIAG_CD_ID_SAIDA", nullable=true, insertable=false, updatable=false)
    private String vgmSaida;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ESPT_DT_ETA", nullable=true, insertable=false, updatable=false)
    private Date eta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POOP_CD_ID", nullable=false, insertable=false, updatable=false)
    private PontoOperacional pontoOperacional;
    
    @Column(name = "ESPT_CD_ID", nullable=true, insertable=false, updatable=false)
    private Long escala;
    
    @Override
    public ProgramacaoPk getId() {
        return id;
    }

    @Override
    public void setId(ProgramacaoPk id) {
    }

    public String getVgmSaida() {
        return vgmSaida;
    }

    public PontoOperacional getPontoOperacional() {
        return pontoOperacional;
    }

    public Long getEscala() {
        return escala;
    }
}
