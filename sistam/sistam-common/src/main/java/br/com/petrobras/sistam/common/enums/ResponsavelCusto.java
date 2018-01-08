package br.com.petrobras.sistam.common.enums;

import br.com.petrobras.sistam.common.util.Petrobras;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ResponsavelCusto implements Serializable {

    PETROBRAS("P", "PETROBRAS", Petrobras.PETROBRAS_NOME_COMPLETO, Petrobras.PETROBRAS_ENDERECO, Petrobras.PETROBRAS_CEP, Petrobras.PETROBRAS_CNPJ, Petrobras.PETROBRAS_INSC_ESTADUAL, Petrobras.PETROBRAS_BAIRRO, Petrobras.PETROBRAS_CIDADE, Petrobras.PETROBRAS_UF),
    TRANSPETRO("T", "TRANSPETRO", Petrobras.TRANSPETRO_NOME_COMPLETO, Petrobras.TRANSPETRO_ENDERECO, Petrobras.TRANSPETRO_CEP, Petrobras.TRANSPETRO_CNPJ, Petrobras.TRANSPETRO_INSC_ESTADUAL, Petrobras.TRANSPETRO_BAIRRO, Petrobras.TRANSPETRO_CIDADE, Petrobras.TRANSPETRO_UF),
    GAS_E_ENEGIA("G", "GÁS E ENERGIA", null),
    SEM_CUSTO("S", "SEM CUSTO", null),
    E_e_P("E", "E&P", null);
    
    private String id;
    private String porExtenso;
    private String nomeCompleto;
    private String endereco;
    private String cep;
    private String cnpj;
    private String ie;
    private String bairro;
    private String cidade;
    private String uf;

    private ResponsavelCusto(String id, String porExtenso, String nomeCompleto) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.nomeCompleto = nomeCompleto;
    }

    private ResponsavelCusto(String id, String porExtenso, String nomeCompleto, String endereco, String cep, String cnpj, String ie, String bairro, String cidade, String uf) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.cnpj = cnpj;
        this.ie = ie;
    }

    public static ResponsavelCusto[] valuesPetrobrasETranspetro() {
        return new ResponsavelCusto[]{PETROBRAS, TRANSPETRO};
    }

    public static ResponsavelCusto[] valuesComCustoEfetivo() {
        return new ResponsavelCusto[]{PETROBRAS, TRANSPETRO, GAS_E_ENEGIA};
    }
    
    public static List<ResponsavelCusto> listValues() {
        return new ArrayList<ResponsavelCusto>(Arrays.asList(ResponsavelCusto.values()));
    }

    public String getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getIe() {
        return ie;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public static ResponsavelCusto from(String s) {
        if (s == null) {
            return null;
        }
        for (ResponsavelCusto tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
