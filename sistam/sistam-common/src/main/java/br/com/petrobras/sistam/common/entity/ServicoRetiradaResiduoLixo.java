package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoResiduo;
import br.com.petrobras.sistam.common.enums.TipoUnidadeMedida;
import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 */
@Entity
@Table(name = "SERVICO_RETIRADA_RESIDUO", schema = "STAM")
public class ServicoRetiradaResiduoLixo extends AbstractIdBean<Long> implements ServicoExecutado, Serializable {

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    private static final long serialVersionUID = 1L;
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_ID = "id";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_SERVICO_PROTECAO = "servicoProtecao";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_REQUERENTE = "empresaRequerente";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_MARITIMA = "empresaMaritima";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_SERVICO = "empresaServico";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_MARITIMA_RODOVIARIA = "empresaMaritimaRodoviaria";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_TIPO_RESIDUO = "tipoResiduo";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_CARACTERIZACAO = "caracterizacao";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_ESTADO_FISICO = "estadoFisico";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_CLASSIFICACAO = "classificacao";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_CODIGO_ONU = "codigoOnu";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_QUANTIDADE_RESIDUO = "quantidadeResiduo";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_TIPO_UNIDADE_MEDIDA = "tipoUnidadeMedida";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_DATA_INICIO_OPERACAO = "dataInicioOperacao";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_DATA_TERMINO_OPERACAO = "dataFimOperacao";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_LOCAL_ARMAZENAMENTO = "localArmazenagem";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_LON_ARMAZENAGEM = "lonArmazenagem";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_DESCRICAO_CADRI = "descCadri";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_LOCAL_DESTINO_FINAL = "localDestinoFinal";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_LON_DESTINO_FINAL = "lonDestinoFinal";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_CADRI_DESTINO_FINAL = "cadriDestinoFinal";
    public static final String SERVICO_RETIRADA_RESIDUO_LIXO_VALOR_SERVICO = "valorServico";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Propriedades">    
    @Id
    @Column(name = "PRSE_SQ_PROTECAO", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRSE_SQ_PROTECAO", nullable = false, insertable = false, updatable = false)
    private ServicoProtecao servicoProtecao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMMA_SQ_EMPRESA_MARITIMA", nullable = false)
    private EmpresaMaritima empresaMaritima;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMSE_SQ_EMPRESA_SERVICO", nullable = false)
    private Servico empresaServico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMMA_SQ_EMPRESA_MARITIMA_RODOV", nullable = false)
    private EmpresaMaritima empresaMaritimaRodoviaria;
    
    @Column(name = "SERR_TX_CARACTERIZACAO")
    private String caracterizacao;
    
    @Column(name = "SERR_TX_ESTADO_FISICO")
    private String estadoFisico;
    
    @Column(name = "SERR_TX_CLASSIFICACAO")
    private String classificacao;
    
    @Column(name = "SERR_TX_CODIGO_ONU")
    private String codigoOnu;
    
    @Column(name = "SERR_QN_RESIDUO")
    private Integer quantidadeResiduo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SERR_DT_INICIO_OPERACAO")
    private Date dataInicioOperacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SERR_DT_TERMINO_OPERACAO")
    private Date dataFimOperacao;
    
    @Column(name = "SERR_TX_LOCAL_ARMAZENAGEM")
    private String localArmazenagem;
    
    @Column(name = "SERR_TX_LONR_ARMAZENAGEM")
    private String lonArmazenagem;
    
    @Column(name = "SERR_TX_CADRI_ARMAZENAGEM")
    private String descCadri;
    
    @Column(name = "SERR_TX_LOCAL_DESTINO")
    private String localDestinoFinal;
    
    @Column(name = "SERR_TX_LONR_DESTINO")
    private String lonDestinoFinal;
    
    @Column(name = "SERR_TX_CADRI_DESTINO")
    private String cadriDestinoFinal;
    
    @Column(name = "SERR_VL_SERVIVCO")
    private BigDecimal valorServico;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.ResponsavelCusto")})
    @Column(name = "SERR_IN_EMPRESA_REQUERENTE", nullable = false)
    private ResponsavelCusto responsavelCusto;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoResiduo")})
    @Column(name = "SERR_IN_TIPO_RESIDUO", nullable = false)
    private TipoResiduo tipoResiduo;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoUnidadeMedida")})
    @Column(name = "SERR_IN_UNIDADE_MEDIDA", nullable = false)
    private TipoUnidadeMedida tipoUnidadeMedida;

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
        firePropertyChange(SERVICO_RETIRADA_RESIDUO_LIXO_ID, null, null);
    }

