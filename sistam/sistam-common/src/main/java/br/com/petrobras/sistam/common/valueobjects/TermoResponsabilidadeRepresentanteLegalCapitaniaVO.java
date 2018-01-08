package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;

public class TermoResponsabilidadeRepresentanteLegalCapitaniaVO implements Serializable {

    private String nrDuv;
    private String portoEstadia;
    private String nomeEmbarcacao;
    private String proprietario;
    private String armador;
    private String imo;
    private String bandeiraEmbarcacao;
    private String nomeComandanteEntrada;
    private String nomeComandanteSaida;
       
    private String agenteConsignatario;
    private String enderecoTelefone;
    private String enderecoEletronico;
    private String cnpj;
    
    private boolean procuracaoRegistradaEmOficio;
    private String validadeProcuracao;
    
    private String numeroProcessoDespacho;

    private String municipioResponsavel;
    private String data;

    public String getNrDuv() {
        return nrDuv;
    }

    public void setNrDuv(String nrDuv) {
        this.nrDuv = nrDuv;
    }

    public String getPortoEstadia() {
        return portoEstadia;
    }

    public void setPortoEstadia(String portoEstadia) {
        this.portoEstadia = portoEstadia;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getArmador() {
        return armador;
    }

    public void setArmador(String armador) {
        this.armador = armador;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getBandeiraEmbarcacao() {
        return bandeiraEmbarcacao;
    }

    public void setBandeiraEmbarcacao(String bandeiraEmbarcacao) {
        this.bandeiraEmbarcacao = bandeiraEmbarcacao;
    }

    public String getNomeComandanteEntrada() {
        return nomeComandanteEntrada;
    }

    public void setNomeComandanteEntrada(String nomeComandanteEntrada) {
        this.nomeComandanteEntrada = nomeComandanteEntrada;
    }

    public String getNomeComandanteSaida() {
        return nomeComandanteSaida;
    }

    public void setNomeComandanteSaida(String nomeComandanteSaida) {
        this.nomeComandanteSaida = nomeComandanteSaida;
    }

    public String getAgenteConsignatario() {
        return agenteConsignatario;
    }

    public void setAgenteConsignatario(String agenteConsignatario) {
        this.agenteConsignatario = agenteConsignatario;
    }

    public String getEnderecoTelefone() {
        return enderecoTelefone;
    }

    public void setEnderecoTelefone(String enderecoTelefone) {
        this.enderecoTelefone = enderecoTelefone;
    }

    public String getEnderecoEletronico() {
        return enderecoEletronico;
    }

    public void setEnderecoEletronico(String enderecoEletronico) {
        this.enderecoEletronico = enderecoEletronico;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isProcuracaoRegistradaEmOficio() {
        return procuracaoRegistradaEmOficio;
    }

    public void setProcuracaoRegistradaEmOficio(boolean procuracaoRegistradaEmOficio) {
        this.procuracaoRegistradaEmOficio = procuracaoRegistradaEmOficio;
    }

    public String getValidadeProcuracao() {
        return validadeProcuracao;
    }

    public void setValidadeProcuracao(String validadeProcuracao) {
        this.validadeProcuracao = validadeProcuracao;
    }

    public String getMunicipioResponsavel() {
        return municipioResponsavel;
    }

    public void setMunicipioResponsavel(String municipioResponsavel) {
        this.municipioResponsavel = municipioResponsavel;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNumeroProcessoDespacho() {
        return numeroProcessoDespacho;
    }

    public void setNumeroProcessoDespacho(String numeroProcessoDespacho) {
        this.numeroProcessoDespacho = numeroProcessoDespacho;
    }
    
}
