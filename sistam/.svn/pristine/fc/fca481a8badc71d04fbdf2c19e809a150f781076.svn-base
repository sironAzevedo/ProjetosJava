package br.com.petrobras.sistam.service.mock;

import br.com.petrobras.notesweb2.common.enumeration.TipoResposta;
import br.com.petrobras.notesweb2.common.valueobject.Email;
import br.com.petrobras.notesweb2.common.valueobject.Resposta;
import br.com.petrobras.sistam.common.business.Notesweb2Service;

/**
 *
 */
public class Notesweb2Mock implements Notesweb2Service {

    @Override
    public Resposta enviarEmail(Email email) {
        Resposta resposta = new Resposta();
        resposta.setTipo(TipoResposta.SUCESSO);
        return resposta;
    }

}
