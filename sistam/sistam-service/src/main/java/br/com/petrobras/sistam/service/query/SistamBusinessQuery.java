package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.valueobjects.Periodo;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.io.Serializable;
import java.util.Date;

/**
 * @author adzk
 */
public abstract class SistamBusinessQuery<T extends Serializable> extends BusinessQuery<T> {

    protected void adicionarCondicaoPeriodoZerandoAsHoras(StringBuilder hql, Periodo periodo, String propertyName) {
        adicionarCondicaoPeriodo(hql, periodo, propertyName, true);
    }
    
    protected void adicionarCondicaoPeriodo(StringBuilder hql, Periodo periodo, String propertyName) {
        adicionarCondicaoPeriodo(hql, periodo, propertyName, false);
    }
    
    private void adicionarCondicaoPeriodo(StringBuilder hql, Periodo periodo, String propertyName, boolean zerarHoras) {
        Date inicio = periodo.getDataInicio();
        Date fim = periodo.getDataFim();
        if (zerarHoras) {
            inicio = periodo.getDataInicioNaPrimeiraHoraDoDia();
            fim = periodo.getDataFimNaUltimaHoraDoDia();
        }
        adicionarCondicaoPeriodo(hql, inicio, fim, propertyName);
    }

    protected void adicionarCondicaoPeriodo(StringBuilder hql, Date inicio, Date fim, String propertyName) {
        String paramName = propertyName.replaceAll("[{|}.]", "");
        String nomeParametroInicio = paramName + "Inicio";
        String nomeParametroTermino = paramName + "Termino";

        if (inicio != null && fim != null) {
            hql.append(" and (")
                    .append(" (").append(propertyName).append(" >= :").append(nomeParametroInicio).append(" and ").append(propertyName).append(" < :").append(nomeParametroTermino).append(")")
                    .append(" or (").append(propertyName).append(" > :").append(nomeParametroInicio).append(" and ").append(propertyName).append(" <= :").append(nomeParametroTermino).append(")")
                    .append(" or (").append(propertyName).append(" = :").append(nomeParametroInicio).append(" and ").append(propertyName).append(" = :").append(nomeParametroTermino).append(")")
                    .append(" ) ");
            addParameter(nomeParametroInicio, inicio);
            addParameter(nomeParametroTermino, fim);

        } else if (inicio != null) {
            hql.append(" and ").append(propertyName).append(" >= :").append(nomeParametroInicio);
            addParameter(nomeParametroInicio, inicio);

        } else if (fim != null) {
            hql.append(" and ").append(propertyName).append(" <= :").append(nomeParametroTermino);
            addParameter(nomeParametroTermino, fim);
        }
    }
}
