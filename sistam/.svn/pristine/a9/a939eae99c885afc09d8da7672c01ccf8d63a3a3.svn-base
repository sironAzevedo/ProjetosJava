package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "AGENCIAMENTO_ACOMPANHAMENTO", schema = "STAM")
public class Acompanhamento extends AbstractIdBean<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    
    @Id
    @Column(name = "AGAC_SQ_ACOMPANHAMENTO", nullable=false)
    @GeneratedValue(generator = "SQ_AGAC_SQ_ACOMPANHAMENTO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGAC_SQ_ACOMPANHAMENTO", sequenceName = "STAM.SQ_AGAC_SQ_ACOMPANHAMENTO", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Agenciamento agenciamento;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGAC_DT_ACOMPANHAMENTO", nullable=false)
    private Date data;
    
    @Column(name = "AGAC_CD_CHAVE_CADASTRADOR", nullable=false)
    private String chaveCadastrador;

    @Column(name = "AGAC_NM_CADASTRADOR", nullable=false)
    private String nomeCadastrador;
    
    @Column(name = "AGAC_TX_ACOMPANHAMENTO", nullable=false)
    private String texto;

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

    public String getDataHoraFormatada(){
        String dtRetorno = "";
        if (this.data !=null) {
            dtRetorno = SistamDateUtils.formatDate(this.data,"dd/MM/yyyy HH:mm", TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()) );           
        }
        return dtRetorno; 
        
    }
    
    public String getChaveCadastrador() {
        return chaveCadastrador;
    }

    public void setChaveCadastrador(String chaveCadastrador) {
        this.chaveCadastrador = chaveCadastrador;
    }

    public String getNomeCadastrador() {
        return nomeCadastrador;
    }

    public void setNomeCadastrador(String nomeCadastrador) {
        this.nomeCadastrador = nomeCadastrador;
    }
    
    public String getCadastradorFormatado() {
        String result = "";
        if (chaveCadastrador != null) {
            result = result + "[" + chaveCadastrador + "] ";
        }
        if (nomeCadastrador != null) {
            result = result + nomeCadastrador;
        }
        return result;
    }
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    
    
    
    
    
    
}
