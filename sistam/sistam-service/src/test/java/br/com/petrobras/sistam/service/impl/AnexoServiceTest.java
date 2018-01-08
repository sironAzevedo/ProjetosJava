package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.PastaAnexo;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.validator.ValidadorAnexo;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.AnexoBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;


public class AnexoServiceTest {
    private AnexoServiceImpl anexoService;
    private ValidadorPermissao validadorPermissao;
    private ValidadorAnexo validadorAnexo;
    private AcessoServiceImpl acessoService;
    private GenericDao dao;
    
    @Before
    public void setUp(){
        validadorAnexo = mock(ValidadorAnexo.class);
        validadorPermissao = mock(ValidadorPermissao.class);
        acessoService = mock(AcessoServiceImpl.class);
        dao = mock(GenericDao.class);
        
        anexoService = new AnexoServiceImpl(dao);
        anexoService.setAcessoService(acessoService);
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Anexo">
    @Test
    public void naoSeraPossivelSalvarAnexosPassandoUmaListaVazia(){
        try{
            anexoService.salvarAnexos(new ArrayList<Anexo>());
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.ANEXO_LISTA_VAZIO, ex.getMessage());
        }
    }
    @Test
    public void deveChamarOsValidadoresQuandoSalvarOsAnexos() {
        Anexo anexo1 = obterAnexoValido();
        Anexo anexo2 = AnexoBuilder.novoAnexo(obterAnexoValido()).comId(10L).build();
        
        List<Anexo> listaAnexo = new ArrayList<Anexo>();
        listaAnexo.add(anexo1);
        listaAnexo.add(anexo2);
        
        
        anexoService.salvarAnexos(listaAnexo);
        Mockito.verify(validadorAnexo).validarSalvarAnexo(anexo1);
        Mockito.verify(validadorAnexo).validarSalvarAnexo(anexo2);
        Mockito.verify(validadorPermissao).podeSalvarAnexo(Mockito.any(Agencia.class));
    }
    
    @Test
    public void aoSalvarOsAnexosADataDeCriacaoDeveSerADataAtual(){
        Anexo anexo = obterAnexoValido();
        List<Anexo> listaAnexo = new ArrayList<Anexo>();
        listaAnexo.add(anexo);
        
        listaAnexo = anexoService.salvarAnexos(listaAnexo);
        
        Date dataAtual = SistamDateUtils.truncateTime(new Date(), null);
        assertTrue(dataAtual.compareTo(SistamDateUtils.truncateTime(listaAnexo.get(0).getDataDeCriacao(), null)) == 0);
        
    }
    
    @Test
    public void aoSalvarOsAnexosDeveAtualizarAChaveDoUsuarioComAChaveDoUsuarioLogado(){
        //Prepara o teste
        Anexo anexo = obterAnexoValido();
        List<Anexo> listaAnexo = new ArrayList<Anexo>();
        listaAnexo.add(anexo);
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        listaAnexo = anexoService.salvarAnexos(listaAnexo);
        
        assertEquals("STAM", listaAnexo.get(0).getChaveUsuario());
    }
    
    @Test
    public void aoSalvarOsAnexosDeveAtualizarONomeDoUsuarioComONomeDoUsuarioLogado(){
        //Prepara o teste
        Anexo anexo = obterAnexoValido();
        List<Anexo> listaAnexo = new ArrayList<Anexo>();
        listaAnexo.add(anexo);
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        listaAnexo = anexoService.salvarAnexos(listaAnexo);
        
        assertEquals("SISTAM", listaAnexo.get(0).getNomeUsuario());
    }
    
    
    @Test
    public void deveChamarOSaveOrUpdateDoDaoAoSalvarOsAnexos() {
        Anexo anexo = obterAnexoValido();
        List<Anexo> listaAnexo = new ArrayList<Anexo>();
        listaAnexo.add(anexo);
        
        anexoService.salvarAnexos(listaAnexo);
        
        Mockito.verify(dao).saveOrUpdate(anexo);
    }
    
    @Test
    public void deveAtualizarOIdDoArquivoComOMesmoIdDoAnexo(){
        //Prepara o teste
        Anexo anexo = obterAnexoValido();
        List<Anexo> listaAnexo = new ArrayList<Anexo>();
        listaAnexo.add(anexo);
        
        listaAnexo =anexoService.salvarAnexos(listaAnexo);
        
        assertEquals(Long.valueOf(55), listaAnexo.get(0).getArquivo().getId());
        
    }
    @Test
    public void deveChamarOSaveOrUpdateDoDaoParaOsArquivosDosAnexos() {
        //Prepara o teste
        Anexo anexo = obterAnexoValido();
        List<Anexo> listaAnexo = new ArrayList<Anexo>();
        listaAnexo.add(anexo);
        
        Arquivo arquivo = anexo.getArquivo();
        
        anexoService.salvarAnexos(listaAnexo);
        
        Mockito.verify(dao).saveOrUpdate(arquivo);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private Anexo obterAnexoValido(){
        Anexo anexo = AnexoBuilder.novoAnexo()
                .comId(55L)
                .comAgenciamento(obterAgenciamentoValido())
                .comExtensao("pdf")
                .comNome("Comprovante.pdf")
                .comTamanhoEmBytes(1024L)
                .comPasta(PastaAnexo.SUPRIMENTOS)
                .comArquivo(new Arquivo())
                .build();
        
        return anexo;
    }
    
    private Agenciamento obterAgenciamentoValido(){
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento()
                .comEmbarcacao(new Embarcacao())
                .comAgencia(new Agencia()).build();
        
        return agenciamento;
    }
    
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(AnexoServiceImpl.class, "validadorAnexo", false);
        ReflectionUtils.setFieldValue(anexoService, field, validadorAnexo);
    }
    
    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarAnexo(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(AnexoServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(anexoService, field, validadorPermissao);
    }
    
    //</editor-fold>
    
    
    
}
