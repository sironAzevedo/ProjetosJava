package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoAcesso;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoNacionalidade;
import br.com.petrobras.sistam.common.enums.TipoSolicitacaoTransito;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "SERVICO_ACESSO_PESSOA", schema = "STAM")
public class ServicoAcessoPessoa extends AbstractIdBean<Long> implements ServicoExecutado, Serializable {

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    private static final long serialVersionUID = 1L;
    public static final String SERV_ACESSO_PESSOA_ID = "id";
    public static final String SERV_ACESSO_PESSOA_SERVICO_PROTECAO = "servicoProtecao";
    public static final String SERV_ACESSO_PESSOA_COMPANHIA = "companhia";  
    public static final String SERV_ACESSO_PESSOA_NOME_TERMINAL = "nomeTerminal";
    public static final String SERV_ACESSO_PESSOA_TIPO_SOLI_TRANSITO = "tipoSolicitacaoTransito";
    public static final String SERV_ACESSO_PESSOA_TIPO_ACESSO = "tipoAcesso";
    public static final String SERV_ACESSO_PESSOA_TIPO_CATEGORIA = "tipoCategoria";
    public static final String SERV_ACESSO_PESSOA_TIPO_NACIONALIDADE = "tipoNacionalidade";
    public static final String SERV_ACESSO_PESSOA_COMPANHIA_DOCAS = "companhiaDocas";
    public static final String SERV_ACESSO_PESSOA_TERMINAL = "terminal";
    public static final String SERV_ACESSO_PESSOA_DESCRICAO_TERMINAL = "descricaoTerminal";
    public static final String SERV_ACESSO_PESSOA_PESSOAS = "pessoas";
    public static final String SERV_ACESSO_PESSOA_PESTADOR_SERVICO_NOME= "nomePrestadorServico";
    public static final String SERV_ACESSO_PESSOA_PESTADOR_SERVICO_CNPJ= "cnpjPrestadorServico";
    public static final String SERV_ACESSO_PESSOA_PESTADOR_SERVICO_ATIVIDADE= "pestadorServicoAtividade";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Propriedades">
    @Id
    @Column(name = "PRSE_SQ_PROTECAO", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRSE_SQ_PROTECAO", nullable = false, insertable = false, updatable = false)
    private ServicoProtecao servicoProtecao;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoSolicitacaoTransito")})
    @Column(name = "SEAP_IN_TIPO_SOLI_TRANSITO", nullable = true)
    private TipoSolicitacaoTransito tipoSolicitacaoTransito;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoAcesso")})
    @Column(name = "SEAP_IN_TIPO_ACESSO", nullable = true)
    private TipoAcesso tipoAcesso;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoCategoria")})
    @Column(name = "SEAP_IN_TIPO_CATEGORIA", nullable = true)
    private TipoCategoria tipoCategoria;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoNacionalidade")})
    @Column(name = "SEAP_IN_TIPO_NACIONALIDADE", nullable = true)
    private TipoNacionalidade tipoNacionalidade;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "SEAP_IN_COMPANHIA_DOCAS", nullable = false)
    private boolean companhiaDocas;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "SEAP_IN_TERMINAL", nullable = false)
    private boolean terminal;
    
    @Column(name = "SEAP_TX_TERMINAL")
    private String descricaoTerminal;
    
    @Column(name = "SEAP_TX_EMPRESA_PROTECAO")
    private String nomePrestadorServico;
    
    @Column(name = "SEAP_NR_CNPJ")
    private String cnpjPrestadorServico;
    
    @Column(name = "SEAP_TX_NR_TELEFONE_EMPRESA")
    private String telefonePrestadorServico;
    
    @Column(name = "SEAP_TX_ATIVIDADE_BORDO")
    private String prestadorServicoAtividadeBordo;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = PessoaAcesso.SERV_PROTECAO)
    private Set<PessoaAcesso> pessoas = new HashSet<PessoaAcesso>();
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        firePropertyChange(SERV_ACESSO_PESSOA_ID, null, null);
    }

    public String getNomePrestadorServico() {
        return nomePrestadorServico;
    }
    
    public String getCnpjPrestadorServicoComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpjPrestadorServico);
    }

    public void setCnpjPrestadorServicoComMascara(String cnpj) {
        setCnpjPrestadorServico(cnpj == null ? null : cnpj.replaceAll("\\D", ""));
    }

    public void setNomePrestadorServico(String nomePrestadorServico) {
        this.nomePrestadorServico = nomePrestadorServico;
        firePropertyChange("nomePrestadorServico", null, null);
    }

    public String getTelefonePrestadorServico() {
        return telefonePrestadorServico;
    }

    public void setTelefonePrestadorServico(String telefonePrestadorServico) {
        this.telefonePrestadorServico = telefonePrestadorServico;
        firePropertyChange("telefonePrestadorServico", null, null);
    }
    
    public String getCnpjPrestadorServico() {
        return cnpjPrestadorServico;
    }

    public void setCnpjPrestadorServico(String cnpjPrestadorServico) {
        this.cnpjPrestadorServico = cnpjPrestadorServico;
        firePropertyChange("cnpjPrestadorServico", null, null);
    }
    
    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
        firePropertyChange(SERV_ACESSO_PESSOA_SERVICO_PROTECAO, null, null);
    }

    public TipoSolicitacaoTransito getTipoSolicitacaoTransito() {
        return tipoSolicitacaoTransito;
    }

    public void setTipoSolicitacaoTransito(TipoSolicitacaoTransito tipoSolicitacaoTransito) {
        this.tipoSolicitacaoTransito = tipoSolicitacaoTransito;
        firePropertyChange(SERV_ACESSO_PESSOA_TIPO_SOLI_TRANSITO, null, null);
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
        firePropertyChange(SERV_ACESSO_PESSOA_TIPO_ACESSO, null, null);
    }

    public TipoCategoria getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(TipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
        firePropertyChange(SERV_ACESSO_PESSOA_TIPO_CATEGORIA, null, null);
    }

    public TipoNacionalidade getTipoNacionalidade() {
        return tipoNacionalidade;
    }

    public void setTipoNacionalidade(TipoNacionalidade tipoNacionalidade) {
        this.tipoNacionalidade = tipoNacionalidade;
        firePropertyChange(SERV_ACESSO_PESSOA_TIPO_NACIONALIDADE, null, null);
    }

    public boolean isCompanhiaDocas() {
        return companhiaDocas;
    }

    public void setCompanhiaDocas(boolean companhiaDocas) {
        this.companhiaDocas = companhiaDocas;
        firePropertyChange(SERV_ACESSO_PESSOA_DESCRICAO_TERMINAL, null, null);
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
        firePropertyChange(SERV_ACESSO_PESSOA_TERMINAL, null, null);
        if(!terminal){
            setDescricaoTerminal(null);
        }
    }

    public String getDescricaoTerminal() {
        return descricaoTerminal;
    }

    public void setDescricaoTerminal(String descricaoTerminal) {
        this.descricaoTerminal = descricaoTerminal;
        firePropertyChange(SERV_ACESSO_PESSOA_DESCRICAO_TERMINAL, null, null);
    }

    public List<PessoaAcesso> getPessoasAsList() {
        List<PessoaAcesso> lista = new ArrayList<PessoaAcesso>(pessoas);
        Collections.sort(lista);
        return lista;
    }

    public Set<PessoaAcesso> getPessoas() {
        return pessoas;
    }
    
    public String getPrestadorServicoAtividadeBordo() {
        return prestadorServicoAtividadeBordo;
    }

    public void setPrestadorServicoAtividadeBordo(String prestadorServicoAtividadeBordo) {
        this.prestadorServicoAtividadeBordo = prestadorServicoAtividadeBordo;
        firePropertyChange(SERV_ACESSO_PESSOA_PESTADOR_SERVICO_ATIVIDADE, null, null);
    }
    //</editor-fold>
    
    public boolean getNacionalidadeBrasileira(){
        return tipoNacionalidade.equals(TipoNacionalidade.BRASILEIROS);
    }
    
    public void adicionarPessoas(List<PessoaAcesso> pessoas){
        for(PessoaAcesso pessoa : pessoas){
            pessoa.setAtivo(true);
            adicionarPessoa(pessoa);
        }
    }
    
    public void adicionarPessoa(PessoaAcesso pessoa) {
        if (pessoa != null) {
            pessoa.setServicoProtecao(this.getServicoProtecao());
            pessoas.add(pessoa);
            firePropertyChange(SERV_ACESSO_PESSOA_PESSOAS, null, null);
            firePropertyChange("pessoasAsList", null, null);
        }
    }

    public void removerPessoa(PessoaAcesso pessoa) {
        pessoas.remove(pessoa);
        firePropertyChange(SERV_ACESSO_PESSOA_PESSOAS, null, null);
        firePropertyChange("pessoasAsList", null, null);
    }
    
    public boolean isTipoAcessoAcesso(){
        return tipoAcesso != null && TipoAcesso.ACESSO.equals(tipoAcesso);
    }
    
    public boolean isTipoAcessoEmbarque(){
        return tipoAcesso != null && TipoAcesso.EMBARQUE.equals(tipoAcesso);
    }
    
    public boolean isTipoAcessoDesembarque(){
        return tipoAcesso != null && TipoAcesso.DESEMBARQUE.equals(tipoAcesso);
    }
    
    public boolean isTipoAcessoVisitante(){
        return tipoAcesso != null && TipoAcesso.VISITA.equals(tipoAcesso);
    }
    
    public boolean isTipoCategoriaVisitante(){
        return tipoCategoria != null && TipoCategoria.VISITANTES.equals(tipoCategoria);
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