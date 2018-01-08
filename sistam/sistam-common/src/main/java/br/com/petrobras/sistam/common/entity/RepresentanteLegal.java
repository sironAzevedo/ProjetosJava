package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
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
import org.hibernate.annotations.Type;

@Entity
@Table(name = "AGENCIA_CONTATO", schema = "STAM")
public class RepresentanteLegal extends AbstractIdBean<Long> implements Comparable<RepresentanteLegal> {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIA = "agencia";
    public static final String PROP_NOME = "nome";
    public static final String PROP_CPF = "cpf";
    public static final String PROP_ATIVO = "ativo";

    @Id
    @Column(name = "AGCO_SQ_CONTATO", nullable=false)
    @GeneratedValue(generator = "SQ_AGCO_SQ_CONTATO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGCO_SQ_CONTATO", sequenceName = "STAM.SQ_AGCO_SQ_CONTATO", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEN_SQ_AGENCIA", nullable=false)
    private Agencia agencia;
    
    @Column(name = "AGCO_NM_CONTATO", nullable = false)
    private String nome;
    
    @Column(name = "AGCO_TX_NR_CPF", nullable = true)
    private String cpf;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGCO_IN_ATIVO", nullable=false) 
    private Boolean ativo = true;
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public Agencia getAgencia() {
        return agencia;
    }
    
    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean getAtivo() {
        return ativo != null ? ativo : false;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters Sintéticos">
    public String getCpfComMascara() {
        return SistamUtils.formatMask("###.###.###-##", cpf);
    }
    
    public void setCpfComMascara(String cpf) {
        if (cpf == null) {
            setCpf(null);
        } else {
            setCpf(cpf.replaceAll("\\D", ""));
        }
    }
    
    public String getNomeCPFFormatado(){
        StringBuilder sb = new StringBuilder();
        sb.append(nome)
                .append(" CPF: ")
                .append(getCpfComMascara());
        
        return sb.toString();
    }
    
    //</editor-fold>
    
    @Override
    public String toString() {
        return nome;
    }
    

    @Override
    public int compareTo(RepresentanteLegal other) {
        if (other == null){
            return 1;
        }
        
        return nome.compareTo(other.getNome());
    }
    
}
