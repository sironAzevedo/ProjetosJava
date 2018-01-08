package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.UnidadeMedida;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
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
@Table(name = "OPERACAO", schema = "STAM")
public class Operacao extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_TIPO_OPERACAO = "tipoOperacao";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_PRODUTO = "produto";
    public static final String PROP_QTDE_ESTIMADA = "quantidadeEstimada";
    public static final String PROP_UNIDADE_MEDIDA = "unidadeMedida";
    public static final String PROP_DATA_INICIO = "dataInicio";
    public static final String PROP_DATA_FIM = "dataFim";
    public static final String PROP_ANEXO_UNICO = "anexoUnico";
    @Id
    @Column(name = "OPER_SQ_OPERACAO", nullable = false)
    @GeneratedValue(generator = "SQ_OPER_SQ_OPERACAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_OPER_SQ_OPERACAO", sequenceName = "STAM.SQ_OPER_SQ_OPERACAO", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoOperacao")})
    @Column(name = "OPER_IN_TIPO_OPERACAO", nullable = false)
    private TipoOperacao tipoOperacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGEC_SQ_AGENCIAMENTO", nullable = false)
    private Agenciamento agenciamento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROD_CD_ID")
    private Produto produto;
    @Column(name = "OPER_QN_ESTIMADA")
    private Double quatidadeEstimada;
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.UnidadeMedida")})
    @Column(name = "OPER_IN_MEDIDA")
    private UnidadeMedida unidadeMedida;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OPER_DT_INICIO")
    private Date dataInicio;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OPER_DT_FINAL")
    private Date dataFim;
    @Column(name = "OPER_TX_ANEXO_UNICO")
    private String anexoUnico;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getQuatidadeEstimada() {
        return quatidadeEstimada;
    }

    public void setQuatidadeEstimada(Double quatidadeEstimada) {
        this.quatidadeEstimada = quatidadeEstimada;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public String getDataHoraInicioFormatada() {
        String DtRetorno = "";
        if (this.dataInicio != null) {
            DtRetorno = SistamDateUtils.formatDate(this.dataInicio, "dd/MM/yyyy HH:mm", TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
        }
        return DtRetorno;

    }

    public void setDataInicio(Date dataInicio) {
        if (dataInicio != null && this.dataFim != null) {
            AssertUtils.assertExpression(dataInicio.before(this.dataFim), ConstantesI18N.OPERACAO_DATA_INICIO_DEVE_SER_ANTERIOR_DATA_FIM);
        }
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public String getDataHoraFimFormatada() {
        String DtRetorno = "";
        if (this.dataFim != null) {
            DtRetorno = SistamDateUtils.formatDate(this.dataFim, "dd/MM/yyyy HH:mm", TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
        }
        return DtRetorno;

    }

    public void setDataFim(Date dataFim) {
        if (dataFim != null && this.dataInicio != null) {
            AssertUtils.assertExpression(dataFim.after(this.dataInicio), ConstantesI18N.OPERACAO_DATA_FIM_DEVE_SER_POSTERIOR_DATA_INICIO);
        }
        this.dataFim = dataFim;
    }

    public String getAnexoUnico() {
        return anexoUnico;
    }

    public void setAnexoUnico(String anexoUnico) {
        this.anexoUnico = anexoUnico;
    }

    public String getQuantidadeFormatada() {
        if (quatidadeEstimada == null) {
            return null;
        }
        return String.format("%,.4f", quatidadeEstimada);
    }

    public boolean isUnidadeMedidaTonelada() {
        return UnidadeMedida.TONELADA.equals(unidadeMedida);
    }

    public boolean isUnidadeMedidaMetroCubico() {
        return UnidadeMedida.METRO_CUBICO.equals(unidadeMedida);
    }

    @Override
    public Operacao clone() {
        Operacao clone = (Operacao) super.clone();
        return clone;
    }

    @Override
    public String toString() {
        return this.produto.getNomeCompleto();
    }

    public boolean isTipoCargaCabotagem() {
        return TipoOperacao.CARGA_CABOTAGEM.equals(tipoOperacao);
    }

    public boolean isTipoDescargaCabotagem() {
        return TipoOperacao.DESCARGA_CABOTAGEM.equals(tipoOperacao);
    }
    
    public boolean isTipoCargaExportacao() {
        return TipoOperacao.CARGA_EXPORTACAO.equals(tipoOperacao);
    }

    public boolean isTipoDescargaImportacao() {
        return TipoOperacao.DESCARGA_IMPORTACAO.equals(tipoOperacao);
    }

    public boolean isTipoSemOperacaoComercial() {
        return TipoOperacao.SEM_OPERACAO_COMERCIAL.equals(tipoOperacao);
    }

    public String getQuantidadeEstimadaFormatada() {
        if (quatidadeEstimada == null) {
            return null;
        }
        return String.format("%,.4f", quatidadeEstimada);
    }
}