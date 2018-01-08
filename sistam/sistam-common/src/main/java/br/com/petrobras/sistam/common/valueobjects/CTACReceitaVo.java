package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Porto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contém as informações para geração do formulário CTAC
 */
public class CTACReceitaVo implements Serializable{
    
    
    private String conhecimentoEmbarque;
    private String nomeEmbarcacao;
    private String imo;
    private String viagem;
    private String areaNavegacaoEmbarcacao;
    private String portoEmbarque;
    private String portoDesembarque;
    private Porto portoDesembarqueDestino;
    
    private String petrobras;
    private String petrobrasCep;
    private String petrobrasBairroUf;
    
    private String embarcadorEndereco;
    private String embarcadorMunicipio;
    private String embarcadorUF;
    private String embarcadorCNPJ;
    private String embarcadorInscEstadual;
    
    private String destinatarioEndereco;
    private String destinatarioMunicipio;
    private String destinatarioUF;
    private String destinatarioCNPJ;
    private String destinatarioInscEstadual;

    private String consignatarioEndereco;
    private String consignatarioMunicipio;
    private String consignatarioUF;
    private String consignatarioCNPJ;
    private String consignatarioInscEstadual;
    
    private String descricaoProduto;
    private Double peso;
    private Double volume;
    private Double frete;
    
    private List<CTACProdutoVo> produtos = new ArrayList<CTACProdutoVo>();
    private Double totalFrete;
    
    private String municipio;
    private String dataAssinatura;
    
    private int diaEmissao;
    private int mesEmissao;
    private int anoEmissao;
    
    private String emitente;
    private String observacao;

    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getConhecimentoEmbarque() {
        return conhecimentoEmbarque;
    }

    public void setConhecimentoEmbarque(String conhecimentoEmbarque) {
        this.conhecimentoEmbarque = conhecimentoEmbarque;
    }

    public Porto getPortoDesembarqueDestino() {
        return portoDesembarqueDestino;
    }

