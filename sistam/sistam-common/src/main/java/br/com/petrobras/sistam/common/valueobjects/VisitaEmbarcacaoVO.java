package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.Date;

/**
 * @author adzk
 */
public class VisitaEmbarcacaoVO extends AbstractBean {

    private String nome;
    private String parentesco;
    private Date dataNascimento;
    private String tipoDocumento;
    private String numeroDocumento;
    private int numeracao;
    

    public VisitaEmbarcacaoVO() {
    }
    
    public VisitaEmbarcacaoVO(int numeracao) {
        this.numeracao = numeracao;
    }

    public VisitaEmbarcacaoVO(String nome, String parentesco, Date dataNascimento, String tipoDocumento, String numeroDocumento) {
        this.nome = nome;
        this.parentesco = parentesco;
        this.dataNascimento = dataNascimento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    } 
    
    public String getDtNascimento(){
        return SistamDateUtils.formatDate(dataNascimento, SistamDateUtils.DATE_PATTERN, null);
    }
    
    public int getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(int numeracao) {
        this.numeracao = numeracao;
    } 
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 97 * hash + (this.parentesco != null ? this.parentesco.hashCode() : 0);
        hash = 97 * hash + (this.dataNascimento != null ? this.dataNascimento.hashCode() : 0);
        hash = 97 * hash + (this.tipoDocumento != null ? this.tipoDocumento.hashCode() : 0);
        hash = 97 * hash + (this.numeroDocumento != null ? this.numeroDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VisitaEmbarcacaoVO other = (VisitaEmbarcacaoVO) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        
        if ((this.parentesco == null) ? (other.parentesco != null) : !this.parentesco.equals(other.parentesco)) {
            return false;
        }
        
        if ((this.dataNascimento == null) ? (other.dataNascimento != null) : !this.dataNascimento.equals(other.dataNascimento)) {
            return false;
        }
        
        if ((this.tipoDocumento == null) ? (other.tipoDocumento != null) : !this.tipoDocumento.equals(other.tipoDocumento)) {
            return false;
        }
        
        if ((this.numeroDocumento == null) ? (other.numeroDocumento != null) : !this.numeroDocumento.equals(other.numeroDocumento)) {
            return false;
        }
        return true;
    }  
    
} 