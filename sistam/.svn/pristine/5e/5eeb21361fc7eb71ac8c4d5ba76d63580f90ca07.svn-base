package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.business.EmpresaProtecaoService;
import br.com.petrobras.sistam.common.business.PessoaProtecaoService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroPessoaProtecao;
import br.com.petrobras.sistam.test.builder.PessoaProtecaoBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/PessoaProtecaoService.xml")
public class PessoaProtecaoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("PessoaProtecaoServiceImpl")
    private PessoaProtecaoService pessoaProtecaoService;
    @SpringBean("EmpresaProtecaoServiceImpl")
    private EmpresaProtecaoService empresaProtecaoService;

    //<editor-fold defaultstate="collapsed" desc="Buscar Pessoas por Filtro">
    @Test
    public void deveRetornarTodasAsPessoasExistentesQuandoNaoHouverFiltros() {
        FiltroPessoaProtecao filtro = instanciarFiltroPesquisa();
        List<Pessoa> resultado = pessoaProtecaoService.buscarPessoasProtecaoPorFiltro(filtro);
        assertEquals(2, resultado.size());
    }

    @Test
    public void deveRetornarUmaPessoaEspecificaQuandoInformadoUmNomeParcialExistenteNoFiltro() {
        FiltroPessoaProtecao filtro = instanciarFiltroPesquisa();
        filtro.setNome("aria");
        List<Pessoa> resultado = pessoaProtecaoService.buscarPessoasProtecaoPorFiltro(filtro);
        assertEquals(1, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());
    }

    @Test
    public void deveRetornarUmaPessoaEspecificaQuandoInformadoUmCpfExistenteNoFiltro() {
        FiltroPessoaProtecao filtro = instanciarFiltroPesquisa();
        filtro.setCpf("47008722338");
        List<Pessoa> resultado = pessoaProtecaoService.buscarPessoasProtecaoPorFiltro(filtro);
        assertEquals(1, resultado.size());
        assertEquals("Joao", resultado.get(0).getNome());
    }

    @Test
    public void deveRetornarUmaPessoaEspecificaQuandoInformadoUmDocumentoExistenteNoFiltro() {
        FiltroPessoaProtecao filtro = instanciarFiltroPesquisa();
        filtro.setDocumento("12312313");
        List<Pessoa> resultado = pessoaProtecaoService.buscarPessoasProtecaoPorFiltro(filtro);
        assertEquals(1, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());
    }

    @Test
    public void deveRetornarUmaPessoaEspecificaQuandoInformadoUmaDataDeNascimentoExistenteNoFiltro() {
        FiltroPessoaProtecao filtro = instanciarFiltroPesquisa();
        filtro.setDataNascimento(SistamDateUtils.informarDataHora(1, 1, 1980, 1, 2, null));
        List<Pessoa> resultado = pessoaProtecaoService.buscarPessoasProtecaoPorFiltro(filtro);
        assertEquals(1, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());
    }

    @Test
    public void deveRetornarUmaPessoaEspecificaQuandoInformadoUmaEmpresaExistenteNoFiltro() {
        FiltroPessoaProtecao filtro = instanciarFiltroPesquisa();
        filtro.setEmpresa(empresaProtecaoService.buscarEmpresaProtecaoPorId(900l));
        List<Pessoa> resultado = pessoaProtecaoService.buscarPessoasProtecaoPorFiltro(filtro);
        assertEquals(1, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());
    }
    //</editor-fold>

    @Test
    public void deveRetornarUmaPessoaPorSeuId() {
        Pessoa pessoa = pessoaProtecaoService.buscarPessoaProtecaoPorId(90);
        assertNotNull(pessoa);
        assertEquals("Maria", pessoa.getNome());
    }
    
    @Test
    public void deveRetornarUmaPessoaPorSeuCpf() {
        Pessoa pessoa = pessoaProtecaoService.buscarPessoaProtecaoPorCpf("47008722338");
        assertNotNull(pessoa);
        assertEquals("Joao", pessoa.getNome());
    }

    @Test
    public void deveValidarCpfExistente() {
        Pessoa pessoa = obterPessoaValida();
        assertNotNull(pessoaProtecaoService.salvarPessoaProtecao(pessoa));
    }
    
    @Test
    public void deveRetornarAsPessoasAtivasPorEmpresaENome(){
        EmpresaProtecao empresa = empresaProtecaoService.buscarEmpresaProtecaoPorId(900l);
        List<Pessoa> pessoas = pessoaProtecaoService.buscarPessoasProtecaoAtivaPorEmpresaNome(empresa, "Maria");
        assertEquals(1, pessoas.size());
    }

    @Test(expected = BusinessException.class)
    public void deveInvalidarCpfExistente() {
        Pessoa pessoa = obterPessoaValida();
        pessoa.setCpf("47008722338");
        pessoaProtecaoService.salvarPessoaProtecao(pessoa);
    }

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private Pessoa obterPessoaValida() {
        return PessoaProtecaoBuilder.novaPessoaProtecao()
                .comId(null)
                .comNome("Hotel Salvador")
                .comCpf("34285208407")
                .build();
    }

    private FiltroPessoaProtecao instanciarFiltroPesquisa() {
        return new FiltroPessoaProtecao();
    }
    //</editor-fold>
}
