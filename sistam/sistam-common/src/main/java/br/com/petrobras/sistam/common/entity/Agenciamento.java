package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.AreaNavegacao;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.SituacaoLivrePratica;
import br.com.petrobras.sistam.common.enums.SituacaoProcesso;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoArmador;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "AGENCIAMENTO", schema = "STAM")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
public class Agenciamento extends AbstractIdBean<Long> implements Comparable<Agenciamento> {

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    public static final String PROP_ID = "id";
    public static final String PROP_CODIGO = "codigo";
    public static final String PROP_ANO_PROCESSO = "anoProcesso";
    public static final String PROP_AGENCIA = "agencia";
    public static final String PROP_AREA_NAVEGACAO = "areaNavegacao";
    public static final String PROP_AREA_NAVEGACAO_SAIDA = "areaNavegacaoSaida";
    public static final String PROP_EMBARCACAO = "embarcacao";
    public static final String PROP_STATUS_EMBARCACAO = "statusEmbarcacao";
    public static final String PROP_SITUACAO_PROCESSO = "situacaoProcesso";
    public static final String PROP_EXCLUIDO = "excluido";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_PORTO_ORIGEM = "portoOrigem";
    public static final String PROP_PORTO_DESTINO = "portoDestino";
    public static final String PROP_DESTINO_INTERMEDIARIO = "destinoIntermediario";
    public static final String PROP_VGM = "vgm";
    public static final String PROP_VGM_SAIDA = "vgmSaida";
    public static final String PROP_DATA_ESTIMADA_SAIDA = "dataEstimadaSaida";
    public static final String PROP_DATA_SAIDA = "dataSaida";
    public static final String PROP_ETA = "eta";
    public static final String PROP_DATA_OFICIAL_CHEGADA = "dataChegada";
    public static final String PROP_NUMERO_TRIPULANTES = "numeroTripulantes";
    public static final String PROP_NUMERO_PASSAGEIROS = "numeroPassageiros";
    public static final String PROP_CONTATO = "tipoContato";
    public static final String PROP_ARMADOR = "TipoArmador";
    public static final String PROP_AGENCIAMENTO_CARGA = "agenciamentoCarga";
    public static final String PROP_AGENCIAMENTO_PROTECAO = "agenciamentoProtecao";
    public static final String PROP_AGENCIAMENTO_PLATAFORMA = "agenciamentoPlataforma";
    public static final String PROP_AGENCIAMENTO_VIAGEM = "agenciementoViagem";
    public static final String PROP_AGENCIAMENTO_SANITARIA = "agenciementoSanitaria";
    public static final String PROP_MANOBRAS = "manobras";
    public static final String PROP_OPERACOES = "operacoes";
    public static final String PROP_CALADO_CHEGADA_RE = "caladoChegadaRe";
    public static final String PROP_CALADO_CHEGADA_VANTE = "caladoChegadaVante";
    public static final String PROP_CALADO_SAIDA_RE = "caladoSaidaRe";
    public static final String PROP_CALADO_SAIDA_VANTE = "caladoSaidaVante";
    public static final String PROP_PENDENCIAS = "pendencias";
    public static final String PROP_ETA_PROX_PORTO = "etaProximoPorto";
    public static final String PROP_ESCALAS = "escalas";
    public static final String PROP_NUMERO_ESCALA_MERCANTE = "numeroEscalaMercante";
    public static final String PROP_DESVIO = "desvio";
    public static final String PROP_TAXAS = "taxas";
    public static final String PROP_DOCUMENTOS = "documentos";
    public static final String PROP_NUMERO_PROCESSO_DESPACHO = "numeroProcessoDespacho";
    public static final String PROP_TIPO_CONTRATO = "tipoContrato"; 
    public static final String PROP_DATA_INCLUSAO = "dataInclusao";
    public static final String PROP_CHAVE_CADASTRADOR = "chaveCadastrador";
    public static final String PROP_NOME_CADASTRADOR = "nomeCadastrador";
    public static final String PROP_LATITUDE_ENTRADA = "latitudeEntrada";
    public static final String PROP_LONGITUDE_ENTRADA = "longitudeEntrada";
    public static final String PROP_ANEXOS = "anexos";
    public static final String PROP_VISITAS = "visitas";
    public static final String PROP_CANCELAMENTO = "cancelamento";
    public static final String PROP_OBSERVACOES = "observacoes";
    public static final long TAM_MAX_TOTAL_ARQUIVOS_EM_BYTES = 1024 * 1024 * 10; // Tamanho máximo de 5 MB
    public static final Integer PROP_LIMITE_CONSULTA_REGISTROS = 100;
    public static final String PROP_FROTA = "tipoFrota"; 
    public static final String PROP_ACOMPANHAMENTO = "acompanhamentos";
    
    
 //</editor-fold>      
    
