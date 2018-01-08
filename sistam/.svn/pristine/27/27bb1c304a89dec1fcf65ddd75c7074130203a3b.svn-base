package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaPendenciasNaoResolvidasDoAgenciamentoPorTipo;
import br.com.petrobras.sistam.service.query.agenciamento.pendencia.ConsultaPendenciasDaTaxa;
import br.com.petrobras.sistam.service.validator.ValidadorPendencia;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import static br.com.petrobras.sistam.test.builder.AgenciamentoBuilder.novoAgenciamento;
import br.com.petrobras.sistam.test.builder.DocumentoBuilder;
import br.com.petrobras.sistam.test.builder.TaxaBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.unitils.util.ReflectionUtils;


public class PendenciaServiceTest {
    private PendenciaServiceImpl service;
    private GenericDao dao;
    private ValidadorPendencia validador;
    private ValidadorPermissao validadorPermissao;
    private AcessoServiceImpl acessoService;
    private SistamCredentialsBean credentialsBean;
    
    @Before
    public void setUp(){        
        dao = Mockito.mock(GenericDao.class);
        acessoService = Mockito.mock(AcessoServiceImpl.class);
        
        service = new PendenciaServiceImpl(dao);
        service.setAcessoService(acessoService);
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
        
        credentialsBean = Mockito.mock(SistamCredentialsBean.class);
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Criação de pendências">
    @Test
    public void aoCriarUmaPendenciaOValidadorDeveSerChamado(){
        Agenciamento agenciamento = novoAgenciamento().build();
        TipoPendencia tipo = TipoPendencia.CABOTAGEM_PASSE_SAIDA;
        
        service.criarPendencia(agenciamento, tipo);
        
        Mockito.verify(validador).validarCriarPendencia(agenciamento, tipo);
    }
    
    @Test
    public void aoCriarUmaPendenciaOSaveOrUpdateDoDaoDeveSerChamado(){
        Agenciamento agenciamento = novoAgenciamento().build();
        TipoPendencia tipo = TipoPendencia.CABOTAGEM_PASSE_SAIDA;
        
        service.criarPendencia(agenciamento, tipo);
        
        Mockito.verify(dao).saveOrUpdate(Mockito.any(Pendencia.class));
    }
    
    @Test
    public void aoCriarUmaPendenciaAPendenciaDevePertencerAoAgenciamentoInformado(){
        Agenciamento agenciamento = novoAgenciamento().comId(1L).build();
        TipoPendencia tipo = TipoPendencia.CABOTAGEM_PASSE_SAIDA;
        
        Pendencia pendencia = service.criarPendencia(agenciamento, tipo);
        assertEquals(agenciamento, pendencia.getAgenciamento());
    }
    
    @Test
    public void aoCriarUmaPendenciaAPendenciaDeveSerDoTipoInformado(){
        Agenciamento agenciamento = novoAgenciamento().comId(1L).build();
        TipoPendencia tipo = TipoPendencia.CABOTAGEM_PASSE_SAIDA;
        
        Pendencia pendencia = service.criarPendencia(agenciamento, tipo);
        assertEquals(TipoPendencia.CABOTAGEM_PASSE_SAIDA, pendencia.getTipoPendencia());
    }
    
    @Test
    public void aoCriarUmaPendenciaAPendenciaAPendenciaDeveEstarComoNaoResolvida(){
        Agenciamento agenciamento = novoAgenciamento().comId(1L).build();
        TipoPendencia tipo = TipoPendencia.CABOTAGEM_PASSE_SAIDA;
        
        Pendencia pendencia = service.criarPendencia(agenciamento, tipo);
        assertEquals(false, pendencia.isResolvida());
    }
    
    //</editor-fold>
       
    //<editor-fold defaultstate="collapsed" desc="Resolução de pendência manual">
    @Test
    public void naResolucaoDaPendenciaManualOValidadorDeveSerChamado(){
        Pendencia pendenciaUm = new Pendencia();
        pendenciaUm.setTipoPendencia(TipoPendencia.PAGAMENTO_LP);
        
        service.resolverPendenciaManual(pendenciaUm);
        Mockito.verify(validador).validarResolucaoDaPendenciaManual(pendenciaUm);
    }
    
    
    @Test
    public void naResolucaoDaPendenciaManualAPendenciaDeveFicarComoResolvida() {
        Pendencia pendencia = new Pendencia();
        pendencia.setResolvida(Boolean.FALSE);

        pendencia = service.resolverPendenciaManual(pendencia);
        assertEquals(Boolean.TRUE, pendencia.isResolvida());
    }
    
    @Test
    public void naResolucaoDaPendenciaOSaveOrUpdateDeveSerChamado(){
        Pendencia pendencia = new Pendencia();
        pendencia.setResolvida(Boolean.FALSE);
        
        service.resolverPendenciaManual(pendencia);
        Mockito.verify(dao).saveOrUpdate(pendencia);
    }
    
    @Test
    public void naResolucaoDaPendenciaManualDeveAtaualiarONomeDoresponsavelComONomeDoUsuarioLogado(){
        Pendencia pendencia = new Pendencia();
        
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        
        pendencia = service.resolverPendenciaManual(pendencia);
        assertEquals("SISTAM", pendencia.getNomeResposavel());
    }
    
    @Test
    public void naResolucaoDaPendenciaManualDeveAtaualiarAChaveDoresponsavelComAChaveDoUsuarioLogado(){
        Pendencia pendencia = new Pendencia();
        
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        
        pendencia = service.resolverPendenciaManual(pendencia);
        assertEquals("STAM", pendencia.getChaveResponsavel());
    }
    
    @Test
    public void naResolucaoDaPendenciaManualDeveAtaualiarADataDeSolucaoComADataAtual(){
        Pendencia pendencia = new Pendencia();
        
        pendencia = service.resolverPendenciaManual(pendencia);
        
        Date dataAtual = SistamDateUtils.truncateTime(new Date(), null);
        assertTrue(dataAtual.compareTo(SistamDateUtils.truncateTime(pendencia.getDataSolucao(), null)) == 0);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Resolução de Pendências do Documento">
    @Test
    public void naResolucaoDaPendenciaDoDocumentoOValidadorDeveSerChamado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PARTE_ENTRADA);
        
        Documento documento = DocumentoBuilder.novoDocumento().comTipo(TipoDocumento.PARTE_SAIDA).build();
        service.resolverPendenciaDoDocumento(documento);
        Mockito.verify(validador).validarResolucaoDaPendenciaDoDocumento(documento);
    }
    
