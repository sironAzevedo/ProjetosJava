/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.service.impl;

import br.com.spassu.nihil.common.entity.Monitor;
import br.com.spassu.nihil.common.entity.Target;
import br.com.spassu.nihil.common.enums.StatusTarget;
import br.com.spassu.nihil.common.filtro.MonitorFiltro;
import br.com.spassu.nihil.common.filtro.TargetFiltro;
import br.com.spassu.nihil.service.persistence.GenericDao;
import br.com.spassu.nihil.service.query.BuscarMonitor;
import br.com.spassu.nihil.service.query.BuscarTarget;
import br.com.spassu.nihil.service.tester.TargetTester;
import br.com.spassu.nihil.service.tester.TargetTesterFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MonitorServiceImpl {
    
    public static final int BUSCAR_MONITORES_MAX_PAGE_SIZE = 10;
    public static final int BUSCAR_TARGETS_MAX_PAGE_SIZE = 10;
    
    
    @Autowired
    private GenericDao dao;

    @Autowired
    private TargetTesterFactory targetTesterFactory;
    
    @Autowired
    private EmailServiceImpl emailService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());    
    
    @Transactional(readOnly = false)
    public void reachableVerify() {

        List<Monitor> mudados = new ArrayList<Monitor>();
        
        try {
            
            List<Target> targets = dao.findAll(Target.class);

            for (Target target : targets) {

                TargetTester tester = targetTesterFactory.createTester(target);
                StatusTarget status = tester.executeTest(target);
                
                MonitorFiltro filtro = new MonitorFiltro();
                filtro.setIdTarget(target.getId());
                filtro.setOrderByDesc(true);

                List<Monitor> monitores = buscarMonitores(filtro, 0, 0);
                Monitor ultimo = monitores != null && !monitores.isEmpty() ? monitores.get(0) : null;

                if (ultimo == null || !ultimo.getStatus().equals(status)) {
                    Monitor monitor = new Monitor();
                    monitor.setData(new Date());
                    monitor.setTarget(target);
                    monitor.setStatus(status);
                    dao.saveOrUpdate(monitor);
                    
                    if (ultimo != null) {
                        mudados.add(monitor);
                    }    
                    
                }

            }
            
            if (!mudados.isEmpty()) {
                //emailService.notificarMudancaStatusTargets(mudados);
            }
            
        } catch (Exception e) {
            logger.error("Erro na rotina de monitoramento.", e);
            throw new RuntimeException(e);
        }

    }
    
    @Transactional(readOnly = true)
    public Integer buscarMonitoresCount(MonitorFiltro filtro) {
        Long count = (Long) dao.uniqueResult(new BuscarMonitor.Count().joinTarget()
                .whereId(filtro.getId())
                .whereIdTarget(filtro.getIdTarget())
                .whereIdTipoTarget(filtro.getIdTipoTarget()));
        return count.intValue();
    }
    
    @Transactional(readOnly = true)
    public List<Monitor> buscarMonitores(MonitorFiltro filtro, int pagina, Integer maxPageSize) {
        Integer count = buscarMonitoresCount(filtro);
        
        if (maxPageSize ==  null) {
           maxPageSize = count > 0 ? count : BUSCAR_MONITORES_MAX_PAGE_SIZE;
        } else if (maxPageSize == 0) {
            maxPageSize = BUSCAR_MONITORES_MAX_PAGE_SIZE;
        }
        
        return dao.list(new BuscarMonitor.Entities().fetchTarget()
                .whereId(filtro.getId())
                .whereIdTarget(filtro.getIdTarget())
                .whereIdTipoTarget(filtro.getIdTipoTarget())
                .orderByData(filtro.getOrderByDesc())
                .paging(pagina, maxPageSize, count));
    }    

    @Transactional(readOnly = true)
    public Integer buscarTargetsCount(TargetFiltro filtro) {
        Long count = (Long) dao.uniqueResult(new BuscarTarget.Count()
                .whereId(filtro.getId())
                .whereIdTipo(filtro.getIdTipo()));
        return count.intValue();
    }
    
    
    @Transactional(readOnly = true)
    public List<Target> buscarTargets(TargetFiltro filtro, int pagina, Integer maxPageSize) {
        Integer count = buscarTargetsCount(filtro);
        
        if (maxPageSize ==  null) {
           maxPageSize = count > 0 ? count : BUSCAR_TARGETS_MAX_PAGE_SIZE;
        } else if (maxPageSize == 0) {
            maxPageSize = BUSCAR_TARGETS_MAX_PAGE_SIZE;
        }
        
        return dao.list(new BuscarTarget.Entities()
                .whereId(filtro.getId())
                .whereIdTipo(filtro.getIdTipo())
                .paging(pagina, maxPageSize, count));
    }    
    
}
