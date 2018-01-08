package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.validator.ValidadorCertificado;
import br.com.petrobras.sistam.test.builder.CertificadoBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;


public class CertificadoServiceTest {
    private CertificadoServiceImpl certificadoService;
    private ValidadorPermissao validadorPermissao = mock(ValidadorPermissao.class);;
    private ValidadorCertificado  validador = mock(ValidadorCertificado.class);;
    
    @Before
    public void setUp(){
        certificadoService = new CertificadoServiceImpl();
        certificadoService.setDao(Mockito.mock(GenericDao.class));
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
    }    
    
    @Test
    public void deveChamarOsValidadoresQuandoSalvarCertificado() {
        Certificado certificado = CertificadoBuilder.novoCertificado()
                .build();
        certificadoService.salvarCertificado(certificado);
        Mockito.verify(validador).validarSalvarCertificado(certificado);
        Mockito.verify(validadorPermissao).podeSalvarCertificado();
    }

    @Test
    public void deveChamarOsValidadoresQuandoSalvarListaDeCertificados() {
        Certificado certificado1 = CertificadoBuilder.novoCertificado()
                .build();
        Certificado certificado2 = CertificadoBuilder.novoCertificado()
                .build();
        List<Certificado> certificados = new ArrayList<Certificado>(Arrays.asList(certificado1, certificado2));
        certificadoService.salvarCertificados(certificados);
        Mockito.verify(validador, Mockito.times(certificados.size())).validarSalvarCertificado(Mockito.any(Certificado.class));
        Mockito.verify(validadorPermissao, Mockito.times(certificados.size())).podeSalvarCertificado();
    }
    
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarCertificado(){
        Certificado certificado = CertificadoBuilder.novoCertificado()
                .build();
        certificadoService.salvarCertificado(certificado);
        Mockito.verify(obterDao()).saveOrUpdate(certificado);
    }
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarListaDeCertificados() {
        Certificado certificado1 = CertificadoBuilder.novoCertificado()
                .build();
        Certificado certificado2 = CertificadoBuilder.novoCertificado()
                .build();
        List<Certificado> certificados = new ArrayList<Certificado>(Arrays.asList(certificado1, certificado2));
        certificadoService.salvarCertificados(certificados);
        Mockito.verify(obterDao(), Mockito.times(certificados.size())).saveOrUpdate(Mockito.any(Certificado.class));
    }
    
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(CertificadoServiceImpl.class, "validadorCertificado", false);
        ReflectionUtils.setFieldValue(certificadoService, field, validador);
    }
    
    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarCertificado()).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(CertificadoServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(certificadoService, field, validadorPermissao);
    }
    
    private GenericDao obterDao() {
        Field field = ReflectionUtils.getFieldWithName(CertificadoServiceImpl.class, "dao", false);
        return (ReflectionUtils.getFieldValue(certificadoService, field));
    }
    
    
}
