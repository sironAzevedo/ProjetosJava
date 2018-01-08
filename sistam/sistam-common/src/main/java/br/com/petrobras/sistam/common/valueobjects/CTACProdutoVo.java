package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;

/**
 *
 */
public class CTACProdutoVo implements Serializable {
    
    private String descricaoProduto;
    private Double peso;
    private Double volume;
    private Double frete;
    private String conhecimentoEmbarque;
    
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

    public String getConhecimentoEmbarque() {
        return conhecimentoEmbarque;
    }

    public void setConhecimentoEmbarque(String conhecimentoEmbarque) {
        this.conhecimentoEmbarque = conhecimentoEmbarque;
    }
    
}