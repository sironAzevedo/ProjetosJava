package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;

public class FiltroAgenciamentoAtendimento extends AbstractBean implements Serializable {
    Agencia agencia;
    Porto porto;
    TipoContrato tipoContrato;
    Integer qtdeDiasAtendimento = 7;
    Date dataInicial;
    Date dataFinal;
    Integer mesInicial;
    Integer anoInicial;
    Integer mesFinal;
    Integer anoFinal; 
    
    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, null);
    }

    public Integer getMesInicial() {
        return SistamDateUtils.getMonthDate(getDataInicial(), null);
    }

    public Integer getAnoInicial() {
        return SistamDateUtils.getYearDate(getDataInicial(), null);
    }

    public Integer getMesFinal() {
        return SistamDateUtils.getMonthDate(getDataFinal(), null);
    }

    public Integer getAnoFinal() {
        return SistamDateUtils.getYearDate(getDataFinal(), null);
    }

    public void setAnoFinal(Date data) {
        this.anoFinal = SistamDateUtils.getYearDate(data, null);
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
        firePropertyChange("tipoContrato", null, null);
        
    }

    public Integer getQtdeDiasAtendimento() {
        return qtdeDiasAtendimento;
    }

    public void setQtdeDiasAtendimento(Integer qtdeDiasAtendimento) {
        this.qtdeDiasAtendimento = qtdeDiasAtendimento;
        firePropertyChange("qtdeDiasAtendimento", null, null);
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
        firePropertyChange("dataInicial", null, null);
    }
    
    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
        firePropertyChange("dataFinal", null, null);
    }
    
    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }  
}
