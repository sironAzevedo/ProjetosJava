package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProgramacaoPk extends AbstractBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_EMBARCACAO = "embarcacao";
    public static final String PROP_VGM_ENTRADA = "vgmEntrada";
    public static final String PROP_PORTO = "porto";
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMBA_CD_ID", nullable=false, insertable=false, updatable=false)
    private Embarcacao embarcacao;
    
    @Column(name = "VIAG_CD_ID_ENTRADA", nullable = false, insertable=false, updatable=false)
    private String vgmEntrada;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMBA_CD_ID", nullable=false, insertable=false, updatable=false)
    private Porto porto;

    public ProgramacaoPk() {
    }

    public ProgramacaoPk(Embarcacao embarcacao, Porto porto, String vgmEntrada) {
        this.embarcacao = embarcacao;
        this.porto = porto;
        this.vgmEntrada = vgmEntrada;
    }

    public Embarcacao getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(Embarcacao embarcacao) {
        this.embarcacao = embarcacao;
    }

    public String getVgmEntrada() {
        return vgmEntrada;
    }

    public void setVgmEntrada(String vgmEntrada) {
        this.vgmEntrada = vgmEntrada;
    }

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgramacaoPk other = (ProgramacaoPk) obj;
        if (this.embarcacao != other.embarcacao && (this.embarcacao == null || !this.embarcacao.equals(other.embarcacao))) {
            return false;
        }
        if ((this.vgmEntrada == null) ? (other.vgmEntrada != null) : !this.vgmEntrada.equals(other.vgmEntrada)) {
            return false;
        }
        if ((this.porto == null) ? (other.porto != null) : !this.porto.equals(other.porto)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.embarcacao != null ? this.embarcacao.hashCode() : 0);
        hash = 67 * hash + (this.vgmEntrada != null ? this.vgmEntrada.hashCode() : 0);
        hash = 67 * hash + (this.porto != null ? this.porto.hashCode() : 0);
        return hash;
    }
}