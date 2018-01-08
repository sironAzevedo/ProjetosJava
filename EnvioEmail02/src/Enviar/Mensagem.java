package Enviar;

import java.io.Serializable;
import java.util.Set; 
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 *
 */
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    private String remetente;
    private Set<String> destinatarioList;
    private String assunto;
    private String texto;
    private boolean html;

    public Mensagem(String remetente, Set<String> destinatarioList, String assunto, String texto, boolean html) {
        this.remetente = remetente;
        this.destinatarioList = destinatarioList;
        this.assunto = assunto;
        this.texto = texto;
        this.html = html;
    }

    public Mensagem(String remetente, Set<String> destinatarioList, String assunto, String texto) {
        this(remetente, destinatarioList, assunto, texto, false);
    }

    public String getRemetente() {
        return remetente;
    }
    
    public String getAssunto() {
        return assunto;
    }

    public String getTexto() {
        return texto;
    }

    public Set<String> getDestinatarioList() {
        return destinatarioList;
    }

    public boolean isHtml() {
        return html;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
