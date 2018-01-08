package br.com.petrobras.sistam.service.query.common;

import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaResponsavelCustoExcetoSemCusto extends BusinessQuery<ResponsavelCustoEntity> {

    public ConsultaResponsavelCustoExcetoSemCusto() {
        appendText("Select recu from ",ResponsavelCustoEntity.class.getSimpleName()," recu ");
        appendText(" where recu.id <> 4");
    }
}
