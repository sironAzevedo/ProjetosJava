package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.validator.ValidadorOperacao;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoLongoCursoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoOperacaoBuilder;
import br.com.petrobras.sistam.test.builder.DocumentacaoProdutoBuilder;
import br.com.petrobras.sistam.test.builder.OperacaoBuilder;
import br.com.petrobras.sistam.test.builder.ProdutoBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;

public class OperacaoServiceTest {
    private OperacaoServiceImpl operacaoService;
    private GenericDao dao;
    private PendenciaServiceImpl pendenciaService;
    private DocumentoServiceImpl documentoService;

    @Before
    public void setup() {
        dao = mock(GenericDao.class);
        pendenciaService = mock(PendenciaServiceImpl.class);
        documentoService = mock(DocumentoServiceImpl.class);
        operacaoService = new OperacaoServiceImpl();
        operacaoService.setDao(dao);
        operacaoService.setPendenciaService(pendenciaService);
        operacaoService.setDocumentoService(documentoService);
        carregarValidadorComMock();
        carregarValidadorPermissaoComMock();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Operação">
    @Test
    public void deveChamarOsValidadoresQuandoForSalvarAOperacao() {
        Operacao operacao = OperacaoBuilder.novaOperacao()
                .doAgenciamento(new Agenciamento())
                .build();
        operacaoService.salvarOperacao(operacao);
        Mockito.verify(obterValidadorPermissao()).podeSalvarOperacao(Mockito.any(Agencia.class));
        Mockito.verify(obterValidador()).validarSalvarOperacao(operacao);
    }
    
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarOperacao(){
        Operacao operacao = OperacaoBuilder.novaOperacao()
                .doAgenciamento(new Agenciamento())
                .build();
        operacaoService.salvarOperacao(operacao);
        Mockito.verify(dao).saveOrUpdate(operacao);
    }
   
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Excluir operação">
    @Test
    public void deveChamarOsValidadoresQuandoForExcluirAOperacao() {
        Operacao operacao = OperacaoBuilder.novaOperacao()
                .doAgenciamento(new Agenciamento())
                .build();
        operacaoService.excluirOperacao(operacao);
        Mockito.verify(obterValidadorPermissao()).podeExcluirOperacao(Mockito.any(Agencia.class));
    }
    
    @Test
    public void deveChamarORemoveDoDaoQuandoForExcluirAOperacao() {
        Operacao operacao = OperacaoBuilder.novaOperacao()
                .doAgenciamento(new Agenciamento())
                .build();
        operacaoService.excluirOperacao(operacao);
        Mockito.verify(dao).remove(operacao);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Documentação da operação">
    
    @Test
    public void deveChamarOsValidadoresQuandoForSalvarDocumentacaoOperacao() {
        DocumentacaoCabotagem documentacaoOperacao = DocumentacaoOperacaoBuilder.novaDocumentacaoOperacao()
                .doAgenciamento(new Agenciamento())
                .build();
        operacaoService.salvarDocumentacaoCabotagem(documentacaoOperacao);
        Mockito.verify(obterValidadorPermissao()).podeSalvarDocumentacaoCabotagem(Mockito.any(Agencia.class));
        Mockito.verify(obterValidador()).validarSalvarDocumentacaoOperacao(documentacaoOperacao);
    }
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarDocumentacaoOperacao(){
        DocumentacaoCabotagem documentacaoOperacao = DocumentacaoOperacaoBuilder.novaDocumentacaoOperacao()
                .doAgenciamento(new Agenciamento())
                .build();
        operacaoService.salvarDocumentacaoCabotagem(documentacaoOperacao);
        Mockito.verify(dao).saveOrUpdate(documentacaoOperacao);
    }
    
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Produto da documentação">
    
    @Test
    public void deveChamarOsValidadoresQuandoForSalvarDocumentacaoProduto() {
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build();
        DocumentacaoOperacao documentacaoProduto = DocumentacaoProdutoBuilder.novaDocumentacaoProduto()
                .daOperacao(obterOperacaoValida())
                .daDocumentacao(DocumentacaoOperacaoBuilder.novaDocumentacaoOperacao().doAgenciamento(agenciamento).build())
                .build();
        operacaoService.salvarDocumentacaoProduto(documentacaoProduto);
        Mockito.verify(obterValidadorPermissao()).podeSalvarDocumentacaoCabotagem(Mockito.any(Agencia.class));
        Mockito.verify(obterValidador()).validarSalvarDocumentacaoProduto(documentacaoProduto);
    }
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarDocumentacaoProduto(){
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build();
        DocumentacaoOperacao documentacaoProduto = DocumentacaoProdutoBuilder.novaDocumentacaoProduto()
                .daOperacao(obterOperacaoValida())
                .daDocumentacao(DocumentacaoOperacaoBuilder.novaDocumentacaoOperacao().doAgenciamento(agenciamento).build())
                .build();
        operacaoService.salvarDocumentacaoProduto(documentacaoProduto);
        Mockito.verify(dao).saveOrUpdate(documentacaoProduto);
    }
    
    
    
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Documentação Longo Curso">
    
    @Test
    public void deveChamarOsValidadoresQuandoForSalvarDocumentacaoLongoCurso() {
        DocumentacaoLongoCurso documentacaoLongoCurso = DocumentacaoLongoCursoBuilder.novaOperacaoLongoCurso()
                .daOperacao(obterOperacaoValida())
                .build();
        operacaoService.salvarDocumentacaoLongoCurso(documentacaoLongoCurso);
        Mockito.verify(obterValidadorPermissao()).podeSalvarDocumentacaoCabotagem(Mockito.any(Agencia.class));
        Mockito.verify(obterValidador()).validarSalvarDocumentacaoLongoCurso(documentacaoLongoCurso);
    }
    

    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoSalvarDocumentacaoLongoCurso(){
        DocumentacaoLongoCurso documentacaoLongoCurso = DocumentacaoLongoCursoBuilder.novaOperacaoLongoCurso()
                .daOperacao(obterOperacaoValida())
                .build();
        operacaoService.salvarDocumentacaoLongoCurso(documentacaoLongoCurso);
        Mockito.verify(dao).saveOrUpdate(documentacaoLongoCurso);
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoForExcluirDocumentacaoLongoCurso() {
        DocumentacaoLongoCurso documentacaoLongoCurso = DocumentacaoLongoCursoBuilder.novaOperacaoLongoCurso()
                .daOperacao(obterOperacaoValida())
                .build();
        operacaoService.excluirDocumentacaoLongoCurso(documentacaoLongoCurso);
        Mockito.verify(obterValidadorPermissao()).podeExcluirDocumentacaoCabotagem(Mockito.any(Agencia.class));
    }
    
    @Test
    public void deveChamarORemoveDoDaoQuandoForExcluirDocumentacaoLongoCurso() {
        DocumentacaoLongoCurso documentacaoLongoCurso = DocumentacaoLongoCursoBuilder.novaOperacaoLongoCurso()
                .daOperacao(obterOperacaoValida())
                .build();
        operacaoService.excluirDocumentacaoLongoCurso(documentacaoLongoCurso);
        Mockito.verify(dao).remove(documentacaoLongoCurso);
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock() {
        ValidadorOperacao  validador = Mockito.mock(ValidadorOperacao.class);
        Field field = ReflectionUtils.getFieldWithName(OperacaoServiceImpl.class, "validador", false);
        ReflectionUtils.setFieldValue(operacaoService, field, validador);
    }
    
    private void carregarValidadorPermissaoComMock() {
        ValidadorPermissao validadorPermissao = Mockito.mock(ValidadorPermissao.class);
        Mockito.when(validadorPermissao.podeSalvarOperacao(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeExcluirOperacao(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarDocumentacaoCabotagem(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeExcluirDocumentacaoCabotagem(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(OperacaoServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(operacaoService, field, validadorPermissao);
    }
    
    private ValidadorOperacao obterValidador() {
        Field field = ReflectionUtils.getFieldWithName(OperacaoServiceImpl.class, "validador", false);
        return (ReflectionUtils.getFieldValue(operacaoService, field));
    }
    
    private ValidadorPermissao obterValidadorPermissao() {
        Field field = ReflectionUtils.getFieldWithName(OperacaoServiceImpl.class, "validadorPermissao", false);
        return (ReflectionUtils.getFieldValue(operacaoService, field));
    }
    
    private Operacao obterOperacaoValida() {
        Operacao operacao = OperacaoBuilder.novaOperacao()
                .comId(1L)
                .comProduto(ProdutoBuilder.novoProduto().build())
                .comQuantidadeEstimada(10D)
                .comTipoOperacao(TipoOperacao.CARGA_CABOTAGEM)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build())
                .build();
        return operacao;
    }
    
    
    //</editor-fold>
    
}
