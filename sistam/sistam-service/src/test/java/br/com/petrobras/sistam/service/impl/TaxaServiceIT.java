package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.business.AgenciamentoService;
import br.com.petrobras.sistam.common.business.PortoService;
import br.com.petrobras.sistam.common.business.TaxaService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.TaxaMensalVO;
import br.com.petrobras.sistam.service.mock.AcessoServiceMock;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.TaxaBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/TaxaService.xml")
public class TaxaServiceIT extends BaseIntegrationTestClass {

    @SpringBean("TaxaServiceImpl")
    private TaxaService taxaSerivice;
    
    @SpringBean("AgenciamentoServiceImpl")
    private AgenciamentoService agenciamentoSerivice;
    
    @SpringBean("AgenciaServiceImpl")
    private AgenciaService agenciaSerivice;
    
    @SpringBean("PortoServiceImpl")
    private PortoService portoService;
    
    @SpringBean("AcessoServiceImpl")
    private AcessoServiceMock acessoServiceMock;

    @Before
    public void setUp() {
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");
    }

    @Test
    public void deveSalvarTaxa() {
        Taxa taxa = obterTaxaValida();
        taxa = taxaSerivice.salvarTaxa(taxa);
        assertNotNull(taxa.getId());
    }

    @Test
    public void deveRetornarAsTaxasDoAgenciamento() {
        Agenciamento agenciamento = agenciamentoSerivice.buscarAgenciamentoPorId(855L);

        List<Taxa> taxas = taxaSerivice.buscarTaxasPorAgenciamento(agenciamento);

        List<Long> listaIdEsperada = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L, 5L, 6L));
        assertListaDeTaxas(taxas, listaIdEsperada);
    }

    @Test
    public void deveRetornarATaxasDoiD() {
        Taxa taxa = taxaSerivice.buscarTaxaPorId(1L);
        assertNotNull(taxa);
        assertTrue(taxa.getId() == 1);

    }

    @Test
    public void deveRetornarAsTaxasDeAcordoComOAgenciamentoEOTipo() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).build();
        TipoTaxa tipo = TipoTaxa.LIVRE_PRATICA_ANVISA;

        List<Taxa> taxas = taxaSerivice.buscarTaxaPorAgenciamentoETipo(agenciamento, tipo);
        List<Long> listaIdEsperada = Arrays.asList(new Long[]{2L, 5L});

        assertListaDeTaxas(taxas, listaIdEsperada);
    }

    @Test
    public void deveExcluirTaxa() {
        Taxa taxa = taxaSerivice.buscarTaxaPorId(1L);
        taxaSerivice.excluirTaxa(taxa);
        taxa = taxaSerivice.buscarTaxaPorId(1L);
        assertNull(taxa);
    }

    @Test
    public void deveRetornarAsTaxasPorFiltroDaAgenciaDoPeriodo() {
        Agencia agencia = agenciaSerivice.buscarAgenciaPorId(1L);
        FiltroTaxa filtro = new FiltroTaxa();
        filtro.setAgencia(agencia);

        Calendar dataInicial = Calendar.getInstance(agencia.obterTimezone());
        dataInicial.set(2013, 9, 17);

        Calendar dataFinal = Calendar.getInstance(agencia.obterTimezone());
        dataFinal.set(2013, 9, 20);

        filtro.setDataPagamentoInicial(dataInicial.getTime());
        filtro.setDataPagamentoFinal(dataFinal.getTime());
        
        List<TaxaMensalVO> taxas = taxaSerivice.buscarTaxasPorFiltro(filtro);
        assertNotNull(taxas);
        assertEquals(taxas.size(), 1);
    }

    @Test
    public void deveRetornarAsTaxasOrdenadasPorTipoComDataInicioEFimPagamento() {
        Agencia agencia = agenciaSerivice.buscarAgenciaPorId(1L);
        FiltroTaxa filtro = new FiltroTaxa();
        filtro.setAgencia(agencia);

        Calendar dataInicial = Calendar.getInstance(agencia.obterTimezone());
        dataInicial.set(2013, 9, 17);

        Calendar dataFinal = Calendar.getInstance(agencia.obterTimezone());
        dataFinal.set(2013, 9, 20);

        filtro.setDataPagamentoInicial(dataInicial.getTime());
        filtro.setDataPagamentoFinal(dataFinal.getTime());

        List<Taxa> taxas = taxaSerivice.buscarTaxasOrdenadasPorTipo(filtro);
        assertNotNull(taxas);
        assertEquals(1, taxas.size());
    }

    @Test
    public void deveRetornarAsTaxasOrdenadasPorTipoComDataInicioPagamento() {
        Agencia agencia = agenciaSerivice.buscarAgenciaPorId(1L);
        FiltroTaxa filtro = new FiltroTaxa();
        filtro.setAgencia(agencia);

        Calendar dataInicial = Calendar.getInstance(agencia.obterTimezone());
        dataInicial.set(2013, 9, 17);

        filtro.setDataPagamentoInicial(dataInicial.getTime());

        List<Taxa> taxas = taxaSerivice.buscarTaxasOrdenadasPorTipo(filtro);
        assertNotNull(taxas);
        assertEquals(5, taxas.size());
    }

    @Test
    public void deveRetornarAsTaxasOrdenadasPorTipoComDataFimPagamento() {
        Agencia agencia = agenciaSerivice.buscarAgenciaPorId(1L);
        FiltroTaxa filtro = new FiltroTaxa();
        filtro.setAgencia(agencia);

        Calendar dataFinal = Calendar.getInstance(agencia.obterTimezone());
        dataFinal.set(2013, 9, 20);

        filtro.setDataPagamentoFinal(dataFinal.getTime());

        List<Taxa> taxas = taxaSerivice.buscarTaxasOrdenadasPorTipo(filtro);
        assertNotNull(taxas);
        assertEquals(1, taxas.size());
    }

    @Test
    public void deveRetornarTaxasDaAgenciaEMesAnoOrdenadasPorTipo() {
        Agencia agencia = agenciaSerivice.buscarAgenciaPorId(1L);
        Porto porto = portoService.buscarPortosPorId("SALV");
        List<TipoTaxa> tiposTaxa = new ArrayList(Arrays.asList(new TipoTaxa[]{TipoTaxa.TUF_TAXA_UTILIZACAO_FAROL_CAPITANIA}));
        List<Taxa> taxas = taxaSerivice.buscarTaxasDaAgenciaEMesAnoOrdenadasPorTipo(agencia, porto, tiposTaxa, 10, 2013);
        assertEquals(1, taxas.size());
    }

    @Test
    public void deveRetornarAsTaxasSolicitadasOrdenadasPorTipo() {
        Agencia agencia = agenciaSerivice.buscarAgenciaPorId(1L);
        FiltroTaxa filtro = new FiltroTaxa();
        filtro.setAgencia(agencia);

        Calendar dataInicial = Calendar.getInstance(agencia.obterTimezone());
        dataInicial.set(2013, 9, 17);

        Calendar dataFinal = Calendar.getInstance(agencia.obterTimezone());
        dataFinal.set(2013, 9, 20);

        filtro.setDataPagamentoInicial(dataInicial.getTime());
        filtro.setDataPagamentoFinal(dataFinal.getTime());
        filtro.setListaTipoTaxa(Arrays.asList(new TipoTaxa[]{TipoTaxa.TUF_TAXA_UTILIZACAO_FAROL_CAPITANIA}));

        List<Taxa> taxas = taxaSerivice.buscarTaxasOrdenadasPorTipo(filtro);
        assertNotNull(taxas);
        assertTrue(taxas.size() == 1);

    }

    @Test
    public void deveRetornarOValorAcumuladoDasTaxasDeAcordoComOFiltroInformado() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        List<TipoTaxa> listaTipoTaxas = Arrays.asList(new TipoTaxa[]{TipoTaxa.TUF_TAXA_UTILIZACAO_FAROL_CAPITANIA, TipoTaxa.LIVRE_PRATICA_ANVISA});

        FiltroTaxa filtro = new FiltroTaxa();
        filtro.setAgencia(agencia);
        filtro.setListaTipoTaxa(listaTipoTaxas);
        filtro.setDataPagamentoInicial(DateBuilder.on(1, 11, 2013).at(0, 0).getTime());
        filtro.setDataPagamentoFinal(DateBuilder.on(30, 11, 2013).at(23, 59).getTime());

        Map<TipoTaxa, Double> map = taxaSerivice.buscarTaxasValorAcumulado(filtro);

        assertEquals(Double.valueOf(50), map.get(TipoTaxa.TUF_TAXA_UTILIZACAO_FAROL_CAPITANIA));
        assertEquals(Double.valueOf(500), map.get(TipoTaxa.LIVRE_PRATICA_ANVISA));
    }

    private Taxa obterTaxaValida() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).comTimeZone("America/Bahia").build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).comAgencia(agencia).build();
        ResponsavelCustoEntity responsavelCusto = ResponsavelCustoBuilder.novoResponsavelCusto().comId(1L).build(); 
        
        Taxa taxa = TaxaBuilder.novaTaxa()
                .comDataPagamento(new Date())
                .comNumeroNecessidadeLiberacaoSap(123L)
                .comResponsavelCusto(responsavelCusto)
                .comValor(1500.00D)
                .doAgenciamento(agenciamento)
                .doTipo(TipoTaxa.OUTRAS_TAXAS)
                .build();
        return taxa;
    }

    private void assertListaDeTaxas(List<Taxa> listaTaxaRetornada, List<Long> listaIdEsperado) {
        assertTrue(listaTaxaRetornada.size() == listaIdEsperado.size());

        List<Long> listaIdRetornado = new ArrayList<Long>();
        for (Taxa taxa : listaTaxaRetornada) {
            listaIdRetornado.add(taxa.getId());
        }

        for (Long idEsperado : listaIdEsperado) {
            assertTrue(listaIdRetornado.contains(idEsperado));
        }
    }
}