    //<editor-fold defaultstate="collapsed" desc="Propriedades">
    @Id
    @Column(name = "AGEC_SQ_AGENCIAMENTO", nullable = false)
    @GeneratedValue(generator = "SQ_AGEC_SQ_AGENCIAMENTO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGEC_SQ_AGENCIAMENTO", sequenceName = "STAM.SQ_AGEC_SQ_AGENCIAMENTO", allocationSize = 1)
    private Long id;
    
    @Version
    @Column(name = "AGEC_NR_VERSAO_REGISTRO", nullable = false)
    private Long versao;
    
    @Column(name = "AGEC_CD_AGENCIAMENTO", nullable = false)
    private Long codigo;
    
    @Column(name = "AGEC_NR_ANO_PROCESSO", nullable = false)
    private Integer anoProcesso;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGEN_SQ_AGENCIA", nullable = false)
    private Agencia agencia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMBA_CD_ID", nullable = false)
    private Embarcacao embarcacao;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.StatusEmbarcacao")})
    @Column(name = "AGEC_IN_STATUS_EMBARCACAO", nullable = false)
    private StatusEmbarcacao statusEmbarcacao;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.SituacaoProcesso")})
    @Column(name = "AGEC_IN_SITUACAO_PROCESSO", nullable = false)
    private SituacaoProcesso situacaoProcesso;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoContrato")})
    @Column(name = "AGEC_IN_TIPO_CONTRATO", nullable = true)
    private TipoContrato tipoContrato;   
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoArmador")})
    @Column(name = "AGEC_IN_ARMADOR", nullable = false)
    private TipoArmador tipoArmador;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoFrota")})
    @Column(name = "AGEC_IN_FROTA", nullable = false)
    private TipoFrota tipoFrota;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGEC_IN_CARGA", nullable = false)
    private Boolean agenciamentoCarga = false;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGEC_IN_PROTECAO", nullable = false)
    private Boolean agenciamentoProtecao = false;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGEC_IN_PLATAFORMA", nullable = false)
    private Boolean agenciamentoPlataforma = false;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGEC_IN_EXCLUIDO", nullable = true)
    private Boolean excluido = Boolean.FALSE;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORT_CD_ID", nullable = false)
    private Porto porto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORT_CD_ID_ORIGEM", nullable = false)
    private Porto portoOrigem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORT_CD_ID_DESTINO", nullable = true)
    private Porto portoDestino;
    
    @Column(name = "AGEC_TX_DESTINO", nullable = true)
    private String destinoIntermediario;
    
    @Column(name = "AGEC_CD_VIAGEM", nullable = false)
    private String vgm;
    
