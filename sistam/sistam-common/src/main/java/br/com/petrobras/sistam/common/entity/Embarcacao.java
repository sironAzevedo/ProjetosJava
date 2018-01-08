package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.ClassificacaoEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoEmbarcacao;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "EMBARCACAO", schema = "STAM")
public class Embarcacao extends AbstractIdBean<String> implements Serializable {
    private static final long serialVersionUID = 1L;
     
    public static final String PROP_ID = "id";
    public static final String PROP_CLASSIFICACAO = "classificacao";
    public static final String PROP_INSCRICAO = "inscricao";
    public static final String PROP_DATA_ULTIMA_INSPECAO = "dataUltimaInspecao";
    public static final String PROP_PAIS_INSPECAO = "paisInspecao";
    public static final String PROP_DATA_VALIDADE_DECLARACAO_PROVISORIA = "dataValidadeDeclaracaoProvisoria";
    public static final String PROP_DATA_VALIDADE_DECLARACAO_CONFORMIDADE = "dataValidadeDeclaracaoConformidade";
    public static final String PROP_HELIPORTO = "heliporto";
    public static final String PROP_NOME_COMPLETO = "nomeCompleto";
    public static final String PROP_APELIDO = "apelido";
    public static final String PROP_BANDEIRA = "bandeira";
    public static final String PROP_IMO = "imo";
    public static final String PROP_CAPACIDADE_AGUA = "capacidadeAgua";
    public static final String PROP_CALADO_MAXIMO = "caladoMaximo";
    public static final String PROP_ESCALAS = "escalas";
    public static final String PROP_ARQUEACAO_LIQUIDA = "arqueacaoLiquida";
    public static final String PROP_ARQUEACAO_BRUTA = "arqueacaoBruta";
    public static final String PROP_DWT = "dwt";
    public static final String PROP_LOA = "loa";
    public static final String PROP_IRIN = "irin";
    public static final String PROP_TIPO_EMBARCACAO = "tipoEmbarcacao";
    public static final String PROP_NUMERO_REGISTRO = "numeroRegistro";
    public static final String PROP_DATA_REGISTRO = "dataRegistro";
    public static final String PROP_PORTO_REGISTRO = "portoRegistro";
    public static final String PROP_PROPRIETARIO = "proprietario";
    public static final String PROP_ARMADOR = "armador";
    public static final String PROP_EMAIL = "email";
    public static final String PROP_TELEFONE = "telefone";
    public static final String PROP_CENTRO_CUSTO = "centro_custo";
    public static final String PROP_SOCIEDADE_CLASSIFICADORA = "sociedade_classificadora";
    
    public Embarcacao() {
    }
    
    @Id
    @Column(name = "EMBA_CD_ID", nullable=false)
    private String id;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.ClassificacaoEmbarcacao")})
    @Column(name="EMBA_IN_CLASSIFICACAO", nullable=false)
    private ClassificacaoEmbarcacao classificacao;

    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoEmbarcacao")})
    @Column(name="EMBA_IN_TIPO_EMBARCACAO", nullable=false)
    private TipoEmbarcacao tipoEmbarcacao;
    
