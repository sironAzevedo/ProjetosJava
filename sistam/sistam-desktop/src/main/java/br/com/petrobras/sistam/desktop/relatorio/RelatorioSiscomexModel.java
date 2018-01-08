package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioSiscomex;
import br.com.petrobras.sistam.common.valueobjects.RelatorioSiscomexVO;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_LIGHT_GREEN;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import static br.com.petrobras.sistam.common.util.SistamDateUtils.*;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_RED;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_WHITE;
import br.com.petrobras.sistam.desktop.util.AddDimensionedImageToXLS;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author adzk
 */
public class RelatorioSiscomexModel extends AbstractRelatorioXlsModel {

    private FiltroRelatorioSiscomex filtro;
    private List<Agencia> agencias;
    private List<Porto> portos;
    private List<TipoOperacao> tiposOperacao;
    private List<TipoContrato> tiposContrato;
    private List<StatusEmbarcacao> situacoesEmbarcacao;
    private List<TipoSimNao> tiposSimNao;
    private List<FiltroRelatorioSiscomex.TipoOrdenacaoSiscomex> tiposOrdenacao;

    public RelatorioSiscomexModel() {
        filtro = new FiltroRelatorioSiscomex();
        filtro.addPropertyChangeListener(this);

        SistamCredentialsBean credentialsBean = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        agencias = credentialsBean.getAgenciasAutorizadas();
        //agencias.add(0, null);

        tiposOperacao = TipoOperacao.listValuesToSiscomex();
        tiposContrato = TipoContrato.listValues();
        situacoesEmbarcacao = StatusEmbarcacao.listValues();
        tiposSimNao = TipoSimNao.listValues();
        tiposSimNao.add(0, null);

        tiposOrdenacao = FiltroRelatorioSiscomex.TipoOrdenacaoSiscomex.listValues();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public FiltroRelatorioSiscomex getFiltro() {
        return filtro;
    }

    public List<Agencia> getAgencias() {
        return agencias;        
    }
    
    public void setPortos(List<Porto> portos) {
        this.portos = portos; 
    }
    
    public List<Porto> getPortos() {
        return portos;
    }

    public List<TipoOperacao> getTiposOperacao() {
        return tiposOperacao;
    }

    public List<TipoContrato> getTiposContrato() {
        return tiposContrato;
    }

    public List<TipoSimNao> getTiposSimNao() {
        return tiposSimNao;
    }

    public List<StatusEmbarcacao> getSituacoesEmbarcacao() {
        return situacoesEmbarcacao;
    }

    public List<FiltroRelatorioSiscomex.TipoOrdenacaoSiscomex> getTiposOrdenacao() {
        return tiposOrdenacao;
    }
    //</editor-fold>

    public void gerar() {
        visualizarArquivo("Siscomex");
    }

    @Override
    protected String getNomeArquivoParaXls() {
        StringBuilder nomeArquivo = new StringBuilder();
        nomeArquivo.append("Siscomex")
                .append("-").append(formatDate(new Date(), "ddMMyyyy", null));
        return nomeArquivo.toString();
    }    

    @Override
    protected void gerarConteudoXls(HSSFSheet sheet) throws IOException {

        RelatorioSiscomexVO vo = getService().buscarDocumentosParaRelatorioSiscomex(filtro);
        AssertUtils.assertExpression(vo != null, ConstantesI18N.RELATORIO_SISCOMEX_NAO_EXISTE_DADOS);

        addColorGrey10Percent(sheet.getWorkbook());

        adicionarLogoCabecalhoNoXls(sheet);

        int countRow = 7;

        countRow = adicionarCabecalhoNoXls(sheet, countRow);

        if (filtro.getTiposOperacao().isEmpty() || filtro.isTipoOperacaoCargaExportacao()) {
            countRow = adicionarTabelaParaDocumentacaoLongoCurso("CARGA EXPORTAÇÃO", vo.getCargasExportacao(), vo.getOperacoesSemDocumentacaoCargaExportacao(), sheet, countRow);
        }

        if (filtro.getTiposOperacao().isEmpty() || filtro.isTipoOperacaoCargaCabotagem()) {
            countRow = adicionarTabelaCargaCabotagem(vo.getCargasCabotagem(), vo.getOperacoesSemDocumentacaoCargaCabotagem(), sheet, countRow);
        }

        if (filtro.getTiposOperacao().isEmpty() || filtro.isTipoOperacaoDescargaImportacao()) {
            countRow = adicionarTabelaParaDocumentacaoLongoCurso("DESCARGA IMPORTAÇÃO", vo.getDescargasImportacao(), vo.getOperacoesSemDocumentacaoDescargaImportacao(), sheet, countRow);
        }

        if (filtro.getTiposOperacao().isEmpty() || filtro.isTipoOperacaoDescargaCabotagem()) {
            countRow = adicionarTabelaDescargaCabotagem(vo.getDescargasCabotagem(), vo.getOperacoesSemDocumentacaoDescargaCabotagem(), sheet, countRow);
        }

        if (filtro.getTiposOperacao().isEmpty() || filtro.isTipoSemOperacaoComercial()) {
            countRow = adicionarTabelaSemOperacaoComercial(vo.getSemOperacoesComerciais(), sheet, countRow);
            adicionarTabelaSemOperacao(vo.getSemOperacoes(), sheet, countRow);
        }
    }

    private int adicionarTabelaSemOperacaoComercial(List<Operacao> listaSemOperacoesComerciais, HSSFSheet sheet, int countRow) {

        Map<Agenciamento, List<Operacao>> operacoesSemOperacaoComercialPorAgenciamento = new LinkedHashMap<Agenciamento, List<Operacao>>();

        for (Operacao operacao : listaSemOperacoesComerciais) {
            Agenciamento agenciamento = operacao.getAgenciamento();

            List<Operacao> operacoes = new ArrayList<Operacao>();
            if (operacoesSemOperacaoComercialPorAgenciamento.containsKey(agenciamento)) {
                operacoes = operacoesSemOperacaoComercialPorAgenciamento.get(agenciamento);
            }
            operacoes.add(operacao);
            operacoesSemOperacaoComercialPorAgenciamento.put(agenciamento, operacoes);
        }

        HSSFWorkbook workbook = sheet.getWorkbook();
        HSSFRow row = sheet.createRow(countRow);

        setCellFontBold(true);
        setCellFontColor(COLOR_WHITE);
        setCellBackgroundColor(COLOR_DARK_BLUE);

        criarCelula(row, 0, "SEM OPERAÇÃO COMERCIAL", null, HSSFCellStyle.ALIGN_LEFT, false, false, false, false);
        CellRangeAddress region = new CellRangeAddress(countRow, countRow, 0, 10);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        countRow++;

        setCellFontColor(COLOR_BLACK);
        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "EMBARCAÇÃO", 7000, null, true, true, true, true);
        criarCelula(row, 1, "IMO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 2, "VGM", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 3, "HOC", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 4, "SITUAÇÃO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 5, "CMT CHEGADA", 7000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 6, "PORTO ORIGEM", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 7, "PORTO DESTINO", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 8, "ESCALA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 9, "ETA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 10, "SISTAM", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);

        setCellFontBold(false);
        setCellBackgroundColor(COLOR_WHITE);

        int linha = 0;
        for (Map.Entry<Agenciamento, List<Operacao>> map : operacoesSemOperacaoComercialPorAgenciamento.entrySet()) {
            Agenciamento agenciamento = map.getKey();

            row = sheet.createRow(countRow++);
            setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

            criarCelula(row, 0, agenciamento.getEmbarcacao().getNomeCompleto(), false, true, true, true);
            criarCelulaCentralizado(row, 1, agenciamento.getEmbarcacao().getImo(), false, true, true, true);
            criarCelulaCentralizado(row, 2, agenciamento.getVgm(), false, true, false, true);
            criarCelulaCentralizado(row, 3, formatDate(agenciamento.getDataChegada(), DATE_HOUR_PATTERN, null), false, true, false, true);
            criarCelulaCentralizado(row, 4, agenciamento.getStatusEmbarcacao().getPorExtenso(), false, true, true, true);
            criarCelulaCentralizado(row, 5, agenciamento.getAgenciementoViagem().getComandanteEntrada(), false, true, true, true);
            criarCelulaCentralizado(row, 6, agenciamento.getPortoOrigem() == null ? "" : String.valueOf(agenciamento.getPortoOrigem().getNomeCompleto()), false, true, true, true);
            criarCelulaCentralizado(row, 7, agenciamento.getPortoDestino() == null ? "" : String.valueOf(agenciamento.getPortoDestino().getNomeCompleto()), false, true, true, true);
            criarCelulaCentralizado(row, 8, agenciamento.getNumeroEscalaMercante() == null ? "" : String.valueOf(agenciamento.getNumeroEscalaMercante()), false, true, false, true);
            criarCelulaCentralizado(row, 9, formatDate(agenciamento.getEta(), DATE_HOUR_PATTERN, null), false, true, false, true);
            criarCelulaCentralizado(row, 10, agenciamento.getNumeroProcessoformatado(), false, true, false, true);
        }
        countRow++;
        return countRow;
    }

