package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import java.io.Serializable;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class SuprimentoTransitoCondutorVO extends AbstractBean implements Serializable{
    
    private String nomeCondutor="";
    private String cpfCondutor;
    private String tipoVeiculo;
    private String placaVeiculo;
    private Integer numeracao;
    
    
    public SuprimentoTransitoCondutorVO(Integer numeracao){
        this.numeracao = numeracao;
    } 
    
    public SuprimentoTransitoCondutorVO(){}

    public Integer getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Integer numeracao) {
        this.numeracao = numeracao;
        firePropertyChange("numeracao", null, null);
    }
    
    public String getNomeCondutor() {
        return nomeCondutor;
    }

    public void setNomeCondutor(String nomeCondutor) {
        this.nomeCondutor = nomeCondutor;
        firePropertyChange("nomeCondutor", null, null);
    }

    public String getCpfCondutor() {
        return cpfCondutor;
    }

    public void setCpfCondutor(String cpfCondutor) {
        this.cpfCondutor = cpfCondutor;
        firePropertyChange("cpfCondutor", null, null);
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
        firePropertyChange("tipoVeiculo", null, null);
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
        firePropertyChange("placaVeiculo", null, null);
    } 
    
} 