    @Column(name = "EMBA_TX_NR_INSCRICAO")
    private String inscricao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMBA_DT_ULTIMA_INSPECAO")
    private Date dataUltimaInspecao;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="PAIS_CD_ULTIMA_INSPECAO", nullable=true )
    private Pais paisInspecao;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "EMBA_DT_VALIDADE_PROVISORIA")
    private Date dataValidadeDeclaracaoProvisoria;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "EMBA_DT_VALIDADE_CONFORMIDADE")
    private Date dataValidadeDeclaracaoConformidade;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "EMBA_IN_TEM_HELIPORTO", nullable=false)
    private Boolean heliporto;
    
    @Column(name = "EMBA_NM_COMPLETO", nullable=true )
    private String nomeCompleto;

    @Column(name = "EMBA_NM_APELIDO", nullable=true)
    private String apelido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PAIS_CD_IBGE", nullable=true)
    private Pais bandeira;
    
    @Column(name = "EMBA_TX_IMO", nullable=true)
    private String imo;

    @Column(name = "EMBA_MD_CAP_AGUA", nullable=true)
    private Double capacidadeAgua;

    @Column(name = "EMBA_MD_CAL_MAXIMO", nullable=true)
    private Double caladoMaximo;

    @Column(name = "EMBA_MD_NRT", nullable=true)
    private Long arqueacaoLiquida;

    @Column(name = "EMBA_MD_GRT", nullable=true)
    private Long arqueacaoBruta;

    @Column(name = "EMBA_NR_DWT", nullable=true)
    private Long dwt;
    
    @Column(name = "EMBA_MD_LOA", nullable=true)
    private Double loa;
       
    @Column(name = "EMBA_TX_PREFIXO", nullable=true)
    private String irin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMBA_DT_CONSTRUCAO", nullable=true)
    private Date dataConstrucao;

    @Column(name = "EMBA_NR_REGISTRO", nullable=true)
    private Long numeroRegistro;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMBA_DT_REGISTRO", nullable=true)
    private Date dataRegistro;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="PORT_CD_ID_REGISTRO", nullable=true)
    private Porto portoRegistro;

    @Column(name = "EMBA_NM_PROPRIETARIO", nullable=true)
    private String proprietario;
    
    @Column(name = "EMBA_NM_ARMADOR", nullable=true)
    private String armador;
    
    @Column(name = "EMBA_TX_EMAIL", nullable=true)
    private String email;

    @Column(name = "EMBA_NR_TELEFONE", nullable=true)
    private String telefone;

    @Column(name = "EMBA_TX_CENTRO_CUSTO", nullable=true)
    private String centro_custo;

    @Column(name = "EMBA_TX_SOC_CLASSIFICADORA", nullable=true)
    private String sociedade_classificadora;
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
        firePropertyChange("id", null, this.id);
    }

    public ClassificacaoEmbarcacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoEmbarcacao classificacao) {
        this.classificacao = classificacao;
    }

    public TipoEmbarcacao getTipoEmbarcacao() {
        return tipoEmbarcacao;
    }

    public void setTipoEmbarcacao(TipoEmbarcacao tipoEmbarcacao) {
        this.tipoEmbarcacao = tipoEmbarcacao;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public Date getDataUltimaInspecao() {
        return dataUltimaInspecao;
    }

    public void setDataUltimaInspecao(Date dataUltimaInspecao) {
        this.dataUltimaInspecao = dataUltimaInspecao;
    }

    public Pais getPaisInspecao() {
        return paisInspecao;
    }

    public void setPaisInspecao(Pais paisInspecao) {
        this.paisInspecao = paisInspecao;
    }

    public Date getDataValidadeDeclaracaoProvisoria() {
        return dataValidadeDeclaracaoProvisoria;
    }

    public void setDataValidadeDeclaracaoProvisoria(Date dataValidadeDeclaracaoProvisoria) {
        this.dataValidadeDeclaracaoProvisoria = dataValidadeDeclaracaoProvisoria;
    }

    public Date getDataValidadeDeclaracaoConformidade() {
        return dataValidadeDeclaracaoConformidade;
    }

    public void setDataValidadeDeclaracaoConformidade(Date dataValidadeDeclaracaoConformidade) {
        this.dataValidadeDeclaracaoConformidade = dataValidadeDeclaracaoConformidade;
    }

    public boolean getHeliporto() {
        if (heliporto == null){
            return false;
        }
        return heliporto;
    }

    public void setHeliporto(boolean heliporto) {
        this.heliporto = heliporto ;
    }
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    
    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public Double getCapacidadeAgua() {
        return capacidadeAgua;
    }

    public void setCapacidadeAgua(Double capacidadeAgua) {
        this.capacidadeAgua = capacidadeAgua;
    }

    public Pais getBandeira() {
        return bandeira;
    }

    public void setBandeira(Pais bandeira) {
        this.bandeira = bandeira;
    }

    public Double getCaladoMaximo() {
        return caladoMaximo;
    }

    public void setCaladoMaximo(Double caladoMaximo) {
        this.caladoMaximo = caladoMaximo;
    }

    public Long getArqueacaoLiquida() {
        return arqueacaoLiquida;
    }

    public void setArqueacaoLiquida(Long arqueacaoLiquida) {
        this.arqueacaoLiquida = arqueacaoLiquida;
    }

    public Long getArqueacaoBruta() {
        return arqueacaoBruta;
    }

    public void setArqueacaoBruta(Long arqueacaoBruta) {
        this.arqueacaoBruta = arqueacaoBruta;
    }

    public Long getDwt() {
        return dwt;
    }

    public void setDwt(Long dwt) {
        this.dwt = dwt;
    }
    
    public Double getLoa() {
        return loa;
    }

    public void setLoa(Double loa) {
        this.loa = loa;
    }
    
    public String getIrin() {
        return irin;
    }

    public void setIrin(String irin) {
        this.irin = irin;
    }

    public Date getDataConstrucao() {
        return dataConstrucao;
    }

    public void setDataConstrucao(Date dataConstrucao) {
        this.dataConstrucao = dataConstrucao;
    }

    public Long getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Porto getPortoRegistro() {
        return portoRegistro;
    }

    public void setPortoRegistro(Porto portoRegistro) {
        this.portoRegistro = portoRegistro;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getArmador() {
        return armador;
    }

    public void setArmador(String armador) {
        this.armador = armador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCentro_custo() {
        return centro_custo;
    }

    public void setCentro_custo(String centro_custo) {
        this.centro_custo = centro_custo;
    }

    public String getSociedade_classificadora() {
        return sociedade_classificadora;
    }

    public void setSociedade_classificadora(String sociedade_classificadora) {
        this.sociedade_classificadora = sociedade_classificadora;
    }
    
    
    
    @Override
    public String toString() {
        return nomeCompleto;
    }
    
}

