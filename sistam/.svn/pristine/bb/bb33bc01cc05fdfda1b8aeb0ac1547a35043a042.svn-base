package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoServico;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@Table(name = "EMPRESA_MANOBRA", schema = "STAM")
public class ServicoManobra extends AbstractIdBean<Long> {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_ID = "id";
    public static final String PROP_MANOBRA = "manobra";
    public static final String PROP_EMPRESA_MARITIMA = "empresaMaritima";
    public static final String PROP_TIPO_SERVICO = "tipoDoServico";
    public static final String PROP_DATA_PROGRAMADA = "dataProgramada";
    public static final String PROP_DATA_ENVIO = "dataEnvio";
    public static final String PROP_OBSERVACAO = "observacao";
    public static final String PROP_RESPONSAVEIS = "responsaveis";
    
    @Id
    @Column(name = "EMAN_SQ_MANOBRA", nullable=false)
    @GeneratedValue(generator = "SQ_EMAN_SQ_MANOBRA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_EMAN_SQ_MANOBRA", sequenceName = "STAM.SQ_EMAN_SQ_MANOBRA", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MANO_SQ_MANOBRA", nullable=false)
    private Manobra manobra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMMA_SQ_EMPRESA_MARITIMA", nullable=false)
    private EmpresaMaritima empresaMaritima;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoServico")})
    @Column(name="EMAN_IN_SERVICO", nullable=false)
    private TipoServico tipoDoServico;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMAN_DT_PROGRAMADA", nullable=true)
    private Date dataProgramada;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMAN_DT_ENVIO", nullable=true)
    private Date dataEnvio;
    
    @Column(name = "EMAN_TX_OBSERVACAO", nullable=true)
    private String observacao;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = ServicoResponsavel.PROP_SERVICO_MANOBRA)
    private Set<ServicoResponsavel> responsaveis = new  HashSet<ServicoResponsavel>(); 

    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public Manobra getManobra() {
        return manobra;
    }
    
    public void setManobra(Manobra manobra) {
        this.manobra = manobra;
    }
    
    public EmpresaMaritima getEmpresaMaritima() {
        return empresaMaritima;
    }
    
    public void setEmpresaMaritima(EmpresaMaritima empresaMaritima) {
        this.empresaMaritima = empresaMaritima;
        firePropertyChange(PROP_EMPRESA_MARITIMA, null, null);
    }
    
    public TipoServico getTipoDoServico() {
        return tipoDoServico;
    }
    
    public void setTipoDoServico(TipoServico tipoDoServico) {
        this.tipoDoServico = tipoDoServico;
        firePropertyChange("tipoDoServico", null, null);
    }
    
    public Date getDataProgramada() {
        return dataProgramada;
    }
    
    public void setDataProgramada(Date dataProgramada) {
        this.dataProgramada = dataProgramada;
    }
    
    public Date getDataEnvio() {
        return dataEnvio;
    }
    
    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
    
    public String getObservacao() {
        return observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<ServicoResponsavel> getResponsaveis() {
        return Collections.unmodifiableList(new ArrayList<ServicoResponsavel>(responsaveis));
    }
    
    /**
     * Adiciona o responsável informado, quando diferente de nulo, no serviço.
     * @param servicoResponsavel 
     */
    public void adicionarResponsavel(ServicoResponsavel servicoResponsavel){
        if (servicoResponsavel != null){
            servicoResponsavel.setServicoManobra(this);
            responsaveis.add(servicoResponsavel);
        }
        firePropertyChange("responsaveis", null, null);
    }
    
    /**
     * Remove o responsável informado do serviço.
     * @param servicoResponsavel 
     */
    public void removerResponsavel(ServicoResponsavel servicoResponsavel){
        responsaveis.remove(servicoResponsavel);
        firePropertyChange("responsaveis", null, null);
    }
    
    /**
     * Remove todos os responsáveis da serviço.
     */
    public void removerTodosResponsaveis(){
        responsaveis.clear();
        firePropertyChange("responsaveis", null, null);
    }
    
    @Override
    public ServicoManobra clone(){
        ServicoManobra clone = (ServicoManobra)super.clone();
        clone.responsaveis = new HashSet<ServicoResponsavel>(this.responsaveis);
        return clone;
    }

    @Override
    public int hashCode() {
        if (id != null){
            return super.hashCode();
        }
        
        int hash = 7;
        hash = 11 * hash + (this.manobra != null ? this.manobra.hashCode() : 0);
        hash = 11 * hash + (this.empresaMaritima != null ? this.empresaMaritima.hashCode() : 0);
        hash = 11 * hash + (this.tipoDoServico != null ? this.tipoDoServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
         
        
        final ServicoManobra other = (ServicoManobra) obj;
        
        if (id != null || other.getId() != null){
            return super.equals(obj);
        }
        
        if (this.manobra != other.manobra && (this.manobra == null || !this.manobra.equals(other.manobra))) {
            return false;
        }
        if (this.empresaMaritima != other.empresaMaritima && (this.empresaMaritima == null || !this.empresaMaritima.equals(other.empresaMaritima))) {
            return false;
        }
        if (this.tipoDoServico != other.tipoDoServico) {
            return false;
        }
        return true;
    }
    
    
}
