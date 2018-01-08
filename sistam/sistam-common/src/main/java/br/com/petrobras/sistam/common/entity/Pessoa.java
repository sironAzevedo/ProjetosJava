package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean; 
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
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

/**
 * @author adzk
 */
@Entity
@Table(name = "PESSOA", schema = "STAM")
public class Pessoa extends AbstractIdBean<Long> implements Serializable {

    public static final String PROP_ID = "id";
    public static final String PROP_NOME = "nome";
    public static final String PROP_ATIVA = "ativa";
    public static final String PROP_DOCUMENTO = "documento";
    public static final String PROP_TIPO_DOCUMENTO_IDENTIFICACAO = "tipoDocumentoIdentificacao";
    public static final String PROP_CPF = "cpf";
    public static final String PROP_DATA_NASCIMENTO = "dataNascimento";
    public static final String PROP_EMPRESA = "empresa";
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    @Id
    @Column(name = "PESS_SQ_PESSOA", nullable = false)
    @GeneratedValue(generator = "SQ_PESS_SQ_PESSOA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PESS_SQ_PESSOA", sequenceName = "STAM.SQ_PESS_SQ_PESSOA", allocationSize = 1)
    private Long id;
    
    @Column(name = "PESS_NM_PESSOA", nullable = false)
    private String nome;
    
    @Column(name = "PESS_TX_DOCUMENTO", nullable = true)
    private String documento;
    
    @Column(name = "PESS_CD_CPF", nullable = true)
    private String cpf;
    
    @Column(name = "PESS_TX_NACIONALIDADE", nullable = true)
    private String nacionalidade;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PESS_DT_NASCIMENTO", nullable = true)
    private Date dataNascimento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPR_SQ_EMPRESA_PROTECAO", nullable = true)
    private EmpresaProtecao empresa;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "PESS_IN_PESSOA_ATIVA", nullable = false)
    private Boolean ativa = true;    
    
    @Column(name = "PESS_TX_TIPO_DOCUMENTO", nullable = true)
     private String tipoDocumentoIdentificacao;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
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
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public String getTipoDocumentoIdentificacao() {
        return tipoDocumentoIdentificacao;
    }

    public void setTipoDocumentoIdentificacao(String tipoDocumentoIdentificacao) {
        this.tipoDocumentoIdentificacao = tipoDocumentoIdentificacao;         
    }  

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpfComMascara() {
        return SistamUtils.formatMask("###.###.###-##", cpf);
    }

    public void setCpfComMascara(String cpf) {
        setCpf(cpf == null ? null : cpf.replaceAll("\\D", ""));
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public String getDataNascimentoFormatada(){
        return SistamDateUtils.formatDate(dataNascimento, SistamDateUtils.DATE_PATTERN, null);
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EmpresaProtecao getEmpresa() {
        return empresa;
    } 

    public void setEmpresa(EmpresaProtecao empresa) {
        this.empresa = empresa;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    
    
    //</editor-fold>

    public boolean isNova() {
        return id == null || id == 0;
    }
    
    public String getNomeEmpresa(){
        return empresa == null ? null : empresa.getRazaoSocial();
    }
    
    @Override
    public String toString(){
        return nome == null ? "" : nome;
    }
}