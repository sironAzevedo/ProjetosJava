package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.DocumentoService;
import br.com.petrobras.sistam.common.business.OperacaoService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioSiscomex;
import br.com.petrobras.sistam.common.valueobjects.RelatorioSiscomexVO;
import br.com.petrobras.sistam.service.query.operacao.ConsultaDocumentacaoLongoCursoPorOperacao;
import br.com.petrobras.sistam.service.query.operacao.ConsultaDocumentacaoCabotagemPorAgenciamento;
import br.com.petrobras.sistam.service.query.operacao.ConsultaDocumentacaoCabotagemPorId;
import br.com.petrobras.sistam.service.query.operacao.ConsultaDocumentacaoOperacaoPorDocumentacao;
import br.com.petrobras.sistam.service.query.operacao.ConsultaDocumentacaoCabotagemPorFiltroSiscomex;
import br.com.petrobras.sistam.service.query.operacao.ConsultaDocumentacaoLongoCursoPorFiltroSiscomex;
import br.com.petrobras.sistam.service.query.operacao.ConsultaDocumentacaoOperacaoPorId;
import br.com.petrobras.sistam.service.query.operacao.ConsultaOperaoPorAgenciamento;
import br.com.petrobras.sistam.service.query.operacao.ConsultaOperacaoPorId;
import br.com.petrobras.sistam.service.query.operacao.ConsultaOperacaoSemDocumentacaoPorFiltroSiscomex;
import br.com.petrobras.sistam.service.query.operacao.ConsultaOperaoPorAgenciamentoETipo;
import br.com.petrobras.sistam.service.query.operacao.ConsultaSemOperacaoComercialPorFiltroSiscomex;
import br.com.petrobras.sistam.service.query.operacao.ConsultaSemOperacaoPorFiltroSiscomex;
import br.com.petrobras.sistam.service.validator.ValidadorOperacao;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OperacaoServiceImpl implements OperacaoService {

    private GenericDao dao;
    private PendenciaService pendenciaService;
    private DocumentoService documentoService;
    @Autowired
    private ValidadorOperacao validador;
    @Autowired
    private ValidadorPermissao validadorPermissao;

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }

    public void setPendenciaService(PendenciaService pendenciaService) {
        this.pendenciaService = pendenciaService;
    }

    public void setDocumentoService(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @Transactional(readOnly = false)
    @Override
    public Operacao salvarOperacao(Operacao operacao) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarOperacao(operacao.getAgenciamento().getAgencia()), ConstantesI18N.ORPERACAO_USUARIO_SEM_PERMISSAO);
        validador.validarSalvarOperacao(operacao);

        if (TipoContrato.TCP.equals(operacao.getAgenciamento().getTipoContrato())
                && operacao.getId() == null
                && TipoOperacao.CARGA_EXPORTACAO.equals(operacao.getTipoOperacao())) {

            pendenciaService.criarPendencia(operacao.getAgenciamento(), TipoPendencia.BL);
        }

        dao.saveOrUpdate(operacao);
        return operacao;
    }

    @Transactional(readOnly = false)
    @Override
    public Operacao excluirOperacao(Operacao operacao) {
        AssertUtils.assertExpression(validadorPermissao.podeExcluirOperacao(operacao.getAgenciamento().getAgencia()), ConstantesI18N.OPERACAO_USUARIO_SEM_PERMISSAO_EXCLUIR);

        if (TipoOperacao.CARGA_EXPORTACAO.equals(operacao.getTipoOperacao())) {
            Documento documento = documentoService.buscarDocumentoDaOperacao(operacao);
            AssertUtils.assertExpression(documento == null, ConstantesI18N.OPERACAO_NAO_PODE_SER_EXCLUIDA_EXISTE_DOCUMENTO);

            Pendencia pendencia = pendenciaService.buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(operacao.getAgenciamento(), TipoPendencia.BL);
            if (pendencia != null) {
                pendenciaService.excluirPendencia(pendencia);
            }
        }

        dao.remove(operacao);

        return operacao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Operacao> buscarOperacoesPorAgenciamento(Agenciamento agenciamento) {
        return dao.list(new ConsultaOperaoPorAgenciamento(agenciamento));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Operacao> buscarOperacoesPorAgenciamentoETipo(Agenciamento agenciamento, TipoOperacao tipo) {
        return dao.list(new ConsultaOperaoPorAgenciamentoETipo(agenciamento, tipo));
    }

    @Transactional(readOnly = true)
    @Override
    public Operacao buscarOperacaoPorId(Long id) {
        return dao.uniqueResult(new ConsultaOperacaoPorId(id));
    }

    @Transactional(readOnly = false)
    @Override
    public DocumentacaoCabotagem salvarDocumentacaoCabotagem(DocumentacaoCabotagem documentacaoCabotagem) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarDocumentacaoCabotagem(documentacaoCabotagem.getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTACAO_OPERACAO_USUARIO_SEM_PERMISSAO);
        validador.validarSalvarDocumentacaoOperacao(documentacaoCabotagem);

        for (DocumentacaoOperacao produto : documentacaoCabotagem.getDocumentacaoProdutos()) {
            if (StringUtils.isNotEmpty(documentacaoCabotagem.getConhecimentoEletronico())) {
                produto.setConhecimentoEletronico(documentacaoCabotagem.getConhecimentoEletronico());
            }
            if (StringUtils.isNotEmpty(documentacaoCabotagem.getNumeroCTAC())) {
                produto.setCtac(documentacaoCabotagem.getNumeroCTAC());
            }
            dao.saveOrUpdate(produto);
        }
        dao.saveOrUpdate(documentacaoCabotagem);
        return documentacaoCabotagem;
    }

    @Transactional(readOnly = false)
    @Override
    public DocumentacaoCabotagem excluirDocumentacaoCabotagem(DocumentacaoCabotagem documentacaoCabotagem) {
        AssertUtils.assertExpression(validadorPermissao.podeExcluirDocumentacaoCabotagem(documentacaoCabotagem.getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTACAO_OPERACAO_USUARIO_SEM_PERMISSAO_EXCLUIR);

        for (DocumentacaoOperacao documentacaoOperacao : documentacaoCabotagem.getDocumentacaoProdutos()) {
            dao.remove(documentacaoOperacao);
        }

        dao.remove(documentacaoCabotagem);
        return documentacaoCabotagem;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentacaoCabotagem> buscarDocumentacaoCabotagemPorAgenciamentoETipo(Agenciamento agenciamento, TipoOperacao tipo) {
        return dao.list(new ConsultaDocumentacaoCabotagemPorAgenciamento(agenciamento, tipo));
    }

    @Transactional(readOnly = true)
    @Override
    public RelatorioSiscomexVO buscarDocumentosParaRelatorioSiscomex(FiltroRelatorioSiscomex filtro) {
        List<DocumentacaoCabotagem> documentacoesCabotagem = new ArrayList<DocumentacaoCabotagem>();
        List<DocumentacaoLongoCurso> documentacoesLongoCurso = new ArrayList<DocumentacaoLongoCurso>();
        List<Operacao> semOperacoesComerciais = new ArrayList<Operacao>();
        List<Agenciamento> agenciamentosSemOperacao = new ArrayList<Agenciamento>();
        List<Operacao> operacoesSemDocumentacao = new ArrayList<Operacao>();
        
        if (filtro.getTiposOperacao().isEmpty() || filtro.isTipoOperacaoCabotagem()) {
            documentacoesCabotagem = dao.list(new ConsultaDocumentacaoCabotagemPorFiltroSiscomex(filtro));
        }
        if (filtro.getTiposOperacao().isEmpty() || filtro.isTipoOperacaoLongoCurso()) {
            documentacoesLongoCurso = dao.list(new ConsultaDocumentacaoLongoCursoPorFiltroSiscomex(filtro));
        }
        if (filtro.getTiposOperacao().isEmpty() || filtro.isTipoSemOperacaoComercial()) {
            semOperacoesComerciais = dao.list(new ConsultaSemOperacaoComercialPorFiltroSiscomex(filtro));
            agenciamentosSemOperacao = dao.list(new ConsultaSemOperacaoPorFiltroSiscomex(filtro));
        }
        operacoesSemDocumentacao = dao.list(new ConsultaOperacaoSemDocumentacaoPorFiltroSiscomex(filtro));
        
        if (documentacoesCabotagem.isEmpty() && documentacoesLongoCurso.isEmpty() && semOperacoesComerciais.isEmpty() && operacoesSemDocumentacao.isEmpty()) {
            return null;
        }

        RelatorioSiscomexVO relatorio = new RelatorioSiscomexVO();
        for (DocumentacaoCabotagem documentacao : documentacoesCabotagem) {
            if (documentacao.isTipoCargaCabotagem()) {
                relatorio.getCargasCabotagem().add(documentacao);
            } else if (documentacao.isTipoDescargaCabotagem()) {
                relatorio.getDescargasCabotagem().add(documentacao);
            }
        }

        for (DocumentacaoLongoCurso documentacao : documentacoesLongoCurso) {
            if (documentacao.getOperacao().isTipoCargaExportacao()) {
                relatorio.getCargasExportacao().add(documentacao);
            } else if (documentacao.getOperacao().isTipoDescargaImportacao()) {
                relatorio.getDescargasImportacao().add(documentacao);
            }
        }

        for (Operacao operacao : operacoesSemDocumentacao) {
            if (operacao.isTipoCargaCabotagem()) {
                relatorio.getOperacoesSemDocumentacaoCargaCabotagem().add(operacao);
            } else if (operacao.isTipoDescargaCabotagem()) {
                relatorio.getOperacoesSemDocumentacaoDescargaCabotagem().add(operacao);
            } else if (operacao.isTipoCargaExportacao()) {
                relatorio.getOperacoesSemDocumentacaoCargaExportacao().add(operacao);
            } else if (operacao.isTipoDescargaImportacao()) {
                relatorio.getOperacoesSemDocumentacaoDescargaImportacao().add(operacao);
            }
        }
        
        relatorio.getSemOperacoesComerciais().addAll(semOperacoesComerciais);
        
        relatorio.getSemOperacoes().addAll(agenciamentosSemOperacao);

        return relatorio;
    }

    @Transactional(readOnly = true)
    @Override
    public DocumentacaoCabotagem buscarDocumentacaoCabotagemPorId(Long id) {
        return dao.uniqueResult(new ConsultaDocumentacaoCabotagemPorId(id));
    }

    @Transactional(readOnly = false)
    @Override
    public DocumentacaoOperacao salvarDocumentacaoProduto(DocumentacaoOperacao documentacaoOperacao) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarDocumentacaoCabotagem(documentacaoOperacao.getDocumentacaoOperacao().getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTACAO_OPERACAO_USUARIO_SEM_PERMISSAO);
        validador.validarSalvarDocumentacaoProduto(documentacaoOperacao);
        dao.saveOrUpdate(documentacaoOperacao);
        return documentacaoOperacao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentacaoOperacao> buscarDocumentacaoOperacaoPorDocumentacao(DocumentacaoCabotagem documentacaoCabotagem) {
        return dao.list(new ConsultaDocumentacaoOperacaoPorDocumentacao(documentacaoCabotagem));
    }

    @Transactional(readOnly = true)
    @Override
    public DocumentacaoOperacao buscarDocumentacaoOperacaoPorId(Long id) {
        return dao.uniqueResult(new ConsultaDocumentacaoOperacaoPorId(id));
    }

    @Transactional(readOnly = false)
    @Override
    public void excluirDocumentacaoOperacao(DocumentacaoOperacao documentacaoOperacao) {
        AssertUtils.assertExpression(validadorPermissao.podeExcluirDocumentacaoCabotagem(documentacaoOperacao.getOperacao().getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTACAO_OPERACAO_USUARIO_SEM_PERMISSAO_EXCLUIR);
        dao.remove(documentacaoOperacao);
    }

    @Transactional(readOnly = false)
    @Override
    public DocumentacaoLongoCurso salvarDocumentacaoLongoCurso(DocumentacaoLongoCurso documentacaoLongoCurso) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarDocumentacaoCabotagem(documentacaoLongoCurso.getOperacao().getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_USUARIO_SEM_PERMISSAO);
        validador.validarSalvarDocumentacaoLongoCurso(documentacaoLongoCurso);
        dao.saveOrUpdate(documentacaoLongoCurso);
        return documentacaoLongoCurso;
    }

    @Transactional(readOnly = false)
    @Override
    public void excluirDocumentacaoLongoCurso(DocumentacaoLongoCurso documentacaoLongoCurso) {
        AssertUtils.assertExpression(validadorPermissao.podeExcluirDocumentacaoCabotagem(documentacaoLongoCurso.getOperacao().getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_USUARIO_SEM_PERMISSAO_EXCLUIR);
        dao.remove(documentacaoLongoCurso);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentacaoLongoCurso> buscarDocumentacaoLongoCursoPorOperacao(Operacao operacao) {
        return dao.list(new ConsultaDocumentacaoLongoCursoPorOperacao(operacao));
    }
}
