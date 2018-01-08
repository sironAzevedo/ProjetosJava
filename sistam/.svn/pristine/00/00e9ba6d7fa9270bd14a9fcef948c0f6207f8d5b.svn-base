package br.com.petrobras.sistam.service.dao;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import br.com.petrobras.snarf.persistence.TransactionCallback;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import org.junit.Assert;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
        
@DataSet("/datasets/GeradorDeNumeroProcessoAgenciamento.xml")
public class GeradorDeNumeroProcessoAgenciamentoIT extends BaseIntegrationTestClass {

    @SpringBean("dao")
    private GenericDao dao;

    @SpringBean("geradorDeNumeroProcessoAgenciamento")
    private GeradorDeNumeroProcessoAgenciamento gerador;
    
    @Test
    public void quandoAAgenciaNaoPossuiProcessoNoAnoEntaoONumeroGeradoDeveSer1() {
        final Agencia agencia = AgenciaBuilder.novaAgencia().comId(6L).build();
        
        Long numero = (Long) dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override public Object doInsideTransaction() {
                return gerador.gerarNumero(agencia, 2013);
            }
        });
        
        Assert.assertEquals(1L, numero.longValue());
    }
    
    @Test
    public void quandoAAgenciaPossuiProcessoNoAnoEntaoONumeroGeradoDeveSerONumeroAtualMais1() {
        final Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();

        Long numero = (Long) dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override public Object doInsideTransaction() {
                return gerador.gerarNumero(agencia, 2013);
            }
        });

        Assert.assertEquals(8L, numero.longValue());
    }
    
    
    
}
