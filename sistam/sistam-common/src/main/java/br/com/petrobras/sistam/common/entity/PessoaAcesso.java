package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "PESSOA_ACESSO", schema = "STAM")
public class PessoaAcesso extends AbstractIdBean<Long> implements Serializable, Comparable<PessoaAcesso> {
    
    //<editor-fold defaultstate="collapsed" desc="Constantes">
    
    public static final String PROP_ID = "id";
    public static final String PROP_PESSOA = "pessoa";
    public static final String PROP_VOLUME= "volume";
    public static final String PROP_BAGAGEM= "bagagem";
    public static final String PROP_FORMULARIO_GERADO_POLICIA= "formularioGeradoPolicia";
    public static final String PROP_FORMULARIO_GERADO_RECEITA= "formularioGeradoReceita";
    public static final String PROP_ATIVO = "ativo";
    public static final String PROP_RESPONSAVEL = "responsavel";
    public static final String SERV_PROTECAO = "servicoProtecao";
    public static final String SERV_RESPONSAVEL = "responsavel";

    private static final long serialVersionUID = 1L;  
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Propriedades">
    @Id
    @Column(name = "PEAC_SQ_PESSOA", nullable=false)
    @GeneratedValue(generator = "SQ_PEAC_SQ_PESSOA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PEAC_SQ_PESSOA", sequenceName = "STAM.SQ_PEAC_SQ_PESSOA", allocationSize = 1)
    private Long id;
    
    @Column(name="PEAC_NR_VOLUME")
    private Integer volume;
    
    @Column(name="PEAC_TX_BAGAGEM")
    private String bagagem;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name="PEAC_IN_GERADO_PF")
    private boolean formularioGeradoPolicia;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name="PEAC_IN_GERADO_RF")
    private boolean formularioGeradoReceita;
        
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name="PEAC_IN_GERADO_RF_EMB_DES")
    private boolean formularioGeradoTripulante = false;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name="PEAC_IN_GERADO_RF_ACESSO")
    private boolean formularioGeradoPrestacaoServico = false;
    
    @Column(name = "PEAC_NM_PESSOA", nullable = false) 
    private String nome;
    
    @Column(name = "PEAC_TX_DOCUMENTO") 
    private String documento;
    
    @Column(name = "PEAC_TX_TIPO_DOCUMENTO")
     private String tipoDocumentoIdentificacao;
    
    @Column(name = "PEAC_CD_CPF") 
    private String cpf;
    
    @Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "PEAC_DT_NASCIMENTO") 
    private Date dataNascimento;
    
    @Column(name = "PEAC_TX_EMPRESA") 
    private String nomeEmpresa;
    
    @Column(name = "PEAC_NR_CNPJ") 
    private String cnpjEmpresa;
    
    @Column(name = "PEAC_TX_NACIONALIDADE") 
    private String nacionalidade;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRSE_SQ_PROTECAO", nullable = false)
    private ServicoProtecao servicoProtecao;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "PEAC_IN_ATIVO", nullable=false) 
    private boolean ativo;
    
    @Column(name = "PEAC_NM_RESPONSAVEL") 
    private String responsavel;
   
    
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, null);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
        firePropertyChange("documento", null, null);
    }

    public String getTipoDocumentoIdentificacao() {
        return tipoDocumentoIdentificacao;
    }

    public void setTipoDocumentoIdentificacao(String tipoDocumentoIdentificacao) {
        this.tipoDocumentoIdentificacao = tipoDocumentoIdentificacao;
        firePropertyChange("tipoDocumentoIdentificacao", null, null);
    } 

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
        firePropertyChange("cpf", null, null);
    }
    
    public String getCpfComMascara() { 
        return SistamUtils.formatMask("###.###.###-##", cpf);
    }

    public void setCpfComMascara(String cpf) {
        setCpf(cpf == null ? null : cpf.replaceAll("\\D", ""));
        firePropertyChange("cpfComMascara", null, null);
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public String getDataNascimentoFormatada() {
        return SistamDateUtils.formatDate(dataNascimento, SistamDateUtils.DATE_PATTERN, null);
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        firePropertyChange("dataNascimento", null, null);
        firePropertyChange("dataNascimentoFormatada", null, null);
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        firePropertyChange("nomeEmpresa", null, null);
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
        firePropertyChange("cnpjEmpresa", null, null);
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
        firePropertyChange("nacionalidade", null, null);
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getBagagem() {
        return bagagem;
    }

    public void setBagagem(String bagagem) {
        this.bagagem = bagagem;
    }

    public boolean isFormularioGeradoPolicia() {
        return formularioGeradoPolicia;
    }

    public void setFormularioGeradoPolicia(boolean formularioGeradoPolicia) {
        this.formularioGeradoPolicia = formularioGeradoPolicia;
    }
    
    public boolean isFormularioGeradoReceita() {
        return formularioGeradoReceita;
    }

    public void setFormularioGeradoReceita(boolean formularioGeradoReceita) {
        this.formularioGeradoReceita = formularioGeradoReceita;
    }

    public boolean isFormularioGeradoTripulante() {
        return formularioGeradoTripulante;
    }

    public void setFormularioGeradoTripulante(boolean formularioGeradoTripulante) {
        this.formularioGeradoTripulante = formularioGeradoTripulante;
    }

    public boolean isFormularioGeradoPrestacaoServico() {
        return formularioGeradoPrestacaoServico;
    }

    public void setFormularioGeradoPrestacaoServico(boolean formularioGeradoPrestacaoServico) {
        this.formularioGeradoPrestacaoServico = formularioGeradoPrestacaoServico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
    //</editor-fold>
    
    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int compareTo(PessoaAcesso o) {
        if(StringUtils.isBlank(this.getNome())){
            return -1;
        }
        return this.getNome().compareToIgnoreCase(o.getNome());
    }
    
}
