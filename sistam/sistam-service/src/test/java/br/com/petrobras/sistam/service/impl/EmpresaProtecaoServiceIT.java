package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.business.EmpresaProtecaoService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresaProtecao;
import br.com.petrobras.sistam.test.builder.EmpresaProtecaoBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/EmpresaProtecaoService.xml")
public class EmpresaProtecaoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("EmpresaProtecaoServiceImpl")
    private EmpresaProtecaoService empresaProtecaoService;

    //<editor-fold defaultstate="collapsed" desc="Buscar Empresas por Filtro">
    @Test
    public void deveRetornarTodasAsEmpresasExistentesQuandoNaoHouverFiltros() {
        FiltroEmpresaProtecao filtro = new FiltroEmpresaProtecao();
        List<EmpresaProtecao> resultado = empresaProtecaoService.buscarEmpresasProtecaoPorFiltro(filtro);
        assertEquals(2, resultado.size());
    }

    @Test
    public void deveRetornarUmaEmpresaEspecificaQuandoInformadoUmCnpjExistenteNoFiltro() {
        FiltroEmpresaProtecao filtro = new FiltroEmpresaProtecao();
        filtro.setCnpj("25285531000107");
        List<EmpresaProtecao> resultado = empresaProtecaoService.buscarEmpresasProtecaoPorFiltro(filtro);
        assertEquals(1, resultado.size());
        assertEquals("Transportes e Cia.", resultado.get(0).getRazaoSocial());
    }

    @Test
    public void deveRetornarUmaEmpresaEspecificaQuandoInformadoUmaEmpresaExistenteNoFiltro() {
        FiltroEmpresaProtecao filtro = new FiltroEmpresaProtecao();
        filtro.setRazaoSocial("Transportes");
        List<EmpresaProtecao> resultado = empresaProtecaoService.buscarEmpresasProtecaoPorFiltro(filtro);
        assertEquals(1, resultado.size());
        assertEquals("Transportes e Cia.", resultado.get(0).getRazaoSocial());
    }
    //</editor-fold>

    @Test
    public void deveRetornarTodasAsEmpresasExistentes() {
        List<EmpresaProtecao> resultado = empresaProtecaoService.buscarEmpresasProtecao();
        assertEquals(2, resultado.size());
    }
    
    @Test
    public void deveRetornarTodasAsEmpresasAtivaExistentes() {
        List<EmpresaProtecao> resultado = empresaProtecaoService.buscarEmpresasProtecaoAtiva();
        assertEquals(1, resultado.size());
        assertEquals(true, resultado.get(0).getAtiva().booleanValue());
    }
    
    @Test
    public void deveRetornarTodasAsEmpresasExistentesPorTipoServico() {
        List<EmpresaProtecao> resultado = empresaProtecaoService.buscarEmpresasProtecaoPorTipoServico(TipoServicoProtecao.HOSPEDAGEM);
        assertEquals(1, resultado.size());
    }
    
    @Test
    public void deveRetornarTodasAsEmpresasAtivaExistentesPorTipoServicoNomeOuCnpj() {
        List<EmpresaProtecao> resultado = empresaProtecaoService.buscarEmpresasProtecaoAtivaPorTipoNomeCnpj(TipoServicoProtecao.HOSPEDAGEM, "Transportes", "25285531000107");
        assertEquals(1, resultado.size());
    }

    @Test
    public void deveRetornarUmaEmpresaPorSeuId() {
        assertNotNull(empresaProtecaoService.buscarEmpresaProtecaoPorId(3001L));
    }
    
    @Test
    public void deveRetornarUmaEmpresaPorSeuCnpj() {
        assertNotNull(empresaProtecaoService.buscarEmpresaProtecaoPorCnpj("25285531000107"));
    }
    
    @Test
    public void deveSalvarQuandoCnpjNaoExiste() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.setId(11000l);
        assertNotNull(empresaProtecaoService.salvarEmpresaProtecao(empresa));
    }
    
    @Test(expected = BusinessException.class)
    public void deveInvalidarQuandoCnpjJaExiste() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.setCnpj("05864866000164");
        empresaProtecaoService.salvarEmpresaProtecao(empresa);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private EmpresaProtecao obterEmpresaValida(){
        return EmpresaProtecaoBuilder.novaEmpresaProtecao()
                .comId(null)
                .comRazaoSocial("Hotel Salvador")
                .comCnpj("85411551000109")
                .comEmail("salvador@hotel.com.br")
                .comTelefone("7123423423")
                .comCidade("SALVADOR")
                .comEstado("BA")
                .comServicos(TipoServicoProtecao.HOSPEDAGEM)
                .build();
    }
    //</editor-fold>
}
