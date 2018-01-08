package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioVisita;
import br.com.petrobras.sistam.common.valueobjects.RelatorioVisitaVO;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.common.valueobjects.Usuario;
import br.com.petrobras.sistam.desktop.SistamApp;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_LIGHT_YELLOW;
import br.com.petrobras.sistam.desktop.util.AddDimensionedImageToXLS;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class RelatorioVisitaModel extends AbstractRelatorioXlsModel {

    private FiltroRelatorioVisita filtro;
    private List<Agencia> agencias;
    private List<Porto> portos = new ArrayList<Porto>();
    private List<Usuario> agentes = new ArrayList<Usuario>();
    private List<TipoTransporte> tiposTransporte;
    private List<ResponsavelCustoEntity> responsaveis;

    public RelatorioVisitaModel() {
        filtro = new FiltroRelatorioVisita();
        filtro.addPropertyChangeListener(this);

        SistamCredentialsBean credentialsBean = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        agencias = credentialsBean.getAgenciasAutorizadas();

        tiposTransporte = new ArrayList<TipoTransporte>(Arrays.asList(TipoTransporte.values()));
        tiposTransporte.add(0, null);
        
        carregarResponsaveisCusto();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">                         
    public FiltroRelatorioVisita getFiltro() {
        return filtro;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public List<Usuario> getAgentes() {
        return agentes;
    }

    public List<TipoTransporte> getTiposTransporte() {
        return tiposTransporte;
    }

    public List<ResponsavelCustoEntity> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<ResponsavelCustoEntity> responsaveis) {
        this.responsaveis = responsaveis;
    }
    
    
    
    // </editor-fold>

    public void gerar() {
        validarFiltrosRelatorio();
        visualizarArquivo("Visitas");
    }

    private void validarFiltrosRelatorio() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(filtro.getAgencia()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_VISITA_AGENCIA_OBRIGATORIA);
        pm.assertThat(filtro.getInicio() != null && filtro.getTermino() != null).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_VISITA_PERIODO_OBRIGATORIA);
        if (filtro.getInicio() != null && filtro.getTermino() != null) {
            pm.assertThat(!filtro.getTermino().before(filtro.getInicio())).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_VISITA_PERIODO_INVALIDO);
        }
        pm.verifyAll();
    }

    @Override
    protected String getNomeArquivoParaXls() {
        final String DATE_PATTERN = "ddMMyyyy";

        StringBuilder nomeArquivo = new StringBuilder();
        nomeArquivo.append("Visitas-").append(filtro.getAgencia().getNome());
        if (filtro.getPorto() != null) {
            nomeArquivo.append("-").append(filtro.getPorto().getNomeCompleto());
        }
        nomeArquivo.append("-").append(SistamDateUtils.formatDate(filtro.getInicio(), DATE_PATTERN, null))
                .append("-")
                .append(SistamDateUtils.formatDate(filtro.getTermino(), DATE_PATTERN, null));
        return nomeArquivo.toString();
    }

    @Override
    protected void gerarConteudoXls(HSSFSheet sheet) throws IOException {
        List<RelatorioVisitaVO> visitas = getService().buscarTransportesUtilizadosNaVisita(filtro);
        AssertUtils.assertExpression(!visitas.isEmpty(), ConstantesI18N.RELATORIO_VISITA_NAO_EXISTE_DADOS);

        int countRow = adicionarCabecalhoNoXls(sheet);

        countRow++;
        HSSFRow row = sheet.createRow(countRow);
        for (int column = 0; column < 12; column++) {
            criarCelula(row, column, "", false, true, false, false);
        }
        countRow++;

        setCellFontBold(true);
        setCellBackgroundColor(COLOR_LIGHT_YELLOW);
        
        // Tabela com Resultados
        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "EMBARCAÇÃO", 6000, null, true, true, true, true);
        criarCelula(row, 1, "VGM", 6000, null, true, true, false, true);
        criarCelula(row, 2, "DATA", 3000, null, true, true, false, true);
        criarCelula(row, 3, "LANCHA", 7000, null, true, true, false, true);
        criarCelula(row, 4, "SERVIÇO", 7000, null, true, true, false, true);
        criarCelula(row, 5, "COND. EMBARCAÇÃO", 5000, null, true, true, false, true);
        criarCelula(row, 6, "DATA REQ.", 4000, null, true, true, false, true);
        criarCelula(row, 7, "HIS", 4000, null, true, true, false, true);
        criarCelula(row, 8, "HTS", 4000, null, true, true, false, true);
        criarCelula(row, 9, "AGENTE", 10000, null, true, true, false, true);
        criarCelula(row, 10, "HTS-HIS", 2500, null, true, true, false, true);
        criarCelula(row, 11, "OBS", 15000, null, true, true, false, true);

        setCellFontBold(false);
        setCellBackgroundColor(COLOR_WHITE);
        
        for (RelatorioVisitaVO vo : visitas) {
            row = sheet.createRow(countRow++);
            criarCelula(row, 0, vo.getNavio().getNomeCompleto(), false, true, true, true);
            criarCelula(row, 1, vo.getNumeroViagem(), false, true, false, true);
            criarCelula(row, 2, SistamDateUtils.formatDate(vo.getData(), SistamDateUtils.DATE_PATTERN, vo.getTimeZone()), false, true, false, true);
            criarCelula(row, 3, vo.getLancha(), false, true, false, true);
            criarCelula(row, 4, vo.getServico(), false, true, false, true);
            criarCelula(row, 5, vo.getCondicaoNavio(), false, true, false, true);
            criarCelula(row, 6, SistamDateUtils.formatDate(vo.getDataRequisicao(), SistamDateUtils.DATE_HOUR_PATTERN, vo.getTimeZone()), false, true, false, true);
            criarCelula(row, 7, SistamDateUtils.formatDate(vo.getDataInicio(), SistamDateUtils.DATE_HOUR_PATTERN, vo.getTimeZone()), false, true, false, true);
            criarCelula(row, 8, SistamDateUtils.formatDate(vo.getDataTermino(), SistamDateUtils.DATE_HOUR_PATTERN, vo.getTimeZone()), false, true, false, true);
            criarCelula(row, 9, vo.getAgente().getNome(), false, true, false, true);
            criarCelula(row, 10, vo.getDiferencaDataInicoEFim(), false, true, false, true);
            criarCelula(row, 11, vo.getObservacao(), false, true, false, true);
        }
    }

    private int adicionarCabecalhoNoXls(HSSFSheet sheet) throws IOException {
        int countRow = 6;

        adicionarLogoCabecalhoNoXls(sheet);
        
        HSSFRow row = sheet.createRow(countRow);
        criarTitulo(row, 0, "RELATÓRIO DE VISITAS");
        sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 11));

        //Cabeçalho
        countRow++;
        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Agência", true, true, true, true);
        criarCelula(row, 1, filtro.getAgencia().getNome(), true, true, false, true);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Porto", false, true, true, true);
        criarCelula(row, 1, filtro.getPorto() == null ? "-" : filtro.getPorto().getNomeCompleto(), false, true, false, true);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Agente", false, true, true, true);
        criarCelula(row, 1, filtro.getAgente() == null ? "-" : filtro.getAgente().getNome(), false, true, false, true);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Responsável pelo Custo", false, true, true, true);
        if (filtro.getResponsaveis().isEmpty()) {
            criarCelula(row, 1, "Todos", false, true, false, true);
        } else {
            criarCelula(row, 1, filtro.getResponsaveisDescricao(), false, true, false, true);
        }
        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Tipo de Transporte", false, true, true, true);
        criarCelula(row, 1, filtro.getTipoTransporte() == null ? "-" : filtro.getTipoTransporte().getPorExtenso(), false, true, false, true);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Período", false, true, true, true);
        criarCelula(row, 1, filtro.getInicioFormatado() + " a " + filtro.getTerminoFormatado(), false, true, false, true);


        return countRow;
    }

    private void adicionarLogoCabecalhoNoXls(HSSFSheet sheet) throws IOException {
        sheet.addMergedRegion(new CellRangeAddress(0, 4, 0, 6));
        new AddDimensionedImageToXLS().addImageToSheet("A1", sheet, sheet.createDrawingPatriarch(),
                DesktopUtil.getUrlDaLogoPetrobras(), 25, 20,
                AddDimensionedImageToXLS.OVERLAY_ROW_AND_COLUMN);

        sheet.addMergedRegion(new CellRangeAddress(0, 4, 7, 11));
        new AddDimensionedImageToXLS().addImageToSheet("H1", sheet, sheet.createDrawingPatriarch(),
                DesktopUtil.getUrlDaLogoNP1(), 9, 17,
                AddDimensionedImageToXLS.OVERLAY_ROW_AND_COLUMN);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (FiltroRelatorioVisita.PROP_AGENCIA.equals(evt.getPropertyName())) {
            final Agencia agencia = filtro.getAgencia();
            if (agencia == null) {
                portos.clear();
                agentes.clear();
            } else {
                portos = getService().buscarPortosPorAgencia(agencia);
                portos.add(0, null);

                final String papel = "AGENTE_MARITIMO";
                agentes = getService().obterUsuariosPorAgenciaEPapel(agencia, papel);
                agentes.add(0, null);
            }
            firePropertyChange("portos", null, null);
            firePropertyChange("agentes", null, null);
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