package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.common.support.CollectionUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class RelatorioControlePagamentoTaxaModel extends BasePresentationModel<SistamService> {
    private FiltroTaxa filtro;
    private List<Agencia> agencias;
    private List<Porto> portos;
    private TimeZone timeZoneSelecionado;
    private List<TipoTaxa> tiposTaxa;
    private List<TipoTaxa> tiposTaxaSelecionados;
    private Date dataSelecionada;
    private List<Taxa> taxas;

    public RelatorioControlePagamentoTaxaModel() {
        filtro = new FiltroTaxa();
        filtro.addPropertyChangeListener(this);
        
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        agencias = contexto.getAgenciasAutorizadas();
        agencias.add(0, null);
        
        carregaListaTipoTaxa();
        
        
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    
    public List<Agencia> getAgencias() {
        return agencias;
    }
    
    public List<Porto> getPortos() {
        return portos;
    }

    public TimeZone getTimeZoneSelecionado() {
        return timeZoneSelecionado;
    }

    public void setTimeZoneSelecionado(TimeZone timeZoneSelecionado) {
        this.timeZoneSelecionado = timeZoneSelecionado;
        firePropertyChange("timeZoneSelecionado", null, null);
    }

    public List<TipoTaxa> getTiposTaxa() {
        return tiposTaxa;
    }

    public void setTiposTaxa(List<TipoTaxa> tiposTaxa) {
        this.tiposTaxa = tiposTaxa;
        firePropertyChange("tiposTaxa", null, null);
    }
    
    public void setDataSelecionada(Date dataSelecionada) {
        //Seta a data para o dia 15 do mês
        if (dataSelecionada != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataSelecionada);
            calendar.set(Calendar.DAY_OF_MONTH, 15);
            dataSelecionada = calendar.getTime();
        }
        this.dataSelecionada = dataSelecionada;
    }

    public Date getDataSelecionada() {
        return dataSelecionada;
    }
    
    public FiltroTaxa getFiltro() {
        return filtro;
    }

    public List<TipoTaxa> getTiposTaxaSelecionados() {
        return tiposTaxaSelecionados;
    }

    public void setTiposTaxaSelecionados(List<TipoTaxa> tiposTaxaSelecionados) {
        this.tiposTaxaSelecionados = tiposTaxaSelecionados;
        firePropertyChange("tiposTaxaSelecionados", null, null);
    }

    //</editor-fold>
    
    public void carregaListaTipoTaxa(){
        tiposTaxa = new ArrayList<TipoTaxa>(Arrays.asList(TipoTaxa.values()));
        CollectionUtils.sort(tiposTaxa, "ordenar");
    }
    
    public List<Taxa> buscarTaxas() {
        validarRelatorioControlePagamentoTaxa();

        Calendar calendar = Calendar.getInstance(filtro.getAgencia().obterTimezone());
        calendar.setTime(dataSelecionada);

        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);
        int ultimoDiaDoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        filtro.setDataPagamentoInicial(DateBuilder.on(1, mes, ano).at(0, 0, 0).getTime());
        filtro.setDataPagamentoFinal(DateBuilder.on(ultimoDiaDoMes, mes, ano).at(23, 59, 59).getTime());

        filtro.setListaTipoTaxa(tiposTaxaSelecionados);
        
        //return getService().buscarTaxasOrdenadasPorTipo(filtro);
        
        taxas = getService().buscarTaxasDaAgenciaEMesAnoOrdenadasPorTipo(filtro.getAgencia(), filtro.getPorto(), tiposTaxaSelecionados, mes, ano);
        return taxas;
    }
    
    
    public Map<String, Object> obterParametros(){
        Calendar calendar = Calendar.getInstance(filtro.getAgencia().obterTimezone());
        calendar.setTime(dataSelecionada);
        
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("AGENCIA", filtro.getAgencia());
        parametros.put("AGENCIA_PORTO", filtro.getPorto() != null ? filtro.getPorto().getNomeCompleto(): "-");
        parametros.put("PERIODO", calendar.getTime());
        parametros.put("REPORT_TIME_ZONE", filtro.getAgencia().obterTimezone());
        parametros.put("DATA_IMPRESSAO", SistamDateUtils.formatDate(new Date(), "dd/MM/yyyy HH:mm", null));
        
        Map<TipoTaxa, Double> mapAcumuladoAnual = calcularTotaisPorTipoTaxa();
        parametros.put("MAP_TOTAL_ANUAL", mapAcumuladoAnual);
        
        Double totalAnualAcumulado = calcularTotalAnualAcumulado(mapAcumuladoAnual);
        parametros.put("TOTAL_GERAL_ANUAL", totalAnualAcumulado);
        
        return parametros;
    }
    
    private Map<TipoTaxa, Double> calcularTotaisPorTipoTaxa() {
        
        Map<TipoTaxa, Double> totais = new HashMap<TipoTaxa, Double>();
        for (Taxa taxa : taxas) {
            Double total = totais.get(taxa.getTipoTaxa());
            if (total == null) {
                total = 0D;
            }
            total += taxa.getValor();
            totais.put(taxa.getTipoTaxa(), total);
        }
        return totais;
    }

    
    private Map<TipoTaxa, Double> buscarAcumuladoAnual() {
        //Carrega o filtro das datas de pagamento como sendo o primeiro e último dia do ano informado
        Calendar calendar = Calendar.getInstance(filtro.getAgencia().obterTimezone());
        calendar.setTime(dataSelecionada);
        int ano = calendar.get(Calendar.YEAR);
        
        filtro.setDataPagamentoInicial(DateBuilder.on(1, 1, ano).at(0, 0, 0).getTime());
        filtro.setDataPagamentoFinal(DateBuilder.on(31, 12, ano).at(23, 59, 59).getTime());
        
        return getService().buscarTaxasValorAcumulado(filtro);
    }
    
            
    private double calcularTotalAnualAcumulado(Map<TipoTaxa, Double> mapValoresAcumulados){
        BigDecimal total = new BigDecimal(0);
        
        for ( Map.Entry<TipoTaxa, Double> entry : mapValoresAcumulados.entrySet() ) {
            BigDecimal valor = new BigDecimal(entry.getValue());
            total = total.add(valor).setScale(2, RoundingMode.HALF_EVEN);
        }        
        
        return total.doubleValue();
    }
    
    private void validarRelatorioControlePagamentoTaxa() {
        SistamPendencyManager pm = new SistamPendencyManager();
        
        pm.assertNotNull(filtro.getAgencia()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_TAXAS_AGENCIA_OBRIGATORIA);
        pm.assertNotNull(dataSelecionada).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_TAXAS_MES_ANO_OBRIGATORIO);
        pm.assertThat(!this.tiposTaxaSelecionados.isEmpty()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_TAXA_SELECIONADA);

        pm.verifyAll();
    }

    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(FiltroTaxa.PROP_AGENCIA)) {
            if (filtro.getAgencia() != null) {
                setTimeZoneSelecionado(filtro.getAgencia().obterTimezone()); 
                portos = getService().buscarPortosPorAgencia(filtro.getAgencia());
                portos.add(0, null);
            } else {
                setTimeZoneSelecionado(null); 
                 if(portos != null){
                    portos.clear();
                }
            } 
            firePropertyChange("portos", null, null);
        }
    }
}
