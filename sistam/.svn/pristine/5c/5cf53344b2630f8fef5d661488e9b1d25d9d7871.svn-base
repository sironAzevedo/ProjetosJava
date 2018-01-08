package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.UnidadeMedida;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoLongoCursoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoOperacaoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoProdutoBuilder;
import br.com.petrobras.sistam.test.builder.OperacaoBuilder;
import br.com.petrobras.sistam.test.builder.PaisBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.sistam.test.builder.ProdutoBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.lang.reflect.Field;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.unitils.util.ReflectionUtils;

public class ValidadorOperacaoTest {

    private ValidadorOperacao validador = new ValidadorOperacao();
    
    //<editor-fold defaultstate="collapsed" desc="Operação">
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarOperacaoSemInformarOAgenciamento() {
        Operacao operacao = obterOperacaoValida();
        operacao.setAgenciamento(null);
        try {
            validador.validarSalvarOperacao(operacao);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.OPERACAO_AGENCIAMENTO_NULO, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmaOperacaoSemInformarOProduto() {
        Operacao operacao = obterOperacaoValida();
        operacao.setProduto(null);
        try {
            validador.validarSalvarOperacao(operacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.OPERACAO_INFORME_PRODUTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmaOperacaoSemInformarAQuantidadeEstimada() {
        Operacao operacao = obterOperacaoValida();
        operacao.setQuatidadeEstimada(null);
        try {
            validador.validarSalvarOperacao(operacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.OPERACAO_INFORME_QUANTIDADE_ESTIMADA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmaOperacaoSemInformarTipoOperacao() {
        Operacao operacao = obterOperacaoValida();
        operacao.setTipoOperacao(null);
        try {
            validador.validarSalvarOperacao(operacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.OPERACAO_INFORME_TIPO_OPERACAO, pendencias.get(0).getMessage());
        }
    }
    
    
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Documentação da Operação">

    @Test
    public void naoSeraPossivelSalvarUmaDocumentacaoOperacaoDeCargaCabotagemSemInformarOPorto() {
        DocumentacaoCabotagem documentacaoOperacao = obterDocumentacaoOperacaoValida();
        documentacaoOperacao.setTipoOperacao(TipoOperacao.CARGA_CABOTAGEM);
        documentacaoOperacao.setPorto(null);
        try {
            validador.validarSalvarDocumentacaoOperacao(documentacaoOperacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_PORTO_DESTINO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmaDocumentacaoOperacaoDeCargaCabotagemSemInformarUmPortoNacional() {
        DocumentacaoCabotagem documentacaoOperacao = obterDocumentacaoOperacaoValida();
        documentacaoOperacao.setTipoOperacao(TipoOperacao.CARGA_CABOTAGEM);
        documentacaoOperacao.getPorto().setPais(PaisBuilder.novoPais().comId("044").build()); //Informa um porto diferente de BRASIL (019)
        
        try {
            validador.validarSalvarDocumentacaoOperacao(documentacaoOperacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_OPERACAO_PORTO_DEVE_SER_NACIONAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmaDocumentacaoOperacaoDeDescargaCabotagemSemInformarAAgencia() {
        DocumentacaoCabotagem documentacaoOperacao = obterDocumentacaoOperacaoValida();
        documentacaoOperacao.setTipoOperacao(TipoOperacao.DESCARGA_CABOTAGEM);
        documentacaoOperacao.setAgencia(null);
        try {
            validador.validarSalvarDocumentacaoOperacao(documentacaoOperacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_AGENCIA_ORIGEM, pendencias.get(0).getMessage());
        }
    }
    
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarDocumentacaoOperacaoSemInformarOAgenciamento() {
        DocumentacaoCabotagem documentacaoOperacao = obterDocumentacaoOperacaoValida();
        documentacaoOperacao.setAgenciamento(null);
        try {
            validador.validarSalvarDocumentacaoOperacao(documentacaoOperacao);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.DOCUMENTACAO_OPERACAO_AGENCIAMENTO_NULO, ex.getMessage());
        }
    }
    
    
    @Test
    public void naoSeraPossivelSalvarUmaDocumentacaoOperacaoSemInformarTipoOperacao() {
        DocumentacaoCabotagem documentacaoOperacao = obterDocumentacaoOperacaoValida();
        documentacaoOperacao.setTipoOperacao(null);
        try {
            validador.validarSalvarDocumentacaoOperacao(documentacaoOperacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_TIPO_OPERACAO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmaDocumentacaoOperacaoSemInformarManifestoEletronico() {
        DocumentacaoCabotagem documentacaoOperacao = obterDocumentacaoOperacaoValida();
        documentacaoOperacao.setManifestoEletronico(null);
        try {
            validador.validarSalvarDocumentacaoOperacao(documentacaoOperacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_MANIFESTO_ELETRONICO, pendencias.get(0).getMessage());
        }
        
        documentacaoOperacao.setManifestoEletronico("");
        try {
            validador.validarSalvarDocumentacaoOperacao(documentacaoOperacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_MANIFESTO_ELETRONICO, pendencias.get(0).getMessage());
        }
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Produto da documentação">

    
    @Test
    public void deveRetornarExcecaoQuandoSalvarProdutoDaDocumentacaoSemInformarADocumentacao() {
        DocumentacaoOperacao documentacaoProduto = obterDocumentacaoProdutoValida();
        documentacaoProduto.setDocumentacaoOperacao(null);
        try {
            validador.validarSalvarDocumentacaoProduto(documentacaoProduto);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_PRODUTO_DOCUMENTACAO_OPERACAO_NULL, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoSalvarProdutoDaDocumentacaoSemInformarAOperacao() {
        DocumentacaoOperacao documentacaoProduto = obterDocumentacaoProdutoValida();
        documentacaoProduto.setOperacao(null);
        try {
            validador.validarSalvarDocumentacaoProduto(documentacaoProduto);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_PRODUTO_OPERACAO_NULL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmProdutoDaDocumentacaoSemInformarConhecimentoEletronico() {
        DocumentacaoOperacao documentacaoProduto = obterDocumentacaoProdutoValida();
        documentacaoProduto.setConhecimentoEletronico(null);
        try {
            validador.validarSalvarDocumentacaoProduto(documentacaoProduto);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_PRODUTO_INFORME_CONHECIMENTO_ELETRONICO, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Documentação Longo Curso">
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarDocumentacaoLongoCursoSemInformarOperacao() {
        DocumentacaoLongoCurso documentacaoLongoCurso = obterDocumentacaoLongoCursoValida();
        documentacaoLongoCurso.setOperacao(null);
        try {
            validador.validarSalvarDocumentacaoLongoCurso(documentacaoLongoCurso);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_OPERACAO_NULA, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarDocumentacaoLongoCursoSemInformarPorto() {
        DocumentacaoLongoCurso documentacaoLongoCurso = obterDocumentacaoLongoCursoValida();
        documentacaoLongoCurso.setPorto(null);
        try {
            validador.validarSalvarDocumentacaoLongoCurso(documentacaoLongoCurso);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_INFORME_PORTO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPossivelSalvarDocumentacaoLongoCursoComPortoDoBrasil() {
        DocumentacaoLongoCurso documentacaoLongoCurso = obterDocumentacaoLongoCursoValida();
        Porto porto = PortoBuilder.novoPorto().comId("TEST").comPais(Pais.CODIGO_BRASIL).build();
        documentacaoLongoCurso.setPorto(porto);
        try {
            validador.validarSalvarDocumentacaoLongoCurso(documentacaoLongoCurso);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_PAIS_DEVE_SER_DIFERENTE_BRASIL, pendencias.get(0).getMessage());
        }
    }
    
    
    @Test
    public void naoSeraPossivelSalvarDocumentacaoLongoCursoSemManifestoEletronico() {
        DocumentacaoLongoCurso documentacaoLongoCurso = obterDocumentacaoLongoCursoValida();
        documentacaoLongoCurso.setManifestoEletronico(null);
        try {
            validador.validarSalvarDocumentacaoLongoCurso(documentacaoLongoCurso);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_INFORME_MANIFESTO_ELETRONICO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarDocumentacaoLongoCursoSemInformarPeloMenosUmConhecimentoEletronico() {
        DocumentacaoLongoCurso documentacaoLongoCurso = obterDocumentacaoLongoCursoValida();
        
        Field field = ReflectionUtils.getFieldWithName(DocumentacaoLongoCurso.class, "listaConhecimentoEletronico", false);
        ReflectionUtils.setFieldValue(documentacaoLongoCurso, field, "");
        
        try {
            validador.validarSalvarDocumentacaoLongoCurso(documentacaoLongoCurso);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_INFORME_CONHECIMENTO_ELETRONICO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPossivelSalvarDocumentacaoLongoCursoSemCidade() {
        DocumentacaoLongoCurso documentacaoLongoCurso = obterDocumentacaoLongoCursoValida();
        documentacaoLongoCurso.setCidade(null);
        try {
            validador.validarSalvarDocumentacaoLongoCurso(documentacaoLongoCurso);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.OPERACAO);
            assertEquals(ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_INFORME_CIDADE, pendencias.get(0).getMessage());
        }
    }
    
    
    //</editor-fold>
    
    
    private Operacao obterOperacaoValida() {
        Operacao operacao = OperacaoBuilder.novaOperacao()
                .comId(1L)
                .comProduto(ProdutoBuilder.novoProduto().build())
                .comQuantidadeEstimada(10D)
                .comTipoOperacao(TipoOperacao.CARGA_CABOTAGEM)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build())
                .comUnidadeDeMedida(UnidadeMedida.TONELADA)
                .build();
        return operacao;
    }
    

    private DocumentacaoCabotagem obterDocumentacaoOperacaoValida() {
        Porto porto = PortoBuilder.novoPorto().comPais(Pais.CODIGO_BRASIL).build();
        DocumentacaoCabotagem documentacaoOperacao = DocumentacaoOperacaoBuilder.novaDocumentacaoOperacao()
                .comId(1L)
                .comTipoOperacao(TipoOperacao.CARGA_CABOTAGEM)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build())
                .comManifestoEletronico("1233")
                .comPorto(porto)
                .build();
        return documentacaoOperacao;
    }
    
    private DocumentacaoOperacao obterDocumentacaoProdutoValida() {
        DocumentacaoOperacao documentacaoProduto = DocumentacaoProdutoBuilder.novaDocumentacaoProduto()
                .comId(1L)
                .daDocumentacao(new DocumentacaoCabotagem())
                .daOperacao(new Operacao())
                .comConhecimentoEletronico("123456")
                .build();
        return documentacaoProduto;
    }

    
    private DocumentacaoLongoCurso obterDocumentacaoLongoCursoValida() {
        Porto porto = PortoBuilder.novoPorto().comPais("TESTE").build();
        DocumentacaoLongoCurso documentacaoLongoCurso = DocumentacaoLongoCursoBuilder.novaOperacaoLongoCurso()
                .comId(1L)
                .daOperacao(new Operacao())
                .comConhecimentoEletronico("123456")
                .comManifestoEletronico("121212")
                .doPorto(porto)
                .comCidade("TESTE")
                .comBlRecebido(false)
                .build();
        return documentacaoLongoCurso;
    }
    
    
    
}
