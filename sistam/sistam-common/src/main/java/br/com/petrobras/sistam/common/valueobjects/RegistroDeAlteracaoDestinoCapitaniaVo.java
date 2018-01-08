package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;

/**
 * Classe que contém as informações para geração do formulário Registro de Movimentação da Embarcação.
 */
public class RegistroDeAlteracaoDestinoCapitaniaVo implements Serializable{
    private String duv;
    private String orgaoDespacho;
    private String imo;
    private String numeroInscricao;
    private String nomeEmbarcacao;
    private String informacoesAgente;
    private String portoDestinoAlterado;
    private String portoDestinoEfetivo;
    private String municipio;
    private String dataAssinatura;
    private String doFrom;
    private String aoTo;
    
    
    private boolean reabastecimento;
    private boolean carregamentoOuDescarregamento;
    private boolean servicoMedicoHospitalar;
    private boolean desembarcarCorpo;
    private boolean solicitacaoDeAbrigo;
    private boolean embarcacaoAvariada;

    private String numeroProcessoDespacho;
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    
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
    
    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }
    
    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }
    
    public String getInformacoesAgente() {
        return informacoesAgente;
    }
    
    public void setInformacoesAgente(String informacoesAgente) {
        this.informacoesAgente = informacoesAgente;
    }
    
    public String getPortoDestinoAlterado() {
        return portoDestinoAlterado;
    }
    
    public void setPortoDestinoAlterado(String portoDestinoAlterado) {
        this.portoDestinoAlterado = portoDestinoAlterado;
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
    
    public boolean isReabastecimento() {
        return reabastecimento;
    }
    
    public void setReabastecimento(boolean reabastecimento) {
        this.reabastecimento = reabastecimento;
    }
    
    public boolean isCarregamentoOuDescarregamento() {
        return carregamentoOuDescarregamento;
    }
    
    public void setCarregamentoOuDescarregamento(boolean carregamentoOuDescarregamento) {
        this.carregamentoOuDescarregamento = carregamentoOuDescarregamento;
    }
    
    public boolean isServicoMedicoHospitalar() {
        return servicoMedicoHospitalar;
    }
    
    public void setServicoMedicoHospitalar(boolean servicoMedicoHospitalar) {
        this.servicoMedicoHospitalar = servicoMedicoHospitalar;
    }
    
    public boolean isDesembarcarCorpo() {
        return desembarcarCorpo;
    }
    
    public void setDesembarcarCorpo(boolean desembarcarCorpo) {
        this.desembarcarCorpo = desembarcarCorpo;
    }
    
    public boolean isSolicitacaoDeAbrigo() {
        return solicitacaoDeAbrigo;
    }
    
    public void setSolicitacaoDeAbrigo(boolean solicitacaoDeAbrigo) {
        this.solicitacaoDeAbrigo = solicitacaoDeAbrigo;
    }
    
    public boolean isEmbarcacaoAvariada() {
        return embarcacaoAvariada;
    }
    
    public void setEmbarcacaoAvariada(boolean embarcacaoAvariada) {
        this.embarcacaoAvariada = embarcacaoAvariada;
    }

    public String getPortoDestinoEfetivo() {
        return portoDestinoEfetivo;
    }

    public void setPortoDestinoEfetivo(String portoDestinoEfetivo) {
        this.portoDestinoEfetivo = portoDestinoEfetivo;
    }

    public String getDoFrom() {
        return doFrom;
    }

    public void setDoFrom(String doFrom) {
        this.doFrom = doFrom;
    }

    public String getAoTo() {
        return aoTo;
    }

    public void setAoTo(String aoTo) {
        this.aoTo = aoTo;
    }
    
    public String getNumeroProcessoDespacho() {
        return numeroProcessoDespacho;
    }

    public void setNumeroProcessoDespacho(String numeroProcessoDespacho) {
        this.numeroProcessoDespacho = numeroProcessoDespacho;
    }
    //</editor-fold>
}
