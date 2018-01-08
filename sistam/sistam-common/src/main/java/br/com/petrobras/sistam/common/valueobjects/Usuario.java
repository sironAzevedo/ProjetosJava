package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;

public class Usuario implements Serializable{
    public static final String PROP_NOME = "nome";
    
    private String chave;
    private String nome;

    public Usuario(String chave, String nome) {
        this.chave = chave;
        this.nome = nome;
    }

    public String getChave() {
        return chave;   
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Usuario other = (Usuario) obj;
        if ((this.chave == null) ? (other.chave != null) : !this.chave.equals(other.chave)) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        if (nome == null || chave == null ){
            return "";
        }
        return chave + " - " + nome;
    }
    
}
