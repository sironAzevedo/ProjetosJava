package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.SituacaoAFRM;
import br.com.petrobras.sistam.common.enums.SituacaoCarga;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
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
@Table(name = "DOCUMENTACAO_OPERACAO", schema = "STAM")
public class DocumentacaoOperacao extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_DOCUMENTACAO_OPERACAO = "documentacaoOperacao";
    public static final String PROP_OPERACAO = "operacao";
    public static final String PROP_QUANTIDADE_KG = "quantidadeKg";
    public static final String PROP_QUANTIDADE_LT = "quantidadeLt";
    public static final String PROP_FRETE = "frete";
    public static final String PROP_CONHECIMENTO_ELETRONICO = "conhecimentoEletronico";
    public static final String PROP_CTAC = "ctac";
    public static final String PROP_NOTA_FISCAL_RECEBIDA = "notaFiscalRecebida";
    public static final String PROP_SITUACAO_AFRM = "situacaoAfrm";
    public static final String PROP_CTAC_RECEBIDA = "ctacRecebida";
    public static final String PROP_SITUACAO_CARGA = "situacaoCarga";
    public static final String PROP_ANEXO_UNICO_RECEBIDO = "anexoUnicoRecebido";
    public static final String PROP_PROTOCOLO_DMM = "protocoloDmm";
    public static final String PROP_DATA_PROTOCOLO_DMM = "dataProtocoloDmm";
    
    @Id
    @Column(name = "DOOP_SQ_DOC_OPERACAO", nullable=false)
    @GeneratedValue(generator = "SQ_DOOP_SQ_DOC_OPERACAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DOOP_SQ_DOC_OPERACAO", sequenceName = "STAM.SQ_DOOP_SQ_DOC_OPERACAO", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DOCA_SQ_DOC_CABOTAGEM", nullable=false)
    private DocumentacaoCabotagem documentacaoOperacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OPER_SQ_OPERACAO", nullable=false)
    private Operacao operacao;

    @Column(name = "DOOP_MD_PRODUTO_KG", nullable=true)
    private Double quantidadeKg;
    
    @Column(name = "DOOP_MD_PRODUTO_LT", nullable=true)
    private Double quantidadeLt;
    
    @Column(name = "DOOP_VL_FRETE", nullable=true)
    private Double frete;
    
    @Column(name = "DOOP_TX_CONHECIMENTO_ELETRONIC", nullable=false)
    private String conhecimentoEletronico;
    
    @Column(name = "DOOP_TX_TERMINAL_DESCARGA", nullable=true)
    private String terminal;
    
    @Column(name = "DOOP_TX_NR_CTAC", nullable=true)
    private String ctac;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "DOOP_IN_NF_RECEBIDA", nullable=true) 
    private Boolean notaFiscalRecebida = false;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.SituacaoAFRM")})
    @Column(name="DOOP_IN_SITUACAO_AFRM", nullable=true)
    private SituacaoAFRM situacaoAfrm;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "DOOP_IN_CTAC_RECEBIDA", nullable=true) 
    private Boolean ctacRecebida = false;

    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.SituacaoCarga")})
    @Column(name="DOOP_IN_SIUTACAO_CARGA", nullable=true)
    private SituacaoCarga situacaoCarga;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "DOOP_IN_ANEXO_UNICO_RECEBIDO", nullable=true) 
    private Boolean anexoUnicoRecebido = false;
    
    @Column(name = "DOOP_TX_PROTOCOLO_DMM", nullable=true)
    private String protocoloDmm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DOOP_DT_PROTOCOLO_DMM", nullable=true)
    private Date dataProtocoloDmm;
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    }

    public DocumentacaoCabotagem getDocumentacaoOperacao() {
        return documentacaoOperacao;
    }

    public void setDocumentacaoOperacao(DocumentacaoCabotagem documentacaoOperacao) {
        this.documentacaoOperacao = documentacaoOperacao;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    
    public Double getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(Double quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }
    
    public String getQuantidadeKgFormatada() {
        if (quantidadeKg == null) {
            return null;
        }
        return String.format("%,.3f", quantidadeKg);
    }
    
    public String getQuantidadeLtFormatada() {
        if (quantidadeLt == null) {
            return null;
        }
        return String.format("%,.3f", quantidadeLt);
    }

    public Double getQuantidadeLt() {
        return quantidadeLt;
    }

    public void setQuantidadeLt(Double quantidadeLt) {
        this.quantidadeLt = quantidadeLt;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public String getConhecimentoEletronico() {
        return conhecimentoEletronico;
    }

    public void setConhecimentoEletronico(String conhecimentoEletronico) {
        this.conhecimentoEletronico = conhecimentoEletronico;
    }

    public String getCtac() {
        return ctac;
    }

    public void setCtac(String ctac) {
        this.ctac = ctac;
    }

    public boolean getNotaFiscalRecebida() {
        return notaFiscalRecebida != null ? notaFiscalRecebida : false;
    }
    
    public void setNotaFiscalRecebida(boolean notaFiscalRecebida) {
        this.notaFiscalRecebida = notaFiscalRecebida;
    }

    public SituacaoAFRM getSituacaoAfrm() {
        return situacaoAfrm;
    }

    public void setSituacaoAfrm(SituacaoAFRM situacaoAfrm) {
        this.situacaoAfrm = situacaoAfrm;
    }

    public boolean getCtacRecebida() {
        return ctacRecebida != null ? ctacRecebida : false;
    }

    public void setCtacRecebida(boolean ctacRecebida) {
        this.ctacRecebida = ctacRecebida;
    }

    public SituacaoCarga getSituacaoCarga() {
        return situacaoCarga;
    }

    public void setSituacaoCarga(SituacaoCarga situacaoCarga) {
        this.situacaoCarga = situacaoCarga;
    }

    public boolean isAnexoUnicoRecebido() {
        return anexoUnicoRecebido != null ? anexoUnicoRecebido : false;
    }
    
    public String getAnexoUnicoRecebidoPorExtenso(){
        return isAnexoUnicoRecebido() ? "Recebido" : "";
    }

    public void setAnexoUnicoRecebido(boolean anexoUnicoRecebido) {
        this.anexoUnicoRecebido = anexoUnicoRecebido;
    }

    public String getProtocoloDmm() {
        return protocoloDmm;
    }

    public void setProtocoloDmm(String protocoloDmm) {
        this.protocoloDmm = protocoloDmm;
    }

    public Date getDataProtocoloDmm() {
        return dataProtocoloDmm;
    }

    public void setDataProtocoloDmm(Date dataProtocoloDmm) {
        this.dataProtocoloDmm = dataProtocoloDmm;
    }
    
    @Override
    public DocumentacaoOperacao clone(){
        DocumentacaoOperacao clone = (DocumentacaoOperacao)super.clone();
        return clone;
    }
        
}