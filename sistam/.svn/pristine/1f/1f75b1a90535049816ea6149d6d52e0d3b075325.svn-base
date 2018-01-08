package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.VisitaService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Transporte;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioVisita;
import br.com.petrobras.sistam.common.valueobjects.RelatorioVisitaVO;
import br.com.petrobras.sistam.common.valueobjects.Usuario;
import br.com.petrobras.sistam.service.query.agenciamento.visita.ConsultaVisitasPorAgenciamento;
import br.com.petrobras.sistam.service.validator.ValidadorVisita;
import br.com.petrobras.snarf.persistence.GenericDao;
import br.com.petrobras.sistam.service.query.agenciamento.visita.ConsultaTransportesPorFiltroRelatorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class VisitaServiceImpl implements VisitaService {
    private GenericDao dao;
    private AcessoService acessoService;
     
    @Autowired
    private ValidadorPermissao validadorPermissao;
    
    @Autowired
    private ValidadorVisita validadorVisita;

    
    public VisitaServiceImpl(GenericDao dao) {
        this.dao = dao;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    @Transactional(readOnly = false)
    @Override
    public List<RelatorioVisitaVO> buscarTransportesUtilizadosNaVisita(FiltroRelatorioVisita filtro) {
        List<Transporte> transportes = buscarTransportesPorFiltroRelatorio(filtro);
        List<RelatorioVisitaVO> vos = new ArrayList<RelatorioVisitaVO>();

        for (Transporte transporte : transportes) {
            final Visita visita = transporte.getVisita();
            final Agenciamento agenciamento = visita.getAgenciamento();

            RelatorioVisitaVO vo = new RelatorioVisitaVO(agenciamento.getAgencia().obterTimezone());
            vo.setNavio(agenciamento.getEmbarcacao());
            vo.setNumeroViagem(agenciamento.getVgm());
            vo.setData(visita.getDataInicio());
            vo.setLancha(transporte.getDescricao());
            vo.setServico(transporte.getServico());
            if (transporte.getCondicaoNavio() != null) {
                vo.setCondicaoNavio(transporte.getCondicaoNavio().getPorExtenso());
            }
            vo.setDataRequisicao(visita.getDataProgramada());
            vo.setDataInicio(visita.getDataInicio());
            vo.setDataTermino(visita.getDataTermino());
            vo.setAgente(new Usuario(visita.getChaveAgente(), visita.getNomeAgente()));
            vo.setObservacao(visita.getObservacao());

            vos.add(vo);
        }

        return vos;
    }

    @Transactional(readOnly = false)
    protected List<Transporte> buscarTransportesPorFiltroRelatorio(FiltroRelatorioVisita filtro){
        return dao.list(new ConsultaTransportesPorFiltroRelatorio(filtro));
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Visita> buscarVisitasDoAgenciamento(Agenciamento agenciamento){
        return dao.list(new ConsultaVisitasPorAgenciamento(agenciamento));
    }
    
    @Override
    @Transactional(readOnly=false)
    public Visita salvarVisita(Visita visita) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarVisita(visita.getAgenciamento().getAgencia()), ConstantesI18N.VISITA_SEM_PERMISSAO_SALVAR);
        validadorVisita.validarSalvarVisita(visita);
        
        boolean novaVisita = visita.getId() ==  null ? true : false;
        
        //Atualiza com o usuário logado
        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        visita.setChaveUsuarioAlteracao(credentialsBean.getLogon());
        visita.setNomeUsuarioAlteracao(credentialsBean.getUsername());
        visita.setDataAlteracao(new Date());
        
        
        
        dao.saveOrUpdate(visita);
        
        if (novaVisita){
            for (Transporte transporte : visita.getTransportes()){
                salvarTransporte(transporte);
            }
        }
        
        return visita;
    }
    
    @Override
    @Transactional(readOnly=false)
    public void excluirVisita(Visita visita){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarVisita(visita.getAgenciamento().getAgencia()), ConstantesI18N.VISITA_SEM_PERMISSAO_EXCLUIR);
        
        //Remove todos os tranportes antes.
        for (Transporte transporte : visita.getTransportes()){
            dao.remove(transporte);
        }
        
        dao.remove(visita);
    }
    
    @Override
    @Transactional(readOnly=false)
    public Transporte salvarTransporte(Transporte transporte) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarTransporte(transporte.getVisita().getAgenciamento().getAgencia()), ConstantesI18N.TRANSPORTE_SEM_PERMISSAO_SALVAR);
        validadorVisita.validarSalvarTransporte(transporte);
        dao.saveOrUpdate(transporte);
        return transporte;
    }
    
    @Override
    @Transactional(readOnly=false)
    public void excluirTransporte(Transporte transporte){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarTransporte(transporte.getVisita().getAgenciamento().getAgencia()), ConstantesI18N.TRANSPORTE_SEM_PERMISSAO_EXCLUIR);
        dao.remove(transporte);
    }

    @Override
    @Transactional(readOnly=true)
    public void validarSalvarTransporte(Transporte transporte) {
        validadorVisita.validarSalvarTransporte(transporte);
    }
    
}
