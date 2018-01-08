/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help.estudo.service.rest.data;

import help.estudo.service.rest.classEnum.chamado.Status;
import help.estudo.service.rest.classEnum.chamado.Tipo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.TemporalType;

/**
 *
 * @author siron
 */
@XmlRootElement
@Entity
@Table(name = "TB_CHAMADO")
public class Chamado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "CD_CHAMADO")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "DATA_CADASTRO", nullable = false, updatable = false)
    private Date dataRegistro;

    @Enumerated(EnumType.STRING)
//    @Column(name = "CH_IN_TIPO_CHAMDO", nullable = false)
    private Tipo tipo;

    @ManyToOne(optional = false)
//    @JoinColumn(name = "USER_SQ_CHAMADO", nullable = false, insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
//    @JoinColumn(name = "USST_SQ_CHAMADO", nullable = false, insertable = false, updatable = false)
    private Usuario usuarioStatus;

    @Column(length = 64, nullable = false)
    private String assunto;

    @Column(length = 2048, nullable = false)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(length = 8, nullable = false)
    private Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getUsuarioStatus() {
        return usuarioStatus;
    }

    public void setUsuarioStatus(Usuario usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chamado other = (Chamado) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Chamado{" + "id=" + id + ", dataRegistro=" + dataRegistro + ", tipo=" + tipo + ", usuario=" + usuario + ", assunto=" + assunto + ", mensagem=" + mensagem + ", status=" + status + ", usuarioStatus=" + usuarioStatus + '}';
    }
}
