package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.Operacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author adzk
 */
public class RelatorioSiscomexVO implements Serializable {

    private List<DocumentacaoCabotagem> cargasCabotagem = new ArrayList<DocumentacaoCabotagem>();
    private List<DocumentacaoCabotagem> descargasCabotagem = new ArrayList<DocumentacaoCabotagem>();
    private List<DocumentacaoLongoCurso> cargasExportacao = new ArrayList<DocumentacaoLongoCurso>();
    private List<DocumentacaoLongoCurso> descargasImportacao = new ArrayList<DocumentacaoLongoCurso>();
    private List<Operacao> semOperacoesComerciais = new ArrayList<Operacao>();
    private List<Agenciamento> semOperacoes = new ArrayList<Agenciamento>();
    private List<Operacao> operacoesSemDocumentacaoCargaCabotagem = new ArrayList<Operacao>();
    private List<Operacao> operacoesSemDocumentacaoDescargaCabotagem = new ArrayList<Operacao>();
    private List<Operacao> operacoesSemDocumentacaoCargaExportacao = new ArrayList<Operacao>();
    private List<Operacao> operacoesSemDocumentacaoDescargaImportacao = new ArrayList<Operacao>();

    public List<Agenciamento> getSemOperacoes() {
        return semOperacoes;
    }

    public List<DocumentacaoCabotagem> getCargasCabotagem() {
        return cargasCabotagem;
    }

    public List<DocumentacaoCabotagem> getDescargasCabotagem() {
        return descargasCabotagem;
    }

    public List<DocumentacaoLongoCurso> getCargasExportacao() {
        return cargasExportacao;
    }

    public List<DocumentacaoLongoCurso> getDescargasImportacao() {
        return descargasImportacao;
    }

    public List<Operacao> getSemOperacoesComerciais() {
        return semOperacoesComerciais;
    }

    public List<Operacao> getOperacoesSemDocumentacaoCargaCabotagem() {
        return operacoesSemDocumentacaoCargaCabotagem;
    }

    public List<Operacao> getOperacoesSemDocumentacaoDescargaCabotagem() {
        return operacoesSemDocumentacaoDescargaCabotagem;
    }

    public List<Operacao> getOperacoesSemDocumentacaoCargaExportacao() {
        return operacoesSemDocumentacaoCargaExportacao;
    }

    public List<Operacao> getOperacoesSemDocumentacaoDescargaImportacao() {
        return operacoesSemDocumentacaoDescargaImportacao;
    }
}
