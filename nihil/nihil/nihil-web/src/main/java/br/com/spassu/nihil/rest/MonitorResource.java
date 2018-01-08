/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.rest;

import br.com.spassu.nihil.common.entity.Monitor;
import br.com.spassu.nihil.common.entity.Target;
import br.com.spassu.nihil.common.filtro.MonitorFiltro;
import br.com.spassu.nihil.common.filtro.TargetFiltro;
import br.com.spassu.nihil.service.impl.MonitorServiceImpl;
import br.com.spassu.nihil.web.ListContainer;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADM
 */
@Path("/monitor")
@Component
public class MonitorResource {
    
    @Autowired
    private MonitorServiceImpl monitorService;

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public ListContainer list(@QueryParam("id") Long id,
                              @QueryParam("idTarget") Long idTarget,
                              @QueryParam("idTipoTarget") String idTipoTarget,
                              @QueryParam("online") Boolean online, 
                              @QueryParam("obrderByDesc") Boolean orderByDesc, 
                              @QueryParam("pagina") Integer pagina,
                              @QueryParam("maxPageSize") Integer maxPageSize) {
        
        MonitorFiltro filtro = new MonitorFiltro();
        filtro.setId(id);
        filtro.setIdTarget(idTarget);
        filtro.setIdTipoTarget(idTipoTarget);
        filtro.setOrderByDesc(orderByDesc);
        
        Integer total = monitorService.buscarMonitoresCount(filtro);
        List<Monitor> monitores = monitorService.buscarMonitores(filtro, pagina!=null?pagina-1:0, maxPageSize);

        ListContainer container = new ListContainer();
        container.setPagedList(monitores);
        container.setTotalGeral(total);
        
        return container;
    }
    

    @GET
    @Path("/resumo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Monitor> findResumo() {
        List<Monitor> resumo = new ArrayList<Monitor>();
        List<Target> targets = monitorService.buscarTargets(new TargetFiltro(), 0, null);
        for (Target target : targets) {
            MonitorFiltro filtro = new MonitorFiltro();
            filtro.setIdTarget(target.getId());
            filtro.setOrderByDesc(true);
            List<Monitor> monitores = monitorService.buscarMonitores(filtro, 0, 0);
            Monitor ultimo = monitores != null && !monitores.isEmpty() ? monitores.get(0) : null;
            if (ultimo != null) {
                resumo.add(ultimo);
            }
        }
        
        return resumo;
        
    }
    
}
