package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import java.util.TimeZone;

public class DetalheAgenciaModel extends BasePresentationModel<SistamService> {
    private Agencia agencia;
    private TimeZone timeZoneSelecionado;
    
    public DetalheAgenciaModel(Agencia agencia){
        this.agencia = agencia;
    }
    
    public void salvar() {
        if (timeZoneSelecionado != null) {
            agencia.setTimezone(timeZoneSelecionado.getID());
        }    
        agencia = getService().salvarAgencia(agencia);
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, this.agencia);
    }

    public TimeZone getTimeZoneSelecionado() {
        return timeZoneSelecionado;
    }

    public void setTimeZoneSelecionado(TimeZone timeZoneSelecionado) {
        this.timeZoneSelecionado = timeZoneSelecionado;
        firePropertyChange("timeZoneSelecionado", null, this.timeZoneSelecionado);
    }
    
    
    
}
