package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.RelatorioMovimentacaoEmbarcacaoVO;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioMovimentacaoEmbarcacaoModel extends BasePresentationModel<SistamService> {

    private List<Agencia> agencias;
    private List<Porto> portos;
    private Porto portoSelecionado;
    private Agencia agenciaSelecionada;
    private List<Destinatario> destinatarios;

    public RelatorioMovimentacaoEmbarcacaoModel() {
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean()); 
        agencias = contexto.getAgenciasAutorizadas();
        agencias.add(0, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
        firePropertyChange("portos", null, null);
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public void setPortoSelecionado(Porto portoSelecionado) {
        this.portoSelecionado = portoSelecionado;
        firePropertyChange("portoSelecionado", null, this.portoSelecionado);
    }

    public Porto getPortoSelecionado() {
        return portoSelecionado;
    }

    public Agencia getAgenciaSelecionada() {
        return agenciaSelecionada;
    }

    public void setAgenciaSelecionada(Agencia agenciaSelecionada) {
        Object old = this.agenciaSelecionada;
        this.agenciaSelecionada = agenciaSelecionada;
        firePropertyChange("agenciaSelecionada", old, this.agenciaSelecionada);
        carregarDestinatarios();
        carregarPorto();
    }

    public List<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Destinatario> destinatarios) {
        Object old = this.destinatarios;
        this.destinatarios = destinatarios;
        firePropertyChange("destinatarios", old, this.destinatarios);
    }

    //</editor-fold>
    private void carregarDestinatarios() {
        if (agenciaSelecionada == null) {
            setDestinatarios(new ArrayList<Destinatario>());
        } else {
            setDestinatarios(getService().buscarDestinatariosDaAgencia(agenciaSelecionada));
        }
    }

    private void carregarPorto() {
        List<Porto> lista = new ArrayList<Porto>();
        if (agenciaSelecionada != null) {
            lista = getService().buscarPortosPorAgencia(agenciaSelecionada);
            lista.add(0, null);
        }
        setPortos(lista);
    }

    public RelatorioMovimentacaoEmbarcacaoVO obterVOParaRelatorio() {
        return getService().buscarDadosRelatorioMovimentacaoEmbarcacao(agenciaSelecionada, portoSelecionado);
    }

    public Map<String, Object> obterParametros() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("AGENCIA", agenciaSelecionada.toString());
        parametros.put("AGENCIA_PORTO", portoSelecionado != null ? portoSelecionado.getNomeCompleto() : "Todos");
        parametros.put("DATA_IMPRESSAO", SistamDateUtils.formatDate(new Date(), "dd/MM/yyyy HH:mm", agenciaSelecionada.obterTimezone()));

        return parametros;
    } 
}