    @Test
    public void aPendenciaDoDocumentoDeveFicarComoResolvidaAposSuaResolucao(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PARTE_ENTRADA);
        
        Documento documento = DocumentoBuilder.novoDocumento().comTipo(TipoDocumento.PARTE_SAIDA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDoDocumento(documento);
        
        assertTrue(pendenciaResolvida.isResolvida());
        
    }
    
    @Test
    public void naResolucaoDaPendenciaDoDocumentoOSavOrUpdateDoDaoDeveSerChamado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PARTE_ENTRADA);
        
        Documento documento = DocumentoBuilder.novoDocumento().comTipo(TipoDocumento.PARTE_SAIDA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDoDocumento(documento);
        
        Mockito.verify(dao).saveOrUpdate(pendenciaResolvida);
    }
    
    @Test
    public void naResolucaoDaPendenciaDoDocumentoDeveAtaualiarONomeDoresponsavelComONomeDoUsuarioLogado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PARTE_ENTRADA);
        
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        
        Documento documento = DocumentoBuilder.novoDocumento().comTipo(TipoDocumento.PARTE_SAIDA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDoDocumento(documento);
        
        assertEquals("SISTAM", pendenciaResolvida.getNomeResposavel());
    }
    
    @Test
    public void naResolucaoDaPendenciaDoDocumentoDeveAtaualiarAChaveDoresponsavelComAChaveDoUsuarioLogado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PARTE_ENTRADA);
        
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        
        Documento documento = DocumentoBuilder.novoDocumento().comTipo(TipoDocumento.PARTE_SAIDA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDoDocumento(documento);
        
        assertEquals("STAM", pendenciaResolvida.getChaveResponsavel());
    }
    
    @Test
    public void naResolucaoDaPendenciaDoDocumentoDeveAtaualiarADataComoSendoADataAtual(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PARTE_ENTRADA);
        
        Documento documento = DocumentoBuilder.novoDocumento().comTipo(TipoDocumento.PARTE_SAIDA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDoDocumento(documento);
        
        Date dataAtual = SistamDateUtils.truncateTime(new Date(), null);
        assertTrue(dataAtual.compareTo(SistamDateUtils.truncateTime(pendenciaResolvida.getDataSolucao(), null)) == 0);
    }
    
    @Test
    public void naResolucaoDaPendenciaDoDocumentoDeveAtaualiarODocumentoComODocumentoInformado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PARTE_ENTRADA);
        
        Documento documento = DocumentoBuilder.novoDocumento().comTipo(TipoDocumento.PARTE_SAIDA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDoDocumento(documento);
        
        assertEquals(documento, pendenciaResolvida.getDocumento());
    }
    
    @Test
    public void quandoODocumentoNaoTiverPendenciaParaSerResolvidaDeveRetornarNulo(){
        Documento documento = DocumentoBuilder.novoDocumento().comTipo(TipoDocumento.BILL_OF_LADING).build();
        Pendencia pendencia = service.resolverPendenciaDoDocumento(documento);
        assertNull(pendencia);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Resolução de Pendências da Taxa">
    @Test
    public void naResolucaoDaPendenciaDaTaxaOValidadorDeveSerChamado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PAGAMENTO_LP);
        
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA).build();
        service.resolverPendenciaDaTaxa(taxa);
        Mockito.verify(validador).validarResolucaoDaPendenciaDaTaxa(taxa);
    }
    
    @Test
    public void aPendenciaDaTaxaDeveFicarComoResolvidaAposSuaResolucao(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PAGAMENTO_LP);
        
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDaTaxa(taxa);
        
        assertTrue(pendenciaResolvida.isResolvida());
        
    }
    
    @Test
    public void naResolucaoDaPendenciaDaTaxaOSavOrUpdateDoDaoDeveSerChamado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PAGAMENTO_LP);
        
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDaTaxa(taxa);
        
        Mockito.verify(dao).saveOrUpdate(pendenciaResolvida);
    }
    
    @Test
    public void naResolucaoDaPendenciaDaTaxaDeveAtaualiarONomeDoresponsavelComONomeDoUsuarioLogado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PAGAMENTO_LP);
        
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDaTaxa(taxa);
        
        assertEquals("SISTAM", pendenciaResolvida.getNomeResposavel());
    }
    
    @Test
    public void naResolucaoDaPendenciaDaTaxaDeveAtaualiarAChaveDoresponsavelComAChaveDoUsuarioLogado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PAGAMENTO_LP);
        
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDaTaxa(taxa);
        
        assertEquals("STAM", pendenciaResolvida.getChaveResponsavel());
    }
    
    @Test
    public void naResolucaoDaPendenciaDaTaxaDeveAtaualiarADataComoSendoADataAtual(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PAGAMENTO_LP);
        
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDaTaxa(taxa);
        
        Date dataAtual = SistamDateUtils.truncateTime(new Date(), null);
        assertTrue(dataAtual.compareTo(SistamDateUtils.truncateTime(pendenciaResolvida.getDataSolucao(), null)) == 0);
    }
    
    @Test
    public void naResolucaoDaPendenciaDaTaxaDeveAtaualiarODocumentoComODocumentoInformado(){
        //Prepara o teste
        preparaTestesDeResolucaoDePendencia(TipoPendencia.PAGAMENTO_LP);
        
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA).build();
        Pendencia pendenciaResolvida = service.resolverPendenciaDaTaxa(taxa);
        
        assertEquals(taxa, pendenciaResolvida.getTaxa());
    }
    
    @Test
    public void quandoATaxaNaoTiverPendenciaParaSerResolvidaDeveRetornarNulo(){
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.AIT_CTS_CAPITANIA).build();
        Pendencia pendencia = service.resolverPendenciaDaTaxa(taxa);
        assertNull(pendencia);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Remoção da Resolução de Pendências da Taxa">
    @Test
    public void naRemocaoDaResolucaoDaPendenciaDaTaxaOValidadorDeveSerChamado(){
        Taxa taxa = TaxaBuilder.novaTaxa().build();
        service.removerResolucaoDaPendenciaDaTaxa(taxa);
        Mockito.verify(validador).validarRemocaoDaResolucaoDaPendenciaDaTaxa(taxa);
    }
    
    @Test
    public void naRemocaoDaResolucaoDaPendenciaDaTaxaOSaveOrUpdateDoDaoDeveSerChamado(){
        //Prepara o teste
        Pendencia pendencia = new Pendencia();
        Mockito.when(dao.uniqueResult(Mockito.any(ConsultaPendenciasDaTaxa.class))).thenReturn(pendencia);
        
        Taxa taxa = TaxaBuilder.novaTaxa().build();
        pendencia = service.removerResolucaoDaPendenciaDaTaxa(taxa);
        
        Mockito.verify(dao).saveOrUpdate(pendencia);
    }
    
    @Test
    public void naRemocaoDaResolucaoDaPendenciaOSaveOrUpdateDoDaoDeveSerChamado(){
        //Prepara o teste
        Pendencia pendencia = new Pendencia();
        Mockito.when(dao.uniqueResult(Mockito.any(ConsultaPendenciasDaTaxa.class))).thenReturn(pendencia);
        
        pendencia = service.removerResolucaoDaPendencia(pendencia);
        
        Mockito.verify(dao).saveOrUpdate(pendencia);
    }
    
    @Test
    public void quandoUmaTaxaForExcluidaEExistirUmPendenciaResolvidaParaEssaTaxaAPendenciaDeveFicarComoNaoResolvida(){
        //Prepara o teste
        Taxa taxa = TaxaBuilder.novaTaxa().build();
        
        Pendencia pendencia = new Pendencia();
        pendencia.setTaxa(taxa);
        pendencia.setChaveResponsavel("STAM");
        pendencia.setNomeResposavel("SISTAM");
        pendencia.setDataSolucao(DateBuilder.on(30, 12, 2013).getTime());
        pendencia.setResolvida(true);
        
        Mockito.when(dao.uniqueResult(Mockito.any(ConsultaPendenciasDaTaxa.class))).thenReturn(pendencia);
        
        Pendencia pendenciaNaoResolvida = service.removerResolucaoDaPendenciaDaTaxa(taxa);
        
        assertNull(pendenciaNaoResolvida.getTaxa());
        assertNull(pendenciaNaoResolvida.getNomeResposavel());
        assertNull(pendenciaNaoResolvida.getChaveResponsavel());
        assertNull(pendenciaNaoResolvida.getDataSolucao());
        assertFalse(pendenciaNaoResolvida.isResolvida());
    }
    
    @Test
    public void quandoATaxaNaoTiverPendenciaResolvidaDeveRetornarNulo(){
        Mockito.when(dao.uniqueResult(Mockito.any(ConsultaPendenciasDaTaxa.class))).thenReturn(null);
        
        Taxa taxa = TaxaBuilder.novaTaxa().doTipo(TipoTaxa.AIT_CTS_CAPITANIA).build();
        Pendencia pendencia = service.resolverPendenciaDaTaxa(taxa);
        assertNull(pendencia);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Exclusão de Pendência">
    @Test
    public void naExclusaoDaPendenciOValidadorDeveSerChamado(){
        Pendencia pendencia = obterPendenciaValida();
        
        service.excluirPendencia(pendencia);
        Mockito.verify(validador).validarExclusaoDaPendencia(pendencia);
        Mockito.verify(validadorPermissao).podeExcluirPendencia(Mockito.any(Agencia.class));
    }
    
    @Test
    public void naExclusaoDaPendenciaORemoveDoDaoDeveSerChamado(){
         Pendencia pendencia = obterPendenciaValida();
        
        service.excluirPendencia(pendencia);
        Mockito.verify(dao).remove(pendencia);
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock(){
        validador = Mockito.mock(ValidadorPendencia.class);
        Field field = ReflectionUtils.getFieldWithName(PendenciaServiceImpl.class, "validador", false);
        ReflectionUtils.setFieldValue(service, field, validador);
    }
    
      private void carregarValidadorPermissaoComMock() {
        validadorPermissao = Mockito.mock(ValidadorPermissao.class);
        Mockito.when(validadorPermissao.podeExcluirPendencia(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(PendenciaServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(service, field, validadorPermissao);
    }
    
    private void preparaTestesDeResolucaoDePendencia(TipoPendencia tipo){
        Pendencia pendencia = new Pendencia();
        pendencia.setResolvida(false);
        pendencia.setTipoPendencia(tipo);
        
        List<Pendencia> listaPendencia = new ArrayList<Pendencia>();
        listaPendencia.add(pendencia);
        
        Mockito.when(dao.list(new ConsultaPendenciasNaoResolvidasDoAgenciamentoPorTipo(Mockito.any(Agenciamento.class), tipo)))
               .thenReturn(listaPendencia);
        
    }
    private Pendencia obterPendenciaValida(){
        Pendencia pendencia = new Pendencia();
        pendencia.setAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build());
        pendencia.setTipoPendencia(TipoPendencia.PAGAMENTO_LP);
        return pendencia;
    }
    
    //</editor-fold>
}
