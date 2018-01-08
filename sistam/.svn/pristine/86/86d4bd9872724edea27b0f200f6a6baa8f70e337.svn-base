package br.com.petrobras.sistam.common.valueobjects;

import static br.com.petrobras.sistam.common.util.SistamDateUtils.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author adzk
 */
public class Periodo implements Serializable {

    private Date dataInicio;
    private Date dataFim;

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isDataInicioPreenchida() {
        return dataInicio != null;
    }

    public boolean isDataFimPreenchida() {
        return dataFim != null;
    }

    public boolean isDataFimMenorQueDataInicio() {
        return truncateTime(dataFim, null).before(truncateTime(dataInicio, null));
    }

    public boolean isDataHoraFimMenorQueDataHoraInicio() {
        return dataFim.before(dataInicio);
    }

    public Date getDataInicioNaPrimeiraHoraDoDia() {
        return !isDataInicioPreenchida() ? null : alterarHorarioParaInicioDia(dataInicio, null);
    }

    public Date getDataFimNaUltimaHoraDoDia() {
        return !isDataFimPreenchida() ? null : alterarHorarioParaFimDia(dataFim, null);
    }

    public String getDataPeriodoDescricao() {
        if (isDataInicioPreenchida() && isDataFimPreenchida()) {
            return formatDate(dataInicio, DATE_PATTERN, null) + " até " + formatDate(dataFim, DATE_PATTERN, null);
        } else if (isDataInicioPreenchida()) {
            return "A partir de " + formatDate(dataInicio, DATE_PATTERN, null);
        } else if (isDataFimPreenchida()) {
            return "Até " + formatDate(dataFim, DATE_PATTERN, null);
        }
        return "-";
    }

    @Override
    public String toString() {
        if (dataInicio != null && dataFim != null) {
            return formatDateComplete(dataInicio, null) + " até " + formatDateComplete(dataFim, null);
        } else if (dataInicio != null) {
            return "A partir de " + formatDateComplete(dataInicio, null);
        } else if (dataFim != null) {
            return "Até " + formatDateComplete(dataFim, null);
        }
        return "";
    }
}
