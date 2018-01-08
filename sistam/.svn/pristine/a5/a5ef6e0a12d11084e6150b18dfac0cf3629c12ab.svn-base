package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.fcorp.common.support.CollectionUtils;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "SERVICO_TRANSPORTE", schema = "STAM")
public class ServicoTransporte extends AbstractIdBean<Long> implements ServicoExecutado, Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Constantes">
    
    public static final String SERV_TRANSPORTE_ID = "id";
    public static final String SERV_TRANSPORTE_SERVICO_PROTECAO = "servicoProtecao";
    public static final String SERV_TRANSPORTE_DATA_HORA_SERVICO = "dataServico";
    public static final String SERV_TRANSPORTE_ORIGEM = "origem";
    public static final String SERV_TRANSPORTE_DESTINO = "destino";
    public static final String SERV_TRANSPORTE_AUTORIZACAO = "autorizacao";
    public static final String SERV_TRANSPORTE_VOO = "voo";
    public static final String SERV_TRANSPORTE_TIPO_TRANSPORTE = "tipoTransporte";
    public static final String SERV_TRANSPORTE_EMPRESA_SERVICO = "empresaServico";
    public static final String SERV_TRANSPORTE_PASSAGEIROS = "passageiros";

    private static final long serialVersionUID = 1L;  
    
    @Id
    @Column(name = "PRSE_SQ_PROTECAO", nullable=false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRSE_SQ_PROTECAO", nullable=false, insertable = false, updatable = false)
    private ServicoProtecao servicoProtecao;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SETR_DT_HORA_SERVICO")
    private Date dataServico;
    
    @Column(name="SETR_TX_ORIGEM")
    private String origem;
   
    @Column(name="SETR_TX_DESTINO")
    private String destino;    

    @Column(name="SETR_TX_AUTORIZACAO")
    private String autorizacao;

    @Column(name="SETR_TX_VOO")
    private String voo;    
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoTransporte")})
    @Column(name = "SETR_IN_TIPO_TRANSPORTE", nullable = false)
    private TipoTransporte tipoTransporte;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMSE_SQ_EMPRESA_SERVICO", nullable=true)
    private Servico empresaServico;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Passageiro.SERV_PROTECAO)
    private Set<Passageiro> passageiros = new HashSet<Passageiro>();   
    
    public Long getId() {
        return id;
    }

    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
    }

    public Date getDataServico() {
        return dataServico;
    }

    public void setDataServico(Date dataServico) {
        this.dataServico = dataServico;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    public String getVoo() {
        return voo;
    }

    public void setVoo(String voo) {
        this.voo = voo;
    }

    public TipoTransporte getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(TipoTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
        firePropertyChange("tipoTransporte", null, null);
    }

    public Servico getEmpresaServico() {
        return empresaServico;
    }

    public void setEmpresaServico(Servico empresaServico) {
        this.empresaServico = empresaServico;
    }
    
    public List<Passageiro> getPassageiros() {
        List<Passageiro> lista = new ArrayList<Passageiro>(passageiros);
        CollectionUtils.sort(lista, Passageiro.PROP_NOME);
        return  lista;
    }

    public void adicionarPassageiro(Passageiro passageiro) {
        if (passageiro != null) {
            passageiro.setServicoProtecao(passageiro.getServicoProtecao());
            passageiros.add(passageiro);
            firePropertyChange(SERV_TRANSPORTE_PASSAGEIROS, null, null);
        }
    }

    public void removerPassageiro(Passageiro passageiro) {
        passageiros.remove(passageiro);
        firePropertyChange(SERV_TRANSPORTE_PASSAGEIROS, null, null);
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
