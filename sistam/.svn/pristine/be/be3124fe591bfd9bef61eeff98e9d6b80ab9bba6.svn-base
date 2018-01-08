package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.AgenciamentoService;
import br.com.petrobras.sistam.common.business.EmbarcacaoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmbarcacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import br.com.petrobras.sistam.service.query.ConsultaEmbarcacaoPorId;
import br.com.petrobras.sistam.service.query.ConsultaEmbarcacoes;
import br.com.petrobras.sistam.service.query.ConsultaEmbarcacoesPorFiltro;
import br.com.petrobras.sistam.service.query.ConsultaAgenciamentosPorFiltroRelatorioTimesheet;
import br.com.petrobras.sistam.service.query.ConsultaEscalasDosUltimos30DiasPorEmbarcacao;
import br.com.petrobras.sistam.service.query.ConsultaEscalasPorEmbarcacao;
import br.com.petrobras.sistam.service.validator.EmbarcacaoValidador;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class EmbarcacaoServiceImpl implements EmbarcacaoService {
    
    private GenericDao dao;
    private AgenciamentoService agenciamentoService;
    
    @Autowired
    private ValidadorPermissao validadorPermissao;
    
    @Autowired
    private EmbarcacaoValidador embarcacaoValidador;
    
    public GenericDao getDao() {
        return dao;
    }

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }

    public void setAgenciamentoService(AgenciamentoService agenciamentoService) {
        this.agenciamentoService = agenciamentoService;
    }
    
    public EmbarcacaoValidador getEmbarcacaoValidador() {
        return embarcacaoValidador;
    }

    public void setEmbarcacaoValidador(EmbarcacaoValidador embarcacaoValidador) {
        this.embarcacaoValidador = embarcacaoValidador;
    }

    public void setValidadorPermissao(ValidadorPermissao validadorPermissao) {
        this.validadorPermissao = validadorPermissao;
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<Escala> buscarEscalaPorEmbarcacao(Embarcacao embarcacao, Date eta) {
        return dao.list(new ConsultaEscalasDosUltimos30DiasPorEmbarcacao(embarcacao, eta));
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<Escala> buscarUltimasEscalasDaEmbarcacao(Embarcacao embarcacao, int quantidadeDeEscalas) {
        Query query = dao.createHibernateQuery(new ConsultaEscalasPorEmbarcacao(embarcacao));
        query.setMaxResults(quantidadeDeEscalas);
        return (List<Escala>) query.list();
    }

    
    @Transactional(readOnly = true)
    @Override
    public Embarcacao buscarEmbarcacaoPorId(String id) {
        return dao.uniqueResult(new ConsultaEmbarcacaoPorId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Embarcacao> buscarEmbarcacoes(String nome) {
        return dao.list(new ConsultaEmbarcacoes(nome));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Embarcacao> buscarEmbarcacoesPorFiltro(FiltroEmbarcacao filtro) {
        return dao.list(new ConsultaEmbarcacoesPorFiltro(filtro));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Embarcacao> buscarEmbarcacoesPorFiltro(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas) {
        List<Agenciamento> agenciamentos = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, agenciasAutorizadas);
        Set<Embarcacao> embarcacoes = new HashSet<Embarcacao>();
        for (Agenciamento agenciamento : agenciamentos) {
            embarcacoes.add(agenciamento.getEmbarcacao());
        }
        return ordenarPeloNomeListaEmbarcacoes(new ArrayList<Embarcacao>(embarcacoes));
    }
    

    @Override
    @Transactional(readOnly = false)    
    public Embarcacao salvarEmbarcacao(Embarcacao embarcacao){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarEmbarcacao(), ConstantesI18N.EMBARCACAO_SEM_PERMISSAO_SALVAR);
        embarcacaoValidador.validarSalvarEmbarcacao(embarcacao);
        dao.saveOrUpdate(embarcacao);
        return embarcacao;
    }

    private List<Embarcacao> ordenarPeloNomeListaEmbarcacoes(List<Embarcacao> lista) {
        Collections.sort(lista, new Comparator<Embarcacao>() {
            @Override
            public int compare(Embarcacao e1, Embarcacao e2) {
                return e1.getNomeCompleto().compareToIgnoreCase(e2.getNomeCompleto());
            }
        });
        return lista;
    }
                            
}