    public void setPortoDesembarqueDestino(Porto portoDesembarqueDestino) {
        this.portoDesembarqueDestino = portoDesembarqueDestino;
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

    public Double getTotalFrete() {
        return totalFrete;
    }

    public void setTotalFrete(Double totalFrete) {
        this.totalFrete = totalFrete;
    }

    public String getViagem() {
        return viagem;
    }

    public void setViagem(String viagem) {
        this.viagem = viagem;
    }

    public String getAreaNavegacaoEmbarcacao() {
        return areaNavegacaoEmbarcacao;
    }

    public void setAreaNavegacaoEmbarcacao(String areaNavegacaoEmbarcacao) {
        this.areaNavegacaoEmbarcacao = areaNavegacaoEmbarcacao;
    }

    public String getPortoEmbarque() {
        return portoEmbarque;
    }

    public void setPortoEmbarque(String portoEmbarque) {
        this.portoEmbarque = portoEmbarque;
    }

    public String getPortoDesembarque() {
        return portoDesembarque;
    }

    public void setPortoDesembarque(String portoDesembarque) {
        this.portoDesembarque = portoDesembarque;
    }

    public String getPetrobras() {
        return petrobras;
    }

    public void setPetrobras(String petrobras) {
        this.petrobras = petrobras;
    }

    public String getPetrobrasCep() {
        return petrobrasCep;
    }

    public void setPetrobrasCep(String petrobrasCep) {
        this.petrobrasCep = petrobrasCep;
    }

    public String getPetrobrasBairroUf() {
        return petrobrasBairroUf;
    }

    public void setPetrobrasBairroUf(String petrobrasBairroUf) {
        this.petrobrasBairroUf = petrobrasBairroUf;
    }
    
    public String getEmbarcadorEndereco() {
        return embarcadorEndereco;
    }

    public void setEmbarcadorEndereco(String embarcadorEndereco) {
        this.embarcadorEndereco = embarcadorEndereco;
    }

    public String getEmbarcadorMunicipio() {
        return embarcadorMunicipio;
    }

    public void setEmbarcadorMunicipio(String embarcadorMunicipio) {
        this.embarcadorMunicipio = embarcadorMunicipio;
    }

    public String getEmbarcadorUF() {
        return embarcadorUF;
    }

    public void setEmbarcadorUF(String embarcadorUF) {
        this.embarcadorUF = embarcadorUF;
    }

    public String getEmbarcadorCNPJ() {
        return embarcadorCNPJ;
    }

    public void setEmbarcadorCNPJ(String embarcadorCNPJ) {
        this.embarcadorCNPJ = embarcadorCNPJ;
    }

    public String getEmbarcadorInscEstadual() {
        return embarcadorInscEstadual;
    }

    public void setEmbarcadorInscEstadual(String embarcadorInscEstadual) {
        this.embarcadorInscEstadual = embarcadorInscEstadual;
    }

    public String getDestinatarioEndereco() {
        return destinatarioEndereco;
    }

    public void setDestinatarioEndereco(String destinatarioEndereco) {
        this.destinatarioEndereco = destinatarioEndereco;
    }

    public String getDestinatarioMunicipio() {
        return destinatarioMunicipio;
    }

    public void setDestinatarioMunicipio(String destinatarioMunicipio) {
        this.destinatarioMunicipio = destinatarioMunicipio;
    }

    public String getDestinatarioUF() {
        return destinatarioUF;
    }

    public void setDestinatarioUF(String destinatarioUF) {
        this.destinatarioUF = destinatarioUF;
    }

    public String getDestinatarioCNPJ() {
        return destinatarioCNPJ;
    }

    public void setDestinatarioCNPJ(String destinatarioCNPJ) {
        this.destinatarioCNPJ = destinatarioCNPJ;
    }

    public String getDestinatarioInscEstadual() {
        return destinatarioInscEstadual;
    }

    public void setDestinatarioInscEstadual(String destinatarioInscEstadual) {
        this.destinatarioInscEstadual = destinatarioInscEstadual;
    }

    public String getConsignatarioEndereco() {
        return consignatarioEndereco;
    }

    public void setConsignatarioEndereco(String consignatarioEndereco) {
        this.consignatarioEndereco = consignatarioEndereco;
    }

    public String getConsignatarioMunicipio() {
        return consignatarioMunicipio;
    }

    public void setConsignatarioMunicipio(String consignatarioMunicipio) {
        this.consignatarioMunicipio = consignatarioMunicipio;
    }

    public String getConsignatarioUF() {
        return consignatarioUF;
    }

    public void setConsignatarioUF(String consignatarioUF) {
        this.consignatarioUF = consignatarioUF;
    }

    public String getConsignatarioCNPJ() {
        return consignatarioCNPJ;
    }

    public void setConsignatarioCNPJ(String consignatarioCNPJ) {
        this.consignatarioCNPJ = consignatarioCNPJ;
    }

    public String getConsignatarioInscEstadual() {
        return consignatarioInscEstadual;
    }

    public void setConsignatarioInscEstadual(String consignatarioInscEstadual) {
        this.consignatarioInscEstadual = consignatarioInscEstadual;
    }

    public List<CTACProdutoVo> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CTACProdutoVo> produtos) {
        this.produtos = produtos;
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
   

    public String getEmitente() {
        return emitente;
    }

    public void setEmitente(String emitente) {
        this.emitente = emitente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    public int getDiaEmissao() {
        return diaEmissao;
    }

    public void setDiaEmissao(int diaEmissao) {
        this.diaEmissao = diaEmissao;
    }

    public int getMesEmissao() {
        return mesEmissao;
    }

    public void setMesEmissao(int mesEmissao) {
        this.mesEmissao = mesEmissao;
    }

    public int getAnoEmissao() {
        return anoEmissao;
    }

    public void setAnoEmissao(int anoEmissao) {
        this.anoEmissao = anoEmissao;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }
    
    //</editor-fold>

}
