package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import br.com.petrobras.sistam.common.enums.MotivoDesvio;
import br.com.petrobras.sistam.common.enums.SituacaoProcesso;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamentoAtendimento;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.dao.GeradorDeNumeroProcessoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaAgenciamentoExistenteComEscalaMercante;
import br.com.petrobras.sistam.service.query.agenciamento.ExcluirPendenciaPorAgenciamento;
import br.com.petrobras.sistam.service.validator.ValidadorAgenciamento;
import br.com.petrobras.sistam.test.builder.AcompanhamentoBuilder;
import static br.com.petrobras.sistam.test.builder.AgenciaBuilder.novaAgencia;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import static br.com.petrobras.sistam.test.builder.AgenciamentoBuilder.novoAgenciamento;
import br.com.petrobras.sistam.test.builder.DesvioBuilder;
import br.com.petrobras.sistam.test.builder.EmbarcacaoBuilder;
import static br.com.petrobras.sistam.test.builder.EscalaBuilder.novaEscala;

import br.com.petrobras.sistam.test.builder.PortoBuilder;
import static br.com.petrobras.sistam.test.builder.PortoBuilder.novoPorto;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;

public class AgenciamentoServiceTest {
    private AgenciamentoServiceImpl agenciaemntoService;
    private AcessoServiceImpl acessoService;
    private GenericDao dao;
    private ValidadorPermissao validadorPermissao;
    private ValidadorAgenciamento validadorAgenciamento;
    private PendenciaServiceImpl pendenciaService;
    private EmbarcacaoServiceImpl embarcacaoService;
    private GeradorDeNumeroProcessoAgenciamento geradorProcesso;

    @Before
    public void setUp() {
        dao = Mockito.mock(GenericDao.class);
        acessoService = mock(AcessoServiceImpl.class);
        geradorProcesso = Mockito.mock(GeradorDeNumeroProcessoAgenciamento.class);
        pendenciaService = Mockito.mock(PendenciaServiceImpl.class);
        
        agenciaemntoService = new AgenciamentoServiceImpl(dao);
        agenciaemntoService.setAcessoService(acessoService);
        agenciaemntoService.setGeradorProcesso(geradorProcesso);
        agenciaemntoService.setPendenciaAgenciamentoService(pendenciaService);
        
        carregarValidadorPermissaoComMock();
        carregarValidadorComMock();
        carregarEmbarcacaoServiceComMock();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
    }

    @Test
    public void naoDeveInciarAgenciamentoSeStatusForDiferenteDeEsperado() {
        Agenciamento agenciamento = novoAgenciamento().comAgencia(novaAgencia().build()).build();
        agenciamento.setId(1L);
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.FUNDEADO);
        agenciamento.setDataChegada(SistamDateUtils.informarDataHora(05, 10, 2013, 8, 15, null));
        agenciaemntoService.salvarAgenciamento(agenciamento);
        assertEquals("O agenciamemnto não deve ser iniciado se o status da embarcação for diferente de esperado", StatusEmbarcacao.FUNDEADO, agenciamento.getStatusEmbarcacao());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Agenciamento">
    
    @Test
    public void deveChamarOsValidadoresAoSalvarUmAgenciamento(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciaemntoService.salvarAgenciamento(agenciamento);
        Mockito.verify(validadorPermissao).podeSalvarAgenciamento(agenciamento.getAgencia());
        Mockito.verify(validadorAgenciamento).validarSalvarAgenciamento(agenciamento);
    }
    
