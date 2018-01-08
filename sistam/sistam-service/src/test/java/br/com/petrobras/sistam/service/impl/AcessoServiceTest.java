package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.support.CollectionUtils;
import br.com.petrobras.security.ISecurityContext;
import br.com.petrobras.security.authorization.IResourceAuthorizer;
import br.com.petrobras.security.authorization.IRoleAuthorizer;
import br.com.petrobras.security.configuration.SecurityConfigurer;
import br.com.petrobras.security.configuration.SecuritySettings;
import br.com.petrobras.security.connection.IApplicationEnvironmentConnector;
import br.com.petrobras.security.model.Resource;
import br.com.petrobras.security.model.User;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;
        
/**
 * Testes para o serviço de acesso.
 */
public class AcessoServiceTest {

    private AcessoServiceImpl acessoService;
    
    @Before
    public void instanciarServico() {
        acessoService = spy(new AcessoServiceImpl());
    }
    
    //<editor-fold defaultstate="collapsed" desc="logon">
    @Test
    public void quandoOTokenEInformadoEntaoOMetodoDeConectarNoControleDeAcessoEChamado() {
        final String tokenDeTeste = "1234567890";
        IApplicationEnvironmentConnector connector = prepararTeste().paraConectarAoCA();
        
        acessoService.confirmarLogon(tokenDeTeste);
        verify(connector).connect(eq(tokenDeTeste), anyString(), anyString(), anyString(), anyString());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="buscarDadosDeAutenticacao">
    @Test
    public void quandoOUsuarioNaoEstaLogadoEntaoRetornaUmObjetoInvalido() {
        prepararTeste().semUsuarioLogado();
        
        CredentialsBean credentials = acessoService.buscarDadosDeAutenticacao();
        assertFalse(credentials.isAuthenticated());
    }
    
    @Test
    public void quandoOUsuarioEstaLogadoEntaoRetornaUmObjetoValido() {
        prepararTeste().comUsuario("1234").semBuscarRecursos().semBuscarRoles();
        
        CredentialsBean credentials = acessoService.buscarDadosDeAutenticacao();
        assertTrue(credentials.isAuthenticated());
    }
    
    @Test
    @Ignore
    public void quandoOUsuarioEstaLogadoEntaoRetornaOsRecursosCadastrados() {
        prepararTeste().comUsuario("1234").comRecursos("alfa", "beta").semBuscarRoles();
        
        CredentialsBean credentials = acessoService.buscarDadosDeAutenticacao();
        assertTrue(credentials.verifySecurityResource("alfa"));
        assertTrue(credentials.verifySecurityResource("beta"));
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos Privados">
    private MockManager prepararTeste() {
        return new MockManager();
    }
    
    private class MockManager {
        private ISecurityContext securityContext;
        
        public MockManager() {
            configurarSecuritySettings();
            configurarSecurityContext();
        }
        
        private void configurarSecuritySettings() {
            Properties propriedadesCA = new Properties();
            propriedadesCA.putAll(CollectionUtils.createMap(SecuritySettings.REGIONAL_ID_KEY, "REGIONAL_ID_KEY",
                                                            SecuritySettings.ENVIRONMENT_ID_KEY, "ENVIRONMENT_ID_KEY",
                                                            SecuritySettings.APP_CATALOG_ID_KEY, "APP_CATALOG_ID_KEY",
                                                            SecuritySettings.APP_PASSWORD_ID_KEY, "APP_PASSWORD_ID_KEY",
                                                            SecuritySettings.ENDPOINT_WSDL_KEY, "ENDPOINT_WSDL_KEY"));
            SecuritySettings.load(mock(SecurityConfigurer.class), propriedadesCA, false);
        }
        
        private void configurarSecurityContext() {
            securityContext = mock(ISecurityContext.class);
            doReturn(securityContext).when(acessoService).getContext();
        }
        
        public IApplicationEnvironmentConnector paraConectarAoCA() {
            IApplicationEnvironmentConnector connector = mock(IApplicationEnvironmentConnector.class);
            when(securityContext.getApplicationEnvironmentConnector()).thenReturn(connector);
            doReturn(new SistamCredentialsBean()).when(acessoService).buscarDadosDeAutenticacao();
            return connector;
        }
        
        public MockManager semUsuarioLogado() {
            when(securityContext.hasLoggedUser()).thenReturn(Boolean.FALSE);
            return this;
        }
        
        public MockManager comUsuario(String chave) {
            User usuarioLogado = new User();
            usuarioLogado.setLogin(chave);
            usuarioLogado.setName(chave);
            when(securityContext.hasLoggedUser()).thenReturn(Boolean.TRUE);
            when(securityContext.getCurrentLoggedUser()).thenReturn(usuarioLogado);
            return this;
        }
        
        public MockManager semBuscarRecursos() {
            IResourceAuthorizer authorizer = mock(IResourceAuthorizer.class);
            when(securityContext.getResourceAuthorizer()).thenReturn(authorizer);
            return this;
        }
        
        public MockManager comRecursos(String... recursos) {
            List<Resource> lista = new ArrayList<Resource>();
            for (String id : recursos) {
                lista.add(new Resource(id));
            }
            IResourceAuthorizer authorizer = mock(IResourceAuthorizer.class);
            when(securityContext.getResourceAuthorizer()).thenReturn(authorizer);
            when(authorizer.findAll()).thenReturn(lista);
            return this;
        }
        
        public MockManager semBuscarRoles() {
            IRoleAuthorizer authorizer = mock(IRoleAuthorizer.class);
            when(securityContext.getRoleAuthorizer()).thenReturn(authorizer);
            return this;
        }
        
    }
    //</editor-fold>
}
