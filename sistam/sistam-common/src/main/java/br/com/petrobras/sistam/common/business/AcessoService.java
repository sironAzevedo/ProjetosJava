/*
 * Serviço de comunicação com o controle de acesso.  
 */

package br.com.petrobras.sistam.common.business;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import br.com.petrobras.sistam.common.valueobjects.Usuario;
import java.util.List;

/**
 * Serviço de comunicação com o controle de acesso. Todo o acesso à API do
 * controle de acesso deve ser feito através deste serviço.
 */
public interface AcessoService {
    
    /**
     * Conecta a aplicação à uma sessão previamente autenticada do controle de acesso.
     * @param token Identificador da sessão 
     * @return Objetos com dados de autenticação
     */
    CredentialsBean confirmarLogon(Object token);
        
    /**
     * Busca dados de autenticação do usuário logado.
     * @return Objeto com dados de autenticação
     */
    CredentialsBean buscarDadosDeAutenticacao();
    
    
    boolean validarPermissao(Agencia agencia, RecursoCA recurso);
    
    public List<Usuario> obterUsuariosPorAgenciaEPapel(Agencia agencia, String codPapel);
    
    
    /**
     * Recupera o application environemtn uid do cav4 para 
     * ser passado como parametro para a tela de login
     * centralizado.     * 
     * 
     */    
    public String buscarApplicationEnvironmentUID();
    
    public String buscarEnvironmentID();
}
