package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class PessoaPoliciaFederalVO extends AbstractBean implements Serializable{
    
    private String nome="";
    private String tipoDocumento="";
    private String numeroDocumento="";
    private String nomeEmbarcacao="";
    private Integer numeracao;

    public PessoaPoliciaFederalVO(Integer numeracao){
        this.numeracao = numeracao;
    }
        
    public PessoaPoliciaFederalVO(){}
    
    public Integer getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Integer numeracao) {
        this.numeracao = numeracao;
        firePropertyChange("numeracao", null, null);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, null);
    }
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        firePropertyChange("numeroDocumento", null, null);
    } 
}

   