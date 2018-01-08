package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/CommonService.xml")
public class CommonServiceIT extends BaseIntegrationTestClass {

    @SpringBean("CommonServiceImpl")
    private CommonService commonService;

    @Test
    public void deveRetornarServico() {
        List<Servico> servicos = commonService.buscarServicosPorTipo(TipoServico.HOSPEDAGEM);

        Assert.assertEquals(2, servicos.size());
        Assert.assertEquals("GRAND HOTEL STELLA MARIS", servicos.get(0).getEmpresa().getRazaoSocial());
        Assert.assertEquals("OTHON", servicos.get(1).getEmpresa().getRazaoSocial());
    }

    @Test
    public void deveRetornarUmProdutoPorId() {
        Produto produto = commonService.buscarProdutoPorId("28W");
        Assert.assertNotNull(produto);
    }

    @Test
    public void deveRetornarProdutosPorNome() {
        List<Produto> produtos = commonService.buscarProdutos("OLEO COMBUSTIVEL KUWAIT");
        Assert.assertEquals(1, produtos.size());
    }

    @Test
    public void deveRetornarTodosOsResponsaveisCusto() {
        List<ResponsavelCustoEntity> responsavelCusto = commonService.buscarResponsavelCustoApenasPetrobrasETranspetro();
        Assert.assertEquals(2, responsavelCusto.size());
    }

    @Test
    public void deveRetornarTodosOsResponsaveisCustoExcetoSemCusto() {
        List<ResponsavelCustoEntity> responsavelCusto = commonService.buscarResponsavelCustoExcetoSemCusto();
        Assert.assertEquals(3, responsavelCusto.size());
    }

    @Test
    public void deveBuscarPaises() {
        assertNotNull(commonService.buscarPaises("Brasil"));
    }

    @Test
    public void deveBuscarTodosResponsavelCusto() {
        assertEquals(4, commonService.buscarTodosResponsavelCusto().size());
    }

    @Test
    public void deveBuscarServicosPorEmpresaETipo() {
        EmpresaMaritima empresaMaritima = new EmpresaMaritima();
        empresaMaritima.setId(3L);
        List<Servico> lista = commonService.buscarServicosPorEmpresaETipo(empresaMaritima, TipoServico.HOSPEDAGEM);
        assertEquals(1, lista.size());
    }

    @Test
    public void deveBuscarEmpresasMaritimasPorAgenciaTipoServico() {
        Agencia agencia = new Agencia();
        agencia.setId(1L);
        List<EmpresaMaritima> lista = commonService.buscarEmpresasMaritimasPorAgenciaTipoServico(agencia, TipoServico.HOSPEDAGEM);
        assertEquals(2, lista.size());
    }
}
