package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "SERVICO_SUPRIMENTO", schema = "STAM")
public class ServicoSuprimento extends AbstractIdBean<Long> implements ServicoExecutado, Serializable {

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    private static final long serialVersionUID = 1L;
    public static final String SERV_SUPRIMENTOS_ID = "id";
    public static final String SERV_SUPRIMENTOS_SERVICO_PROTECAO = "servicoProtecao";
    public static final String SERV_SUPRIMENTOS_EMPRESA_MARITIMA = "empresaMaritima";
    public static final String SERV_SUPRIMENTOS_EMPRESA_SERVICO = "empresaServico";
    public static final String SERV_SUPRIMENTOS_DATA_INICIO_OPERACAO = "dataInicioOperacao";
    public static final String SERV_SUPRIMENTOS_DATA_FIM_OPERACAO = "dataFimOperacao";
    public static final String SERV_SUPRIMENTOS_VALOR_TRANSPORTE_RODOVIARIO = "valorTransporteRodoviario";
    public static final String SERV_SUPRIMENTOS_VALOR_TRANSPORTE_MARITIMO = "valorTransporteMaritimo";
    public static final String SERV_SUPRIMENTOS_CENTRO_CUSTO = "centroCusto";
    public static final String SERV_SUPRIMENTOS_VALOR_CUSTO_OPERADOR_PORTUARIO = "custoOperadorPortuario";
    public static final String SERV_SUPRIMENTOS_VALOR_CUSTO_HORA_EXCEDENTE = "custoHoraExcedente";
    public static final String SERV_SUPRIMENTOS_VALOR_CUSTO_OHMO = "custoOGMO";
    public static final String SERV_SUPRIMENTOS_VALOR_CUSTO_OHMO_DOBRAS = "custoOgmoDobra";
    public static final String SERV_SUPRIMENTOS_VALOR_CUSTO_OPERACAOES_PORTUARIAS_ANTERIORES_DO = "portuariaAnteriorDo";
    public static final String SERV_SUPRIMENTOS_CENTRO_CUSTO_DO = "centroCustoDo";
    public static final String SERV_SUPRIMENTOS_JUSTIFICATIVA_DO = "justificativaDo";
    public static final String SERV_SUPRIMENTOS_VALOR_TOTAL_OPERACAO_PORTUARIA = "valorTotalOperacaoPortuaria";
    public static final String SERV_SUPRIMENTOS_VALOR_TOTAL_MERCADORIAS = "valorTotalMercadorias";
    public static final String SERV_SUPRIMENTOS_SERVICO_SUPRIMENTO_TRANSITO = "transitos";
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
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SESU_DT_INICIO_OPERACAO")
    private Date dataInicioOperacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SESU_DT_FIM_OPERACAO")
    private Date dataFimOperacao;
    
    @Column(name = "SESU_VL_TRANSP_RODOVIARIO")
    private BigDecimal valorTransporteRodoviario = BigDecimal.ZERO;
    
    @Column(name = "SESU_VL_TRANSP_MARITIMO")
    private BigDecimal valorTransporteMaritimo = BigDecimal.ZERO;
    
    @Column(name = "SESU_TX_CENTRO_CUSTO")
    private String centroCusto;
    
    @Column(name = "SESU_VL_OPER_PORTUARIO")
    private BigDecimal custoOperadorPortuario = BigDecimal.ZERO;
    
    @Column(name = "SESU_VL_HR_EXCEDENTE")
    private BigDecimal custoHoraExcedente = BigDecimal.ZERO;
    
    @Column(name = "SESU_VL_OGMO")
    private BigDecimal custoOGMO = BigDecimal.ZERO;
    
    @Column(name = "SESU_VL_OGMO_DOBRA")
    private BigDecimal custoOgmoDobra = BigDecimal.ZERO;
    
    @Column(name = "SESU_VL_ANTERIOR_DO")
    private BigDecimal portuariaAnteriorDo = BigDecimal.ZERO;
    
