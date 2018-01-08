package br.com.petrobras.sistam.service.dao;

import br.com.petrobras.fcorp.persistence.dao.hibernate.support.HibernateSupport;
import br.com.petrobras.sistam.common.entity.NumeracaoIncrementavel;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class GeradorDeNumero extends HibernateSupport {

    public Long gerarNumero(Long numeracaoInicial, NumeracaoIncrementavel numeracao, Long numero) {
        if (numero == null) {
            numeracao.setNumero(numeracaoInicial);
            getCurrentSession().persist(numeracao);
        } else {
            numeracao.setNumero(numero+1L);
            getCurrentSession().update(numeracao);
        }
        return numeracao.getNumero();
    }

    public Long gerarNumero(NumeracaoIncrementavel numeracao, Long numero) {
        return this.gerarNumero(1L, numeracao, numero);
    }

    public SQLQuery createSQLQuery(String sql) {
        return getCurrentSession().createSQLQuery(sql);
    }

    public Query createQuery(String hql) {
        return getCurrentSession().createQuery(hql);
    }
    
    @Override
    protected Session getCurrentSession() {
        return super.getCurrentSession();
    }

}
