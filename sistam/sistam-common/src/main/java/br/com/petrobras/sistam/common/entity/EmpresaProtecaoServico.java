package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
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

/**
 * @author adzk
 */
@Entity
@Table(name = "EMPRESA_PROTECAO_SERVICO", schema = "STAM")
public class EmpresaProtecaoServico extends AbstractIdBean<Long> implements Serializable, Comparable<EmpresaProtecaoServico> {

    public static final String PROP_ID = "id";
    public static final String PROP_EMPRESA = "empresa";
    public static final String PROP_TIPO = "tipo";
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    @Id
    @Column(name = "EMPS_SQ_EMP_PROTECAO_SERVICO", nullable = false)
    @GeneratedValue(generator = "SQ_EMPS_SQ_EMP_PROTECAO_SERV", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_EMPS_SQ_EMP_PROTECAO_SERV", sequenceName = "STAM.SQ_EMPS_SQ_EMP_PROTECAO_SERV", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPR_SQ_EMPRESA_PROTECAO", nullable = false)
    private EmpresaProtecao empresa;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {
        @Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoServicoProtecao")})
    @Column(name = "EMPS_IN_TIPO_SERVICO_PROTECAO", nullable = false)
    private TipoServicoProtecao tipo;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public EmpresaProtecao getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaProtecao empresa) {
        this.empresa = empresa;
    }

    public TipoServicoProtecao getTipo() {
        return tipo;
    }

    public void setTipo(TipoServicoProtecao tipo) {
        this.tipo = tipo;
    }
    //</editor-fold>

    @Override
    public int compareTo(EmpresaProtecaoServico o) {
        return this.getTipo().getPorExtenso().compareToIgnoreCase(o.getTipo().getPorExtenso());
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Tipo: " + tipo.getPorExtenso();
    }
}