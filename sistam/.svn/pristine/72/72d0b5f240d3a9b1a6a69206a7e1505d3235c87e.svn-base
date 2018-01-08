package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NUMERACAO_AGENCIAMENTO", schema = "STAM")
public class NumeracaoProcessoAgenciamento extends AbstractIdBean<NumeracaoProcessoAgenciamentoPk> implements Serializable, NumeracaoIncrementavel {

    public static final String PROPRIEDADE_CONTRATO = "id";
    public static final String PROPRIEDADE_NUMERO = "numero";

    @EmbeddedId
    private NumeracaoProcessoAgenciamentoPk id;

    @Column(name = "NUAG_CD_AGENCIAMENTO", nullable = true)
    private Long numero;

    public NumeracaoProcessoAgenciamento() {
    }

    public NumeracaoProcessoAgenciamento(Agencia agencia, Integer ano) {
        this.id = new NumeracaoProcessoAgenciamentoPk(agencia.getId(), ano);
    }

    @Override public NumeracaoProcessoAgenciamentoPk getId() {
        return id;
    }

    @Override public void setId(NumeracaoProcessoAgenciamentoPk id) {
        throw new UnsupportedOperationException("not supported");
    }

    @Override public Long getNumero() {
        return numero;
    }

    @Override public void setNumero(Long numero) {
        this.numero = numero;
    }

}
