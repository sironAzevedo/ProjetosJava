package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.VisitaService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioVisita;
import br.com.petrobras.sistam.common.valueobjects.RelatorioVisitaVO;
import br.com.petrobras.sistam.common.valueobjects.Usuario;
import br.com.petrobras.sistam.service.mock.AcessoServiceMock;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.VisitaBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/VisitaService.xml")
public class VisitaServiceIT extends BaseIntegrationTestClass {

    @SpringBean("VisitaServiceImpl")
    private VisitaService visitaSerivice;
    @SpringBean("CommonServiceImpl")
    private CommonService commonService;
    @SpringBean("AcessoServiceImpl")
    private AcessoServiceMock acessoServiceMock;

    @Before
    public void setUp() {
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");
    }

    @Test
    public void deveSalvarVisita() {
        Visita visita = obterVisitaValida();
        visita = visitaSerivice.salvarVisita(visita);
        assertNotNull(visita.getId());
    }

    @Test
    public void deveRetornarAsVisitasDoAgenciamentoComTransporte() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).build();

        List<Visita> visitas = visitaSerivice.buscarVisitasDoAgenciamento(agenciamento);

        List<Long> ids = new ArrayList<Long>(Arrays.asList(1L));
        assertEquals(visitas.size(), 2);
        for (Visita visita : visitas) {
            assertTrue(ids.contains(visita.getId()));
            break;
        }
    }

    @Test
    public void deveExcluirVisita() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).build();
        Visita visita = VisitaBuilder.novaVisita(obterVisitaValida()).comId(1L).doAgenciamento(agenciamento).build();

        visitaSerivice.excluirVisita(visita);

        visita = (Visita) commonService.buscarEntidadePorId(Visita.class, 1L);
        assertNull(visita);
    }

    private Visita obterVisitaValida() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).comVersao(1L).comAgencia(agencia).build();

        Visita visita = VisitaBuilder.novaVisita()
                .doAgenciamento(agenciamento)
                .comChaveAgente("STAM")
                .comNomeAgente("SISTAM")
                .comDataInicio(DateBuilder.on(1, 1, 2014).getTime())
                .comDataTermino(DateBuilder.on(30, 1, 2014).getTime())
                .build();
        return visita;
    }

    // <editor-fold desc="Relatório de Visitas">
    @Test
    public void devePesquisarOsTransportesUtilizadosNaVisitaPorAgencia() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();

        FiltroRelatorioVisita filtro = new FiltroRelatorioVisita();
        filtro.setAgencia(agencia);
        List<RelatorioVisitaVO> vos = visitaSerivice.buscarTransportesUtilizadosNaVisita(filtro);
        assertEquals(vos.size(), 2);
    }

    @Test
    public void devePesquisarOsTransportesUtilizadosNaVisitaPorAgenciaEPorto() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Porto porto = PortoBuilder.novoPorto().comId("SALV").build();

        FiltroRelatorioVisita filtro = new FiltroRelatorioVisita();
        filtro.setAgencia(agencia);
        filtro.setPorto(porto);
        List<RelatorioVisitaVO> vos = visitaSerivice.buscarTransportesUtilizadosNaVisita(filtro);
        assertEquals(vos.size(), 2);
    }

    @Test
    public void devePesquisarOsTransportesUtilizadosNaVisitaPorAgenciaERespCusto() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();

        FiltroRelatorioVisita filtro = new FiltroRelatorioVisita(); 
        filtro.setAgencia(agencia);
        filtro.setResponsaveis(Arrays.asList(ResponsavelCustoBuilder.novoResponsavelCusto().comId(1L).build()));
        List<RelatorioVisitaVO> vos = visitaSerivice.buscarTransportesUtilizadosNaVisita(filtro);
        assertEquals(vos.size(), 1);
    }

    @Test
    public void devePesquisarOsTransportesUtilizadosNaVisitaPorAgenciaETpTransporte() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();

        FiltroRelatorioVisita filtro = new FiltroRelatorioVisita();
        filtro.setAgencia(agencia);
        filtro.setTipoTransporte(TipoTransporte.TERRESTRE);
        List<RelatorioVisitaVO> vos = visitaSerivice.buscarTransportesUtilizadosNaVisita(filtro);
        assertEquals(vos.size(), 1);
    }

    @Test
    public void devePesquisarOsTransportesUtilizadosNaVisitaPorAgenciaEData() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();

        FiltroRelatorioVisita filtro = new FiltroRelatorioVisita();
        filtro.setAgencia(agencia);
        filtro.setInicio(DateBuilder.on(10, 12, 2012).getTime());
        filtro.setTermino(DateBuilder.on(10, 12, 2012).getTime());
        List<RelatorioVisitaVO> vos = visitaSerivice.buscarTransportesUtilizadosNaVisita(filtro);
        assertEquals(vos.size(), 2);
    }

    @Test
    public void devePesquisarOsTransportesUtilizadosNaVisitaPorAgenciaEAgente() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();

        FiltroRelatorioVisita filtro = new FiltroRelatorioVisita();
        filtro.setAgencia(agencia);
        filtro.setAgente(new Usuario("XPTO", "Xpto"));
        List<RelatorioVisitaVO> vos = visitaSerivice.buscarTransportesUtilizadosNaVisita(filtro);
        assertEquals(vos.size(), 2);
    }
    // </editor-fold>
}
