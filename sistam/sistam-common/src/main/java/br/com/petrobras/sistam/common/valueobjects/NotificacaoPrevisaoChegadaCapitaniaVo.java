package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Escala;
import java.io.Serializable;
import java.util.List;

/**
 * Classe que contém as informações para geração do formulário Notificação de Previsão de Chegada
 */
public class NotificacaoPrevisaoChegadaCapitaniaVo implements Serializable{
    private String processoDespacho;
    private String duv;
    private String portoChegada;
    private String dataHoraPrevisaoChegada;
    
    private String nomeEmbarcacao;
    private String irin;
    private String bandeira;
    
    private String imo;
    private String numeroInscricao;
    private Long arqueacaoBruta;
    private String tipoEmbarcacao;
    private String areaNavegacaoEmbarcacao;

    private String informacoesAgente;
    private String sociedadClassificadora;
    private String armador;

    private String municipio;
    private String dataAssinatura;
    
    // incluir as  informaç~eos das ultimas 5 escalas conforme formulário//
    private List<Escala> portosVisitados;
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
  
    public String getProcessoDespacho() {
        return processoDespacho;
    }

    public void setProcessoDespacho(String processoDespacho) {
        this.processoDespacho = processoDespacho;
    }

    public String getDuv() {
        return duv;
    }

    public void setDuv(String duv) {
        this.duv = duv;
    }

    public String getPortoChegada() {
        return portoChegada;
    }

    public void setPortoChegada(String portoChegada) {
        this.portoChegada = portoChegada;
    }

    public String getDataHoraPrevisaoChegada() {
        return dataHoraPrevisaoChegada;
    }

    public void setDataHoraPrevisaoChegada(String dataHoraPrevisaoChegada) {
        this.dataHoraPrevisaoChegada = dataHoraPrevisaoChegada;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getIrin() {
        return irin;
    }

    public void setIrin(String irin) {
        this.irin = irin;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
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

    public Long getArqueacaoBruta() {
        return arqueacaoBruta;
    }

    public void setArqueacaoBruta(Long arqueacaoBruta) {
        this.arqueacaoBruta = arqueacaoBruta;
    }

    public String getTipoEmbarcacao() {
        return tipoEmbarcacao;
    }

    public void setTipoEmbarcacao(String tipoEmbarcacao) {
        this.tipoEmbarcacao = tipoEmbarcacao;
    }

    public String getAreaNavegacaoEmbarcacao() {
        return areaNavegacaoEmbarcacao;
    }

    public void setAreaNavegacaoEmbarcacao(String areaNavegacaoEmbarcacao) {
        this.areaNavegacaoEmbarcacao = areaNavegacaoEmbarcacao;
    }

    public String getInformacoesAgente() {
        return informacoesAgente;
    }

    public void setInformacoesAgente(String informacoesAgente) {
        this.informacoesAgente = informacoesAgente;
    }

    public String getSociedadClassificadora() {
        return sociedadClassificadora;
    }

    public void setSociedadClassificadora(String sociedadClassificadora) {
        this.sociedadClassificadora = sociedadClassificadora;
    }

    public String getArmador() {
        return armador;
    }

    public void setArmador(String armador) {
        this.armador = armador;
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
    
    public void setPortosVisitados(List<Escala> portosVisitados) {
        this.portosVisitados = portosVisitados;
    }
    
    public Escala getPortoVisitado1(){
        if (portosVisitados.size() >= 1){
            return portosVisitados.get(0);
        }
        return null;
    }
    
    public Escala getPortoVisitado2(){
        if (portosVisitados.size() >= 2){
            return portosVisitados.get(1);
        }
        return null;
    }
    
    public Escala getPortoVisitado3(){
        if (portosVisitados.size() >= 3){
            return portosVisitados.get(2);
        }
        return null;
    }
    
    public Escala getPortoVisitado4(){
        if (portosVisitados.size() >= 4){
            return portosVisitados.get(3);
        }
        return null;
    }
    
    public Escala getPortoVisitado5(){
        if (portosVisitados.size() >= 5){
            return portosVisitados.get(4);
        }
        return null;
    }
    
    //</editor-fold>
    
   
     
    
}
