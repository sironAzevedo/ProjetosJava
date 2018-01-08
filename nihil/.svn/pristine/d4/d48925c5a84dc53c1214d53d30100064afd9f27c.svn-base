/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.rest;

import br.com.spassu.nihil.common.entity.Projeto;
import br.com.spassu.nihil.service.impl.ProjetoServiceImpl;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("/projeto")
@Component
public class ProjetoResource {
    
    @Autowired
    private ProjetoServiceImpl projetoService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Projeto> list() {
        return projetoService.buscarProjetos();
    }
    
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Projeto save(Projeto projeto) {
        return projetoService.salvarProjeto(projeto);
    }
    
    @DELETE
    @Path("/remove/{id}")
    public void remove(@PathParam("id")Long projetoId) {
        projetoService.remover(projetoId);
    }
    
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Projeto buscarPorId(@PathParam("id")Long projetoId) {
        return projetoService.buscarPorId(projetoId);
    }
    
}