    @Column(name = "SESU_TX_CENTRO_CUSTO_DO")
    private String centroCustoDo;
    
    @Column(name = "SESU_TX_JUSTIFICATIVA_DO")
    private String justificativaDo;
    
    @Column(name = "SESU_VL_TOTAL_OP_PORTUARIA")
    private BigDecimal valorTotalOperacaoPortuaria;
    
    @Column(name = "SESU_VL_TOTAL_MERCADORIAS")
    private BigDecimal valorTotalMercadorias = BigDecimal.ZERO;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = ServicoSuprimentoTransito.SERV_SUPRIMENTO_TRANSITO_SERVICO_PROTECAO)
    private Set<ServicoSuprimentoTransito> transitos = new HashSet<ServicoSuprimentoTransito>();

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
        firePropertyChange(SERV_SUPRIMENTOS_ID, null, null);
    }

    @Override
    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
        firePropertyChange(SERV_SUPRIMENTOS_SERVICO_PROTECAO, null, null);
    }

    public EmpresaMaritima getEmpresaMaritima() {
        return empresaMaritima;
    }

    public void setEmpresaMaritima(EmpresaMaritima empresaMaritima) {
        this.empresaMaritima = empresaMaritima;
        firePropertyChange(SERV_SUPRIMENTOS_EMPRESA_MARITIMA, null, null);
    }

    public Servico getEmpresaServico() {
        return empresaServico;
    }

    public void setEmpresaServico(Servico empresaServico) {
        this.empresaServico = empresaServico;
        firePropertyChange(SERV_SUPRIMENTOS_EMPRESA_SERVICO, null, null);
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

    public BigDecimal getValorTransporteRodoviario() {
        return valorTransporteRodoviario;
    }

    public void setValorTransporteRodoviario(BigDecimal valorTransporteRodoviario) {
        this.valorTransporteRodoviario = valorTransporteRodoviario !=null ? valorTransporteRodoviario : BigDecimal.ZERO;
        firePropertyChange("valorTransporteRodoviario", null, null);
        calcularValorTotalOperacaoPortuaria();
    }

    public BigDecimal getValorTransporteMaritimo() {
        return valorTransporteMaritimo;
    }

    public void setValorTransporteMaritimo(BigDecimal valorTransporteMaritimo) {
        this.valorTransporteMaritimo = valorTransporteMaritimo !=null ? valorTransporteMaritimo : BigDecimal.ZERO;
        firePropertyChange("valorTransporteMaritimo", null, null);
        calcularValorTotalOperacaoPortuaria();
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
        firePropertyChange("centroCusto", null, null);
    }

    public BigDecimal getCustoOperadorPortuario() {
        return custoOperadorPortuario;
    }

    public void setCustoOperadorPortuario(BigDecimal custoOperadorPortuario) {
        this.custoOperadorPortuario = custoOperadorPortuario !=null  ?custoOperadorPortuario : BigDecimal.ZERO;
        firePropertyChange("custoOperadorPortuario", null, null);
        calcularValorTotalOperacaoPortuaria();
    }

    public BigDecimal getCustoHoraExcedente() {
        return custoHoraExcedente;
    }

    public void setCustoHoraExcedente(BigDecimal custoHoraExcedente) {
        this.custoHoraExcedente = custoHoraExcedente!=null ? custoHoraExcedente : BigDecimal.ZERO;
        firePropertyChange("custoHoraExcedente", null, null);
        calcularValorTotalOperacaoPortuaria();
    }

    public BigDecimal getCustoOGMO() {
        return custoOGMO;
    }

    public void setCustoOGMO(BigDecimal custoOGMO) {
        this.custoOGMO = custoOGMO !=null ? custoOGMO : BigDecimal.ZERO;
        firePropertyChange("custoOGMO", null, null);
        calcularValorTotalOperacaoPortuaria();
    }

    public BigDecimal getCustoOgmoDobra() {
        return custoOgmoDobra;
    }

    public void setCustoOgmoDobra(BigDecimal custoOgmoDobra) {
        this.custoOgmoDobra = custoOgmoDobra !=null ? custoOgmoDobra : BigDecimal.ZERO;
        firePropertyChange("custoOgmoDobra", null, null);
        calcularValorTotalOperacaoPortuaria();
    }

    public BigDecimal getPortuariaAnteriorDo() {
        return portuariaAnteriorDo;
    }

    public void setPortuariaAnteriorDo(BigDecimal portuariaAnteriorDo) {
        this.portuariaAnteriorDo = portuariaAnteriorDo !=null ? portuariaAnteriorDo : BigDecimal.ZERO;
        firePropertyChange("portuariaAnteriorDo", null, null);
        calcularValorTotalOperacaoPortuaria();
    }

    public String getCentroCustoDo() {
        return centroCustoDo;
    }

    public void setCentroCustoDo(String centroCustoDo) {
        this.centroCustoDo = centroCustoDo;
        firePropertyChange("centroCustoDo", null, null);
    }

    public String getJustificativaDo() {
        return justificativaDo;
    }

    public void setJustificativaDo(String justificativaDo) {
        this.justificativaDo = justificativaDo;
        firePropertyChange("justificativaDo", null, null);
    }

    public BigDecimal getvalorTotalOperacaoPortuaria() {
        return valorTotalOperacaoPortuaria;
    }

    public BigDecimal getValorTotalMercadorias() {
        return valorTotalMercadorias;
    }

    public void setValorTotalMercadorias(BigDecimal valorTotalMercadorias) {
        this.valorTotalMercadorias = valorTotalMercadorias != null ? valorTotalMercadorias : BigDecimal.ZERO;
        firePropertyChange("valorTotalMercadorias", null, null); 
        calcularValorTotalOperacaoPortuaria();
    }

    public List<ServicoSuprimentoTransito> getTransitos() {
        List<ServicoSuprimentoTransito> lista = new ArrayList<ServicoSuprimentoTransito>(transitos);
        Collections.sort(lista);
        return lista;
    }

//</editor-fold>
    
    public void calcularValorTotalOperacaoPortuaria() {
        valorTotalOperacaoPortuaria = BigDecimal.ZERO;
        valorTotalOperacaoPortuaria = valorTotalOperacaoPortuaria.add(valorTransporteMaritimo).add(valorTransporteRodoviario).add(custoOperadorPortuario).add(custoHoraExcedente).add(custoOGMO).add(custoOgmoDobra).add(portuariaAnteriorDo);
        firePropertyChange("valorTotalOperacaoPortuaria", null, null);
    }

    public void calcularValorTotalMercadoria() {
        valorTotalMercadorias = BigDecimal.ZERO;
        for (ServicoSuprimentoTransito transito : transitos) {
            for (ServicoSuprimentoTransitoEmpresa transitoEmpresa : transito.getTransitosEmpresas()) {
                valorTotalMercadorias = valorTotalMercadorias.add(transitoEmpresa.getValorMercadorias());
            }
        }
      setValorTotalMercadorias(valorTotalMercadorias);
    } 

    public void adicionarSuprimentoTransito(ServicoSuprimentoTransito suprimentoTransito) {
        if (suprimentoTransito != null) {
            suprimentoTransito.setServicoProtecao(this.getServicoProtecao());
            transitos.add(suprimentoTransito);
            firePropertyChange(SERV_SUPRIMENTOS_SERVICO_SUPRIMENTO_TRANSITO, null, null);
        }

    }

    public void removerSuprimentoTransito(ServicoSuprimentoTransito suprimentoTransito) {
        transitos.remove(suprimentoTransito);
        calcularValorTotalMercadoria();
        firePropertyChange(SERV_SUPRIMENTOS_SERVICO_SUPRIMENTO_TRANSITO, null, null);
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
