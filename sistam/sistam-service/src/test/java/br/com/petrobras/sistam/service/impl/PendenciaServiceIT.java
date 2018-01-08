package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import static br.com.petrobras.sistam.test.builder.AgenciamentoBuilder.novoAgenciamento;
import br.com.petrobras.sistam.test.builder.DocumentoBuilder;
import br.com.petrobras.sistam.test.builder.TaxaBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;


@DataSet("/datasets/PendenciaService.xml")
public class PendenciaServiceIT extends BaseIntegrationTestClass {

    @SpringBean("PendenciaServiceImpl")
    private PendenciaService service;
    
    @SpringBean("CommonServiceImpl")
    private CommonService commonService;
    
    
    @Test
    public void deveRetornarPendenciasDoAgenciamentoPorTipo(){
        Agenciamento agenciamento = novoAgenciamento().comId(827L).build();
        assertEquals(1, service.buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.PARTE_ENTRADA).size());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Busca das pendências do agenciamento">
    @Test
    public void deveRetornarAsPendenciasDoAgenciamentoInformado(){
        Agenciamento agenciamento = novoAgenciamento().comId(827L).build();
        List<Pendencia> listaPendencias = service.buscarPendenciasDoAgenciamento(agenciamento, null);
        
        List<Long> listaIdEsperada = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L));
        assertListaDePendencias(listaPendencias, listaIdEsperada);
    }
    
    @Test
    public void deveRetornarAsPendenciasDoAgenciamentoInformadoResolvidas(){
        Agenciamento agenciamento = novoAgenciamento().comId(827L).build();
        List<Pendencia> listaPendencias = service.buscarPendenciasDoAgenciamento(agenciamento, Boolean.TRUE);
        
        List<Long> listaIdEsperada = new ArrayList<Long>(Arrays.asList(3L));
        assertListaDePendencias(listaPendencias, listaIdEsperada);
    }
    
    @Test
    public void deveRetornarAsPendenciasDoAgenciamentoInformadoNaoResolvidas(){
        Agenciamento agenciamento = novoAgenciamento().comId(827L).build();
        List<Pendencia> listaPendencias = service.buscarPendenciasDoAgenciamento(agenciamento, Boolean.FALSE);
        
        List<Long> listaIdEsperada = new ArrayList<Long>(Arrays.asList(1L, 2L));
        assertListaDePendencias(listaPendencias, listaIdEsperada);
    }
    
    @Test
    public void deveRetornarAPendenciaDoAgenciamentoDeAcordoComOTipoInformado(){
        Agenciamento agenciamento = novoAgenciamento().comId(827L).build();
        TipoPendencia tipo = TipoPendencia.PAGAMENTO_LP;
        Pendencia pendencia = service.buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(agenciamento, tipo);
        
        assertEquals(2, pendencia.getId().intValue());
    }
    
        @Test
        public void deveRetornarVazioQuandoAPendenciaDoAgenciamentoEstaResolvida(){
            Agenciamento agenciamento = novoAgenciamento().comId(828L).build();
            TipoPendencia tipo = TipoPendencia.CONTROLE_SANITARIO;
            Pendencia pendencia = service.buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(agenciamento, tipo);
            
            assertNull(pendencia);
        }

    @Test
    public void deveRetornarNuloAoBuscarUmaPendenciaDoAgenciamentoPorTipoInformandoUmAgenciamentoNulo(){
        Agenciamento agenciamento = null;
        TipoPendencia tipo = TipoPendencia.CONTROLE_SANITARIO;
        Pendencia pendencia = service.buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(agenciamento, tipo);
        
        assertNull(pendencia);
    }
    
    @Test
    public void deveRetornarNuloAoBuscarUmaPendenciaDoAgenciamentoPorTipoInformandoUmTipoNulo(){
        Agenciamento agenciamento = novoAgenciamento().comId(827L).build();
        TipoPendencia tipo = null;
        Pendencia pendencia = service.buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(agenciamento, tipo);
        
        assertNull(pendencia);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Criação de pendências">
    @Test
    public void deveCriarUmaNovaPendencia(){
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(827l).build();
        Pendencia pendencia = service.criarPendencia(agenciamento, TipoPendencia.PARTE_SAIDA);
        
        pendencia = (Pendencia) commonService.buscarEntidadePorId(Pendencia.class, pendencia.getId());
        assertNotNull(pendencia);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Resolução de pendências">
    @Test
    public void deveSalvarAPendenciaComoResolvidaManualmenteNoBanco(){
        Pendencia pendencia = (Pendencia)commonService.buscarEntidadePorId(Pendencia.class, 2L);
        
        service.resolverPendenciaManual(pendencia);
        
        pendencia = (Pendencia)commonService.buscarEntidadePorId(Pendencia.class, 2L);
        assertEquals(Boolean.TRUE, pendencia.isResolvida());
    }
    
    @Test
    public void deveSalvarAPendenciaDoDocumentoAposSuaResolucao(){
        //Prepara o teste
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(827L).build();
        Documento documento = DocumentoBuilder.novoDocumento()
                .comId(1L).comTipo(TipoDocumento.PARTE_ENTRADA).comAgenciamento(agenciamento).build();
        
        Pendencia pendencia = service.resolverPendenciaDoDocumento(documento);
        
        //Retornar a pendência de id 1, que não está resolvida e não tem documento.
        pendencia = (Pendencia)commonService.buscarEntidadePorId(Pendencia.class, pendencia.getId());
        assertEquals(Boolean.TRUE, pendencia.isResolvida());
        assertNotNull(pendencia.getDocumento());
    }
    
    @Test
    public void deveSalvarAPendenciaDaTaxaAposSuaResolucao(){
        //Prepara o teste
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(827L).build();
        Taxa taxa = TaxaBuilder.novaTaxa()
                .comId(1L).doTipo(TipoTaxa.LIVRE_PRATICA_ANVISA).doAgenciamento(agenciamento).build();
        
        Pendencia pendencia = service.resolverPendenciaDaTaxa(taxa);
        
        //Retornar a pendência de id 2, que não está resolvida e não tem documento.
        pendencia = (Pendencia)commonService.buscarEntidadePorId(Pendencia.class, pendencia.getId());
        assertEquals(Boolean.TRUE, pendencia.isResolvida());
        assertNotNull(pendencia.getTaxa());
    }
    
    //</editor-fold>
    
    @Test
    public void deveExcluirAPendencia(){
        Pendencia pendencia = new Pendencia();
        pendencia.setAgenciamento(AgenciamentoBuilder.novoAgenciamento().comId(828L).build());
        pendencia.setTipoPendencia(TipoPendencia.COMUNICACAO_CHEGADA);
        pendencia.setId(1L);
        
        pendencia = service.excluirPendencia(pendencia);
        
        assertNull(commonService.buscarEntidadePorId(Pendencia.class, pendencia.getId()));
    }
    
    private void assertListaDePendencias(List<Pendencia> listaPendenciasRetornada, List<Long> listaIdEsperado){
       assertTrue(listaPendenciasRetornada.size() == listaIdEsperado.size());
       
       List<Long> listaIdRetornado = new ArrayList<Long>();
       for (Pendencia agenciamentoPendencia : listaPendenciasRetornada){
           listaIdRetornado.add(agenciamentoPendencia.getId());
       }
       
       for (Long idEsperado : listaIdEsperado){
           assertTrue(listaIdRetornado.contains(idEsperado));
       }
    }
    
}
