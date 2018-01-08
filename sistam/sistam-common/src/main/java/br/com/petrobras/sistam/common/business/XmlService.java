/*
 * Serviço de controle de xml. 
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import java.util.Map;

public interface XmlService {
    
    @AuthorizedResource("")
    public byte[] downloadXmlDuv(Map<String, String> parameterMap);

}
