package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.MotivoDesvio;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "DESVIO", schema = "STAM")
public class Desvio extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_PORTO_DESTINO_ALTERADO = "portoDestinoAlterado";
    public static final String PROP_INSCRICAO = "inscricao";
    public static final String PROP_DATA = "data";
    public static final String PROP_MOTIVO = "motivo";
    public static final String PROP_DESTINO = "destinoIntermediarioAlterado";
    public static final String PROP_CHAVE_USUARIO = "chaveUsuario";
    public static final String PROP_NOME_USUARIO = "nomeUsuario";

    @Id
    @Column(name = "AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false, insertable = false, updatable = false)
    private Agenciamento agenciamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PORT_CD_ID", nullable=false)
    private Porto portoDestinoAlterado;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DESV_DT_DESVIO", nullable=false)
    private Date data;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.MotivoDesvio")})
    @Column(name="DESV_IN_MOTIVO", nullable=false)
    private MotivoDesvio motivo;
    
    @Column(name = "DESV_TX_DESTINO", nullable=true)
    private String destinoIntermediarioAlterado;
    
    
    
    @Column(name = "DESV_CD_CHAVE_CADASTRADOR", nullable=false)
    private String chaveUsuario;
    
    @Column(name = "DESV_NM_CADASTRADOR", nullable=false)
    private String nomeUsuario;
    
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
    
    public Porto getPortoDestinoAlterado() {
        return portoDestinoAlterado;
    }

    public void setPortoDestinoAlterado(Porto portoDestinoAlterado) {
        this.portoDestinoAlterado = portoDestinoAlterado;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public MotivoDesvio getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoDesvio motivo) {
        this.motivo = motivo;
    }

    public String getDestinoIntermediarioAlterado() {
        return destinoIntermediarioAlterado;
    }

    public void setDestinoIntermediarioAlterado(String destinoIntermediarioAlterado) {
        this.destinoIntermediarioAlterado = destinoIntermediarioAlterado;
    }

     
       
    public String getChaveUsuario() {
        return chaveUsuario;
    }

    public void setChaveUsuario(String chaveUsuario) {
        this.chaveUsuario = chaveUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public String getUsuarioFormatado(){
        if (nomeUsuario != null && chaveUsuario != null){
            return chaveUsuario + " -  " + nomeUsuario;
        }
        return null;
    }
    
    public String getDestinoAlteradoFormatado() {
        StringBuilder destinoAleradoFormatado = new StringBuilder();
        if (null != portoDestinoAlterado) {
            destinoAleradoFormatado.append(portoDestinoAlterado.getNomeCompleto());
            destinoAleradoFormatado.append(" ");
        }
        destinoAleradoFormatado.append(destinoIntermediarioAlterado != null ? destinoIntermediarioAlterado : "");
        return destinoAleradoFormatado.toString();
    }
}
