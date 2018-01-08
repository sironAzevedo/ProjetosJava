package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;

/**
 * Classe que contém as informações para geração do formulário Parte de Saída
 */
public class AvisoDeSaidaCapitaniaVo implements Serializable{
    private String duv;
    private String portoSaida;
    private String nomeEmbarcacao;
    private String imo;
    private String numeroInscricao;
    private String destino;
    private String caladoAVante;
    private String caladoARe;
    private String observacao;
    private String municipio;
    private String dataAssinatura;
    private String numeroProcessoDespacho;
    

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

    public String getDuv() {
        return duv;
    }

    public void setDuv(String duv) {
        this.duv = duv;
    }

    public String getPortoSaida() {
        return portoSaida;
    }

    public void setPortoSaida(String portoSaida) {
        this.portoSaida = portoSaida;
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

    public String getNumeroInscricao() {
        return numeroInscricao;
    }

    public void setNumeroInscricao(String numeroInscricao) {
        this.numeroInscricao = numeroInscricao;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCaladoAVante() {
        return caladoAVante;
    }

    public void setCaladoAVante(String caladoAVante) {
        this.caladoAVante = caladoAVante;
    }

    public String getCaladoARe() {
        return caladoARe;
    }

    public void setCaladoARe(String caladoARe) {
        this.caladoARe = caladoARe;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public String getNumeroProcessoDespacho() {
        return numeroProcessoDespacho;
    }

    public void setNumeroProcessoDespacho(String numeroProcessoDespacho) {
        this.numeroProcessoDespacho = numeroProcessoDespacho;
    }
    
    //</editor-fold>

    
}
