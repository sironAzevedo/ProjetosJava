package br.com.petrobras.sistam.service.query.agenciamento.pendencia;

import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 * Classe que consulta a pendência de uma taxa informada
 * não resolvidas ou ambas.
 * @author X4Z0
 */
public class ConsultaPendenciasDaTaxa extends BusinessQuery<Pendencia> {

    public ConsultaPendenciasDaTaxa(Taxa taxa) {
        StringBuilder texto = new StringBuilder();

        texto.append(" from {AgenciamentoPendencia} p ")
                .append(" where p.{taxa} = :taxa ");

        String hql = texto.toString();
        hql = hql.replace("{AgenciamentoPendencia}", Pendencia.class.getSimpleName())
                .replace("{taxa}", Pendencia.PROP_TAXA);

        setText(hql);
        addParameter("taxa", taxa);

    }

}
