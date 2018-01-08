package br.com.petrobras.sistam.service.aspect;

import br.com.petrobras.sistam.service.impl.*;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;


public class AspectValidatorTest {
    private AcessoServiceImpl acessoService = mock(AcessoServiceImpl.class);
    private ValidadorPermissao validadorPermissao = mock(ValidadorPermissao.class);
    private AspectValidator aspectValidator;
    
    @Before
    public void setUp(){
        aspectValidator = new AspectValidator();
        aspectValidator.setAcessoService(acessoService);
        aspectValidator.setValidadorPermissao(validadorPermissao);
    }    
    
    @Test
    public void deveInjetarAsCredenciais() {
        aspectValidator.credentialsBeanInject();
        Mockito.verify(validadorPermissao).setCredentialsBean(Mockito.any(SistamCredentialsBean.class));
    }

    
    
}
