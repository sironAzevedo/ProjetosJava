package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class EscalaPk extends AbstractBean{

    private static final long serialVersionUID = 1L;
    public static final String PROP_EMBARCACAO = "embarcacao";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_DATA = "data";

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PORT_CD_ID", nullable=false)
    private Porto porto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMBA_CD_ID", nullable=false)
    private Embarcacao embarcacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_SAIDA", nullable=false)
    private Date data;

    public Porto getPorto() {
        return porto;
    }

    public Embarcacao getEmbarcacao() {
        return embarcacao;
    }


    public Date getData() {
        return data;
    }
}
