package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.EmpresaProtecaoService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.EmpresaProtecaoServico;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresaProtecao;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaEmpresaProtecaoComCnpjExistente;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaEmpresaProtecaoPorCnpj;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaEmpresaProtecaoPorId;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaEmpresasProtecao;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaEmpresasProtecaoPorFiltro;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaEmpresasProtecaoAtivaPorTipoNomeCnpj;
import br.com.petrobras.sistam.service.validator.ValidadorEmpresaProtecao;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class EmpresaProtecaoServiceImpl implements EmpresaProtecaoService {

    private GenericDao dao;
    @Autowired
    private ValidadorEmpresaProtecao validador;

    public EmpresaProtecaoServiceImpl(GenericDao dao) {
        this.dao = dao;
    }

    public void setValidador(ValidadorEmpresaProtecao validador) {
        this.validador = validador;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmpresaProtecao> buscarEmpresasProtecaoAtivaPorTipoNomeCnpj(TipoServicoProtecao tipo, String nome, String cnpj) {
        return dao.list(new ConsultaEmpresasProtecaoAtivaPorTipoNomeCnpj(tipo, nome, cnpj));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmpresaProtecao> buscarEmpresasProtecaoPorFiltro(FiltroEmpresaProtecao filtro) {
        return dao.list(new ConsultaEmpresasProtecaoPorFiltro(filtro));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmpresaProtecao> buscarEmpresasProtecao() {
        return dao.list(new ConsultaEmpresasProtecao());
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmpresaProtecao> buscarEmpresasProtecaoAtiva() {
        return dao.list(new ConsultaEmpresasProtecao(true));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmpresaProtecao> buscarEmpresasProtecaoPorTipoServico(TipoServicoProtecao tipoServicoProtecao) {
        return dao.list(new ConsultaEmpresasProtecao(tipoServicoProtecao));
    }

    @Transactional(readOnly = false)
    @Override
    public EmpresaProtecao buscarEmpresaProtecaoPorId(long id) {
        return dao.uniqueResult(new ConsultaEmpresaProtecaoPorId(id));
    }
    
    @Transactional(readOnly = false)
    @Override
    public EmpresaProtecao buscarEmpresaProtecaoPorCnpj(String cnpj) {
        return dao.uniqueResult(new ConsultaEmpresaProtecaoPorCnpj(cnpj));
    }

    @Transactional(readOnly = false)
    @Override
    public EmpresaProtecao salvarEmpresaProtecao(EmpresaProtecao empresa) {
        validador.validarSalvar(empresa);
        AssertUtils.assertExpression(isNotExists(empresa), ConstantesI18N.EMPRESA_PROTECAO_CNPJ_EXISTENTE);

        dao.saveOrUpdate(empresa);
        for (EmpresaProtecaoServico servico : empresa.getServicos()) {
            if (servico.getId() != null && servico.getId().longValue() != 0) {
                dao.remove(dao.get(EmpresaProtecaoServico.class, servico.getId()));
            } else {
                servico.setEmpresa(empresa);
                dao.saveOrUpdate(servico);
            }
        }
        return empresa;
    }

    @Transactional(readOnly = true)
    private boolean isNotExists(EmpresaProtecao empresa) {
        return dao.list(new ConsultaEmpresaProtecaoComCnpjExistente(empresa)).isEmpty();
    }
}