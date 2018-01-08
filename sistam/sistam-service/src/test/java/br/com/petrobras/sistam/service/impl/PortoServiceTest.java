package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.validator.ValidadorPorto;
import br.com.petrobras.sistam.test.builder.PontoAtracacaoBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;


public class PortoServiceTest {
    private PortoServiceImpl portoService;
    private AgenciaServiceImpl agenciaService;
    private ValidadorPermissao validadorPermissao ;
    private ValidadorPorto validador;
    private GenericDao dao;
    
    @Before
    public void setUp(){
        validadorPermissao = mock(ValidadorPermissao.class);
        validador = mock(ValidadorPorto.class);
        agenciaService = mock(AgenciaServiceImpl.class);
        dao = mock(GenericDao.class);
        portoService = new PortoServiceImpl();
        portoService.setDao(dao);
        portoService.setAgenciaService(agenciaService);
        
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Ponto de Atracação">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarOPontoDeAtracacao() {
        PontoAtracacao pontoAtracacao = obterPontoAtracacaoValido();
        portoService.salvarPontoAtracacao(pontoAtracacao);
        
        Mockito.verify(validador).validarSalvarPontoAtracacao(pontoAtracacao);
        Mockito.verify(validadorPermissao).podeSalvarPorto(any(List.class));
    }
    
    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarOPontoDeAtracacao() {
        PontoAtracacao pontoAtracacao = obterPontoAtracacaoValido();
        portoService.salvarPontoAtracacao(pontoAtracacao);
        
        Mockito.verify(dao).saveOrUpdate(pontoAtracacao);
    }
    
    
    //</editor-fold>
    
    @Test
    public void deveChamarOsValidadoresQuandoSalvarAAgenciaPorto() {
        AgenciaPorto agenciaPorto = obterAgenciaPortoValido();
        portoService.salvarAgenciaPorto(agenciaPorto);
        
        Mockito.verify(validador).validarSalvarAgenciaPorto(agenciaPorto);
        Mockito.verify(validadorPermissao).podeSalvarAgenciaPorto(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOMergeQuandoSalvarAAgenciaPorto() {
        AgenciaPorto agenciaPorto = obterAgenciaPortoValido();
        portoService.salvarAgenciaPorto(agenciaPorto);
        
        Mockito.verify(dao).merge(agenciaPorto);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Porto Complemento">
    @Test
    public void deveValidarCamposQuandoSalvarPortoComplemento() {
        Mockito.when(validadorPermissao.podeSalvarPortoComplemento()).thenReturn(true);
        PortoComplemento complemento = obterPortoComplementoValido();
        portoService.salvarPortoComplemento(complemento);
        Mockito.verify(validador).validarSalvarPortoComplemento(complemento);
    }
    
    @Test
    public void deveChamarSaveOrUpdateQuandoSalvarPortoComplemento() {
        Mockito.when(validadorPermissao.podeSalvarPortoComplemento()).thenReturn(true);
        PortoComplemento complemento = obterPortoComplementoValido();
        portoService.salvarPortoComplemento(complemento);
        Mockito.verify(dao).saveOrUpdate(complemento);
    }
    
    @Test
    public void deveChamarRemoveQuandoExcluirPortoComplemento() {
        Mockito.when(validadorPermissao.podeSalvarPortoComplemento()).thenReturn(true);
        PortoComplemento complemento = obterPortoComplementoValido();
        portoService.excluirPortoComplemento(complemento);
        Mockito.verify(dao).remove(complemento);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(PortoServiceImpl.class, "validadorPorto", false);
        ReflectionUtils.setFieldValue(portoService, field, validador);
    }
    
    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarPorto(Mockito.any(List.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarAgenciaPorto(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(PortoServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(portoService, field, validadorPermissao);
    }
    
    private PontoAtracacao obterPontoAtracacaoValido() {
        PontoAtracacao pontoAtracacao = PontoAtracacaoBuilder.novoPontoAtracacao()
                .comNome("SISTAM")
                .doPontoOperacional(new PontoOperacional())
                .build();
        return pontoAtracacao;
    }
    
    private AgenciaPorto obterAgenciaPortoValido(){
        AgenciaPorto agenciaPorto = new AgenciaPorto();
        agenciaPorto.setAgencia(new Agencia());
        agenciaPorto.setPorto(new Porto());
        agenciaPorto.setMunicipio("BONINAL/BA");
        
        return agenciaPorto;
    }
    
    private PortoComplemento obterPortoComplementoValido(){
        PortoComplemento complemento = new PortoComplemento();
        complemento.setNomeCtac("Ssa");
        complemento.setPorto(new Porto());
        complemento.setCnpjComMascara("00.000.000/0000-00");
        complemento.setInscricaoEstadual("123123");
        complemento.setEndereco("Rua Xpto");
        complemento.setCidade("Salvador");
        complemento.setEstado("Bahia");
        return complemento;
    }
    
    
    //</editor-fold>
    
    
    
}
