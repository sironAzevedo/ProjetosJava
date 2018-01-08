package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "EMPRESA_SERVICO_MANOBRA", schema = "STAM")
public class ServicoResponsavel extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_MANOBRA = "manobra";
    public static final String PROP_DATA_INICIO = "dataInicio";
    public static final String PROP_DATA_TERMINO = "dataTermino";
    public static final String PROP_OBSERVACAO = "observacao";
    public static final String PROP_SERVICO_MANOBRA = "servicoManobra";
    public static final String PROP_SERVICO = "servico";

    @Id
    @Column(name = "EMSM_SQ_SERVICO_MANOBRA", nullable=false)
    @GeneratedValue(generator = "SQ_EMSM_SQ_SERVICO_MANOBRA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_EMSM_SQ_SERVICO_MANOBRA", sequenceName = "STAM.SQ_EMSM_SQ_SERVICO_MANOBRA", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMAN_SQ_MANOBRA", nullable=false)
    private ServicoManobra servicoManobra;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMSM_DT_INICIO", nullable=false)
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMSM_DT_TERMINO", nullable=false)
    private Date dataTermino;

    @Column(name = "EMSM_TX_OBSERVACAO", nullable=true)
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMSE_SQ_EMPRESA_SERVICO", nullable=false)
    private Servico servico;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public ServicoManobra getServicoManobra() {
        return servicoManobra;
    }

    public void setServicoManobra(ServicoManobra servicoManobra) {
        this.servicoManobra = servicoManobra;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
        firePropertyChange("servico", null, null);
    }
    
    @Override
    public String toString() {
        return servico.toString();
    }

}
