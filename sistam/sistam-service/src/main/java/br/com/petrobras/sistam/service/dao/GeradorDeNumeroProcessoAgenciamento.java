package br.com.petrobras.sistam.service.dao;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.NumeracaoProcessoAgenciamento;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;

public class GeradorDeNumeroProcessoAgenciamento {

    private final GeradorDeNumero geradorDeNumero;

    public GeradorDeNumeroProcessoAgenciamento(GeradorDeNumero geradorDeNumero) {
        this.geradorDeNumero = geradorDeNumero;
    }

    public Long gerarNumero(Agencia agencia, Integer ano) {
        SQLQuery query = this.geradorDeNumero.createSQLQuery("SELECT numeracao.NUAG_CD_AGENCIAMENTO AS NUMERO "
                            + "FROM STAM.NUMERACAO_AGENCIAMENTO numeracao "
                            + "WHERE numeracao.AGEN_SQ_AGENCIA = :idAgencia "
                            + "AND numeracao.NUAG_NR_ANO_PROCESSO = :ano "
                            + "FOR UPDATE OF numeracao.AGEN_SQ_AGENCIA, numeracao.NUAG_NR_ANO_PROCESSO");
        query.addScalar("NUMERO", Hibernate.LONG);
        query.setLong("idAgencia", agencia.getId());
        query.setInteger("ano", ano);
        Long numero = (Long) query.uniqueResult();
        return this.geradorDeNumero.gerarNumero(new NumeracaoProcessoAgenciamento(agencia, ano), numero);
    }

}
