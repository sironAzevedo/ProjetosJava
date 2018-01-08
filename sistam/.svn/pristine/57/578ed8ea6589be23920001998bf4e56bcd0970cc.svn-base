/*
 * Serviço de comunicação para envio de e-mail no notesWeb.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.notesweb2.common.valueobject.Email;
import br.com.petrobras.notesweb2.common.valueobject.Resposta;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://ws.notesweb2.petrobras.com.br/")
public interface Notesweb2Service  {

    @WebMethod
    public Resposta enviarEmail(Email email);
    
    
}
