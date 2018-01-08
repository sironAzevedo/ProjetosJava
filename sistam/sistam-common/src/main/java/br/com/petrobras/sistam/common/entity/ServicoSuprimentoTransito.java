package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoMaterial;
import br.com.petrobras.sistam.common.enums.TipoMercadorias;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 *
 */
@Entity
@Table(name = "SUPRIMENTO_TRANSITO", schema = "STAM")
public class ServicoSuprimentoTransito extends AbstractIdBean<Long> implements Serializable, Comparable<ServicoSuprimentoTransito> {

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    public static final String SERV_SUPRIMENTO_TRANSITO_ID = "id";
    public static final String SERV_SUPRIMENTO_TRANSITO_SERVICO_PROTECAO = "servicoProtecao";
    public static final String SERV_SUPRIMENTO_TRANSITO_COMPANHIA_DOCAS = "companhiaDocas";
    public static final String SERV_SUPRIMENTO_TRANSITO_TERMINAL = "terminal";
    public static final String SERV_SUPRIMENTO_TRANSITO_DESCRICAO_TERMINAL = "descricaoTerminal";
    public static final String SERV_SUPRIMENTO_TRANSITO_TIPO_ACESSO = "TipoMercadorias";
    public static final String SERV_SUPRIMENTO_TRANSITO_TIPO_MATERIAL = "TipoMaterial";
    public static final String SERV_SUPRIMENTO_TRANSITO_FORMULARIO_GERADO = "formularioGeradoReceitaFederal";
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA = "transitosEmpresas";
    public static final String SERV_SUPRIMENTO_TRANSITO_VEICULO = "transitosVeiculos";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Propriedades">    
    @Id
    @Column(name = "SUTR_SQ_SOLICITACAO", nullable = false)
    @GeneratedValue(generator = "SQ_SUTR_SQ_SOLICITACAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_SUTR_SQ_SOLICITACAO", sequenceName = "STAM.SQ_SUTR_SQ_SOLICITACAO", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRSE_SQ_PROTECAO", nullable = false)
    private ServicoProtecao servicoProtecao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRSE_SQ_PROTECAO", nullable = false, insertable = false, updatable = false)
    private ServicoSuprimento servicoSuprimento;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "SUTR_IN_COMPANHIA_DOCAS", nullable = false)
    private boolean companhiaDocas;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "SUTR_IN_TERMINAL", nullable = false)
    private boolean terminal;
    
    @Column(name = "SUTR_TX_TERMINAL")
    private String descricaoTerminal;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "SUTR_IN_FORMULARIO_GERADO")
    private boolean formularioGeradoReceitaFederal;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoMercadorias")})
    @Column(name = "SUTR_IN_TIPO_ACESSO", nullable = true)
    private TipoMercadorias tipoMercadorias;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoMaterial")})
    @Column(name = "SUTR_IN_TIPO_MATERIAL", nullable = true)
    private TipoMaterial tipoMaterial; 
    

    @OneToMany(fetch = FetchType.LAZY, mappedBy = ServicoSuprimentoTransitoEmpresa.SERV_SUPRIMENTO_TRANSITO_EMPRESA_SOLICITACAO_TRANSITO, cascade = CascadeType.REMOVE) 
    private Set<ServicoSuprimentoTransitoEmpresa> transitosEmpresas = new HashSet<ServicoSuprimentoTransitoEmpresa>();  
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = ServicoSuprimentoTransitoVeiculo.SERV_SUPRIMENTO_TRANSITO_VEICULO_SOLICITACAO_TRANSITO, cascade = CascadeType.REMOVE) 
    private Set<ServicoSuprimentoTransitoVeiculo> transitosVeiculos = new HashSet<ServicoSuprimentoTransitoVeiculo>();

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public void setId(Long id) {
        this.id = id;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_ID, null, null);
    }

    @Override
    public Long getId() {
        return id;
    }

    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
    }

    public String getDescricaoTerminal() {
        return descricaoTerminal;
    }

    public void setDescricaoTerminal(String descricaoTerminal) {
        this.descricaoTerminal = descricaoTerminal;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_DESCRICAO_TERMINAL, null, null);
    }

    public boolean isCompanhiaDocas() {
        return companhiaDocas;
    }

    public void setCompanhiaDocas(boolean companhiaDocas) {
        this.companhiaDocas = companhiaDocas;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_DESCRICAO_TERMINAL, null, null);
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_TERMINAL, null, null);
        if (!terminal) {
            setDescricaoTerminal(null);
        }
    }

    public TipoMercadorias getTipoMercadorias() {
        return tipoMercadorias;
    }

    public void setTipoMercadorias(TipoMercadorias tipoMercadorias) {
        this.tipoMercadorias = tipoMercadorias;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_TIPO_ACESSO, null, null);
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_TIPO_MATERIAL, null, null);
    }

    public boolean isFormularioGeradoReceitaFederal() {
        return formularioGeradoReceitaFederal;
    }

    public void setFormularioGeradoReceitaFederal(boolean formularioGeradoReceitaFederal) {
        this.formularioGeradoReceitaFederal = formularioGeradoReceitaFederal;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_FORMULARIO_GERADO, null, null);
    }

    public ServicoSuprimento getServicoSuprimento() {
        return servicoSuprimento;
    }
    
    public List<ServicoSuprimentoTransitoEmpresa> getTransitosEmpresas() {
        List<ServicoSuprimentoTransitoEmpresa> lista = new ArrayList<ServicoSuprimentoTransitoEmpresa>(transitosEmpresas);
        Collections.sort(lista);
        return lista;

    }

    public void setTransitosEmpresas(Set<ServicoSuprimentoTransitoEmpresa> transitosEmpresas) {
        this.transitosEmpresas = transitosEmpresas;
    }

    public List<ServicoSuprimentoTransitoVeiculo> getTransitosVeiculos() {
        List<ServicoSuprimentoTransitoVeiculo> lista = new ArrayList<ServicoSuprimentoTransitoVeiculo>(transitosVeiculos);
        Collections.sort(lista);
        return lista;
    }

    public void setTransitosVeiculos(Set<ServicoSuprimentoTransitoVeiculo> transitosVeiculos) {
        this.transitosVeiculos = transitosVeiculos;
    }

    //</editor-fold> 
    
    public String getLocal() {
        if (isTerminal() && isCompanhiaDocas()) {
            return "cia Docas" + "/" + descricaoTerminal;
        } else if (isTerminal()) {
            return descricaoTerminal;
        } else {
            return "Cia Docas";
        }
    }

    public String getNomeFornecedor() {
        StringBuilder empresa = new StringBuilder();
        for (ServicoSuprimentoTransitoEmpresa transitoEmpresa : transitosEmpresas) {
            empresa.append(transitoEmpresa.getNomePrestadorServico());
            if (transitosEmpresas.size() > 1) {
                empresa.append("/");
            }
        }
        if (transitosEmpresas.size() > 1) {
            empresa = new StringBuilder(empresa.substring(0, (empresa.length()-1)));
        }
        return empresa.toString();
    }

    public void adicionarSuprimentoTransitoFornecedor(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa) {
        if (suprimentoTransitoEmpresa != null) {
            suprimentoTransitoEmpresa.setSolicitacaoTransito(this);
            this.transitosEmpresas.add(suprimentoTransitoEmpresa);
        }
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_EMPRESA, null, null);
    }

    public void removerSuprimentoTransitoFornecedo(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa) {
        if (suprimentoTransitoEmpresa != null) {
            suprimentoTransitoEmpresa.setSolicitacaoTransito(null);
            this.transitosEmpresas.remove(suprimentoTransitoEmpresa);
        }
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_EMPRESA, null, null);
    }

    public void limparSuprimentoTransitoFornecedo() {
        this.transitosEmpresas.clear();
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_EMPRESA, null, null);
    }

    public void adicionarSuprimentoTransitoVeiculo(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo) {
        if (suprimentoTransitoVeiculo != null) {
            suprimentoTransitoVeiculo.setSolicitacaoTransito(this);
            this.transitosVeiculos.add(suprimentoTransitoVeiculo);
        }
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_VEICULO, null, null);
    }

    public void removerSuprimentoTransitoVeiculo(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo) {
        if (suprimentoTransitoVeiculo != null) {
            suprimentoTransitoVeiculo.setSolicitacaoTransito(null);
            this.transitosVeiculos.remove(suprimentoTransitoVeiculo);
        }
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_VEICULO, null, null);

    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public int compareTo(ServicoSuprimentoTransito o) {
        if (StringUtils.isBlank(this.getDescricaoTerminal())) {
            return -1;
        }
        return 1;
    }
}