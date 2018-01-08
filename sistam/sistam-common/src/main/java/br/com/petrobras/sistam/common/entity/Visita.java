package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VISITA", schema = "STAM")
public class Visita extends AbstractIdBean<Long> implements Serializable {
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_DATA_INICIO = "dataInicio";
    public static final String PROP_DATA_TERMINO = "dataTermino";
    public static final String PROP_OBSERVACAO = "observacao";
    public static final String PROP_CHAVE_AGENTE = "chaveAgente";
    public static final String PROP_NOME_AGENTE = "nomeAgente";
    public static final String PROP_TRANSPORTES = "transportes";
    public static final String PROP_NOME_USUARIO_ALTERACAO = "nomeUsuarioAlteracao";
    public static final String PROP_CHAVE_ALTERACAO = "chaveUsuarioAlteracao";
    public static final String PROP_DATA_ALTERACAO = "dataAlteracao";
        
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "VISI_SQ_VISITA", nullable=false)
    @GeneratedValue(generator = "SQ_VISI_SQ_VISITA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_VISI_SQ_VISITA", sequenceName = "STAM.SQ_VISI_SQ_VISITA", allocationSize = 1)
    private Long id;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Agenciamento agenciamento;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VISI_DT_INICIO", nullable=false)
    private Date dataInicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VISI_DT_TERMINO", nullable=false)
    private Date dataTermino;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VISI_DT_PROGRAMADA", nullable=true)
    private Date dataProgramada;
    
    @Column(name="VISI_TX_OBSERVACAO", nullable=true)
    private String observacao;
    
    @Column(name="VISI_CD_CHAVE", nullable=false)
    private String chaveAgente;
    
    @Column(name="VISI_NM_VISITANTE", nullable=false)
    private String nomeAgente;
 
    @Column(name="VISI_CD_CHAVE_ALTERACAO", nullable=false)
    private String chaveUsuarioAlteracao;
    
    @Column(name="VISI_NM_USU_ALTERACAO", nullable=false)
    private String nomeUsuarioAlteracao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VISI_DT_ALTERACAO", nullable=false)
    private Date dataAlteracao;
        
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Transporte.PROP_VISITA)
    private Set<Transporte> transportes = new  HashSet<Transporte>(); 

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {}
    
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
    
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Date getDataProgramada() {
        return dataProgramada;
    }

    public void setDataProgramada(Date dataProgramada) {
        this.dataProgramada = dataProgramada;
    }
    
    public String getObservacao() {
        return observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Transporte> getTransportes() {
        return Collections.unmodifiableList(new ArrayList<Transporte>(transportes));
    }

    public String getChaveAgente() {
        return chaveAgente;
    }

    public void setChaveAgente(String chaveAgente) {
        this.chaveAgente = chaveAgente;
    }

    public String getNomeAgente() {
        return nomeAgente;
    }

    public void setNomeAgente(String nomeAgente) {
        this.nomeAgente = nomeAgente;
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

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    
    
    //</editor-fold>
    
    public String getChaveNomeAgente(){
        if (chaveAgente != null && nomeAgente != null){
            return chaveAgente + " - " + nomeAgente;
        }
        return null;
    }
    
    
    /**
     * Adiciona um transporte, quando diferente de null.
     */
    public void adicionarTransporte(Transporte transporte){
        if (transporte != null){
            transporte.setVisita(this);
            this.transportes.add(transporte);
        }
        firePropertyChange(PROP_TRANSPORTES, null, null);
    }
    
    /**
     * Remove um transporte.
     */
    public void removerTransporte(Transporte transporte){
        if (transporte != null){
            transporte.setVisita(null);
            this.transportes.remove(transporte);
        }
        firePropertyChange(PROP_TRANSPORTES, null, null);
    }
}  
