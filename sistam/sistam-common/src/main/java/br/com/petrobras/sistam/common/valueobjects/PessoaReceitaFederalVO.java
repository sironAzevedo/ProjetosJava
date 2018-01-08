package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class PessoaReceitaFederalVO extends AbstractBean implements Serializable{
    
    private String nome="";
    private String documento;
    private String nacionalidade;
    private String volume;
    private String bagagem="";
    private Integer numeracao;

    public PessoaReceitaFederalVO(Integer numeracao){
        this.numeracao = numeracao;
    }
        
    public PessoaReceitaFederalVO(){}
    
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
        firePropertyChange("documento", null, null);
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
        firePropertyChange("nacionalidade", null, null);
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
        firePropertyChange("volume", null, null);
    }

    public String getBagagem() {
        return bagagem!=null? bagagem : "";
    }

    public void setBagagem(String bagagem) {
        this.bagagem = bagagem;
        firePropertyChange("bagagem", null, null);
    }

    
}
