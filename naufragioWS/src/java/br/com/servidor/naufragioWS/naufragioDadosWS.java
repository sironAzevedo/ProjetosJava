/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor.naufragioWS;

import br.com.naufragio.DAO.NaufragioDAO;
import br.com.valueobjects.NaufragioVO;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ADM
 */
@WebService(serviceName = "naufragioDadosWS")
public class naufragioDadosWS {

    /**
     * This is a sample web service operation
     */
    /*@WebMethod(operationName = "hello")
     public String hello(@WebParam(name = "name") String txt) {
     return "Hello " + txt + " !";
     }*/
    @WebMethod(operationName = "inserirUsuario")
    public ArrayList<NaufragioVO> buscaMunicipio(@WebParam(name = "nome") String nome) {
        NaufragioDAO dao = new NaufragioDAO();
        return dao.buscaTodosNaufragio();
    }
}
