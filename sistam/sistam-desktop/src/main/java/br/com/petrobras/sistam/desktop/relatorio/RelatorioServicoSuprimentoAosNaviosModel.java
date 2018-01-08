package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.common.enums.TipoMaterial;
import br.com.petrobras.sistam.common.enums.TipoMercadorias;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import static br.com.petrobras.sistam.common.util.SistamDateUtils.DATE_HOUR_PATTERN;
import static br.com.petrobras.sistam.common.util.SistamDateUtils.DATE_PATTERN;
import static br.com.petrobras.sistam.common.util.SistamDateUtils.formatDate;
import static br.com.petrobras.sistam.common.util.SistamUtils.*;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioServicoSuprimentoAosNavios;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_LIGHT_GREEN;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_LIGHT_YELLOW;
import static br.com.petrobras.sistam.desktop.relatorio.AbstractRelatorioXlsModel.COLOR_WHITE;
import br.com.petrobras.sistam.desktop.util.AddDimensionedImageToXLS;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class RelatorioServicoSuprimentoAosNaviosModel extends AbstractRelatorioXlsModel {

    private FiltroRelatorioServicoSuprimentoAosNavios filtro;
    private List<Agencia> agencias = new ArrayList<Agencia>();
    private List<Porto> portos;
    private List<TipoMaterial> tiposMaterial = new ArrayList<TipoMaterial>();
    private List<TipoMercadorias> tiposAcesso = new ArrayList<TipoMercadorias>();
    private List<EmpresaMaritima> empresasMaritima = new ArrayList<EmpresaMaritima>();
    private List<Servico> servicos = new ArrayList<Servico>();

    public RelatorioServicoSuprimentoAosNaviosModel() {
        this.filtro = new FiltroRelatorioServicoSuprimentoAosNavios();
        this.filtro.addPropertyChangeListener(this);

        SistamCredentialsBean credentialsBean = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        this.agencias = credentialsBean.getAgenciasAutorizadas();

        this.tiposMaterial.addAll(TipoMaterial.listValues());
        this.tiposMaterial.add(0, null);

        this.tiposAcesso.addAll(TipoMercadorias.listValues());
        this.tiposAcesso.add(0, null);
    }

    public FiltroRelatorioServicoSuprimentoAosNavios getFiltro() {
        return filtro;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }
    
     public List<Porto> getPortos() {
        return portos;
    }

    public List<TipoMaterial> getTiposMaterial() {
        return tiposMaterial;
    }

    public List<TipoMercadorias> getTiposAcesso() {
        return tiposAcesso;
    }

    public List<EmpresaMaritima> getEmpresasMaritima() {
        return empresasMaritima;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("agencia".equals(evt.getPropertyName())) {
            final Agencia agencia = filtro.getAgencia();
            if (agencia == null) {
                empresasMaritima.clear();
                servicos.clear();
                filtro.setEmpresaMaritima(null);
                filtro.setServico(null);
                portos.clear();
            } else {
                empresasMaritima = getService().buscarEmpresasMaritimasPorAgenciaTipoServico(agencia, TipoServico.SUPRIMENTO);
                empresasMaritima.add(0, null);
                
                portos = getService().buscarPortosPorAgencia(filtro.getAgencia());
                portos.add(0, null); 
            }
            firePropertyChange("portos", null, null);
        }

        if ("empresaMaritima".equals(evt.getPropertyName())) {
            final EmpresaMaritima empresaMaritima = filtro.getEmpresaMaritima();
            if (empresaMaritima == null) {
                servicos.clear();
                filtro.setServico(null);
            } else {
                servicos = getService().buscarServicosPorEmpresaETipo(empresaMaritima, TipoServico.SUPRIMENTO);
                servicos.add(0, null);
            }
        }

        firePropertyChange("empresasMaritima", null, null);
        firePropertyChange("servicos", null, null);
    }

    public void gerar() {
        visualizarArquivo("ServicoSuprimentoAosNavios");
    }

    @Override
    protected String getNomeArquivoParaXls() {
        StringBuilder nomeArquivo = new StringBuilder();
        nomeArquivo.append("ServicoSuprimentoAosNavios")
                .append("-").append(formatDate(new Date(), "ddMMyyyy", null));
        return nomeArquivo.toString();
    }

    @Override
    protected void gerarConteudoXls(HSSFSheet sheet) throws IOException {

        List<ServicoSuprimentoTransitoEmpresa> fornecedores = getService().buscarServicoSuprimentoTransitoEmpresaParaRelatorio(filtro);

        AssertUtils.assertExpression(!fornecedores.isEmpty(), ConstantesI18N.RELATORIO_SUPRIMENTO_NAVIOS_NAO_EXISTE_DADOS);

        addColorGrey10Percent(sheet.getWorkbook());

        adicionarLogoCabecalhoNoXls(sheet);

        int countRow = 7;

        countRow = adicionarCabecalhoNoXls(sheet, countRow);

        adicionarTabela(fornecedores, sheet, countRow);
    }

    private int adicionarTabela(List<ServicoSuprimentoTransitoEmpresa> fornecedores, HSSFSheet sheet, int countRow) {
        int starting = countRow++;

        Map<ServicoSuprimentoTransito, List<ServicoSuprimentoTransitoEmpresa>> fornecedoresPorSolicitacao = new LinkedHashMap<ServicoSuprimentoTransito, List<ServicoSuprimentoTransitoEmpresa>>();
        Map<ServicoSuprimento, List<ServicoSuprimentoTransito>> solicitacoesPorSuprimento = new LinkedHashMap<ServicoSuprimento, List<ServicoSuprimentoTransito>>();
        Map<Agenciamento, List<ServicoSuprimento>> suprimentosPorAgenciamento = new LinkedHashMap<Agenciamento, List<ServicoSuprimento>>();

        for (ServicoSuprimentoTransitoEmpresa fornecedor : fornecedores) {
            ServicoSuprimentoTransito solicitacao = fornecedor.getSolicitacaoTransito();
            ServicoSuprimento suprimento = solicitacao.getServicoSuprimento();
            ServicoProtecao servicoProtecao = solicitacao.getServicoProtecao();
            Agenciamento agenciamento = servicoProtecao.getAgenciamento();

            List<ServicoSuprimentoTransitoEmpresa> listaFornecedores = new ArrayList<ServicoSuprimentoTransitoEmpresa>();
            if (fornecedoresPorSolicitacao.containsKey(solicitacao)) {
                listaFornecedores = fornecedoresPorSolicitacao.get(solicitacao);
            }
            if (!listaFornecedores.contains(fornecedor)) {
                listaFornecedores.add(fornecedor);
            }
            fornecedoresPorSolicitacao.put(solicitacao, listaFornecedores);

            List<ServicoSuprimentoTransito> listaSolicitacoes = new ArrayList<ServicoSuprimentoTransito>();
            if (solicitacoesPorSuprimento.containsKey(suprimento)) {
                listaSolicitacoes = solicitacoesPorSuprimento.get(suprimento);
            }
            if (!listaSolicitacoes.contains(solicitacao)) {
                listaSolicitacoes.add(solicitacao);
            }
            solicitacoesPorSuprimento.put(suprimento, listaSolicitacoes);

            List<ServicoSuprimento> listaSuprimentos = new ArrayList<ServicoSuprimento>();
            if (suprimentosPorAgenciamento.containsKey(agenciamento)) {
                listaSuprimentos = suprimentosPorAgenciamento.get(agenciamento);
            }
            if (!listaSuprimentos.contains(suprimento)) {
                listaSuprimentos.add(suprimento);
            }
            suprimentosPorAgenciamento.put(agenciamento, listaSuprimentos);
        }

        HSSFWorkbook workbook = sheet.getWorkbook();
        HSSFRow row = sheet.createRow(countRow);

        setCellFontBold(true);
        setCellBackgroundColor(COLOR_LIGHT_YELLOW);

        criarCelula(row, 0, "AGENCIAMENTO", null, HSSFCellStyle.ALIGN_CENTER, false, false, false, false);
        CellRangeAddress region = new CellRangeAddress(countRow, countRow, 0, 1);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);

        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        criarCelula(row, 2, "OPERAÇÃO PORTUÁRIA", null, HSSFCellStyle.ALIGN_CENTER, false, false, false, false);
        region = new CellRangeAddress(countRow, countRow, 2, 18);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);

        setCellBackgroundColor(COLOR_LIGHT_YELLOW);

        criarCelula(row, 19, "SOLICITAÇÃO", null, HSSFCellStyle.ALIGN_CENTER, false, false, false, false);
        region = new CellRangeAddress(countRow, countRow, 19, 22);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);

        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        criarCelula(row, 23, "FORNECEDOR", null, HSSFCellStyle.ALIGN_CENTER, false, false, false, false);
        region = new CellRangeAddress(countRow, countRow, 23, 27);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        
        setCellBackgroundColor(COLOR_LIGHT_YELLOW);
        criarCelula(row, 28, "OBSERVAÇÕES", 5000, null, true, true, false, true);
        
        countRow++;

        setCellFontColor(COLOR_BLACK);
        setCellBackgroundColor(COLOR_LIGHT_YELLOW);

        row = sheet.createRow(countRow++);
        criarCelula(row, 0, "EMBARCAÇÃO", 7000, null, true, true, true, true);
        criarCelula(row, 1, "VGM", 3000, null, true, true, true, true);

        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        criarCelula(row, 2, "DATA SOLICITAÇÃO", 5000, null, true, true, true, true);
        criarCelula(row, 3, "INÍCIO DA OPERAÇÃO", 5000, null, true, true, true, true);
        criarCelula(row, 4, "TÉRMINO DA OPERAÇÃO", 5000, null, true, true, true, true);
        criarCelula(row, 5, "EMPRESA TRANSPORTE", 5000, null, true, true, true, true);
        criarCelula(row, 6, "EMBARCAÇÃO TRANSPORTE", 5000, null, true, true, true, true);

        criarCelula(row, 7, "VL TRANSPORTE MARÍTIMO", 5000, null, true, true, true, true);
        criarCelula(row, 8, "VL TRANSPORTE RODOVIÁRIO", 5000, null, true, true, true, true);
        criarCelula(row, 9, "CENTRO DE CUSTO OP.", 5000, null, true, true, true, true);

        criarCelula(row, 10, "VL OPERADOR PORTUÁRIO", 5000, null, true, true, true, true);
        criarCelula(row, 11, "VL OGMO", 5000, null, true, true, true, true);
        criarCelula(row, 12, "VL TOTAL MERCADORIAS", 5000, null, true, true, true, true);
        criarCelula(row, 13, "VL HORA EXCEDENTE OP. PORTUÁRIO", 5000, null, true, true, true, true);
        criarCelula(row, 14, "VL OGMO - DOBRA", 5000, null, true, true, true, true);
        criarCelula(row, 15, "VL TOTAL OP. PORTUÁRIA", 5000, null, true, true, true, true);

        criarCelula(row, 16, "VL OP. PORTUÁRIA ANTERIOR", 5000, null, true, true, true, true);
        criarCelula(row, 17, "CENTRO DE CUSTO", 5000, null, true, true, true, true);
        criarCelula(row, 18, "JUSTIFICATIVA", 5000, null, true, true, true, true);

        setCellBackgroundColor(COLOR_LIGHT_YELLOW);

        criarCelula(row, 19, "LOCAL DE ACESSO (COMPANHIA DAS DOCAS)", 5000, null, true, true, false, true);
        criarCelula(row, 20, "LOCAL DE ACESSO (TERMINAL)", 5000, null, true, true, false, true);
        criarCelula(row, 21, "TIPO DE ACESSO", 5000, null, true, true, false, true);
        criarCelula(row, 22, "TIPO DO MATERIAL", 5000, null, true, true, false, true);

        setCellBackgroundColor(COLOR_LIGHT_GREEN);

        criarCelula(row, 23, "EMPRESA", 5000, null, true, true, false, true);
        criarCelula(row, 24, "PESO BRUTO", 5000, null, true, true, false, true);
        criarCelula(row, 25, "VOLUME", 5000, null, true, true, false, true);
        criarCelula(row, 26, "DOC/Nº DA NOTA FISCAL", 5000, null, true, true, false, true);
        criarCelula(row, 27, "VALOR MERCADORIA", 5000, null, true, true, false, true);
        
        // Merge campo Observação
        region = new CellRangeAddress(countRow -2, countRow -1, 28, 28);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);

        setCellFontBold(false);
        setCellBackgroundColor(COLOR_WHITE);
        row = sheet.createRow(countRow++);

        int linha = 0;
        for (Map.Entry<Agenciamento, List<ServicoSuprimento>> entrySet : suprimentosPorAgenciamento.entrySet()) {
            Agenciamento agenciamento = entrySet.getKey();
            List<ServicoSuprimento> suprimentos = entrySet.getValue();

            int totalNivel1 = 0;
            setCellBackgroundColor(linha++ % 2 == 0 ? COLOR_WHITE : COLOR_GREY_10_PERCENT);

            criarCelulaSemBorda(row, 0, agenciamento.getEmbarcacao().getNomeCompleto());
            criarCelulaSemBorda(row, 1, agenciamento.getVgm());

            for (ServicoSuprimento servicoSuprimento : suprimentos) {
                ServicoProtecao servicoProtecao = servicoSuprimento.getServicoProtecao();

                criarCelulaSemBorda(row, 2, formatDate(servicoProtecao.getDataExecucao(), DATE_PATTERN, null));
                criarCelulaSemBorda(row, 3, formatDate(servicoSuprimento.getDataInicioOperacao(), DATE_HOUR_PATTERN, null));
                criarCelulaSemBorda(row, 4, formatDate(servicoSuprimento.getDataFimOperacao(), DATE_HOUR_PATTERN, null));
                criarCelulaSemBorda(row, 5, servicoSuprimento.getEmpresaMaritima().getRazaoSocial());
                criarCelulaSemBorda(row, 6, servicoSuprimento.getEmpresaServico().getNomeServico());

                criarCelulaSemBorda(row, 7, getValorDecimalComPrecisao(servicoSuprimento.getValorTransporteMaritimo(), 2));
                criarCelulaSemBorda(row, 8, getValorDecimalComPrecisao(servicoSuprimento.getValorTransporteRodoviario(), 2));
                criarCelulaSemBorda(row, 9, servicoSuprimento.getCentroCusto());

                criarCelulaSemBorda(row, 10, getValorDecimalComPrecisao(servicoSuprimento.getCustoOperadorPortuario(), 2));
                criarCelulaSemBorda(row, 11, getValorDecimalComPrecisao(servicoSuprimento.getCustoOGMO(), 2));
                criarCelulaSemBorda(row, 12, getValorDecimalComPrecisao(servicoSuprimento.getValorTotalMercadorias(), 2));
                criarCelulaSemBorda(row, 13, getValorDecimalComPrecisao(servicoSuprimento.getCustoHoraExcedente(), 2));
                criarCelulaSemBorda(row, 14, getValorDecimalComPrecisao(servicoSuprimento.getCustoOgmoDobra(), 2));
                criarCelulaSemBorda(row, 15, getValorDecimalComPrecisao(servicoSuprimento.getvalorTotalOperacaoPortuaria(), 2));

                criarCelulaSemBorda(row, 16, getValorDecimalComPrecisao(servicoSuprimento.getPortuariaAnteriorDo(), 2));
                criarCelulaSemBorda(row, 17, servicoSuprimento.getCentroCustoDo());
                criarCelulaSemBorda(row, 18, servicoSuprimento.getJustificativaDo());
                criarCelulaSemBorda(row, 28, servicoProtecao.getObservacao());

                int totalNivel2 = 0;
                List<ServicoSuprimentoTransito> listaSolicitacoes = solicitacoesPorSuprimento.get(servicoSuprimento);
                for (ServicoSuprimentoTransito solicitacao : listaSolicitacoes) {

                    criarCelulaSemBorda(row, 19, solicitacao.isCompanhiaDocas() ? "Sim" : "Não");
                    criarCelulaSemBorda(row, 20, solicitacao.isTerminal() ? solicitacao.getDescricaoTerminal() : "");
                    criarCelulaSemBorda(row, 21, solicitacao.getTipoMercadorias().getPorExtenso());
                    criarCelulaSemBorda(row, 22, solicitacao.getTipoMaterial().getPorExtenso());

                    int totalNivel3 = 0;
                    List<ServicoSuprimentoTransitoEmpresa> listaFornecedores = fornecedoresPorSolicitacao.get(solicitacao);
                    for (ServicoSuprimentoTransitoEmpresa fornecedor : listaFornecedores) {
                        totalNivel1++;
                        totalNivel2++;
                        totalNivel3++;
                        criarCelula(row, 23, fornecedor.getNomePrestadorServico(), false, true, false, true);
                        criarCelula(row, 24, fornecedor.getValorPesoBruto(), false, true, false, true);
                        criarCelula(row, 25, String.valueOf(fornecedor.getQuantidadeVolume()), false, true, false, true);
                        criarCelula(row, 26, fornecedor.getDescNotaFiscal(), false, true, false, true);
                        criarCelula(row, 27, getValorDecimalComPrecisao(fornecedor.getValorMercadorias(), 2), false, true, false, true);
                        row = sheet.createRow(countRow++);
                    }

                    Integer[] cellToMerge = new Integer[]{19, 20, 21, 22};
                    for (Integer column : cellToMerge) {
                        region = new CellRangeAddress(countRow - (totalNivel3 + 1), countRow - 2, column, column);
                        if (totalNivel3 > 1) {
                            sheet.addMergedRegion(region);
                        }
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                    }
                }

                Integer[] cellToMerge = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 28};
                for (Integer column : cellToMerge) {
                    region = new CellRangeAddress(countRow - (totalNivel2 + 1), countRow - 2, column, column);
                    if (totalNivel2 > 1) {
                        sheet.addMergedRegion(region);
                    }
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                }
            }

            Integer[] cellToMerge = new Integer[]{0, 1};
            for (Integer column : cellToMerge) {
                region = new CellRangeAddress(countRow - totalNivel1 - 1, countRow - 2, column, column);
                if (totalNivel1 > 1) {
                    sheet.addMergedRegion(region);
                }
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
            }
        }
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, new CellRangeAddress(starting + 1, countRow - 2, 0, 0), sheet, workbook);

        adicionarTotais(suprimentosPorAgenciamento, solicitacoesPorSuprimento, sheet, countRow);

        return countRow;
    }

    private int adicionarCabecalhoNoXls(HSSFSheet sheet, int countRow) throws IOException {
        Workbook workbook = sheet.getWorkbook();

        String[][] filtros = new String[][]{
            {"Agência", filtro.isAgenciaPreenchido() ? filtro.getAgencia().getNome() : "-"},
            {"Nome da Embarcação", filtro.isNavioPreenchido() ? filtro.getNavio().getNomeCompleto() : "-"},
            {"Porto", filtro.isPortoPreenchido() ? filtro.getPorto().getNomeCompleto() : "-"},
            {"Número da Viagem", filtro.isNumeroViagemPreenchido() ? filtro.getNumeroViagem() : "-"},
            {"Tipo de Acesso", filtro.isTipoAcessoPreenchido() ? filtro.getTipoAcesso().getPorExtenso() : "-"},
            {"Tipo de Material", filtro.isTipoMaterialPreenchido() ? filtro.getTipoMaterial().getPorExtenso() : "-"},
            {"Fornecedor", filtro.isFornecedorPreenchido() ? filtro.getFornecedor().getRazaoSocial() : "-"},
            {"Nota Fiscal/GTRM/DTA/Outros", filtro.isNotaFiscalPreenchido() ? filtro.getNotaFiscal() : "-"},
            {"Data da Operação", filtro.getPeriodoOperacao().getDataPeriodoDescricao()},
            {"Local de Embarque", filtro.getLocalEmbarqueDescricao()},
            {"Empresa de Transporte", filtro.isEmpresaMaritimaPreenchido() ? filtro.getEmpresaMaritima().getRazaoSocial() : "-"},
            {"Embarcação", filtro.isServicoPreenchido() ? filtro.getServico().getNomeServico() : "-"}
        };

        CellRangeAddress labelRegion = null;
        CellRangeAddress valueRegion = null;
        for (String[] linha : filtros) {
            HSSFRow row = sheet.createRow(countRow);

            setCellFontBold(true);
            criarCelulaSemBorda(row, 0, linha[0]);
            labelRegion = new CellRangeAddress(countRow, countRow, 0, 1);
            sheet.addMergedRegion(labelRegion);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);

            setCellFontBold(false);
            criarCelulaSemBorda(row, 2, linha[1]);
            valueRegion = new CellRangeAddress(countRow, countRow, 2, 4);
            sheet.addMergedRegion(valueRegion);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, valueRegion, sheet, workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, valueRegion, sheet, workbook);

            countRow++;
        }
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, labelRegion, sheet, workbook);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, valueRegion, sheet, workbook);

        return countRow + 1;
    }

    private int adicionarTotais(Map<Agenciamento, List<ServicoSuprimento>> suprimentosPorAgenciamento, Map<ServicoSuprimento, List<ServicoSuprimentoTransito>> solicitacoesPorSuprimento, HSSFSheet sheet, int countRow) {
        String[][] totais = new String[][]{
            {"Total de Agenciamentos", String.valueOf(suprimentosPorAgenciamento.size())},
            {"Total de Operações", String.valueOf(solicitacoesPorSuprimento.size())}
        };

        setCellBackgroundColor(COLOR_WHITE);
        for (String[] total : totais) {
            HSSFRow row = sheet.createRow(countRow);

            setCellFontBold(true);
            criarCelulaSemBorda(row, 0, total[0]);

            setCellFontBold(false);
            criarCelulaSemBorda(row, 1, total[1]);

            countRow++;
        }

        return countRow;
    }

    private void adicionarLogoCabecalhoNoXls(HSSFSheet sheet) throws IOException {
        sheet.addMergedRegion(new CellRangeAddress(0, 4, 0, 6));
        new AddDimensionedImageToXLS().addImageToSheet("A1", sheet, sheet.createDrawingPatriarch(),
                DesktopUtil.getUrlDaLogoPetrobras(), 28, 20,
                AddDimensionedImageToXLS.OVERLAY_ROW_AND_COLUMN);

        sheet.addMergedRegion(new CellRangeAddress(0, 4, 7, 7));
        new AddDimensionedImageToXLS().addImageToSheet("H1", sheet, sheet.createDrawingPatriarch(),
                DesktopUtil.getUrlDaLogoNP1(), 7, 17,
                AddDimensionedImageToXLS.OVERLAY_ROW_AND_COLUMN);
    }
}
