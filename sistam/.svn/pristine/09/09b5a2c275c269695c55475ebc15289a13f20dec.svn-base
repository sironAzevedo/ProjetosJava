package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaPendenciasDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaPendenciasDoAgenciamentoPorTipo;
import br.com.petrobras.sistam.service.query.agenciamento.ConsultaPendenciasNaoResolvidasDoAgenciamentoPorTipo;
import br.com.petrobras.sistam.service.query.agenciamento.pendencia.ConsultaPendenciasDaTaxa;
import br.com.petrobras.sistam.service.validator.ValidadorPendencia;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



public class PendenciaServiceImpl implements PendenciaService { 
    private GenericDao dao;
    
    @Autowired
    private ValidadorPendencia validador;
     
    @Autowired
    private ValidadorPermissao validadorPermissao;
    
    private AcessoService acessoService;
    
    public PendenciaServiceImpl(GenericDao dao) {
        this.dao = dao;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }
    

    @Transactional(readOnly=true)
    @Override
    public List<Pendencia> buscarPendenciasDoAgenciamento(Agenciamento agenciamento, Boolean resolvida){
        return dao.list(new ConsultaPendenciasDoAgenciamento(agenciamento, resolvida));
    }
    
    @Transactional(readOnly=true)
    @Override
    public Pendencia buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(Agenciamento agenciamento, TipoPendencia tipo){
        List<Pendencia> pendencias = dao.list(new ConsultaPendenciasNaoResolvidasDoAgenciamentoPorTipo(agenciamento, tipo));
        return pendencias != null && !pendencias.isEmpty() ? pendencias.get(0) : null;
    }
    
    @Transactional(readOnly=true)
    @Override
    public List<Pendencia> buscarPendenciasDoAgenciamentoPorTipo(Agenciamento agenciamento, TipoPendencia tipo) {
        return dao.list(new ConsultaPendenciasDoAgenciamentoPorTipo(agenciamento, tipo));
    }
    
    
    @Override
    @Transactional(readOnly=false)
    public Pendencia criarPendencia(Agenciamento agenciamento, TipoPendencia tipo){
        
         validador.validarCriarPendencia(agenciamento, tipo);
        
        Pendencia pendencia = new Pendencia();
        pendencia.setAgenciamento(agenciamento);
        pendencia.setTipoPendencia(tipo);
        pendencia.setResolvida(false);
        
        dao.saveOrUpdate(pendencia);
        return pendencia;
    }
    
    @Transactional(readOnly=false)
    @Override
    public Pendencia resolverPendenciaManual(Pendencia pendencia){
        validador.validarResolucaoDaPendenciaManual(pendencia);
        
        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        
        pendencia.setNomeResposavel(credentialsBean.getUsername());
        pendencia.setChaveResponsavel(credentialsBean.getLogon());
        pendencia.setDataSolucao(new Date());
        pendencia.setResolvida(Boolean.TRUE);
        
        
        dao.saveOrUpdate(pendencia);
        return pendencia;
    }
    
    @Transactional(readOnly=false)
    @Override
    public Pendencia resolverPendenciaDoDocumento(Documento documento){
        validador.validarResolucaoDaPendenciaDoDocumento(documento);
        
        Pendencia pendencia = null;
        TipoPendencia tipoPendencia = TipoPendencia.obterPorTipoDocumento(documento.getTipoDocumento());
        
        if (tipoPendencia != null){
            List<Pendencia> listaPendencias = dao.list(new ConsultaPendenciasNaoResolvidasDoAgenciamentoPorTipo(documento.getAgenciamento(), tipoPendencia));
            
            if (!listaPendencias.isEmpty()){
                CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();

                pendencia = listaPendencias.get(0);
                pendencia.setNomeResposavel(credentialsBean.getUsername());
                pendencia.setChaveResponsavel(credentialsBean.getLogon());
                pendencia.setDataSolucao(new Date());
                pendencia.setResolvida(true);
                pendencia.setDocumento(documento);

                dao.saveOrUpdate(pendencia);
            }
        }
        return pendencia;
    }
    
    @Transactional(readOnly=false)
    @Override
    public Pendencia resolverPendenciaDaTaxa(Taxa taxa){
        validador.validarResolucaoDaPendenciaDaTaxa(taxa);
        
        Pendencia pendencia = null;
        TipoPendencia tipoPendencia = TipoPendencia.obterPorTipoTaxa(taxa.getTipoTaxa());
        
        if (tipoPendencia != null){
            List<Pendencia> listaPendencias = dao.list(new ConsultaPendenciasNaoResolvidasDoAgenciamentoPorTipo(taxa.getAgenciamento(), tipoPendencia));

            if (!listaPendencias.isEmpty()){
                CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();

                pendencia = listaPendencias.get(0);
                pendencia.setNomeResposavel(credentialsBean.getUsername());
                pendencia.setChaveResponsavel(credentialsBean.getLogon());
                pendencia.setDataSolucao(new Date());
                pendencia.setResolvida(true);
                pendencia.setTaxa(taxa);

                dao.saveOrUpdate(pendencia);
            }
        }
        return pendencia;
    }
    
    @Transactional(readOnly=false)
    @Override
    public Pendencia removerResolucaoDaPendenciaDaTaxa(Taxa taxa){
        validador.validarRemocaoDaResolucaoDaPendenciaDaTaxa(taxa);
        
        Pendencia pendencia = dao.uniqueResult(new ConsultaPendenciasDaTaxa(taxa));
        
        if (pendencia != null){
            pendencia.setTaxa(null);
            pendencia.setChaveResponsavel(null);
            pendencia.setNomeResposavel(null);
            pendencia.setDataSolucao(null);
            pendencia.setResolvida(false);
            
            dao.saveOrUpdate(pendencia);
        }
        
        return pendencia;
    }
    
    @Transactional(readOnly=false)
    @Override
    public Pendencia removerResolucaoDaPendencia(Pendencia pendencia){
        validador.validarRemocaoDaResolucaoDaPendencia(pendencia);
        
        if (pendencia != null){
            pendencia.setTaxa(null);
            pendencia.setChaveResponsavel(null);
            pendencia.setNomeResposavel(null);
            pendencia.setDataSolucao(null);
            pendencia.setResolvida(false);
            
            dao.saveOrUpdate(pendencia);
        }
        
        return pendencia;
    }
    
    @Transactional(readOnly=false)
    @Override
    public Pendencia excluirPendencia(Pendencia pendencia){
       AssertUtils.assertExpression(validadorPermissao.podeExcluirPendencia(pendencia.getAgenciamento().getAgencia()), ConstantesI18N.PENDENCIA_USUARIO_SEM_PERMISSAO_EXCLUIR);
        validador.validarExclusaoDaPendencia(pendencia);
        dao.remove(pendencia);
        
        return pendencia;
    }

    
    
}
