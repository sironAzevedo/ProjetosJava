package vo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 *
 */
public class emailVo implements Serializable {

    private String remetente;
    private String senha;
    private Set<String> destinatario;
    private String assunto;
    private String mensagem;

    public emailVo() {

    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<String> getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Set<String> destinatario) {
        this.destinatario = destinatario;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.remetente);
        hash = 97 * hash + Objects.hashCode(this.senha);
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
        final emailVo other = (emailVo) obj;
        if (!Objects.equals(this.remetente, other.remetente)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return remetente;
    }
}
