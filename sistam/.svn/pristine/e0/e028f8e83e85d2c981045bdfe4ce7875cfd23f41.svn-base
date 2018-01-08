package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamUtils;
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

@Entity
@Table(name = "PORTO_COMPLEMENTO", schema = "STAM")
public class PortoComplemento extends AbstractIdBean<Long> {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_ID = "id";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_ENDERECO = "endereco";
    public static final String PROP_CIDADE = "cidade";
    public static final String PROP_ESTADO = "estado";
    public static final String PROP_INSCRICAO_ESTADUAL = "inscricaoEstadual";
    public static final String PROP_CNPJ = "cnpj";
    public static final String PROP_NOME_CTAC = "nomeCtac";
    
    @Id
    @Column(name = "PCOM_SQ_PORTO_COMPLEMENTO", nullable=false)
    @GeneratedValue(generator = "SQ_PCOM_SQ_PORTO_COMPLEMENTO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PCOM_SQ_PORTO_COMPLEMENTO", sequenceName = "STAM.SQ_PCOM_SQ_PORTO_COMPLEMENTO", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PORT_CD_ID", nullable=false)
    private Porto porto;
    
    @Column(name = "PCOM_TX_ENDERECO", nullable=false)
    private String endereco;

    @Column(name = "PCOM_NM_CIDADE", nullable=false)
    private String cidade;
    
    @Column(name = "PCOM_SG_ESTADO", nullable=false)
    private String estado;
    
    @Column(name = "PCOM_TX_INSCRICAO_ESTADUAL", nullable=false)
    private String inscricaoEstadual;
    
    @Column(name = "PCOM_TX_NR_CNPJ", nullable=false)
    private String cnpj;
  
    @Column(name = "PCOM_NM_CTAC", nullable=true)
    private String nomeCtac;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }
    
    public String getInscricaoEstadualFormatado() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCnpjComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpj);
    }

    public void setCnpjComMascara(String cnpj) {
        Object old = this.cnpj;
        
        if (cnpj == null) {
            this.cnpj = null;
        } else {
            this.cnpj = cnpj.replaceAll("\\D", "");
        }   
        
        firePropertyChange("cnpj", old, this.cnpj);
    }

    public String getNomeCtac() {
        return nomeCtac;
    }

    public void setNomeCtac(String nomeCtac) {
        Object old = this.nomeCtac;
        this.nomeCtac = nomeCtac;
        firePropertyChange("nomeCtac", old, this.nomeCtac);
    }
    
    
    public String getNomePortoCtacFormatado(){
        String nomePortoCtac = nomeCtac;
        if(nomeCtac == null || nomeCtac.isEmpty()){
            nomePortoCtac = porto.getNomeCompleto();
        }
        return nomePortoCtac;
    }
    
    
    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        Object old = this.porto;
        this.porto = porto;
        firePropertyChange("porto", old, this.porto);
    }
    
    public String getEnderecoComplemento(){
        return endereco + " - " + cidade + " - " + estado;
    }
    
}