    @Override
    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
        firePropertyChange(SERVICO_RETIRADA_RESIDUO_LIXO_SERVICO_PROTECAO, null, null);
    }

    public EmpresaMaritima getEmpresaMaritima() {
        return empresaMaritima;
    }

    public void setEmpresaMaritima(EmpresaMaritima empresaMaritima) {
        this.empresaMaritima = empresaMaritima;
        firePropertyChange(SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_MARITIMA, null, null);
    }

    public Servico getEmpresaServico() {
        return empresaServico;
    }

    public void setEmpresaServico(Servico empresaServico) {
        this.empresaServico = empresaServico;
        firePropertyChange(SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_SERVICO, null, null);
    }

    public Date getDataInicioOperacao() {
        return dataInicioOperacao;
    }

    public void setDataInicioOperacao(Date dataInicioOperacao) {
        this.dataInicioOperacao = dataInicioOperacao;
    }

    public Date getDataFimOperacao() {
        return dataFimOperacao;
    }

    public void setDataFimOperacao(Date dataFimOperacao) {
        this.dataFimOperacao = dataFimOperacao;
    }

    public ResponsavelCusto getResponsavelCusto() {
        return responsavelCusto;
    }

    public void setResponsavelCusto(ResponsavelCusto responsavelCusto) {
        this.responsavelCusto = responsavelCusto;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
        firePropertyChange("tipoResiduo", null, null);
    }

    public TipoUnidadeMedida getTipoUnidadeMedida() {
        return tipoUnidadeMedida;
    }

    public void setTipoUnidadeMedida(TipoUnidadeMedida tipoUnidadeMedida) {
        this.tipoUnidadeMedida = tipoUnidadeMedida;
        firePropertyChange("tipoUnidadeMedida", null, null);
    }

    public EmpresaMaritima getEmpresaMaritimaRodoviaria() {
        return empresaMaritimaRodoviaria;
    }

    public void setEmpresaMaritimaRodoviaria(EmpresaMaritima empresaMaritimaRodoviaria) {
        this.empresaMaritimaRodoviaria = empresaMaritimaRodoviaria;
    }

    public String getCaracterizacao() {
        return caracterizacao;
    }

    public void setCaracterizacao(String caracterizacao) {
        this.caracterizacao = caracterizacao;
        firePropertyChange("caracterizacao", null, null);
    }

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
        firePropertyChange("estadoFisico", null, null);
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
        firePropertyChange("classificacao", null, null);
    }

    public String getCodigoOnu() {
        return codigoOnu;
    }

    public void setCodigoOnu(String codigoOnu) {
        this.codigoOnu = codigoOnu;
        firePropertyChange("codigoOnu", null, null);
    }

    public Integer getQuantidadeResiduo() {
        return quantidadeResiduo;
    }

    public void setQuantidadeResiduo(Integer quantidadeResiduo) {
        this.quantidadeResiduo = quantidadeResiduo;
    }

    public String getLocalArmazenagem() {
        return localArmazenagem;
    }

    public void setLocalArmazenagem(String localArmazenagem) {
        this.localArmazenagem = localArmazenagem;
    }

    public String getLonArmazenagem() {
        return lonArmazenagem;
    }

    public void setLonArmazenagem(String lonArmazenagem) {
        this.lonArmazenagem = lonArmazenagem;
    }

    public String getDescCadri() {
        return descCadri;
    }

    public void setDescCadri(String descCadri) {
        this.descCadri = descCadri;
    }

    public String getLocalDestinoFinal() {
        return localDestinoFinal;
    }

    public void setLocalDestinoFinal(String localDestinoFinal) {
        this.localDestinoFinal = localDestinoFinal;
    }

    public String getLonDestinoFinal() {
        return lonDestinoFinal;
    }

    public void setLonDestinoFinal(String lonDestinoFinal) {
        this.lonDestinoFinal = lonDestinoFinal;
    }

    public String getCadriDestinoFinal() {
        return cadriDestinoFinal;
    }

    public void setCadriDestinoFinal(String cadriDestinoFinal) {
        this.cadriDestinoFinal = cadriDestinoFinal;
    }
    
    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    } 
    
    //</editor-fold>

    
    public boolean isTipoResiduoClasseI(){
        return TipoResiduo.CLASSE_I.equals(tipoResiduo);
    }
    
    public boolean isTipoResiduoClasseII(){
        return TipoResiduo.CLASSE_II.equals(tipoResiduo);
    }
    
    @Override
    public int hashCode() {
        if (null == getId()) {
            return super.hashCode();
        } else {
            return getId().hashCode();
        }
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!this.getClass().isAssignableFrom(object.getClass()) && !(object instanceof ServicoExecutado)) {
            return false;
        }
        AbstractIdBean other = (AbstractIdBean) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && other.getId() == null)) {
            return false;
        } else if (this.getId() == null && other.getId() == null) {
            return super.equals(object);
        } else {
            return this.getId().equals(other.getId());
        }
    } 
}