    @Column(name = "AGEC_CD_VIAGEM_SAIDA", nullable = true)
    private String vgmSaida;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGEC_DT_ESTIMADA_SAIDA", nullable = true)
    private Date dataEstimadaSaida;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGEC_DT_SAIDA")
    private Date dataSaida;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGEC_DT_ETA", nullable = false)
    private Date eta;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGEC_DT_CHEGADA", nullable = true)
    private Date dataChegada;
    
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private AgenciamentoViagem agenciementoViagem = new AgenciamentoViagem();
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private AgenciamentoSanitaria agenciementoSanitaria = new AgenciamentoSanitaria();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Manobra.PROP_AGENCIAMENTO)
    private Set<Manobra> manobras = new HashSet<Manobra>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Operacao.PROP_AGENCIAMENTO)
    private Set<Operacao> operacoes = new HashSet<Operacao>();
    @Column(name = "AGEC_MD_CALADO_ENTRADA_RE", nullable = true)
    private Double caladoChegadaRe;
    @Column(name = "AGEC_MD_CALADO_ENTRADA_VANTE", nullable = true)
    private Double caladoChegadaVante;
    @Column(name = "AGEC_MD_CALADO_SAIDA_RE", nullable = true)
    private Double caladoSaidaRe;
    @Column(name = "AGEC_MD_CALADO_SAIDA_VANTE", nullable = true)
    private Double caladoSaidaVante;
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.AreaNavegacao")})
    @Column(name = "AGEC_IN_NAVEGACAO", nullable = true)
    private AreaNavegacao areaNavegacao;
    
     @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.AreaNavegacao")})
    @Column(name = "AGEC_IN_NAVEGACAO_SAIDA", nullable = true)
    private AreaNavegacao areaNavegacaoSaida;
    
    @Column(name = "AGEC_TX_DUV", nullable = true)
    private String numeroDUV;
    
    @Column(name = "AGEC_NR_ESCALA_MERCANTE", nullable = true)
    private Long numeroEscalaMercante;
   
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Pendencia.PROP_AGENCIAMENTO)
    private Set<Pendencia> pendencias = new HashSet<Pendencia>();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGEC_DT_ETA_PROX_DESTINO", nullable = true)
    private Date etaProximoPorto;
   
    @Column(name = "AGEC_TX_ESCALA", nullable = true)
    private String escalas;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Desvio.PROP_AGENCIAMENTO, cascade = CascadeType.ALL)
    private List<Desvio> desvio = new ArrayList<Desvio>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Taxa.PROP_AGENCIAMENTO)
    private Set<Taxa> taxas = new HashSet<Taxa>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Documento.PROP_AGENCIAMENTO)
    private Set<Documento> documentos = new HashSet<Documento>();
    
    @Column(name = "AGEC_TX_NR_PROCESSO_DESPACHO", nullable = true)
    private String numeroProcessoDespacho;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGEC_DT_INCLUSAO", nullable = false)
    private Date dataInclusao;
    
    @Column(name = "AGEC_NM_CADASTRADOR", nullable = false)
    private String nomeCadastrador;
    
    @Column(name = "AGEC_CD_CHAVE_CADASTRADOR", nullable = false)
    private String chaveCadastrador;
    
    @Column(name = "AGEC_TX_LONGITUDE_ENTRADA", nullable = true)
    private String longitudeEntrada;
    
    @Column(name = "AGEC_TX_LATITUDE_ENTRADA", nullable = true)
    private String latitudeEntrada;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Anexo.PROP_AGENCIAMENTO)
    private Set<Anexo> anexos = new HashSet<Anexo>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Visita.PROP_AGENCIAMENTO)
    private Set<Visita> visitas = new HashSet<Visita>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = CancelamentoAgenciamento.PROP_AGENCIAMENTO)
    private Set<CancelamentoAgenciamento> cancelamento = new HashSet<CancelamentoAgenciamento>();
    
    @Column(name = "AGEC_TX_OBSERVACAO")
    private String observacoes;  
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Acompanhamento.PROP_AGENCIAMENTO)
    private Set<Acompanhamento> acompanhamentos = new HashSet<Acompanhamento>();
    
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getAnoProcesso() {
        return anoProcesso;
    }

    public void setAnoProcesso(Integer anoProcesso) {
        this.anoProcesso = anoProcesso;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange(PROP_AGENCIA, null, null);
    }

    public Embarcacao getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(Embarcacao embarcacao) {
        this.embarcacao = embarcacao;
        firePropertyChange(PROP_EMBARCACAO, null, null);
        firePropertyChange("nomeComposto", null, null);
    }

    public StatusEmbarcacao getStatusEmbarcacao() {
        return statusEmbarcacao;
    }

    public void setStatusEmbarcacao(StatusEmbarcacao statusEmbarcacao) {
        this.statusEmbarcacao = statusEmbarcacao;
    }

    public SituacaoProcesso getSituacaoProcesso() {
        return situacaoProcesso;
    }

    public void setSituacaoProcesso(SituacaoProcesso situacaoProcesso) {
        this.situacaoProcesso = situacaoProcesso;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    
    public TipoArmador getTipoArmador() {
        return tipoArmador;
    }

    public void setTipoArmador(TipoArmador tipoArmador) {
        this.tipoArmador = tipoArmador;
    }

    public TipoFrota getTipoFrota() {
        return tipoFrota;
    }

    public void setTipoFrota(TipoFrota tipoFrota) {
        this.tipoFrota = tipoFrota;
    }

    public boolean getAgenciamentoCarga() { 
        return agenciamentoCarga != null ? agenciamentoCarga : false;
    }

    public void setAgenciamentoCarga(boolean agenciamentoCarga) {
        this.agenciamentoCarga = agenciamentoCarga;
        firePropertyChange(PROP_AGENCIAMENTO_CARGA, null, null);
    }

    public boolean getAgenciamentoProtecao() {
        return agenciamentoProtecao != null ? agenciamentoProtecao : false;
    }

    public void setAgenciamentoProtecao(boolean agenciamentoProtecao) {
        this.agenciamentoProtecao = agenciamentoProtecao;
        firePropertyChange(PROP_AGENCIAMENTO_PROTECAO, null, null);
    }

    public boolean getAgenciamentoPlataforma() {
        return agenciamentoPlataforma != null ? agenciamentoPlataforma : false;
    }

    public void setAgenciamentoPlataforma(boolean agenciamentoPlataforma) {
        this.agenciamentoPlataforma = agenciamentoPlataforma;
        firePropertyChange(PROP_AGENCIAMENTO_PLATAFORMA, null, null);
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
        firePropertyChange(PROP_PORTO, null, null);
    }

    public Porto getPortoOrigem() {
        return portoOrigem;
    }

    public void setPortoOrigem(Porto portoOrigem) {
        this.portoOrigem = portoOrigem;
    }

    public Porto getPortoDestino() {
        return portoDestino;
    }

    public void setPortoDestino(Porto portoDestino) {
        this.portoDestino = portoDestino;
    }

    public String getDestinoIntermediario() {
        return destinoIntermediario;
    }

    public void setDestinoIntermediario(String destinoIntermediario) {
        this.destinoIntermediario = destinoIntermediario;
    }

    public String getVgm() {
        return vgm;
    }

    public void setVgm(String vgm) {
        this.vgm = vgm;
    }

    public String getVgmSaida() {
        return vgmSaida;
    }

    public void setVgmSaida(String vgmSaida) {
        this.vgmSaida = vgmSaida;
    } 

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public Date getDataEstimadaSaida() {
        return dataEstimadaSaida;
    }

    public void setDataEstimadaSaida(Date dataEstimadaSaida) {
        this.dataEstimadaSaida = dataEstimadaSaida;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public AgenciamentoViagem getAgenciementoViagem() {
        return agenciementoViagem;
    }

    public void setAgenciementoViagem(AgenciamentoViagem agenciementoViagem) {
        this.agenciementoViagem = agenciementoViagem;
    }

    public AgenciamentoSanitaria getAgenciementoSanitaria() {
        return agenciementoSanitaria;
    }

    public void setAgenciementoSanitaria(AgenciamentoSanitaria agenciementoSanitaria) {
        this.agenciementoSanitaria = agenciementoSanitaria;
    }

    public Desvio getDesvio() {
        if (desvio.isEmpty()) {
            return null;
        }
        return desvio.get(0);
    }

    public void setDesvio(Desvio desvio) {
        this.desvio.set(0, desvio);
    }

    public CancelamentoAgenciamento getCancelamento() {
        if (cancelamento.isEmpty()) {
            return null;
        }
        return new ArrayList<CancelamentoAgenciamento>(cancelamento).get(0);
    }

    public List<Manobra> getManobras() {
        return Collections.unmodifiableList(new ArrayList<Manobra>(manobras));
    }

    public void setManobras(Set<Manobra> manobras) {
        this.manobras = manobras;
    }

    public void adicionarManobra(Manobra manobra) {
        if (manobra != null) {
            manobras.add(manobra);
        }
        firePropertyChange(PROP_MANOBRAS, null, null);
    }

    public void removerManobra(Manobra manobra) {
        manobras.remove(manobra);
        firePropertyChange(PROP_MANOBRAS, null, null);
    }

    public List<Operacao> getOperacoes() {
        return Collections.unmodifiableList(new ArrayList<Operacao>(operacoes));
    }

    public void setOperacoes(Set<Operacao> operacoes) {
        this.operacoes = operacoes;
    }

    public Double getCaladoChegadaRe() {
        return caladoChegadaRe;
    }

    public void setCaladoChegadaRe(Double caladoChegadaRe) {
        this.caladoChegadaRe = caladoChegadaRe;
        firePropertyChange(PROP_CALADO_CHEGADA_RE, null, null);
    }

    public Double getCaladoChegadaVante() {
        return caladoChegadaVante;
    }

    public void setCaladoChegadaVante(Double caladoChegadaVante) {
        this.caladoChegadaVante = caladoChegadaVante;
        firePropertyChange(PROP_CALADO_CHEGADA_VANTE, null, null);
    }

    public Double getCaladoSaidaRe() {
        return caladoSaidaRe;
    }

    public void setCaladoSaidaRe(Double caladoSaidaRe) {
        this.caladoSaidaRe = caladoSaidaRe;
        firePropertyChange(PROP_CALADO_SAIDA_RE, null, null);
    }

    public Double getCaladoSaidaVante() {
        return caladoSaidaVante;
    }

    public void setCaladoSaidaVante(Double caladoSaidaVante) {
        this.caladoSaidaVante = caladoSaidaVante;
        firePropertyChange(PROP_CALADO_SAIDA_VANTE, null, null);
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public AreaNavegacao getAreaNavegacao() {
        return areaNavegacao;
    }

    public void setAreaNavegacao(AreaNavegacao areaNavegacao) {
        this.areaNavegacao = areaNavegacao;
    }

    public AreaNavegacao getAreaNavegacaoSaida() {
        return areaNavegacaoSaida;
    }

    public void setAreaNavegacaoSaida(AreaNavegacao areaNavegacaoSaida) {
        this.areaNavegacaoSaida = areaNavegacaoSaida;
    } 

    public String getNumeroDUV() {
        return numeroDUV;
    }

    public void setNumeroDUV(String numeroDUV) {
        this.numeroDUV = numeroDUV;
    }

    public Long getNumeroEscalaMercante() {
        return numeroEscalaMercante;
    }

    public void setNumeroEscalaMercante(Long numeroEscalaMercante) {
        this.numeroEscalaMercante = numeroEscalaMercante;
    }

    public String getNumeroProcessoDespacho() {
        return numeroProcessoDespacho;
    }

    public void setNumeroProcessoDespacho(String numeroProcessoDespacho) {
        this.numeroProcessoDespacho = numeroProcessoDespacho;
    }

    public List<Pendencia> getPendencias() {
        return Collections.unmodifiableList(new ArrayList<Pendencia>(pendencias));
    }

    public void setPendencias(Set<Pendencia> pendencias) {
        this.pendencias = pendencias;
    }

    public Date getEtaProximoPorto() {
        return etaProximoPorto;
    }

    public void setEtaProximoPorto(Date etaProximoPorto) {
        this.etaProximoPorto = etaProximoPorto;
    }

    public String getEscalas() {
        return escalas;
    }

    public void setEscalas(String escalas) {
        this.escalas = escalas;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getNomeCadastrador() {
        return nomeCadastrador;
    }

    public void setNomeCadastrador(String nomeCadastrador) {
        this.nomeCadastrador = nomeCadastrador;
    }

    public String getChaveCadastrador() {
        return chaveCadastrador;
    }

    public void setChaveCadastrador(String chaveCadastrador) {
        this.chaveCadastrador = chaveCadastrador;
    }

    public String getLongitudeEntrada() {
        return longitudeEntrada;
    }

    public void setLongitudeEntrada(String longitudeEntrada) {
        this.longitudeEntrada = longitudeEntrada;
    }

    public String getLatitudeEntrada() {
        return latitudeEntrada;
    }

    public void setLatitudeEntrada(String latitudeEntrada) {
        this.latitudeEntrada = latitudeEntrada;
    }

    public String getNumeroProcessoformatado() {
        return agencia.getSigla().trim() + String.format("%04d", codigo) + "/" + anoProcesso;
    }
    
    public String getDataInclusaoProcessoFormatado(){
        return SistamDateUtils.formatDate(this.dataInclusao, "dd/MM/yyyy HH:mm", TimeZone.getTimeZone(agencia.getTimezone()));
    } 

    public String getNumeroEscalaMercanteFormatado() {
        if (numeroEscalaMercante == null) {
            return null;
        }
        return String.format("%d", numeroEscalaMercante);
    }

    public Set<Taxa> getTaxas() {
        return taxas;
    }

    public void setTaxas(Set<Taxa> taxas) {
        this.taxas = taxas;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Set<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(Set<Anexo> anexos) {
        this.anexos = anexos;
    }

    public Set<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(Set<Visita> visitas) {
        this.visitas = visitas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    } 

    public List<Acompanhamento> getAcompanhamentos() {
        return new ArrayList<Acompanhamento>(acompanhamentos);
    }

    public void setAcompanhamentos(Set<Acompanhamento> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    } 
    
    

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters Sintéticos">
    /**
     * Obtém as pendências que ainda não foram resolvidas.
     *
     * @return
     */
    public List<Pendencia> getPendenciasNaoResolvidas() {
        List<Pendencia> lista = new ArrayList<Pendencia>();

        for (Pendencia pendencia : pendencias) {
            if (Boolean.FALSE.equals(pendencia.isResolvida())) {
                lista.add(pendencia);
            }
        }
        return lista;
    }

    /**
     * Obtém as manobras solicitadas. As manobras consideradas soliictadas têm
     * um dos seguintes status: SOLICITADA, CANCELADA ou PLANEJADA.
     *
     * @return
     */
    public List<Manobra> getManobrasSolicitadas() {
        List<Manobra> lista = new ArrayList<Manobra>();
        for (Manobra manobra : manobras) {
            if (StatusManobra.SOLICITADA.equals(manobra.getStatus())
                    || StatusManobra.CANCELADA.equals(manobra.getStatus())
                    || StatusManobra.PLANEJADA.equals(manobra.getStatus())) {
                lista.add(manobra);
            }
        }
        return lista;
    }

    public String getDestinoFormatado() {
        StringBuilder destinoFormatado = new StringBuilder();
        if (portoDestino != null) {
            destinoFormatado.append(portoDestino.getNomeCompleto());
        }

        if (destinoIntermediario != null) {
            destinoFormatado.append(" ");
            destinoFormatado.append(destinoIntermediario);
        }
        return destinoFormatado.toString();
    }

    /**
     * Obtém as manobras registradas. A manobras consideradas registradas têm um
     * dos seguintes status: RGISTRADA, CANCELADA FORA DO PRAZO ou ENCERRADA.
     *
     * @return
     */
    public List<Manobra> getManobrasRegistradas() {
        List<Manobra> lista = new ArrayList<Manobra>();
        for (Manobra manobra : manobras) {
            if (StatusManobra.REGISTRADA.equals(manobra.getStatus())
                    || StatusManobra.CANCELADA_FORA_PRAZO.equals(manobra.getStatus())
                    || StatusManobra.PRE_REGISTRADA.equals(manobra.getStatus())
                    || StatusManobra.ENCERRADA.equals(manobra.getStatus())) {
                lista.add(manobra);
            }
        }
        return lista;
    }

    /**
     * Obtém um resumo das operaçõs.
     *
     * @return
     */
    public String getResumoOperacoes() {
        StringBuilder resumo = new StringBuilder();

        for (Operacao operacao : operacoes) {
            resumo.append(operacao.getTipoOperacao().getPorExtenso());
            if (operacao.getProduto() != null) {
                resumo.append(" - ").append(operacao.getProduto().getNomeCompleto());
            }
            if (operacao.getQuatidadeEstimada() != null) {
                resumo.append(" - ").append(String.format("%.4f", operacao.getQuatidadeEstimada()));
            }
            if (operacao.getUnidadeMedida() != null) {
                resumo.append("(").append(operacao.getUnidadeMedida().getSigla()).append(") ");
            }
        }

        return resumo.toString();
    }

    public String getOperacoesFormatadas() {
        StringBuilder resumo = new StringBuilder();

        for (Operacao operacao : operacoes) {

            if (!resumo.toString().isEmpty()) {
                resumo.append(" / ");
            }

            resumo.append(operacao.getTipoOperacao().getSigla());
            if (operacao.getProduto() != null) {
                resumo.append(" - ").append(operacao.getProduto().getNomeCompleto());
            }
        }

        return resumo.toString();
    }

    public String getQuantidadesProdutosFormatadas() {
        StringBuilder resumo = new StringBuilder();

        for (Operacao operacao : operacoes) {

            if (!resumo.toString().isEmpty()) {
                resumo.append(" / ");
            }

            if (operacao.getQuatidadeEstimada() != null) {
                resumo.append(String.format("%,.4f", operacao.getQuatidadeEstimada()));
                if (operacao.getUnidadeMedida() != null) {
                    resumo.append(" ");
                }
            }
            if (operacao.getUnidadeMedida() != null) {
                resumo.append(operacao.getUnidadeMedida().getSigla());
            }
        }

        return resumo.toString();

    }

    /**
     * Obtém uma descrição para o agenciamento na forma "nome completo da
     * embarcação - viagem"
     *
     * @return
     */
    public String getNomeComposto() {
        return getEmbarcacao().getNomeCompleto() + " - " + getVgm();
    }

    /**
     * Obtém o espaço disponivel para anexar arquivos.
     *
     * @return
     */
    public String getEspacoDiponvivelParaArquivosFormatado() {
        long espacoDisponivel = getEspacoDiponivelParaArquivosEmBytes();

        if (espacoDisponivel <= 0) {
            return "0 MB";
        }
        final String[] units = new String[]{"bytes", "kB", "MB"};
        int digitGroups = (int) (Math.log10(espacoDisponivel) / Math.log10(1024));
        String tamanho = new DecimalFormat("#,##0.##").format(espacoDisponivel / Math.pow(1024, digitGroups)) + " " + units[digitGroups];

        return String.format("%s de um total de %.0f MB.", tamanho, (double) (TAM_MAX_TOTAL_ARQUIVOS_EM_BYTES / (1024 * 1024)));
    }

    /**
     * Obtém o espaço, em bytes, disponível para anexar arquivos .
     *
     * @return
     */
    public long getEspacoDiponivelParaArquivosEmBytes() {
        return TAM_MAX_TOTAL_ARQUIVOS_EM_BYTES - getEspacoUsandoPorArquivosEmBytes();
    }

    /**
     * Obtém os espaço, em bytes, usado pelos arquivos anexados.
     *
     * @return
     */
    public long getEspacoUsandoPorArquivosEmBytes() {
        long total = 0;

        for (Anexo anexo : anexos) {
            total += anexo.getTamanhoEmBytes();
        }

        return total;
    }

    /**
     * Retorna a data da maior data entre as manobras de atracação
     *
     * @return
     */
    public Date getDataHoraAtracacao() {
        Date data = null;

        for (Manobra manobra : manobras) {

            if (FinalidadeManobra.ATRACACAO.equals(manobra.getFinalidadeManobra())
                    && manobra.getDataTermino() != null) {

                if (data == null || data.before(manobra.getDataTermino())) {
                    data = manobra.getDataTermino();
                }
            }
        }
        return data;

    }

    /**
     * Obtém a situação de Livre Prática do agenciamento.
     *
     * @return
     */
    public SituacaoLivrePratica getSituacaoLivrePratica() {
        //Se o agenciamento for VCP, o tipo de livre prática será VCP.
        if (TipoContrato.VCP.equals(tipoContrato)) {
            return SituacaoLivrePratica.VCP;
        }


        //Se tiver documento do tipo Solicitação de Certificado, 
        //o tipo será OK se estiver protocolado ou ANV se não estiver protocolado.
        for (Documento documento : documentos) {
            if (TipoDocumento.SOLICITACAO_CERTIFICADO.equals(documento.getTipoDocumento())) {
                if (documento.getDataProtocolo() != null) {
                    return SituacaoLivrePratica.OK;
                }
                return SituacaoLivrePratica.ANV;
            }
        }

        //Se não tiver documento do tipo Solicitação de Certificado e não tiver
        //pago a taxa do tipo Livre Prática Anvisa, a situação livre prática será PP.
        boolean pagouTaxaLP = false;
        for (Taxa taxa : taxas) {
            if (TipoTaxa.LIVRE_PRATICA_ANVISA.equals(taxa.getTipoTaxa())) {
                pagouTaxaLP = true;
            }
        }

        if (!pagouTaxaLP) {
            return SituacaoLivrePratica.PP;
        }

        return null;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Métodos para adicionar e remover objetos das listas">
    /**
     * Adiciona uma operação, quando diferente de null.
     *
     * @param operacao
     */
    public void adicionarOperacao(Operacao operacao) {
        if (operacao != null) {
            operacao.setAgenciamento(this);
            operacoes.add(operacao);
        }
    }

    /**
     * Remove uma operação.
     *
     * @param operacao
     */
    public void removerOpecacao(Operacao operacao) {
        if (operacao != null) {
            operacao.setAgenciamento(null);
            operacoes.remove(operacao);
        }
    }

    /**
     * Adiciona uma pendência, quando diferente de null.
     *
     * @param pendencia
     */
    public void adicionarPendencia(Pendencia pendencia) {
        if (pendencia != null) {
            pendencia.setAgenciamento(this);
            pendencias.add(pendencia);
        }
    }

    /**
     * Remove uma pedência.
     *
     * @param pendencia
     */
    public void removerPendencia(Pendencia pendencia) {
        if (pendencia != null) {
            pendencia.setAgenciamento(null);
            pendencias.remove(pendencia);
        }
    }

    /**
     * Adiciona uma taxa, quando diferente de null.
     *
     * @param taxa
     */
    public void adicionarTaxa(Taxa taxa) {
        if (taxa != null) {
            taxa.setAgenciamento(this);
            taxas.add(taxa);
        }
    }

    /**
     * Remove uma taxa.
     *
     * @param taxa
     */
    public void removerTaxa(Taxa taxa) {
        if (taxa != null) {
            taxa.setAgenciamento(null);
        }
        taxas.remove(taxa);
    }

    /**
     * Adiciona um documento, quando diferente de null.
     *
     * @param documento
     */
    public void adicionarDocumento(Documento documento) {
        if (documento != null) {
            documento.setAgenciamento(this);
            documentos.add(documento);
        }
    }

    /**
     * Remove um documento.
     *
     * @param documento
     */
    public void removerDocumento(Documento documento) {
        if (documento != null) {
            documento.setAgenciamento(null);
        }
        documentos.remove(documento);
    }

    /**
     * Adiciona um anexo, qundo diferente de null.
     *
     * @param anexo
     */
    public void adicionarAnexo(Anexo anexo) {
        if (anexo != null) {
            anexo.setAgenciamento(this);
            anexos.add(anexo);
        }

        firePropertyChange("espacoDiponvivelParaArquivosFormatado", null, null);
    }

    /**
     * Adciona uma lista de anexos.
     *
     * @param listaAnexos
     */
    public void adicionarAnexos(List<Anexo> listaAnexos) {
        for (Anexo anexo : listaAnexos) {
            if (anexo != null) {
                anexo.setAgenciamento(this);
                anexos.add(anexo);
            }
        }

        firePropertyChange("espacoDiponvivelParaArquivosFormatado", null, null);
    }

    /**
     * Remove um anexo.
     *
     * @param anexo
     */
    public void removerAnexo(Anexo anexo) {
        if (anexo != null) {
            anexo.setAgenciamento(null);
            anexos.remove(anexo);
        }
        firePropertyChange("espacoDiponvivelParaArquivosFormatado", null, null);
    }

    /**
     * Adiciona uma visita, quando diferente de null.
     *
     * @param visita
     */
    public void adicionarVisita(Visita visita) {
        if (visita != null) {
            visita.setAgenciamento(this);
            this.visitas.add(visita);
        }
    }

    /**
     * Remove uma visita.
     *
     * @param visita
     */
    public void removerVisita(Visita visita) {
        if (visita != null) {
            visita.setAgenciamento(null);
            this.visitas.remove(visita);
        }
    }
    //</editor-fold>

    /**
     * Informa se o agenciamento possui operação do tipo Carga Cabotagem.
     *
     * @return
     */
    public boolean possuiOperacaoCarga() {
        for (Operacao operacao : operacoes) {
            if (TipoOperacao.CARGA_CABOTAGEM.equals(operacao.getTipoOperacao())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Informa se o agenciamento possui operação do tipo Descarga Cabotagem.
     *
     * @return
     */
    public boolean possuiOperacaoDescarga() {
        for (Operacao operacao : operacoes) {
            if (TipoOperacao.DESCARGA_CABOTAGEM.equals(operacao.getTipoOperacao())) {
                return true;
            }
        }
        return false;
    }

    public boolean isTipoContatoVCP() {
        return TipoContrato.VCP.equals(tipoContrato);
    }

    public boolean isTipoContatoTCP() {
        return TipoContrato.TCP.equals(tipoContrato);
    }
    
    public boolean isTipoAreaNavegacaoChegadaCABOTAGEM(){
        return AreaNavegacao.CABOTAGEM.equals(areaNavegacao);
    }
    public boolean isTipoAreaNavegacaoChegadaLONGO_CURSO(){
        return AreaNavegacao.LONGO_CURSO.equals(areaNavegacao);
    }
    public boolean isTipoAreaNavegacaoChegadaTANSITO_ADUANEIRO(){
        return AreaNavegacao.TANSITO_ADUANEIRO.equals(areaNavegacao);
    }
    
    public boolean isTipoAreaNavegacaoSaidaCABOTAGEM(){
        return AreaNavegacao.CABOTAGEM.equals(areaNavegacaoSaida);
    }
    
    public boolean isTipoAreaNavegacaoSaidaLONGO_CURSO(){
        return AreaNavegacao.LONGO_CURSO.equals(areaNavegacaoSaida);
    }
    
    public boolean isTipoAreaNavegacaoSaidaTANSITO_ADUANEIRO(){
        return AreaNavegacao.TANSITO_ADUANEIRO.equals(areaNavegacaoSaida);
    } 
    
    public boolean isTipoAgenciamentoCarga(){
        return agenciamentoCarga;
    }
    
    public boolean isTipoAgenciamentoProtecao(){
        return agenciamentoProtecao;
    }
    
    public boolean isTipoAgenciamentoPlataforma(){
        return agenciamentoPlataforma;
    }
    
    public String getvgmEntradaSaida(){
        String vgmChegada = vgm;
        String vgmSda   = vgmSaida != null ? vgmSaida : "";
        String vgmResultado = vgmChegada;
        if (vgmChegada.equals(vgmSda)) {
            vgmResultado = vgmChegada;
        }
        else if (!vgmSda.equals(""))
            vgmResultado = vgmChegada + " / " + vgmSda;
        
        return vgmResultado;
    } 
    

    /**
     *     * Obtém a localização atual da embarcação, que será o ponto de
     * chegada da     * última manobra registrada. A última manobra registrada
     * será a que tiver a     * maior data de chegada dentre as manobras com
     * status REGISTRADA ou     * ENCERRADA.     * Quando o status da embarcação
     * for ENCERRADO, será retornado null.     *    
     *
     * @return    
     */
    public PontoAtracacao obterLocalizacaoAtual() {

        if (StatusEmbarcacao.SAIDO.equals(statusEmbarcacao)
                || StatusEmbarcacao.DESVIADO.equals(statusEmbarcacao)
                || StatusEmbarcacao.CANCELADO.equals(statusEmbarcacao)
                || StatusEmbarcacao.ESPERADO.equals(statusEmbarcacao)
                || StatusEmbarcacao.NO_PORTO.equals(statusEmbarcacao)) {

            return null;
        }

        Manobra ultimaManobraRegistrada = null;

        for (Manobra manobra : manobras) {
            if (StatusManobra.REGISTRADA.equals(manobra.getStatus())
                    || StatusManobra.ENCERRADA.equals(manobra.getStatus())
                    || StatusManobra.PRE_REGISTRADA.equals(manobra.getStatus())) {

                if ((ultimaManobraRegistrada == null && manobra.getDataTermino() != null) || (ultimaManobraRegistrada != null && manobra.getDataTermino() != null && manobra.getDataTermino().after(ultimaManobraRegistrada.getDataTermino()))) {
                    ultimaManobraRegistrada = manobra;
                }
            }
        }

        if (ultimaManobraRegistrada != null) {
            return ultimaManobraRegistrada.getPontoAtracacaoDestino();
        }
        return null;
    }

    @Override
    public int compareTo(Agenciamento outro) {
        return this.eta.compareTo(outro.getEta());
    }

    @Override
    public String toString() {
        return getEmbarcacao().getNomeCompleto() + " - " + getVgm();
    }

    @Override
    public Agenciamento clone() {
        Agenciamento clone = (Agenciamento) super.clone();
        clone.setAgenciementoViagem(this.agenciementoViagem.clone());
        clone.setAgenciementoSanitaria(this.agenciementoSanitaria.clone());
        return clone;
    }
}
