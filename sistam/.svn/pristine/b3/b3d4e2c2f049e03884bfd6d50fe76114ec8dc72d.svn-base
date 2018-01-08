package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
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
import org.hibernate.annotations.Type;

@Entity
@Table(name = "PASSAGEIRO", schema = "STAM")
public class Passageiro extends AbstractIdBean<Long> implements Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Constantes">
    
    public static final String PROP_ID = "id";
    public static final String PROP_NOME = "nome";
    public static final String PROP_DOCUMENTO = "documento";
    public static final String PROP_CPF = "cpf";
    public static final String PROP_ATIVO = "ativo";
    public static final String SERV_PROTECAO = "servicoProtecao";

    private static final long serialVersionUID = 1L;  
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Propriedades">
    @Id
    @Column(name = "PASS_SQ_PASSAGEIRO", nullable=false)
    @GeneratedValue(generator = "SQ_PASS_SQ_PASSAGEIRO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PASS_SQ_PASSAGEIRO", sequenceName = "STAM.SQ_PASS_SQ_PASSAGEIRO", allocationSize = 1)
    private Long id;
   
    @Column(name="PASS_NM_PASSAGEIRO")
    private String nome;
    
    @Column(name="PASS_TX_DOCUMENTO")
    private String documento;
    
    @Column(name="PASS_TX_NR_CPF")
    private String cpf;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRSE_SQ_PROTECAO", nullable = false)
    private ServicoProtecao servicoProtecao;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "PASS_IN_ATIVO", nullable=false) 
    private boolean ativo;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
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
    //</editor-fold>
    
    @Override
    public String toString() {
        return nome;
    }
}
