package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.validator.ValidadorManobra;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.sistam.test.builder.ManobraBuilder;
import br.com.petrobras.sistam.test.builder.PontoAtracacaoBuilder;
import br.com.petrobras.sistam.test.builder.PontoOperacionalBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoManobraBuilder;
import br.com.petrobras.sistam.test.builder.ServicoResponsavelBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.unitils.util.ReflectionUtils;

public class ManobraServiceTest {
    private ManobraServiceImpl manobraService;
    private GenericDao dao;
    private ValidadorManobra  validador;
    private ValidadorPermissao validadorPermissao;
    private PendenciaServiceImpl pendenciaService;
    private NotesWebServiceImpl notesWebService;


    @Before
    public void setup() {
        dao = mock(GenericDao.class);
        validador = mock(ValidadorManobra.class);
        validadorPermissao = mock(ValidadorPermissao.class);
        pendenciaService = mock(PendenciaServiceImpl.class);
        notesWebService = mock(NotesWebServiceImpl.class);
        
        manobraService = new ManobraServiceImpl();
        manobraService.setDao(dao);
        manobraService.setPendenciaService(pendenciaService);
        manobraService.setNotesWebService(notesWebService);
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoForSalvarAManobra() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        manobraService.salvarManobra(manobra);
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarCamposObrigatoriosManobra(manobra);
        verify(validador).validarSalvarManobra(manobra);
    }

