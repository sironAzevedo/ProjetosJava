package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.snarf.persistence.GenericDao;
import br.com.petrobras.sistam.common.business.AgenciaService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.query.ConsultaAgenciaPorId;
import br.com.petrobras.sistam.service.query.ConsultaAgenciaPorSigla;
import br.com.petrobras.sistam.service.query.ConsultaAgencias;
import br.com.petrobras.sistam.service.query.agencia.ConsultaAgenciaSigoPorId;
import br.com.petrobras.sistam.service.query.agencia.ConsultaAgenciaSigoPorNome;
import br.com.petrobras.sistam.service.query.agencia.ConsultaAgenciasPorPorto;
import br.com.petrobras.sistam.service.query.agencia.ConsultaContatosPorAgencia;
import br.com.petrobras.sistam.service.query.agencia.ConsultaDestinatariosDaAgencia;
import br.com.petrobras.sistam.service.query.agencia.ConsultaOrgaosDespachoPorAgencia;
import br.com.petrobras.sistam.service.validator.ValidadorAgencia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class AgenciaServiceImpl implements AgenciaService {
    
    private GenericDao dao;

    @Autowired
    private ValidadorPermissao validadorPermissao;
    
    @Autowired
    private ValidadorAgencia validadorAgencia;
    
    public void setDao(GenericDao dao) {
        this.dao = dao;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Agencia> buscarAgencias() {
        return dao.list(new ConsultaAgencias());
    }
    
    
    @Transactional(readOnly = true)
    @Override
    public Agencia buscarAgenciaPorId(Long id) {
        return dao.uniqueResult(new ConsultaAgenciaPorId(id));
    }

    @Transactional(readOnly = true)
    @Override
    public Agencia buscarAgenciaPorSigla(String sigla) {
        return dao.uniqueResult(new ConsultaAgenciaPorSigla(sigla));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgenciaSigo> buscarAgenciaSigoPorNome(String nome) {
        return dao.list(new ConsultaAgenciaSigoPorNome(nome));
    }

    @Transactional(readOnly = true)
    @Override
    public AgenciaSigo buscarAgenciaSigoPorId(Long id) {
        return dao.uniqueResult(new ConsultaAgenciaSigoPorId(id));
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<RepresentanteLegal> buscarContatosPorAgencia(Agencia agencia, Boolean ativo) {
        return dao.list(new ConsultaContatosPorAgencia(agencia, ativo));
    }

    @Transactional(readOnly = true)
    @Override
    public List<AgenciaOrgaoDespacho> buscarOrgaosDespachoPorAgencia(Agencia agencia) {
        return dao.list(new ConsultaOrgaosDespachoPorAgencia(agencia));
    }
    
    
    @Transactional(readOnly = true)
    @Override
    public List<Agencia> buscarAgenciasPorPorto(Porto porto){
        return dao.list(new ConsultaAgenciasPorPorto(porto));
    }

    @Transactional(readOnly = false)
    @Override
    public Agencia salvarAgencia(Agencia agencia) {
        
        if (agencia.getId() != null) {
            AssertUtils.assertExpression(validadorPermissao.podeSalvarAgencia(agencia), ConstantesI18N.AGENCIA_SEM_PERMISSAO_SALVAR);
        } else {
            AssertUtils.assertExpression(validadorPermissao.podeIncluirAgencia(), ConstantesI18N.AGENCIA_SEM_PERMISSAO_INCLUIR);
        }
            
        validadorAgencia.validarSalvarAgencia(agencia);
        dao.merge(agencia);
        return agencia;
    }

    @Transactional(readOnly = false)
    @Override
    public RepresentanteLegal salvarRepresentanteLegal(RepresentanteLegal agenciaContato) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgenciaContato(agenciaContato.getAgencia()), ConstantesI18N.AGENCIA_CONTATO_SEM_PERMISSAO_SALVAR);
        validadorAgencia.validarSalvarAgenciaContato(agenciaContato);
        dao.saveOrUpdate(agenciaContato);
        return agenciaContato;
    }

    @Transactional(readOnly = false)
    @Override
    public void excluirAgenciaContato(RepresentanteLegal agenciaContato) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgenciaContato(agenciaContato.getAgencia()), ConstantesI18N.AGENCIA_CONTATO_SEM_PERMISSAO_EXCLUIR);
        dao.remove(agenciaContato);
    }

    @Transactional(readOnly = false)
    @Override
    public AgenciaOrgaoDespacho salvarAgenciaOrgaoDespacho(AgenciaOrgaoDespacho agenciaOrgaoDespacho) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgenciaOrgaoDespacho(agenciaOrgaoDespacho.getAgencia()), ConstantesI18N.AGENCIA_ORGAO_DESPACHO_SEM_PERMISSAO_SALVAR);
        validadorAgencia.validarSalvarAgenciaOrgaoDespacho(agenciaOrgaoDespacho);
        dao.saveOrUpdate(agenciaOrgaoDespacho);
        return agenciaOrgaoDespacho;
    }

    @Transactional(readOnly = false)
    @Override
    public void excluirAgenciaOrgaoDespacho(AgenciaOrgaoDespacho agenciaOrgaoDespacho) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgenciaOrgaoDespacho(agenciaOrgaoDespacho.getAgencia()), ConstantesI18N.AGENCIA_ORGAO_DESPACHO_SEM_PERMISSAO_EXCLUIR);
        dao.remove(agenciaOrgaoDespacho);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Destinatários da Agência">
    @Transactional(readOnly = false)
    @Override
    public Destinatario salvarDestinatario(Destinatario destinatario){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgencia(destinatario.getAgencia()), ConstantesI18N.DESTINATARIO_SEM_PERMISSAO_SALVAR);
        validadorAgencia.validarSalvarDestinatario(destinatario);
        
        dao.saveOrUpdate(destinatario);
        return destinatario;
    }
    
    @Transactional(readOnly = false)
    @Override
    public Destinatario excluirDestinatario(Destinatario destinatario){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAgencia(destinatario.getAgencia()), ConstantesI18N.DESTINATARIO_SEM_PERMISSAO_SALVAR);
        
        dao.remove(destinatario);
        return destinatario;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Destinatario> buscarDestinatariosDaAgencia(Agencia agencia){
        return dao.list(new ConsultaDestinatariosDaAgencia(agencia));
    }
    //</editor-fold>

                            
}
