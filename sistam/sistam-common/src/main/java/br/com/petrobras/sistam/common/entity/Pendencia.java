package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
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

@Entity
@Table(name = "AGENCIAMENTO_PENDENCIA", schema = "STAM")
public class Pendencia extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_RESOLVIDA = "resolvida";
    public static final String PROP_TIPO_PENDENCIA = "tipoPendencia";    
    public static final String PROP_NOME_RESPONSAVEL = "nomeResponsavel";    
    public static final String PROP_CHAVE_RESPONSAVEL = "chaveResponsavel";    
    public static final String PROP_DATA_SOLUCAO = "dataSolucao";    
    public static final String PROP_DOCUMENTO = "documento";    
    public static final String PROP_TAXA = "taxa";    

    @Id
    @Column(name = "AGPE_SQ_PENDENCIA", nullable=false)
    @GeneratedValue(generator = "SQ_AGPE_SQ_PENDENCIA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGPE_SQ_PENDENCIA", sequenceName = "STAM.SQ_AGPE_SQ_PENDENCIA", allocationSize = 1)
    private Long id;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Agenciamento agenciamento;
        
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGPE_IN_RESOLVIDA", nullable=false)
    private Boolean resolvida = false;
        
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoPendencia")})
    @Column(name="AGPE_IN_TIPO_PENDENCIA", nullable=false)
    private TipoPendencia tipoPendencia;
    
    @Column(name = "AGPE_NM_RESPONSAVEL_SOLUCAO", nullable=true)
    private String nomeResposavel;
    
    @Column(name = "AGPE_CD_CHAVE_RESPONSAVEL", nullable=true)
    private String chaveResponsavel;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGPE_DT_SOLUCAO", nullable=true)
    private Date dataSolucao;
              
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGDO_SQ_AGENCIAMENTO_DOC", nullable=true)
    private Documento documento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGTA_SQ_AGENCIAMENTO_TAXA", nullable=true)
    private Taxa taxa;
    
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
    
    public boolean isResolvida() {
        if (resolvida == null){
            return false;
        }
        return resolvida;
    }
    
    public void setResolvida(boolean resolvida) {
        this.resolvida = resolvida;
    }
    
    public TipoPendencia getTipoPendencia() {
        return tipoPendencia;
    }
    
    public void setTipoPendencia(TipoPendencia tipoPendencia) {
        this.tipoPendencia = tipoPendencia;
    }
    
    public String getNomeResposavel() {
        return nomeResposavel;
    }
    
    public void setNomeResposavel(String nomeResposavel) {
        this.nomeResposavel = nomeResposavel;
    }
    
    public String getChaveResponsavel() {
        return chaveResponsavel;
    }
    
    public void setChaveResponsavel(String chaveResponsavel) {
        this.chaveResponsavel = chaveResponsavel;
    }
    
    public Date getDataSolucao() {
        return dataSolucao;
    }
    
    public void setDataSolucao(Date dataSolucao) {
        this.dataSolucao = dataSolucao;
    }
    
    public Documento getDocumento() {
        return documento;
    }
    
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
    public Taxa getTaxa() {
        return taxa;
    }
    
    public void setTaxa(Taxa taxa) {
        this.taxa = taxa;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters Sintéticos">
    /**
     * Obtém o rsponsável na forma 'chave - nome'.
     * @return 
     */
    public String getResponsavelFormatado(){
        if (nomeResposavel != null && chaveResponsavel != null){
            return chaveResponsavel + " - " + nomeResposavel;
        }
        return null;
    }
    //</editor-fold>
               
    @Override
    public String toString() {
        return tipoPendencia.getPorExtenso();
    }
    
}