    @Test
    public void deveChamarODaoParaSalvarManobra() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        manobraService.salvarManobra(manobra);
        verify(dao).saveOrUpdate(manobra);
    }

    @Test
    public void deveAlterarOStatusDaManobraParaCriadaQuandoOStatusForNulo() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        manobraService.salvarManobra(manobra);
        assertEquals(StatusManobra.PLANEJADA, manobra.getStatus());
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoForSolicitarServicoManobra() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        ServicoManobra servicoManobra = ServicoManobraBuilder.novoServicoManobra()
                .daManobra(manobra)
                .build();
        manobra.adicionarServico(servicoManobra);
        
        manobraService.solicitarServicoManobra(manobra);
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validadorPermissao).podeEnviarSolicitacaoApoioManobra(any(Agencia.class));
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Registro de Manobra">
    @Test
    public void deveChamarOsValidadoresQuandoRegistrarManobra() {
        Manobra manobra = obterManobraValida();
        
        manobraService.registrarManobra(manobra);
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarCamposObrigatoriosManobra(manobra);
        verify(validador).validarRegistrarManobra(any(Manobra.class));
    }
    
    @Test
    public void deveAlterarOStatusDaManobraParaRegistradaQuandoRegistrarManobra() {
        Manobra manobra = obterManobraValida();
        
        manobraService.registrarManobra(manobra);
        assertEquals(StatusManobra.REGISTRADA, manobra.getStatus());
    }
    
    @Test
    public void deveChamarSalvarManobraQuandoRegistrarManobra() {
        Manobra manobra = obterManobraValida();
        
        manobraService.registrarManobra(manobra);
        verify(dao, times(1)).saveOrUpdate(manobra);
    }
    
    @Test
    public void deveChamaOValidadorDeServicoManobraParaTodosOsServicosQuandoForRegistroDeUmaNovaManobra(){
        Manobra manobra = ManobraBuilder.novaManobra(obterManobraValida())
                .comId(null)
                .build();
        
        ServicoManobra servico01 = obterServicoManobraValido();
        ServicoManobra servico02 = obterServicoManobraValido();
        
        manobra.adicionarServico(servico01);
        manobra.adicionarServico(servico02);
        
        manobraService.registrarManobra(manobra);
         
         verify(validador).validarSalvarServicoManobra(servico01);
         verify(validador).validarSalvarServicoManobra(servico02);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Cancelar Manobra Fora de Prazo">
    @Test
    public void deveChamarOsValidadoresQuandoForCancelarManobraForaDoPrazo() {
        Manobra manobra = obterManobraValida();
        
        manobraService.cancelarManobraForaDoProazo(manobra, "Motivo de teste");
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarCamposObrigatoriosManobra(manobra);
        verify(validador).validarCancelarManobraForaDoPrazo(manobra, "Motivo de teste");
    }
    
    @Test
    public void deveAlterarStatusDaManobraQuandoCancelarManobraForaDoPrazo() {
        Manobra manobra = obterManobraValida();
        
        manobraService.cancelarManobraForaDoProazo(manobra, "Motivo de teste");
        assertEquals(StatusManobra.CANCELADA_FORA_PRAZO, manobra.getStatus());
    }
    
    @Test
    public void deveAlterarOMovivoDoCancelamentoQuandoCancelarManobraForaDoPrazo() {
        Manobra manobra = obterManobraValida();
        
        manobraService.cancelarManobraForaDoProazo(manobra, "Motivo de teste");
        assertEquals("Motivo de teste", manobra.getMotivoDoCancelamento());
    }
    
    @Test
    public void deveSalvarAManobraQuandoCancelarManobraForaDoPrazo() {
        Manobra manobra = obterManobraValida();
        
        manobraService.cancelarManobraForaDoProazo(manobra, "Motivo de teste");
        verify(dao, times(1)).saveOrUpdate(manobra);
    }
    
    
     @Test
    public void deveChamaOValidadorDeServicoManobraParaTodosOsServicosQuandoForCancelarForaDoPrazoUmaNovaManobra(){
        Manobra manobra = ManobraBuilder.novaManobra(obterManobraValida())
                .comId(null)
                .build();
        
        ServicoManobra servico01 = obterServicoManobraValido();
        ServicoManobra servico02 = obterServicoManobraValido();
        
        manobra.adicionarServico(servico01);
        manobra.adicionarServico(servico02);
        
        manobraService.cancelarManobraForaDoProazo(manobra, "Motivo");
         
         verify(validador).validarSalvarServicoManobra(servico01);
         verify(validador).validarSalvarServicoManobra(servico02);
    }
     
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Encerramento de Manobra">
    @Test
    public void deveChamarOsValidadoresAoEncerrarUmaManobra(){
        Manobra manobra = obterManobraValida();
        
        manobraService.encerrarManobra(manobra, null, null);
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarEncerrarManobra(manobra, null, null);
    }
    
    @Test
    public void aManobraDeveFicarComOStatusEncerrado(){
        Manobra manobra = obterManobraValida();
        manobra.setStatus(StatusManobra.REGISTRADA);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(StatusManobra.ENCERRADA, manobra.getStatus());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateParaAManobra(){
        Manobra manobra = obterManobraValida();
        manobra = manobraService.encerrarManobra(manobra, null, null);
        verify(dao).saveOrUpdate(manobra);
    }
    
    @Test
    public void oAgenciamentoDeveFicarComOStatusDaEmbarcacaoDeAcordoComAFinalidadeDaManobraQuandoNaoForShipToShip() {
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.FUNDEIO_ENTRADA);
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);

        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(StatusEmbarcacao.FUNDEADO_NA_ENTRADA, manobra.getAgenciamento().getStatusEmbarcacao());
    }
    
    @Test
    public void quandoAFinalidadeForShipToShipOStatusDaEmbarcacaoDoAgenciamentoNaoDeveSerAlterado() {
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.SHIP_TO_SHIP);
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);

        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(StatusEmbarcacao.ESPERADO, manobra.getAgenciamento().getStatusEmbarcacao());
    }
    @Test
    public void quandoAFinalidadeForNavegacaoOStatusDaEmbarcacaoDoAgenciamentoNaoDeveSerAlterado() {
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.NAVEGACAO);
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);

        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(StatusEmbarcacao.ESPERADO, manobra.getAgenciamento().getStatusEmbarcacao());
    }
    
    @Test
    public void quandoAFinalidadeForTrocaDePortoADataDeSaidaDoAgenciamentoDeveSerADataDePartidaDaManobra(){
        Date dataPartida = DateBuilder.on(10, 6, 2013).getTime();
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        manobra.setDataPartidaOrigem(dataPartida);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertTrue(dataPartida.compareTo(manobra.getAgenciamento().getDataSaida()) == 0);
    }
   
    @Test
    public void quandoAFinalidadeForDesatracacaoSaidaADataDeSaidaDoAgenciamentoDeveSerADataDePartidaDaManobra(){
        Date dataPartida = DateBuilder.on(10, 6, 2013).getTime();
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        manobra.setDataPartidaOrigem(dataPartida);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertTrue(dataPartida.compareTo(manobra.getAgenciamento().getDataSaida()) == 0);
    }
    
    @Test
    public void quandoAFinalidadeForSaidaFundeioADataDeSaidaDoAgenciamentoDeveSerADataDePartidaDaManobra(){
        Date dataPartida = DateBuilder.on(10, 6, 2013).getTime();
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        manobra.setDataPartidaOrigem(dataPartida);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertTrue(dataPartida.compareTo(manobra.getAgenciamento().getDataSaida()) == 0);
    }
    
    
    @Test
    public void quandoAFinalidadeForTrocaDePortoEtaProximoPortoDoAgenciamentoDeveSerADataDeChegadaDaManobra(){
        Date dataChegada = DateBuilder.on(10, 6, 2013).getTime();
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        manobra.setDataChegadaDestino(dataChegada);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertTrue(dataChegada.compareTo(manobra.getAgenciamento().getEtaProximoPorto()) == 0);
    }
    
    @Test
    public void quandoAFinalidadeForTrocaDePortoOPortoDestinoDoAgenciamentoDeveSerOPortoDoPontoFinalDaManobra(){
        Porto porto = PortoBuilder.novoPorto().comId("SALV").build();
        PontoOperacional pontoOperacional = PontoOperacionalBuilder.novoPontoOperacional().doPorto(porto).build();
        PontoAtracacao pontoFinal = PontoAtracacaoBuilder.novoPontoAtracacao().doPontoOperacional(pontoOperacional).build();
               
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        manobra.setPontoAtracacaoDestino(pontoFinal);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(porto, manobra.getAgenciamento().getPortoDestino());
    }
    
    @Test
    public void quandoAFinalidadeForDesatracacaoSaidaOCaladoDeSaidaAVanteDoAgenciamentoDeveSerOCaladoAVanteDaManobra(){
        Double caladoAVante = 15.5D; 
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        manobra.setCaladoVante(caladoAVante);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(caladoAVante, manobra.getAgenciamento().getCaladoSaidaVante());
    }
    
    @Test
    public void quandoAFinalidadeForSaidaFundeioOCaladoDeSaidaAVanteDoAgenciamentoDeveSerOCaladoAVanteDaManobra(){
        Double caladoAVante = 15.5D; 
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        manobra.setCaladoVante(caladoAVante);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(caladoAVante, manobra.getAgenciamento().getCaladoSaidaVante());
    }
    
     @Test
    public void quandoAFinalidadeForTrocaDePortoOCaladoDeSaidaAVanteDoAgenciamentoDeveSerOCaladoAVanteDaManobra(){
        Double caladoAVante = 15.5D; 
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        manobra.setCaladoVante(caladoAVante);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(caladoAVante, manobra.getAgenciamento().getCaladoSaidaVante());
    }
    
    @Test
    public void quandoAFinalidadeForTrocaDePortoOCaladoDeSaidaAReDoAgenciamentoDeveSerOCaladoAReDaManobra(){
        Double caladoARe = 30.5D; 
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        manobra.setCaladoRe(caladoARe);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(caladoARe, manobra.getAgenciamento().getCaladoSaidaRe());
    }
    
    @Test
    public void quandoAFinalidadeForDesatracacaoSaidaOCaladoDeSaidaAReDoAgenciamentoDeveSerOCaladoAReDaManobra(){
        Double caladoARe = 30.5D; 
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        manobra.setCaladoRe(caladoARe);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(caladoARe, manobra.getAgenciamento().getCaladoSaidaRe());
    }
    
    @Test
    public void quandoAFinalidadeForSaidaFundeioOCaladoDeSaidaAReDoAgenciamentoDeveSerOCaladoAReDaManobra(){
        Double caladoARe = 30.5D; 
        
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        manobra.setCaladoRe(caladoARe);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertEquals(caladoARe, manobra.getAgenciamento().getCaladoSaidaRe());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateParaOAgenciamento(){
        Manobra manobra = obterManobraValida();
        manobra = manobraService.encerrarManobra(manobra, null, null);
        verify(dao).saveOrUpdate(manobra.getAgenciamento());
    }
    
    @Test
    public void naoDeveAtualizarOAgenciamentoQuandoOStatusDoAgenciamentoForSaido(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        manobra.setDataChegadaDestino(DateBuilder.on(10, 6, 2013).getTime());
        manobra.setCaladoVante(15D);
        manobra.setCaladoRe(30.5D);
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.SAIDO);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertNull(manobra.getAgenciamento().getEtaProximoPorto());
        assertNull(manobra.getAgenciamento().getCaladoSaidaVante());
        assertNull(manobra.getAgenciamento().getCaladoSaidaRe());
    }
    
    @Test
    public void naoDeveAtualizarOAgenciamentoQuandoOStatusDoAgenciamentoForDesviado(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        manobra.setDataChegadaDestino(DateBuilder.on(10, 6, 2013).getTime());
        manobra.setCaladoVante(15D);
        manobra.setCaladoRe(30.5D);
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.DESVIADO);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertNull(manobra.getAgenciamento().getEtaProximoPorto());
        assertNull(manobra.getAgenciamento().getCaladoSaidaVante());
        assertNull(manobra.getAgenciamento().getCaladoSaidaRe());
    }
    
    @Test
    public void naoDeveAtualizarOAgenciamentoQuandoOStatusDoAgenciamentoForCancelado(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        manobra.setDataChegadaDestino(DateBuilder.on(10, 6, 2013).getTime());
        manobra.setCaladoVante(15D);
        manobra.setCaladoRe(30.5D);
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.CANCELADO);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        assertNull(manobra.getAgenciamento().getEtaProximoPorto());
        assertNull(manobra.getAgenciamento().getCaladoSaidaVante());
        assertNull(manobra.getAgenciamento().getCaladoSaidaRe());
    }
    
    @Test
    public void deveGerarAPendenciaRegistroDeMovimentacaoQuandoAFinalidadeForDiferenteDeDesatracacaoParaSaidaESaidaDeFundeio(){
        Manobra manobra = obterManobraValida();
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService).criarPendencia(manobra.getAgenciamento(), TipoPendencia.REGISTRO_MOVIMENTACAO);
    }
    
    @Test
    public void naoDeveGerarAPendenciaQuandoOAgenciamengoForVCPMesnoAFinalidadeSendoDiferenteDeDesatracacaoParaSaidaESaidaDeFundeio(){
        Manobra manobra = obterManobraValida();
        manobra.getAgenciamento().setTipoContrato(TipoContrato.VCP);
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService, times(0)).criarPendencia(manobra.getAgenciamento(), TipoPendencia.REGISTRO_MOVIMENTACAO);
    }
    
    @Test
    public void naoDeveGerarAPendenciaRegistroDeMovimentacaoQuandoStatusDaEmbarcacaoDoAgenciamentoForSaido(){
        Manobra manobra = obterManobraValida();
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.SAIDO);
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService, times(0)).criarPendencia(manobra.getAgenciamento(), TipoPendencia.REGISTRO_MOVIMENTACAO);
    }
    
    @Test
    public void naoDeveGerarAPendenciaRegistroDeMovimentacaoQuandoStatusDaEmbarcacaoDoAgenciamentoForDesviado(){
        Manobra manobra = obterManobraValida();
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.DESVIADO);
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService, times(0)).criarPendencia(manobra.getAgenciamento(), TipoPendencia.REGISTRO_MOVIMENTACAO);
    }
     
     
    @Test
    public void naoDeveGerarAPendenciaRegistroDeMovimentacaoQuandoStatusDaEmbarcacaoDoAgenciamentoForCancelado(){
        Manobra manobra = obterManobraValida();
        manobra.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.CANCELADO);
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService, times(0)).criarPendencia(manobra.getAgenciamento(), TipoPendencia.REGISTRO_MOVIMENTACAO);
    }
    
    @Test
    public void quandoAFinalidadeForDesatracacaoSaidaOPortoDestinoDoAgenciamentoDeveSerOPortoDestinoInformado(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        
        Porto portoDestino = PortoBuilder.novoPorto().comId("SALV").build();
        
        manobra = manobraService.encerrarManobra(manobra, portoDestino, null);
        
        assertEquals(portoDestino, manobra.getAgenciamento().getPortoDestino());
    }
    
    
    @Test
    public void quandoAFinalidadeForSaidaDeFundeioOPortoDestinoDoAgenciamentoDeveSerOPortoDestinoInformado(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        
        Porto portoDestino = PortoBuilder.novoPorto().comId("SALV").build();
        
        manobra = manobraService.encerrarManobra(manobra, portoDestino, null);
        
        assertEquals(portoDestino, manobra.getAgenciamento().getPortoDestino());
    }
    
    @Test
    public void quandoAFinalidadeForDesatracacaoSaidaOEtaProximoPOrtoDoAgenciamentoDeveSerOEtaInformado(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        Date eta = DateBuilder.on(1,1,2013).getTime();
        
        manobra = manobraService.encerrarManobra(manobra, null, eta);
        
        assertTrue(eta.compareTo(manobra.getAgenciamento().getEtaProximoPorto()) == 0);
    }
    
    @Test
    public void quandoAFinalidadeForSaidaFundeioOEtaProximoPOrtoDoAgenciamentoDeveSerOEtaInformado(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        Date eta = DateBuilder.on(1,1,2013).getTime();
        
        manobra = manobraService.encerrarManobra(manobra, null, eta);
        
        assertTrue(eta.compareTo(manobra.getAgenciamento().getEtaProximoPorto()) == 0);
    }
    
    @Test
    public void deveGerarAPendenciaParteDeSaidaQuandoAFinalidadeForDesatracacaoParaSaida(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService).criarPendencia(manobra.getAgenciamento(), TipoPendencia.PARTE_SAIDA);
    }
    
    @Test
    public void deveGerarAPendenciaParteDeSaidaQuandoAFinalidadeForSaidaFundeio(){
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService).criarPendencia(manobra.getAgenciamento(), TipoPendencia.PARTE_SAIDA);
    }
    
    /*@Test
    public void deveGerarAPendenciaDocumentoOperacaoCargaCabotagemQuandoAFinalidadeForDesatracacaoParaSaidaEOAgenciamentoPossuiOperacaoCarga(){
        OperacaoProduto produto = OperacaoProdutoBuilder.novaOperacaoProduto().comTipoOperacao(TipoOperacao.CARGA_CABOTAGEM).build();
        Operacao operacao = OperacaoBuilder.novaOperacao().comProdutos(produto).build();
        
        Manobra manobra = obterManobraValida();
        manobra.getAgenciamento().adicionarOperacao(operacao);
        manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService).criarPendencia(manobra.getAgenciamento(), TipoPendencia.DOCUMENTACAO_OPERACAO_CARGA_CABOTAGEM);
    }
    
    @Test
    public void deveGerarAPendenciaDocumentoOperacaoCargaCabotagemQuandoAFinalidadeForSaidaFundeioEOAgenciamentoPossuiOperacaoCarga(){
        OperacaoProduto produto = OperacaoProdutoBuilder.novaOperacaoProduto().comTipoOperacao(TipoOperacao.CARGA_CABOTAGEM).build();
        Operacao operacao = OperacaoBuilder.novaOperacao().comProdutos(produto).build();
        
        Manobra manobra = obterManobraValida();
        manobra.getAgenciamento().adicionarOperacao(operacao);
        manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        
        manobra = manobraService.encerrarManobra(manobra, null, null);
        
        verify(pendenciaService).criarPendencia(manobra.getAgenciamento(), TipoPendencia.DOCUMENTACAO_OPERACAO_CARGA_CABOTAGEM);
    }*/
    
     
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serviço Manobra">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarServicoManobra() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        
        manobraService.salvarServicoManobra(servicoManobra);
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarSalvarServicoManobra(servicoManobra);
    }
     
    @Test
    public void deveChamarSalvarServicoManobraQuandoSalvarServicoManobra() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        
        manobraService.salvarServicoManobra(servicoManobra);
        verify(dao, times(1)).saveOrUpdate(servicoManobra);
    }
    
    @Test
    public void deveChamaOValidadorDoResponsavelParaTodosOsResponsaveisQuandoForSalvarUmNovoServico(){
        ServicoManobra servico = ServicoManobraBuilder.novoServicoManobra(obterServicoManobraValido())
                .comId(null)
                .build();
        
        ServicoResponsavel responsavel01 = obterServicoResponsavelValido();
        ServicoResponsavel responsavel02 = obterServicoResponsavelValido();
        
        servico.adicionarResponsavel(responsavel01);
        servico.adicionarResponsavel(responsavel02);
        
        manobraService.salvarServicoManobra(servico);
         
         verify(validador).validarSalvarResponsavel(responsavel01);
         verify(validador).validarSalvarResponsavel(responsavel02);
    }
    
    
    @Test
    public void deveChamarOsValidadoresQuandoForExcluirServicoManobra() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        ServicoManobra servicoManobra = ServicoManobraBuilder.novoServicoManobra()
                .daManobra(manobra)
                .build();
        when(dao.get(ServicoManobra.class, servicoManobra.getId())).thenReturn(servicoManobra);
        manobraService.excluirServicoManobra(servicoManobra);
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarExclusaoServicoManobra(servicoManobra);
    }
    
     @Test
    public void deveExcluirOServicoManobraEOsResponsaveis() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        
        ServicoManobra servicoManobra = ServicoManobraBuilder.novoServicoManobra()
                .daManobra(manobra)
                .build();
        
        ServicoResponsavel responsavelA = new ServicoResponsavel();
        ServicoResponsavel responsavelB = new ServicoResponsavel();
        
        servicoManobra.adicionarResponsavel(responsavelA);
        servicoManobra.adicionarResponsavel(responsavelB);
        
        when(dao.get(ServicoManobra.class, servicoManobra.getId())).thenReturn(servicoManobra);
        
        manobraService.excluirServicoManobra(servicoManobra);
        verify(dao).remove(responsavelA);
        verify(dao).remove(responsavelB);
        verify(dao).remove(servicoManobra);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Responsável pelo Serviço">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarServicoResponsavel() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        
        manobraService.salvarResponsavel(servicoResponsavel);
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarSalvarResponsavel(servicoResponsavel);
    }
    
    @Test
    public void deveChamarSalvarServicoResponsavelQuandoSalvarServicoResponsavel() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        
        manobraService.salvarResponsavel(servicoResponsavel);
        verify(dao, times(1)).saveOrUpdate(servicoResponsavel);
    }
    
    @Test
    public void deveChamarOValidadorPermissaoQuandoExcluirServicoResponsavel() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        
        manobraService.excluirResponsavel(servicoResponsavel);
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarExclusaoDeResponsavel(servicoResponsavel);
    }
    
    @Test
    public void deveChamarDaoParaExcluirQuandoExcluirServicoResponsavel() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        
        manobraService.excluirResponsavel(servicoResponsavel);
        verify(dao, times(1)).remove(servicoResponsavel);
    }
    
    //</editor-fold>
    
    
    @Test
    public void deveChamarOsValidadoresQuandoCancelarManobraDentroPrazo() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        
        manobraService.cancelarManobraDentroPrazo(manobra, "TESTE");
        verify(validadorPermissao).podeSalvarManobra(any(Agencia.class));
        verify(validador).validarCancelarManobraDentroPrazo(manobra, "TESTE");
    }
    
    @Test
    public void deveAlterarStatusParaCanceladaDentroPrazoQuandoCancelarManobraDentroPrazo() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        
        manobraService.cancelarManobraDentroPrazo(manobra, "TESTE");
        assertEquals(StatusManobra.CANCELADA, manobra.getStatus());
    }
    
    
    private Manobra obterManobraValida() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento()
                .comEmbarcacao(new Embarcacao())
                .comAgencia(AgenciaBuilder.novaAgencia().build()).build();
        
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(agenciamento)
                .comResponsavelCusto(ResponsavelCustoBuilder.novoResponsavelCusto().build())
                .comStatus(StatusManobra.SOLICITADA)
                .comCaladoVante(12D)
                .comCaladoRe(12D)
                .comPontoAtracacaoOrigem(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comPontoAtracacaoDestino(PontoAtracacaoBuilder.novoPontoAtracacao().doPontoOperacional(PontoOperacionalBuilder.novoPontoOperacional().doPorto(PortoBuilder.novoPorto().comId("GFR").comNomeCompleto("TESTE P").build()).build()).build())
                .comFinalidadeManobra(FinalidadeManobra.ATRACACAO)
                .comDataInicio(new Date())
                .comDataTermino(new Date())
                .comDataPartidaOrigem(new Date())
                .comDataChegadaDestino(new Date())
                .build();
        return manobra;
    }
    
    private ServicoManobra obterServicoManobraValido() {
        ServicoManobra servicoManobra = ServicoManobraBuilder.novoServicoManobra()
                .comId(1L)
                .comTipoServico(TipoServico.PRATICOS)
                .comDataProgramada(new Date())
                .daManobra(obterManobraValida())
                .daEmpresa(EmpresaMaritimaBuilder.novaEmpresaMaritima().comId(12L).comCnpj("1324").build())
                .build();
        return servicoManobra;
    }
    
    private ServicoResponsavel obterServicoResponsavelValido() {
        ServicoResponsavel servicoResponsavel = ServicoResponsavelBuilder.novoServicoResponsavel()
                .doServicoManobra(obterServicoManobraValido())
                .comServico(ServicoBuilder.novoServico().comId(1L).build())
                .comDataInicio(new Date())
                .comDataTermino(new Date())
                .build();
        return servicoResponsavel;
    }
    
  

    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(ManobraServiceImpl.class, "validador", false);
        ReflectionUtils.setFieldValue(manobraService, field, validador);
    }
    
    private void carregarValidadorPermissaoComMock() {
        when(validadorPermissao.podeSalvarManobra(any(Agencia.class))).thenReturn(true);
        when(validadorPermissao.podeEnviarSolicitacaoApoioManobra(any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(ManobraServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(manobraService, field, validadorPermissao);
    }
    
}
