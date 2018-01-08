package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe que contém as informações para geração do formulário Parte de Entrada
 */
public class ParteDeEntradaCapitaniaVo implements Serializable{
    private String duv;
    private String portoChegada;
    private String nomeEmbarcacao;
    private String irin;
    private String bandeira;
    private String anoConstrucao;
    private Long arqueacaoBruta;
    private String imo;
    private String numeroInscricao;
    private Long tonelagemPortoBruto;
    private String tipoEmbarcacao;
    private String areaNavegacaoEmbarcacao;
    private String portoOrigem;
    private String dataHoraChegada;
    private String dataHoraEstimadaSaida;
    private String proximoDestino;
    private String localizacaoNoPorto;
    private String informacoesAgente;
    private String dataUltimaInspecao;
    private String paisInspecao;
    private String dataValidadeDeclaracaoConformidade;
    private String dataValidadeDeclaracaoProvisoria;
    private String municipio;
    private String dataAssinatura;
    private Date dataDocumento;
    
    private boolean possuiHeliporto;
    private boolean deficienciaNestePorto;
    private boolean cargaConves;
    private boolean cargaPerigosa;
    private boolean navioGraneleiroOuCombinado;
    
    private String numeroProcessoDespacho;
    
    

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
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
    
    public String getAnoConstrucao() {
        return anoConstrucao;
    }
    
    public void setAnoConstrucao(String anoConstrucao) {
        this.anoConstrucao = anoConstrucao;
    }
    
    public Long getArqueacaoBruta() {
        return arqueacaoBruta;
    }
    
    public void setArqueacaoBruta(Long arqueacaoBruta) {
        this.arqueacaoBruta = arqueacaoBruta;
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
    
    public Long getTonelagemPortoBruto() {
        return tonelagemPortoBruto;
    }
    
    public void setTonelagemPortoBruto(Long tonelagemPortoBruto) {
        this.tonelagemPortoBruto = tonelagemPortoBruto;
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
    
    public String getPortoOrigem() {
        return portoOrigem;
    }
    
    public void setPortoOrigem(String portoOrigem) {
        this.portoOrigem = portoOrigem;
    }
    
    public String getDataHoraChegada() {
        return dataHoraChegada;
    }
    
    public void setDataHoraChegada(String dataHoraChegada) {
        this.dataHoraChegada = dataHoraChegada;
    }
    
    public String getDataHoraEstimadaSaida() {
        return dataHoraEstimadaSaida;
    }
    
    public void setDataHoraEstimadaSaida(String dataHoraEstimadaSaida) {
        this.dataHoraEstimadaSaida = dataHoraEstimadaSaida;
    }
    
    public String getProximoDestino() {
        return proximoDestino;
    }
    
    public void setProximoDestino(String proximoDestino) {
        this.proximoDestino = proximoDestino;
    }
    
    public String getLocalizacaoNoPorto() {
        return localizacaoNoPorto;
    }
    
    public void setLocalizacaoNoPorto(String localizacaoNoPorto) {
        this.localizacaoNoPorto = localizacaoNoPorto;
    }
    
    public String getInformacoesAgente() {
        return informacoesAgente;
    }
    
    public void setInformacoesAgente(String informacoesAgente) {
        this.informacoesAgente = informacoesAgente;
    }
    
    public String getDataUltimaInspecao() {
        return dataUltimaInspecao;
    }
    
    public void setDataUltimaInspecao(String dataUltimaInspecao) {
        this.dataUltimaInspecao = dataUltimaInspecao;
    }
    
    public String getPaisInspecao() {
        return paisInspecao;
    }
    
    public void setPaisInspecao(String paisInspecao) {
        this.paisInspecao = paisInspecao;
    }
    
    public String getDataValidadeDeclaracaoConformidade() {
        return dataValidadeDeclaracaoConformidade;
    }
    
    public void setDataValidadeDeclaracaoConformidade(String dataValidadeDeclaracaoConformidade) {
        this.dataValidadeDeclaracaoConformidade = dataValidadeDeclaracaoConformidade;
    }
    
    public String getDataValidadeDeclaracaoProvisoria() {
        return dataValidadeDeclaracaoProvisoria;
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
    
    public void setDataValidadeDeclaracaoProvisoria(String dataValidadeDeclaracaoProvisoria) {
        this.dataValidadeDeclaracaoProvisoria = dataValidadeDeclaracaoProvisoria;
    }
    
    public boolean isPossuiHeliporto() {
        return possuiHeliporto;
    }
    
    public void setPossuiHeliporto(boolean possuiHeliporto) {
        this.possuiHeliporto = possuiHeliporto;
    }
    
    public boolean isDeficienciaNestePorto() {
        return deficienciaNestePorto;
    }
    
    public void setDeficienciaNestePorto(boolean deficienciaNestePorto) {
        this.deficienciaNestePorto = deficienciaNestePorto;
    }
    
    public boolean isCargaConves() {
        return cargaConves;
    }
    
    public void setCargaConves(boolean cargaConves) {
        this.cargaConves = cargaConves;
    }
    
    public boolean isCargaPerigosa() {
        return cargaPerigosa;
    }
    
    public void setCargaPerigosa(boolean cargaPerigosa) {
        this.cargaPerigosa = cargaPerigosa;
    }
    
    public boolean isNavioGraneleiroOuCombinado() {
        return navioGraneleiroOuCombinado;
    }
    
    public void setNavioGraneleiroOuCombinado(boolean navioGraneleiroOuCombinado) {
        this.navioGraneleiroOuCombinado = navioGraneleiroOuCombinado;
    }
    
    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    } 
    
    
    
    //</editor-fold>

    public String getNumeroProcessoDespacho() {
        return numeroProcessoDespacho;
    }

    public void setNumeroProcessoDespacho(String numeroProcessoDespacho) {
        this.numeroProcessoDespacho = numeroProcessoDespacho;
    }
    
    
    
}
