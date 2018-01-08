package br.com.petrobras.sistam.service.interceptor;

import br.com.petrobras.security.ISecurityContext;
import java.util.concurrent.Callable;

public class CurrentUserNameParameter implements Callable<String> {

    @Override
    public String call() {
        try {
            ISecurityContext context = ISecurityContext.getContext();
            if (context.hasLoggedUser()) {
                return context.getCurrentLoggedUser().getName();
            } else {
                return "????";
            }
        } catch (Exception e) {
            return "ERRO";
        }
    }
    
    
}
