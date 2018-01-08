package br.com.petrobras.sistam.desktop.components.lookups.timezone;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public class TimeZoneInputPresentationModel extends AbstractBean {

    private List<TimeZone> timeZones;
    private TimeZone timeZoneSelecionado;
    private boolean confirmado;
    private TimeZoneInput comboPai;
    private String nome;

    public TimeZoneInputPresentationModel() {
        timeZones = Collections.<TimeZone>emptyList();
    }

    public void consultar() {
        AssertUtils.assertExpression(nome != null && nome.length() >= 3, ConstantesI18N.CONSULTA_LOOKUP);
        List l = comboPai.buscarPorNome(nome);
        setTimeZones(l);
        AssertUtils.assertNotNullNotEmpty(l, ConstantesI18N.NENHUM_TIMEZONE_ENCONTRADO);
    }

    public List<TimeZone> getTimeZones() {
        return timeZones;
    }

    public TimeZone getTimeZoneSelecionado() {
        return timeZoneSelecionado;
    }

    public TimeZoneInput getComboPai() {
        return comboPai;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setTimeZones(List<TimeZone> timeZones) {
        this.timeZones = timeZones;
        firePropertyChange("timeZones", null, this.timeZones);
    }

    public void setTimeZoneSelecionado(TimeZone timeZoneSelecionado) {
        this.timeZoneSelecionado = timeZoneSelecionado;
        firePropertyChange("timeZoneSelecionado", null, this.timeZoneSelecionado);
    }

    public void setComboPai(TimeZoneInput comboPai) {
        this.comboPai = comboPai;
        firePropertyChange("comboPai", null, this.comboPai);
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
        firePropertyChange("confirmado", null, this.confirmado);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, this.nome);
    }
}