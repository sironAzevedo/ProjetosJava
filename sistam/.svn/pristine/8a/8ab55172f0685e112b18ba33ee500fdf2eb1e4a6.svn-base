package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.common.support.Pagination;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.AgenciamentoService;
import br.com.petrobras.sistam.common.business.CertificadoService;
import br.com.petrobras.sistam.common.business.DocumentoService;
import br.com.petrobras.sistam.common.business.EmbarcacaoService;
import br.com.petrobras.sistam.common.business.ManobraService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.AgenciamentoSanitaria;
import br.com.petrobras.sistam.common.entity.AgenciamentoViagem;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import br.com.petrobras.sistam.common.enums.SituacaoLivrePratica;
import br.com.petrobras.sistam.common.enums.SituacaoProcesso;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.FileUtil;
import br.com.petrobras.sistam.common.util.PdfUtil;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.GrupoAgenciaEStatusEmbarcacaoVO;
import br.com.petrobras.sistam.common.valueobjects.AtendimentoVO;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamentoAtendimento;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import br.com.petrobras.sistam.common.valueobjects.GrupoAgenciaEFrotaVO;
import br.com.petrobras.sistam.common.valueobjects.GrupoFrota;
import br.com.petrobras.sistam.common.valueobjects.GrupoStatusAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.RelatorioAgenciamentoAtendimentoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioMovimentacaoEmbarcacaoVO;
import br.com.petrobras.sistam.common.valueobjects.SubrelatorioMovimentacaoEmbarcacaoVO;
import br.com.petrobras.sistam.service.dao.GeradorDeNumeroProcessoAgenciamento;
import br.com.petrobras.sistam.service.query.ConsultaAgenciamentoPorId;
import br.com.petrobras.sistam.service.query.ConsultaAgenciamentoSanitariaPorId;
import br.com.petrobras.sistam.service.query.ConsultaAgenciamentoViagemPorId;
import br.com.petrobras.sistam.service.query.ConsultaAgenciamentosPorFiltroRelatorioTimesheet;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAcompanamentosDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentoComPendenciaParaCaixaDeEntrada;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentoParaCaixaDeEntrada;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentoParaDesvioDeRota;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentosAtendimentos;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentosParaCriacaoDeNovo;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentosParaPainel;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentosPorFiltro;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentosProdutividade;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentoExistenteComEscalaMercante;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentosParaRelatorioMovimentacaoEmbacacao;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultacCancelamentoDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.ExcluirPendenciaPorAgenciamento;
import br.com.petrobras.sistam.service.util.ServiceUtil;
import br.com.petrobras.sistam.service.validator.ValidadorAgenciamento;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AgenciamentoServiceImpl implements AgenciamentoService {

    @Autowired
    private ValidadorAgenciamento validador;
    @Autowired
    private ValidadorPermissao validadorPermissao;
    private GenericDao dao;
    private EmbarcacaoService embarcacaoService;
    private PendenciaService pendenciaAgenciamentoService;
    private AcessoService acessoService;
    private GeradorDeNumeroProcessoAgenciamento geradorProcesso;
    private DocumentoService documentoService;
    private CertificadoService certificadoService;
    private ManobraService manobraService;

    public AgenciamentoServiceImpl(GenericDao dao) {
        this.dao = dao;
    }

    //<editor-fold defaultstate="collapsed" desc="Relatório Timesheet">
    @Override
    @Transactional(readOnly = true)
    public List<Agenciamento> buscarAgenciamentosPorFiltroRelatorioTimesheet(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas) {
        return dao.list(new ConsultaAgenciamentosPorFiltroRelatorioTimesheet(filtro, agenciasAutorizadas));
    } 

    @Override
    @Transactional(readOnly = true)
    public Map<GrupoAgenciaEStatusEmbarcacaoVO, InputStream> gerarArquivosZipComPdfsAgrupadosPorStatusEmbarcacao(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas) {
        Map<GrupoAgenciaEStatusEmbarcacaoVO, List<File>> pdfPorStatus = gerarPdfsAgrupadosPorAgenciaEStatusEmbarcacao(filtro, agenciasAutorizadas);

        final String extensao = ".zip";
        Map<GrupoAgenciaEStatusEmbarcacaoVO, InputStream> zipPorAgenciaEStatus = new HashMap<GrupoAgenciaEStatusEmbarcacaoVO, InputStream>();

        for (Map.Entry<GrupoAgenciaEStatusEmbarcacaoVO, List<File>> pdfStatus : pdfPorStatus.entrySet()) {
            GrupoAgenciaEStatusEmbarcacaoVO key = pdfStatus.getKey();
            List<File> files = pdfStatus.getValue();

            try {
                String nomeArquivoZip = getNomeParaArquivoZipPorAgenciaEStatusEmbarcacao(key);
                File zipFile = File.createTempFile(nomeArquivoZip, ".zip");
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));

                key.setExtensao(extensao);
                key.setNomeArquivo(nomeArquivoZip);

                for (File file : files) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    FileInputStream fileInputStream = new FileInputStream(file);

                    byte[] buffer = new byte[2048];
                    int bytesRead = 0;
                    while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, bytesRead);
                    }
                    fileInputStream.close();
                }
                zipOutputStream.close();

                FileInputStream fin = new FileInputStream(zipFile);
                /*byte[] zipBytes = new byte[(int) zipFile.length()];*/
                //fin.read();
                //fin.close();

                zipPorAgenciaEStatus.put(key, fin);
            } catch (IOException ex) {
                throw new BusinessException(ex.getMessage(), ex);
            }
        }

        return zipPorAgenciaEStatus;
    }      
    
    @Override
    @Transactional(readOnly = true)
    public Map<GrupoAgenciaEFrotaVO, InputStream> gerarArquivosZipComPdfsAgrupadosPorFrota(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas) {
       Map<GrupoAgenciaEFrotaVO, List<File>> pdfPorFrota = gerarPdfsAgrupadosPorAgenciaEFrota(filtro, agenciasAutorizadas);

        final String extensao = ".zip";
        Map<GrupoAgenciaEFrotaVO, InputStream> zipPorAgenciaEFrota= new HashMap<GrupoAgenciaEFrotaVO, InputStream>();

        for (Map.Entry<GrupoAgenciaEFrotaVO, List<File>> pdfStatus : pdfPorFrota.entrySet()) {
            GrupoAgenciaEFrotaVO key = pdfStatus.getKey();
            List<File> files = pdfStatus.getValue();

            try {
                String nomeArquivoZip = getNomeParaArquivoZipPorAgenciaEFrota(key);
                File zipFile = File.createTempFile(nomeArquivoZip, ".zip");
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));

                key.setExtensao(extensao);
                key.setNomeArquivo(nomeArquivoZip);

                for (File file : files) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    FileInputStream fileInputStream = new FileInputStream(file);

                    byte[] buffer = new byte[2048];
                    int bytesRead = 0;
                    while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, bytesRead);
                    }
                    fileInputStream.close();
                }
                zipOutputStream.close();

                FileInputStream fin = new FileInputStream(zipFile);
                /*byte[] zipBytes = new byte[(int) zipFile.length()];*/
                //fin.read();
                //fin.close();

                zipPorAgenciaEFrota.put(key, fin);
            } catch (IOException ex) {
                throw new BusinessException(ex.getMessage(), ex);
            }
        }

        return zipPorAgenciaEFrota;
    }
    
    private Map<GrupoAgenciaEStatusEmbarcacaoVO, List<File>> gerarPdfsAgrupadosPorAgenciaEStatusEmbarcacao(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas) {
        List<Agenciamento> agenciamentos = buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, agenciasAutorizadas);
        List<GrupoStatusAgenciamento> agenciamentosAgrupados = agruparAgenciamentosPorStatusEmbarcacao(new HashSet<Agenciamento>(agenciamentos));

        Map<GrupoAgenciaEStatusEmbarcacaoVO, List<File>> pdfPorStatus = new HashMap<GrupoAgenciaEStatusEmbarcacaoVO, List<File>>();
        for (GrupoStatusAgenciamento agenciamentoAgrupado : agenciamentosAgrupados) {
            StatusEmbarcacao status = agenciamentoAgrupado.getStatus();

            for (Agenciamento agenciamento : agenciamentoAgrupado.getAgenciamento()) {
                List<File> pdfs = new ArrayList<File>();
                Agencia agencia = agenciamento.getAgencia();
                Date geradoEm = new Date();

                GrupoAgenciaEStatusEmbarcacaoVO key = new GrupoAgenciaEStatusEmbarcacaoVO(agencia, status, geradoEm);

                if (pdfPorStatus.containsKey(key)) {
                    pdfs = pdfPorStatus.get(key);
                }

                pdfs.add(criarPdfDaEmbarcacaoEmAgenciamento(agenciamento, geradoEm));
                pdfPorStatus.put(key, pdfs);
            }
        }

        return pdfPorStatus;
    }        
    
    private Map<GrupoAgenciaEFrotaVO, List<File>> gerarPdfsAgrupadosPorAgenciaEFrota(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas) {
        List<Agenciamento> agenciamentos = buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, agenciasAutorizadas);
        List<GrupoFrota> agenciamentosAgrupados = agruparAgenciamentosPorFrota(new HashSet<Agenciamento>(agenciamentos));

        Map<GrupoAgenciaEFrotaVO, List<File>> pdfPorFrota = new HashMap<GrupoAgenciaEFrotaVO, List<File>>();
        for (GrupoFrota agenciamentoAgrupado : agenciamentosAgrupados) {
            TipoFrota frota = agenciamentoAgrupado.getFrota();

            for (Agenciamento agenciamento : agenciamentoAgrupado.getAgenciamento()) {
                List<File> pdfs = new ArrayList<File>();
                Agencia agencia = agenciamento.getAgencia();
                Date geradoEm = new Date();

                GrupoAgenciaEFrotaVO key = new GrupoAgenciaEFrotaVO(agencia, frota, geradoEm);

                if (pdfPorFrota.containsKey(key)) {
                    pdfs = pdfPorFrota.get(key);
                }

                pdfs.add(criarPdfDaEmbarcacaoEmAgenciamento(agenciamento, geradoEm));
                pdfPorFrota.put(key, pdfs);
            }
        }

        return pdfPorFrota;

    }

    private File criarPdfDaEmbarcacaoEmAgenciamento(Agenciamento agenciamento, Date geradoEm) {
        Document document = new Document(PageSize.A4, PdfUtil.MARGIN_LEFT,
                PdfUtil.MARGIN_RIGHT, PdfUtil.MARGIN_TOP, PdfUtil.MARGIN_BOTTOM);

        File file = null;
        try {
            String nomeArquivo = getNomeParaArquivoPdfDoAgenciamento(agenciamento, geradoEm, agenciamento.getAgencia().obterTimezone());
            file = FileUtil.createFileOnTemp(nomeArquivo, ".pdf");

            FileOutputStream fos = new FileOutputStream(file);
            try {
                PdfWriter.getInstance(document, fos);
            } catch (DocumentException ex) {
                throw new BusinessException(ex.getMessage(), ex);
            }

            document.open();

            try {
                document.add(adicionarLogoPetrobrasENP1());

                document.add(criarTabelaComAgenciaEPorto(agenciamento.getAgencia(), agenciamento.getPorto()));

                document.add(criarTabelaCabecalho(agenciamento));

                document.add(criarTabelaOcorrencias(agenciamento));

                document.add(criarTabelaAcompanhamentos(agenciamento));
            } catch (DocumentException ex) {
                throw new BusinessException(ex.getMessage(), ex);
            }

            document.close();
            fos.close();
        } catch (IOException ex) {
            throw new BusinessException(ex.getMessage(), ex);
        }

        return file;
    }

    private PdfPTable adicionarLogoPetrobrasENP1() {
        PdfPTable table = criarEstruturaPadraoTabela(2, PdfUtil.SPACING_AFTER_20, new int[]{1, 1});
        try {
            Image image = Image.getInstance(ServiceUtil.getUrlDaLogoPetrobras());
            image.scalePercent(100);
            PdfPCell cell = new PdfPCell(image);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            image = Image.getInstance(ServiceUtil.getUrlDaLogoNP1());
            image.scalePercent(80);
            cell = new PdfPCell(image);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage(), ex);
        }
        return table;
    }

    private PdfPTable criarTabelaComAgenciaEPorto(Agencia agencia, Porto porto) {
        PdfPTable table = criarEstruturaPadraoTabela(2, PdfUtil.SPACING_AFTER_20, new int[]{1, 1});
        table.addCell(criarCelulaComLabelBoldETextoNormal("AGÊNCIA: ", agencia.getNome()));
        table.addCell(criarCelulaComLabelBoldETextoNormal("PORTO: ", porto.getNomeCompleto()));
        return table;
    }

    private PdfPTable criarTabelaCabecalho(Agenciamento agenciamento) {
        PdfPTable table = criarEstruturaPadraoTabela(2, PdfUtil.SPACING_AFTER_20, new int[]{2, 3});

        table.addCell(criarCelulaLabelADireita("Processo SISTAM"));
        table.addCell(criarCelulaTexto(StringUtils.isBlank(agenciamento.getNumeroProcessoformatado()) ? "-" : agenciamento.getNumeroProcessoformatado()));

        table.addCell(criarCelulaLabelADireita("EMBARCAÇÃO"));
        table.addCell(criarCelulaTexto(agenciamento.getEmbarcacao().getNomeCompleto()));

        String vgmChegada = agenciamento.getVgm() != null ? agenciamento.getVgm() : "";
        String vgmSaida = agenciamento.getVgmSaida() != null ? " / " + agenciamento.getVgmSaida() : "";
        String vgm;
        if (!agenciamento.getVgm().equals(agenciamento.getVgmSaida())) {
            vgm = vgmChegada.concat(vgmSaida);
        } else {
            vgm = vgmChegada;
        }

        table.addCell(criarCelulaLabelADireita("Viagem"));
        table.addCell(criarCelulaTexto(vgm));

        table.addCell(criarCelulaLabelADireita("Porto Origem"));
        Porto portoOrigem = agenciamento.getPortoOrigem();
        table.addCell(criarCelulaTexto(portoOrigem == null ? "-" : portoOrigem.getNomeCompleto()));

        table.addCell(criarCelulaLabelADireita("Porto Destino"));
        Porto portoDestino = agenciamento.getPortoDestino();
        table.addCell(criarCelulaTexto(portoDestino == null ? "-" : portoDestino.getNomeCompleto()));

        table.addCell(criarCelulaLabelADireita("Situação"));
        table.addCell(criarCelulaTexto(agenciamento.getStatusEmbarcacao().getPorExtenso()));
        
        table.addCell(criarCelulaLabelADireita("Frota"));
        table.addCell(criarCelulaTexto(agenciamento.getTipoFrota().getPorExtenso())); 

        table.addCell(criarCelulaLabelADireita("Localização"));
        PontoAtracacao localizacaoAtual = agenciamento.obterLocalizacaoAtual();
        table.addCell(criarCelulaTexto(localizacaoAtual == null ? "-" : localizacaoAtual.getNome()));

        table.addCell(criarCelulaLabelADireita("Calado de Chegada"));

        String avanteChegada = String.valueOf(agenciamento.getCaladoChegadaVante() == null ? "-" : agenciamento.getCaladoChegadaVante());
        String areChegada = String.valueOf(agenciamento.getCaladoChegadaRe() == null ? "-" : agenciamento.getCaladoChegadaRe());
        table.addCell(criarCelulaTexto("A Vante: ".concat(avanteChegada).concat("                              A Ré: ").concat(areChegada)));

        table.addCell(criarCelulaLabelADireita("Calado de Saída"));
        String avanteSaida = String.valueOf(agenciamento.getCaladoSaidaVante() == null ? "-" : agenciamento.getCaladoSaidaVante());
        String areSaida = String.valueOf(agenciamento.getCaladoSaidaRe() == null ? "-" : agenciamento.getCaladoSaidaRe());
        table.addCell(criarCelulaTexto("A Vante: ".concat(avanteSaida).concat("                              A Ré: ").concat(areSaida)));

        table.addCell(criarCelulaLabelADireita("Obs. Painel"));
        table.addCell(criarCelulaTexto(StringUtils.isBlank(agenciamento.getObservacoes()) ? "-" : agenciamento.getObservacoes()));
        return table;
    }

    private PdfPTable criarTabelaOcorrencias(Agenciamento agenciamento) {
        TimeZone timeZone = agenciamento.getAgencia().obterTimezone();
        PdfPTable table = criarEstruturaPadraoTabela(4, PdfUtil.SPACING_AFTER_20, new int[]{1, 1, 1, 2});

        table.addCell(criarCelulaLabel("OCORRÊNCIA", Element.ALIGN_CENTER, 2, null));
        table.addCell(criarCelulaLabel("DATA/HORA", Element.ALIGN_CENTER, null, null));
        table.addCell(criarCelulaLabel("EVENTO", Element.ALIGN_CENTER, null, null));

        table.addCell(criarCelulaLabel("Livre Prática", null, 2, 2));
        List<Documento> docsAnvisa = documentoService.buscarDocumentoDoAgenciamentoPorTipo(TipoDocumento.SOLICITACAO_CERTIFICADO, agenciamento);
        table.addCell(criarCelulaTexto(docsAnvisa.isEmpty() ? "-" : safeDataHoraRelatorio(docsAnvisa.get(0).getDataProtocolo(), timeZone)));
        table.addCell(criarCelulaTexto("Protocolo ANVISA"));

        Certificado certificado = certificadoService.buscarCertificadoValidoPorTipo(TipoCertificado.CLP, agenciamento, null, agenciamento.getDataEstimadaSaida());
        table.addCell(criarCelulaTexto(certificado == null ? "-" : safeDataHoraRelatorio(certificado.getData(), timeZone)));
        table.addCell(criarCelulaTexto("Emissão Certificado"));

        table.addCell(criarCelulaLabel("No Porto", null, 2, null));
        table.addCell(criarCelulaTexto(safeDataHoraRelatorio(agenciamento.getDataChegada(), null)));
        table.addCell(criarCelulaTexto("Hora Oficial de Chegada"));

        List<Manobra> manobras = manobraService.buscarManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento(agenciamento);
        adicionarManobrasNaTabelaOcorrencias(manobras, table, timeZone);

        adicionarOperacoesNaTabelaOcorrencias(agenciamento.getOperacoes(), table, timeZone);

        table.addCell(criarCelulaLabel("Saída", null, 2, 3));
        table.addCell(criarCelulaTexto(safeDataHoraRelatorio(agenciamento.getDataEstimadaSaida(), null)));
        table.addCell(criarCelulaTexto("ETS"));
        table.addCell(criarCelulaTexto(safeDataHoraRelatorio(agenciamento.getDataSaida(), null)));
        table.addCell(criarCelulaTexto("Oficial de Saída"));
        table.addCell(criarCelulaTexto(safeDataHoraRelatorio(agenciamento.getEtaProximoPorto(), null)));
        table.addCell(criarCelulaTexto("ETA Próximo Porto"));
        return table;
    }

    private void adicionarManobrasNaTabelaOcorrencias(List<Manobra> manobras, PdfPTable table, TimeZone timeZone) {
        boolean impresso = false;
        for (Manobra manobra : manobras) {
            if (!impresso) {
                table.addCell(criarCelulaLabel("Manobras", null, null, manobras.size() * 4));
                impresso = true;
            }
            table.addCell(criarCelulaTexto(manobra.getFinalidadeManobra().getPorExtenso(), null, null, 4));
            table.addCell(criarCelulaTexto(safeDataHoraRelatorio(manobra.getDataPartidaOrigem(), timeZone)));
            table.addCell(criarCelulaTexto("Data Partida (Ponto Inicial)"));
            table.addCell(criarCelulaTexto(safeDataHoraRelatorio(manobra.getDataChegadaDestino(), timeZone)));
            table.addCell(criarCelulaTexto("Data Chegada (Ponto Final)"));
            table.addCell(criarCelulaTexto(safeDataHoraRelatorio(manobra.getDataInicio(), timeZone)));
            table.addCell(criarCelulaTexto("Data Início Manobra"));
            table.addCell(criarCelulaTexto(safeDataHoraRelatorio(manobra.getDataTermino(), timeZone)));
            table.addCell(criarCelulaTexto("Data Término Manobra"));
        }
    }

    private void adicionarOperacoesNaTabelaOcorrencias(List<Operacao> operacoes, PdfPTable table, TimeZone timeZone) {
        boolean impresso = false;
        for (Operacao operacao : operacoes) {
            if (!impresso) {
                table.addCell(criarCelulaLabel("Operações", null, null, operacoes.size() * 2));
                impresso = true;
            }
            table.addCell(criarCelulaTexto(operacao.getTipoOperacao().getPorExtenso().toUpperCase(), null, null, 2));
            table.addCell(criarCelulaTexto(safeDataHoraRelatorio(operacao.getDataInicio(), timeZone)));
            table.addCell(criarCelulaTexto("Data Início"));
            table.addCell(criarCelulaTexto(safeDataHoraRelatorio(operacao.getDataFim(), timeZone)));
            table.addCell(criarCelulaTexto("Data Fim"));
        }
    }

    private PdfPTable criarTabelaAcompanhamentos(Agenciamento agenciamento) {
        List<Acompanhamento> acompanhamentos = buscarAcompanhamentos(agenciamento);
        TimeZone timeZone = agenciamento.getAgencia().obterTimezone();

        PdfPTable table = criarEstruturaPadraoTabela(3, PdfUtil.SPACING_AFTER_0, new int[]{1, 1, 3});
        table.addCell(criarCelulaLabel("ACOMPANHAMENTOS", Element.ALIGN_CENTER, 3, null));
        table.addCell(criarCelulaLabel("Data", Element.ALIGN_CENTER, null, null));
        table.addCell(criarCelulaLabel("Cadastrador", Element.ALIGN_CENTER, null, null));
        table.addCell(criarCelulaLabel("Texto", Element.ALIGN_CENTER, null, null));

        for (Acompanhamento acompanhamento : acompanhamentos) {
            table.addCell(criarCelulaTexto(safeDataHoraRelatorio(acompanhamento.getData(), timeZone)));
            table.addCell(criarCelulaTexto(acompanhamento.getChaveCadastrador()));
            table.addCell(criarCelulaTexto(acompanhamento.getTexto()));
        }
        return table;
    }

    private String safeDataHoraRelatorio(Date data, TimeZone zone) {
        return data == null ? "-" : SistamDateUtils.formatDate(data, SistamDateUtils.DATE_HOUR_PATTERN, zone);
    }

    private String getNomeParaArquivoPdfDoAgenciamento(Agenciamento agenciamento, Date geradoEm, TimeZone timeZone) {
        final String DATE_HOUR_PATTERN = "ddMMyyyy-KKmm";

        StringBuilder nome = new StringBuilder();
        nome.append("Timesheet-")
                .append(agenciamento.getPorto().getNomeCompleto()).append("-")
                .append(agenciamento.getEmbarcacao().getNomeCompleto()).append("-")
                .append(agenciamento.getVgm()).append("-")
                .append(SistamDateUtils.formatDate(geradoEm, DATE_HOUR_PATTERN, timeZone));
        return FileUtil.unaccent(nome.toString());
    }

    private String getNomeParaArquivoZipPorAgenciaEStatusEmbarcacao(GrupoAgenciaEStatusEmbarcacaoVO grupo) {
        final String DATE_HOUR_PATTERN = "ddMMyyyy-KKmm";
        final Agencia agencia = grupo.getAgencia();

        StringBuilder nome = new StringBuilder();
        nome.append(agencia.getNome()).append("-")
                .append(grupo.getStatusEmbarcacao().getPorExtenso()).append("-")
                .append(SistamDateUtils.formatDate(grupo.getGeradoEm(), DATE_HOUR_PATTERN, agencia.obterTimezone()));
        return FileUtil.unaccent(nome.toString());
    }
    
    private String getNomeParaArquivoZipPorAgenciaEFrota(GrupoAgenciaEFrotaVO grupo) {
        final String DATE_HOUR_PATTERN = "ddMMyyyy-KKmm";
        final Agencia agencia = grupo.getAgencia();

        StringBuilder nome = new StringBuilder();
        nome.append(agencia.getNome()).append("-")
                .append(grupo.getTipoFrota().getPorExtenso()).append("-")
                .append(SistamDateUtils.formatDate(grupo.getGeradoEm(), DATE_HOUR_PATTERN, agencia.obterTimezone()));
        return FileUtil.unaccent(nome.toString());
    }

    private PdfPCell criarCelulaLabelADireita(String label) {
        return criarCelulaLabel(label, Element.ALIGN_RIGHT, null, null);
    }

    private PdfPCell criarCelulaLabel(String label, Integer horizontalAlignment, Integer colSpan, Integer rowSpan) {
        PdfPCell cell = new PdfPCell(new Phrase(label, PdfUtil.FONT_TIMES_11_BOLD));
        if (horizontalAlignment == null) {
            horizontalAlignment = Element.ALIGN_LEFT;
        }
        if (colSpan != null) {
            cell.setColspan(colSpan);
        }
        if (rowSpan != null) {
            cell.setRowspan(rowSpan);
        }
        cell.setBackgroundColor(PdfUtil.COLOR_BLUE);
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(PdfUtil.CELL_PADDING);
        return cell;
    }

    private PdfPCell criarCelulaTexto(String texto) {
        return criarCelulaTexto(texto, null, null, null);
    }

    private PdfPCell criarCelulaTexto(String texto, Integer horizontalAlignment, Integer colSpan, Integer rowSpan) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, PdfUtil.FONT_TIMES_11_NORMAL));
        if (horizontalAlignment == null) {
            horizontalAlignment = Element.ALIGN_LEFT;
        }
        if (colSpan != null) {
            cell.setColspan(colSpan);
        }
        if (rowSpan != null) {
            cell.setRowspan(rowSpan);
        }
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(PdfUtil.CELL_PADDING);
        return cell;
    }

    private PdfPCell criarCelulaComLabelBoldETextoNormal(String label, String texto) {
        Phrase phrase = new Phrase();
        phrase.setFont(PdfUtil.FONT_TIMES_11_NORMAL);
        phrase.add(new Phrase(label, PdfUtil.FONT_TIMES_11_BOLD));
        phrase.add(texto);

        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(PdfUtil.CELL_PADDING);
        return cell;
    }

    private PdfPTable criarEstruturaPadraoTabela(int numColumns, int spacingAfter, int[] widths) {
        PdfPTable table = new PdfPTable(numColumns);
        table.setWidthPercentage(100);
        table.setSpacingAfter(spacingAfter);
        try {
            table.setWidths(widths);
        } catch (DocumentException ex) {
            throw new BusinessException(ex.getMessage(), ex);
        }
        return table;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setPendenciaAgenciamentoService(PendenciaService pendenciaAgenciamentoService) {
        this.pendenciaAgenciamentoService = pendenciaAgenciamentoService;
    }

    public void setManobraService(ManobraService manobraService) {
        this.manobraService = manobraService;
    }

    public void setDocumentoService(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    public void setCertificadoService(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    public void setEmbarcacaoService(EmbarcacaoService embarcacaoService) {
        this.embarcacaoService = embarcacaoService;
    }

    public void setGeradorProcesso(GeradorDeNumeroProcessoAgenciamento geradorProcesso) {
        this.geradorProcesso = geradorProcesso;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Busca de agenciamentos">
    @Override
    @Transactional(readOnly = true)
    public Agenciamento buscarAgenciamentoPorId(Long id) {
        return dao.uniqueResult(new ConsultaAgenciamentoPorId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrupoStatusAgenciamento> buscarAgenciamentosParaCaixaDeEntrada(Agencia agencia, Porto porto, Date dataDeCorte) {

        //Busca os agenciamentos.
        Set<Agenciamento> agenciamentos = new HashSet<Agenciamento>(dao.list(new ConsultaAgenciamentoParaCaixaDeEntrada(agencia, porto, dataDeCorte)));
        agenciamentos.addAll(dao.list(new ConsultaAgenciamentoComPendenciaParaCaixaDeEntrada(agencia, porto)));
        List<GrupoStatusAgenciamento> agenciamentoPorStatus = agruparAgenciamentosPorStatusEmbarcacao(agenciamentos);

        Collections.sort(agenciamentoPorStatus, new Comparator<GrupoStatusAgenciamento>() {
            @Override
            public int compare(GrupoStatusAgenciamento g1, GrupoStatusAgenciamento g2) {
                if (g1.equals(g2)) {
                    return 0;
                }
                if (g1.getStatus().getOrdem() < g2.getStatus().getOrdem()) {
                    return -1;
                }
                return 1;
            }
        });

        return agenciamentoPorStatus;
    }

    @Override
    @Transactional(readOnly = true)
    public AgenciamentoViagem buscarAgenciamentoViagemPorId(Long id) {
        return dao.uniqueResult(new ConsultaAgenciamentoViagemPorId(id));

    }

    @Override
    @Transactional(readOnly = true)
    public AgenciamentoSanitaria buscarAgenciamentoSanitariaPorId(Long id) {
        return dao.uniqueResult(new ConsultaAgenciamentoSanitariaPorId(id));

    }

    @Transactional(readOnly = true)
    @Override
    public Agenciamento buscarAgenciamentoParaDesvioDeRota(Long idAgenciamento) {
        return dao.uniqueResult(new ConsultaAgenciamentoParaDesvioDeRota(idAgenciamento));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Agenciamento> buscarAgenciamentosPorFiltro(FiltroAgenciamento filtro) {
        validador.validarBuscarAgenciamentosPorFiltro(filtro);
        ConsultaAgenciamentosPorFiltro consulta = new ConsultaAgenciamentosPorFiltro(filtro);
        Pagination pagination = new Pagination(Agenciamento.PROP_LIMITE_CONSULTA_REGISTROS);
        consulta.setPagination(pagination);
        return dao.list(consulta);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Agenciamento> buscarAgenciamentosRelatorioProdutividade(FiltroAgenciamento filtro) {
        validador.validarBuscarParaRelatorioDeProdutividade(filtro);
        return dao.list(new ConsultaAgenciamentosProdutividade(filtro));
    } 

    @Transactional(readOnly = true)
    @Override
    public List<Agenciamento> buscarAgenciamentosParaPainel(Agencia agencia, List<StatusEmbarcacao> listaStatus) {
        return dao.list(new ConsultaAgenciamentosParaPainel(agencia, listaStatus));
    }

    @Transactional(readOnly = true)
    @Override
    public RelatorioMovimentacaoEmbarcacaoVO buscarDadosRelatorioMovimentacaoEmbarcacao(Agencia agencia, Porto porto) {
        RelatorioMovimentacaoEmbarcacaoVO voRelatorio = new RelatorioMovimentacaoEmbarcacaoVO();
        List<StatusEmbarcacao> status = StatusEmbarcacao.listValues();
        List<Agenciamento> agenciamentos = dao.list(new ConsultaAgenciamentosParaRelatorioMovimentacaoEmbacacao(agencia, status, porto));
        AssertUtils.assertExpression(!agenciamentos.isEmpty(), ConstantesI18N.RELATORIO_MANOBRA_NAO_EXISTE_DADOS);

        for (Agenciamento agenciamento : agenciamentos) {

            String vgmChegada = agenciamento.getVgm() != null ? agenciamento.getVgm() : "";
            String vgmSaida = agenciamento.getVgmSaida() != null ? " / " + agenciamento.getVgmSaida() : "";
            String viagem;
            if (!agenciamento.getVgm().equals(agenciamento.getVgmSaida())) {
                viagem = vgmChegada.concat(vgmSaida);
            } else {
                viagem = vgmChegada;
            }

            SubrelatorioMovimentacaoEmbarcacaoVO voSubrelatorio = new SubrelatorioMovimentacaoEmbarcacaoVO();
            voSubrelatorio.setStatusEmbarcacao(agenciamento.getStatusEmbarcacao());
            voSubrelatorio.setNavio(agenciamento.getEmbarcacao().getNomeCompleto());
            voSubrelatorio.setVgm(viagem);
            voSubrelatorio.setObservacoes(agenciamento.getObservacoes());
            voSubrelatorio.setEta(SistamDateUtils.formatDate(agenciamento.getEta(), "dd/MM HH:mm", agencia.obterTimezone()));
            voSubrelatorio.setEtaProxPorto(SistamDateUtils.formatDate(agenciamento.getEtaProximoPorto(), "dd/MM HH:mm", agencia.obterTimezone()));
            voSubrelatorio.setDataEta(agenciamento.getEta());
            voSubrelatorio.setDataEtaProxPorto(agenciamento.getEtaProximoPorto());
            voSubrelatorio.setOrigem(agenciamento.getPortoOrigem().toString());
            voSubrelatorio.setQuantidade(agenciamento.getQuantidadesProdutosFormatadas());
            voSubrelatorio.setOperacao(agenciamento.getOperacoesFormatadas());

            voSubrelatorio.setDataAtracacao(agenciamento.getDataHoraAtracacao());
            voSubrelatorio.setDataChegada(agenciamento.getDataChegada());
            voSubrelatorio.setDataSaida(agenciamento.getDataSaida());

            voSubrelatorio.setAtracacao(SistamDateUtils.formatDate(agenciamento.getDataHoraAtracacao(), "dd/MM HH:mm", agencia.obterTimezone()));
            voSubrelatorio.setChegada(SistamDateUtils.formatDate(agenciamento.getDataChegada(), "dd/MM HH:mm", agencia.obterTimezone()));
            voSubrelatorio.setSaida(SistamDateUtils.formatDate(agenciamento.getDataSaida(), "dd/MM HH:mm", agencia.obterTimezone()));

            Porto destino = agenciamento.getPortoDestino();
            voSubrelatorio.setDestino(destino != null ? destino.toString() : null);

            SituacaoLivrePratica situacaoLivrePratica = agenciamento.getSituacaoLivrePratica();
            voSubrelatorio.setLp(situacaoLivrePratica != null ? situacaoLivrePratica.getId() : null);

            PontoAtracacao pontoAtracacao = agenciamento.obterLocalizacaoAtual();
            voSubrelatorio.setLocalizacaoAtual(pontoAtracacao != null ? pontoAtracacao.getSiglaFormatada() : null);

            // porto do desvio            
            if (null != agenciamento.getDesvio()) {
                Porto destinoAnterior = agenciamento.getDesvio().getPortoDestinoAlterado();
                voSubrelatorio.setDestinoAnterior(destinoAnterior != null ? destinoAnterior.toString() : null);
            }
            //dados do cancelamento
            if (null != agenciamento.getCancelamento()) {
                voSubrelatorio.setCancelamento(agenciamento.getCancelamento().getData());
                voSubrelatorio.setDataCancelamento(SistamDateUtils.formatDate(agenciamento.getCancelamento().getData(), "dd/MM HH:mm", agencia.obterTimezone()));
                voSubrelatorio.setMotivoCancelamento(agenciamento.getCancelamento().getMotivo().getPorExtenso());
            }
            voRelatorio.adicionarSubrelatorioVO(voSubrelatorio);
        }
        voRelatorio.ordenarListas();
        return voRelatorio;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Criar novo agenciamento">
    @Transactional(readOnly = false)
    @Override
    public Agenciamento criarNovoAgenciamento(Agenciamento agenciamento, Integer ano) {
        //Faz as validaÃ§Ãµes necessÃ¡rias
        AssertUtils.assertNotNull(agenciamento.getAgencia(), ConstantesI18N.AGENCIAMENTO_INFORME_AGENCIA);
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgenciamento(agenciamento.getAgencia()), ConstantesI18N.AGENCIAMENTO_SEM_PERMISSAO_SALVAR);
        validador.validarIniciarAgenciamento(agenciamento);

        //preenche com as informaÃ§Ãµes iniciais
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setSituacaoProcesso(SituacaoProcesso.EM_ANDAMENTO);

        List<Escala> escalas = embarcacaoService.buscarEscalaPorEmbarcacao(agenciamento.getEmbarcacao(), agenciamento.getEta());
        agenciamento.setEscalas(Escala.formatarEscalas(escalas, agenciamento.getAgencia().obterTimezone()));

        //Gerando nÃºmero para o processo
        Long numero = geradorProcesso.gerarNumero(agenciamento.getAgencia(), ano);
        agenciamento.setCodigo(numero);
        agenciamento.setAnoProcesso(ano);

        //Atualiza com o usuÃ¡rio logado
        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        agenciamento.setChaveCadastrador(credentialsBean.getLogon());
        agenciamento.setNomeCadastrador(credentialsBean.getUsername());
        agenciamento.setDataInclusao(new Date());

        dao.saveOrUpdate(agenciamento);

        //cria o agenciamento viagem e sanitÃ¡rio
        agenciamento.getAgenciementoViagem().setId(agenciamento.getId());
        agenciamento.getAgenciementoSanitaria().setId(agenciamento.getId());
        dao.saveOrUpdate(agenciamento.getAgenciementoViagem());
        dao.saveOrUpdate(agenciamento.getAgenciementoSanitaria());

        if (agenciamento.getTipoContrato().equals(TipoContrato.TCP)) {
            gerarPendenciasDeNovoAgenciamento(agenciamento);
        }

        return agenciamento;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean verificarSeAgenciamentoJaFoiCriado(Agenciamento novoAgenciamento) {
        AssertUtils.assertNotNull(novoAgenciamento.getAgencia(), ConstantesI18N.AGENCIAMENTO_INFORME_AGENCIA);
        validador.validarIniciarAgenciamento(novoAgenciamento);

        FiltroAgenciamento filtro = new FiltroAgenciamento();
        TimeZone zone = novoAgenciamento.getAgencia().obterTimezone();

        filtro.setAgencia(novoAgenciamento.getAgencia());
        filtro.setPorto(novoAgenciamento.getPorto());
        filtro.setEmbarcacao(novoAgenciamento.getEmbarcacao());
        filtro.setEtaInicial(DateBuilder.on(novoAgenciamento.getEta()).at(0, 0, 0).getTime(zone));
        filtro.setEtaFinal(DateBuilder.on(novoAgenciamento.getEta()).at(23, 59, 59).getTime(zone));

        //Se retornar valor de acordo com os filtros informados, entÃ£o jÃ¡ existe agenciamento.
        if (!dao.list(new ConsultaAgenciamentosParaCriacaoDeNovo(filtro)).isEmpty()) {
            return true;
        }

        //caso contrÃ¡rio, nÃ£o foi criado o agenciamento com as filtros informados.
        return false;
    }

    private void gerarPendenciasDeNovoAgenciamento(Agenciamento agenciamento) {
        agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.PAGAMENTO_LP));
        agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.DESPACHO_SAIDA));

        if (!"019".equals(agenciamento.getPortoOrigem().getPais().getId())) {
            agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.PAGAMENTO_FUNAPOL));
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Salvar um agenciamento jÃ¡ criado">
    @Transactional(readOnly = false)
    @Override
    public Agenciamento salvarAgenciamento(Agenciamento agenciamento) {
        //Faz as validaÃ§Ãµes necessÃ¡rias
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgenciamento(agenciamento.getAgencia()), ConstantesI18N.AGENCIAMENTO_SEM_PERMISSAO_SALVAR);
        validador.validarSalvarAgenciamento(agenciamento);

        StatusEmbarcacao novoStatus = null;

        //Se informou a a Escala(Mercante), verifica se ainda nÃ£o foi informada em outro agenciamento.
        if (agenciamento.getNumeroEscalaMercante() != null) {
            Agenciamento agm = dao.uniqueResult(new ConsultaAgenciamentoExistenteComEscalaMercante(agenciamento.getNumeroEscalaMercante(), agenciamento));
            if (agm != null) {
                StringBuilder detalhes = new StringBuilder();
                detalhes.append(agm.getNumeroProcessoformatado()).append(" - ")
                        .append(agm.getEmbarcacao().getNomeCompleto()).append(" - ")
                        .append(agm.getVgm());
                throw new BusinessException(ConstantesI18N.AGENCIAMENTO_ESCALA_MARCANTE_JA_EXISTENTE, detalhes.toString());
            }
        }

        //Altera os status e gera as pendÃªncias
        if (StatusEmbarcacao.ESPERADO.equals(agenciamento.getStatusEmbarcacao()) && agenciamento.getDataChegada() != null) {
            agenciamento.setStatusEmbarcacao(StatusEmbarcacao.NO_PORTO);
            novoStatus = StatusEmbarcacao.NO_PORTO;
        }

        if (agenciamento.getDataSaida() != null && !StatusEmbarcacao.SAIDO.equals(agenciamento.getStatusEmbarcacao()) && !StatusEmbarcacao.DESVIADO.equals(agenciamento.getStatusEmbarcacao())) {
            validador.validarSaidaEmbarcacaoAgenciada(agenciamento);
            agenciamento.setStatusEmbarcacao(StatusEmbarcacao.SAIDO);
            novoStatus = StatusEmbarcacao.SAIDO;
        }

        dao.saveOrUpdate(agenciamento);
        dao.saveOrUpdate(agenciamento.getAgenciementoViagem());
        dao.saveOrUpdate(agenciamento.getAgenciementoSanitaria());

        gerarPendenciasDoAgenciamento(agenciamento, novoStatus);
        return agenciamento;
    }

    private void gerarPendenciasDoAgenciamento(Agenciamento agenciamento, StatusEmbarcacao novoStatus) {
        if (TipoContrato.TCP.equals(agenciamento.getTipoContrato())) {

            if (StatusEmbarcacao.NO_PORTO.equals(novoStatus)) {
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.PARTE_ENTRADA));
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.PAGAMENTO_TUF));
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.COMUNICACAO_CHEGADA));
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA));
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.LISTA_TRIPULANTES_PASSAGEIROS));
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.LISTA_PERTENCES_TRIPULANTES_PASSAGEIROS));
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.LISTA_ULTIMOS_PORTOS));
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.LISTA_PROVISOES_BORDO));
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.PLANO_CARGA));
            }

            if (StatusEmbarcacao.SAIDO.equals(novoStatus)) {
                agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.PARTE_SAIDA));

                if (agenciamento.possuiOperacaoCarga()) {
                    agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_CARGA_CABOTAGEM));
                }
            }

            if (agenciamento.possuiOperacaoDescarga()) {
                //Pendencia pendencia = pendenciaAgenciamentoService.buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_DESGARGA_CABOTAGEM);

                boolean resolvida = false;
                int qtdPendencia = 0;
                
                List<Pendencia> listaPendenciaDescarga = pendenciaAgenciamentoService.buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_DESGARGA_CABOTAGEM);

                for (Pendencia pendencia : listaPendenciaDescarga){
                    qtdPendencia++;
                    if (pendencia.isResolvida()) {
                        resolvida = true;
                        break;
                    }
                }
                
                if (qtdPendencia == 0 && !resolvida) {
                    agenciamento.adicionarPendencia(pendenciaAgenciamentoService.criarPendencia(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_DESGARGA_CABOTAGEM));
                }
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Desvio do Agenciamento">
    @Transactional(readOnly = false)
    @Override
    public void salvarDesvio(Desvio desvio, Porto novoPorto, String novoDestinoIntermediario) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgenciamento(desvio.getAgenciamento().getAgencia()), ConstantesI18N.AGENCIAMENTO_SEM_PERMISSAO_SALVAR);
        validador.validarDesvio(desvio, novoPorto);
        if (desvio.getId() == null) {
            desvio.setId(desvio.getAgenciamento().getId());
        }
        desvio.setPortoDestinoAlterado(desvio.getAgenciamento().getPortoDestino());
        desvio.setDestinoIntermediarioAlterado(desvio.getAgenciamento().getDestinoIntermediario());

        desvio.getAgenciamento().setPortoDestino(novoPorto);
        desvio.getAgenciamento().setDestinoIntermediario(novoDestinoIntermediario);
        desvio.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.DESVIADO);

        //Guarda o usuÃ¡rio logado que realizou o desvio.
        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        desvio.setChaveUsuario(credentialsBean.getLogon());
        desvio.setNomeUsuario(credentialsBean.getUsername());

        if (TipoContrato.TCP.equals(desvio.getAgenciamento().getTipoContrato())) {
            pendenciaAgenciamentoService.criarPendencia(desvio.getAgenciamento(), TipoPendencia.DESVIO_ROTA);
        }

        dao.saveOrUpdate(desvio);
        dao.saveOrUpdate(desvio.getAgenciamento());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cancelamento do Agenciamento">
    @Transactional(readOnly = true)
    @Override
    public CancelamentoAgenciamento buscarCancelamentoDoAgenciamento(Long idAgenciamento) {
        return dao.uniqueResult(new ConsultacCancelamentoDoAgenciamento(idAgenciamento));
    }

    @Transactional(readOnly = false)
    @Override
    public CancelamentoAgenciamento cancelarAgenciamento(CancelamentoAgenciamento cancelamento) {
        AssertUtils.assertExpression(validadorPermissao.podeCancelarAgenciamento(cancelamento.getAgenciamento().getAgencia()), ConstantesI18N.AGENCIAMENTO_SEM_PERMISSAO_SALVAR);
        boolean cancelaAgenciamentoAdm = acessoService.validarPermissao(cancelamento.getAgenciamento().getAgencia(), RecursoCA.CANCELAR_AGENCIAMENTO_ADM);
        validador.validarCancelarAgenciamento(cancelamento, cancelaAgenciamentoAdm);

        //Atualiza o agenciamento
        cancelamento.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.CANCELADO);

        //Preenche as informaÃ§Ãµes necessÃ¡rias do cancelamento
        cancelamento.setId(cancelamento.getAgenciamento().getId());
        cancelamento.setData(new Date());

        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        cancelamento.setChaveUsuario(credentialsBean.getLogon());
        cancelamento.setNomeUsuario(credentialsBean.getUsername());

        dao.saveOrUpdate(cancelamento.getAgenciamento());
        dao.saveOrUpdate(cancelamento);
        dao.executeDML(new ExcluirPendenciaPorAgenciamento(cancelamento.getAgenciamento()));

        return cancelamento;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Acompanhamento do Agenciamento"> 
    @Transactional(readOnly = false)
    @Override
    public Acompanhamento salvarAcompanhamento(Acompanhamento acompanhamento) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAcompanhamento(acompanhamento.getAgenciamento().getAgencia()), ConstantesI18N.AGENCIAMENTO_SEM_PERMISSAO_SALVAR);
        validador.validarAcompanhamento(acompanhamento);

        //Guarda o usuÃ¡rio logado que realizou o desvio.
        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        acompanhamento.setChaveCadastrador(credentialsBean.getLogon());
        acompanhamento.setNomeCadastrador(credentialsBean.getUsername());

        dao.saveOrUpdate(acompanhamento);
        return acompanhamento;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Acompanhamento> buscarAcompanhamentos(Agenciamento agenciamento) {
        return dao.list(new ConsultaAcompanamentosDoAgenciamento(agenciamento));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Atendimento">
    @Transactional(readOnly = true)
    @Override
    public List<RelatorioAgenciamentoAtendimentoVO> buscarAgenciamentosRelatorioAtendimento(FiltroAgenciamentoAtendimento filtro) {
        validador.validarBuscarRelatorioAtendimentos(filtro);

        List<RelatorioAgenciamentoAtendimentoVO> vos = new ArrayList<RelatorioAgenciamentoAtendimentoVO>();
        List<Agenciamento> agenciamentos = dao.list(new ConsultaAgenciamentosAtendimentos(filtro));

        for (Agenciamento agenciamento : agenciamentos) {
            List<AtendimentoVO> atendimentos = buscarAtendimentos(agenciamento, filtro.getQtdeDiasAtendimento(), filtro.getDataFinal());
            Map<String, List<AtendimentoVO>> mapPorMes = buscarAtendimentosPorMes(atendimentos);

            Integer mes = filtro.getMesInicial();
            Integer ano = filtro.getAnoInicial();
            Long anoMes = Long.valueOf("" + ano + "" + String.format("%02d", mes));
            Long anoMesFinal = Long.valueOf("" + filtro.getAnoFinal() + "" + String.format("%02d", filtro.getMesFinal())); 
            
            while (anoMes <= anoMesFinal) {
                String mesAno = String.format("%02d", mes) + "/" + ano;  
                List<AtendimentoVO> atendimentosMes = mapPorMes.get(mesAno); 
               

                if (atendimentosMes != null && !atendimentosMes.isEmpty()) {

                    RelatorioAgenciamentoAtendimentoVO vo = new RelatorioAgenciamentoAtendimentoVO();
                    vo.setAnoMes(anoMes);
                    vo.setDataReferencia(DateBuilder.on(1, mes, ano).getTime());
                    vo.setAgenciamento(agenciamento);

                    Integer qtdAtendimentos = 0;
                    Integer qtdDias = 0;
                    for (AtendimentoVO atendimento : atendimentosMes) {
                        qtdAtendimentos++;
                        qtdDias += atendimento.getQuantidadeDias();
                    }

                    vo.setQuantidadeAtendimentos(qtdAtendimentos);
                    vo.setQuantidadeDias(qtdDias); 
                    vos.add(vo);
                }

                mes += 1;
                if (mes == 13) {
                    mes = 1;
                    ano++;
                }
                anoMes = Long.valueOf("" + ano + "" + String.format("%02d", mes));
            }
        } 

        return vos;
    }

    private Map<String, List<AtendimentoVO>> buscarAtendimentosPorMes(List<AtendimentoVO> atendimentos) {
        Map<String, List<AtendimentoVO>> map = new HashMap<String, List<AtendimentoVO>>();

        for (AtendimentoVO atendimento : atendimentos) {
            String mesAno = SistamDateUtils.getMonthYear(atendimento.getDataInicial(), null);
            if (!map.containsKey(mesAno)) {
                map.put(mesAno, new ArrayList<AtendimentoVO>());
            }

            map.get(mesAno).add(atendimento);
        }

        return map;
    }

    private List<AtendimentoVO> buscarAtendimentos(Agenciamento agenciamento, Integer cicloDias, Date dataLimite) {

        List<AtendimentoVO> atendimentos = new ArrayList<AtendimentoVO>();
        Date dataChegada = SistamDateUtils.truncateTime(agenciamento.getDataChegada(), null);
        Date dataSaida = SistamDateUtils.truncateTime(agenciamento.getDataSaida(), null);
        Date dataInicial = null;
        Date dataFinal = null;

        dataLimite = SistamDateUtils.truncateTime(dataLimite, null);

        if (dataChegada == null) {
            return atendimentos;
        }

        if (dataSaida == null) {
            dataSaida = SistamDateUtils.obterUltimoDiaMes(dataLimite, null);
            dataSaida = SistamDateUtils.truncateTime(dataSaida, null);
        }

        int sobra = 0;
        while ((dataFinal == null) || (dataFinal.before(dataSaida))) {

            if (dataInicial == null) {
                dataInicial = dataChegada;
            } else {
                dataInicial = SistamDateUtils.somarDias(dataInicial, cicloDias, null);
            }

            /*if(!dataSaida.after(dataInicial)){
             dataInicial = dataSaida;
             }*/

            dataFinal = SistamDateUtils.somarDias(dataInicial, cicloDias - 1, null);

            if (dataFinal.after(dataSaida)) {
                dataFinal = dataSaida;
            }

            Long anoMesInicial = SistamDateUtils.getYearMonth(dataInicial, null);
            Long anoMesFinal = SistamDateUtils.getYearMonth(dataFinal, null);

            boolean temSobra = false;

            if (anoMesFinal > anoMesInicial) {
                temSobra = true;
                dataFinal = SistamDateUtils.obterUltimoDiaMes(dataInicial, null);
            }

            Integer diasEntre = SistamDateUtils.diasCorridos(dataInicial, dataFinal, null) + 1;

            if (dataInicial.before(dataFinal) || dataInicial.equals(dataFinal)) {

                AtendimentoVO atendimento = new AtendimentoVO();
                atendimento.setAgenciamento(agenciamento);
                atendimento.setCicloDias(cicloDias);
                atendimento.setDataInicial(dataInicial);
                atendimento.setDataFinal(dataFinal);
                atendimento.setQuantidadeDias(diasEntre + sobra);
                atendimentos.add(atendimento);

            }

            if (temSobra) {
                sobra = cicloDias - diasEntre;
            } else {
                sobra = 0;
            }


        }

        return atendimentos;
    } 

    //</editor-fold>
    private List<GrupoStatusAgenciamento> agruparAgenciamentosPorStatusEmbarcacao(Set<Agenciamento> agenciamentos) {
        //Agrupa os agenciamentos por status.
        List<GrupoStatusAgenciamento> agenciamentoPorStatus = new ArrayList<GrupoStatusAgenciamento>();
        Map<StatusEmbarcacao, List<Agenciamento>> mapTemporario = new HashMap<StatusEmbarcacao, List<Agenciamento>>();
        for (Agenciamento agenciamento : agenciamentos) {
            if (!mapTemporario.containsKey(agenciamento.getStatusEmbarcacao())) {
                mapTemporario.put(agenciamento.getStatusEmbarcacao(), new ArrayList<Agenciamento>());
            }
            mapTemporario.get(agenciamento.getStatusEmbarcacao()).add(agenciamento);
        }
        
        //montagem da caixa de entrada
        for (Map.Entry<StatusEmbarcacao, List<Agenciamento>> entry : mapTemporario.entrySet()) {
            GrupoStatusAgenciamento g = new GrupoStatusAgenciamento(entry.getKey());
            g.adicionarAgenciamentos(mapTemporario.get(entry.getKey()));
            agenciamentoPorStatus.add(g);
        }
        
        return agenciamentoPorStatus;
    }
    
    private List<GrupoFrota> agruparAgenciamentosPorFrota(Set<Agenciamento> agenciamentos) {
        //Agrupa os agenciamentos por frota.
        List<GrupoFrota> agenciamentoPorFrota = new ArrayList<GrupoFrota>();
        Map<TipoFrota, List<Agenciamento>> mapTemporario = new HashMap<TipoFrota, List<Agenciamento>>();
        for (Agenciamento agenciamento : agenciamentos) {
            if (!mapTemporario.containsKey(agenciamento.getTipoFrota())) {
                mapTemporario.put(agenciamento.getTipoFrota(), new ArrayList<Agenciamento>());
            }
            mapTemporario.get(agenciamento.getTipoFrota()).add(agenciamento);
        }
        //montagem da caixa de entrada
        for (Map.Entry< TipoFrota, List<Agenciamento>> entry : mapTemporario.entrySet()) {
            GrupoFrota g = new GrupoFrota(entry.getKey());
            g.adicionarAgenciamentos(mapTemporario.get(entry.getKey()));
            agenciamentoPorFrota.add(g);
        }
        return agenciamentoPorFrota;
    } 
    
}