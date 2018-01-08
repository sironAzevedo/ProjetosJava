package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICO_MEDICO_ODONTOLOGICO", schema = "STAM")
public class ServicoMedicoOdontologico extends AbstractIdBean<Long> implements ServicoExecutado, Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Constantes">
    
    public static final String SERV_MED_ODONTO_ID = "id";
    public static final String SERV_MED_ODONTO_SERVICO_PROTECAO = "servicoProtecao";
    public static final String SERV_MED_ODONTO_NOME_TRIPULANTE = "nomeTripulante";
    
    private static final long serialVersionUID = 1L;  
    
    @Id
    @Column(name = "PRSE_SQ_PROTECAO", nullable=false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRSE_SQ_PROTECAO", nullable=false, insertable = false, updatable = false)
    private ServicoProtecao servicoProtecao;
    
    @Column(name="SEMO_NM_TRIPULANTE")
    private String nomeTripulante;
   
    public Long getId() {
        return id;
    }

    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public String getNomeTripulante() {
        return nomeTripulante;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
    }

    public void setNomeTripulante(String nomeTripulante) {
        this.nomeTripulante = nomeTripulante;
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
