package br.com.petrobras.sistam.service.query.common;

import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaResponsavelCustoApenasPetrobrasETranspetro extends BusinessQuery<ResponsavelCustoEntity> {

    public ConsultaResponsavelCustoApenasPetrobrasETranspetro() {
        appendText("Select recu from ",ResponsavelCustoEntity.class.getSimpleName()," recu ");
        appendText(" where recu.id < 3");
    }
}
