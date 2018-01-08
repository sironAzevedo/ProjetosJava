package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.RelatorioAgenciamentoProdutividadeVO;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class RelatorioProdutividadeAgenciamentoModel extends BasePresentationModel<SistamService> {
    private List<Agencia> agencias;
    private List<Porto> portos;
    private List<TipoContrato> tipoContrato;
    private FiltroAgenciamento filtro;
    private TimeZone timeZone;

    public RelatorioProdutividadeAgenciamentoModel() {
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        
        filtro = new FiltroAgenciamento();
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
    
    
    public FiltroAgenciamento getFiltro() {
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
    
    public RelatorioAgenciamentoProdutividadeVO obterVOParaRelatorio(){
        List<Agenciamento> agenciamentosCarga = new ArrayList<Agenciamento>();
        List<Agenciamento> agenciamentosProtecao = new ArrayList<Agenciamento>();
        List<Agenciamento> agenciamentosPlataforma = new ArrayList<Agenciamento>();  
        
        RelatorioAgenciamentoProdutividadeVO vo = new RelatorioAgenciamentoProdutividadeVO();

        List<Agenciamento> listaAgenciamento = getService().buscarAgenciamentosRelatorioProdutividade(filtro); 
        AssertUtils.assertExpression(!listaAgenciamento.isEmpty(), ConstantesI18N.RELATORIO_MANOBRA_NAO_EXISTE_DADOS);
        
        Integer qtdAtendimentoAplataforma = 0;
        for (Agenciamento agenciamento : listaAgenciamento) {
            if (agenciamento.getAgenciamentoCarga()) {
                agenciamentosCarga.add(agenciamento);
            }

            if (agenciamento.getAgenciamentoProtecao()) {
                agenciamentosProtecao.add(agenciamento);
            }

            if (agenciamento.getAgenciamentoPlataforma()) {
                if (agenciamento.getAcompanhamentos().size() > 0) {
                    agenciamentosPlataforma.add(agenciamento);
                    qtdAtendimentoAplataforma += agenciamento.getAcompanhamentos().size();
                }
            }
        }

        for (int i = 0; i < listaAgenciamento.size(); i++) {
            Agenciamento a = listaAgenciamento.get(i);
            if (a.isTipoAgenciamentoPlataforma() && a.getAcompanhamentos().size() <= 0) {
                listaAgenciamento.remove(a);
            }
        }

        Integer qtdEmbarcacao = listaAgenciamento.size() - agenciamentosPlataforma.size();
        
        vo.setTotalEmbarcacoes(Long.valueOf(qtdEmbarcacao)); 
        vo.setTotalPlataforma(Long.valueOf(qtdAtendimentoAplataforma));
        vo.setAgenciamentosCarga(agenciamentosCarga);
        vo.setAgenciamentosProtecao(agenciamentosProtecao);
        vo.setAgenciamentosPlataforma(agenciamentosPlataforma);
        vo.ordenarAgenciamentos();
        
        return vo;
    }
    
    public Map<String, Object> obterParametros(){
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("AGENCIA", filtro.getAgencia().toString());
        parametros.put("AGENCIA_PORTO", filtro.getPorto() != null ? filtro.getPorto().getNomeCompleto(): " ");
        parametros.put("TIPO_CONTRATO", filtro.getTipoContrato() != null ? filtro.getTipoContrato().toString() : null);
        parametros.put("PERIODO_INICIO", SistamDateUtils.formatDate(filtro.getDataChegadaIni(), "dd/MM/yyyy", filtro.getAgencia().obterTimezone()));
        parametros.put("PERIODO_FIM", SistamDateUtils.formatDate(filtro.getDataChegadaFim(), "dd/MM/yyyy", filtro.getAgencia().obterTimezone()));
        parametros.put("DATA_IMPRESSAO", SistamDateUtils.formatDate(new Date(), "dd/MM/yyyy HH:mm", null));
        
        return parametros;
    }
    
     @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(FiltroAgenciamento.PROP_AGENCIA)) {
            if (filtro.getAgencia() == null) {
                setTimeZone(null);
                if(portos != null){
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
