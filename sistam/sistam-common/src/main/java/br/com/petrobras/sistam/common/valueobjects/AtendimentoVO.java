package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import java.io.Serializable;
import java.util.Date;

public class AtendimentoVO implements Serializable {
    
    private Agenciamento agenciamento;
    private Date dataInicial;
    private Date dataFinal;
    private Integer cicloDias;
    private Integer quantidadeDias;

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getCicloDias() {
        return cicloDias;
    }

    public void setCicloDias(Integer cicloDias) {
        this.cicloDias = cicloDias;
    }

    public Integer getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }
    
}
