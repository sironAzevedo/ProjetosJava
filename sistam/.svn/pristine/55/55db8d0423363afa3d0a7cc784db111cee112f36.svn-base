package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "AGENCIAMENTO_CANCELAMENTO", schema = "STAM")
public class CancelamentoAgenciamento extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_MOTIVO = "motivo";
    public static final String PROP_DESCRICAO_MOTIVO = "descricaoMotivo";
    public static final String PROP_DATA = "data";
    public static final String PROP_CHAVE_USUARIO = "chaveUsuario";
    public static final String PROP_NOME_USUARIO = "nomeUsuario";

    @Id
    @Column(name = "AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false, insertable = false, updatable = false)
    private Agenciamento agenciamento;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGCA_DT_CANCELAMENTO", nullable=false)
    private Date data;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.MotivoCancelamento")})
    @Column(name="AGCA_IN_CANCELAMENTO", nullable=false)
    private MotivoCancelamento motivo;
    
    @Column(name = "AGCA_TX_MOTIVO_CANCELAMENTO", nullable=true)
    private String descricaoMotivo;
    
    @Column(name = "AGCA_CD_CHAVE_CANCELAMENTO", nullable=false)
    private String chaveUsuario;
    
    @Column(name = "AGCA_NM_CANCELAMENTO", nullable=false)
    private String nomeUsuario;
    
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
    

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public MotivoCancelamento getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoCancelamento motivo) {
        this.motivo = motivo;
    }

    public String getDescricaoMotivo() {
        return descricaoMotivo;
    }

    public void setDescricaoMotivo(String descricaoMotivo) {
        this.descricaoMotivo = descricaoMotivo;
    }

    public String getChaveUsuario() {
        return chaveUsuario;
    }

    public void setChaveUsuario(String chaveUsuario) {
        this.chaveUsuario = chaveUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public String getUsuarioFormatado(){
        if (nomeUsuario != null && chaveUsuario != null){
            return chaveUsuario + " -  " + nomeUsuario;
        }
        return null;
    }
    
}
