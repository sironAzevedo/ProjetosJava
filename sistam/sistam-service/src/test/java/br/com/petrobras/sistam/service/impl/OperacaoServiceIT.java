package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.OperacaoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.enums.UnidadeMedida;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioSiscomex;
import br.com.petrobras.sistam.common.valueobjects.RelatorioSiscomexVO;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoLongoCursoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoOperacaoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoProdutoBuilder;
import br.com.petrobras.sistam.test.builder.OperacaoBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.sistam.test.builder.ProdutoBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import static org.junit.Assert.*;

@DataSet("/datasets/OperacaoService.xml")
public class OperacaoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("OperacaoServiceImpl")
    private OperacaoService operacaoService;
    @SpringBean("AgenciaServiceImpl")
    private AgenciaService agenciaService;
    @SpringBean("CommonServiceImpl")
    private CommonService commonService;

    //<editor-fold defaultstate="collapsed" desc="Operação">
    @Test
    public void deveRetornarOperacoesPorAgenciamentoETipo() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(827L).build();
        List<Operacao> operacoes = operacaoService.buscarOperacoesPorAgenciamentoETipo(agenciamento, TipoOperacao.CARGA_CABOTAGEM);
        assertEquals(2, operacoes.size());
    }

    @Test
    public void aoSerSalvaUmaNovaOperacaoDeveSerPersistidaNoBanco() {
        Operacao operacao = obterOperacaoValida();
        operacao = operacaoService.salvarOperacao(operacao);
        assertNotNull(operacao.getId());
    }

    @Test
    public void aoSerSalvaUmaNovaOperacaoCargaExportacaoDeveSerPersistidaNoBanco() {
        Operacao operacao = obterOperacaoValida();
        operacao.setTipoOperacao(TipoOperacao.CARGA_EXPORTACAO);
        operacao = operacaoService.salvarOperacao(operacao);
        assertNotNull(operacao.getId());
    }

    @Test
    public void aoExcluirUmaOperacaoDeveSerRemovidaDaBase() {
        Operacao operacao = obterOperacaoValida();
        operacao.setId(8283L);
        operacaoService.excluirOperacao(operacao);
        assertNull(commonService.buscarEntidadePorId(Operacao.class, operacao.getId()));
    }

    @Test
    public void aoExcluirUmaOperacaoJaAssociadaAUmDocumentoDeveOcorrerUmErro() {
        Operacao operacao = obterOperacaoValida();
        operacaoService.excluirOperacao(operacao);
        assertNull(commonService.buscarEntidadePorId(Operacao.class, operacao.getId()));
    }

    @Test
    public void deveRetornarAsOperacoesDoAgenciamento() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(827L).build();
        List<Operacao> operacoes = operacaoService.buscarOperacoesPorAgenciamento(agenciamento);

        assertTrue(operacoes.size() == 2);
        List<Long> ids = new ArrayList<Long>(Arrays.asList(8271L, 8272L));
        for (Operacao operadao : operacoes) {
            assertTrue(ids.contains(operadao.getId()));
        }
    }

    @Test
    public void deveRetornarAOperacaoDoId() {
        Operacao operacao = operacaoService.buscarOperacaoPorId(8271L);
        assertNotNull(operacao);
        assertEquals(8271L, operacao.getId().longValue());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Documentação da operação">
    @Test
    public void aoSerSalvaUmaNovaDocumentacaoOperacaoDeveSerPersistidaNoBanco() {
        DocumentacaoCabotagem documentacaoOperacao = obterDocumentacaoOperacaoValida();
        documentacaoOperacao = operacaoService.salvarDocumentacaoCabotagem(documentacaoOperacao);
        assertNotNull(documentacaoOperacao.getId());
    }

    @Test
    public void deveExcluirDocumentacaoCabotagem() {
        operacaoService.excluirDocumentacaoCabotagem(operacaoService.buscarDocumentacaoCabotagemPorId(8283L));
        assertNull(operacaoService.buscarDocumentacaoCabotagemPorId(8283L));
    }

    @Test
    public void deveRetornarAsDocumentacoesDoAgenciamentoDeAcordoComOTipoCargaCabotagemInformado() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(827L).build();
        List<DocumentacaoCabotagem> list = operacaoService.buscarDocumentacaoCabotagemPorAgenciamentoETipo(agenciamento, TipoOperacao.CARGA_CABOTAGEM);

        assertEquals(2, list.size());
        List<Long> ids = new ArrayList<Long>(Arrays.asList(8271L, 8272L));
        for (DocumentacaoCabotagem documentacaoOperadao : list) {
            assertTrue(ids.contains(documentacaoOperadao.getId()));
        }
    }

    @Test
    public void deveRetornarAsDocumentacoesDoAgenciamentoDeAcordoComOTipoDescargaCabotagemInformado() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(828L).build();
        List<DocumentacaoCabotagem> list = operacaoService.buscarDocumentacaoCabotagemPorAgenciamentoETipo(agenciamento, TipoOperacao.DESCARGA_CABOTAGEM);

        assertEquals(1, list.size());
        assertEquals(8284, list.get(0).getId().longValue());
    }

    @Test
    public void deveRetornarADocumentacaoOperacaoDoId() {
        DocumentacaoCabotagem documentacaoOperacao = operacaoService.buscarDocumentacaoCabotagemPorId(8271L);
        assertNotNull(documentacaoOperacao);
        assertEquals(8271, documentacaoOperacao.getId().longValue());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Produto da documentação">
    @Test
    public void aoSerSalvaUmNovoProdutoDaDocumentacaoDeveSerPersistidaNoBanco() {
        DocumentacaoOperacao documentacaoproduto = obterDocumentacaoProdutoValida();
        documentacaoproduto = operacaoService.salvarDocumentacaoProduto(documentacaoproduto);
        assertNotNull(documentacaoproduto.getId());
    }

    @Test
    public void deveRetornarOsProdutosDadocumentacao() {
        DocumentacaoCabotagem documentacaoOperacao = operacaoService.buscarDocumentacaoCabotagemPorId(8271L);
        List<DocumentacaoOperacao> list = operacaoService.buscarDocumentacaoOperacaoPorDocumentacao(documentacaoOperacao);

        List<Long> ids = new ArrayList<Long>(Arrays.asList(8271L));
        assertEquals(1, list.size());
        for (DocumentacaoOperacao documentacaoProduto : list) {
            assertTrue(ids.contains(documentacaoProduto.getId()));
        }
    }

    @Test
    public void deveRetornarOsProdutosDadocumentacaoPorId() {
        DocumentacaoOperacao documentacaoProduto = operacaoService.buscarDocumentacaoOperacaoPorId(8271L);
        assertNotNull(documentacaoProduto);
        assertEquals(8271L, documentacaoProduto.getId().longValue());

    }

    @Test
    public void deveExcluirProdutoDaDocumentacao() {
        DocumentacaoOperacao documentacaoProduto = operacaoService.buscarDocumentacaoOperacaoPorId(8271L);
        operacaoService.excluirDocumentacaoOperacao(documentacaoProduto);
        documentacaoProduto = operacaoService.buscarDocumentacaoOperacaoPorId(8271L);
        assertNull(documentacaoProduto);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Documentação Longo Curso">
    @Test
    public void aoSerSalvaUmNovaDocumentacaoLongoCursoDeveSerPersistidaNoBanco() {
        DocumentacaoLongoCurso documentacaoLongoCurso = obterDocumentacaoLongoCursoValida();
        documentacaoLongoCurso = operacaoService.salvarDocumentacaoLongoCurso(documentacaoLongoCurso);
        assertNotNull(documentacaoLongoCurso.getId());
    }

    @Test
    public void aoExcluirUmaDocumentacaoLongoCursoDeveSerRemovidaDaBase() {
        DocumentacaoLongoCurso documentacaoLongoCurso = (DocumentacaoLongoCurso) commonService.buscarEntidadePorId(DocumentacaoLongoCurso.class, 1L);
        documentacaoLongoCurso.setOperacao(obterOperacaoValida());
        operacaoService.excluirDocumentacaoLongoCurso(documentacaoLongoCurso);
        assertNull(commonService.buscarEntidadePorId(DocumentacaoLongoCurso.class, 1L));
    }

    @Test
    public void deveRetornarAsDocumentacoesLongoCurso() {
        Operacao operacao = operacaoService.buscarOperacaoPorId(8271L);
        List<DocumentacaoLongoCurso> listaDocumentacaoLongoCurso = operacaoService.buscarDocumentacaoLongoCursoPorOperacao(operacao);
        assertTrue(listaDocumentacaoLongoCurso.size() == 1);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Documentações para Relatorio Siscomex">
    @Test
    public void deveRetornarDocumentacoesDoTipoCargaCabotagem() {
        FiltroRelatorioSiscomex filtro = new FiltroRelatorioSiscomex();
        filtro.setTiposOperacao(new ArrayList<TipoOperacao>(Arrays.asList(new TipoOperacao[]{TipoOperacao.CARGA_CABOTAGEM})));
        RelatorioSiscomexVO vo = operacaoService.buscarDocumentosParaRelatorioSiscomex(filtro);
        assertNotNull(vo);
        assertEquals(2, vo.getCargasCabotagem().size());
    }

    @Test
    public void deveRetornarDocumentacoesDoTipoDescargaCabotagem() {
        FiltroRelatorioSiscomex filtro = new FiltroRelatorioSiscomex();
        filtro.setTiposOperacao(new ArrayList<TipoOperacao>(Arrays.asList(new TipoOperacao[]{TipoOperacao.DESCARGA_CABOTAGEM})));
        RelatorioSiscomexVO vo = operacaoService.buscarDocumentosParaRelatorioSiscomex(filtro);
        assertNotNull(vo);
        assertEquals(1, vo.getDescargasCabotagem().size());
    }

    @Test
    public void deveRetornarDocumentacoesDoTipoSemOperacaoComercial() {
        FiltroRelatorioSiscomex filtro = new FiltroRelatorioSiscomex();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(2L).build());
        filtro.setPorto(PortoBuilder.novoPorto().comId("FLZA").build());
        filtro.setTiposContrato(Arrays.asList(TipoContrato.VCP));
        filtro.setSituacoesEmbarcacao(Arrays.asList(StatusEmbarcacao.NO_PORTO));
        filtro.setComEscalaMercante(TipoSimNao.NAO);
        filtro.setTiposOperacao(new ArrayList<TipoOperacao>(Arrays.asList(new TipoOperacao[]{TipoOperacao.SEM_OPERACAO_COMERCIAL})));
        RelatorioSiscomexVO vo = operacaoService.buscarDocumentosParaRelatorioSiscomex(filtro);
        assertNotNull(vo);
        assertEquals(2, vo.getSemOperacoesComerciais().size());
    }

    @Test
    public void deveRetornarDocumentacoesDoTipoLongoCursoCargaExportacao() {
        FiltroRelatorioSiscomex filtro = new FiltroRelatorioSiscomex();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(2L).build());
        filtro.setPorto(PortoBuilder.novoPorto().comId("FLZA").build());
        filtro.setTiposContrato(Arrays.asList(TipoContrato.VCP));
        filtro.setSituacoesEmbarcacao(Arrays.asList(StatusEmbarcacao.NO_PORTO));
        filtro.setComEscalaMercante(TipoSimNao.NAO);
        filtro.setComManisfesto(TipoSimNao.NAO);
        filtro.setTiposOperacao(new ArrayList<TipoOperacao>(Arrays.asList(new TipoOperacao[]{TipoOperacao.CARGA_EXPORTACAO})));
        RelatorioSiscomexVO vo = operacaoService.buscarDocumentosParaRelatorioSiscomex(filtro);
        assertNotNull(vo);
        assertEquals(1, vo.getOperacoesSemDocumentacaoCargaExportacao().size());
    }

    @Test
    public void naoDeveRetornarDocumentacoesDoTipoLongoCursoCargaExportacao() {
        FiltroRelatorioSiscomex filtro = new FiltroRelatorioSiscomex();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(2L).build());
        filtro.setPorto(PortoBuilder.novoPorto().comId("FLZA").build());
        filtro.setTiposContrato(Arrays.asList(TipoContrato.VCP));
        filtro.setSituacoesEmbarcacao(Arrays.asList(StatusEmbarcacao.NO_PORTO));
        filtro.setComEscalaMercante(TipoSimNao.SIM);
        filtro.setComManisfesto(TipoSimNao.NAO);
        filtro.setTiposOperacao(new ArrayList<TipoOperacao>(Arrays.asList(new TipoOperacao[]{TipoOperacao.CARGA_EXPORTACAO})));
        RelatorioSiscomexVO vo = operacaoService.buscarDocumentosParaRelatorioSiscomex(filtro);
        assertNull(vo);
    }

    @Test
    public void deveRetornarDocumentacoesParaRelatorioSiscomex() {
        FiltroRelatorioSiscomex filtro = new FiltroRelatorioSiscomex();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(2l).build());
        filtro.setComEscalaMercante(TipoSimNao.NAO);
        filtro.setComManisfesto(TipoSimNao.SIM);
        filtro.setOrdenacao(FiltroRelatorioSiscomex.TipoOrdenacaoSiscomex.POR_NAVIO_VIAGEM);
        filtro.setSituacoesEmbarcacao(new ArrayList<StatusEmbarcacao>(Arrays.asList(new StatusEmbarcacao[]{StatusEmbarcacao.NO_PORTO})));
        filtro.setTiposContrato(new ArrayList<TipoContrato>(Arrays.asList(new TipoContrato[]{TipoContrato.TCP})));
        filtro.setTiposOperacao(new ArrayList<TipoOperacao>(Arrays.asList(new TipoOperacao[]{TipoOperacao.CARGA_CABOTAGEM})));

        RelatorioSiscomexVO vo = operacaoService.buscarDocumentosParaRelatorioSiscomex(filtro);
        assertNotNull(vo);
        assertEquals(2, vo.getCargasCabotagem().size());
    }
    //</editor-fold>

    private Operacao obterOperacaoValida() {

        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).comVersao(1L).comAgencia(agencia).build();

        Operacao operacao = OperacaoBuilder.novaOperacao()
                .comId(1L)
                .comProduto(ProdutoBuilder.novoProduto().comId("28W").build())
                .comQuantidadeEstimada(10D)
                .comTipoOperacao(TipoOperacao.CARGA_CABOTAGEM)
                .doAgenciamento(agenciamento)
                .comUnidadeDeMedida(UnidadeMedida.TONELADA)
                .build();
        return operacao;
    }

    private DocumentacaoCabotagem obterDocumentacaoOperacaoValida() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).comVersao(1L).comAgencia(agencia).build();
        AgenciaSigo agenciaSigo = agenciaService.buscarAgenciaSigoPorId(8450L);
        DocumentacaoOperacao produto = DocumentacaoProdutoBuilder.novaDocumentacaoProduto().comConhecimentoEletronico("XPTO")
                .daOperacao(OperacaoBuilder.novaOperacao().comId(8283L).build()).build();
        DocumentacaoCabotagem documentacaoOperacao = DocumentacaoOperacaoBuilder.novaDocumentacaoOperacao()
                .comId(1L)
                .comTipoOperacao(TipoOperacao.DESCARGA_CABOTAGEM)
                .doAgenciamento(agenciamento)
                .comAgencia(agenciaSigo)
                .comManifestoEletronico("1233")
                .build();
        documentacaoOperacao.adicionarDocumentacaoProduto(produto);
        return documentacaoOperacao;
    }

    private DocumentacaoOperacao obterDocumentacaoProdutoValida() {
        DocumentacaoOperacao documentacaoProduto = DocumentacaoProdutoBuilder.novaDocumentacaoProduto()
                .comId(1L)
                .daDocumentacao(obterDocumentacaoOperacaoValida())
                .daOperacao(obterOperacaoValida())
                .comConhecimentoEletronico("123456")
                .build();
        return documentacaoProduto;
    }

    private DocumentacaoLongoCurso obterDocumentacaoLongoCursoValida() {
        Porto porto = PortoBuilder.novoPorto().comId("CODA").comPais("012").build();
        DocumentacaoLongoCurso documentacaoLongoCurso = DocumentacaoLongoCursoBuilder.novaOperacaoLongoCurso()
                .comId(1L)
                .daOperacao(obterOperacaoValida())
                .comConhecimentoEletronico("123456")
                .comManifestoEletronico("121212")
                .doPorto(porto)
                .comCidade("TESTE")
                .comBlRecebido(false)
                .build();
        return documentacaoLongoCurso;
    }
}
