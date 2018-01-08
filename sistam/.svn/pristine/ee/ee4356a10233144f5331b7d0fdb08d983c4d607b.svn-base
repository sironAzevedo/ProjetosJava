package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;

/**
 * @author adzk
 */
public class PessoaTripulateVO implements Serializable {

    private Long id;
    private int numeracao;
    private String nome;
    private String nacionalidade;
    private String passaporte;
    private String volumeBagagem;

    private PessoaTripulateVO() {
        this.nome = "";
        this.nacionalidade = "";
        this.passaporte = "";
        this.volumeBagagem = "";
    }

    private PessoaTripulateVO(Long id) {
        this();
        this.id = id;
    }
    
    public static PessoaTripulateVO novoTripulante(Long id) {
        return new PessoaTripulateVO(id);
    }

    public Long getId() {
        return id;
    }

    public int getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(int numeracao) {
        this.numeracao = numeracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getVolumeBagagem() {
        return volumeBagagem;
    }

    public void setVolumeBagagem(String volumeBagagem) {
        this.volumeBagagem = volumeBagagem;
    }
}