    private int adicionarTabelaSemOperacao(List<Agenciamento> listaSemOperacoes, HSSFSheet sheet, int countRow) {
        HSSFWorkbook workbook = sheet.getWorkbook();
        HSSFRow row = sheet.createRow(countRow);

        setCellFontBold(true);
        setCellFontColor(COLOR_WHITE);
        setCellBackgroundColor(COLOR_DARK_BLUE);

        criarCelula(row, 0, "SEM OPERAÇÃO", null, HSSFCellStyle.ALIGN_LEFT, false, false, false, false);
        CellRangeAddress region = new CellRangeAddress(countRow, countRow, 0, 10);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        countRow++;

        setCellFontColor(COLOR_BLACK);
        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "EMBARCAÇÃO", 7000, null, true, true, true, true);
        criarCelula(row, 1, "IMO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 2, "VGM", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 3, "HOC", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 4, "SITUAÇÃO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 5, "CMT CHEGADA", 7000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 6, "PORTO ORIGEM", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 7, "PORTO DESTINO", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 8, "ESCALA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 9, "ETA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 10, "SISTAM", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);

        setCellFontBold(false);
        setCellBackgroundColor(COLOR_WHITE);

        int linha = 0;
        for (Agenciamento agenciamento : listaSemOperacoes) {

            row = sheet.createRow(countRow++);
            setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

            criarCelula(row, 0, agenciamento.getEmbarcacao().getNomeCompleto(), false, true, true, true);
            criarCelulaCentralizado(row, 1, agenciamento.getEmbarcacao().getImo(), false, true, true, true);
            criarCelulaCentralizado(row, 2, agenciamento.getVgm(), false, true, false, true);
            criarCelulaCentralizado(row, 3, formatDate(agenciamento.getDataChegada(), DATE_HOUR_PATTERN, null), false, true, false, true);
            criarCelulaCentralizado(row, 4, agenciamento.getStatusEmbarcacao().getPorExtenso(), false, true, true, true);
            criarCelulaCentralizado(row, 5, agenciamento.getAgenciementoViagem().getComandanteEntrada(), false, true, true, true);
            criarCelulaCentralizado(row, 6, agenciamento.getPortoOrigem() == null ? "" : String.valueOf(agenciamento.getPortoOrigem().getNomeCompleto()), false, true, true, true);
            criarCelulaCentralizado(row, 7, agenciamento.getPortoDestino() == null ? "" : String.valueOf(agenciamento.getPortoDestino().getNomeCompleto()), false, true, true, true);
            criarCelulaCentralizado(row, 8, agenciamento.getNumeroEscalaMercante() == null ? "" : String.valueOf(agenciamento.getNumeroEscalaMercante()), false, true, false, true);
            criarCelulaCentralizado(row, 9, formatDate(agenciamento.getEta(), DATE_HOUR_PATTERN, null), false, true, false, true);
            criarCelulaCentralizado(row, 10, agenciamento.getNumeroProcessoformatado(), false, true, false, true);
        }
        return countRow;
    }

    private int adicionarTabelaParaDocumentacaoLongoCurso(String titulo, List<DocumentacaoLongoCurso> listaDocumentacoes, List<Operacao> listaOperacoesSemDocumentacao, HSSFSheet sheet, int countRow) {
        Map<Agenciamento, List<DocumentacaoLongoCurso>> longosCursoPorAgenciamento = new LinkedHashMap<Agenciamento, List<DocumentacaoLongoCurso>>();

        for (DocumentacaoLongoCurso documentacao : listaDocumentacoes) {
            Agenciamento agenciamento = documentacao.getOperacao().getAgenciamento();

            List<DocumentacaoLongoCurso> documentacoes = new ArrayList<DocumentacaoLongoCurso>();
            if (longosCursoPorAgenciamento.containsKey(agenciamento)) {
                documentacoes = longosCursoPorAgenciamento.get(agenciamento);
            }
            documentacoes.add(documentacao);
            longosCursoPorAgenciamento.put(agenciamento, documentacoes);
        }

        int starting = countRow++;

        HSSFWorkbook workbook = sheet.getWorkbook();
        HSSFRow row = sheet.createRow(countRow);

        setCellFontBold(true);
        setCellFontColor(COLOR_WHITE);
        setCellBackgroundColor(COLOR_DARK_BLUE);

        criarCelula(row, 0, titulo, null, HSSFCellStyle.ALIGN_LEFT, false, false, false, false);
        CellRangeAddress region = new CellRangeAddress(countRow, countRow, 0, 19);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        countRow++;

        setCellFontColor(COLOR_BLACK);
        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "EMBARCAÇÃO", 7000, HSSFCellStyle.ALIGN_LEFT, true, true, true, true);
        criarCelula(row, 1, "IMO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 2, "VGM", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 3, "HOC", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 4, "SITUAÇÃO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 5, "CMT CHEGADA", 7000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 6, "PORTO ORIGEM", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 7, "PORTO DESTINO", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 8, "ETA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 9, "ESCALA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 10, "MANIFESTO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 11, "CONHECIMENTO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 12, "PRODUTO", 5000, HSSFCellStyle.ALIGN_LEFT, true, true, false, true);
        criarCelula(row, 13, "QUANTIDADE TONELADAS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 14, "QUANTIDADE METROS CÚBICOS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 15, "INÍCIO OPERAÇÃO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 16, "TERMINO OPERAÇÃO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 17, "PAÍS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 18, "PORTO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 19, "SISTAM", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);

        setCellFontBold(false);
        setCellBackgroundColor(COLOR_WHITE);
        row = sheet.createRow(countRow++);

        int linha = 0;
        for (Map.Entry<Agenciamento, List<DocumentacaoLongoCurso>> map : longosCursoPorAgenciamento.entrySet()) {
            Agenciamento agenciamento = map.getKey();
            List<DocumentacaoLongoCurso> documentacoes = map.getValue();

            int total = 0;
            setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

            criarCelulaSemBorda(row, 0, agenciamento.getEmbarcacao().getNomeCompleto());
            criarCelulaSemBordaCentralizado(row, 1, agenciamento.getEmbarcacao().getImo());
            criarCelulaSemBordaCentralizado(row, 2, agenciamento.getVgm());
            criarCelulaSemBordaCentralizado(row, 3, formatDate(agenciamento.getDataChegada(), DATE_HOUR_PATTERN, null));
            criarCelulaSemBordaCentralizado(row, 4, agenciamento.getStatusEmbarcacao().getPorExtenso());
            criarCelulaSemBordaCentralizado(row, 5, agenciamento.getAgenciementoViagem() == null ? "" : agenciamento.getAgenciementoViagem().getComandanteEntrada());
            criarCelulaSemBordaCentralizado(row, 6, agenciamento.getPortoOrigem() == null ? "" : agenciamento.getPortoOrigem().getNomeCompleto());
            criarCelulaSemBordaCentralizado(row, 7, agenciamento.getPortoDestino() == null ? "" : agenciamento.getPortoDestino().getNomeCompleto());
            criarCelulaSemBordaCentralizado(row, 8, formatDate(agenciamento.getEta(), DATE_HOUR_PATTERN, null));
            criarCelulaSemBordaCentralizado(row, 9, agenciamento.getNumeroEscalaMercante() == null ? "" : String.valueOf(agenciamento.getNumeroEscalaMercante()));
            criarCelulaSemBordaCentralizado(row, 19, agenciamento.getNumeroProcessoformatado());

            for (DocumentacaoLongoCurso documentacao : documentacoes) {
                Operacao operacao = documentacao.getOperacao();
                Produto produto = operacao.getProduto();

                criarCelulaSemBordaCentralizado(row, 10, documentacao.getManifestoEletronico());
                criarCelulaSemBorda(row, 12, produto.getNomeCompleto());

                if (operacao.isUnidadeMedidaTonelada()) {
                    criarCelulaSemBordaCentralizado(row, 13, operacao.getQuantidadeEstimadaFormatada());
                    criarCelulaSemBordaCentralizado(row, 14, "");
                } else if (operacao.isUnidadeMedidaMetroCubico()) {
                    criarCelulaSemBordaCentralizado(row, 13, "");
                    criarCelulaSemBordaCentralizado(row, 14, operacao.getQuantidadeEstimadaFormatada());
                }

                criarCelulaSemBordaCentralizado(row, 15, formatDate(operacao.getDataInicio(), DATE_PATTERN, null));
                criarCelulaSemBordaCentralizado(row, 16, formatDate(operacao.getDataFim(), DATE_PATTERN, null));
                criarCelulaSemBordaCentralizado(row, 17, documentacao.getPorto().getPais().getNomeCompleto());
                criarCelulaSemBordaCentralizado(row, 18, documentacao.getPorto().getNomeCompleto());

                for (String conhecimento : documentacao.getListaConhecimentoEletronicoFormatada()) {
                    total++;
                    criarCelulaCentralizado(row, 11, conhecimento, false, true, false, true);
                    row = sheet.createRow(countRow++);
                }

                Integer[] cellToMerge = new Integer[]{10, 12, 13, 14, 15, 16, 17, 18};
                for (Integer column : cellToMerge) {
                    region = new CellRangeAddress(countRow - (documentacao.getListaConhecimentoEletronicoFormatada().size() + 1), countRow - 2, column, column);
                    if (documentacao.getListaConhecimentoEletronicoFormatada().size() > 1) {
                        sheet.addMergedRegion(region);
                    }
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                }
            }

            Integer[] cellToMerge = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 19};
            for (Integer column : cellToMerge) {
                region = new CellRangeAddress(countRow - total - 1, countRow - 2, column, column);
                if (total > 1) {
                    sheet.addMergedRegion(region);
                }
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
            }
        }

        if (!listaOperacoesSemDocumentacao.isEmpty()) {

            Map<Agenciamento, List<Operacao>> operacoesLongoCursoSemDocumentacaoPorAgenciamento = new LinkedHashMap<Agenciamento, List<Operacao>>();

            for (Operacao operacao : listaOperacoesSemDocumentacao) {
                Agenciamento agenciamento = operacao.getAgenciamento();

                List<Operacao> operacoes = new ArrayList<Operacao>();
                if (operacoesLongoCursoSemDocumentacaoPorAgenciamento.containsKey(agenciamento)) {
                    operacoes = operacoesLongoCursoSemDocumentacaoPorAgenciamento.get(agenciamento);
                }
                operacoes.add(operacao);
                operacoesLongoCursoSemDocumentacaoPorAgenciamento.put(agenciamento, operacoes);
            }

            setCellFontBold(false);
            setCellFontColor(COLOR_RED);

            for (Map.Entry<Agenciamento, List<Operacao>> map : operacoesLongoCursoSemDocumentacaoPorAgenciamento.entrySet()) {
                Agenciamento agenciamento = map.getKey();
                List<Operacao> operacoes = map.getValue();

                setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

                criarCelula(row, 0, agenciamento.getEmbarcacao().getNomeCompleto(), false, true, false, true);
                criarCelulaCentralizado(row, 1, agenciamento.getEmbarcacao().getImo(), false, true, false, true);
                criarCelulaCentralizado(row, 2, agenciamento.getVgm(), false, true, false, true);
                criarCelulaCentralizado(row, 3, formatDate(agenciamento.getDataChegada(), DATE_HOUR_PATTERN, null), false, true, false, true);
                criarCelulaCentralizado(row, 4, agenciamento.getStatusEmbarcacao().getPorExtenso(), false, true, false, true);
                criarCelulaCentralizado(row, 5, agenciamento.getAgenciementoViagem() == null ? "" : agenciamento.getAgenciementoViagem().getComandanteEntrada(), false, true, false, true);
                criarCelulaCentralizado(row, 6, agenciamento.getPortoOrigem() == null ? "" : agenciamento.getPortoOrigem().getNomeCompleto(), false, true, false, true);
                criarCelulaCentralizado(row, 7, agenciamento.getPortoDestino() == null ? "" : agenciamento.getPortoDestino().getNomeCompleto(), false, true, false, true);
                criarCelulaCentralizado(row, 8, formatDate(agenciamento.getEta(), DATE_HOUR_PATTERN, null), false, true, false, true);
                criarCelulaCentralizado(row, 9, agenciamento.getNumeroEscalaMercante() == null ? "" : String.valueOf(agenciamento.getNumeroEscalaMercante()), false, true, false, true);
                criarCelulaCentralizado(row, 10, "", false, true, false, true);
                criarCelulaCentralizado(row, 11, "", false, true, false, true);
                criarCelulaCentralizado(row, 17, "", false, true, false, true);
                criarCelulaCentralizado(row, 18, "", false, true, false, true);
                criarCelulaCentralizado(row, 19, agenciamento.getNumeroProcessoformatado(), false, true, false, true);

                int total = 0;
                for (Operacao operacao : operacoes) {
                    total++;

                    criarCelula(row, 12, operacao.getProduto().getNomeCompleto(), false, true, false, true);
                    if (operacao.isUnidadeMedidaTonelada()) {
                        criarCelulaCentralizado(row, 13, operacao.getQuantidadeEstimadaFormatada(), false, true, false, true);
                        criarCelulaCentralizado(row, 14, "", true, true, true, true);
                    } else if (operacao.isUnidadeMedidaMetroCubico()) {
                        criarCelulaCentralizado(row, 13, "", true, true, true, true);
                        criarCelulaCentralizado(row, 14, operacao.getQuantidadeEstimadaFormatada(), false, true, false, true);
                    }
                    criarCelulaCentralizado(row, 15, formatDate(operacao.getDataInicio(), DATE_PATTERN, null), false, true, false, true);
                    criarCelulaCentralizado(row, 16, formatDate(operacao.getDataFim(), DATE_PATTERN, null), false, true, false, true);
                    row = sheet.createRow(countRow++);
                }

                Integer[] cellToMerge = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 17, 18, 19};
                for (Integer column : cellToMerge) {
                    region = new CellRangeAddress(countRow - total - 1, countRow - 2, column, column);
                    if (total > 1) {
                        sheet.addMergedRegion(region);
                    }
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                }

            }
        }

        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, new CellRangeAddress(starting + 1, countRow - 2, 0, 0), sheet, workbook);
        return countRow;
    }

    private int adicionarTabelaDescargaCabotagem(List<DocumentacaoCabotagem> listaCabotagens, List<Operacao> listaOperacoesSemDocumentacao, HSSFSheet sheet, int countRow) {
        Map<Agenciamento, List<DocumentacaoCabotagem>> cabotagensPorAgenciamento = new LinkedHashMap<Agenciamento, List<DocumentacaoCabotagem>>();

        for (DocumentacaoCabotagem documentacao : listaCabotagens) {
            Agenciamento agenciamento = documentacao.getAgenciamento();

            List<DocumentacaoCabotagem> documentacoes = new ArrayList<DocumentacaoCabotagem>();
            if (cabotagensPorAgenciamento.containsKey(agenciamento)) {
                documentacoes = cabotagensPorAgenciamento.get(agenciamento);
            }
            documentacoes.add(documentacao);
            cabotagensPorAgenciamento.put(agenciamento, documentacoes);
        }

        int starting = countRow++;

        HSSFWorkbook workbook = sheet.getWorkbook();
        HSSFRow row = sheet.createRow(countRow);

        setCellFontBold(true);
        setCellFontColor(COLOR_WHITE);
        setCellBackgroundColor(COLOR_DARK_BLUE);

        criarCelula(row, 0, "DESCARGA CABOTAGEM", null, HSSFCellStyle.ALIGN_LEFT, false, false, false, false);
        CellRangeAddress region = new CellRangeAddress(countRow, countRow, 0, 21);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        countRow++;

        setCellFontColor(COLOR_BLACK);
        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "EMBARCAÇÃO", 7000, HSSFCellStyle.ALIGN_LEFT, true, true, true, true);
        criarCelula(row, 1, "IMO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 2, "VGM CHEGADA", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 3, "SITUAÇÃO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 4, "CMT CHEGADA", 7000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 5, "PORTO ORIGEM", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 6, "PORTO DESTINO", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 7, "ESCALA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 8, "MANIFESTO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 9, "CONHECIMENTO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 10, "AGENCIA ORIGEM", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 11, "ETA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 12, "HOC", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 13, "PRODUTO", 5000, HSSFCellStyle.ALIGN_LEFT, true, true, false, true);
        criarCelula(row, 14, "QUANTIDADE TONELADAS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 15, "QUANTIDADE METROS CÚBICOS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 16, "INÍCIO OPERAÇÃO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 17, "TERMINO OPERAÇÃO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 18, "ANEXO ÚNICO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 19, "SITUAÇÃO DO AFRMM", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 20, "SISTUAÇÃO DA CARGA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 21, "SISTAM", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);

        setCellFontBold(false);
        setCellBackgroundColor(COLOR_WHITE);
        row = sheet.createRow(countRow++);

        int linha = 0;
        for (Map.Entry<Agenciamento, List<DocumentacaoCabotagem>> map : cabotagensPorAgenciamento.entrySet()) {
            Agenciamento agenciamento = map.getKey();
            List<DocumentacaoCabotagem> documentacoes = map.getValue();

            int total = 0;
            setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

            criarCelulaSemBorda(row, 0, agenciamento.getEmbarcacao().getNomeCompleto());
            criarCelulaSemBordaCentralizado(row, 1, agenciamento.getEmbarcacao().getImo());
            criarCelulaSemBordaCentralizado(row, 2, agenciamento.getVgm());
            criarCelulaSemBordaCentralizado(row, 3, agenciamento.getStatusEmbarcacao().getPorExtenso());
            criarCelulaSemBordaCentralizado(row, 4, agenciamento.getAgenciementoViagem().getComandanteEntrada());
            criarCelulaSemBordaCentralizado(row, 5, agenciamento.getPortoOrigem() == null ? "" : String.valueOf(agenciamento.getPortoOrigem().getNomeCompleto()));
            criarCelulaSemBordaCentralizado(row, 6, agenciamento.getPortoDestino() == null ? "" : String.valueOf(agenciamento.getPortoDestino().getNomeCompleto()));
            criarCelulaSemBordaCentralizado(row, 7, agenciamento.getNumeroEscalaMercante() == null ? "" : String.valueOf(agenciamento.getNumeroEscalaMercante()));
            criarCelulaSemBordaCentralizado(row, 11, formatDate(agenciamento.getEta(), DATE_HOUR_PATTERN, null));
            criarCelulaSemBordaCentralizado(row, 12, formatDate(agenciamento.getDataChegada(), DATE_HOUR_PATTERN, null));
            criarCelulaSemBordaCentralizado(row, 21, agenciamento.getNumeroProcessoformatado());

            for (DocumentacaoCabotagem documentacao : documentacoes) {
                criarCelulaSemBordaCentralizado(row, 8, documentacao.getManifestoEletronico());
                criarCelulaSemBordaCentralizado(row, 10, documentacao.getAgencia() == null ? "" : documentacao.getAgencia().getNome());

                for (DocumentacaoOperacao produto : documentacao.getDocumentacaoProdutos()) {
                    total++;

                    Operacao operacao = produto.getOperacao();

                    criarCelulaCentralizado(row, 9, produto.getConhecimentoEletronico(), false, true, false, true);
                    criarCelula(row, 13, operacao.getProduto().getNomeCompleto(), false, true, false, true);

                    if (operacao.isUnidadeMedidaTonelada()) {
                        criarCelulaCentralizado(row, 14, operacao.getQuantidadeEstimadaFormatada(), false, true, false, true);
                        criarCelulaCentralizado(row, 15, "", false, true, false, true);
                    } else if (operacao.isUnidadeMedidaMetroCubico()) {
                        criarCelulaCentralizado(row, 14, "", false, true, false, true);
                        criarCelulaCentralizado(row, 15, operacao.getQuantidadeEstimadaFormatada(), false, true, false, true);
                    }

                    criarCelulaCentralizado(row, 16, formatDate(operacao.getDataInicio(), DATE_PATTERN, null), false, true, false, true);
                    criarCelulaCentralizado(row, 17, formatDate(operacao.getDataFim(), DATE_PATTERN, null), false, true, false, true);
                    criarCelulaCentralizado(row, 18, produto.getAnexoUnicoRecebidoPorExtenso(), false, true, false, true);
                    criarCelulaCentralizado(row, 19, produto.getSituacaoAfrm() == null ? "" : produto.getSituacaoAfrm().getPorExtenso(), false, true, false, true);
                    criarCelulaCentralizado(row, 20, produto.getSituacaoCarga() == null ? "" : produto.getSituacaoCarga().getPorExtenso(), false, true, false, true);

                    row = sheet.createRow(countRow++);
                }

                Integer[] cellToMerge = new Integer[]{8, 10};
                for (Integer column : cellToMerge) {
                    region = new CellRangeAddress(countRow - (documentacao.getDocumentacaoProdutos().size() + 1), countRow - 2, column, column);
                    if (documentacao.getDocumentacaoProdutos().size() > 1) {
                        sheet.addMergedRegion(region);
                    }
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                }
            }

            Integer[] cellToMerge = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 11, 12, 21};
            for (Integer column : cellToMerge) {
                region = new CellRangeAddress(countRow - total - 1, countRow - 2, column, column);
                if (total > 1) {
                    sheet.addMergedRegion(region);
                }
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
            }
        }

        if (!listaOperacoesSemDocumentacao.isEmpty()) {

            Map<Agenciamento, List<Operacao>> operacoesDescargaCabotagemSemDocumentacaoPorAgenciamento = new LinkedHashMap<Agenciamento, List<Operacao>>();

            for (Operacao operacao : listaOperacoesSemDocumentacao) {
                Agenciamento agenciamento = operacao.getAgenciamento();

                List<Operacao> operacoes = new ArrayList<Operacao>();
                if (operacoesDescargaCabotagemSemDocumentacaoPorAgenciamento.containsKey(agenciamento)) {
                    operacoes = operacoesDescargaCabotagemSemDocumentacaoPorAgenciamento.get(agenciamento);
                }
                operacoes.add(operacao);
                operacoesDescargaCabotagemSemDocumentacaoPorAgenciamento.put(agenciamento, operacoes);
            }

            setCellFontBold(false);
            setCellFontColor(COLOR_RED);

            for (Map.Entry<Agenciamento, List<Operacao>> map : operacoesDescargaCabotagemSemDocumentacaoPorAgenciamento.entrySet()) {
                Agenciamento agenciamento = map.getKey();
                List<Operacao> operacoes = map.getValue();

                setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

                criarCelula(row, 0, agenciamento.getEmbarcacao().getNomeCompleto(), true, true, true, true);
                criarCelulaCentralizado(row, 1, agenciamento.getEmbarcacao().getImo(), true, true, true, true);
                criarCelulaCentralizado(row, 2, agenciamento.getVgm(), true, true, true, true);
                criarCelulaCentralizado(row, 3, agenciamento.getStatusEmbarcacao().getPorExtenso(), true, true, true, true);
                criarCelulaCentralizado(row, 4, agenciamento.getAgenciementoViagem().getComandanteEntrada(), true, true, true, true);
                criarCelulaCentralizado(row, 5, agenciamento.getPortoOrigem() == null ? "" : String.valueOf(agenciamento.getPortoOrigem().getNomeCompleto()), true, true, true, true);
                criarCelulaCentralizado(row, 6, agenciamento.getPortoDestino() == null ? "" : String.valueOf(agenciamento.getPortoDestino().getNomeCompleto()), true, true, true, true);
                criarCelulaCentralizado(row, 7, agenciamento.getNumeroEscalaMercante() == null ? "" : String.valueOf(agenciamento.getNumeroEscalaMercante()), true, true, true, true);
                criarCelulaCentralizado(row, 8, "", true, true, true, true);
                criarCelulaCentralizado(row, 9, "", true, true, true, true);
                criarCelulaCentralizado(row, 10, "", true, true, true, true);
                criarCelulaCentralizado(row, 11, formatDate(agenciamento.getEta(), DATE_HOUR_PATTERN, null), true, true, true, true);
                criarCelulaCentralizado(row, 12, formatDate(agenciamento.getDataChegada(), DATE_HOUR_PATTERN, null), true, true, true, true);
                criarCelulaCentralizado(row, 18, "", true, true, true, true);
                criarCelulaCentralizado(row, 19, "", true, true, true, true);
                criarCelulaCentralizado(row, 20, "", true, true, true, true);
                criarCelulaCentralizado(row, 21, agenciamento.getNumeroProcessoformatado(), true, true, true, true);

                int total = 0;
                for (Operacao operacao : operacoes) {
                    total++;

                    criarCelula(row, 13, operacao.getProduto().getNomeCompleto(), true, true, true, true);
                    if (operacao.isUnidadeMedidaTonelada()) {
                        criarCelulaCentralizado(row, 14, operacao.getQuantidadeEstimadaFormatada(), true, true, true, true);
                        criarCelulaCentralizado(row, 15, "", true, true, true, true);

                    } else if (operacao.isUnidadeMedidaMetroCubico()) {
                        criarCelulaCentralizado(row, 14, "", true, true, true, true);
                        criarCelulaCentralizado(row, 15, operacao.getQuantidadeEstimadaFormatada(), true, true, true, true);
                    }
                    criarCelulaCentralizado(row, 16, formatDate(operacao.getDataInicio(), DATE_PATTERN, null), false, true, false, true);
                    criarCelulaCentralizado(row, 17, formatDate(operacao.getDataFim(), DATE_PATTERN, null), true, true, true, true);
                    row = sheet.createRow(countRow++);
                }

                Integer[] cellToMerge = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 18, 19, 20, 21};
                for (Integer column : cellToMerge) {
                    region = new CellRangeAddress(countRow - total - 1, countRow - 2, column, column);
                    if (total > 1) {
                        sheet.addMergedRegion(region);
                    }
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                }
            }
        }

        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, new CellRangeAddress(starting + 1, countRow - 2, 0, 0), sheet, workbook);
        return countRow;
    }

    private int adicionarTabelaCargaCabotagem(List<DocumentacaoCabotagem> listaCabotagens, List<Operacao> listaOperacoesSemDocumentacao, HSSFSheet sheet, int countRow) {
        Map<Agenciamento, List<DocumentacaoCabotagem>> cabotagensPorAgenciamento = new LinkedHashMap<Agenciamento, List<DocumentacaoCabotagem>>();
        Map<Operacao, Integer> totaisPorOperacaoProduto = new LinkedHashMap<Operacao, Integer>();

        for (DocumentacaoCabotagem documentacao : listaCabotagens) {
            Agenciamento agenciamento = documentacao.getAgenciamento();

            List<DocumentacaoCabotagem> documentacoes = new ArrayList<DocumentacaoCabotagem>();
            if (cabotagensPorAgenciamento.containsKey(agenciamento)) {
                documentacoes = cabotagensPorAgenciamento.get(agenciamento);
            }
            documentacoes.add(documentacao);
            cabotagensPorAgenciamento.put(agenciamento, documentacoes);

            for (DocumentacaoOperacao produto : documentacao.getDocumentacaoProdutos()) {
                Operacao operacao = produto.getOperacao();

                Integer total = 0;
                if (totaisPorOperacaoProduto.containsKey(operacao)) {
                    total = totaisPorOperacaoProduto.get(operacao);
                }
                total++;
                totaisPorOperacaoProduto.put(operacao, total);
            }
        }

        Map<Agenciamento, Documento> anexosUnicoPorAgenciamento = getService().buscarDocumentoAnexoUnicoPorAgenciamento(new HashSet<Agenciamento>(cabotagensPorAgenciamento.keySet()));

        int starting = countRow++;

        HSSFWorkbook workbook = sheet.getWorkbook();
        HSSFRow row = sheet.createRow(countRow);

        setCellFontBold(true);
        setCellFontColor(COLOR_WHITE);
        setCellBackgroundColor(COLOR_DARK_BLUE);
        criarCelula(row, 0, "CARGA CABOTAGEM", null, HSSFCellStyle.ALIGN_LEFT, false, false, false, false);
        CellRangeAddress region = new CellRangeAddress(countRow, countRow, 0, 26);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        countRow++;

        setCellFontColor(COLOR_BLACK);
        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "EMBARCAÇÃO", 7000, HSSFCellStyle.ALIGN_LEFT, true, true, true, true);
        criarCelula(row, 1, "IMO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 2, "VGM SAÍDA", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 3, "HOC", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 4, "SITUAÇÃO", 3000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 5, "CMT CHEGADA", 7000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 6, "PORTO ORIGEM", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 7, "PORTO DESTINO", 9000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 8, "ETS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 9, "ESCALA", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        setCellBackgroundColor(COLOR_LIGHT_YELLOW);
        criarCelula(row, 10, "PRODUTO DA OPERACAO", 7000, HSSFCellStyle.ALIGN_LEFT, true, true, false, true);
        criarCelula(row, 11, "QUANTIDADE TONELADAS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 12, "QUANTIDADE METROS CÚBICOS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 13, "INÍCIO DA OPERAÇÃO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 14, "TERMINO DA OPERAÇÃO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 15, "ANEXO ÚNICO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        setCellBackgroundColor(COLOR_LIGHT_GREEN);
        criarCelula(row, 16, "MANIFESTO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 17, "CONHECIMENTO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 18, "PRODUTO", 5000, HSSFCellStyle.ALIGN_LEFT, true, true, false, true);
        criarCelula(row, 19, "QUANTIDADE KG", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 20, "QUANTIDADE LITROS", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 21, "DESTINO", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 22, "TERMINAL", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 23, "NÚMERO CTAC", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 24, "DATA EMISSÃO CTAC", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 25, "DATA FORMULÁRIO CTAC", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);
        criarCelula(row, 26, "SISTAM", 5000, HSSFCellStyle.ALIGN_CENTER, true, true, false, true);

        setCellFontBold(false);
        setCellBackgroundColor(COLOR_WHITE);
        row = sheet.createRow(countRow++);

        int linha = 0;
        for (Map.Entry<Agenciamento, List<DocumentacaoCabotagem>> map : cabotagensPorAgenciamento.entrySet()) {
            Agenciamento agenciamento = map.getKey();
            List<DocumentacaoCabotagem> documentacoes = map.getValue();

            int total = 0;
            setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

            criarCelulaSemBorda(row, 0, agenciamento.getEmbarcacao().getNomeCompleto());
            criarCelulaSemBordaCentralizado(row, 1, agenciamento.getEmbarcacao().getImo());
            criarCelulaSemBordaCentralizado(row, 2, agenciamento.getVgmSaida());
            criarCelulaSemBordaCentralizado(row, 3, formatDate(agenciamento.getDataChegada(), DATE_HOUR_PATTERN, null));
            criarCelulaSemBordaCentralizado(row, 4, agenciamento.getStatusEmbarcacao().getPorExtenso());
            criarCelulaSemBordaCentralizado(row, 5, agenciamento.getAgenciementoViagem().getComandanteEntrada());
            criarCelulaSemBordaCentralizado(row, 6, agenciamento.getPortoOrigem().getNomeCompleto());
            criarCelulaSemBordaCentralizado(row, 7, agenciamento.getPortoDestino() == null ? "" : String.valueOf(agenciamento.getPortoDestino().getNomeCompleto()));
            criarCelulaSemBordaCentralizado(row, 8, formatDate(agenciamento.getDataEstimadaSaida(), DATE_HOUR_PATTERN, null));
            criarCelulaSemBordaCentralizado(row, 9, agenciamento.getNumeroEscalaMercante() == null ? "" : String.valueOf(agenciamento.getNumeroEscalaMercante()));
            criarCelulaSemBordaCentralizado(row, 26, agenciamento.getNumeroProcessoformatado());
            if (anexosUnicoPorAgenciamento.containsKey(agenciamento)) {
                criarCelulaSemBordaCentralizado(row, 15, formatDate(anexosUnicoPorAgenciamento.get(agenciamento).getDataEmissao(), DATE_PATTERN, null));
            } else {
                criarCelulaSemBordaCentralizado(row, 15, "");
            }

            for (DocumentacaoCabotagem documentacao : documentacoes) {

                criarCelulaSemBordaCentralizado(row, 16, documentacao.getManifestoEletronico());
                criarCelulaSemBordaCentralizado(row, 21, documentacao.getPorto().getNomeCompleto());

                if (!documentacao.isPermitidoEditarCE()) {
                    criarCelulaSemBordaCentralizado(row, 17, documentacao.getConhecimentoEletronico());
                }
                if (!documentacao.isPermitidoEditarCTAC()) {
                    criarCelulaSemBordaCentralizado(row, 23, documentacao.getNumeroCTAC());

                    Documento conhecimentoEmbarque = getService().buscarDocumentoPorTipoAgenciamentoCtacPortoDestino(TipoDocumento.CONHECIMENTO_EMBARQUE, agenciamento, documentacao.getNumeroCTAC(), documentacao.getPorto());
                    if (conhecimentoEmbarque == null) {
                        criarCelulaSemBordaCentralizado(row, 24, "");
                        criarCelulaSemBordaCentralizado(row, 25, "");
                    } else {
                        criarCelulaSemBordaCentralizado(row, 24, formatDate(conhecimentoEmbarque.getDataEmissao(), DATE_PATTERN, null));
                        criarCelulaSemBordaCentralizado(row, 25, formatDate(conhecimentoEmbarque.getDataFormulario(), DATE_PATTERN, null));
                    }
                }

                for (DocumentacaoOperacao produto : documentacao.getDocumentacaoProdutos()) {
                    total++;

                    Operacao operacao = produto.getOperacao();

                    if (documentacao.isPermitidoEditarCE()) {
                        criarCelulaCentralizado(row, 17, produto.getConhecimentoEletronico(), false, true, false, true);
                    }

                    criarCelula(row, 18, operacao.getProduto().getNomeCompleto(), false, true, false, true);

                    criarCelulaCentralizado(row, 19, produto.getQuantidadeKgFormatada(), false, true, false, true);
                    criarCelulaCentralizado(row, 20, produto.getQuantidadeLtFormatada(), false, true, false, true);
                    criarCelulaCentralizado(row, 22, produto.getTerminal(), false, true, false, true);

                    if (documentacao.isPermitidoEditarCTAC()) {
                        criarCelulaCentralizado(row, 23, produto.getCtac(), false, true, false, true);

                        Documento conhecimentoEmbarque = getService().buscarDocumentoPorTipoAgenciamentoCtacPortoDestino(TipoDocumento.CONHECIMENTO_EMBARQUE, agenciamento, produto.getCtac(), documentacao.getPorto());
                        if (conhecimentoEmbarque == null) {
                            criarCelulaCentralizado(row, 24, "", false, true, false, true);
                            criarCelulaCentralizado(row, 25, "", false, true, false, true);
                        } else {
                            criarCelulaCentralizado(row, 24, formatDate(conhecimentoEmbarque.getDataEmissao(), DATE_PATTERN, null), false, true, false, true);
                            criarCelulaCentralizado(row, 25, formatDate(conhecimentoEmbarque.getDataFormulario(), DATE_PATTERN, null), false, true, false, true);
                        }
                    }

                    row = sheet.createRow(countRow++);
                }

                Integer[] cellToMerge = new Integer[]{16, 21}; /* 10 - PRODUTO OPERACAO, 11 - QTDE ESTIM. TONELADA, 12 - QTDE ESTIMADA M3,  14{4} - MANIFESTO, 22{14} - DESTINO, */
                if (!documentacao.isPermitidoEditarCE()) {
                    cellToMerge = (Integer[]) ArrayUtils.add(cellToMerge, 17); /*15{5} - CONHECIMENTO*/
                }
                if (!documentacao.isPermitidoEditarCTAC()) {
                    cellToMerge = (Integer[]) ArrayUtils.addAll(cellToMerge, new Integer[]{23, 24, 25}); /*24{16} - NÚMERO CTAC, 25{17} - DATA EMISSÃO CTAC, 26{18} - DATA FORMULÁRIO CTAC*/
                }

                for (Integer column : cellToMerge) {
                    region = new CellRangeAddress(countRow - (documentacao.getDocumentacaoProdutos().size() + 1), countRow - 2, column, column);
                    if (documentacao.getDocumentacaoProdutos().size() > 1) {
                        sheet.addMergedRegion(region);
                    }
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                }
            }

            Integer[] cellToMerge = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 26, 15}; /* 0 - NAVIO, 1 - IMO, 2{1} - VGM, 3 - HOC, 4 - SITUACAO, 5 - CMT CHEGADA, 6 - PORTO ORIGEM, 7 - PORTO DESTINO, 8{2} - ETS, 9{3} - ESCALA, 21{13} - SISTAM, 13{19} - ANEXO UNICO */
            for (Integer column : cellToMerge) {
                region = new CellRangeAddress(countRow - total - 1, countRow - 2, column, column);
                if (total > 1) {
                    sheet.addMergedRegion(region);
                }
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
            }
        }


        Integer totais = starting + 3;
        linha = 0;
        for (Map.Entry<Operacao, Integer> entry : totaisPorOperacaoProduto.entrySet()) {
            Operacao operacao = entry.getKey();
            int totalOperacao = entry.getValue();

            Produto produto = operacao.getProduto();

            HSSFRow rowExistente = sheet.getRow(totais);

            setCellBackgroundColor(rowExistente.getRowStyle().getFillForegroundColor());

            criarCelula(rowExistente, 10, produto.getNomeCompleto(), false, true, false, true);
            if (operacao.isUnidadeMedidaTonelada()) {
                criarCelulaCentralizado(rowExistente, 11, operacao.getQuantidadeEstimadaFormatada(), false, true, false, true);
                criarCelulaCentralizado(rowExistente, 12, "", false, true, false, true);
            } else if (operacao.isUnidadeMedidaMetroCubico()) {
                criarCelulaCentralizado(rowExistente, 11, "", false, true, false, true);
                criarCelulaCentralizado(rowExistente, 12, operacao.getQuantidadeEstimadaFormatada(), false, true, false, true);
            }

            criarCelulaCentralizado(rowExistente, 13, formatDate(operacao.getDataInicio(), DATE_PATTERN, null), false, true, false, true);
            criarCelulaCentralizado(rowExistente, 14, formatDate(operacao.getDataFim(), DATE_PATTERN, null), false, true, false, true);

            Integer[] cellToMerge = new Integer[]{10, 11, 12, 13, 14};
            for (Integer column : cellToMerge) {
                region = new CellRangeAddress(totais, totais + totalOperacao - 1, column, column);
                sheet.addMergedRegion(region);
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
            }
            totais += totalOperacao;
        }

        if (!listaOperacoesSemDocumentacao.isEmpty()) {

            Map<Agenciamento, List<Operacao>> operacoesCabotagemSemDocumentacaoPorAgenciamento = new LinkedHashMap<Agenciamento, List<Operacao>>();

            for (Operacao operacao : listaOperacoesSemDocumentacao) {
                Agenciamento agenciamento = operacao.getAgenciamento();

                List<Operacao> operacoes = new ArrayList<Operacao>();
                if (operacoesCabotagemSemDocumentacaoPorAgenciamento.containsKey(agenciamento)) {
                    operacoes = operacoesCabotagemSemDocumentacaoPorAgenciamento.get(agenciamento);
                }
                operacoes.add(operacao);
                operacoesCabotagemSemDocumentacaoPorAgenciamento.put(agenciamento, operacoes);
            }

            setCellFontBold(false);
            setCellFontColor(COLOR_RED);

            for (Map.Entry<Agenciamento, List<Operacao>> map : operacoesCabotagemSemDocumentacaoPorAgenciamento.entrySet()) {
                Agenciamento agenciamento = map.getKey();
                List<Operacao> operacoes = map.getValue();

                setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

                criarCelula(row, 0, agenciamento.getEmbarcacao().getNomeCompleto(), false, true, false, true);
                criarCelulaCentralizado(row, 1, agenciamento.getEmbarcacao().getImo(), false, true, false, true);
                criarCelulaCentralizado(row, 2, agenciamento.getVgm(), false, true, false, true);
                criarCelulaCentralizado(row, 3, formatDate(agenciamento.getDataChegada(), DATE_HOUR_PATTERN, null), false, true, false, true);
                criarCelulaCentralizado(row, 4, agenciamento.getStatusEmbarcacao().getPorExtenso(), false, true, false, true);
                criarCelulaCentralizado(row, 5, agenciamento.getAgenciementoViagem().getComandanteEntrada(), false, true, false, true);
                criarCelulaCentralizado(row, 6, agenciamento.getPortoOrigem().getNomeCompleto(), false, true, false, true);
                criarCelulaCentralizado(row, 7, agenciamento.getPortoDestino() == null ? "" : String.valueOf(agenciamento.getPortoDestino().getNomeCompleto()), false, true, false, true);
                criarCelulaCentralizado(row, 8, formatDate(agenciamento.getDataEstimadaSaida(), DATE_HOUR_PATTERN, null), false, true, false, true);
                criarCelulaCentralizado(row, 9, agenciamento.getNumeroEscalaMercante() == null ? "" : String.valueOf(agenciamento.getNumeroEscalaMercante()), false, true, false, true);
                criarCelulaCentralizado(row, 15, "", true, true, true, true);
                criarCelulaCentralizado(row, 16, "", true, true, true, true);
                criarCelulaCentralizado(row, 17, "", true, true, true, true);
                criarCelula(row, 18, "", false, true, false, true);
                criarCelulaCentralizado(row, 19, "", false, true, false, true);
                criarCelulaCentralizado(row, 20, "", false, true, false, true);
                criarCelulaCentralizado(row, 21, "", true, true, true, true);
                criarCelulaCentralizado(row, 22, "", false, true, false, true);
                criarCelulaCentralizado(row, 23, "", true, true, true, true);
                criarCelulaCentralizado(row, 24, "", true, true, true, true);
                criarCelulaCentralizado(row, 25, "", true, true, true, true);
                criarCelulaCentralizado(row, 26, agenciamento.getNumeroProcessoformatado(), true, true, true, true);

                int total = 0;
                for (Operacao operacao : operacoes) {
                    total++;

                    criarCelula(row, 10, operacao.getProduto().getNomeCompleto(), false, true, false, true);
                    if (operacao.isUnidadeMedidaTonelada()) {
                        criarCelulaCentralizado(row, 11, operacao.getQuantidadeEstimadaFormatada(), false, true, false, true);
                        criarCelulaCentralizado(row, 12, "", true, true, true, true);
                    } else if (operacao.isUnidadeMedidaMetroCubico()) {
                        criarCelulaCentralizado(row, 11, "", true, true, true, true);
                        criarCelulaCentralizado(row, 12, operacao.getQuantidadeEstimadaFormatada(), false, true, false, true);
                    }
                    criarCelulaCentralizado(row, 13, formatDate(operacao.getDataInicio(), DATE_PATTERN, null), false, true, false, true);
                    criarCelulaCentralizado(row, 14, formatDate(operacao.getDataFim(), DATE_PATTERN, null), false, true, false, true);
                    row = sheet.createRow(countRow++);
                }

                Integer[] cellToMerge = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
                for (Integer column : cellToMerge) {
                    region = new CellRangeAddress(countRow - total - 1, countRow - 2, column, column);
                    if (total > 1) {
                        sheet.addMergedRegion(region);
                    }
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                }
            }
        }

        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, new CellRangeAddress(starting + 1, countRow - 2, 0, 0), sheet, workbook);
        return countRow;
    }

    private int adicionarCabecalhoNoXls(HSSFSheet sheet, int countRow) throws IOException {
        Workbook workbook = sheet.getWorkbook();

        String[][] filtros = new String[][]{
            {"Agência", filtro.getAgencia() == null ? "-" : filtro.getAgencia().getNome()},
            {"Porto", filtro.isPortoPreenchido() ? filtro.getPorto().getNomeCompleto() : "-"},
            {"Tipo de Operação", filtro.getTiposOperacao().isEmpty() ? "-" : filtro.getTiposOperacaoDescricao()},
            {"Tipo de Contrato", filtro.getTiposContrato().isEmpty() ? "-" : filtro.getTiposContratoDescricao()},
            {"Situação da Embarcação no Porto", filtro.getSituacoesEmbarcacao().isEmpty() ? "-" : filtro.getSituacoesEmbarcacaoDescricao()},
            {"Hora Oficial de Chegada", filtro.getPeriodoOficialChegada().getDataPeriodoDescricao()},
            {"ETA", filtro.getPeriodoEta().getDataPeriodoDescricao()},
            {"Hora Oficial de Saída", filtro.getPeriodoOficialSaida().getDataPeriodoDescricao()},
            {"ETS", filtro.getPeriodoEts().getDataPeriodoDescricao()},
            {"Início da Operação", filtro.getPeriodoInicioOperacao().getDataPeriodoDescricao()},
            {"Termino da Operação", filtro.getPeriodoTerminoOperacao().getDataPeriodoDescricao()},
            {"Com Manifesto?", filtro.getComManisfesto() == null ? "Todos" : filtro.getComManisfesto().getPorExtenso()},
            {"Com Escala Mercante?", filtro.getComEscalaMercante() == null ? "Todos" : filtro.getComEscalaMercante().getPorExtenso()},
            {"Data de Inclusão no Sistam", filtro.getPeriodoInclusao().getDataPeriodoDescricao()},
            {"Ordenação", filtro.getOrdenacao().getPorExtenso()}
        };

        CellRangeAddress labelRegion = null;
        CellRangeAddress valueRegion = null;
        for (String[] linha : filtros) {
            HSSFRow row = sheet.createRow(countRow);

            criarCelulaSemBorda(row, 0, linha[0]);
            labelRegion = new CellRangeAddress(countRow, countRow, 0, 1);
            sheet.addMergedRegion(labelRegion);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);

            criarCelulaSemBorda(row, 2, linha[1]);
            valueRegion = new CellRangeAddress(countRow, countRow, 2, 5);
            sheet.addMergedRegion(valueRegion);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, valueRegion, sheet, workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, valueRegion, sheet, workbook);

            countRow++;
        }
        HSSFRow row = sheet.createRow(countRow);

        setCellFontColor(COLOR_RED);
        criarCelulaSemBorda(row, 0, "Linhas em vermelho");
        labelRegion = new CellRangeAddress(countRow, countRow, 0, 1);
        sheet.addMergedRegion(labelRegion);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);

        setCellFontColor(COLOR_RED);
        criarCelulaSemBorda(row, 2, "Agenciamento com Operação, mas sem documentação associada.");
        valueRegion = new CellRangeAddress(countRow, countRow, 2, 5);
        sheet.addMergedRegion(valueRegion);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, valueRegion, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, valueRegion, sheet, workbook);

        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, valueRegion, sheet, workbook);

        return countRow + 1;
    }

    private void adicionarLogoCabecalhoNoXls(HSSFSheet sheet) throws IOException {
        sheet.addMergedRegion(new CellRangeAddress(0, 4, 0, 6));
        new AddDimensionedImageToXLS().addImageToSheet("A1", sheet, sheet.createDrawingPatriarch(),
                DesktopUtil.getUrlDaLogoPetrobras(), 25, 20,
                AddDimensionedImageToXLS.OVERLAY_ROW_AND_COLUMN);

        sheet.addMergedRegion(new CellRangeAddress(0, 4, 7, 7));
        new AddDimensionedImageToXLS().addImageToSheet("H1", sheet, sheet.createDrawingPatriarch(),
                DesktopUtil.getUrlDaLogoNP1(), 7, 17,
                AddDimensionedImageToXLS.OVERLAY_ROW_AND_COLUMN);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(FiltroRelatorioSiscomex.PROP_AGENCIA)) {
            if (filtro.getAgencia() != null) { 
                portos = getService().buscarPortosPorAgencia(filtro.getAgencia());
                portos.add(0, null);
            } else { 
                if(filtro.getAgencia() == null){
                    portos.clear();
                }
            } 
            firePropertyChange("portos", null, null);
        }
    }
}