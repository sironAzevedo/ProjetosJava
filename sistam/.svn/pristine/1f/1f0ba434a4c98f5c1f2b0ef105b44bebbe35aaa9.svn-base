package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
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
@Table(name = "PONTO_ATRACACAO", schema = "STAM")
public class PontoAtracacao extends AbstractIdBean<Long> implements Serializable, Comparable {
    private static final long serialVersionUID = 1L;
     
    public static final String PROP_ID = "id";
    public static final String PROP_NOME = "nome";
    public static final String PROP_SIGLA = "sigla";
    public static final String PROP_PONTO_OPERACIONAL = "pontoOperacional";
    
    @Id
    @Column(name = "POAT_SQ_PONTO_ATRACACAO", nullable=false)
    @GeneratedValue(generator = "SQ_POAT_SQ_PONTO_ATRACACAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_POAT_SQ_PONTO_ATRACACAO", sequenceName = "STAM.SQ_POAT_SQ_PONTO_ATRACACAO", allocationSize = 1)
    private Long id;
    
    @Column(name = "POAT_NM_COMPLETO", nullable= false)
    private String nome;

    @Column(name = "POAT_SG_PONTO_ATRACACAO", nullable= true)
    private String sigla;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POOP_CD_ID", nullable=false)
    private PontoOperacional pontoOperacional;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
       this.id = id; 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    

    public PontoOperacional getPontoOperacional() {
        return pontoOperacional;
    }

    public void setPontoOperacional(PontoOperacional pontoOperacional) {
        this.pontoOperacional = pontoOperacional;
    }
    
    public String getNomeFormatado(){
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.pontoOperacional.getPorto().getNomeCompleto().trim())
                .append(" - ")
                .append(this.nome);
        
        return sb.toString();
    }
    
    public String getSiglaFormatada() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.pontoOperacional.getPorto().getId().trim())
                .append(" - ");
                // para n imprimir null no painel,
                //qdo a sigla é null adiciona o nome do ponto de atracação
                if(null == this.sigla){
                   sb.append(this.nome);
                }else{
                    sb.append(this.sigla);
                }
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return nome;   
    }

    @Override
    public int compareTo(Object o) {
        PontoAtracacao outro = (PontoAtracacao)o;
        return this.getNomeFormatado().compareTo(outro.getNomeFormatado());
    }

}
