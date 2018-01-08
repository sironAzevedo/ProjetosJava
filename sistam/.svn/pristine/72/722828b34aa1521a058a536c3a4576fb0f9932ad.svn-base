package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciaContatoBuilder;
import br.com.petrobras.sistam.test.builder.AgenciaOrgaoDespachoBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

/**
 *
 */
@DataSet("/datasets/AgenciaService.xml")
public class AgenciaServiceIT extends BaseIntegrationTestClass {

    @SpringBean("AgenciaServiceImpl")
    private AgenciaService agenciaService;

    @SpringBean("CommonServiceImpl")
    private CommonService commonService;
    
    @Test
    public void deveSalvarAgencia() {
        Agencia agencia = obterAgenciaValida();
        agencia = agenciaService.salvarAgencia(agencia);
        assertNotNull(agencia.getId());
    }
    
    @Test
    public void deveRetornarOsContatosAtivosPorAgencia() {
        Agencia agencia = obterAgenciaValida();
        List<RepresentanteLegal> contatos = agenciaService.buscarContatosPorAgencia(agencia, true);
        assertFalse(contatos.isEmpty());
        Assert.assertEquals(contatos.size(), 2);
    }
    
    @Test
    public void deveRetornarVazioQuandoContatosInativosPorAgencia() {
        Agencia agencia = obterAgenciaValida();
        List<RepresentanteLegal> contatos = agenciaService.buscarContatosPorAgencia(agencia, false);
        assertTrue(contatos.isEmpty());
    }

    @Test
    public void deveSalvarContatoDaAgencia() {
        RepresentanteLegal agenciaContato = obterAgenciaContatoValido();
        agenciaContato.setAgencia(agenciaService.buscarAgenciaPorId(1L));
        agenciaContato = agenciaService.salvarRepresentanteLegal(agenciaContato);
        assertNotNull(agenciaContato.getId());
    }
    
    @Test
    public void deveExcluirContatoDaAgencia() {
        RepresentanteLegal contato = (RepresentanteLegal) commonService.buscarEntidadePorId(RepresentanteLegal.class, 1L);
        agenciaService.excluirAgenciaContato(contato);
        contato = (RepresentanteLegal) commonService.buscarEntidadePorId(RepresentanteLegal.class, 1L);
        assertNull(contato);
    }

    @Test
    public void deveSalvarOrgaoDespachoDaAgencia() {
        AgenciaOrgaoDespacho agenciaOrgaoDespacho = obterAgenciaOrgaoDespacho();
        agenciaOrgaoDespacho.setAgencia(agenciaService.buscarAgenciaPorId(1L));
        agenciaOrgaoDespacho = agenciaService.salvarAgenciaOrgaoDespacho(agenciaOrgaoDespacho);
        assertNotNull(agenciaOrgaoDespacho.getId());
    }
    
    @Test
    public void deveExcluirOrgaoDespachoDaAgencia() {
        AgenciaOrgaoDespacho agenciaOrgaoDespacho = (AgenciaOrgaoDespacho) commonService.buscarEntidadePorId(AgenciaOrgaoDespacho.class, 1L);
        agenciaService.excluirAgenciaOrgaoDespacho(agenciaOrgaoDespacho);
        agenciaOrgaoDespacho = (AgenciaOrgaoDespacho) commonService.buscarEntidadePorId(AgenciaOrgaoDespacho.class, 1L);
        assertNull(agenciaOrgaoDespacho);
    }
    
