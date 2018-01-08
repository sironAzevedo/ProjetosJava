package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.common.valueobjects.TaxaMensalVO;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class RelatorioTaxaModel extends BasePresentationModel<SistamService> {

    private FiltroTaxa filtro;
    private List<Agencia> agencias;
    private List<Porto> portos;
    private TimeZone timeZoneSelecionado;
    private Date dataSelecionada;
    private List<ResponsavelCustoEntity> responsaveis;

    public RelatorioTaxaModel() {
        filtro = new FiltroTaxa();
        filtro.addPropertyChangeListener(this);
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        agencias = contexto.getAgenciasAutorizadas();
        agencias.add(0, null);
        carregarResponsaveisCusto();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Agencia> getAgencias() {
        return agencias;
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
        firePropertyChange("portos", null, null);
    }

    public TimeZone getTimeZoneSelecionado() {
        return timeZoneSelecionado;
    }

    public void setTimeZoneSelecionado(TimeZone timeZoneSelecionado) {
        this.timeZoneSelecionado = timeZoneSelecionado;
        firePropertyChange("timeZoneSelecionado", null, null);
    }

    public Date getDataSelecionada() {
        return dataSelecionada;
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

    public FiltroTaxa getFiltro() {
        return filtro;
    }
    
    public List<ResponsavelCustoEntity> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<ResponsavelCustoEntity> responsaveis) {
        this.responsaveis = responsaveis;
    }  
    //</editor-fold>
    
    public List<TaxaMensalVO> buscarListaDeTaxas() {
        validarRelatorioMensalTaxa();

        Calendar calendar = new GregorianCalendar(filtro.getAgencia().obterTimezone());
        calendar.setTime(dataSelecionada);

        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);
        int ultimoDiaDoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        filtro.setDataPagamentoInicial(DateBuilder.on(1, mes, ano).at(0, 0, 0).getTime());
        filtro.setDataPagamentoFinal(DateBuilder.on(ultimoDiaDoMes, mes, ano).at(23, 59, 59).getTime());

        return getService().buscarTaxasPorFiltro(filtro);
    }

    private void validarRelatorioMensalTaxa() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotNull(filtro.getAgencia()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_TAXAS_AGENCIA_OBRIGATORIA);
//        pm.assertNotNull(filtro.getPorto()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_TAXAS_PORTO_OBRIGATORIO);
        pm.assertNotNull(dataSelecionada).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_TAXAS_MES_ANO_OBRIGATORIO);

        pm.verifyAll();
    }

    private void carregarPortos() {
        List<Porto> listaDePortos = new ArrayList<Porto>();

        if (filtro.getAgencia() != null) {
            listaDePortos = getService().buscarPortosPorAgencia(filtro.getAgencia());
            listaDePortos.add(0, null);
        }

        setPortos(listaDePortos);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(FiltroTaxa.PROP_AGENCIA)) {
            if (filtro.getAgencia() != null) {
                setTimeZoneSelecionado(filtro.getAgencia().obterTimezone());
            } else {
                setTimeZoneSelecionado(null);
            }

            carregarPortos();
        }
    }
    
    private void carregarResponsaveisCusto(){
        ResponsavelCustoEntity custoEntity = new ResponsavelCustoEntity();
        custoEntity.setDescricao("TODOS");
        custoEntity.setId(null);
        List<ResponsavelCustoEntity> lista = new ArrayList<ResponsavelCustoEntity>();
        lista.add(0,custoEntity);
        lista.addAll(1,getService().buscarTodosResponsavelCusto());
        setResponsaveis(lista); 
    }
}
