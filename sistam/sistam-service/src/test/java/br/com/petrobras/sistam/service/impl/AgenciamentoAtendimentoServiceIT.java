package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.business.AgenciamentoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamentoAtendimento;
import br.com.petrobras.sistam.common.valueobjects.RelatorioAgenciamentoAtendimentoVO;
import br.com.petrobras.sistam.service.mock.AcessoServiceMock;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;


@DataSet("/datasets/AgenciamentoAtendimentoService.xml")
public class AgenciamentoAtendimentoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("AgenciamentoServiceImpl")
    private AgenciamentoService agenciamentoService;

    @SpringBean("AgenciaServiceImpl")
    private AgenciaService agenciaService;
    
    @SpringBean("AcessoServiceImpl")
    private AcessoServiceMock acessoServiceMock;
    
    public void setUp(){
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");
    }

    //<editor-fold defaultstate="collapsed" desc="Busca de agenciamento">
    @Test
    public void deveRetornarOsAtendimentosDoAgenciamentoComCicloDeSeteDias() {
        
        Agencia agencia = agenciaService.buscarAgenciaPorId(1L);
        TimeZone zone = TimeZone.getTimeZone(agencia.getTimezone());
        Date dataInicio = SistamDateUtils.informarDataHora(01, 01, 2014, 12, 0, zone);
        Date dataFim = SistamDateUtils.informarDataHora(31, 1, 2014, 12, 0, zone);
        
        FiltroAgenciamentoAtendimento filtro = new FiltroAgenciamentoAtendimento();
        filtro.setAgencia(agencia);
        filtro.setQtdeDiasAtendimento(7);
        filtro.setDataInicial(dataInicio);
        filtro.setDataFinal(dataFim);
        filtro.setTipoContrato(TipoContrato.TCP);
        filtro.setPorto(PortoBuilder.novoPorto().comId("MAUS").build());
        
        List<RelatorioAgenciamentoAtendimentoVO> list = agenciamentoService.buscarAgenciamentosRelatorioAtendimento(filtro);
        assertNotNull(list);
        assertEquals(2, list.size());        
        
        List<Long> ids = new ArrayList<Long>(Arrays.asList(826L, 827L));
        
        for(RelatorioAgenciamentoAtendimentoVO vo : list) {
            assertTrue(ids.contains(vo.getAgenciamento().getId()));
            if (vo.getAgenciamento().getId() == 826L) {
                assertEquals(Integer.valueOf(4), vo.getQuantidadeAtendimentos());
                assertEquals(Integer.valueOf(31), vo.getQuantidadeDias());
            } else {
                assertEquals(Integer.valueOf(2), vo.getQuantidadeAtendimentos());
                assertEquals(Integer.valueOf(12), vo.getQuantidadeDias());
            }
        }
        
    }
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    
    //</editor-fold>
    
        
    
}