    @Test
    public void naoSeraPossivelSalvarUmaAgenciamentoComAEscalaMercanteIgualADeOutroAgenciamentoExistente(){
        Long escalaMercante = 12345L;
        Agenciamento agm = novoAgenciamento().comCodigo(123l).comAnoPorcesso(2015)
                .comAgencia(novaAgencia().comSigla("ABC").build())
                .comEmbarcacao(EmbarcacaoBuilder.novaEmbarcacao().comNome("NavioX").build())
                .comViagem("103 ET")
                .build();
        Mockito.when(dao.uniqueResult(Mockito.any(ConsultaAgenciamentoExistenteComEscalaMercante.class))).thenReturn(agm);
        
        Agenciamento agenciamento = novoAgenciamento().comAgencia(novaAgencia().build()).build();
        agenciamento.setNumeroEscalaMercante(escalaMercante);
        
        try{
            agenciaemntoService.salvarAgenciamento(agenciamento);
            fail();
        }catch(BusinessException ex){
            BusinessException e = new BusinessException(ConstantesI18N.AGENCIAMENTO_ESCALA_MARCANTE_JA_EXISTENTE, "ABC123/2015");
            assertEquals(e.getMessage(), ex.getMessage());
        }
    }
    
    @Test
    public void seOStatusForEsperadoEADataDeChegadaForInformadaEntaoOAgenciamentoDeveFicarComOStatusEmTransito(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciamento = agenciaemntoService.salvarAgenciamento(agenciamento);
        
        assertEquals(StatusEmbarcacao.NO_PORTO, agenciamento.getStatusEmbarcacao());
    }
    
    @Test
    public void seOStatusForDiferenteDeSaidoEDesviadoEADataDeSaidaForInformadaDeveValidarASaidaDaEmbarcacao(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.NO_PORTO);
        agenciamento.setDataSaida(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(validadorAgenciamento).validarSaidaEmbarcacaoAgenciada(agenciamento);
    }
    
