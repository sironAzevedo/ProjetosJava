package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.AreaNavegacao;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public class ConsultaAgenciamentoModel extends BasePresentationModel<SistamService> {

    private FiltroAgenciamento filtro = new FiltroAgenciamento();
    private List<Agencia> agencias;
    private List<Porto> portos;
    private List<StatusEmbarcacao> listaStatus;
    private List<TipoContrato> tiposContrato;
    private List<AreaNavegacao> areasNavegacao;
    private List<Agenciamento> agenciamentos;
    private Agenciamento agenciamentoSelecinado;
    private TimeZone timeZoneSelecionado;

    public ConsultaAgenciamentoModel() {
        filtro.addPropertyChangeListener(this);

        tiposContrato = new ArrayList<TipoContrato>(Arrays.asList(TipoContrato.values()));
        tiposContrato.add(0, null);

        listaStatus = new ArrayList<StatusEmbarcacao>(Arrays.asList(StatusEmbarcacao.values()));
        listaStatus.add(0, null);

        areasNavegacao = new ArrayList<AreaNavegacao>(Arrays.asList(AreaNavegacao.values()));
        areasNavegacao.add(0, null);

        agenciamentos = Collections.EMPTY_LIST;

    }

    public List<Agenciamento> getAgenciamentos() {
        return agenciamentos;
    }

    public void setAgenciamentos(List<Agenciamento> agenciamentos) {
        this.agenciamentos = agenciamentos;
        firePropertyChange("agenciamentos", null, this.agenciamentos);
    }

    public Agenciamento getAgenciamentoSelecinado() {
        return agenciamentoSelecinado;
    }

    public void setAgenciamentoSelecinado(Agenciamento agenciamentoSelecinado) {
        this.agenciamentoSelecinado = agenciamentoSelecinado;
        firePropertyChange("agenciamentoSelecinado", null, this.agenciamentoSelecinado);
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
        firePropertyChange("agencias", null, this.agencias);
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
        firePropertyChange("portos", null, this.portos);
    }

    public FiltroAgenciamento getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroAgenciamento filtro) {
        this.filtro = filtro;
        firePropertyChange("filtro", null, this.filtro);
    }

    public List<StatusEmbarcacao> getListaStatus() {
        return listaStatus;
    }

    public List<TipoContrato> getTiposContrato() {
        return tiposContrato;
    }

    public List<AreaNavegacao> getAreasNavegacao() {
        return areasNavegacao;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("agencia")) {
            List<Porto> portos = new ArrayList<Porto>();
            if (filtro.getAgencia() != null) {
                portos = new ArrayList<Porto>(filtro.getAgencia().getPortos());
                portos.add(0, null);
                setTimeZoneSelecionado(TimeZone.getTimeZone(filtro.getAgencia().getTimezone()));
            } else {
                setTimeZoneSelecionado(null);
            }
            
            setPortos(portos);
        }
    }
    
    public boolean validarVisualizacaoConsultaAgenciamento(){
        if (getService().validarPermissao(agenciamentoSelecinado.getAgencia(), RecursoCA.VISUALIZAR_AGENCIAMENTO_ADM)){
            return true;
        }
        return false;
    } 
    
    public void carregarAgencias() {
        List<Agencia> agencias = getService().buscarAgencias();
        agencias.add(0, null);
        setAgencias(agencias);
    }

    public void consultar() {
        setAgenciamentos(Collections.EMPTY_LIST);
        setAgenciamentos(getService().buscarAgenciamentosPorFiltro(filtro));
    }

    public Agenciamento buscarAgenciamentoParaConsulta() {
        return getService().buscarAgenciamentoPorId(agenciamentoSelecinado.getId());
    }

    public TimeZone getTimeZoneSelecionado() {
        return timeZoneSelecionado;
    }

    public void setTimeZoneSelecionado(TimeZone timeZoneSelecionado) {
        this.timeZoneSelecionado = timeZoneSelecionado;
        firePropertyChange("timeZoneSelecionado", null, this.timeZoneSelecionado);
    }
}
