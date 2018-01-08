package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.DocumentoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.service.mock.AcessoServiceMock;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentoBuilder;
import br.com.petrobras.sistam.test.builder.ManobraBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/DocumentoService.xml")
public class DocumentoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("DocumentoServiceImpl")
    private DocumentoService documentoSerivice;

    @SpringBean("CommonServiceImpl")
    private CommonService commonSerivice;

    @SpringBean("AcessoServiceImpl")
    private AcessoServiceMock acessoServiceMock;

    @Test
    public void deveBuscarDocumentoDoAgenciamentoPorTipoSemProtocolo() {
        List<Documento> docs = documentoSerivice.buscarDocumentoDoAgenciamentoPorTipoSemProtocolo(TipoDocumento.PARTE_SAIDA, AgenciamentoBuilder.novoAgenciamento().comId(1L).build());
        assertEquals(2, docs.size());
    }

    @Test
    public void deveBuscarDocumentoPorTipoAgenciamentoCtacPortoDestino() {
        Documento doc = documentoSerivice.buscarDocumentoPorTipoAgenciamentoCtacPortoDestino(TipoDocumento.PARTE_SAIDA, AgenciamentoBuilder.novoAgenciamento().comId(1L).build(), "123",
                PortoBuilder.novoPorto().comId("SALV").build());
        assertNotNull(doc);
    }

    //<editor-fold defaultstate="collapsed" desc="Busca de Documentos">
    @Test
    public void deveRetornarDocumentoPorId() {
        assertNotNull(documentoSerivice.buscarDocumentoPorId(4l));
    }

    @Test
    public void deveRetornarODocumentoNaoProtocoladoDoAgenciamento() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).build();
        Documento documento = null;
        List<Documento> docs = documentoSerivice.buscarDocumentoDoAgenciamentoPorTipo(TipoDocumento.PARTE_SAIDA, agenciamento);
        if (docs != null && !docs.isEmpty()) {
            documento = docs.get(0);
        }

        assertEquals(Long.valueOf(2), documento.getId());
    }

    @Test
    public void deveRetornarOsDocumentosDoAgenciamento() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).build();
        List<Documento> documentos = documentoSerivice.buscarDocumentosDoAgenciamento(agenciamento);

        List<Long> listaIdEsperada = Arrays.asList(new Long[]{2L, 4L, 31L});

        assertListaDeDocumento(documentos, listaIdEsperada);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Salvar Documento">
    @Test
    public void deveSalvarDocumento() {
        Documento documento = obterDocumentoValido();
        documento = documentoSerivice.salvarDocumento(documento);
        assertNotNull(documento.getId());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Excluir Documento">
    @Test
    public void deveExcluirODocumento() {
        //Prepara o documento para exclusão.
        Documento documento = obterDocumentoValido();
        documento.setRepresentante(null);

        documentoSerivice.excluirDocumento(documento);

        documento = (Documento) commonSerivice.buscarEntidadePorId(Documento.class, 1L);
        assertNull(documento);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Registro de Emissão de Documento do Agenciamento">
    @Test
    public void deveRegistrarAEmissaoDeUmNovoDocumentoDeUmAgenciamento() {
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");

        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).build();
        TipoDocumento tipo = TipoDocumento.DECLARACAO_GERAL_ENTRADA;

        Documento documento = documentoSerivice.registrarEmissaoDeDocumento(tipo, agenciamento, false);

        documento = (Documento) commonSerivice.buscarEntidadePorId(Documento.class, documento.getId());

        assertNotNull(documento);
    }

    @Test
    public void deveAtualizarODocumentoDoAgenciamentoAoRegistrarAEmissaoDeUmDocumentoExistenteENaoProtocolado() {
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");

        //Registra a emissão de um documento que já emitido e que ainda não foi protocolado
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).build();
        TipoDocumento tipo = TipoDocumento.PARTE_SAIDA;

        Documento documento = documentoSerivice.registrarEmissaoDeDocumento(tipo, agenciamento, false);

        documento = (Documento) commonSerivice.buscarEntidadePorId(Documento.class, documento.getId());

        //O documento, antes do registro, tinha a chave do emitente X4IJ. Após, deve ficar co a chave STAM
        assertEquals("STAM", documento.getChaveEmitente());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Registro de Emissão de Documento da Manobra">
    @Test
    public void deveRegistrarAEmissaoDeUmNovoDocumentoDeUmaManobra() {
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");

        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).build();
        Manobra manobra = ManobraBuilder.novaManobra().comId(1L).doAgenciamento(agenciamento).build();
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        RepresentanteLegal representante = new RepresentanteLegal();
        representante.setId(100L);
        Documento documento = documentoSerivice.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);

        documento = (Documento) commonSerivice.buscarEntidadePorId(Documento.class, documento.getId());

        assertNotNull(documento);
    }

    @Test
    public void deveAtualizarODocumentoDaManobraAoRegistrarAEmissaoDeUmDocumentoExistenteENaoProtocolado() {
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");

        //Registra a emissão de um documento que já emitido e que ainda não foi protocolado
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).build();
        Manobra manobra = ManobraBuilder.novaManobra().comId(1L).doAgenciamento(agenciamento).build();
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        RepresentanteLegal representante = new RepresentanteLegal();
        representante.setId(100L);
        Documento documento = documentoSerivice.registrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);

        documento = (Documento) commonSerivice.buscarEntidadePorId(Documento.class, documento.getId());

        //O documento, antes do registro, tinha a chave do emitente X4IJ. Após, deve ficar co a chave STAM
        assertEquals("STAM", documento.getChaveEmitente());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Informação do protocolo">
    @Test
    public void deveSalvarOsDadosInformadosNaProtocolacaoDeUmDocumento() {
        Date dataProtocolo = DateBuilder.on(1, 10, 2013).getTime();

        Documento documento = obterDocumentoValido(); //obtém um documento válido, que tem id = 2 não protocolado;
        documento.setDataProtocolo(dataProtocolo);
        documento.setNumeroDocumento("98765");

        documento = documentoSerivice.informarProtocoloDoDocumento(documento);

        documento = (Documento) commonSerivice.buscarEntidadePorId(Documento.class, documento.getId());

        assertTrue(dataProtocolo.compareTo(documento.getDataProtocolo()) == 0);
        assertEquals("98765", documento.getNumeroDocumento());
    }

    @Test
    public void quandoExistirPendenciaDoDocumentoEstaDeveSerReslvida() {
        Documento documento = DocumentoBuilder.novoDocumento(obterDocumentoValido())
                .comId(2L)
                .comDataProtocolo(DateBuilder.on(1, 10, 2013).getTime())
                .comNumeroDocumento("98765").build();

        documentoSerivice.informarProtocoloDoDocumento(documento);

        //Verifica se a pendencia, que estava não resolvida, foi resolvida.
        Pendencia pendencia = (Pendencia) commonSerivice.buscarEntidadePorId(Pendencia.class, 1L);

        assertTrue(pendencia.isResolvida());

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private Documento obterDocumentoValido() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).comTimeZone("America/Bahia").build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).comAgencia(agencia).build();

        Documento documento = DocumentoBuilder.novoDocumento()
                .comId(2L)
                .comAgenciamento(agenciamento)
                .comTipo(TipoDocumento.PARTE_ENTRADA)
                .comDataDeEmissao(new Date())
                .comChaveEmitente("STAM")
                .comNomeEmitente("SISTAM - Agenciamento Marítimo")
                .build();
        return documento;
    }

    private void assertListaDeDocumento(List<Documento> listaDocumentoRetornada, List<Long> listaIdEsperado) {
        assertTrue(listaDocumentoRetornada.size() == listaIdEsperado.size());

        List<Long> listaIdRetornado = new ArrayList<Long>();
        for (Documento docuemnto : listaDocumentoRetornada) {
            listaIdRetornado.add(docuemnto.getId());
        }

        for (Long idEsperado : listaIdEsperado) {
            assertTrue(listaIdRetornado.contains(idEsperado));
        }
    }

    //</editor-fold>
}