    @Test
    public void seOStatusForDiferenteDeSaidoEDesviadoEADataDeSaidaForInformadaOAgenciamentoDeveFicarComOStatusSaido(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.NO_PORTO);
        agenciamento.setDataSaida(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciamento = agenciaemntoService.salvarAgenciamento(agenciamento);
        
        assertEquals(StatusEmbarcacao.SAIDO, agenciamento.getStatusEmbarcacao());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateParaOAgenciamentoAoSavar(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciaemntoService.salvarAgenciamento(agenciamento);
        Mockito.verify(dao).saveOrUpdate(agenciamento);
    }
    
    @Test
    public void deveChamarOSaveOrUpdateParaOAgenciamentoViagemAoSavar(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciaemntoService.salvarAgenciamento(agenciamento);
        Mockito.verify(dao).saveOrUpdate(agenciamento.getAgenciementoViagem());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateParaOAgenciamentoSanitariaAoSavar(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciaemntoService.salvarAgenciamento(agenciamento);
        Mockito.verify(dao).saveOrUpdate(agenciamento.getAgenciementoSanitaria());
    }
    
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaParteDeEntrada(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.PARTE_ENTRADA);
    }
    
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaComunicacaoDeChegada(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.COMUNICACAO_CHEGADA);
    }
    
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaCabotagemParteDeSaida(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
    }
    
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaPagamentoTUF(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.PAGAMENTO_TUF);
    }
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaListaTripulantePassageiro(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.LISTA_TRIPULANTES_PASSAGEIROS);
    }
  
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaListaPertenceTripulantePassageiro(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.LISTA_PERTENCES_TRIPULANTES_PASSAGEIROS);
    }
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaListaUltimosPortos(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.LISTA_ULTIMOS_PORTOS);
    }
  
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaListaProvisoesBorto(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.LISTA_PROVISOES_BORDO);
    }
  
    @Test
    public void aoSalvarUmaAgenciamentoTCPEOStatusMudarDeEsparadoParaEmTransitoDeveGerarAPendenciaPlanoCarga(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        agenciamento.setDataChegada(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.PLANO_CARGA);
    }
    
    @Test
    public void aoSavarUmAgenciamentoTCPEOStatusMudarParaSaidaDeveSerGeradaAPendenciaParteDeSaida(){
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.NO_PORTO);
        agenciamento.setDataSaida(DateBuilder.on(1, 12, 2013).getTime());
        
        agenciamento = agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.PARTE_SAIDA);
    }
    
    /*@Test
    public void aoSavarUmAgenciamentoTCPEOStatusMudarParaSaidaEPossuirOperacaoCargaDeveSerGeradaAPendenciaDocumentoOperacaoCargaCabotagem(){
        OperacaoProduto operacaoProduto = OperacaoProdutoBuilder.novaOperacaoProduto().comTipoOperacao(TipoOperacao.CARGA_CABOTAGEM).build();
        Operacao operacao = OperacaoBuilder.novaOperacao().comProdutos(operacaoProduto).build();
        
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.EM_TRANSITO);
        agenciamento.setDataSaida(DateBuilder.on(1, 12, 2013).getTime());
        agenciamento.adicionarOperacao(operacao);
        
        agenciamento = agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_CARGA_CABOTAGEM);
    }
    
    @Test
    public void aoSavarUmAgenciamentoTCPEPossuirOperacaoDescagaDeveSerGeradaAPendenciaDocumentoOperacaoDescargaCabotagemCasoNaoTenhaSidoGeradaAinda(){
        OperacaoProduto operacaoProduto = OperacaoProdutoBuilder.novaOperacaoProduto().comTipoOperacao(TipoOperacao.DESCARGA_CABOTAGEM).build();
        Operacao operacao = OperacaoBuilder.novaOperacao().comProdutos(operacaoProduto).build();
        
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.EM_TRANSITO);
        agenciamento.setDataSaida(DateBuilder.on(1, 12, 2013).getTime());
        agenciamento.adicionarOperacao(operacao);
        
        Mockito.when(pendenciaService.buscarPendenciaDoAgenciamentoPorTipo(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_DESGARGA_CABOTAGEM))
                .thenReturn(null);
        
        agenciamento = agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_DESGARGA_CABOTAGEM);
    }
    
    @Test
    public void aoSavarUmAgenciamentoTCPEPossuirOperacaoDescagaNaoDeveSerGeradaAPendenciaDocumentoOperacaoDescargaCabotagemCasoJaTenhaSidoGerada(){
        OperacaoProduto operacaoProduto = OperacaoProdutoBuilder.novaOperacaoProduto().comTipoOperacao(TipoOperacao.DESCARGA_CABOTAGEM).build();
        Operacao operacao = OperacaoBuilder.novaOperacao().comProdutos(operacaoProduto).build();
        
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.EM_TRANSITO);
        agenciamento.setDataSaida(DateBuilder.on(1, 12, 2013).getTime());
        agenciamento.adicionarOperacao(operacao);
        
        Mockito.when(pendenciaService.buscarPendenciaDoAgenciamentoPorTipo(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_DESGARGA_CABOTAGEM))
                .thenReturn(new Pendencia());
        
        agenciamento = agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService, Mockito.times(0)).criarPendencia(agenciamento, TipoPendencia.DOCUMENTACAO_OPERACAO_DESGARGA_CABOTAGEM);
    }*/
    
    @Test
    public void aoSavarUmAgenciamentoVCPNaoDeveSerGeradoPendencias(){
        //Prepara um agenciamento VCP que, se fosse TCP, geraria as pendências quando o status muda para saído.
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.NO_PORTO);
        agenciamento.setDataSaida(DateBuilder.on(1, 12, 2013).getTime());
        agenciamento.setTipoContrato(TipoContrato.VCP);
        
        agenciamento = agenciaemntoService.salvarAgenciamento(agenciamento);
        
        Mockito.verify(pendenciaService, Mockito.times(0)).criarPendencia(agenciamento, TipoPendencia.PARTE_SAIDA);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Desvio do Agenciamento">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarDesvio() {
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("1234").build();
      // String novoDestinoIntermediario = "novo inter"
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        Mockito.verify(validadorPermissao).podeSalvarAgenciamento(Mockito.any(Agencia.class));
        Mockito.verify(validadorAgenciamento).validarDesvio(desvio, novoPorto);
    }
    
    @Test
    public void deveAtualizarIdDoDesvioComODoAgenciamentoQuandoSalvarDesvio() {
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("1234").build();
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        assertEquals(desvio.getAgenciamento().getId(), desvio.getId());
    }
    
    @Test
    public void deveAtualizarADataDoDesvioQuandoSalvarDesvio() {
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("1234").build();
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        assertEquals(SistamDateUtils.formatShortDate(new Date(), null), SistamDateUtils.formatShortDate(desvio.getData(), null));
    }
    
    @Test
    public void deveAtualizarOPortoDestinoAlteradoQuandoSalvarDesvio() {
        Desvio desvio = obterDesvioValido();
        Porto portoAntigo = desvio.getAgenciamento().getPortoDestino();
        Porto novoPorto = novoPorto().comId("1234").build();
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        assertEquals(portoAntigo,desvio.getPortoDestinoAlterado());
    }
    
    @Test
    public void deveAtualizarOPortoDestinoDoAgenciamentoQuandoSalvarDesvio() {
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("1234").build();
        agenciaemntoService.salvarDesvio(desvio, novoPorto,null);
        assertEquals(novoPorto,desvio.getAgenciamento().getPortoDestino());
    }

    @Test
    public void deveAtualizarDestinoIntermediarioDoDesvioEDoAgenciamentoQuandoSalvarDesvio() {
        //Prepara o teste
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("1234").build();
        
        String destinoIntermediarioDesvio = desvio.getDestinoIntermediarioAlterado();
        String destinoIntermediarioAgenciamento = desvio.getAgenciamento().getDestinoIntermediario();
        agenciaemntoService.salvarDesvio(desvio, novoPorto, destinoIntermediarioDesvio);
        assertEquals(destinoIntermediarioAgenciamento,desvio.getDestinoIntermediarioAlterado());
        assertEquals(destinoIntermediarioDesvio,desvio.getAgenciamento().getDestinoIntermediario());
        
    }
    
    @Test
    public void deveAtualizarStatusDoAgenciamentoParaDesviadoQuandoSalvarDesvio() {
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("1234").build();
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        assertEquals(StatusEmbarcacao.DESVIADO, desvio.getAgenciamento().getStatusEmbarcacao());
    }
    
    @Test
    public void deveSalvarDesvioEAgenciamentoQuandoSalvarDesvio() {
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("1234").build();
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        Mockito.verify(dao, Mockito.times(1)).saveOrUpdate(desvio);
        Mockito.verify(dao, Mockito.times(1)).saveOrUpdate(desvio.getAgenciamento());
    }
    
    @Test
    public void deveCriarPendenciaQuandoSalvarDesvioDeUmAgenciamentoTCP() {
        Desvio desvio = obterDesvioValido();
        desvio.getAgenciamento().setTipoContrato(TipoContrato.TCP);
        Porto novoPorto = novoPorto().comId("1234").build();
        
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        Mockito.verify(pendenciaService, Mockito.times(1)).criarPendencia(desvio.getAgenciamento(), TipoPendencia.DESVIO_ROTA);
    }
    
    @Test
    public void naoDeveCriarPendenciaQuandoSalvarDesvioDeUmAgenciamentoVCP() {
        Desvio desvio = obterDesvioValido();
        desvio.getAgenciamento().setTipoContrato(TipoContrato.VCP);
        Porto novoPorto = novoPorto().comId("1234").build();
        
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        Mockito.verify(pendenciaService, Mockito.times(0)).criarPendencia(desvio.getAgenciamento(), TipoPendencia.DESVIO_ROTA);
    }
    
    @Test
    public void deveAtualizarAChaveENomeDoUsuarioComOUsuarioLogadoQueRealizouODesvio() {
        //Prepara o teste
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("1234").build();
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        agenciaemntoService.salvarDesvio(desvio, novoPorto, null);
        
        assertEquals("STAM", desvio.getChaveUsuario());
        assertEquals("SISTAM", desvio.getNomeUsuario());
    }
  
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Cancelar Agenciamento">
    @Test
    public void deveChamarOsValidadoresQuandoCancelarAgenciamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        Mockito.verify(validadorPermissao).podeCancelarAgenciamento(Mockito.any(Agencia.class));
        Mockito.verify(validadorAgenciamento).validarCancelarAgenciamento(cancelamento, false);
    }
    
    @Test
    public void naoDeveChamarValidadorSituacaoEsperadoQuandoCancelarAgenciamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        Mockito.verify(validadorPermissao).podeCancelarAgenciamento(Mockito.any(Agencia.class));
        Mockito.verify(validadorAgenciamento).validarCancelarAgenciamento(cancelamento, false);
    }
    
    @Test
    public void deveMudarOStatusParaCanceladoQuandoCancelarAgenciamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        Assert.assertTrue(StatusEmbarcacao.CANCELADO.equals(cancelamento.getAgenciamento().getStatusEmbarcacao()));
    }
    
    @Test
    public void deveAtualizarIdDoCancelamentoComODoAgenciamentoQuandoHouverOCancelamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        assertEquals(cancelamento.getAgenciamento().getId(), cancelamento.getId());
    }
    
    @Test
    public void deveAtualizarADataDoCancelamentoQuandoCancelarOAgenciamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        assertEquals(SistamDateUtils.formatShortDate(new Date(), null), SistamDateUtils.formatShortDate(cancelamento.getData(), null));
    }
    
    @Test
    public void deveAtualizarAChaveENomeDoUsuarioComOUsuarioLogadoQueRealizouOCancelamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        assertEquals("STAM", cancelamento.getChaveUsuario());
        assertEquals("SISTAM", cancelamento.getNomeUsuario());
    }
    
    @Test
    public void deveSalvarOAgenciamentoQuandoCancelarAgenciamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        Mockito.verify(dao).saveOrUpdate(cancelamento.getAgenciamento());
    }
    
    @Test
    public void deveSalvarOCancelamentoQuandoCancelarAgenciamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        Mockito.verify(dao).saveOrUpdate(cancelamento);
    }
    
    @Test
    public void deveExcluirAsPendenciasQuandoCancelarAgenciamento() {
        CancelamentoAgenciamento cancelamento = obterCancelamentoValido();
        agenciaemntoService.cancelarAgenciamento(cancelamento);
        
        Mockito.verify(dao).executeDML(Mockito.any(ExcluirPendenciaPorAgenciamento.class));
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Criação de um novo agenciamento">
    @Test
    public void aoCriarUmNovoAgenciamentoOsValidadoresDevemSerChamados() {
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        Mockito.verify(validadorPermissao).podeSalvarAgenciamento(agenciamento.getAgencia());
        Mockito.verify(validadorAgenciamento).validarIniciarAgenciamento(agenciamento);
    }

    @Test
    public void aoCriarUmNovoAgenciamentoOStatusDeveSerEsperado() {
       Agenciamento agenciamento = obterAgenciamentoValido();
       agenciamento = agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
       assertEquals(StatusEmbarcacao.ESPERADO, agenciamento.getStatusEmbarcacao());
    }
    
    @Test
    public void aoCriarUmNovoAgenciamentoASituacaoProcessoDeveSerEmAndamento() {
       Agenciamento agenciamento = obterAgenciamentoValido();
       agenciamento = agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
       assertEquals(SituacaoProcesso.EM_ANDAMENTO, agenciamento.getSituacaoProcesso());
    }
    
     @Test
    public void aoCriarUmNovoAgenciamentoOGeradorProcessoDeveSerChamado() {
        
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        Mockito.verify(geradorProcesso).gerarNumero(agenciamento.getAgencia(), SistamDateUtils.getCurrentYear(null));
    }
    
     @Test
    public void aoCriarUmNovoAgenciamentoOAnoProcessoDeveSerOAnoAtual() {
       Agenciamento agenciamento = obterAgenciamentoValido();
       
       agenciamento = agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
       assertEquals(Integer.valueOf(SistamDateUtils.getCurrentYear(null)), agenciamento.getAnoProcesso());
    }
     
     @Test
    public void aoCriarUmaNovoAgenciamentoONumeroDoProcessoDeveSerGerado() {
        GeradorDeNumeroProcessoAgenciamento gerador = Mockito.mock(GeradorDeNumeroProcessoAgenciamento.class);
        Mockito.when(gerador.gerarNumero(Mockito.any(Agencia.class), Mockito.any(Integer.class))).thenReturn(666L);
        agenciaemntoService.setGeradorProcesso(gerador);

        Agenciamento agenciamento = obterAgenciamentoValido();

        agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        
        assertEquals(Long.valueOf(666), agenciamento.getCodigo());
    }
    
    @Test
    public void aoCriarUmNovoAgenciamentoOCampoEscalasDeveSerCarregado() {
        //Prepara o teste.
        Agenciamento agenciamento = obterAgenciamentoValido();
        
        Escala escala1 = novaEscala()
                .comPorto(novoPorto().comPais("019").comNomeCompleto("MANAUS").build())
                .build();

        Escala escala2 = novaEscala()
                .comPorto(novoPorto().comPais("035").comNomeCompleto("SALVADOR").build())
                .build();

        List<Escala> listaEscala = new ArrayList<Escala>(Arrays.asList(new Escala[]{escala1, escala2}));

        Mockito.when(embarcacaoService.buscarEscalaPorEmbarcacao(Mockito.any(Embarcacao.class), Mockito.any(Date.class)))
                .thenReturn(listaEscala);
        
        agenciamento = agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        String listaEscalaFormatada = "SALVADOR - null -  / MANAUS - null - ";
        
        assertEquals(listaEscalaFormatada, agenciamento.getEscalas());
    }

    @Test
    public void aoCriarUmaNovoAgenciamentoAPendenciaPagamentoLPDeveSerCriada() {
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento = agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.PAGAMENTO_LP);
    }

    @Test
    public void aoCriarUmaNovoAgenciamentoAPendenciaDespachoDeSaidaDeveSerCriada() {
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento = agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.DESPACHO_SAIDA);
    }

    @Test
    public void aoCriarUmaNovoAgenciamentComUmPortoDeOrigemInternacionalAPendenciaPagamentoFunapolDeveSerCriada() {
        Agenciamento agenciamento = obterAgenciamentoValido();
        agenciamento.setPortoOrigem(novoPorto().comPais("011").build());
        
        agenciamento = agenciaemntoService.criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(null));
        Mockito.verify(pendenciaService).criarPendencia(agenciamento, TipoPendencia.PAGAMENTO_FUNAPOL);
    }

    //</editor-fold>
    
    

    //<editor-fold defaultstate="collapsed" desc="Acompanhamento do Agenciamento">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarAcompanhamento() {
        Acompanhamento acompanhamento = obterAcompanhamentoValido();
        agenciaemntoService.salvarAcompanhamento(acompanhamento);
        Mockito.verify(validadorPermissao).podeSalvarAcompanhamento(Mockito.any(Agencia.class));
        Mockito.verify(validadorAgenciamento).validarAcompanhamento(acompanhamento);
    }
    
    @Test
    public void deveSalvarAcompanhamento() {
        Acompanhamento acompanhamento = obterAcompanhamentoValido();
        agenciaemntoService.salvarAcompanhamento(acompanhamento);
        Mockito.verify(dao, Mockito.times(1)).saveOrUpdate(acompanhamento);
    }
    
    
    @Test
    public void deveAtualizarAChaveENomeDoUsuarioComOUsuarioLogadoQueRealizouOAcompanhamento() {
        //Prepara o teste
        Acompanhamento acompanhamento = obterAcompanhamentoValido();
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        agenciaemntoService.salvarAcompanhamento(acompanhamento);
        
        assertEquals("STAM", acompanhamento.getChaveCadastrador());
        assertEquals("SISTAM", acompanhamento.getNomeCadastrador());
    }
    
    
    //</editor-fold>
        
    //<editor-fold defaultstate="collapsed" desc="Atendimento">
    
    @Test
    public void deveChamarOsValidadoresQuandoBuscarOsAtendimentos() {
        FiltroAgenciamentoAtendimento filtro = new FiltroAgenciamentoAtendimento();
        filtro.setAgencia(new Agencia());
        filtro.setDataInicial(new Date());
        filtro.setDataFinal(new Date());
        filtro.setQtdeDiasAtendimento(6);
        
        agenciaemntoService.buscarAgenciamentosRelatorioAtendimento(filtro);
        Mockito.verify(validadorAgenciamento).validarBuscarRelatorioAtendimentos(filtro);
    }
    
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">

    private void carregarValidadorComMock() {
        validadorAgenciamento = Mockito.mock(ValidadorAgenciamento.class);
        Field field = ReflectionUtils.getFieldWithName(AgenciamentoServiceImpl.class, "validador", false);
        ReflectionUtils.setFieldValue(agenciaemntoService, field, validadorAgenciamento);
    }

    private void carregarValidadorPermissaoComMock() {
        validadorPermissao = Mockito.mock(ValidadorPermissao.class);
        Mockito.when(validadorPermissao.podeSalvarAgenciamento(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeCancelarAgenciamento(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarAcompanhamento(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(AgenciamentoServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(agenciaemntoService, field, validadorPermissao);
    }

    private void carregarEmbarcacaoServiceComMock() {
        embarcacaoService = Mockito.mock(EmbarcacaoServiceImpl.class);
        
        Escala escala = novaEscala()
                .comPorto(novoPorto().comPais("019").comNomeCompleto("MANAUS").build())
                .build();
        
        List<Escala> listaEscala = new ArrayList<Escala>(Arrays.asList(new Escala[]{escala}));
        
        Mockito.when(embarcacaoService.buscarEscalaPorEmbarcacao(Mockito.any(Embarcacao.class), Mockito.any(Date.class))).thenReturn(listaEscala);
        agenciaemntoService.setEmbarcacaoService(embarcacaoService);
    }
    
    public Agenciamento obterAgenciamentoValido(){
        Agenciamento agenciamento = novoAgenciamento()
                .comAgencia(novaAgencia().build())
                .comPortoOrigem(novoPorto().comPais("019").build())
                .comEmbarcacao(new Embarcacao())
                .comEta(new Date())
                .comDestinoIntermediario("DESTINO INFORMADO NO AGENCIAMENTO")
                .build();
        return agenciamento;
                
    }

    private Desvio obterDesvioValido() {
        Porto porto = PortoBuilder.novoPorto().comId("123456").build();
        Desvio desvio = DesvioBuilder.novoDesvio()
                .comData(new Date())
                .comMotivo(MotivoDesvio.REABASTECIMENTO)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comId(1234L).comStatusEmbarcacao(StatusEmbarcacao.SAIDO).comPortoDestino(porto).build())
                .comDestinoIntermediarioValido("DESTINO INFORMADO NO DESVIO")
                .build();
        return desvio;
    }

    private Acompanhamento obterAcompanhamentoValido() {
        Acompanhamento acompanhamento = AcompanhamentoBuilder.novoAcompanhamento()
                .comData(new Date())
                .comTexto("Teste")
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comId(1234L).build())
                .build();
        return acompanhamento;
    }
    
    
    public CancelamentoAgenciamento obterCancelamentoValido(){
        Agenciamento agenciamento = novoAgenciamento()
                .comStatusEmbarcacao(StatusEmbarcacao.ESPERADO)
                .comId(1234L)
                
                .build();
        
        CancelamentoAgenciamento cancelamentoAgenciamento = new CancelamentoAgenciamento();
        cancelamentoAgenciamento.setAgenciamento(agenciamento);
        cancelamentoAgenciamento.setMotivo(MotivoCancelamento.OUTROS);
        cancelamentoAgenciamento.setDescricaoMotivo("Cancelando");
        return cancelamentoAgenciamento;
    }

    //</editor-fold>
    
}
