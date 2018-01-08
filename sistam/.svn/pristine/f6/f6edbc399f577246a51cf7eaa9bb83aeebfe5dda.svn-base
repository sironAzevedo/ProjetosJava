package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class SuprimentoTransitoFornecedorVO extends AbstractBean implements Serializable{
    
    private String nomePrestadorServico="";
    private String pesoBruto;
    private Integer Volume;
    private String notaFiscal;    
    private Integer numeracao;
    
    
    public SuprimentoTransitoFornecedorVO(Integer numeracao){
        this.numeracao = numeracao;
    } 
    
    public SuprimentoTransitoFornecedorVO(){}
    
    public Integer getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Integer numeracao) {
        this.numeracao = numeracao;
        firePropertyChange("numeracao", null, null);
    }

    public String getNomePrestadorServico() {
        return nomePrestadorServico;
    }

    public void setNomePrestadorServico(String nomePrestadorServico) {
        this.nomePrestadorServico = nomePrestadorServico;
        firePropertyChange("nomePrestadorServico", null, null);
    }

    public String getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(String pesoBruto) {
        this.pesoBruto = pesoBruto;
        firePropertyChange("pesoBruto", null, null);
    }

    public Integer getVolume() {
        return Volume;
    }

    public void setVolume(Integer Volume) {
        this.Volume = Volume;
        firePropertyChange("Volume", null, null);
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
        firePropertyChange("notaFiscal", null, null);
    } 
    
} 