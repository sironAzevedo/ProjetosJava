package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.CollectionUtils;
import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.service.query.ConsultaEmpresaPorAgenciaTipoServico;
import br.com.petrobras.sistam.service.query.ConsultaPaises;
import br.com.petrobras.sistam.service.query.ConsultaProdutoPorId;
import br.com.petrobras.sistam.service.query.ConsultaProdutos;
import br.com.petrobras.sistam.service.query.common.ConsultaServicosPorEmpresaETipo;
import br.com.petrobras.sistam.service.query.common.ConsultaServicosPorTipo;
import br.com.petrobras.sistam.service.query.common.ConsultaResponsavelCustoApenasPetrobrasETranspetro;
import br.com.petrobras.sistam.service.query.common.ConsultaResponsavelCustoExcetoSemCusto;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl implements CommonService{
    private GenericDao dao;

    public void setDao(GenericDao dao) {
        this.dao = dao; 
    }
    
    @Transactional(readOnly=false)
    @Override
    public Object buscarEntidadePorId(Class clazz, Serializable id){
        return dao.get(clazz, id);
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<Pais> buscarPaises(String nome) {
        return dao.list(new ConsultaPaises(nome));            
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<ResponsavelCustoEntity> buscarTodosResponsavelCusto() { 
        List<ResponsavelCustoEntity> responsavelCusto = dao.findAll(ResponsavelCustoEntity.class); 
        CollectionUtils.sort(responsavelCusto, ResponsavelCustoEntity.PROP_DESCRICAO);
        return responsavelCusto; 
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<ResponsavelCustoEntity> buscarResponsavelCustoApenasPetrobrasETranspetro() {
        List<ResponsavelCustoEntity> responsavelCusto = dao.list(new ConsultaResponsavelCustoApenasPetrobrasETranspetro());
        CollectionUtils.sort(responsavelCusto, ResponsavelCustoEntity.PROP_DESCRICAO);
        return responsavelCusto;
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<ResponsavelCustoEntity> buscarResponsavelCustoExcetoSemCusto() {
        return dao.list(new ConsultaResponsavelCustoExcetoSemCusto());
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<Produto> buscarProdutos(String nome) {
        return dao.list(new ConsultaProdutos(nome));            
    }
    
    @Transactional(readOnly = true)
    @Override
    public Produto buscarProdutoPorId(String id) {
        return dao.uniqueResult(new ConsultaProdutoPorId(id));
    }                

    @Override
    @Transactional(readOnly = true)    
    public List<EmpresaMaritima> buscarEmpresasMaritimasPorAgenciaTipoServico(Agencia agencia, TipoServico tipoServico) {
        Query query = dao.createHibernateQuery(new ConsultaEmpresaPorAgenciaTipoServico(agencia, tipoServico));
        query.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
        return query.list();             
    }
    
     
    @Transactional(readOnly = true)    
    @Override
    public List<Servico> buscarServicosPorEmpresaETipo(EmpresaMaritima empresa, TipoServico tipo) {
         return dao.list(new ConsultaServicosPorEmpresaETipo(empresa, tipo));
    }
    
    @Transactional(readOnly = true)    
    @Override
    public List<Servico> buscarServicosPorTipo(TipoServico tipo) {
         return dao.list(new ConsultaServicosPorTipo(tipo));
    } 
    
}
