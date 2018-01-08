package br.com.petrobras.sistam.desktop.validator;

import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;

public class ValidadorPermissaoEstatico extends ValidadorPermissao {

    private static ValidadorPermissaoEstatico myInstance = null  ;
    
    public static ValidadorPermissaoEstatico getInstance() {  
        
        if (myInstance == null) {
            myInstance = new ValidadorPermissaoEstatico((SistamCredentialsBean)SistamApp.getSistamApp().getCredentialsBean());
        }
        
        return myInstance;
    }
    
    private ValidadorPermissaoEstatico(SistamCredentialsBean credentials) {
        setCredentialsBean(credentials);
    }
    
}
