package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Transporte;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioVisita;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.validator.ValidadorVisita;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.TransporteBuilder;
import br.com.petrobras.sistam.test.builder.VisitaBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;


public class VisitaServiceTest {
    private VisitaServiceImpl visitaService;
    private AcessoServiceImpl acessoService;
    private ValidadorPermissao validadorPermissao = mock(ValidadorPermissao.class);;
    private ValidadorVisita validador = mock(ValidadorVisita.class);
    private GenericDao dao;
    
    @Before
    public void setUp(){
        dao = mock(GenericDao.class);
        acessoService = mock(AcessoServiceImpl.class);
        visitaService = new VisitaServiceImpl(dao);
        visitaService.setAcessoService(acessoService);
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
        
        SistamCredentialsBean credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);
        
        
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Visita">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarVisita() {
        Visita visita = VisitaBuilder.novaVisita()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        visitaService.salvarVisita(visita);
        Mockito.verify(validador).validarSalvarVisita(visita);
        Mockito.verify(validadorPermissao).podeSalvarVisita(any(Agencia.class));
    }
    
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarVisita(){
        Visita visita = VisitaBuilder.novaVisita()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        visitaService.salvarVisita(visita);
        Mockito.verify(dao).saveOrUpdate(visita);
    }
    
    @Test
    public void deveSalvarTransporteQuandoSalvarNovaVisita(){
        Visita visita = VisitaBuilder.novaVisita()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        Transporte transporte = obterTransporteValido();
        visita.adicionarTransporte(transporte);
        visitaService.salvarVisita(visita);
        Mockito.verify(validador).validarSalvarTransporte(transporte);
        Mockito.verify(dao).saveOrUpdate(transporte);
        Mockito.verify(dao).saveOrUpdate(visita);
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoExcluirVisita() {
        Visita visita = VisitaBuilder.novaVisita()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        visitaService.excluirVisita(visita);
        Mockito.verify(validadorPermissao).podeSalvarVisita(any(Agencia.class));
    }
    
    @Test
    public void deveChamarORemoveDoDaoQuandoExluirVisita(){
        Visita visita = VisitaBuilder.novaVisita()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        visitaService.excluirVisita(visita);
        Mockito.verify(dao).remove(visita);
    }
    
    @Test
    public void deveExcluirTransportesQuandoExluirVisita(){
        Visita visita = VisitaBuilder.novaVisita()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .build();
        Transporte transporte = obterTransporteValido();
        visita.adicionarTransporte(transporte);
        visitaService.excluirVisita(visita);
        Mockito.verify(dao).remove(transporte);
        Mockito.verify(dao).remove(visita);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Relatorio de Visitas">
    @Test
    public void deveChamarBuscaTransportesPorFiltroRelatorio() {
        FiltroRelatorioVisita filtro = mock(FiltroRelatorioVisita.class);
        filtro.setAgencia(mock(Agencia.class));
        Mockito.when(visitaService.buscarTransportesPorFiltroRelatorio(filtro)).thenReturn(Collections.EMPTY_LIST);
        visitaService.buscarTransportesUtilizadosNaVisita(filtro);
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Transporte">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarTransporte() {
        Transporte transporte = obterTransporteValido();
        visitaService.salvarTransporte(transporte);
        Mockito.verify(validador).validarSalvarTransporte(transporte);
        Mockito.verify(validadorPermissao).podeSalvarTransporte(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarTransporte(){
        Transporte transporte = obterTransporteValido();
        visitaService.salvarTransporte(transporte);
        Mockito.verify(dao).saveOrUpdate(transporte);
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoExcluirTransporte() {
        Transporte transporte = obterTransporteValido();
        visitaService.excluirTransporte(transporte);
        Mockito.verify(validadorPermissao).podeSalvarTransporte(any(Agencia.class));
    }

    
    @Test
    public void deveChamarORemoveDoDaoQuandoExluirTransporte(){
        Transporte transporte = obterTransporteValido();
        visitaService.excluirTransporte(transporte);
        Mockito.verify(dao).remove(transporte);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(VisitaServiceImpl.class, "validadorVisita", false);
        ReflectionUtils.setFieldValue(visitaService, field, validador);
    }
    
    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarVisita(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarTransporte(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(VisitaServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(visitaService, field, validadorPermissao);
    }
    
    private Transporte obterTransporteValido() {
        Transporte transporte = TransporteBuilder.novoTransporte()
                .comId(1L)
                .daVisita(VisitaBuilder.novaVisita().doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build()).build())
                .comTipoTransporte(TipoTransporte.MARITIMO)
                .comResponsavelCusto(ResponsavelCustoBuilder.novoResponsavelCusto().build())
                .comCusto(1000D)
                .build();
        return transporte;
    }
    
    
    //</editor-fold>
    
    
    
}
