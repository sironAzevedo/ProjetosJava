package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.common.support.CollectionUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamentoAtendimento;
import br.com.petrobras.sistam.common.valueobjects.RelatorioAgenciamentoAtendimentoVO;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class RelatorioAgenciamentoAtendimentoModel extends BasePresentationModel<SistamService> {
    private List<Agencia> agencias;
    private List<Porto> portos;
    private List<TipoContrato> tipoContrato;
    private FiltroAgenciamentoAtendimento filtro;
    private TimeZone timeZone; 
    
   

    public RelatorioAgenciamentoAtendimentoModel() {
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        filtro = new FiltroAgenciamentoAtendimento();
        filtro.addPropertyChangeListener(this);
        
        agencias = contexto.getAgenciasAutorizadas();
        agencias.add(0, null);
      
        tipoContrato = new ArrayList<TipoContrato>(Arrays.asList(TipoContrato.values()));
        tipoContrato.add(0, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Agencia> getAgencias() {
        return agencias;
    }
    
    public List<Porto> getPortos() {
        return portos;
    }
    
    public List<TipoContrato> getTipoContrato() {
        return tipoContrato;
    }
    
    
    public FiltroAgenciamentoAtendimento getFiltro() {
        return filtro;
    }
    
    public TimeZone getTimeZone() {
        return timeZone;
    }
    
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        firePropertyChange("timeZone", null, null);
    }
    
    //</editor-fold>
    
    public List<RelatorioAgenciamentoAtendimentoVO> obterVOParaRelatorio(){
        List<RelatorioAgenciamentoAtendimentoVO> listaAgenciamento = getService().buscarAgenciamentosRelatorioAtendimento(filtro);
        AssertUtils.assertExpression(!listaAgenciamento.isEmpty(), ConstantesI18N.RELATORIO_MANOBRA_NAO_EXISTE_DADOS);
        CollectionUtils.sort(listaAgenciamento, "anoMes");
        return listaAgenciamento;
    }
    
    public Map<String, Object> obterParametros(){
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("AGENCIA", filtro.getAgencia().toString());
        parametros.put("AGENCIA_PORTO", filtro.getPorto() != null ? filtro.getPorto().getNomeCompleto(): " ");
        parametros.put("TIPO_CONTRATO", filtro.getTipoContrato() != null ? filtro.getTipoContrato().toString() : null);
        parametros.put("PERIODO_INICIO", SistamDateUtils.formatDate(filtro.getDataInicial(), "MMM/yyyy", null));
        parametros.put("PERIODO_FIM", SistamDateUtils.formatDate(filtro.getDataFinal(), "MMM/yyyy", null));
        parametros.put("DATA_IMPRESSAO", SistamDateUtils.formatDate(new Date(), "dd/MM/yyyy HH:mm", null));
        parametros.put("CICLO", filtro.getQtdeDiasAtendimento().toString());
        
        return parametros;
    }
    
    public void imprimir(){
       RelatorioUtil.abrirRelatorioAgenciamentoProdutividadeAtendimentosRealizados(obterVOParaRelatorio(), obterParametros());
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (FiltroAgenciamento.PROP_AGENCIA.equals(evt.getPropertyName())) {
            if (filtro.getAgencia() == null) {
                setTimeZone(null);
                if (portos != null) {
                    portos.clear();
                }
            } else {
                setTimeZone(filtro.getAgencia().obterTimezone());
                portos = getService().buscarPortosPorAgencia(filtro.getAgencia());
                portos.add(0, null);
            }
            firePropertyChange("portos", null, null);
        }
    }
}
