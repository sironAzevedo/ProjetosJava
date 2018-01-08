package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.security.ISecurityContext;
import br.com.petrobras.security.configuration.SecuritySettings;
import br.com.petrobras.security.configuration.StandaloneSecurityConfigurer;
import br.com.petrobras.security.model.ContextValueSet;
import br.com.petrobras.security.model.Role;
import br.com.petrobras.security.model.User;
import br.com.petrobras.security.model.authorization.IAuthorizationContextValue;
import br.com.petrobras.security.model.authorization.access.RoleResourceAuthorization;
import br.com.petrobras.security.model.authorization.access.UserRoleAuthorization;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.valueobjects.ContextoUsuario;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.common.valueobjects.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import org.slf4j.LoggerFactory;

/**
 * {@inheritDoc}
 */
public class AcessoServiceImpl implements AcessoService {
    
    private AgenciaService agenciaService;
    private static final String FWCA = "sistam/fwca";
    private static final String SISTAM = "sistam/sistam";
    
    public AcessoServiceImpl() {
        if (verificarSeEstaExecutandoEmModoFatClient()) {
            LoggerFactory.getLogger(getClass()).info("Iniciando em modo fat-client");
            SecuritySettings.load(new StandaloneSecurityConfigurer(), FWCA, true);
        }
    }
    
    public void setAgenciaService(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }
    
    @Override
    public CredentialsBean confirmarLogon(Object token) {
        ISecurityContext context = this.getContext();
        context.getApplicationEnvironmentConnector().connect(
                token.toString(),
                SecuritySettings.getRegionalId(),
                SecuritySettings.getEnvironmentId(),
                SecuritySettings.getApplicationCatalogId(),
                SecuritySettings.getApplicationPassword());
        return buscarDadosDeAutenticacao();
    }

    @Override
    public SistamCredentialsBean buscarDadosDeAutenticacao() {
        ISecurityContext context = this.getContext();
        ContextoUsuario contextoUsuario = new ContextoUsuario();
	if (context.hasLoggedUser()) {
            User user = context.getCurrentLoggedUser();
            
            Map<Agencia, Set<String>> credenciais = this.obterCredenciais();

            contextoUsuario.setCredenciais(credenciais);

            return new SistamCredentialsBean(user.getLogin(), user.getName(), this.obterSecurityResources(credenciais), (ContextoUsuario) contextoUsuario.clone());
        } else {
            return new SistamCredentialsBean();
        }
    }
    
    protected ISecurityContext getContext() {
        return ISecurityContext.getContext();
    }
    
    private boolean verificarSeEstaExecutandoEmModoFatClient() {
        String fatClientProperty = System.getProperty("fcorp.swing.fatclient");
        return Boolean.TRUE.toString().equals(fatClientProperty);
    }
    
    private Map<Agencia, Set<String>> obterCredenciais() {
        Map<Agencia, Set<String>> credenciais = new HashMap();  
        String siglaAgencia;
        Agencia agencia;
        Set<String> recursos;
        
        List<Role> roles =  this.getContext().getRoleAuthorizer().findAllAuthorized(this.getContext().getCurrentLoggedUser().getLogin(),false);
        
        for (Role role : roles) {
            
            recursos = this.getRecursos(role);
            List<ContextValueSet> contexts = this.getContext().getUserRoleAuthorizationManager().findAllContextValueSets(this.getContext().getCurrentLoggedUser().getLogin(), role.getId());
            if (contexts == null) {
                contexts = new ArrayList<ContextValueSet>();
            }
            for (ContextValueSet contextValueSet : contexts) {
                List<IAuthorizationContextValue> authorizations = contextValueSet.getAuthorizationContextValues();
                for (IAuthorizationContextValue iAuthorizationContextValue : authorizations) {
                    siglaAgencia = iAuthorizationContextValue.getContextValue().getId();
                    agencia = agenciaService.buscarAgenciaPorSigla(siglaAgencia);
                    addRecurso(credenciais, agencia, recursos);
                }
            }
            if (contexts == null || contexts.isEmpty()) {
                addRecurso(credenciais, null, recursos);
            }

        }
        return credenciais;
    }
    
    @Override
    public List<Usuario> obterUsuariosPorAgenciaEPapel(Agencia agencia, String codPapel) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        List<UserRoleAuthorization> uras = this.getContext().getUserRoleAuthorizationManager().findAllWithRole(codPapel);
        for(UserRoleAuthorization ura : uras) {
            List<ContextValueSet> contexts = this.getContext().getUserRoleAuthorizationManager().findAllContextValueSets(ura.getUser().getLogin(), codPapel);
            for (ContextValueSet contextValueSet : contexts) {
                List<IAuthorizationContextValue> authorizations = contextValueSet.getAuthorizationContextValues();
                for (IAuthorizationContextValue iAuthorizationContextValue : authorizations) {
                    String siglaAgencia = iAuthorizationContextValue.getContextValue().getId();
                    if (agencia.getSigla().equals(siglaAgencia)) {
                        usuarios.add(new Usuario(ura.getUser().getLogin(), ura.getUser().getName()));
                    }
                }
            }
        }
        SistamUtils.sort(usuarios, Usuario.PROP_NOME );
        return usuarios;
    }
    
    private void addRecurso(Map<Agencia, Set<String>> credenciais, Agencia agencia, Set<String> recursos) {

        if (credenciais.containsKey(agencia)) {
            for (String recurso : recursos) {
                credenciais.get(agencia).add(recurso);
            }
        } else {
            credenciais.put(agencia, recursos);
        }
        
    }
    
    private Set<String> getRecursos(Role role){
        Set<String> recursos = new HashSet<String>();
        List<RoleResourceAuthorization> resources = this.getContext().getRoleResourceAuthorizationManager().findAll(role);
        for (RoleResourceAuthorization roleResourceAuthorization : resources) {
            recursos.add(roleResourceAuthorization.getResource().getId());
        }
 
        return recursos;
    }
    
    private Set obterSecurityResources(Map credenciais) {
        Set securityResources = new HashSet();
        
        for (Object resources : credenciais.values()) {
            securityResources.addAll((HashSet<String>)resources);
        }
        
        return securityResources;
    }
        
    @Override
    public boolean validarPermissao(Agencia agencia, RecursoCA recurso) {        
        return this.buscarDadosDeAutenticacao().contemRecurso(agencia, recurso);          
    }              

    @Override
    public String buscarApplicationEnvironmentUID() {
        return ResourceBundle.getBundle(FWCA).getString("application_environment_uid");
    }

    @Override
    public String buscarEnvironmentID() {
        return ResourceBundle.getBundle(SISTAM).getString("application.environment");
    }
    
}
