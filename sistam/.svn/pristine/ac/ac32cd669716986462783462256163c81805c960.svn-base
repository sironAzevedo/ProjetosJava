package br.com.petrobras.sistam.service.query.manobra;

import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaFinalidadePorManobraId extends BusinessQuery<FinalidadeManobra> {

    public ConsultaFinalidadePorManobraId(Long id) {

        Map<String, Object> map = new HashMap<String, Object>();

        StringBuilder texto = new StringBuilder();

        texto.append(" select m.{finalidade} from {Manobra} m ")
                .append(" where m.{id} = :id");

        map.put("id", id);

        String hql = texto.toString();

        hql = hql.replace("{Manobra}", Manobra.class.getSimpleName())
                .replace("{finalidade}", Manobra.PROP_FINALIDADE_MANOBRA)
                .replace("{id}", Manobra.PROP_ID);

        this.setText(hql);
        setParameters(map);
    }
}
