package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FiltroRelatorioManobra extends AbstractBean {

    public static final String PROP_AGENCIA = "agencia";
    
    private Agencia agencia;
    private Porto porto;
    private List<ResponsavelCustoEntity> responsaveis = Collections.EMPTY_LIST;
    private Date periodoInicio;
    private Date periodoTermino;
    private TipoContrato tipoContrato;

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, null);
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public List<ResponsavelCustoEntity> getResponsaveis() { 
        return responsaveis;
    }

    public void setResponsaveis(List<ResponsavelCustoEntity> responsaveis) {
        this.responsaveis = responsaveis;
        firePropertyChange("responsaveis", null, null);
    }

    public Date getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(Date periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public Date getPeriodoTermino() {
        return periodoTermino;
    }

    public void setPeriodoTermino(Date periodoTermino) {
        this.periodoTermino = periodoTermino;
    }
    
    public String getPeriodoInicioFormatado(){
        return periodoInicio == null ? "" : SistamDateUtils.formatDate(periodoInicio, SistamDateUtils.DATE_PATTERN, null);
    }
    
    public String getPeriodoTerminoFormatado(){
        return periodoTermino == null ? "" : SistamDateUtils.formatDate(periodoTermino, SistamDateUtils.DATE_PATTERN, null);
    }   
    
    public String getResponsaveisDescricao(){
        StringBuilder nomes = new StringBuilder();
        for (ResponsavelCustoEntity responsavel : responsaveis) {
            if (nomes.length() != 0)
                nomes.append(", ");
            nomes.append(responsavel.getDescricao());
        }
        return nomes.toString();
    }

}