package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioManobra;
import br.com.petrobras.sistam.common.valueobjects.RelatorioManobraVO;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_GREY_25_PERCENT;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_LIGHT_GREEN;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_LIGHT_YELLOW;
import br.com.petrobras.sistam.desktop.util.AddDimensionedImageToXLS;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class RelatorioManobraModel extends AbstractRelatorioXlsModel {

    private List<Agencia> agencias;
    private List<Porto> portos;
    private List<TipoContrato> tiposContrato;
    private FiltroRelatorioManobra filtro;
    private List<ResponsavelCustoEntity> responsaveisCusto;

    public RelatorioManobraModel() {
        filtro = new FiltroRelatorioManobra();
        filtro.addPropertyChangeListener(this);

        SistamCredentialsBean credentialsBean = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        agencias = credentialsBean.getAgenciasAutorizadas();

        tiposContrato = new ArrayList<TipoContrato>(Arrays.asList(TipoContrato.values()));
        tiposContrato.add(0, null);
        
        carregarResponsaveisCusto();

    }

    public List<Porto> getPortos() {
        return portos;
    }

    public List<ResponsavelCustoEntity> getResponsaveisCusto() {
        return responsaveisCusto;
    }
    
    

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setResponsaveisCusto(List<ResponsavelCustoEntity> responsaveisCusto) {
        this.responsaveisCusto = responsaveisCusto;
        firePropertyChange("responsaveisCusto", null, null);
    }

    public List<TipoContrato> getTiposContrato() {
        return tiposContrato;
    }

    public FiltroRelatorioManobra getFiltro() {
        return filtro;
    }

    private List<RelatorioManobraVO> obterVOParaRelatorio() {
        return getService().buscarManobrasParaRelatorio(filtro);
    }

    public void gerar() { 
        validarFiltrosRelatorio();
        visualizarArquivo("Manobras");
    }

    @Override
    protected void gerarConteudoXls(HSSFSheet sheet) throws IOException {
        List<RelatorioManobraVO> manobras = obterVOParaRelatorio();
        AssertUtils.assertExpression(!manobras.isEmpty(), ConstantesI18N.RELATORIO_MANOBRA_NAO_EXISTE_DADOS);

        int countRow = adicionarCabecalhoNoXls(sheet);

        // Tabela com Resultados
        countRow++;
        HSSFRow row = sheet.createRow(countRow);
        for (int column = 0; column < 36; column++) {
            criarCelula(row, column, "", false, true, false, false);
        }
        countRow++;

        setCellFontBold(true);
        row = sheet.createRow(countRow);
        setCellBackgroundColor(COLOR_GREY_25_PERCENT);
        criarCelula(row, 0, "", null, null, false, false, true, false);
        setCellBackgroundColor(COLOR_LIGHT_YELLOW);
        criarCelula(row, 7, "PRATICAGEM", null, HSSFCellStyle.ALIGN_CENTER, false, false, true, false);
        setCellBackgroundColor(COLOR_LIGHT_GREEN);
        criarCelula(row, 14, "REBOCADORES", null, HSSFCellStyle.ALIGN_CENTER, false, false, true, false);
        setCellBackgroundColor(COLOR_GREY_25_PERCENT);
        criarCelula(row, 35, "", null, null, false, false, true, true);
        sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 6));
        sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 7, 13));
        sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 14, 34));
        countRow++;

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Porto/Ponto de Operação", 4000, null, true, true, true, true);
        criarCelula(row, 1, "Embarcação", 7000, null, true, true, false, true);
        criarCelula(row, 2, "Viagem", 3000, null, true, true, false, true);
        criarCelula(row, 3, "Contrato Embarcação", 2500, null, true, true, false, true);
        criarCelula(row, 4, "Manobra", 7000, null, true, true, false, true);
        criarCelula(row, 5, "Início", 7000, null, true, true, false, true);
        criarCelula(row, 6, "Fim", 7000, null, true, true, false, true);

        setCellBackgroundColor(COLOR_LIGHT_YELLOW);
        criarCelula(row, 7, "Empresa", 7000, null, true, true, false, true);
        criarCelula(row, 8, "Prático", 7000, null, true, true, false, true);
        criarCelula(row, 9, "Dt/Hr Req.", 3000, null, true, true, false, true);
        criarCelula(row, 10, "Dt/Hr Início", 3000, null, true, true, false, true);
        criarCelula(row, 11, "Dt/Hr Fim", 3000, null, true, true, false, true);
        criarCelula(row, 12, "Dt/Hr Início Oper.", 3000, null, true, true, false, true);
        criarCelula(row, 13, "Dt/Hr Fim Oper.", 3000, null, true, true, false, true);

        setCellBackgroundColor(COLOR_LIGHT_GREEN);
        criarCelula(row, 14, "Dt/Hr Marcada", 3000, null, true, true, false, true);
        criarCelula(row, 15, "Dt/Hr Início", 3000, null, true, true, false, true);
        criarCelula(row, 16, "Dt/Hr Fim", 3000, null, true, true, false, true);
        for (int i = 0; i < 6; i++) {
            criarCelula(row, (17 + (i * 3)), "Reb " + (i + 1), 7000, null, true, true, false, true);
            criarCelula(row, (18 + (i * 3)), "Dt/Hr Início", 3000, null, true, true, false, true);
            criarCelula(row, (19 + (i * 3)), "Dt/Hr Fim", 3000, null, true, true, false, true);
        }
        setCellBackgroundColor(COLOR_GREY_25_PERCENT);
        criarCelula(row, 35, "Observação", 15000, null, true, true, false, true);

        setCellFontBold(false);
        setCellBackgroundColor(COLOR_WHITE);

        for (RelatorioManobraVO manobra : manobras) {
            row = sheet.createRow(countRow++);
            criarCelula(row, 0, manobra.getPontoOperacional().getNome(), false, true, true, true);
            criarCelula(row, 1, manobra.getNavio().getNomeCompleto(), false, true, false, true);
            criarCelula(row, 2, manobra.getNumeroViagem(), false, true, false, true);
            criarCelula(row, 3, manobra.getTipoContrato().getPorExtenso(), false, true, false, true);
            criarCelula(row, 4, manobra.getFinalidade().getPorExtenso(), false, true, false, true);
            criarCelula(row, 5, manobra.getPontoInicio().getNomeFormatado(), false, true, false, true);
            criarCelula(row, 6, manobra.getPontoFim().getNomeFormatado(), false, true, false, true);

            adicionarPraticosNaTabelaResultadosDoXls(manobra, sheet, row);

            adicionarRebocadoresNaTabelaResultadosDoXls(manobra, sheet, row);

            criarCelula(row, 35, manobra.getObservacao(), false, true, false, true);
        }
    }

    private int adicionarCabecalhoNoXls(HSSFSheet sheet) throws IOException {
        int countRow = 6;

        adicionarLogoCabecalhoNoXls(sheet);

        //Cabeçalho
        HSSFRow row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Agência Marítima", true, true, true, true);
        criarCelula(row, 1, filtro.getAgencia().getNome(), true, true, false, true);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Porto", false, true, true, true);
        criarCelula(row, 1, filtro.getPorto() == null ? "-" : filtro.getPorto().getNomeCompleto(), false, true, false, true);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Responsável pelo Custo", false, true, true, true);
        criarCelula(row, 1, filtro.getResponsaveis().isEmpty() ? "TODOS" : filtro.getResponsaveisDescricao(), false, true, false, true);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Período", false, true, true, true);
        criarCelula(row, 1, filtro.getPeriodoInicioFormatado() + " a " + filtro.getPeriodoTerminoFormatado(), false, true, false, true);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "Tipo de Contrato", false, true, true, true);
        criarCelula(row, 1, filtro.getTipoContrato() == null ? "-" : filtro.getTipoContrato().getPorExtenso(), false, true, false, true);

        return countRow;
    }

    private void adicionarLogoCabecalhoNoXls(HSSFSheet sheet) throws IOException {
        sheet.addMergedRegion(new CellRangeAddress(0, 4, 0, 6));
        new AddDimensionedImageToXLS().addImageToSheet("A1", sheet, sheet.createDrawingPatriarch(),
                DesktopUtil.getUrlDaLogoPetrobras(), 30, 20,
                AddDimensionedImageToXLS.OVERLAY_ROW_AND_COLUMN);

        sheet.addMergedRegion(new CellRangeAddress(0, 4, 7, 14));
        new AddDimensionedImageToXLS().addImageToSheet("H1", sheet, sheet.createDrawingPatriarch(),
                DesktopUtil.getUrlDaLogoNP1(), 5, 17,
                AddDimensionedImageToXLS.OVERLAY_ROW_AND_COLUMN);
    }

    private void adicionarPraticosNaTabelaResultadosDoXls(RelatorioManobraVO manobra, HSSFSheet sheet, HSSFRow row) {
        if (manobra.getPraticos().isEmpty()) {
            for (int i = 7; i < 14; i++) {
                criarCelula(row, i, "", false, true, false, true);
            }
        } else {
            StringBuilder nomePratico = new StringBuilder();
            int quantidade = 0;
            for (ServicoResponsavel servicoResponsavel : manobra.getPraticos()) {
                quantidade++;
                if (nomePratico.length() != 0) {
                    nomePratico.append("/");
                } else {
                    criarCelula(row, 7, servicoResponsavel.getServico().getEmpresa().getRazaoSocial(), false, true, false, true);
                    criarCelula(row, 9, SistamDateUtils.formatDate(servicoResponsavel.getServicoManobra().getDataProgramada(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
                    criarCelula(row, 10, SistamDateUtils.formatDate(servicoResponsavel.getDataInicio(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
                    criarCelula(row, 11, SistamDateUtils.formatDate(servicoResponsavel.getDataTermino(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
                    criarCelula(row, 12, SistamDateUtils.formatDate(manobra.getDataInicioOperacao(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
                    criarCelula(row, 13, SistamDateUtils.formatDate(manobra.getDataFimOperacao(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
                }
                nomePratico.append(servicoResponsavel.getServico().getNomeServico());
                if (quantidade > 2) {
                    break;
                }
            }

            criarCelula(row, 8, nomePratico.toString(), false, true, false, true);
        }
    }

    private void adicionarRebocadoresNaTabelaResultadosDoXls(RelatorioManobraVO manobra, HSSFSheet sheet, HSSFRow row) {
        if (manobra.getRebocadores().isEmpty()) {
            criarCelula(row, 14, "", false, true, false, true);
            criarCelula(row, 15, "", false, true, false, true);
            criarCelula(row, 16, "", false, true, false, true);

            for (int i = 0; i < 6; i++) {
                criarCelula(row, 17 + (i * 3), "", false, true, false, true);
                criarCelula(row, 18 + (i * 3), "", false, true, false, true);
                criarCelula(row, 19 + (i * 3), "", false, true, false, true);
            }
        } else {
            criarCelula(row, 15, SistamDateUtils.formatDate(manobra.getDataInicioReal(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
            criarCelula(row, 16, SistamDateUtils.formatDate(manobra.getDataFimReal(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);

            for (int index = 0; index < 6; index++) {
                try {
                    ServicoResponsavel servicoResponsavel = manobra.getRebocadores().get(index);
                    if (index == 0) {
                        criarCelula(row, 14, SistamDateUtils.formatDate(servicoResponsavel.getServicoManobra().getDataProgramada(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
                    }
                    criarCelula(row, 17 + (3 * index), servicoResponsavel.getServico().getNomeServico(), false, true, false, true);
                    criarCelula(row, 18 + (3 * index), SistamDateUtils.formatDate(servicoResponsavel.getDataInicio(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
                    criarCelula(row, 19 + (3 * index), SistamDateUtils.formatDate(servicoResponsavel.getDataTermino(), SistamDateUtils.DATE_HOUR_PATTERN, filtro.getAgencia().obterTimezone()), false, true, false, true);
                } catch (IndexOutOfBoundsException e) {
                    criarCelula(row, 17 + (3 * index), "", false, true, false, true);
                    criarCelula(row, 18 + (3 * index), "", false, true, false, true);
                    criarCelula(row, 19 + (3 * index), "", false, true, false, true);
                }
            }
        }
    }

    private void validarFiltrosRelatorio() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(filtro.getAgencia()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_MANOBRA_AGENCIA_OBRIGATORIA);
        pm.assertThat(filtro.getPeriodoInicio() != null && filtro.getPeriodoTermino() != null).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_MANOBRA_PERIODO_OBRIGATORIA);
        pm.verifyAll();
    }

    @Override
    protected String getNomeArquivoParaXls() {
        final String DATE_PATTERN = "ddMMyyyy";

        StringBuilder nomeArquivo = new StringBuilder();
        nomeArquivo.append("Manobras-").append(filtro.getAgencia().getNome());
        if (filtro.getPorto() != null) {
            nomeArquivo.append("-").append(filtro.getPorto().getNomeCompleto());
        }
        nomeArquivo.append("-").append(SistamDateUtils.formatDate(filtro.getPeriodoInicio(), DATE_PATTERN, null))
                .append("-")
                .append(SistamDateUtils.formatDate(filtro.getPeriodoTermino(), DATE_PATTERN, null));
        return nomeArquivo.toString();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (FiltroRelatorioManobra.PROP_AGENCIA.equals(evt.getPropertyName())) {
            if (filtro.getAgencia() == null) {
                portos.clear();
            } else {
                portos = getService().buscarPortosPorAgencia(filtro.getAgencia());
                portos.add(0, null);
            }
            firePropertyChange("portos", null, null);
        }
    }
    
    private void carregarResponsaveisCusto(){
        ResponsavelCustoEntity custoEntity = new ResponsavelCustoEntity();
        custoEntity.setDescricao("TODOS");
        custoEntity.setId(null);
        
        List<ResponsavelCustoEntity> lista = new ArrayList<ResponsavelCustoEntity>();
        lista.add(0,custoEntity);
        lista.addAll(1,getService().buscarTodosResponsavelCusto());
        setResponsaveisCusto(lista); 
    }
}