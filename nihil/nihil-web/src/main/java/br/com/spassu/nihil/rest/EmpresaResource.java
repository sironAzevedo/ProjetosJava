/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.rest;

import br.com.spassu.nihil.common.entity.Empresa;
import br.com.spassu.nihil.service.impl.EmpresaServiceImpl;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADM
 */
@Path("/empresa")
@Component
public class EmpresaResource {
    
    @Autowired
    private EmpresaServiceImpl empresaService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> list() {
        return empresaService.buscarEmpresas();
    }
    
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Empresa save(Empresa empresa) {
        return empresaService.salvarEmpresa(empresa);
    }
    
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Empresa buscarPorId(@PathParam("id")Long empresaId) {
        return empresaService.buscarEmpresaPorId(empresaId);
    }
    
}
