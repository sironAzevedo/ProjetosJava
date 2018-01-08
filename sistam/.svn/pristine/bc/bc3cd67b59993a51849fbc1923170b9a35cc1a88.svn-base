package br.com.petrobras.sistam.desktop.components.lookups.timezone;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamLookupTextfield;
import java.util.List;
import java.util.TimeZone;

public abstract class TimeZoneInput extends SistamLookupTextfield<TimeZone> {

    private TimeZoneInputDialog dialog;
    private TimeZone timeZoneSelecionado;

    public TimeZoneInput() {
        setText("");
    }

    @Override
    public void showDialog() {
        showDialog(null, null);
    }

    public void showDialog(List l, String nome) {
        dialog = new TimeZoneInputDialog(BaseApp.getApplication().getMainFrame());
        if (null == l) {
            if (nome != null && !nome.isEmpty()) {
                l = this.buscarPorNome(nome);
            }
        }
        dialog.getModel().setTimeZones(l);
        dialog.getModel().setNome(nome);
        dialog.getModel().setComboPai(this);
        dialog.setVisible(true);
        if (dialog.getModel().isConfirmado()) {
            setValue(dialog.getModel().getTimeZoneSelecionado());
        } else {
            setValue(null);
        }
        dialog = null;
    }

    @Override
    public void guessValue() {
        // Isso evita ficar reabrindo o diálogo ao perder o foco
        if ((dialog != null && dialog.isVisible()) || !this.isVisible()) {
            return;
        }

        String texto = this.getText().trim().toUpperCase();

        AssertUtils.assertExpression(texto != null && texto.length() >= 3, ConstantesI18N.CONSULTA_LOOKUP);
        List<TimeZone> timeZones = buscarPorNome(texto);
        AssertUtils.assertNotNullNotEmpty(timeZones, ConstantesI18N.NENHUM_TIMEZONE_ENCONTRADO);
        if (timeZones != null && timeZones.size() == 1) {
            setValue(timeZones.get(0));
            setTimeZoneSelecionado((TimeZone) timeZones.get(0));
        } else {
            showDialog(timeZones, texto);
        }
    }

    @Override
    protected String valueToText(TimeZone value) {
        return value.getID();
    }

    public TimeZone getTimeZoneSelecionado() {
        return timeZoneSelecionado;
    }

    public void setTimeZoneSelecionado(TimeZone timeZoneSelecionado) {
        this.timeZoneSelecionado = timeZoneSelecionado;
        firePropertyChange("timeZoneSelecionado", null, this.timeZoneSelecionado);
    }

    protected abstract List<TimeZone> buscarPorNome(String nome);
}
