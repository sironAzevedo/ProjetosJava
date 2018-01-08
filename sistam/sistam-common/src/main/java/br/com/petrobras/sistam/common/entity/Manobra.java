package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "MANOBRA", schema = "STAM")
public class Manobra extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_DATA_INICIO = "dataInicio";
    public static final String PROP_DATA_TERMINO = "dataTermino";
    public static final String PROP_RESPONSAVEL_CUSTO = "responsavelCusto";
    public static final String PROP_OBSERVACAO_INTERNA = "observacaoInterna";
    public static final String PROP_PONTO_ATRACACAO_ORIGEM = "pontoAtracacaoOrigem";
    public static final String PROP_PONTO_ATRACACAO_DESTINO = "pontoAtracacaoDestino";
    public static final String PROP_PONTO_ATRACACAO_ESCALA = "pontoAtracacaoEscala";
    public static final String PROP_STATUS = "status";
    public static final String PROP_CALADO_RE = "caladoRe";
    public static final String PROP_CALADO_VANTE = "caladoVante";
    public static final String PROP_FINALIDADE_MANOBRA = "finalidadeManobra";
    public static final String PROP_SERVICOS = "servicos";
    public static final String PROP_MOTIVO_CANCELAMENTO = "motivoDoCancelamento";
    public static final String PROP_DATA_CHEGADA_DESTINO = "dataChegadaDestino";
    public static final String PROP_DATA_PARTIDA_ORIGEM = "dataPartidaOrigem";
    public static final String PROP_DATA_INICIO_OPERACAO = "dataInicioOperacao";
    public static final String PROP_DATA_FIM_OPERACAO = "dataFimOperacao";

    @Id
    @Column(name = "MANO_SQ_MANOBRA", nullable=false)
    @GeneratedValue(generator = "SQ_MANO_SQ_MANOBRA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_MANO_SQ_MANOBRA", sequenceName = "STAM.SQ_MANO_SQ_MANOBRA", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Agenciamento agenciamento;

    @Temporal(TemporalType.TIMESTAMP)    
    @Column(name = "MANO_DT_INICIO")
    private Date dataInicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MANO_DT_TERMINO")
    private Date dataTermino; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RECU_SQ_RESP_CUSTO", nullable=false)
    private ResponsavelCustoEntity responsavelCusto;
    
    @Column(name = "MANO_TX_OBSERV_INTERNA")
    private String observacaoInterna;

    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.StatusManobra")})
    @Column(name="MANO_IN_SITUACAO", nullable=false)
    private StatusManobra status;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "MANO_IN_MOVIMENTADA", nullable=false) 
    private Boolean movimentada = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POAT_SQ_PONTO_ATRAC_ORIGEM", nullable=false)
    private PontoAtracacao pontoAtracacaoOrigem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POAT_SQ_PONTO_ATRAC_DESTINO", nullable=false)
    private PontoAtracacao pontoAtracacaoDestino;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POAT_SQ_PONTO_ATRAC_ESCALA")
    private PontoAtracacao pontoAtracacaoEscala;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.FinalidadeManobra")})
    @Column(name="MANO_IN_FINALIDADE_MANOBRA", nullable=false)
    private FinalidadeManobra finalidadeManobra;

    @Column(name = "MANO_MD_CALADO_VANTE")
    private Double caladoVante;
    
    @Column(name = "MANO_MD_CALADO_RE")
    private Double caladoRe;
    
    @Temporal(TemporalType.TIMESTAMP)    
    @Column(name = "MANO_DT_CHEGADA_DESTINO")
    private Date dataChegadaDestino;
    
    @Temporal(TemporalType.TIMESTAMP)    
    @Column(name = "MANO_DT_PARTIDA_ORIGEM")
    private Date dataPartidaOrigem;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = ServicoManobra.PROP_MANOBRA)
    private Set<ServicoManobra> servicos = new  HashSet<ServicoManobra>(); 
    
    @Column(name = "MANO_TX_CANCELAMENTO")
    private String motivoDoCancelamento;
    
    @Temporal(TemporalType.TIMESTAMP)    
    @Column(name = "MANO_DT_INICIO_OPERACAO")
    private Date dataInicioOperacao;
    
    @Temporal(TemporalType.TIMESTAMP)    
    @Column(name = "MANO_DT_FIM_OPERACAO")
    private Date dataFimOperacao;
    
 
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
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
    
    public Date getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataTermino() {
        return dataTermino;
    }
    
    public String getDataHoraInicioFormatada(){
        return SistamDateUtils.formatDate(this.dataInicio, "dd/MM/yyyy HH:mm", TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
    }
    
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
    
    public String getDataHoraTerminoFormatada(){
        if (this.dataTermino !=null) {
           return SistamDateUtils.formatDate(this.dataTermino,"dd/MM/yyyy HH:mm", TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
        }
        return "";
    }
    
    public ResponsavelCustoEntity getResponsavelCusto() {
        return responsavelCusto;
    }

    public void setResponsavelCusto(ResponsavelCustoEntity responsavelCusto) {
        this.responsavelCusto = responsavelCusto;
         firePropertyChange("responsavelCusto", null, null);
    }
    
    public Boolean getMovimentada() {
        return movimentada;
    }
    
    public void setMovimentada(Boolean movimentada) {
        this.movimentada = movimentada;
    }
    
    
    public String getObservacaoInterna() {
        return observacaoInterna;
    }
    
    public void setObservacaoInterna(String observacaoInterna) {
        this.observacaoInterna = observacaoInterna;
    }
    
    
    public PontoAtracacao getPontoAtracacaoOrigem() {
        return pontoAtracacaoOrigem;
    }
    
    public void setPontoAtracacaoOrigem(PontoAtracacao pontoAtracacaoOrigem) {
        this.pontoAtracacaoOrigem = pontoAtracacaoOrigem;
        firePropertyChange("pontoAtracacaoOrigem", null, null);
    }
    
    public PontoAtracacao getPontoAtracacaoDestino() {
        return pontoAtracacaoDestino;
    }
    
    public void setPontoAtracacaoDestino(PontoAtracacao pontoAtracacaoDestino) {
        this.pontoAtracacaoDestino = pontoAtracacaoDestino;
        firePropertyChange("pontoAtracacaoDestino", null, null);
    }
    
    public FinalidadeManobra getFinalidadeManobra() {
        return finalidadeManobra;
    }
    
    public void setFinalidadeManobra(FinalidadeManobra finalidadeManobra) {
        this.finalidadeManobra = finalidadeManobra;
        firePropertyChange("finalidadeManobra", null, null);
    }
    
    public StatusManobra getStatus() {
        return status;
    }

    public void setStatus(StatusManobra status) {
        this.status = status;
        firePropertyChange("status", null, null);
    }
    
    public Double getCaladoVante() {
        return caladoVante;
    }

    public void setCaladoVante(Double caladoVante) {
        this.caladoVante = caladoVante;
        firePropertyChange("caladoVante", null, null);
    }

    public Double getCaladoRe() {
        return caladoRe;
    }

    public void setCaladoRe(Double caladoRe) {
        this.caladoRe = caladoRe;
        firePropertyChange("caladoRe", null, null);
    }   

    public Date getDataChegadaDestino() {
        return dataChegadaDestino;
    }

    public void setDataChegadaDestino(Date dataChegadaDestino) {
        this.dataChegadaDestino = dataChegadaDestino;
        firePropertyChange("dataChegadaDestino", null, null);
    }

    public Date getDataPartidaOrigem() {
        return dataPartidaOrigem;
    }

    public void setDataPartidaOrigem(Date dataPartidaOrigem) {
        this.dataPartidaOrigem = dataPartidaOrigem;
    }
    
    public List<ServicoManobra> getServicos() {
        return Collections.unmodifiableList(new ArrayList<ServicoManobra>(servicos));
    }

    public String getMotivoDoCancelamento() {
        return motivoDoCancelamento;
    }

    public void setMotivoDoCancelamento(String motivoDoCancelamento) {
        this.motivoDoCancelamento = motivoDoCancelamento;
    }

    public PontoAtracacao getPontoAtracacaoEscala() {
        return pontoAtracacaoEscala;
    }

    public void setPontoAtracacaoEscala(PontoAtracacao pontoAtracacaoEscala) {
        this.pontoAtracacaoEscala = pontoAtracacaoEscala;
        firePropertyChange("pontoAtracacaoEscala", null, null);
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

   
    //</editor-fold>
    
    /**
     * Adicionao o serviço informado, quando difrente de nulo, na manobra.
     * @param servicoManobra 
     */
    public void adicionarServico(ServicoManobra servicoManobra){
        if (servicoManobra != null){
            servicoManobra.setManobra(this);
            servicos.add(servicoManobra);
        }
        firePropertyChange(PROP_SERVICOS, null, null);
    }
    
    /**
     * Remove o serviço informado da manobra.
     * @param servicoManobra 
     */
    public void removerServico(ServicoManobra servicoManobra){
        servicos.remove(servicoManobra);
        firePropertyChange(PROP_SERVICOS, null, null);
    }
    
    /**
     * Remove todos os serviços da manobra.
     */
    public void removerTodosServicos() {
        servicos.clear();
        firePropertyChange(PROP_SERVICOS, null, null);
    }

    public boolean isSolicitada() {
        return StatusManobra.SOLICITADA.equals(this.status);
    }

    public boolean isRegistrada() {
        return StatusManobra.REGISTRADA.equals(this.status);
    }

    public boolean isPreRegistrada() {
        return StatusManobra.PRE_REGISTRADA.equals(this.status);
    }

    public boolean isPlanejada() {
        return StatusManobra.PLANEJADA.equals(this.status);
    }

    public boolean isCancelada() {
        return StatusManobra.CANCELADA.equals(this.status);
    }

    public boolean isCanceladaForaDoPrazo() {
        return StatusManobra.CANCELADA_FORA_PRAZO.equals(this.status);
    }

    public boolean isResponsavelCustoSemCusto() { 
        return "SEM CUSTO".equals(responsavelCusto); 
    }
    
    public String getMovimentadaDescricao(){
        return movimentada == null || !movimentada.booleanValue() ? "Não" : "Sim";
    }

    public boolean isNova(){
        return id == null || id == 0;
    }
    
}
