package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class RelatorioTimesheetModel extends BasePresentationModel<SistamService> {

    private FiltroRelatorioTimesheet filtro;
    private List<Agencia> agencias = new ArrayList<Agencia>();
    private List<Porto> portos = new ArrayList<Porto>();
    private List<StatusEmbarcacao> situacoesEmbarcacao = new ArrayList<StatusEmbarcacao>();
    private List<TipoFrota> tipoFrota = new ArrayList<TipoFrota>();
    private List<Embarcacao> embarcacoes = new ArrayList<Embarcacao>();

    public RelatorioTimesheetModel() {
        this.filtro = new FiltroRelatorioTimesheet();
        this.filtro.addPropertyChangeListener(this);

        SistamCredentialsBean credentialsBean = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        this.agencias = credentialsBean.getAgenciasAutorizadas();
        
        this.embarcacoes = getService().buscarEmbarcacoesPorFiltro(filtro, agencias);

        this.agencias.add(0, null);

        this.situacoesEmbarcacao.addAll(StatusEmbarcacao.listValuesExcetoCancelado());
        this.situacoesEmbarcacao.add(0, null);
        
        this.tipoFrota.addAll(TipoFrota.listValues());
        this.tipoFrota.add(0, null);
    }
    
    private void validarFiltrosRelatorio() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(filtro.getTipoFrota()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_TIMESHEET_FROTA_OBRIGATORIA);
        pm.verifyAll();
    }

    public void enviarRelatorio() {
        validarFiltrosRelatorio();
//        getService().enviarEmailComEmbarcacoesPorStatusParaRelatorioTimesheet(this.filtro, this.agencias);
//        getService().enviarEmailComEmbarcacoesPorFrotaParaRelatorioTimesheet(this.filtro, this.agencias);
        getService().enviarEmailParaRelatorioTimesheetManual(this.filtro, this.agencias);
    }

    public FiltroRelatorioTimesheet getFiltro() {
        return filtro;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public List<StatusEmbarcacao> getSituacoesEmbarcacao() {
        return situacoesEmbarcacao;
    }

    public List<Embarcacao> getEmbarcacoes() {
        return embarcacoes;
    }

    public List<TipoFrota> getTipoFrota() {
        return tipoFrota;
    }

    public void setTipoFrota(List<TipoFrota> tipoFrota) {
        this.tipoFrota = tipoFrota;
    } 

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (FiltroRelatorioTimesheet.PROP_AGENCIA.equals(evt.getPropertyName())) {
            if (filtro.getAgencia() == null) {
                portos.clear();
                filtro.setPorto(null);
            } else {
                portos = getService().buscarPortosPorAgencia(filtro.getAgencia());
                portos.add(0, null);
            }
            firePropertyChange("portos", null, null);
        }

        if (FiltroRelatorioTimesheet.PROP_AGENCIA.equals(evt.getPropertyName())
                || FiltroRelatorioTimesheet.PROP_PORTO.equals(evt.getPropertyName())
                || FiltroRelatorioTimesheet.PROP_SITUACAO_EMBARCACAO.equals(evt.getPropertyName())
                || FiltroRelatorioTimesheet.PROP_TIPO_FROTA.equals(evt.getPropertyName()) ) {
            filtro.getEmbarcacoes().clear();
            embarcacoes.clear();
            embarcacoes = getService().buscarEmbarcacoesPorFiltro(filtro, this.agencias);
            firePropertyChange("embarcacoes", null, null);
        }
    }
}