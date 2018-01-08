package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import javax.persistence.OneToMany;

@Entity
@Table(name = "PROTECAO_SERVICO", schema = "STAM")
public class ServicoProtecao extends AbstractIdBean<Long> implements Serializable, ServicoExecutado {

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_DATA_EXECUCAO = "dataExecucao";
    public static final String PROP_SERVICO_EXECUTADO = "servicoExecutado";
    public static final String PROP_OBSERVACAO = "observacao";
    public static final String PROP_DATA_CANCELAMENTO = "dataCancelamento";
    public static final String PROP_CHAVE_USUARIO = "chaveUsuario";
    public static final String PROP_JUSTIFICATIVA = "justificativa";
    public static final String PROP_NOVO = "novo";
    public static final String PROP_FORMULARIO_GERADO = "formularioGerado";
    private static final long serialVersionUID = 1L;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Propriedades">
    @Id
    @Column(name = "PRSE_SQ_PROTECAO", nullable = false)
    @GeneratedValue(generator = "SQ_PRSE_SQ_PROTECAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PRSE_SQ_PROTECAO", sequenceName = "STAM.SQ_PRSE_SQ_PROTECAO", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGEC_SQ_AGENCIAMENTO", nullable = false)
    private Agenciamento agenciamento;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRSE_DT_SERVICO")
    private Date dataExecucao;
    
    @Column(name = "PRSE_CD_CHAVE_INCLUSAO")
    private String chaveUsuarioInclusao;

    @Column(name = "PRSE_NM_USUARIO_INCLUSAO")
    private String nomeUsuarioInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRSE_DT_ALTERACAO")
    private Date dataAlteracao;
    
    @Column(name = "PRSE_CD_CHAVE_ALTERACAO")
    private String chaveUsuarioAlteracao;

    @Column(name = "PRSE_NM_USUARIO_ALTERACAO")
    private String nomeUsuarioAlteracao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRSE_DT_CANCELAMENTO")
    private Date dataCancelamento;
    
    @Column(name = "PRSE_CD_CHAVE_CANCELAMENTO")
    private String chaveUsuarioCancelamento;

    @Column(name = "PRSE_NM_USUARIO_CANCELAMENTO")
    private String nomeUsuarioCancelamento;
    
    @Column(name = "PRSE_TX_JUSTIFICATIVA")
    private String justificativa;

    @Column(name = "PRSE_TX_OBSERVACAO")
    private String observacao;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoAtendimentoServico")})
    @Column(name = "PRSE_IN_ATENDIMENTO_SERVICO", nullable = false)
    private TipoAtendimentoServico tipoAtendimentoServico;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "PRSE_IN_NOVO", nullable=false)
    private boolean novo = true;;
    
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = ServicoMedicoOdontologico.SERV_MED_ODONTO_SERVICO_PROTECAO)
    private List<ServicoMedicoOdontologico> servicoMedicoOdontologico = new ArrayList<ServicoMedicoOdontologico>();
    
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = ServicoTransporte.SERV_TRANSPORTE_SERVICO_PROTECAO)
    private List<ServicoTransporte> servicoTransporte = new ArrayList<ServicoTransporte>();
    
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = ServicoHospedagem.SERV_HOSPEDAGEM_SERVICO_PROTECAO)
    private List<ServicoHospedagem> servicoHospedagem = new ArrayList<ServicoHospedagem>();
    
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = ServicoAcessoPessoa.SERV_ACESSO_PESSOA_SERVICO_PROTECAO)
    private List<ServicoAcessoPessoa> servicoAcessoPessoa = new ArrayList<ServicoAcessoPessoa>();
    
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = ServicoSuprimento.SERV_SUPRIMENTOS_SERVICO_PROTECAO)
    private List<ServicoSuprimento> servicoSuprimentoAosNavios = new ArrayList<ServicoSuprimento>();
    
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = ServicoRetiradaResiduoLixo.SERVICO_RETIRADA_RESIDUO_LIXO_SERVICO_PROTECAO)
    private List<ServicoRetiradaResiduoLixo> servicoRetiradaResiduoLixo = new ArrayList<ServicoRetiradaResiduoLixo>();
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "PRSE_IN_EMAIL_ENVIADO", nullable=true)
    private Boolean emailEnviado = false;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRSE_DT_EMAIL_ENVIADO")
    private Date dataEmailEnviado;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "PRSE_IN_FORMULARIO_GERADO", nullable=true)
    private Boolean formularioGerado = false;
    
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

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public Boolean getEmailEnviado() {
        return emailEnviado;
    }

    public void setEmailEnviado(Boolean emailEnviado) {
        this.emailEnviado = emailEnviado;
    }

    public Date getDataEmailEnviado() {
        return dataEmailEnviado;
    }

    public String getDataHoraEmailEnviadoFormatado() {
        return SistamDateUtils.formatDate(dataEmailEnviado, "dd/MM/yyyy kk:mm", null);
    }

    public void setDataEmailEnviado(Date dataEmailEnviado) {
        this.dataEmailEnviado = dataEmailEnviado;
    }

    public Boolean getFormularioGerado() {
        return formularioGerado;
    }

    public void setFormularioGerado(Boolean formularioGerado) {
        this.formularioGerado = formularioGerado;
    } 

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

    public Date getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoAtendimentoServico getTipoAtendimentoServico() {
        return tipoAtendimentoServico;
    }

    public void setTipoAtendimentoServico(TipoAtendimentoServico tipoAtendimentoServico) {
        this.tipoAtendimentoServico = tipoAtendimentoServico;
    }

    public ServicoMedicoOdontologico getServicoMedicoOdontologico() {
        return servicoMedicoOdontologico.get(0);
    }

    public void setServicoMedicoOdontologico(ServicoMedicoOdontologico servicoMedicoOdontologico) {
        this.servicoMedicoOdontologico.clear();
        this.servicoMedicoOdontologico.add(servicoMedicoOdontologico);
    }

    public ServicoTransporte getServicoTransporte() {
        if (servicoTransporte.isEmpty()){
            return null;
        }
        return new ArrayList<ServicoTransporte>(servicoTransporte).get(0);
    }

    public void setServicoTransporte(ServicoTransporte servicoTransporte) {
        this.servicoTransporte = new ArrayList<ServicoTransporte>(Arrays.asList(servicoTransporte));
    }

    public ServicoHospedagem getServicoHospedagem() {
        if (servicoHospedagem.isEmpty()){
            return null;
        }
        return new ArrayList<ServicoHospedagem>(servicoHospedagem).get(0);
    }

    public void setServicoHospedagem(ServicoHospedagem servicoHospedagem) {
        this.servicoHospedagem = new ArrayList<ServicoHospedagem>(Arrays.asList(servicoHospedagem));
    }
    
    public ServicoAcessoPessoa getServicoAcessoPessoa() {
        if (servicoAcessoPessoa.isEmpty()){
            return null;
        }
        return new ArrayList<ServicoAcessoPessoa>(servicoAcessoPessoa).get(0);
    }

    public void setServicoAcessoPessoa(ServicoAcessoPessoa servicoAcessoPessoa) {
        this.servicoAcessoPessoa = new ArrayList<ServicoAcessoPessoa>(Arrays.asList(servicoAcessoPessoa));
    }
    
    public ServicoSuprimento getServicoSuprimentoAosNavios() {
        if (servicoSuprimentoAosNavios.isEmpty()){
            return null;
        }
        return new ArrayList<ServicoSuprimento>(servicoSuprimentoAosNavios).get(0);
    }

    public void setServicoSuprimentoAosNavios(ServicoSuprimento servicoSuprimentoAosNavios) {
        this.servicoSuprimentoAosNavios = new ArrayList<ServicoSuprimento>(Arrays.asList(servicoSuprimentoAosNavios));
    } 
    
    public ServicoRetiradaResiduoLixo getServicoRetiradaResiduoLixo() {
        if (servicoRetiradaResiduoLixo.isEmpty()){
            return null;
        }
        return new ArrayList<ServicoRetiradaResiduoLixo>(servicoRetiradaResiduoLixo).get(0);
    }

    public void setServicoRetiradaResiduoLixo(ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo) {
        this.servicoRetiradaResiduoLixo = new ArrayList<ServicoRetiradaResiduoLixo>(Arrays.asList(servicoRetiradaResiduoLixo));
    } 
    
    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getChaveUsuarioInclusao() {
        return chaveUsuarioInclusao;
    }

    public void setChaveUsuarioInclusao(String chaveUsuarioInclusao) {
        this.chaveUsuarioInclusao = chaveUsuarioInclusao;
    }

    public String getNomeUsuarioInclusao() {
        return nomeUsuarioInclusao;
    }

    public void setNomeUsuarioInclusao(String nomeUsuarioInclusao) {
        this.nomeUsuarioInclusao = nomeUsuarioInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
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

    public String getChaveUsuarioCancelamento() {
        return chaveUsuarioCancelamento;
    }

    public void setChaveUsuarioCancelamento(String chaveUsuarioCancelamento) {
        this.chaveUsuarioCancelamento = chaveUsuarioCancelamento;
    }

    public String getNomeUsuarioCancelamento() {
        return nomeUsuarioCancelamento;
    }

    public void setNomeUsuarioCancelamento(String nomeUsuarioCancelamento) {
        this.nomeUsuarioCancelamento = nomeUsuarioCancelamento;
    }
    
    public String getSituacao() {
        return (dataCancelamento == null) ? "Registrado" : "Cancelado";
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }
    
    @Override
    public ServicoProtecao getServicoProtecao() {
        return this;
    }
    
    public String getProtocolo(){
        return String.format("%s", String.format("%05d", getServicoProtecao().getId()));
    }
    
    public boolean isTipoAcessoPessoa(){
        return TipoAtendimentoServico.ACESSO_PESSOA.equals(getTipoAtendimentoServico());
    }
    
    public boolean isTipoHospedagem(){
        return TipoAtendimentoServico.HOSPEDAGEM.equals(getTipoAtendimentoServico());
    }
    
    public boolean isTipoTransporte(){
        return TipoAtendimentoServico.TRANSPORTE.equals(getTipoAtendimentoServico());
    }
    
    public boolean isTipoServicoSuprimento(){
        return TipoAtendimentoServico.SERVICO_SUPRIMENTO.equals(getTipoAtendimentoServico());
    }
    
    public boolean isTipoServicoRetiradaResiduoLixo(){
        return TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO.equals(getTipoAtendimentoServico());
    }
    
    //</editor-fold>
    
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

