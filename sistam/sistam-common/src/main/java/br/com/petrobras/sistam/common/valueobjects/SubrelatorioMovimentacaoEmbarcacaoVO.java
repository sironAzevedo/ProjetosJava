package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import java.io.Serializable;
import java.util.Date;

/**
 * Vo que contém as informações para o subrelatório de Movimentação da
 * Embarcaçãos
 */
public class SubrelatorioMovimentacaoEmbarcacaoVO implements Serializable {

    private String navio;
    private String vgm;
    private String observacoes;
    private String localizacaoAtual;
    private String lp;
    private Date dataEta;
    private Date dataEtaProxPorto;
    private String eta;
    private String etaProxPorto;
    private String saida;
    private Date dataSaida;
    private String chegada;
    private Date dataChegada;
    private String operacao;
    private String quantidade;
    private String destino;
    private String atracacao;
    private Date dataAtracacao;
    private String origem;
    private String motivoCancelamento;
    private String dataCancelamento;
    private Date cancelamento;
    private String destinoAnterior;
    private StatusEmbarcacao statusEmbarcacao;

    public String getNavio() {
        return navio;
    }

    public void setNavio(String navio) {
        this.navio = navio;
    }

    public String getVgm() {
        return vgm;
    }

    public void setVgm(String vgm) {
        this.vgm = vgm;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(String localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public String getLp() {
        return lp;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getEtaProxPorto() {
        return etaProxPorto;
    }

    public void setEtaProxPorto(String etaProxPorto) {
        this.etaProxPorto = etaProxPorto;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAtracacao() {
        return atracacao;
    }

    public void setAtracacao(String atracacao) {
        this.atracacao = atracacao;
    }

    public StatusEmbarcacao getStatusEmbarcacao() {
        return statusEmbarcacao;
    }

    public void setStatusEmbarcacao(StatusEmbarcacao statusEmbarcacao) {
        this.statusEmbarcacao = statusEmbarcacao;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getDestinoAnterior() {
        return destinoAnterior;
    }

    public void setDestinoAnterior(String destinoAnterior) {
        this.destinoAnterior = destinoAnterior;
    }

    public Date getDataEta() {
        return dataEta;
    }

    public void setDataEta(Date dataEta) {
        this.dataEta = dataEta;
    }

    public Date getDataEtaProxPorto() {
        return dataEtaProxPorto;
    }

    public void setDataEtaProxPorto(Date dataEtaProxPorto) {
        this.dataEtaProxPorto = dataEtaProxPorto;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataAtracacao() {
        return dataAtracacao;
    }

    public void setDataAtracacao(Date dataAtracacao) {
        this.dataAtracacao = dataAtracacao;
    }

    public Date getCancelamento() {
        return cancelamento;
    }

    public void setCancelamento(Date cancelamento) {
        this.cancelamento = cancelamento;
    }
}
