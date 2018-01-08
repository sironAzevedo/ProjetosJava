package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.validator.ValidadorTaxa;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.TaxaBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;


public class TaxaServiceTest {
    private TaxaServiceImpl taxaService;
    private AcessoServiceImpl acessoService;
    private ValidadorPermissao validadorPermissao = mock(ValidadorPermissao.class);
    private ValidadorTaxa  validador = mock(ValidadorTaxa.class);;
    private PendenciaService pendenciaService = mock(PendenciaService.class);
    
    @Before
    public void setUp(){
        acessoService = mock(AcessoServiceImpl.class);
        taxaService = new TaxaServiceImpl();
        taxaService.setAcessoService(acessoService);
        taxaService.setDao(Mockito.mock(GenericDao.class));
        taxaService.setPendenciaServie(pendenciaService);
        
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Taxa">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarTaxa() {
        Taxa taxa = TaxaBuilder.novaTaxa()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(AgenciaBuilder.novaAgencia().comId(2L).comTimeZone("America/Bahia").build()).build())
                .comDataPagamento(new Date())
                .build();
        taxaService.salvarTaxa(taxa);
        Mockito.verify(validador).validarSalvarTaxa(taxa);
        Mockito.verify(validadorPermissao).podeSalvarTaxa(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOMetodoParaResolverPendenciaDaTaxa(){
        Taxa taxa = TaxaBuilder.novaTaxa()
                .doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA)
                .comDataPagamento(new Date())
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(AgenciaBuilder.novaAgencia().comId(2L).comTimeZone("America/Bahia").build()).build())
                .build();
        
        taxaService.salvarTaxa(taxa);
        
        Mockito.verify(pendenciaService).resolverPendenciaDaTaxa(taxa);
    }
    
     @Test
    public void naoDeveChamarOMetodoParaResolverPendenciaDaTaxaQuandoOAgenciamentoForVCP(){
        Taxa taxa = TaxaBuilder.novaTaxa()
                .doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA)
                .comDataPagamento(new Date())
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comTipoContrato(TipoContrato.VCP).comAgencia(AgenciaBuilder.novaAgencia().comId(2L).comTimeZone("America/Bahia").build()).build())
                .build();
        
        taxaService.salvarTaxa(taxa);
        
        Mockito.verify(pendenciaService, Mockito.times(0)).resolverPendenciaDaTaxa(taxa);
    }
    
    @Test
    public void seNaoForUmaNovaTaxaNaoDeveChamarOMetodoParaResolverPendenciaDaTaxa(){
        //Prepara o teste
        Taxa taxa = TaxaBuilder.novaTaxa()
                .comId(1L)
                .doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA)
                .comDataPagamento(new Date())
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(AgenciaBuilder.novaAgencia().comId(2L).comTimeZone("America/Bahia").build()).build())
                .build();
        
        taxaService.salvarTaxa(taxa);
        
        Mockito.verify(pendenciaService,Mockito.times(0)).resolverPendenciaDaTaxa(taxa);
    }
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarTaxa(){
        Taxa taxa = TaxaBuilder.novaTaxa()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(AgenciaBuilder.novaAgencia().comId(2L).comTimeZone("America/Bahia").build()).build())
                .comDataPagamento(new Date())
                .build();
        taxaService.salvarTaxa(taxa);
        Mockito.verify(taxaService.getDao()).saveOrUpdate(taxa);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Excluir Taxa">
    @Test
    public void deveChamarOsValidadoresQuandoExcluirTaxa() {
        Taxa taxa = TaxaBuilder.novaTaxa()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        taxaService.excluirTaxa(taxa);
        Mockito.verify(validadorPermissao).podeSalvarTaxa(any(Agencia.class));
    }
    
    @Test
    public void deveChamarORemoverResolucaoDaPendenciaQuandoExcluirATaxa(){
        Taxa taxa = TaxaBuilder.novaTaxa()
                .doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        taxaService.excluirTaxa(taxa);
        Mockito.verify(pendenciaService).removerResolucaoDaPendenciaDaTaxa(taxa);
    }
    
    @Test
    public void deveChamarORemoveDoDaoQuandoSExluirTaxa(){
        Taxa taxa = TaxaBuilder.novaTaxa()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        taxaService.excluirTaxa(taxa);
        Mockito.verify(taxaService.getDao()).remove(taxa);
    }
    
    //</editor-fold>
    
    @Test
    public void deveChamarOsValidadoresQuandoBuscarTaxasValorAcumulado() {
        FiltroTaxa filtro = new FiltroTaxa();
        taxaService.buscarTaxasValorAcumulado(filtro);
        Mockito.verify(validador).validarBuscarTaxasValorAcumulado(filtro);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(TaxaServiceImpl.class, "validadorTaxa", false);
        ReflectionUtils.setFieldValue(taxaService, field, validador);
    }
    
    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarTaxa(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(TaxaServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(taxaService, field, validadorPermissao);
    }
    
    //</editor-fold>
    
    
    
}
