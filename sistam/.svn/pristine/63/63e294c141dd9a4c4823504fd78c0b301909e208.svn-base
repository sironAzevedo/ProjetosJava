package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.business.TaxaService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.TaxaAcumuladoVO;
import br.com.petrobras.sistam.common.valueobjects.TaxaMensalVO;
import br.com.petrobras.sistam.service.query.agenciamento.taxa.ConsultaTaxaMensalPorFiltro;
import br.com.petrobras.sistam.service.query.agenciamento.taxa.ConsultaTaxaPorId;
import br.com.petrobras.sistam.service.query.agenciamento.taxa.ConsultaTaxasDaAgenciaEMesAnoOrdenadasPorTipo;
import br.com.petrobras.sistam.service.query.agenciamento.taxa.ConsultaTaxasOrdenadasPorTipo;
import br.com.petrobras.sistam.service.query.agenciamento.taxa.ConsultaTaxasPorAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.taxa.ConsultaTaxasPorAgenciamentoETipo;
import br.com.petrobras.sistam.service.query.agenciamento.taxa.ConsultaTaxasValorAcumulado;
import br.com.petrobras.sistam.service.validator.ValidadorTaxa;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class TaxaServiceImpl implements TaxaService {
    
    private GenericDao dao;
    private PendenciaService pendenciaServie;
    private AcessoService acessoService;
    
    @Autowired
    private ValidadorPermissao validadorPermissao;
    
    @Autowired
    private ValidadorTaxa validadorTaxa;
    
    public GenericDao getDao() {
        return dao;
    }

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }
    
    public void setPendenciaServie(PendenciaService pendenciaServie) {
        this.pendenciaServie = pendenciaServie;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }
    
    
    @Override
    @Transactional(readOnly = false)    
    public Taxa salvarTaxa(Taxa taxa){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarTaxa(taxa.getAgenciamento().getAgencia()), ConstantesI18N.TAXA_SEM_PERMISSAO_SALVAR);
        validadorTaxa.validarSalvarTaxa(taxa);
        taxa.setDataPagamento(SistamDateUtils.alterarParaMeioDia(taxa.getDataPagamento(), TimeZone.getTimeZone(taxa.getAgenciamento().getAgencia().getTimezone())));
        
         //Atualiza com o usuário logado
        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        taxa.setChaveUsuarioAlteracao(credentialsBean.getLogon());
        taxa.setNomeUsuarioAlteracao(credentialsBean.getUsername());
        taxa.setDataAlteracao(new Date());
        
        if (taxa.getId() == null && TipoContrato.TCP.equals(taxa.getAgenciamento().getTipoContrato())){
            pendenciaServie.resolverPendenciaDaTaxa(taxa);
        }
        
        dao.saveOrUpdate(taxa);
        return taxa;
    }

    @Override
    @Transactional(readOnly = false)    
    public void excluirTaxa(Taxa taxa) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarTaxa(taxa.getAgenciamento().getAgencia()), ConstantesI18N.EMBARCACAO_SEM_PERMISSAO_SALVAR);
        pendenciaServie.removerResolucaoDaPendenciaDaTaxa(taxa);
        dao.remove(taxa);
    }
    
    @Override
    @Transactional(readOnly = true)    
    public List<Taxa> buscarTaxasPorAgenciamento(Agenciamento agenciamento) {
        return dao.list(new ConsultaTaxasPorAgenciamento(agenciamento));
    }

    @Override
    @Transactional(readOnly = true) 
    public Taxa buscarTaxaPorId(Long id) {
        return dao.uniqueResult(new ConsultaTaxaPorId(id));
    }

    @Override
    @Transactional(readOnly = true) 
    public List<Taxa> buscarTaxaPorAgenciamentoETipo(Agenciamento agenciamento, TipoTaxa tipo) {
        return dao.list(new ConsultaTaxasPorAgenciamentoETipo(agenciamento, tipo));
    }   

    @Override
    @Transactional(readOnly = true) 
    public List<TaxaMensalVO> buscarTaxasPorFiltro(FiltroTaxa filtro) {
        return dao.list(new ConsultaTaxaMensalPorFiltro(filtro));
    }
    
    @Override
    @Transactional(readOnly = true) 
    public Map<TipoTaxa, Double> buscarTaxasValorAcumulado(FiltroTaxa filtro){
        validadorTaxa.validarBuscarTaxasValorAcumulado(filtro);
        
        Map<TipoTaxa, Double> mapTaxaAcumulado = new HashMap<TipoTaxa, Double>();
        
        List<TaxaAcumuladoVO> listaTaxaAcumulado  = dao.list(new ConsultaTaxasValorAcumulado(filtro));
        for (TaxaAcumuladoVO vo : listaTaxaAcumulado){
            mapTaxaAcumulado.put(vo.getTipoTaxa(), vo.getValorTotal());
        }
        
        return mapTaxaAcumulado;
    }
    
    @Override
    @Transactional(readOnly = true) 
    public List<Taxa> buscarTaxasOrdenadasPorTipo(FiltroTaxa filtro) {
        return dao.list(new ConsultaTaxasOrdenadasPorTipo(filtro));
    }

    @Override
    @Transactional(readOnly = true) 
    public List<Taxa> buscarTaxasDaAgenciaEMesAnoOrdenadasPorTipo(Agencia agencia, Porto porto, List<TipoTaxa> tiposTaxa, Integer mes, Integer ano) {
        return dao.list(new ConsultaTaxasDaAgenciaEMesAnoOrdenadasPorTipo(agencia, porto, tiposTaxa, mes, ano));
    }
    
}
