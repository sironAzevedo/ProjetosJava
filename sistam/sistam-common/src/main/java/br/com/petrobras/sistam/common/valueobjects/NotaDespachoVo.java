package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.enums.TipoSimNao;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe que contém as informações para geração do formulário Nota de Despacho
 */
public class NotaDespachoVo implements Serializable {

    private String duv;
    private String orgaoDespacho;
    private String nomeEmbarcacao;
    private String hora;
    private String dia;
    private String bandeira;
    private String comandanteSaida;
    private String municipio;
    private String dataAssinatura;
    private String nomeDelegado;
    private String patenteDelegado;
    private Date dataDocumento;
    private String atracacao;
    private String numeroOficiais;
    private TipoSimNao isento;
    private TipoSimNao carga;
    private String DWT;
    private String consignatario;
    private String tripulacao;
    private String placa;
    private String procedencia;
    private String dataSaida;
    private String dataEntrada;
    private String destino;
    private String valor;
    private String armador;
    private String tonBruta;
    private String tonLiquida;
    private String dataTUF;
    private String valorTUF;
    private String numeroTUF;
    private String valorTUFExtenso;
    private String nomeCPFAgente;

    private String numeroProcessoDespacho;

    public String getDuv() {
        return duv;
    }

    public void setDuv(String duv) {
        this.duv = duv;
    }

    public String getOrgaoDespacho() {
        return orgaoDespacho;
    }

    public void setOrgaoDespacho(String orgaoDespacho) {
        this.orgaoDespacho = orgaoDespacho;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getComandanteSaida() {
        return comandanteSaida;
    }

    public void setComandanteSaida(String comandanteSaida) {
        this.comandanteSaida = comandanteSaida;
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

    public String getNomeDelegado() {
        return nomeDelegado;
    }

    public void setNomeDelegado(String nomeDelegado) {
        this.nomeDelegado = nomeDelegado;
    }

    public String getPatenteDelegado() {
        return patenteDelegado;
    }

    public void setPatenteDelegado(String patenteDelegado) {
        this.patenteDelegado = patenteDelegado;
    }

    public String getNumeroProcessoDespacho() {
        return numeroProcessoDespacho;
    }

    public void setNumeroProcessoDespacho(String numeroProcessoDespacho) {
        this.numeroProcessoDespacho = numeroProcessoDespacho;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public String getAtracacao() {
        return atracacao;
    }

    public void setAtracacao(String atracacao) {
        this.atracacao = atracacao;
    }

    public String getNumeroOficiais() {
        return numeroOficiais;
    }

    public void setNumeroOficiais(String numeroOficiais) {
        this.numeroOficiais = numeroOficiais;
    }

    public TipoSimNao getIsento() {
        return isento;
    }

    public void setIsento(TipoSimNao isento) {
        this.isento = isento;
    }

    public TipoSimNao getCarga() {
        return carga;
    }

    public void setCarga(TipoSimNao carga) {
        this.carga = carga;
    }

    public String getDWT() {
        return DWT;
    }

    public void setDWT(String DWT) {
        this.DWT = DWT;
    }

    public String getConsignatario() {
        return consignatario;
    }

    public void setConsignatario(String consignatario) {
        this.consignatario = consignatario;
    }

    public String getTripulacao() {
        return tripulacao;
    }

    public void setTripulacao(String tripulacao) {
        this.tripulacao = tripulacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

 

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getArmador() {
        return armador;
    }

    public void setArmador(String armador) {
        this.armador = armador;
    }

    public String getTonBruta() {
        return tonBruta;
    }

    public void setTonBruta(String tonBruta) {
        this.tonBruta = tonBruta;
    }

    public String getTonLiquida() {
        return tonLiquida;
    }

    public void setTonLiquida(String tonLiquida) {
        this.tonLiquida = tonLiquida;
    }

    public String getDataTUF() {
        return dataTUF;
    }

    public void setDataTUF(String dataTUF) {
        this.dataTUF = dataTUF;
    }

    public String getValorTUF() {
        return valorTUF;
    }

    public void setValorTUF(String valorTUF) {
        this.valorTUF = valorTUF;
    }

    public String getNumeroTUF() {
        return numeroTUF;
    }

    public void setNumeroTUF(String numeroTUF) {
        this.numeroTUF = numeroTUF;
    }

    public String getValorTUFExtenso() {
        return valorTUFExtenso;
    }

    public void setValorTUFExtenso(String valorTUFExtenso) {
        this.valorTUFExtenso = valorTUFExtenso;
    }

    public String getNomeCPFAgente() {
        return nomeCPFAgente;
    }

    public void setNomeCPFAgente(String nomeCPFAgente) {
        this.nomeCPFAgente = nomeCPFAgente;
    }
    
    

    
}
