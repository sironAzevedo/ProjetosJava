package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.query.agenciamento.documento.ConsultaDocumentoDoAgenciamentoPorTipo;
import br.com.petrobras.sistam.service.validator.ValidadorDocumento;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentoBuilder;
import br.com.petrobras.sistam.test.builder.ManobraBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;


public class DocumentoServiceTest {
    private DocumentoServiceImpl documentoService;
    private ValidadorPermissao validadorPermissao;
    private ValidadorDocumento validadorDocumento;
    private AcessoServiceImpl acessoService;
    private PendenciaService pendenciaService;
    private GenericDao dao;
    
    @Before
    public void setUp(){
        validadorDocumento = mock(ValidadorDocumento.class);
        validadorPermissao = mock(ValidadorPermissao.class);
        acessoService = mock(AcessoServiceImpl.class);
        pendenciaService = mock(PendenciaService.class);
        dao = mock(GenericDao.class);
        
        documentoService = new DocumentoServiceImpl(dao);
        documentoService.setAcessoService(acessoService);
        documentoService.setPendenciaServie(pendenciaService);
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Documento">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarDocumento() {
        Documento documento = obterDocumentoValido();
        
        documentoService.salvarDocumento(documento);
        Mockito.verify(validadorDocumento).validarSalvarDocumento(documento);
        Mockito.verify(validadorPermissao).podeSalvarDocumento(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOMetodoSalvarDoDao(){
        Documento documento = obterDocumentoValido();
        
        documentoService.salvarDocumento(documento);
        Mockito.verify(dao).saveOrUpdate(documento);
    }
    
    @Test
    public void quandoODocumentoForDaManobraEJaExistirUmDocumentoCriadoParaAManobraNaoPermiteCriarOutro(){
        //Prepara o teste
        Documento outroDocumento = new Documento();
        Documento documento = DocumentoBuilder.novoDocumento(obterDocumentoValido())
                .comId(null)
                .comTipo(TipoDocumento.REGISTRO_MOVIMENTACAO).build();
        documento.setManobra(new Manobra());
        
        Mockito.when(dao.uniqueResult(new ConsultaDocumentoDoAgenciamentoPorTipo(TipoDocumento.REGISTRO_MOVIMENTACAO, Mockito.any(Manobra.class))))
                .thenReturn(outroDocumento);
        
        try{
            documentoService.salvarDocumento(documento);
            Assert.fail();
        }catch(BusinessException ex){
            Assert.assertEquals(ConstantesI18N.DOCUMENTO_JA_EXISTE_DOCUMENTO_DO_MESMO_TIPO, ex.getMessage());
        }
    }
    
    @Test
    public void quandoODocumentoForDaOperacaoEJaExistirUmDocumentoCriadoParaAOperacaoNaoPermiteCriarOutro(){
        //Prepara o teste
        Documento documento = DocumentoBuilder.novoDocumento(obterDocumentoValido())
                .comId(null)
                .comTipo(TipoDocumento.BILL_OF_LADING).build();
        documento.setOperacao(new Operacao());
        
        Mockito.when(dao.uniqueResult(new ConsultaDocumentoDoAgenciamentoPorTipo(TipoDocumento.BILL_OF_LADING, Mockito.any(Operacao.class))))
                .thenReturn(new Documento());
        
        try{
            documentoService.salvarDocumento(documento);
            Assert.fail();
        }catch(BusinessException ex){
            Assert.assertEquals(ConstantesI18N.DOCUMENTO_JA_EXISTE_DOCUMENTO_DO_MESMO_TIPO, ex.getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Exclusão de Documento">
    @Test
    public void deveChamarOsValidadoresQuandoExcluirUmDocumento() {
        Documento documento = obterDocumentoValido();
        
        documentoService.excluirDocumento(documento);
        Mockito.verify(validadorDocumento).validarExcluirDocumento(documento);
        Mockito.verify(validadorPermissao).podeExcluirDocumento(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOMetodoRemoveDoDao(){
        Documento documento = obterDocumentoValido();
        
        documentoService.excluirDocumento(documento);
        Mockito.verify(dao).remove(documento);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Registro da Emissão de Documentos do Agenciamento">
    @Test
    public void deveChamarOsValidadoresQuandoRegistrarAEmissaoDoDocumentoDoAgenciamento() {
        TipoDocumento tipo = TipoDocumento.SOLICITACAO_CERTIFICADO;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Mockito.verify(validadorDocumento).validarRegistrarEmissaoDeDocumento(tipo, agenciamento, representante);
        Mockito.verify(validadorPermissao).podeSalvarDocumento(any(Agencia.class));
    }
    
    @Test
    public void quandoNaoTiverUmDocumentoDoTipoInformadoDoAgenciamentoUmNovoDeveSerCriadoComOAgenciamentoInformado(){
        TipoDocumento tipo = TipoDocumento.SOLICITACAO_CERTIFICADO;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Mockito.when(dao.uniqueResult(Mockito.any(BusinessQuery.class))).thenReturn(null);
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Assert.assertEquals(agenciamento, documento.getAgenciamento());
    }
    
    @Test
    public void quandoNaoTiverUmDocumentoDoAgenciamentoUmNovoDeveSerCriadoParaOTipoInformado(){
        TipoDocumento tipo = TipoDocumento.SOLICITACAO_CERTIFICADO;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Mockito.when(dao.uniqueResult(Mockito.any(BusinessQuery.class))).thenReturn(null);
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Assert.assertEquals(TipoDocumento.SOLICITACAO_CERTIFICADO, documento.getTipoDocumento());
    }
    
    
    @Test
    public void seOTipoDoDocumentoForSolicitacaoDeCertificadoORepresentanteDoDocumentoDeveSerORepresentanteInformado(){
        TipoDocumento tipo = TipoDocumento.SOLICITACAO_CERTIFICADO;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Assert.assertEquals(representante, documento.getRepresentante());
    }
    
    @Test
    public void seOTipoDoDocumentoForComunicacaoDeChegadaORepresentanteDoDocumentoDeveSerORepresentanteInformado(){
        TipoDocumento tipo = TipoDocumento.COMUNICACAO_CHEGADA;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Assert.assertEquals(representante, documento.getRepresentante());
    }
    
    
    @Test
    public void seOTipoDoDocumentoForParteDeEntradaORepresentanteDoDocumentoDeveSerORepresentanteInformado(){
        TipoDocumento tipo = TipoDocumento.PARTE_ENTRADA;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Assert.assertEquals(representante, documento.getRepresentante());
    }
    
    @Test
    public void deveAtualizarODocumentoDoAgenciamentoComAChaveDoUsuarioLogadoQuandoNaoEstiverProtocolado(){
        //Prepara o teste
        TipoDocumento tipo = TipoDocumento.COMUNICACAO_CHEGADA;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Assert.assertEquals("STAM", documento.getChaveEmitente());
    }
    
    @Test
    public void deveAtualizarODocumentoDoAgenciamentoComONomeDoUsuarioLogadoQuandoNaoEstiverProtocolado(){
        //Prepara o teste
        TipoDocumento tipo = TipoDocumento.COMUNICACAO_CHEGADA;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM - Sistema de Agenciamento Marítimo");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Assert.assertEquals("SISTAM - Sistema de Agenciamento Marítimo", documento.getNomeEmitente());
    }
    
    @Test
    public void deveAtualizarODocumentoDoAgenciamentoComADataAtualQuandoNaoEstiverProtocolado(){
        TipoDocumento tipo = TipoDocumento.COMUNICACAO_CHEGADA;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Date dataAtual = SistamDateUtils.truncateTime(new Date(), null);
        Assert.assertTrue( dataAtual.compareTo(SistamDateUtils.truncateTime(documento.getDataEmissao(), null)) == 0);
        
    }
    
    @Test
    public void quandoJaExistirUmDocumentoDoAgenciamentoProtocoladoNaoSeraFeitaNehumaAlteracaoNoDocumento(){
        TipoDocumento tipo = TipoDocumento.SOLICITACAO_CERTIFICADO;
        Agenciamento agenciamento = obterAgenciamentoValido();
        Date dataEmissao = DateBuilder.on(1, 1, 2010).getTime();
        RepresentanteLegal representanteVelho = new RepresentanteLegal();
        RepresentanteLegal representanteNovo = new RepresentanteLegal();
        
        Documento documento = DocumentoBuilder.novoDocumento().comId(5L)
                .comDataProtocolo(new Date())
                .comDataDeEmissao(dataEmissao)
                .comRepresentante(representanteVelho)
                .comNomeEmitente("SISTAM")
                .comChaveEmitente("STAM")
                .build();
        
        Mockito.when(dao.list(Mockito.any(BusinessQuery.class))).thenReturn(Arrays.asList(documento));
        
        Documento documentoAtualizado = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representanteNovo, false);
        
        //O documento deve permanececer com os mesmos valores para seus campos
        Assert.assertTrue(dataEmissao.compareTo(documentoAtualizado.getDataEmissao()) == 0);
        Assert.assertEquals(representanteVelho, documento.getRepresentante());
        Assert.assertEquals("SISTAM", documento.getNomeEmitente());
        Assert.assertEquals("STAM", documento.getChaveEmitente());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateDoDaoAoRegistrarEmissaoDoDocumentoDoAgenciamento(){
        //Prepara o teste
        TipoDocumento tipo = TipoDocumento.COMUNICACAO_CHEGADA;
        Agenciamento agenciamento = obterAgenciamentoValido();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Documento documento = documentoService.registrarEmissaoDeDocumento(tipo, agenciamento, representante, false);
        
        Mockito.verify(dao).saveOrUpdate(documento);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Registro de Emissão de Documentos da Manobra">
    @Test
    public void deveChamarOsValidadoresQuandoRegistrarAEmissaoDoDocumentoDaManobra() {
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Mockito.verify(validadorDocumento).validarRegistrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        Mockito.verify(validadorPermissao).podeSalvarDocumento(any(Agencia.class));
    }
    
    @Test
    public void quandoNaoTiverUmDocumentoDaManobraUmNovoDeveSerCriadoParaAManobraInformada(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Mockito.when(dao.uniqueResult(Mockito.any(BusinessQuery.class))).thenReturn(null);
        
        Documento documento = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Assert.assertEquals(manobra, documento.getManobra());
    }
    
    @Test
    public void quandoNaoTiverUmDocumentoDaManobraUmNovoDeveSerCriadoParaOTipoInformado(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Mockito.when(dao.uniqueResult(Mockito.any(BusinessQuery.class))).thenReturn(null);
        
        Documento documento = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Assert.assertEquals(tipo, documento.getTipoDocumento());
    }
    
    @Test
    public void quandoTiverUmDocumentoDaManobraEsteDeveSerAtualizado(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        Documento documento = DocumentoBuilder.novoDocumento().comId(5L).build();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Mockito.when(dao.uniqueResult(Mockito.any(BusinessQuery.class))).thenReturn(documento);
        
        Documento documentoAtualizado = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Assert.assertEquals(documento, documentoAtualizado);
    }
    
    @Test
    public void deveAtualizarODocumentoDaManobraComAChaveDoUsuarioLogadoQuandoNaoEstiverProtocolado(){
        //Prepara o teste
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        Documento documento = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Assert.assertEquals("STAM", documento.getChaveEmitente());
    }
    
    @Test
    public void deveAtualizarODocumentoDaManobraComONomeDoUsuarioLogadoQuandoNaoEstiverProtocolado(){
       //Prepara o teste
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM - Sistema de Agenciamento Marítimo");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        Documento documento = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Assert.assertEquals("SISTAM - Sistema de Agenciamento Marítimo", documento.getNomeEmitente());
    }
    
    @Test
    public void deveAtualizarODocumentoDaManobraComADataAtualQuandoNaoEstiverProtocolado(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Documento documento = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Date dataAtual = SistamDateUtils.truncateTime(new Date(), null);
        Assert.assertTrue( dataAtual.compareTo(SistamDateUtils.truncateTime(documento.getDataEmissao(), null)) == 0);
        
    }
    
    @Test
    public void deveAtualizarODocumentoDaManobraComORepresentanteInformadoQuandoNaoEstiverProtocolado(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Documento documento = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Assert.assertEquals(representante, documento.getRepresentante());
    }
    
    @Test
    public void quandoJaExistirUmDocumentoDaManobraProtocoladoNaoSeraFeitaNehumaAlteracaoNoDocumento(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        Date dataEmissao = DateBuilder.on(1, 1, 2010).getTime();
        RepresentanteLegal representanteVelho = new RepresentanteLegal();
        RepresentanteLegal representanteNovo = new RepresentanteLegal();
        
        Documento documento = DocumentoBuilder.novoDocumento().comId(5L)
                .comDataProtocolo(new Date())
                .comDataDeEmissao(dataEmissao)
                .comRepresentante(representanteVelho)
                .comNomeEmitente("SISTAM")
                .comChaveEmitente("STAM")
                .build();
        
        Mockito.when(dao.uniqueResult(Mockito.any(BusinessQuery.class))).thenReturn(documento);
        
        Documento documentoAtualizado = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representanteNovo);
        
        //O documento deve permanececer com os mesmos valores para seus campos
        Assert.assertTrue(dataEmissao.compareTo(documentoAtualizado.getDataEmissao()) == 0);
        Assert.assertEquals(representanteVelho, documento.getRepresentante());
        Assert.assertEquals("SISTAM", documento.getNomeEmitente());
        Assert.assertEquals("STAM", documento.getChaveEmitente());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateDoDaoAoRegistrarEmissaoDoDocumentoDaManobra(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = obterManobraValida();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        Documento documento = documentoService.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
        
        Mockito.verify(dao).saveOrUpdate(documento);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Protocolar Documento">
    @Test
    public void deveChamarOsValidadoresQuandoProtocolarUmDocumento() {
        Documento documento = obterDocumentoValido();
        Mockito.when(dao.get(Documento.class, 1L)).thenReturn(new Documento());
        
        documentoService.informarProtocoloDoDocumento(documento);
        
        Mockito.verify(validadorDocumento).validarProtocolarDocumento(documento);
        Mockito.verify(validadorPermissao).podeSalvarDocumento(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOMergeDoDaoAoProtocolarUmDocumento(){
        Documento documento = obterDocumentoValido();
        Mockito.when(dao.get(Documento.class, 1L)).thenReturn(new Documento());
        
        documentoService.informarProtocoloDoDocumento(documento);
        
        Mockito.verify(dao).merge(documento);
    }
    
    @Test
    public void deveChamarOMetodoParaResolverPendenciaDoDocumento(){
        Documento documento = obterDocumentoValido();
        Mockito.when(dao.get(Documento.class, 1L)).thenReturn(new Documento());
        
        documentoService.informarProtocoloDoDocumento(documento);
        
        Mockito.verify(pendenciaService).resolverPendenciaDoDocumento(documento);
    }
    
    @Test
    public void naoDeveChamarOMetodoParaResolverPendenciaDoDocumentoQuandoOAgenciamentoForVCP(){
        Documento documento = obterDocumentoValido();
        documento.getAgenciamento().setTipoContrato(TipoContrato.VCP);
        Mockito.when(dao.get(Documento.class, 1L)).thenReturn(new Documento());
        
        documentoService.informarProtocoloDoDocumento(documento);
        
        Mockito.verify(pendenciaService, Mockito.times(0)).resolverPendenciaDoDocumento(documento);
    }
    
    @Test
    public void seODocumentoJaFoiProtocoladoNaoDeveChamarOMetodoParaResolverPendenciaDoDocumento(){
        //Prepara o teste
        Documento documentoProtocolado = DocumentoBuilder.novoDocumento()
                .comDataProtocolo(DateBuilder.on(30, 12, 2013).getTime()).build();
        Mockito.when(dao.get(Documento.class, 1L)).thenReturn(documentoProtocolado);
        
        Documento documento = obterDocumentoValido();
        documentoService.informarProtocoloDoDocumento(documento);
        
        Mockito.verify(pendenciaService, Mockito.times(0)).resolverPendenciaDoDocumento(documento);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos Privados">
    private Documento obterDocumentoValido(){
        Agencia agencia = AgenciaBuilder.novaAgencia().comTimeZone("America/Bahia").build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comAgencia(agencia).build();
        
        Documento documento = DocumentoBuilder.novoDocumento()
                .comId(1L)
                .comAgenciamento(agenciamento)
                .comDataDeEmissao(DateBuilder.on(1, 12, 2013).getTime())
                .build();
        
        return documento;
    }
    
    private Agenciamento obterAgenciamentoValido(){
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento()
                .comEmbarcacao(new Embarcacao())
                .comAgencia(new Agencia()).build();
        
        return agenciamento;
    }
    
     private Manobra obterManobraValida(){
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(obterAgenciamentoValido())
                .build();
        
        return manobra;
    }
    
    
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(DocumentoServiceImpl.class, "validadorDocumento", false);
        ReflectionUtils.setFieldValue(documentoService, field, validadorDocumento);
    }
    
    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarDocumento(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeExcluirDocumento(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(DocumentoServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(documentoService, field, validadorPermissao);
    }
    
    //</editor-fold>
    
    
    
}
