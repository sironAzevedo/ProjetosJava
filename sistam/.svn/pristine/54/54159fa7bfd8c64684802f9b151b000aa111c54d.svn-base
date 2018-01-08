package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
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
@Table(name = "AGENCIAMENTO_DOCUMENTO", schema = "STAM")
public class Documento extends AbstractIdBean<Long> {
    
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO="agenciamento";
    public static final String PROP_TIPO = "tipoDocumento";
    public static final String PROP_CHAVE_EMITENTE = "chaveEmitente";
    public static final String PROP_NOME_EMITENTE = "nomeEmitente";
    public static final String PROP_DATA_EMISSAO = "dataEmissao";
    public static final String PROP_NUMERO_DOCUMENTO = "numeroDocumento";
    public static final String PROP_DATA_PROTOCOLO = "dataProtocolo";
    public static final String PROP_REPRESENTANTE = "representante";
    public static final String PROP_MANOBRA = "manobra";
    public static final String PROP_OPERACAO = "operacao";
    public static final String PROP_DATA_FORMULARIO = "dataFormulario";
    public static final String PROP_NUMERO_CTAC = "numeroCTAC";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_DATA_ETA = "dataEta";
    public static final String PROP_DATA_HORA_PARTE_CHEGADA = "dataHoraParteChegada";
    
    
    @Id
    @Column(name = "AGDO_SQ_AGENCIAMENTO_DOC", nullable=false)
    @GeneratedValue(generator = "SQ_AGDO_SQ_AGENCIAMENTO_DOC", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGDO_SQ_AGENCIAMENTO_DOC", sequenceName = "STAM.SQ_AGDO_SQ_AGENCIAMENTO_DOC", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=true)
    private Agenciamento agenciamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MANO_SQ_MANOBRA", nullable=true)
    private Manobra manobra;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OPER_SQ_OPERACAO", nullable=true)
    private Operacao operacao;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoDocumento")})
    @Column(name="AGDO_IN_TIPO_DOCUMENTO", nullable=false)
    private TipoDocumento tipoDocumento;
    
    @Column(name = "AGDO_CD_CHAVE_EMITENTE", nullable=false)
    private String chaveEmitente;
    
    @Column(name = "AGDO_NM_EMITENTE", nullable=false)
    private String nomeEmitente;
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGDO_DT_EMISSAO", nullable=false)
    private Date dataEmissao;
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGDO_DT_FORMULARIO")
    private Date dataFormulario;
    
    @Column(name = "AGDP_TX_NUMERO_PROTOCOLO", nullable=true)
    private String numeroDocumento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGDO_DT_PROTOCOLO", nullable=true)
    private Date dataProtocolo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGCO_SQ_CONTATO", nullable=true)
    private RepresentanteLegal representante;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORT_CD_ID")
    private Porto porto;
    
    @Column(name = "AGDO_TX_NR_CTAC")
    private String numeroCTAC;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGDO_DT_ETA")
    private Date dataEta;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGDO_DT_CHEGADA")
    private Date dataHoraParteChegada;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {}

    public Date getDataFormulario() {
        return dataFormulario;
    }

    public void setDataFormulario(Date dataFormulario) {
        this.dataFormulario = dataFormulario;
    }

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }

    public String getNumeroCTAC() {
        return numeroCTAC;
    }

    public void setNumeroCTAC(String numeroCTAC) {
        this.numeroCTAC = numeroCTAC;
    }
    
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

    public Manobra getManobra() {
        return manobra;
    }

    public void setManobra(Manobra manobra) {
        this.manobra = manobra;
    }
    
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }
    
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        firePropertyChange("tipoDocumento", null, null);
    }
    
    public String getChaveEmitente() {
        return chaveEmitente;
    }
    
    public void setChaveEmitente(String chaveEmitente) {
        this.chaveEmitente = chaveEmitente;
    }
    
    public String getNomeEmitente() {
        return nomeEmitente;
    }
    
    public void setNomeEmitente(String nomeEmitente) {
        this.nomeEmitente = nomeEmitente;
    }
    
    public Date getDataEmissao() {
        return dataEmissao;
    }
    
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getDataProtocolo() {
        return dataProtocolo;
    }

    public void setDataProtocolo(Date dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }
    
    public RepresentanteLegal getRepresentante() {
        return representante;
    }
    
    public void setRepresentante(RepresentanteLegal representante) {
        this.representante = representante;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Date getDataEta() {
        return dataEta;
    }

    public void setDataEta(Date dataEta) {
        this.dataEta = dataEta;
    }

    public Date getDataHoraParteChegada() {
        return dataHoraParteChegada;
    }

    public void setDataHoraParteChegada(Date dataHoraParteChegada) {
        this.dataHoraParteChegada = dataHoraParteChegada;
    } 
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters sintéticos">
    public String getEmitenteFormatado() {
        if (chaveEmitente != null && nomeEmitente != null){
            return "[" + chaveEmitente + "] " + nomeEmitente;
        }
        return null;
    }
    
    public String getEta(){
        if(TipoDocumento.SOLICITACAO_CERTIFICADO.equals(tipoDocumento)){            
            String eta = SistamDateUtils.formatDate(dataEta, SistamDateUtils.DATE_HOUR_PATTERN, null);
            return eta != null ? eta : "";            
        }
        return null;
    }
    
    public String getdataHoraParteEntrada(){
        if(TipoDocumento.PARTE_ENTRADA.equals(tipoDocumento)){            
            String horaParteChegada = SistamDateUtils.formatDate(dataHoraParteChegada, SistamDateUtils.DATE_HOUR_PATTERN, null);
            return horaParteChegada != null ? horaParteChegada : "";            
        }
        return null;
    }
    
    //</editor-fold>
    
    
}
