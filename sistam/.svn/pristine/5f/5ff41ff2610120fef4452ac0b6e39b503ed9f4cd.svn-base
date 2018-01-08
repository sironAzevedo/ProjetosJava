package br.com.petrobras.sistam.service.query.porto;

import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaPortosNacionaisComComplementos extends BusinessQuery<Porto> {

    public ConsultaPortosNacionaisComComplementos() {

        StringBuilder texto = new StringBuilder();
        texto.append(" select distinct p from {Porto} p ")
                .append(" join fetch p.{complementos} c")
                .append(" join fetch p.{pais} pa")
                .append(" where pa.id = :siglaPais")
                .append(" order by p.{nome}");

        String hql = texto.toString();
        hql = hql.replace("{Porto}", Porto.class.getSimpleName())
                .replace("{complementos}", Porto.PROP_COMPLEMENTOS)
                .replace("{pais}", Porto.PROP_PAIS)
                .replace("{nome}", Porto.PROP_NOME_COMPLETO);

        this.setText(hql);
        this.addParameter("siglaPais", Pais.CODIGO_BRASIL);
    }
}
