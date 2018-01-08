package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.validator.ValidadorEmpresa;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;


public class EmpresaServiceTest {
    private EmpresaServiceImpl empresaService;
    private ValidadorPermissao validadorPermissao;
    private ValidadorEmpresa validador;
    private GenericDao dao;
    
    @Before
    public void setUp(){
        validadorPermissao = mock(ValidadorPermissao.class);
        validador = mock(ValidadorEmpresa.class);
        dao = mock(GenericDao.class);
        empresaService = new EmpresaServiceImpl(dao);
        
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Empresa">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarUmaEmpresa() {
        EmpresaMaritima empresaMaritima = obterEmpresaValida();
        
        empresaService.salvarEmpresa(empresaMaritima);
        Mockito.verify(validador).validarSalvarEmpresa(empresaMaritima);
        Mockito.verify(validadorPermissao).podeSalvarEmpresa(any(Agencia.class));
    }
    
    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarAEmpresa(){
        EmpresaMaritima empresaMaritima = obterEmpresaValida();
        empresaService.salvarEmpresa(empresaMaritima);
        Mockito.verify(dao).saveOrUpdate(empresaMaritima);
    }
    
    @Test
    public void quandoForUmaNovaEmpresaDeveChamarOSaveUpdateParaCadaServicoDaEmpresa(){
        EmpresaMaritima empresaMaritima = obterEmpresaValida();
        Servico servico01 = obterServicoValido();
        Servico servico02 = obterServicoValido(); 
        
        empresaMaritima.adicionarServico(servico01);
        empresaMaritima.adicionarServico(servico02);
        
        empresaService.salvarEmpresa(empresaMaritima);
        
        Mockito.verify(dao).saveOrUpdate(servico01);
        Mockito.verify(dao).saveOrUpdate(servico02);
    }
    
     @Test
    public void quandoForUmaNovaEmpresaDeveChamarOValidadorParaCadaServicoDaEmpresa(){
        EmpresaMaritima empresaMaritima = obterEmpresaValida();
        Servico servico01 = obterServicoValido();
        Servico servico02 = obterServicoValido(); 
        
        empresaMaritima.adicionarServico(servico01);
        empresaMaritima.adicionarServico(servico02);
        
        empresaService.salvarEmpresa(empresaMaritima);
        
        Mockito.verify(validador).validarSalvarServico(servico01);
        Mockito.verify(validador).validarSalvarServico(servico02);
    }
    
     @Test
     public void deveChamarOsValidadoresQuandoExcluirEmpresa() {
         EmpresaMaritima empresaMaritima = obterEmpresaValida();
         empresaService.excluirEmpresa(empresaMaritima);
         Mockito.verify(validadorPermissao).podeSalvarEmpresa(any(Agencia.class));
     }
     
     @Test
     public void deveChamarORemoveQuandoExcluirEmpresa() {
         EmpresaMaritima empresaMaritima = obterEmpresaValida();
         empresaService.excluirEmpresa(empresaMaritima);
         Mockito.verify(dao).remove(empresaMaritima);
     }
     
     
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Serviços da Empresa">
     @Test
     public void deveChamarOsValidadoresQuandoSalvarUmServicoParaEmpresa() {
         Servico servico = obterServicoValido();
         
         empresaService.salvarServicoDaEmpresa(servico);
         Mockito.verify(validador).validarSalvarServico(servico);
         Mockito.verify(validadorPermissao).podeSalvarEmpresa(any(Agencia.class));
     }
     
     @Test
     public void deveChamarOSaveOrUpdateQuandoSalvarUmServicoParaEmpresa(){
         Servico servico = obterServicoValido();
         empresaService.salvarServicoDaEmpresa(servico);
         Mockito.verify(dao).saveOrUpdate(servico);
     }
     
     @Test
     public void deveChamarOsValidadoresQuandoExcluirUmServicoParaEmpresa() {
         Servico servico = obterServicoValido();
         
         empresaService.excluirServicoDaEmpresa(servico);
         Mockito.verify(validadorPermissao).podeSalvarEmpresa(any(Agencia.class));
     }
     
     @Test
     public void deveChamarORemoveQuandoExcluirUmServicoParaEmpresa() {
         Servico servico = obterServicoValido();
         
         empresaService.excluirServicoDaEmpresa(servico);
         Mockito.verify(dao).remove(servico);
     }
     
     //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(EmpresaServiceImpl.class, "validadorEmpresa", false);
        ReflectionUtils.setFieldValue(empresaService, field, validador);
    }
    
    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarEmpresa(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(EmpresaServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(empresaService, field, validadorPermissao);
    }
    
    private EmpresaMaritima obterEmpresaValida() {
        EmpresaMaritima empresaMaritima = EmpresaMaritimaBuilder.novaEmpresaMaritima()
                .comAgencia(new Agencia())
                .build();
        return empresaMaritima;
    }
    
    private Servico obterServicoValido() {
        Servico servico = new Servico();
        servico.setEmpresa(obterEmpresaValida());
        servico.setNomeServico("SISTAM");
        servico.setTipoServico(TipoServico.PRATICOS);
        
        return servico;
    }
    
    //</editor-fold>
    
}
