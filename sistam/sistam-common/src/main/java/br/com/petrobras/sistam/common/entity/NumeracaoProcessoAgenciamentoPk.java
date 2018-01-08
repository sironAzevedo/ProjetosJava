package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NumeracaoProcessoAgenciamentoPk extends AbstractBean implements Serializable {

    public static final String PROPRIEDADE_ID_AGENCIA = "idAgencia";
    public static final String PROPRIEDADE_ANO = "ano";

    @Column(name = "AGEN_SQ_AGENCIA", nullable = false)
    private Long idAgencia;
    
    @Column(name = "NUAG_NR_ANO_PROCESSO", nullable = false)
    private Integer ano;

    public NumeracaoProcessoAgenciamentoPk() {
    }

    public NumeracaoProcessoAgenciamentoPk(Long idAgencia, Integer ano) {
        this.idAgencia = idAgencia;
        this.ano = ano;
    }

    public Long getIdAgencia() {
        return idAgencia;
    }

    public Integer getAno() {
        return ano;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NumeracaoProcessoAgenciamentoPk other = (NumeracaoProcessoAgenciamentoPk) obj;
        if (this.idAgencia != other.idAgencia && (this.idAgencia == null || !this.idAgencia.equals(other.idAgencia))) {
            return false;
        }
        if ((this.ano == null) ? (other.ano != null) : !this.ano.equals(other.ano)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.idAgencia != null ? this.idAgencia.hashCode() : 0);
        hash = 67 * hash + (this.ano != null ? this.ano.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "[" + idAgencia + ", " + ano + "]";
    }

}
