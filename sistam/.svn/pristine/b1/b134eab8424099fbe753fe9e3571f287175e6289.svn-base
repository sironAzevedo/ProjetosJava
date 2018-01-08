package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "EMBARCACAO_CERTIFICADO", schema = "STAM")
public class Certificado extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_NUMERO_CERTIFICADO = "numeroCertificado";
    public static final String PROP_DATA = "data";
    public static final String PROP_DATA_VALIDADE = "dataValidade";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_EMBARCACAO = "embarcacao";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_TIPO = "tipo";

    public Certificado() {
        
    }

    public Certificado(TipoCertificado tipo) {
        this();
        this.tipo = tipo;
    }
    
    
    @Id
    @Column(name = "EMCE_SQ_CERTIFICADO", nullable=false)
    @GeneratedValue(generator = "SQ_EMCE_SQ_CERTIFICADO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_EMCE_SQ_CERTIFICADO", sequenceName = "STAM.SQ_EMCE_SQ_CERTIFICADO", allocationSize = 1)
    private Long id;
    
    @Column(name = "EMCE_NR_CERTIFICADO", nullable=true)
    private Long numeroCertificado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMCE_DT_CERTIFICADO", nullable=false)
    private Date data;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMCE_DT_VALIDADE", nullable=true)
    private Date dataValidade;
    
   @Column(name = "EMCE_NM_PORTO", nullable=true)
    private String nomePorto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMBA_CD_ID", nullable=false)
    private Embarcacao embarcacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=true)
    private Agenciamento agenciamento;

 
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoCertificado")})
    @Column(name="EMCE_IN_CERTIFICADO", nullable=false)
    private TipoCertificado tipo;
    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroCertificado() {
        return numeroCertificado;
    }

    public void setNumeroCertificado(Long numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getNomePorto() {
        return nomePorto;
    }

    public void setNomePorto(String nomePorto) {
        this.nomePorto = nomePorto;
    }

    
   
    public Embarcacao getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(Embarcacao embarcacao) {
        this.embarcacao = embarcacao;
    }

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

     public TipoCertificado getTipo() {
        return tipo;
    }

    public void setTipo(TipoCertificado tipo) {
        this.tipo = tipo;
    }

}
