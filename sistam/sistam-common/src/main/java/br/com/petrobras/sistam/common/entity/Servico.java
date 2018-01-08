package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoServico;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "EMPRESA_SERVICO", schema = "STAM")
public class Servico extends AbstractIdBean<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_EMPRESA = "empresa";
    public static final String PROP_TIPO_SERVICO = "tipoServico";    
    public static final String PROP_NOME_SERVICO = "nomeServico";

    @Id
    @Column(name = "EMSE_SQ_EMPRESA_SERVICO", nullable=false)
    @GeneratedValue(generator = "SQ_EMSE_SQ_EMPRESA_SERVICO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_EMSE_SQ_EMPRESA_SERVICO", sequenceName = "STAM.SQ_EMSE_SQ_EMPRESA_SERVICO", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EMMA_SQ_EMPRESA_MARITIMA", nullable=false)
    private EmpresaMaritima empresa;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoServico")})
    @Column(name="EMSE_IN_SERVICO", nullable=false)
    private TipoServico tipoServico;
     
    @Column(name = "EMSE_NM_SERVICO", nullable=false)
    private String nomeServico;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public EmpresaMaritima getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaMaritima empresa) {
        this.empresa = empresa;
    }


    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    @Override
    public String toString() {
        return nomeServico;
    }

}
