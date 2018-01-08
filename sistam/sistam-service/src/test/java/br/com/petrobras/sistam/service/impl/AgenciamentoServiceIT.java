package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.business.AgenciamentoService;
import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.EmbarcacaoService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.business.PortoService;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.AgenciamentoSanitaria;
import br.com.petrobras.sistam.common.entity.AgenciamentoViagem;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.AreaNavegacao;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import br.com.petrobras.sistam.common.enums.MotivoDesvio;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import br.com.petrobras.sistam.common.valueobjects.GrupoAgenciaEFrotaVO;
import br.com.petrobras.sistam.common.valueobjects.GrupoAgenciaEStatusEmbarcacaoVO;
import br.com.petrobras.sistam.common.valueobjects.GrupoStatusAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.RelatorioMovimentacaoEmbarcacaoVO;
import br.com.petrobras.sistam.service.mock.AcessoServiceMock;
import br.com.petrobras.sistam.test.builder.AcompanhamentoBuilder;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import static br.com.petrobras.sistam.test.builder.AgenciaBuilder.novaAgencia;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import static br.com.petrobras.sistam.test.builder.AgenciamentoBuilder.novoAgenciamento;
import br.com.petrobras.sistam.test.builder.DesvioBuilder;
import br.com.petrobras.sistam.test.builder.EmbarcacaoBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import static br.com.petrobras.sistam.test.builder.PortoBuilder.novoPorto;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/AgenciamentoService.xml")
public class AgenciamentoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("AgenciamentoServiceImpl")
    private AgenciamentoService agenciamentoService;

    @SpringBean("AgenciaServiceImpl")
    private AgenciaService agenciaService;

    @SpringBean("EmbarcacaoServiceImpl")
    private EmbarcacaoService embarcacaoService;

    @SpringBean("PortoServiceImpl")
    private PortoService portoService;

    @SpringBean("CommonServiceImpl")
    private CommonService commonService;

    @SpringBean("PendenciaServiceImpl")
    private PendenciaService pendenciaService;

    @SpringBean("AcessoServiceImpl")
    private AcessoServiceMock acessoServiceMock;

    private Embarcacao embarcacaoValida;

    @Before
    public void setUp() {
        embarcacaoValida = embarcacaoService.buscarEmbarcacaoPorId("B915");
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");
    }

    @Test(expected = BusinessException.class)
    public void deveBuscarDadosRelatorioMovimentacaoEmbarcacaoELancarExcessaoQuandoNaoPossuiDados() {
        agenciamentoService.buscarDadosRelatorioMovimentacaoEmbarcacao(AgenciaBuilder.novaAgencia().comId(1L).build(), PortoBuilder.novoPorto().comId("SALV").build());
    }

    @Test
    public void deveBuscarDadosRelatorioMovimentacaoEmbarcacao() {
        RelatorioMovimentacaoEmbarcacaoVO vo = agenciamentoService.buscarDadosRelatorioMovimentacaoEmbarcacao(AgenciaBuilder.novaAgencia().comId(1L).build(), PortoBuilder.novoPorto().comId("COAR").build());
        assertNotNull(vo);
    }

    //<editor-fold defaultstate="collapsed" desc="Busca de Agenciamentos Por Filtro Relatorio Timesheet">
    @Test
    public void deveRetornarTodosAgenciamentosExistente() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        filtro.setDataDeCorte(null);
        List<Agenciamento> list = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, Collections.EMPTY_LIST);
        assertEquals(7, list.size());
    }

    @Test
    public void deveRetornarTodosAgenciamentosComDataDeCorte() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        List<Agenciamento> list = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, Collections.EMPTY_LIST);
        assertEquals(2, list.size());
    }

    @Test
    public void deveRetornarTodosAgenciamentosDoPorto() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).build());
        filtro.setPorto(PortoBuilder.novoPorto().comId("COAR").build());
        List<Agenciamento> list = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, Collections.EMPTY_LIST);
        assertEquals(1, list.size());
    }

    @Test
    public void deveRetornarTodosAgenciamentosComEmbarcacaoNoPorto() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).build());
        filtro.setPorto(PortoBuilder.novoPorto().comId("COAR").build());
        filtro.setSituacaoEmbarcacao(StatusEmbarcacao.NO_PORTO);
        List<Agenciamento> list = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, Collections.EMPTY_LIST);
        assertEquals(1, list.size());
    }

    @Test
    public void deveRetornarTodosAgenciamentosComFrotaNaoAplicavel() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).build());
        filtro.setPorto(PortoBuilder.novoPorto().comId("COAR").build());
        filtro.setTipoFrota(TipoFrota.NAO_APLICAVEL);
        List<Agenciamento> list = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, Collections.EMPTY_LIST);
        assertEquals(1, list.size());
    }

    @Test
    public void deveRetornarTodosAgenciamentosDaEmbarcacao() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).build());
        filtro.setPorto(PortoBuilder.novoPorto().comId("COAR").build());
        filtro.getEmbarcacoes().add(EmbarcacaoBuilder.novaEmbarcacao().comId("0098").build());
        filtro.setSituacaoEmbarcacao(StatusEmbarcacao.NO_PORTO);
        filtro.setTipoFrota(TipoFrota.NAO_APLICAVEL);
        List<Agenciamento> list = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, Collections.EMPTY_LIST);
        assertEquals(list.size(), 1);
    }

    @Test
    public void deveGerarZipComPdfs() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        filtro.setDataDeCorte(null);
        filtro.setTipoFrota(TipoFrota.NAO_APLICAVEL);
        Map<GrupoAgenciaEStatusEmbarcacaoVO, InputStream> map = agenciamentoService.gerarArquivosZipComPdfsAgrupadosPorStatusEmbarcacao(filtro, Collections.EMPTY_LIST);
        assertEquals(map.size(), 2);
    }

    @Test
    public void deveGerarZipComPdfsAgrupadosPorFrota() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        filtro.setDataDeCorte(null);
        filtro.setTipoFrota(TipoFrota.NAO_APLICAVEL);
        Map<GrupoAgenciaEFrotaVO, InputStream> map = agenciamentoService.gerarArquivosZipComPdfsAgrupadosPorFrota(filtro, Collections.EMPTY_LIST);
        assertEquals(map.size(), 2);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Busca de agenciamento">
    @Test
    public void deveRetornarAgenciamentoDoId() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoPorId(827L);
        assertEquals(827, (long) agenciamento.getId());
    }

    /**
     * Deve retornar os agenciamentos da agência e porto informados e com as
     * seguintes condições: - com data de saída nula; - ou com data de saída
     * maior ou igual a data atual-30 dias; - ou com data de saída menor que
     * data atual - 30 dias e com pendências não resolvidas; - ou com desvio de
     * rota com data maior ou igual a data atual - 30 dias;
     */
    @Test
    public void deveRetornarOsAgenciamentosParaCaixaDeEntrada() {

        //Obtém a data de corte
        Calendar dataDeCorte = Calendar.getInstance();
        dataDeCorte.set(2013, 0, 25);

        Agencia agencia = agenciaService.buscarAgenciaPorId(2L);
        List<GrupoStatusAgenciamento> listaGrupo = agenciamentoService.buscarAgenciamentosParaCaixaDeEntrada(agencia, null, dataDeCorte.getTime());

        List<Agenciamento> listaAgenciamentos = obterListaDeAgenciamento(listaGrupo);

        List<Long> listaIdEsperada = new ArrayList<Long>(Arrays.asList(827L, 828L, 830L, 831L, 832L));
        assertListaDeAgenciamento(listaAgenciamentos, listaIdEsperada);
    }

    @Test
    public void deveRetornarAgenciamentosDaViagem() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        FiltroAgenciamento filtro = new FiltroAgenciamento();
        filtro.setVgm("014 ST");
        filtro.setAgencia(agencia);
        filtro.setEmbarcacao(EmbarcacaoBuilder.novaEmbarcacao().comId("0098").build());
        filtro.setPorto(PortoBuilder.novoPorto().comId("COAR").build());
        filtro.setAreaNavegacao(AreaNavegacao.CABOTAGEM);
        filtro.setNumeroProcesso("123");
        filtro.setStatusEmbarcacao(StatusEmbarcacao.NO_PORTO);
        filtro.setTipoContrato(TipoContrato.TCP);
        List<Agenciamento> agenciamentos = agenciamentoService.buscarAgenciamentosPorFiltro(filtro);
        assertEquals(1, agenciamentos.size());
    }

    @Test
    public void deveBuscarCancelamentoDoAgenciamento() {
        CancelamentoAgenciamento cancelamento = agenciamentoService.buscarCancelamentoDoAgenciamento(827L);
        assertNull(cancelamento);
    }

    @Test
    public void deveRetornarAgenciamentosDosPortosDeOrigemEDestino() {

        Porto portoOrigem = portoService.buscarPortosPorId("SALV");
        Porto portoDestino = portoService.buscarPortosPorId("MAUS");

        FiltroAgenciamento filtro = new FiltroAgenciamento();
        filtro.setPortoOrigem(portoOrigem);
        filtro.setPortoDestino(portoDestino);

        List<Agenciamento> agenciamentos = agenciamentoService.buscarAgenciamentosPorFiltro(filtro);

        List<Long> listaIdEsperada = Arrays.asList(new Long[]{826L, 831L});
        assertListaDeAgenciamento(agenciamentos, listaIdEsperada);
    }

    @Test
    public void deveRetornarOsAgenciamentosParaORelatorioDeProdutividade() {
        TimeZone zone = TimeZone.getTimeZone("America/Bahia");
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(2L).comTimeZone("America/Bahia").build();
        Date dataChagadaInicio = SistamDateUtils.informarDataHora(1, 12, 2013, 12, 0, zone);
        Date dataChagadaFim = SistamDateUtils.informarDataHora(31, 12, 2013, 12, 0, zone);

        FiltroAgenciamento filtro = new FiltroAgenciamento();
        filtro.setAgencia(agencia);
        filtro.setDataChegadaIni(dataChagadaInicio);
        filtro.setDataChegadaFim(dataChagadaFim);

        List<Agenciamento> listaAgenciamentos = agenciamentoService.buscarAgenciamentosRelatorioProdutividade(filtro);
        List<Long> listaIdEsperada = new ArrayList<Long>(Arrays.asList(827L, 829L));
        assertListaDeAgenciamento(listaAgenciamentos, listaIdEsperada);
    }

    @Test
    public void deveRetornarOsAgenciamentosVCPParaORelatorioDeProdutividade() {
        TimeZone zone = TimeZone.getTimeZone("America/Bahia");
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(2L).comTimeZone("America/Bahia").build();
        Date dataChagadaInicio = SistamDateUtils.informarDataHora(1, 12, 2013, 12, 0, zone);
        Date dataChagadaFim = SistamDateUtils.informarDataHora(31, 12, 2013, 12, 0, zone);

        FiltroAgenciamento filtro = new FiltroAgenciamento();
        filtro.setAgencia(agencia);
        filtro.setDataChegadaIni(dataChagadaInicio);
        filtro.setDataChegadaFim(dataChagadaFim);
        filtro.setTipoContrato(TipoContrato.VCP);

        List<Agenciamento> listaAgenciamentos = agenciamentoService.buscarAgenciamentosRelatorioProdutividade(filtro);
        List<Long> listaIdEsperada = new ArrayList<Long>(Arrays.asList(829L));
        assertListaDeAgenciamento(listaAgenciamentos, listaIdEsperada);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Criação de um novo agenciamento">
    @Test
    public void aoCriarUmaNovoAgenciamentoOAgenciamentoDeveSerGravadaNoBanco() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento = agenciamentoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        Agenciamento agenciamentoRecuperado = agenciamentoService.buscarAgenciamentoPorId(agenciamento.getId());
        assertNotNull(agenciamentoRecuperado);
    }

    @Test
    public void aoCriarUmaNovoAgenciamentoOAgenciamentoViagemDeveSerCriadaNoBanco() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento = agenciamentoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        assertNotNull(commonService.buscarEntidadePorId(AgenciamentoViagem.class, agenciamento.getId()));
    }

    @Test
    public void aoCriarUmaNovoAgenciamentoOAgenciamentoSanitariaDeveSerCriadaNoBanco() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento = agenciamentoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        assertNotNull(commonService.buscarEntidadePorId(AgenciamentoSanitaria.class, agenciamento.getId()));
    }

    @Test
    public void aoCriarUmaNovoAgenciamentoAPendenciaPagamentoLPDeveSerCriadaNoBanco() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento = agenciamentoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));

        Pendencia pendencia = obterPendenciaPorTipo(agenciamento, TipoPendencia.PAGAMENTO_LP);
        assertNotNull(commonService.buscarEntidadePorId(Pendencia.class, pendencia.getId()));
    }

    @Test
    public void aoCriarUmaNovoAgenciamentoAPendenciaDespachoSaidaDeveSerCriadaNoBanco() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento = agenciamentoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));

        Pendencia pendencia = obterPendenciaPorTipo(agenciamento, TipoPendencia.DESPACHO_SAIDA);
        assertNotNull(commonService.buscarEntidadePorId(Pendencia.class, pendencia.getId()));
    }

    @Test
    public void aoCriarUmaNovoAgenciamentoAPendenciaPagamentoFunapoDeveSerCriadaNoBancoQuandoOPortoOrigemForInternacional() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setPortoOrigem(novoPorto().comId("AGUI").comPais("135").build());
        agenciamento = agenciamentoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));

        Pendencia pendencia = obterPendenciaPorTipo(agenciamento, TipoPendencia.PAGAMENTO_FUNAPOL);
        assertNotNull(commonService.buscarEntidadePorId(Pendencia.class, pendencia.getId()));
    }

    @Test
    public void seExistirUmAgenciamentoComEmbarcacaoPortoEtaInformadosESituacaoDiferenteDeCanceladoNaVerificacaoDeveRetornarTrue() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).comTimeZone("Bahia/America").build());
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setEmbarcacao(EmbarcacaoBuilder.novaEmbarcacao().comId("0098").build());
        agenciamento.setEta(DateBuilder.on(18, 12, 2012).at(0, 0, 0).getTime());
        agenciamento.setPorto(PortoBuilder.novoPorto().comId("COAR").build());

        assertTrue(agenciamentoService.verificarSeAgenciamentoJaFoiCriado(agenciamento));
    }

    @Test
    public void seNaoExistirAgenciamentoComEmbarcacaoPortoEtaInformadosESituacaoDiferenteDeCanceladoNaVerificacaoDeveRetornarFalse() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).comTimeZone("Bahia/America").build());
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setEmbarcacao(EmbarcacaoBuilder.novaEmbarcacao().comId("0098").build());
        agenciamento.setEta(DateBuilder.on(19, 12, 2015).at(0, 0, 0).getTime());
        agenciamento.setPorto(PortoBuilder.novoPorto().comId("COAR").build());

        assertFalse(agenciamentoService.verificarSeAgenciamentoJaFoiCriado(agenciamento));
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cancelamento do Agenciamento">
    @Test
    public void deveSalvarNaBaseOAgenciamentoQuandoHouverOCancelamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciamentoService.cancelarAgenciamento(cancelamento);

        Agenciamento agenciamentoRecuperado = (Agenciamento) commonService.buscarEntidadePorId(Agenciamento.class, cancelamento.getAgenciamento().getId());
        assertTrue(StatusEmbarcacao.CANCELADO.equals(agenciamentoRecuperado.getStatusEmbarcacao()));
    }

    @Test
    public void deveSalvarOCancelamentoQuandoHouverOCancelamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciamentoService.cancelarAgenciamento(cancelamento);

        CancelamentoAgenciamento cancelamentoRecuperado = (CancelamentoAgenciamento) commonService.buscarEntidadePorId(CancelamentoAgenciamento.class, cancelamento.getAgenciamento().getId());
        assertNotNull(StatusEmbarcacao.CANCELADO.equals(cancelamentoRecuperado));
    }

    @Test
    public void deveExcluirAsPendenciasQuandoCancelarAgenciamento() {
        //recupera o agenciamento do banco e se certifica que ele possui pendencias
        Agenciamento agenciamentoComPendencias = agenciamentoService.buscarAgenciamentoPorId(827L);
        agenciamentoComPendencias.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        assertNotNull(agenciamentoComPendencias);
        assertNotNull(agenciamentoComPendencias.getPendencias());
        assertTrue(agenciamentoComPendencias.getPendencias().size() > 0);

        //obtem o cancelamento e atualiza com o agenciamento que contém pendências.
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        cancelamento.setAgenciamento(agenciamentoComPendencias);

        agenciamentoService.cancelarAgenciamento(cancelamento);

        //verifica e as pendencias do agenciamento foram excluídas
        Agenciamento agenciamentoSemPendencias = agenciamentoService.buscarAgenciamentoPorId(agenciamentoComPendencias.getId());
        assertNotNull(agenciamentoSemPendencias);
        assertTrue(agenciamentoSemPendencias.getPendencias() == null || agenciamentoSemPendencias.getPendencias().isEmpty());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Busca de Agenciamento Sanitário">
    @Test
    public void deveRetornarOAgenciamentoSanitariaParaDesvioDeRotaQuandoPassarOId() {
        AgenciamentoSanitaria agenciamentoSanitaria = agenciamentoService.buscarAgenciamentoSanitariaPorId(832L);
        assertNotNull(agenciamentoSanitaria);
    }

    @Test
    public void deveRetornarNullQuandoOIdPassadoNaoCorresponderANehumAgenciamentoSanitaria() {
        AgenciamentoSanitaria agenciamentoSanitaria = agenciamentoService.buscarAgenciamentoSanitariaPorId(1L);
        assertNull(agenciamentoSanitaria);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Busca de Agenciamento Viagem">
    @Test
    public void deveRetornarOAgenciamentoViagemParaDesvioDeRotaQuandoPassarOId() {
        AgenciamentoViagem agenciamentoViagem = agenciamentoService.buscarAgenciamentoViagemPorId(832L);
        assertNotNull(agenciamentoViagem);
    }

    @Test
    public void deveRetornarNullQuandoOIdPassadoNaoCorresponderANehumAgenciamentoViagem() {
        AgenciamentoViagem agenciamentoViagem = agenciamentoService.buscarAgenciamentoViagemPorId(1L);
        assertNull(agenciamentoViagem);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Busca do Agenciamento para desvio de rota">
    @Test
    public void deveRetornarOAgenciamentoParaDesvioDeRotaQuandoPassarOId() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoParaDesvioDeRota(830L);
        assertNotNull(agenciamento);
    }

    @Test
    public void deveRetornarNullQuandoOIdPassadoNaoCorresponderANehumAgenciamento() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoParaDesvioDeRota(1L);
        assertNull(agenciamento);
    }

    @Test
    public void naoDeveDarErroDeLazyAoAcessarODesvioQuandoOAgenciamentoPossuirDesvio() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoParaDesvioDeRota(830L);
        assertNotNull(agenciamento.getDesvio().getId());
    }

    @Test
    public void naoDeveDarErroDeLazyAoAcessarOPortoDestinoDoDesvioQuandoOAgenciaemntoPossuirDesvio() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoParaDesvioDeRota(830L);
        assertNotNull(agenciamento.getDesvio().getPortoDestinoAlterado().getId());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Busca dos Agenciamentos para o Painel">
    @Test
    public void deveRetornarOsAgenciamentosParaPainelDeAcordoComOsFiltrosInformados() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(2L).build();
        List<StatusEmbarcacao> listaStatus = Arrays.asList(new StatusEmbarcacao[]{StatusEmbarcacao.NO_PORTO});

        List<Agenciamento> agenciamentosRetornadas = agenciamentoService.buscarAgenciamentosParaPainel(agencia, listaStatus);

        List<Long> listaIdEsperada = Arrays.asList(new Long[]{828L});

        assertListaDeAgenciamento(agenciamentosRetornadas, listaIdEsperada);
    }

    @Test
    public void aoBuscarOsAgenciamentosParaOPainelAListaDeOperacoesNaoDeveEstarEmLazy() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        List<StatusEmbarcacao> listaStatus = Arrays.asList(new StatusEmbarcacao[]{StatusEmbarcacao.NO_PORTO});

        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentosParaPainel(agencia, listaStatus).get(0);

        //Ao acessar não deve ocorrer erro de lazy;
        agenciamento.getOperacoes().size();
    }

    @Test
    public void aoBuscarOsAgenciamentosParaOPainelOPortoOrigemNaoDeveEstarEmLazy() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        List<StatusEmbarcacao> listaStatus = Arrays.asList(new StatusEmbarcacao[]{StatusEmbarcacao.NO_PORTO});

        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentosParaPainel(agencia, listaStatus).get(0);

        //Ao acessar não deve ocorrer erro de lazy;
        agenciamento.getPortoOrigem().getId();
    }

    @Test
    public void aoBuscarOsAgenciamentosParaOPainelOPortoDestinoNaoDeveEstarEmLazy() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        List<StatusEmbarcacao> listaStatus = Arrays.asList(new StatusEmbarcacao[]{StatusEmbarcacao.NO_PORTO});

        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentosParaPainel(agencia, listaStatus).get(0);

        //Ao acessar não deve ocorrer erro de lazy;
        agenciamento.getPortoDestino().getId();
    }

    @Test
    public void aoBuscarOsAgenciamentosParaOPainelAListaDeManobrasNaoDeveEstarEmLazy() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        List<StatusEmbarcacao> listaStatus = Arrays.asList(new StatusEmbarcacao[]{StatusEmbarcacao.NO_PORTO});

        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentosParaPainel(agencia, listaStatus).get(0);

        //Ao acessar não deve ocorrer erro de lazy;
        agenciamento.getManobras().size();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Salvar desvio">
    @Test
    public void devePersistirNovoDesvioNoBanco() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoPorId(831L);
        Desvio desvio = obterDesvioValido();
        desvio.setAgenciamento(agenciamento);
        Porto novoPorto = portoService.buscarPortosPorId("FLZA");
        String destinoIntermediarioDesvio = desvio.getDestinoIntermediarioAlterado().toString();
        agenciamentoService.salvarDesvio(desvio, novoPorto, destinoIntermediarioDesvio);

        Agenciamento agenciamentoRecuperado = agenciamentoService.buscarAgenciamentoParaDesvioDeRota(831L);
        assertNotNull(agenciamentoRecuperado.getDesvio());
    }

    @Test
    public void deveCriarPendenciaAoSalvarDesvio() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoPorId(831L);
        Desvio desvio = obterDesvioValido();
        desvio.setAgenciamento(agenciamento);
        String destinoIntermediarioDesvio = desvio.getDestinoIntermediarioAlterado().toString();
        Porto novoPorto = portoService.buscarPortosPorId("FLZA");
        agenciamentoService.salvarDesvio(desvio, novoPorto, destinoIntermediarioDesvio);

        Pendencia pendencia = pendenciaService.buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(agenciamento, TipoPendencia.DESVIO_ROTA);
        assertNotNull(pendencia);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Salvar Acompanhamento">
    @Test
    public void devePersistirNovoAcompanhamentoNoBanco() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoPorId(831L);
        Acompanhamento acompanhamento = obterAcompanhamentoValido();
        acompanhamento.setAgenciamento(agenciamento);
        acompanhamento = agenciamentoService.salvarAcompanhamento(acompanhamento);
        assertNotNull(acompanhamento.getId());
    }

    @Test
    public void deveRetornarOsAcompanhamentosDoAgenciamento() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoPorId(832L);
        List<Acompanhamento> acompanhamentos = agenciamentoService.buscarAcompanhamentos(agenciamento);
        List<Long> listaIdEsperada = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L));
        assertTrue(acompanhamentos.size() == 3);
        for (Acompanhamento ac : acompanhamentos) {
            assertTrue(listaIdEsperada.contains(ac.getId()));
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private Agenciamento obterNovoAgenciamentoValido() {
        Agenciamento agenciamento = novoAgenciamento()
                .comAgencia(novaAgencia().comId(1L).build())
                .comEmbarcacao(embarcacaoValida)
                .comViagem("1324")
                .comViagemSaida("1234")
                .comPortoOrigem(novoPorto().comId("AGUI").comPais("135").build())
                .comPortoAtual(novoPorto().comId("SALV").comPais("019").build())
                .comDataEstimadaDeChegada(DateBuilder.on(15, 10, 2013).getTime())
                .comDataInclusao(new Date())
                .comChaveCadastrador("STAM")
                .comNomeCadastrador("SISTAM")
                .comTipoFrota(TipoFrota.NAO_APLICAVEL)
                .build();

        return agenciamento;
    }

    private Agenciamento obterAgenciamentoValidoParaSalvar() {
        Agenciamento agenciamento = novoAgenciamento()
                .comAgencia(novaAgencia().comId(1L).build())
                .comId(826L)
                .comEmbarcacao(embarcacaoValida)
                .comViagem("1324")
                .comViagemSaida("1234")
                .comPortoOrigem(novoPorto().comId("AGUI").comPais("135").build())
                .comPortoAtual(novoPorto().comId("SALV").comPais("019").build())
                .comDataEstimadaDeChegada(DateBuilder.on(15, 10, 2013).getTime())
                .comDataInclusao(new Date())
                .comChaveCadastrador("STAM")
                .comNomeCadastrador("SISTAM")
                .comAnoProcesso(2014)
                .comTipoFrota(TipoFrota.NAO_APLICAVEL)
                .comCodigo(1234L)
                .build();

        return agenciamento;
    }

    private Pendencia obterPendenciaPorTipo(Agenciamento agenciamento, TipoPendencia tipo) {
        for (Pendencia pendencia : agenciamento.getPendencias()) {
            if (tipo.equals(pendencia.getTipoPendencia())) {
                return pendencia;
            }
        }
        return null;
    }

    private Desvio obterDesvioValido() {
        Porto porto = PortoBuilder.novoPorto().comId("SALV").comPais("019").build();
        Desvio desvio = DesvioBuilder.novoDesvio()
                .comData(new Date())
                .comMotivo(MotivoDesvio.REABASTECIMENTO)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comId(1234L).comStatusEmbarcacao(StatusEmbarcacao.SAIDO).comPortoDestino(porto).build())
                .comDestinoIntermediarioValido("TESTE")
                .build();
        return desvio;
    }

    private Acompanhamento obterAcompanhamentoValido() {
        Acompanhamento acompanhamento = AcompanhamentoBuilder.novoAcompanhamento()
                .comData(new Date())
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comId(1234L).comStatusEmbarcacao(StatusEmbarcacao.SAIDO).build())
                .comTexto("Teste")
                .build();
        return acompanhamento;
    }

    public CancelamentoAgenciamento obterCancelamentoValido() {
        Agenciamento agenciamento = obterAgenciamentoValidoParaSalvar();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);

        CancelamentoAgenciamento cancelamentoAgenciamento = new CancelamentoAgenciamento();
        cancelamentoAgenciamento.setAgenciamento(agenciamento);
        cancelamentoAgenciamento.setMotivo(MotivoCancelamento.OUTROS);
        cancelamentoAgenciamento.setDescricaoMotivo("Cancelando");
        return cancelamentoAgenciamento;
    }

    private void assertListaDeAgenciamento(List<Agenciamento> listaAgenciamentoRetornada, List<Long> listaIdEsperado) {
        assertTrue(listaAgenciamentoRetornada.size() == listaIdEsperado.size());

        List<Long> listaIdRetornado = new ArrayList<Long>();
        for (Agenciamento agenciamento : listaAgenciamentoRetornada) {
            listaIdRetornado.add(agenciamento.getId());
        }

        for (Long idEsperado : listaIdEsperado) {
            assertTrue(listaIdRetornado.contains(idEsperado));
        }
    }

    private List<Agenciamento> obterListaDeAgenciamento(List<GrupoStatusAgenciamento> listaGrupo) {
        List<Agenciamento> listaAgenciamento = new ArrayList<Agenciamento>();

        for (GrupoStatusAgenciamento gsa : listaGrupo) {
            listaAgenciamento.addAll(gsa.getAgenciamento());
        }

        return listaAgenciamento;
    }

    //</editor-fold>
}
