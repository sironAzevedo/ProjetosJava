package br.com.petrobras.sistam.desktop.exception;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.fcorp.exceptionhandler.ExceptionMessage;
import br.com.petrobras.fcorp.exceptionhandler.ExceptionHandler;
import br.com.petrobras.fcorp.exceptionhandler.HandlerResult;
import br.com.petrobras.fcorp.swing.components.dialog.DialogMessages;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.PendenciasDialog;
import java.util.ArrayList;
import org.hibernate.StaleObjectStateException;
        
public class SistamPendingExceptionHandler implements ExceptionHandler {

    @Override
    public HandlerResult doHandle(Throwable ex) {

        if (ex instanceof SistamPendingException) {
            SistamPendingException exception = (SistamPendingException) ex;
            
            PendenciasDialog dialog = new PendenciasDialog(SistamApp.getSistamApp().getMainFrame(), true);
            dialog.criarArvore(exception.getPendingMap());
            dialog.setVisible(true);
            return new HandlerResult(true,new ArrayList<ExceptionMessage>());
            
        } else if (ex instanceof StaleObjectStateException) {
            DialogMessages.error(SistamApp.getSistamApp().getMainFrame(), "Existe uma versão mais atual das informações. Feche a tela e abra novamente.");
            return new HandlerResult(true,new ArrayList<ExceptionMessage>());
        }

        return new HandlerResult(false,new ArrayList<ExceptionMessage>());
    }
    
}
