package br.com.petrobras.sistam.service.query.manobra;

import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaServicoResponsavelPorManobra extends BusinessQuery<ServicoResponsavel> {

    public ConsultaServicoResponsavelPorManobra(Manobra manobra) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select sr from {servicoResponsavel} sr")
                .append(" left join fetch sr.{servico} serv")
                .append(" left join fetch serv.{empresa} e")
                .append(" left join fetch sr.{servicoManobra} sm")
                .append(" where sm.{manobra} = :manobra");

        addParameter("manobra", manobra);

        String hql = texto.toString();
        hql = hql.replace("{servicoResponsavel}", ServicoResponsavel.class.getSimpleName())
                .replace("{servico}", ServicoResponsavel.PROP_SERVICO)
                .replace("{servicoManobra}", ServicoResponsavel.PROP_SERVICO_MANOBRA)
                .replace("{manobra}", ServicoManobra.PROP_MANOBRA)
                .replace("{empresa}", Servico.PROP_EMPRESA);

        this.setText(hql);
    }
}
