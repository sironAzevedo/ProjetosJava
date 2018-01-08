package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * @author adzk
 */
@Entity
@Table(name = "EMPRESA_PROTECAO", schema = "STAM")
public class EmpresaProtecao extends AbstractIdBean<Long> implements Serializable {

    public static final String PROP_ID = "id";
    public static final String PROP_CNPJ = "cnpj";
    public static final String PROP_RAZAO_SOCIAL = "razaoSocial";
    public static final String PROP_SERVICOS = "servicos";
    public static final String PROP_ATIVA = "ativa";
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    @Id
    @Column(name = "EMPR_SQ_EMPRESA_PROTECAO", nullable = false)
    @GeneratedValue(generator = "SQ_EMPR_SQ_EMPRESA_PROTECAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_EMPR_SQ_EMPRESA_PROTECAO", sequenceName = "STAM.SQ_EMPR_SQ_EMPRESA_PROTECAO", allocationSize = 1)
    private Long id;
    
    @Column(name = "EMPR_NR_CNPJ", nullable = false)
    private String cnpj;
    
    @Column(name = "EMPR_NM_RAZAO_SOCIAL", nullable = false)
    private String razaoSocial;

    @Column(name = "EMPR_TX_NR_TELEFONE")
    private String telefone;
    
    @Column(name = "EMPR_TX_EMAIL")
    private String email;
    
    @Column(name = "EMPR_TX_CIDADE")
    private String cidade;
    
    @Column(name = "EMPR_CD_ESTADO")
    private String estado;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = EmpresaProtecaoServico.PROP_EMPRESA)
    private Set<EmpresaProtecaoServico> servicos = new HashSet<EmpresaProtecaoServico>(0);
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "EMPR_IN_EMPRESA_ATIVA", nullable = false)
    private Boolean ativa = true;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpjComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpj);
    }

    public void setCnpjComMascara(String cnpj) {
        setCnpj(cnpj == null ? null : cnpj.replaceAll("\\D", ""));
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

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Set<EmpresaProtecaoServico> getServicos() {
        return servicos;
    }

    public List<EmpresaProtecaoServico> getServicosAsList() {
        return new LinkedList<EmpresaProtecaoServico>(servicos);
    }

    public void setServicos(Set<EmpresaProtecaoServico> servicos) {
        this.servicos = servicos;
    }
    //</editor-fold>

    public String getDescricaoServicos() {
        List<TipoServicoProtecao> tipos = new ArrayList<TipoServicoProtecao>();
        List<EmpresaProtecaoServico> servicosOrdenado = new ArrayList<EmpresaProtecaoServico>(servicos);
        Collections.sort(servicosOrdenado);
        StringBuilder descricao = new StringBuilder();
        for (EmpresaProtecaoServico servico : servicosOrdenado) {
            if(!tipos.contains(servico.getTipo())){
                if (descricao.length() != 0) {
                    descricao.append(", ");
                }
                descricao.append(servico.getTipo().getPorExtenso());
                tipos.add(servico.getTipo());
            }
        }
        return descricao.toString();
    }

    public void adicionarTipoServico(TipoServicoProtecao tipo) {
        EmpresaProtecaoServico servico = new EmpresaProtecaoServico();
        servico.setTipo(tipo);
        servico.setEmpresa(this);
        this.servicos.add(servico);
        firePropertyChange("servicos", null, null);
        firePropertyChange("servicosAsList", null, null);
    }

    public boolean isNova() {
        return id == null || id == 0;
    }
    
    @Override
    public String toString(){
        return razaoSocial == null ? "" : razaoSocial;
    }
}