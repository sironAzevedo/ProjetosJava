package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.AnexoService;
import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import br.com.petrobras.sistam.common.enums.PastaAnexo;
import br.com.petrobras.sistam.service.mock.AcessoServiceMock;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.AnexoBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/AnexoService.xml")
public class AnexoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("AnexoServiceImpl")
    private AnexoService anexoSerivice;
    
    @SpringBean("CommonServiceImpl")
    private CommonService commonSerivice;
    
    @SpringBean("AcessoServiceImpl")
    private AcessoServiceMock acessoServiceMock;

      
    @Before
    public void setUp(){
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");
    }
    
    @Test
    public void deveRetornarOsAnexosDoAgenciamento(){
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(2L).build();
        
        List<Anexo> listaAnexo = anexoSerivice.buscarAnexosDoAgenciamento(agenciamento);
        
        List<Long> listaIdEsperada = Arrays.asList(new Long[]{2L, 3L});
        assertListaDeAnexos(listaAnexo, listaIdEsperada);
    }
    
    @Test
    public void deveRetornarOArquivoDoAnexoInformado(){
        Anexo anexo = AnexoBuilder.novoAnexo().comId(3L).build();
        
        Arquivo arquivo = anexoSerivice.buscarArquivoDoAnexo(anexo);
        
        assertEquals(Long.valueOf(3), arquivo.getId());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Anexo">
    @Test
    public void deveSalvarOAnexo() {
        Anexo anexo = obterAnexoValido();
        List<Anexo> listaAnexos = new ArrayList<Anexo>();
        listaAnexos.add(anexo);
        
        listaAnexos = anexoSerivice.salvarAnexos(listaAnexos);
        
        assertNotNull(commonSerivice.buscarEntidadePorId(Anexo.class, listaAnexos.get(0).getId()));
    }
    
    @Test
    public void deveSalvarOArquivoDoAnexo() {
        Anexo anexo = obterAnexoValido();
        List<Anexo> listaAnexos = new ArrayList<Anexo>();
        listaAnexos.add(anexo);
        
        listaAnexos = anexoSerivice.salvarAnexos(listaAnexos);
        
        
        assertNotNull(commonSerivice.buscarEntidadePorId(Arquivo.class, listaAnexos.get(0).getArquivo().getId()));
    }
    
    //</editor-fold>
    
    @Test
    public void deveExcluirOAnexoInformadoESeuArquivo(){
        Arquivo arquivo =(Arquivo)commonSerivice.buscarEntidadePorId(Arquivo.class, 3L);
        Anexo anexo = AnexoBuilder.novoAnexo(obterAnexoValido()).comId(3L).comArquivo(arquivo).build();
        
        anexoSerivice.excluirAnexo(anexo);
        
        assertNull(commonSerivice.buscarEntidadePorId(Arquivo.class, 3L));
        assertNull(commonSerivice.buscarEntidadePorId(Anexo.class, 3L));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private Anexo obterAnexoValido(){
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).comAgencia(agencia).build();
        
        byte[] byes = new byte[]{(byte)1};
        Arquivo arquivo = new Arquivo(byes);
        
        Anexo anexo = AnexoBuilder.novoAnexo()
                .comAgenciamento(agenciamento)
                .comPasta(PastaAnexo.SUPRIMENTOS)
                .comExtensao("pdf")
                .comNome("Coprovante.pdf")
                .comTamanhoEmBytes(1024L)
                .comChaveUsuario("STAM")
                .comNomeUsuario("SISTAM")
                .comArquivo(arquivo)
                .comDataCriacao(new Date())
                .build();
        
        return anexo;
    }
    
     private void assertListaDeAnexos(List<Anexo> listaAnexosRetornada, List<Long> listaIdEsperado){
        assertTrue(listaAnexosRetornada.size() == listaIdEsperado.size());
        
        List<Long> listaIdRetornado = new ArrayList<Long>();
        for (Anexo anexo : listaAnexosRetornada){
            listaIdRetornado.add(anexo.getId());
        }
        
        for (Long idEsperado : listaIdEsperado){
            assertTrue(listaIdRetornado.contains(idEsperado));
        }
     }
    //</editor-fold>
    
    
    
}
