package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.EmpresaService;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresa;
import br.com.petrobras.sistam.service.query.empresa.ConsultaEmpresasPorFiltro;
import br.com.petrobras.sistam.service.validator.ValidadorEmpresa;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class EmpresaServiceImpl implements EmpresaService {
    private GenericDao dao;
    
    @Autowired
    private ValidadorPermissao validadorPermissao;
    
    @Autowired
    private ValidadorEmpresa validadorEmpresa;

    
    public EmpresaServiceImpl(GenericDao dao) {
        this.dao = dao;
    }
    
    @Transactional(readOnly=true)
    @Override
    public List<EmpresaMaritima> buscarEmpresasPorFiltro(FiltroEmpresa filtro){
        validadorEmpresa.validarBuscarEmpresasPorFiltro(filtro);
        return dao.list(new ConsultaEmpresasPorFiltro(filtro));
    }
    
    @Transactional(readOnly=false)
    @Override
    public EmpresaMaritima salvarEmpresa(EmpresaMaritima empresa){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarEmpresa(empresa.getAgencia()), ConstantesI18N.EMPRESA_MARITIMA_SEM_PERMISSAO_SALVAR);
        validadorEmpresa.validarSalvarEmpresa(empresa);

        //Salva a empresa.
        boolean novaEmpresa = empresa.getId() == null ? true : false;
        dao.saveOrUpdate(empresa);
        
        //Se for uma nova empresa, salva os serviços adicionados.
        if (novaEmpresa){
            for (Servico servico : empresa.getServicos()){
                salvarServicoDaEmpresa(servico);
            }
        }
        return empresa;
    }
    
    @Transactional(readOnly=false)
    @Override
    public void excluirEmpresa(EmpresaMaritima empresa) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarEmpresa(empresa.getAgencia()), ConstantesI18N.EMPRESA_MARITIMA_SEM_PERMISSAO_EXCLUIR);
        dao.remove(empresa);
    }
    
    @Transactional(readOnly=false)
    @Override
    public Servico salvarServicoDaEmpresa(Servico servico){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarEmpresa(servico.getEmpresa().getAgencia()), ConstantesI18N.EMPRESA_MARITIMA_SEM_PERMISSAO_SALVAR);
        validarSalvarServicoDaEmpresa(servico);
        
        dao.saveOrUpdate(servico);
        return servico;
    }
    
    @Transactional(readOnly=false)
    @Override
    public Servico excluirServicoDaEmpresa(Servico servico){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarEmpresa(servico.getEmpresa().getAgencia()), ConstantesI18N.EMPRESA_MARITIMA_SEM_PERMISSAO_SALVAR);
        
        dao.remove(servico);
        return servico;
    }
    
    @Override
    public void validarSalvarServicoDaEmpresa(Servico servico){
        validadorEmpresa.validarSalvarServico(servico);
    }

    

    
}