    @Test
    public void deveRetornarOrgaosDaAgencia(){
        Agencia agencia = agenciaService.buscarAgenciaPorId(1L);
        List<AgenciaOrgaoDespacho> orgaos = agenciaService.buscarOrgaosDespachoPorAgencia(agencia);
        assertNotNull(orgaos);
        List<Long> ids = new ArrayList<Long>(Arrays.asList(1L, 2L));
        assertTrue(orgaos.size() == 2);
        for(AgenciaOrgaoDespacho orgao : orgaos) {
            assertTrue(ids.contains(orgao.getId()));
        }
    }
    
    
    @Test
    public void deveRetornarAsAgencias(){
        List<Agencia> agencias = agenciaService.buscarAgencias();
        assertNotNull(agencias);
        List<Long> ids = new ArrayList<Long>(Arrays.asList(1L, 2L, 6L));
        assertTrue(agencias.size() == 3);
        for(Agencia agencia : agencias) {
            assertTrue(ids.contains(agencia.getId()));
        }
    }
    
    
    @Test
    public void deveRetornarAgenciaDoId() {
        Agencia agencia = agenciaService.buscarAgenciaPorId(1L);
        assertEquals("SALVADOR", agencia.getCidade());
    }
    
    @Test
    public void deveRetornarAgenciaDaSigla() {
        Agencia agencia = agenciaService.buscarAgenciaPorSigla("SAL");
        assertEquals("SALVADOR", agencia.getCidade());
    }
    
    @Test
    public void deveRetornarAAgenciaSigoPeloNomePassado(){
        List<AgenciaSigo> agencias = agenciaService.buscarAgenciaSigoPorNome("MARÍTIMA");
        assertEquals(1, agencias.size());
        assertEquals(Long.valueOf(8450), agencias.get(0).getId());
    }

    @Test
    public void deveRetornarAAgenciaSigoPorId(){
        AgenciaSigo agencia = agenciaService.buscarAgenciaSigoPorId(8450L);
        assertNotNull(agencia);
        assertEquals(Long.valueOf(8450), agencia.getId());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Destinatários da Agência">
    @Test
    public void deveSalvarODestinatario(){
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Destinatario destinatario = new Destinatario();
        destinatario.setAgencia(agencia);
        destinatario.setNome("PETROBRAS");
        destinatario.setEmail("petrobras@petrobras.com.br");
        
        destinatario = agenciaService.salvarDestinatario(destinatario);
        
        assertNotNull(commonService.buscarEntidadePorId(Destinatario.class, destinatario.getId()));
    }
    
    @Test
    public void deveExcluirODestinatario(){
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Destinatario destinatario = new Destinatario();
        destinatario.setId(1L);
        destinatario.setAgencia(agencia);
        destinatario.setNome("PETROBRAS");
        destinatario.setEmail("petrobras@petrobras.com.br");
        
        destinatario = agenciaService.excluirDestinatario(destinatario);
        
        assertNull(commonService.buscarEntidadePorId(Destinatario.class, destinatario.getId()));
    }
    
    
    @Test
    public void deveRetornarOsDestinatariosDaAgencia(){
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        
        List<Destinatario> lista = agenciaService.buscarDestinatariosDaAgencia(agencia);
        
        assertTrue(lista.size() == 1);
        assertEquals(Long.valueOf(1), lista.get(0).getId());
    }
    //</editor-fold>
    
    private Agencia obterAgenciaValida() {
        Agencia agencia = AgenciaBuilder.novaAgencia()
                .comId(1L)
                .comSigla("SSE")
                .comNome("TESTE")
                .comEndereco("Teste")
                .comTelefone("99999999")
                .comEmail("teste@teste.com.br")
                .comCidade("Teste")
                .comEstado("BA")
                .build();
        return agencia;
    }

    private RepresentanteLegal obterAgenciaContatoValido() {
        RepresentanteLegal agenciaContato = AgenciaContatoBuilder.novaAgenciaContato()
                .comId(1L)
                .daAgencia(obterAgenciaValida())
                .comNome("Teste")
                .comCpf("1235L")
                .build();
        return agenciaContato;
    }
    
    private AgenciaOrgaoDespacho obterAgenciaOrgaoDespacho() {
        AgenciaOrgaoDespacho agenciaOrgaoDespacho = AgenciaOrgaoDespachoBuilder.novaAgenciaOrgaoDespacho()
                .comId(1L)
                .daAgencia(obterAgenciaValida())
                .comNome("TESTE")
                .build();
        return agenciaOrgaoDespacho;
    }
    
    
}
