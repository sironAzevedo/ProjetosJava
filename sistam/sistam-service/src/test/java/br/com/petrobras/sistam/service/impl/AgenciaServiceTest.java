package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.validator.ValidadorAgencia;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciaContatoBuilder;
import br.com.petrobras.sistam.test.builder.AgenciaOrgaoDespachoBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;


public class AgenciaServiceTest {
    private AgenciaServiceImpl agenciaService;
    private ValidadorPermissao validadorPermissao = mock(ValidadorPermissao.class);;
    private ValidadorAgencia validador = mock(ValidadorAgencia.class);
    private GenericDao dao;
    
    @Before
    public void setUp(){
        dao = mock(GenericDao.class);
        agenciaService = new AgenciaServiceImpl();
        agenciaService.setDao(dao);
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Agencia">
    @Test
    public void deveChamarOsValidadoresQuandoIncluirAgencia() {
        Agencia agencia = AgenciaBuilder.novaAgencia()
                .build();
        agenciaService.salvarAgencia(agencia);
        Mockito.verify(validador).validarSalvarAgencia(agencia);
        Mockito.verify(validadorPermissao).podeIncluirAgencia();
    }

    @Test
    public void deveChamarOsValidadoresQuandoSalvarAgencia() {
        Agencia agencia = AgenciaBuilder.novaAgencia()
                .comId(1L)
                .build();
        agenciaService.salvarAgencia(agencia);
        Mockito.verify(validador).validarSalvarAgencia(agencia);
        Mockito.verify(validadorPermissao).podeSalvarAgencia(Mockito.any(Agencia.class));
    }
    
    
    
    @Test
    public void deveChamarOMergeQuandoSalvarAgencia(){
        Agencia agencia = AgenciaBuilder.novaAgencia()
                .build();
        agenciaService.salvarAgencia(agencia);
        Mockito.verify(dao).merge(agencia);
    }
    
    //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Contato da Agencia">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarContatoDaAgencia() {
        RepresentanteLegal contato = AgenciaContatoBuilder.novaAgenciaContato()
                .daAgencia(AgenciaBuilder.novaAgencia().build())
                .build();
        agenciaService.salvarRepresentanteLegal(contato);
        Mockito.verify(validador).validarSalvarAgenciaContato(contato);
        Mockito.verify(validadorPermissao).podeSalvarAgenciaContato(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarContatoDaAgencia(){
        RepresentanteLegal contato = AgenciaContatoBuilder.novaAgenciaContato()
                .daAgencia(AgenciaBuilder.novaAgencia().build())
                .build();
        agenciaService.salvarRepresentanteLegal(contato);
        Mockito.verify(dao).saveOrUpdate(contato);
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoExcluirContatoDaAgencia() {
        RepresentanteLegal contato = AgenciaContatoBuilder.novaAgenciaContato()
                .daAgencia(AgenciaBuilder.novaAgencia().build())
                .build();
        agenciaService.excluirAgenciaContato(contato);
        Mockito.verify(validadorPermissao).podeSalvarAgenciaContato(any(Agencia.class));
    }
    
    @Test
    public void deveChamarORemoveDoDaoQuandoExluirContatoDaAgencia(){
        RepresentanteLegal contato = AgenciaContatoBuilder.novaAgenciaContato()
                .daAgencia(AgenciaBuilder.novaAgencia().build())
                .build();
        agenciaService.excluirAgenciaContato(contato);
        Mockito.verify(dao).remove(contato);
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="Orgao Despacho da Agencia">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarOrgaoDespachoDaAgencia() {
        AgenciaOrgaoDespacho orgao = AgenciaOrgaoDespachoBuilder.novaAgenciaOrgaoDespacho()
                .daAgencia(AgenciaBuilder.novaAgencia().build())
                .build();
        agenciaService.salvarAgenciaOrgaoDespacho(orgao);
        Mockito.verify(validador).validarSalvarAgenciaOrgaoDespacho(orgao);
        Mockito.verify(validadorPermissao).podeSalvarAgenciaOrgaoDespacho(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarOrgaoDespachoDaAgencia(){
        AgenciaOrgaoDespacho orgao = AgenciaOrgaoDespachoBuilder.novaAgenciaOrgaoDespacho()
                .daAgencia(AgenciaBuilder.novaAgencia().build())
                .build();
        agenciaService.salvarAgenciaOrgaoDespacho(orgao);
        Mockito.verify(dao).saveOrUpdate(orgao);
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoExcluirOrgaoDespachoDaAgencia() {
        AgenciaOrgaoDespacho orgao = AgenciaOrgaoDespachoBuilder.novaAgenciaOrgaoDespacho()
                .daAgencia(AgenciaBuilder.novaAgencia().build())
                .build();
        agenciaService.excluirAgenciaOrgaoDespacho(orgao);
        Mockito.verify(validadorPermissao).podeSalvarAgenciaOrgaoDespacho(any(Agencia.class));
    }
    
    @Test
    public void deveChamarORemoveDoDaoQuandoExluirOrgaoDespachoDaAgencia(){
        AgenciaOrgaoDespacho orgao = AgenciaOrgaoDespachoBuilder.novaAgenciaOrgaoDespacho()
                .daAgencia(AgenciaBuilder.novaAgencia().build())
                .build();
        agenciaService.excluirAgenciaOrgaoDespacho(orgao);
        Mockito.verify(dao).remove(orgao);
    }
    //</editor-fold>
        
    @Test
    public void deveChamarOsValidadoresQuandoSalvarUmDestinatario(){
        Destinatario destinatario = new Destinatario();
        destinatario.setAgencia(new Agencia());
        
        agenciaService.salvarDestinatario(destinatario);
        Mockito.verify(validadorPermissao).podeSalvarAgencia(any(Agencia.class));
        Mockito.verify(validador).validarSalvarDestinatario(destinatario);
    }
    
    @Test
    public void deveChamarOSaveOrUpdateDoDaoAoSalvarUmDestinatario(){
        Destinatario destinatario = new Destinatario();
        destinatario.setAgencia(new Agencia());
        
        agenciaService.salvarDestinatario(destinatario);
        Mockito.verify(dao).saveOrUpdate(destinatario);
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(AgenciaServiceImpl.class, "validadorAgencia", false);
        ReflectionUtils.setFieldValue(agenciaService, field, validador);
    }
    
    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarAgencia(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeIncluirAgencia()).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarAgenciaContato(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarAgenciaOrgaoDespacho(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(AgenciaServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(agenciaService, field, validadorPermissao);
    }
    
    //</editor-fold>
       
    
}
