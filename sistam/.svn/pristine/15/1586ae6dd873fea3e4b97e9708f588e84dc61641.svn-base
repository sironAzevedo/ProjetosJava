package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "EMPRESA_MARITIMA", schema = "STAM")
public class EmpresaMaritima extends AbstractIdBean<Long> implements Serializable {

    public static final String PROP_ID = "id";
    public static final String PROP_CNPJ = "cnpj";
    public static final String PROP_RAZAO_SOCIAL = "razaoSocial";    
    public static final String PROP_EMAIL = "email";
    public static final String PROP_TELEFONE = "telefone";
    public static final String PROP_SERVICOS = "servicos";
    public static final String PROP_AGENCIA = "agencia";
    public static final String PROP_ATIVO = "ativo";
    public static final String PROP_CIDADE = "cidade";
    public static final String PROP_ESTADO = "estado";
    

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "EMMA_SQ_EMPRESA_MARITIMA", nullable=false)
    @GeneratedValue(generator = "SQ_EMMA_SQ_EMPRESA_MARITIMA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_EMMA_SQ_EMPRESA_MARITIMA", sequenceName = "STAM.SQ_EMMA_SQ_EMPRESA_MARITIMA", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEN_SQ_AGENCIA", nullable=false)
    private Agencia agencia;
    
    @Column(name = "EMMA_NR_CNPJ", nullable=false)
    private String cnpj;

    @Column(name = "EMMA_NM_RAZAO_SOCIAL", nullable=false)
    private String razaoSocial;
    
    @Column(name = "EMMA_TX_EMAIL", nullable=true)
    private String email;
    
    @Column(name="EMMA_TX_NR_TELEFINE", nullable=true)
    private String telefone;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Servico.PROP_EMPRESA)
    private Set<Servico> servicos = new  HashSet<Servico>(); 
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "EMMA_IN_EMPRESA_ATIVA", nullable=false) 
    private Boolean ativo = true;
    
    @Column(name = "EMMA_TX_CIDADE", nullable=true)
    private String cidade;
    
    @Column(name = "EMMA_CD_ESTADO", nullable=true)
    private String estado;
    
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCnpj() {
        return cnpj;
    }
    
    public String getCnpjComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpj);
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public void setCnpjComMascara(String cnpj) {
        if (cnpj == null) {
            setCnpj(null);
        } else {
            setCnpj(cnpj.replaceAll("\\D", ""));
        }    
    }
    
    public String getRazaoSocial() {
        return razaoSocial;
    }
    
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
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
    
    public List<Servico> getServicos() {
        return Collections.unmodifiableList(new ArrayList<Servico>(servicos));
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    
    public boolean getAtivo() {
        return ativo != null ? ativo : false;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    //</editor-fold>
    
    public void adicionarServico(Servico servico){
        if (servico != null){
            servico.setEmpresa(this);
            servicos.add(servico);
        }
        firePropertyChange(PROP_SERVICOS, null, null);
    }
    
      public void removerServico(Servico servico){
        if (servico != null){
            servico.setEmpresa(null);
            servicos.remove(servico);
        }
        firePropertyChange(PROP_SERVICOS, null, null);
    }
      
    @Override
    public String toString() {
        return razaoSocial;
    }


    
}
