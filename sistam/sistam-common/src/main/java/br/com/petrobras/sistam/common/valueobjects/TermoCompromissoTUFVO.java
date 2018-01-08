package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;

/**
 * Classe que contém as informações para geração do formulário Parte de Saída
 */
public class TermoCompromissoTUFVO implements Serializable{
    private String numeroProcessoDespacho;
    private String nomePetroCNPJAgencia;
    private String nomeEmbarcacao;
    private String imo;
    private Double valorTUFDolar;   
    private String nomeCPFAgente;
    private String municipio;
    private String dataAssinatura;
    private Long Tpb;
    

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

 
    public String getNumeroProcessoDespacho() {
        return numeroProcessoDespacho;
    }

    public void setNumeroProcessoDespacho(String numeroProcessoDespacho) {
        this.numeroProcessoDespacho = numeroProcessoDespacho;
    }

    public String getNomePetroCNPJAgencia() {
        return nomePetroCNPJAgencia;
    }

    public void setNomePetroCNPJAgencia(String nomePetroCNPJAgencia) {
        this.nomePetroCNPJAgencia = nomePetroCNPJAgencia;
    }
    
  
    
    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

   
    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(String dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }
    

    public Double getValorTUFDolar() {
        return valorTUFDolar;
    }

    public void setValorTUFDolar(Double valorTUFDolar) {
        this.valorTUFDolar = valorTUFDolar;
    }

    public String getNomeCPFAgente() {
        return nomeCPFAgente;
    }

    public void setNomeCPFAgente(String nomeCPFAgente) {
        this.nomeCPFAgente = nomeCPFAgente;
    }

    public Long getTpb() {
        return Tpb;
    }

    public void setTpb(Long Tpb) {
        this.Tpb = Tpb;
    }
    

    //</editor-fold>    
}
