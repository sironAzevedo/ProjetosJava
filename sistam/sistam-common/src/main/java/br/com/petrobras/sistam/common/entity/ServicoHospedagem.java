package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.fcorp.common.support.CollectionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SERVICO_HOSPEDAGEM", schema = "STAM")
public class ServicoHospedagem extends AbstractIdBean<Long> implements ServicoExecutado, Serializable {

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    private static final long serialVersionUID = 1L;
    
    public static final String SERV_HOSPEDAGEM_ID = "id";
    public static final String SERV_HOSPEDAGEM_SERVICO_PROTECAO = "servicoProtecao";
    public static final String SERV_HOSPEDAGEM_EMPRESA_SERVICO = "empresaServico";
    public static final String SERV_HOSPEDAGEM_DT_CHEGADA = "dataChegada";
    public static final String SERV_HOSPEDAGEM_DT_SAIDA = "dataSaida";
    public static final String SERV_TRANSPORTE_AUTORIZACAO = "autorizacao";
    public static final String SERV_HOSPEDAGEM_HOSPEDES = "hospedes";
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Propriedades">
    @Id
    @Column(name = "PRSE_SQ_PROTECAO", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRSE_SQ_PROTECAO", nullable = false, insertable = false, updatable = false)
    private ServicoProtecao servicoProtecao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMSE_SQ_EMPRESA_SERVICO", nullable=false)
    private Servico empresaServico;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SEHO_DT_CHEGADA")
    private Date dataChegada;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SEHO_DT_SAIDA")
    private Date dataSaida;
    
    @Column(name="SEHO_TX_AUTORIZACAO")
    private String autorizacao;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Hospede.SERV_PROTECAO)
    private Set<Hospede> hospedes = new HashSet<Hospede>();    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
    }

    public Servico getEmpresaServico() {
        return empresaServico;
    }

    public void setEmpresaServico(Servico empresaServico) {
        this.empresaServico = empresaServico;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }
    
    public List<Hospede> getHospedes() {
        List<Hospede> lista = new ArrayList<Hospede>(hospedes);
        CollectionUtils.sort(lista, Hospede.PROP_NOME);
        return  lista;
    }
    //</editor-fold>

    
    public void adicionarHospede(Hospede hospede) {
        if (hospede != null) {
            hospede.setServicoProtecao(hospede.getServicoProtecao());
            hospedes.add(hospede);
            firePropertyChange(SERV_HOSPEDAGEM_HOSPEDES, null, null);
        }
    }

    public void removerHospede(Hospede hospede) {
        hospedes.remove(hospede);
        firePropertyChange(SERV_HOSPEDAGEM_HOSPEDES, null, null);
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
