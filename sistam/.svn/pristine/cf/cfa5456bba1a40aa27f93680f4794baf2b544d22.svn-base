package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.PessoaProtecaoService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroPessoaProtecao;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaPessoaProtecaoComCPFExistente;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaPessoaProtecaoPorCpf;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaPessoaProtecaoPorId;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaPessoasProtecaoAtivaPorEmpresaNome;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaPessoasProtecaoPorFiltro;
import br.com.petrobras.sistam.service.validator.ValidadorPessoaProtecao;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PessoaProtecaoServiceImpl implements PessoaProtecaoService {

    private GenericDao dao;
    @Autowired
    private ValidadorPessoaProtecao validador;

    public PessoaProtecaoServiceImpl(GenericDao dao) {
        this.dao = dao;
    }

    public void setValidador(ValidadorPessoaProtecao validador) {
        this.validador = validador;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pessoa> buscarPessoasProtecaoAtivaPorEmpresaNome(EmpresaProtecao empresa, String nome) {
        return dao.list(new ConsultaPessoasProtecaoAtivaPorEmpresaNome(empresa, nome));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pessoa> buscarPessoasProtecaoPorFiltro(FiltroPessoaProtecao filtro) {
        return dao.list(new ConsultaPessoasProtecaoPorFiltro(filtro));
    }

    @Transactional(readOnly = true)
    @Override
    public Pessoa buscarPessoaProtecaoPorId(long id) {
        return dao.uniqueResult(new ConsultaPessoaProtecaoPorId(id));
    }

    @Transactional(readOnly = true)
    @Override
    public Pessoa buscarPessoaProtecaoPorCpf(String cpf) {
        return dao.uniqueResult(new ConsultaPessoaProtecaoPorCpf(cpf));
    }

    @Transactional(readOnly = false)
    @Override
    public Pessoa salvarPessoaProtecao(Pessoa pessoa) {
        validador.validarSalvar(pessoa);
        AssertUtils.assertExpression(isNotExists(pessoa), ConstantesI18N.PESSOA_PROTECAO_CPF_EXISTENTE);
        dao.saveOrUpdate(pessoa);
        return pessoa;
    }

    @Transactional(readOnly = true)
    private boolean isNotExists(Pessoa pessoa) {
        if(StringUtils.isBlank(pessoa.getCpf()))
            return true;
        return dao.list(new ConsultaPessoaProtecaoComCPFExistente(pessoa)).isEmpty();
    }
}
