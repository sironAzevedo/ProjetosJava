/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.rest;

import br.com.spassu.nihil.service.impl.LogServiceImpl;
import java.io.InputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADM
 */
@Path("/log")
@Component
public class LogResource {
    
    @Autowired
    private LogServiceImpl logService;

    @GET
    @Path("/app")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getLogApp() {
        
        InputStream is = logService.buscarLogApp();
        
        return Response
            .ok(is, MediaType.APPLICATION_OCTET_STREAM)
            .header("content-disposition","attachment; filename = nihil.log")
            .build();
    }
    
    
}
