package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.business.PortoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.query.ConsultaPaisPorId;
import br.com.petrobras.sistam.service.query.ConsultaPontoAtracacaoPorId;
import br.com.petrobras.sistam.service.query.ConsultaPontoAtracacaolPorAgencia;
import br.com.petrobras.sistam.service.query.ConsultaPontoAtracacaolPorPontoOperacional;
import br.com.petrobras.sistam.service.query.ConsultaPontoAtracacaolPorPorto;
import br.com.petrobras.sistam.service.query.ConsultaPontoOperacionalPorId;
import br.com.petrobras.sistam.service.query.ConsultaPontoOperacionalPorPorto;
import br.com.petrobras.sistam.service.query.ConsultaPorto;
import br.com.petrobras.sistam.service.query.ConsultaPortoPorId;
import br.com.petrobras.sistam.service.query.ConsultaPortos;
import br.com.petrobras.sistam.service.query.porto.ConsultaAgenciasPortosPorAgencia;
import br.com.petrobras.sistam.service.query.porto.ConsultaPortoComplementoPorId;
import br.com.petrobras.sistam.service.query.porto.ConsultaPortosComplementosPorPorto;
import br.com.petrobras.sistam.service.query.porto.ConsultaPortosComplementos;
import br.com.petrobras.sistam.service.query.porto.ConsultaPortosNacionaisComComplementos;
import br.com.petrobras.sistam.service.query.porto.ConsultaPortosPorAgencia;
import br.com.petrobras.sistam.service.validator.ValidadorPorto;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class PortoServiceImpl implements PortoService {
    private GenericDao dao;
    private AgenciaService agenciaService;
    
    @Autowired
    private ValidadorPermissao validadorPermissao;
    
    @Autowired
    private ValidadorPorto validadorPorto;

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }

    public void setAgenciaService(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }
    
    @Transactional(readOnly = true)
    @Override
    public Porto buscarPortosPorId(String id) {
        return dao.uniqueResult(new ConsultaPortoPorId(id));
    }
    
    @Override
    @Transactional(readOnly = false)
    public PortoComplemento buscarPortoComplemento(Long id) {
        return dao.uniqueResult(new ConsultaPortoComplementoPorId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PortoComplemento> buscarPortosComplementosPorPorto(Porto porto){
        return dao.list(new ConsultaPortosComplementosPorPorto(porto));
    }
    
    @Transactional(readOnly = true)
    @Override
    public Porto buscarPorto(Porto porto) {
        return dao.uniqueResult(new ConsultaPorto(porto));
    }
    
    @Override
    @Transactional(readOnly = false)
    public PortoComplemento salvarPortoComplemento(PortoComplemento complemento){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarPortoComplemento(), ConstantesI18N.PORTO_COMPLEMENTO_SEM_PERMISSAO_SALVAR);
        validadorPorto.validarSalvarPortoComplemento(complemento);
        dao.saveOrUpdate(complemento);
        return complemento;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void excluirPortoComplemento(PortoComplemento complemento){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarPortoComplemento(), ConstantesI18N.PORTO_COMPLEMENTO_SEM_PERMISSAO_SALVAR);
        dao.remove(complemento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Porto> buscarPortos(String nome, Pais pais) {
        return dao.list(new ConsultaPortos(nome, pais));
    }
 
    @Override
    @Transactional(readOnly = true)
    public PontoOperacional buscarPontoOperacionalPorId(String id) {
        return dao.uniqueResult(new ConsultaPontoOperacionalPorId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PontoOperacional> buscarPontoOperacionalPorPorto(Porto porto) {
        return dao.list(new ConsultaPontoOperacionalPorPorto(porto));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PontoAtracacao> buscarPontoAtracacaoPorPontoOperacional(PontoOperacional pontoOperacional) {
        return dao.list(new ConsultaPontoAtracacaolPorPontoOperacional(pontoOperacional));
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<PontoAtracacao> buscarPontosAtracacaolPorPorto(Porto porto) {
        AssertUtils.assertNotNull(porto, ConstantesI18N.PONTO_ATRACACAO_PORTO_OBRIGATORIO);
        return dao.list(new ConsultaPontoAtracacaolPorPorto(porto));            
    }
    
   @Override
   @Transactional(readOnly = true)
   public Pais buscarPaisPorId(String id) {
       return dao.uniqueResult(new ConsultaPaisPorId(id));
   }                           

   @Override
   @Transactional(readOnly = true)
   public List<PontoAtracacao> buscarPontoAtracacaoPorAgencia(Agencia agencia) {
       List<Porto> portosDaAgencia = buscarPortosPorAgencia(agencia);
       List<PontoAtracacao> pontos = dao.list(new ConsultaPontoAtracacaolPorAgencia(agencia, portosDaAgencia));
       Collections.sort(pontos);
       return pontos;
   }

   @Transactional(readOnly = true)
    @Override
    public PontoAtracacao buscarPontoAtracacaoPorId(Long id) {
        return dao.uniqueResult(new ConsultaPontoAtracacaoPorId(id));
    }
   
   @Transactional(readOnly = true)
   @Override
   public List<Porto> buscarPortosPorAgencia(Agencia... agencias) {
        return  dao.list(new ConsultaPortosPorAgencia(agencias));
   }
   
   @Transactional(readOnly = true)
   @Override
   public List<AgenciaPorto> buscarAgenciasPortosPorAgencia(Agencia agencia) {
        return  dao.list(new ConsultaAgenciasPortosPorAgencia(agencia));
   }
   
   @Transactional(readOnly = false)
   @Override
   public PontoAtracacao salvarPontoAtracacao(PontoAtracacao pontoAtracacao){
       validadorPorto.validarSalvarPontoAtracacao(pontoAtracacao);
       
       List<Agencia> listaAgencia = agenciaService.buscarAgenciasPorPorto(pontoAtracacao.getPontoOperacional().getPorto());
       AssertUtils.assertExpression(validadorPermissao.podeSalvarPorto(listaAgencia), ConstantesI18N.PONTO_ATRACACAO_SEM_PERMISSAO_SALVAR);
       dao.saveOrUpdate(pontoAtracacao);
       return pontoAtracacao;
   }
   
   @Transactional(readOnly = false)
   @Override
   public AgenciaPorto salvarAgenciaPorto(AgenciaPorto agenciaPorto){
       validadorPorto.validarSalvarAgenciaPorto(agenciaPorto);
       Agencia agencia = agenciaPorto.getAgencia();
       AssertUtils.assertExpression(validadorPermissao.podeSalvarAgenciaPorto(agencia), ConstantesI18N.AGENCIA_PORTO_SEM_PERMISSAO_PARA_SALVAR);
       
       dao.merge(agenciaPorto);
       
       return agenciaPorto;
   }

   @Transactional(readOnly = true)
   @Override
   public List<PortoComplemento> buscarPortosComplemento(){
       return dao.list(new ConsultaPortosComplementos());
   }
   
   @Override
   @Transactional(readOnly = true)
    public List<Porto> buscarPortosNacionaisComComplementos(){
       return dao.list(new ConsultaPortosNacionaisComComplementos());
   }
   
}
