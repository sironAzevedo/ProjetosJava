package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import java.io.Serializable;
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
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "VISITA_TRANSPORTE", schema = "STAM")
public class Transporte extends AbstractIdBean<Long> implements Serializable {
    public static final String PROP_ID = "id";
    public static final String PROP_VISITA = "visita";
    public static final String PROP_TIPO_TRANSPORTE = "tipoTransporte";
    public static final String PROP_RESPONSAVEL_CUSTO = "responsavelCusto";
    public static final String PROP_CUSTO = "custo";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "VITR_SQ_TRANSPORTE", nullable=false)
    @GeneratedValue(generator = "SQ_VITR_SQ_TRANSPORTE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_VITR_SQ_TRANSPORTE", sequenceName = "STAM.SQ_VITR_SQ_TRANSPORTE", allocationSize = 1)
    private Long id;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="VISI_SQ_VISITA", nullable=false)
    private Visita visita;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoTransporte")})
    @Column(name="VITR_IN_TIPO_TRANSPORTE", nullable=false)
    private TipoTransporte tipoTransporte;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RECU_SQ_RESP_CUSTO", nullable=false)
    private ResponsavelCustoEntity responsavelCusto;
    
    @Column(name="VITR_VL_CUSTO", nullable=false)
    private Double custo;
    
    @Column(name="VITR_TX_DESCRICAO", nullable=true)
    private String descricao;
    
    @Column(name="VITR_TX_SERVICO", nullable=true)
    private String servico;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.StatusEmbarcacao")})
    @Column(name="VITR_IN_CONDICAO_NAVIO", nullable=true)
    private StatusEmbarcacao condicaoNavio;
    

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {}
    
    public Visita getVisita() {
        return visita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public StatusEmbarcacao getCondicaoNavio() {
        return condicaoNavio;
    }

    public void setCondicaoNavio(StatusEmbarcacao condicaoNavio) {
        this.condicaoNavio = condicaoNavio;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public TipoTransporte getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(TipoTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public ResponsavelCustoEntity getResponsavelCusto() {
        return responsavelCusto;
    }

    public void setResponsavelCusto(ResponsavelCustoEntity responsavelCusto) {
        this.responsavelCusto = responsavelCusto;
    } 

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }
    
    //</editor-fold>
    
}
