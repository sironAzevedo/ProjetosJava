package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.PortoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.PaisBuilder;
import br.com.petrobras.sistam.test.builder.PontoAtracacaoBuilder;
import br.com.petrobras.sistam.test.builder.PontoOperacionalBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/PortoService.xml")
public class PortoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("PortoServiceImpl")
    private PortoService portoService;
    @SpringBean("AgenciaServiceImpl")
    private AgenciaService agenciaService;
    @SpringBean("CommonServiceImpl")
    private CommonService commonService;

    @Test
    public void deveBuscarPortosPorNomeEPais() {
        List<Porto> portos = portoService.buscarPortos("SALVADOR", PaisBuilder.novoPais().comId(Pais.CODIGO_BRASIL).build());
        assertFalse(portos.isEmpty());
    }

    @Test
    public void deveRetornarAgenciasPortoPorAgencia() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        List<AgenciaPorto> agenciasPorto = portoService.buscarAgenciasPortosPorAgencia(agencia);
        assertFalse(agenciasPorto.isEmpty());
    }

    @Test
    public void deveRetornarPaisPorId() {
        Pais pais = portoService.buscarPaisPorId(Pais.CODIGO_BRASIL);
        assertNotNull(pais);
    }

    @Test
    public void deveRetornarPaisNuloQuandoIdNaoExistir() {
        Pais pais = portoService.buscarPaisPorId("NOT");
        assertNull(pais);
    }

    @Test
    public void deveRetornarPortoDoId() {
        Porto porto = portoService.buscarPortosPorId("SALV");
        assertEquals("SALVADOR", porto.getNomeCompleto());
    }

    @Test
    public void deveRetornarPortoQuandoRecarregarOProprio() {
        Porto porto = PortoBuilder.novoPorto().comId("SALV").build();
        porto = portoService.buscarPorto(porto);
        assertNotNull(porto);
    }

    @Test
    public void deveRetornarPortoNuloQuandoIdNaoExistir() {
        Porto porto = portoService.buscarPortosPorId("XXXX");
        assertNull(porto);
    }

    @Test
    public void deveRetornarPortoNuloQuandoIdInformadoForNulo() {
        Porto porto = portoService.buscarPortosPorId(null);
        assertNull(porto);
    }

    @Test
    public void deveRetornarPontoOperacionalDoId() {
        PontoOperacional ponto = portoService.buscarPontoOperacionalPorId("MADR");
        assertEquals("MADRE DE DEUS", ponto.getNome());
    }

    @Test
    public void deveRetornarPontosOperacionaisDoPorto() {
        Porto porto = portoService.buscarPortosPorId("SALV");
        List<PontoOperacional> pontos = portoService.buscarPontoOperacionalPorPorto(porto);
        assertFalse(pontos.isEmpty());
    }

    @Test
    public void deveRetornarPontoAtracacaoDoId() {
        PontoAtracacao ponto = portoService.buscarPontoAtracacaoPorId(11L);
        assertNotNull(ponto);
        assertEquals(11, (long) ponto.getId());
    }

    @Test
    public void deveRetornarPontosAtracacaoDoPontoOperacional() {
        PontoOperacional ponto = portoService.buscarPontoOperacionalPorId("MADR");
        List<PontoAtracacao> pontos = portoService.buscarPontoAtracacaoPorPontoOperacional(ponto);
        assertEquals(1, pontos.size());
    }

    @Test
    public void deveRetornarPontosAtracacaoDoPorto() {
        Porto porto = portoService.buscarPortosPorId("MAUS");
        List<PontoAtracacao> pontos = portoService.buscarPontosAtracacaolPorPorto(porto);
        assertEquals(1, pontos.size());
    }

    @Test
    public void deveRetornarPontosAtracacaoDaAgencia() {
        Agencia agencia = agenciaService.buscarAgenciaPorSigla("SAL");
        List<PontoAtracacao> pontos = portoService.buscarPontoAtracacaoPorAgencia(agencia);
        assertEquals(2, pontos.size());
    }

    @Test
    public void deveRetornarOsPortosDeAcordoComAAgenciaInformada() {
        Agencia agencia = agenciaService.buscarAgenciaPorSigla("MAN");
        List<Porto> portos = portoService.buscarPortosPorAgencia(agencia);
        List<String> listaIdEsperada = new ArrayList<String>(Arrays.asList("MAUS"));
        assertListaDePortos(portos, listaIdEsperada);
    }
    
    @Test
    public void deveRetornarPortosNacionaisComComplementos(){
        List<Porto> portos = portoService.buscarPortosNacionaisComComplementos();
        assertEquals(1, portos.size());
    }
    
    @Test
    public void deveRetornarPortosComplementos(){
        List<PortoComplemento> portosComplementos = portoService.buscarPortosComplemento();
        assertEquals(1, portosComplementos.size());
    }
    
    @Test
    public void deveRetornarPortoComplementoPorId(){
        PortoComplemento portoComplemento = portoService.buscarPortoComplemento(101L);
        assertNotNull(portoComplemento);
    }
    
    @Test
    public void deveRetornarPortosComplementosPorPorto(){
        Porto porto = PortoBuilder.novoPorto().comId("SALV").build();
        List<PortoComplemento> portosComplementos = portoService.buscarPortosComplementosPorPorto(porto);
        assertEquals(1, portosComplementos.size());
    }
    
    @Test
    public void deveRetornarVazioQuandoQuandoNaoExistirPortosComplementosPorPorto(){
        Porto porto = PortoBuilder.novoPorto().comId("MAUS").build();
        List<PortoComplemento> portosComplementos = portoService.buscarPortosComplementosPorPorto(porto);
        assertTrue(portosComplementos.isEmpty());
    }
    
    @Test
    public void deveRetornarPortoComplementoNuloQuandoNaoExistirPorId(){
        PortoComplemento portoComplemento = portoService.buscarPortoComplemento(1L);
        assertNull(portoComplemento);
    }

    private void assertListaDePortos(List<Porto> listaPortoRetornada, List<String> listaIdEsperado) {
        assertTrue(listaPortoRetornada.size() == listaIdEsperado.size());

        List<String> listaIdRetornado = new ArrayList<String>();
        for (Porto porto : listaPortoRetornada) {
            listaIdRetornado.add(porto.getId());
        }

        for (String idEsperado : listaIdEsperado) {
            assertTrue(listaIdRetornado.contains(idEsperado));
        }
    }

    @Test
    public void deveSalvarOPontoAtracacao() {
        PontoAtracacao pontoAtracacao = obterPontoAtracacaoValido();
        portoService.salvarPontoAtracacao(pontoAtracacao);

        assertNotNull(commonService.buscarEntidadePorId(PontoAtracacao.class, pontoAtracacao.getId()));
    }

    private PontoAtracacao obterPontoAtracacaoValido() {
        PontoAtracacao pontoAtracacao = PontoAtracacaoBuilder.novoPontoAtracacao()
                .comNome("SISTAM")
                .doPontoOperacional(PontoOperacionalBuilder.novoPontoOperacional().comId("SALV").build())
                .build();
        return pontoAtracacao;
    }
}
