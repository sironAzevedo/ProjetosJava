package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
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
@Table(name = "AGENCIAMENTO_TAXA", schema = "STAM")
public class Taxa extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_VALOR = "valor";
    public static final String PROP_DATA_PAGAMENTO = "dataPagamento";
    public static final String PROP_NUMERO_DOCUMENTO = "numeroDocumento";
    public static final String PROP_NR_DOC_CONTABIL = "numeroDocumentoContabil";
    public static final String PROP_RESPONSAVEL_CUSTO = "responsavelCusto";
    public static final String PROP_NUMERO_PEDIDO_SAP = "numeroPedidoSap";
    public static final String PROP_NUMERO_FRS_SAP = "numeroFrsSap";
    public static final String PROP_NUMERO_NECESSIDADE_LIBERACAO_SAP = "numeroNecessidadeLiberacaoSap";
    public static final String PROP_TIPO_TAXA = "tipoTaxa";
    public static final String PROP_NOME_USUARIO_ALTERACAO = "nomeUsuarioAlteracao";
    public static final String PROP_CHAVE_ALTERACAO = "chaveUsuarioAlteracao";
    public static final String PROP_DATA_ALTERACAO = "dataAlteracao";

    public Taxa() {
        
    }
    
    @Id
    @Column(name = "AGTA_SQ_AGENCIAMENTO_TAXA", nullable=false)
    @GeneratedValue(generator = "SQ_AGTA_SQ_AGENCIAMENTO_TAXA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGTA_SQ_AGENCIAMENTO_TAXA", sequenceName = "STAM.SQ_AGTA_SQ_AGENCIAMENTO_TAXA", allocationSize = 1)
    private Long id;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Agenciamento agenciamento;
        
    @Column(name = "AGTA_VL_TAXA", nullable=false)
    private Double valor;
           
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGTA_DT_PAGAMENTO", nullable=false)
    private Date dataPagamento;
    
    @Column(name = "AGTA_NR_DOCUMENTO", nullable=true)
    private Long numeroDocumento;
    
    @Column(name = "AGTA_NR_DOC_CONTABIL", nullable=true)
    private Long numeroDocumentoContabil;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RECU_SQ_RESP_CUSTO", nullable=false)
    private ResponsavelCustoEntity responsavelCusto;
    
    @Column(name = "AGTA_NR_PEDIDO_SAP", nullable=true)
    private Long numeroPedidoSap;

    @Column(name = "AGTA_NR_FRS_SAP", nullable=true)
    private Long numeroFrsSap;
    
    @Column(name = "AGTA_NR_NL_SAP", nullable=true)
    private Long numeroNecessidadeLiberacaoSap;
    
    @Enumerated(EnumType.STRING )
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoTaxa")})
    @Column(name="AGTA_IN_TIPO_TAXA", nullable=false)
    private TipoTaxa tipoTaxa;

    @Column(name="AGTA_CD_CHAVE_ALTERACAO", nullable=false)
    private String chaveUsuarioAlteracao;
    
    @Column(name="AGTA_NM_USU_ALTERACAO", nullable=false)
    private String nomeUsuarioAlteracao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="AGTA_DT_ALTERACAO", nullable=false)
    private Date dataAlteracao;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Long getNumeroDocumentoContabil() {
        return numeroDocumentoContabil;
    }

    public void setNumeroDocumentoContabil(Long numeroDocumentoContabil) {
        this.numeroDocumentoContabil = numeroDocumentoContabil;
    }

    
    public ResponsavelCustoEntity getResponsavelCusto() {
        return responsavelCusto;
    }

     public void setResponsavelCusto(ResponsavelCustoEntity responsavelCusto) {
        this.responsavelCusto = responsavelCusto;
    }

    public Long getNumeroPedidoSap() {
        return numeroPedidoSap;
    }

    public void setNumeroPedidoSap(Long numeroPedidoSap) {
        this.numeroPedidoSap = numeroPedidoSap;
    }

    public Long getNumeroFrsSap() {
        return numeroFrsSap;
    }

    public void setNumeroFrsSap(Long numeroFrsSap) {
        this.numeroFrsSap = numeroFrsSap;
    }

    public Long getNumeroNecessidadeLiberacaoSap() {
        return numeroNecessidadeLiberacaoSap;
    }

    public void setNumeroNecessidadeLiberacaoSap(Long numeroNecessidadeLiberacaoSap) {
        this.numeroNecessidadeLiberacaoSap = numeroNecessidadeLiberacaoSap;
    }

    public TipoTaxa getTipoTaxa() {
        return tipoTaxa;
    }

    public void setTipoTaxa(TipoTaxa tipoTaxa) {
        this.tipoTaxa = tipoTaxa;
    }

    public String getChaveUsuarioAlteracao() {
        return chaveUsuarioAlteracao;
    }

    public void setChaveUsuarioAlteracao(String chaveUsuarioAlteracao) {
        this.chaveUsuarioAlteracao = chaveUsuarioAlteracao;
    }

    public String getNomeUsuarioAlteracao() {
        return nomeUsuarioAlteracao;
    }

    public void setNomeUsuarioAlteracao(String nomeUsuarioAlteracao) {
        this.nomeUsuarioAlteracao = nomeUsuarioAlteracao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    
    
    @Override
    public String toString() {
        return tipoTaxa.getPorExtenso();
    }
    
}
