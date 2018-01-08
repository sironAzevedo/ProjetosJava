package br.com.petrobras.sistam.desktop;

import br.com.petrobras.fcorp.business.service.support.IServiceFactory;
import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.i18n.I18nManager;
import br.com.petrobras.fcorp.i18n.support.SimpleBundleNameStore;
import br.com.petrobras.fcorp.spring.remoting.SpringRemotingServiceFactory;
import br.com.petrobras.fcorp.swing.ExceptionUtil;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.fcorp.swing.base.SFrame;
import br.com.petrobras.fcorp.swing.blocking.BlockingProxy;
import br.com.petrobras.fcorp.swing.components.dialog.LoginDialog;
import br.com.petrobras.snarf.security.SecureLogonConnector;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.swing.ImageIcon;
import org.springframework.context.support.GenericXmlApplicationContext;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRPdfSaveContributor;
import net.sf.jasperreports.view.save.JRSingleSheetXlsSaveContributor;
import java.util.Locale;
import br.com.petrobras.snarf.desktop.reports.ReportUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.remoting.httpinvoker.CommonsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;


/**
 * Classe de inicialização da aplicação.
 */
public class SistamApp extends BaseApp<SistamService> {
    private transient LoginDialog loginDialog;
    private transient GenericXmlApplicationContext contexto;
    private HttpClient client;
    private String url;

    public static void main(String[] args) {
        SistamUtils.transformarArgumentosEmSystemProperties(args);
        showSplashAndLaunch(args, SistamApp.class, SistamFrame.class);
        configuraSaveRelatorios();
    }

    public static SistamApp getSistamApp() {
        return (SistamApp) getApplication();
    }

    @Override
    protected void setupLookAndFeel() throws Exception {
        SistamTheme.aplicarTema();
    }

    @Override
    public String getApplicationTitle() {
        String envId = getService().buscarEnvironmentID();
        String title = "Sistema de Agenciamento Marítimo";
        if(StringUtils.isNotBlank(envId)){
            title = "[" + envId.toUpperCase() + "] " + title;
        }
        return title;
    }

    @Override
    public Image getApplicationIcon() {
        return new ImageIcon(getClass().getResource("/icons/icon.png")).getImage();
    }

    @Override
    public Image getSplashScreenImage() {
        return new ImageIcon(getClass().getResource("/icons/splash.png")).getImage();
    }

    @Override
    public Class getServiceInterface() {
        return SistamService.class;
    }

    @Override
    public URL getHelpSetURL() {
        try {
            BasicService bs = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
            return new URL(bs.getCodeBase(), "sistam/help/SistamHelpSet.xml");
        } catch(UnavailableServiceException ex) {
            return getClass().getResource("/help/SistamHelpSet.xml");
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    @Override
    public String getHelpBrokerId() {
        return "Sistam_HELP";
    }

    @Override
    protected SimpleBundleNameStore createBundleNameStore() {
        SimpleBundleNameStore store = new SimpleBundleNameStore();
        store.setBundleNames( new String[] {
                    "sistam-labels",
                    "sistam-messages",
                    "snarf-messages",
                    "fcorp-swing-base-messages",
                    "fcorp-swing-components-messages",
        } );
         return store;
    }

    @Override
    protected SistamService createService(boolean isFatClient) {
        
        contexto = new GenericXmlApplicationContext();
        if (isFatClient()) {
            contexto.getEnvironment().setActiveProfiles("fat-client");
            contexto.load("clientContext.xml");
            contexto.refresh();
            return (SistamService) contexto.getBean("sistamService");
        } 
        
        contexto.getEnvironment().setActiveProfiles("thin-client");
        contexto.load("clientContext.xml");
        contexto.refresh();
            
        //Configurando cliente para download
        SpringRemotingServiceFactory factory = (SpringRemotingServiceFactory) contexto.getBean("&sistamService");
        client = new HttpClient(new MultiThreadedHttpConnectionManager());
        HttpInvokerProxyFactoryBean proxyFactory = new HttpInvokerProxyFactoryBean();
        proxyFactory.setHttpInvokerRequestExecutor(new CommonsHttpInvokerRequestExecutor(client));
        factory.setProxyFactory(proxyFactory);
        url = factory.getServiceUrl().replace("SistamService.svlt","SistamTransfer.svlt");

        return (SistamService) factory.createService();
    }

    @Override
    public String getServiceLocation() {
        return ((IServiceFactory) contexto.getBean("&service")).getServiceLocation();
    }

    @Override
    protected CredentialsBean executeLogon(SFrame mainFrame) {
        if (this.loginDialog == null) {
            this.loginDialog = new LoginDialog(mainFrame, true);
        }
        this.loginDialog.setVisible(true);
        
        try {
            String appEnvUid = getService().buscarApplicationEnvironmentUID();
            Object token = BlockingProxy.blockingInvoke(
                    I18nManager.getString(ConstantesI18N.LOGON_SEGURO),
                    null,
                    SecureLogonConnector.getInstance(),
                    SecureLogonConnector.class.getMethod("secureLogonWithAppIdAndGetSessionId", String.class, String.class, String.class),
                    new Object[] {appEnvUid, this.loginDialog.getUsername(), this.loginDialog.getPassword()}
            );
            
            
            SistamCredentialsBean credentials = (SistamCredentialsBean) getService().confirmarLogon(token);
            AssertUtils.assertNotNullNotEmpty(credentials.getCredenciais().keySet(), ConstantesI18N.ACESSO_USUARIO_SEM_PERMISSAO);
            
            return credentials;
            
        } catch (Exception e) {
            this.loginDialog = null;                 
             Throwable t = ExceptionUtil.cleanException(e);
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t);
            }
        }
    }
    
    private static void configuraSaveRelatorios() {
        JRSaveContributor[] contributors = new JRSaveContributor[2];
        contributors[0] = new JRSingleSheetXlsSaveContributor(Locale.getDefault(), null);
        contributors[1] = new JRPdfSaveContributor(Locale.getDefault(), null);
        ReportUtils.setDefaultSaveContributors(contributors);
    }
    
    
    public void atualizarCaixaEntrada() {
        ((SistamFrame)getMainFrame()).getCaixaEntrada().atualizarCaixa();
    }
    
}
