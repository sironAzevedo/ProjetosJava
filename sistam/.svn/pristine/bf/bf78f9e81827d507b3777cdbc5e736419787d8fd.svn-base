package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Armazena as informações de usuário que devem ser passadas para o módulo
 * desktop. Extende o objeto fornecido pelo fcorp para acrescentar os dados 
 * armazenados no contexto do usuário. Preferencialmente, a classe não deve
 * expor o contexto diretamente. Ao invés disso, deve disponibilizar getters
 * para os dados necessários.
 */
public class SistamCredentialsBean extends CredentialsBean {

    private ContextoUsuario contexto;
        
    
    public SistamCredentialsBean() {}

    public SistamCredentialsBean(String logon, String username, Set securityResources, ContextoUsuario contexto) {
        super(logon, username, securityResources);
        this.contexto = contexto;
    }

    public Map<Agencia, Set<String>> getCredenciais() {
        return contexto.getCredenciais();
    }
    
    public List<Agencia> getAgenciasAutorizadas() {
        List<Agencia> agencias = new ArrayList<Agencia>(getCredenciais().keySet());
        agencias.remove(null);
        return agencias;
    }
    
    public List<Agencia> getAgenciasAutorizadas(RecursoCA... recurso) {
        Set<Agencia> agencias = new HashSet<Agencia>();
        
        for ( Map.Entry< Agencia, Set<String> > entry : contexto.getCredenciais().entrySet() ) {
            for (RecursoCA rec : recurso) {
                if (entry.getValue().contains(rec.name())) {
                    agencias.add(entry.getKey());
                }
            }
        }        
        
        agencias.remove(null);
        
        return new ArrayList<Agencia>(agencias);

    }
    
    /**
     * 
     * @param grupo
     * @param recurso
     * @return retorna true se naquele grupo ele tiver o recurso
     */
    public boolean contemRecurso(Agencia agencia, RecursoCA recurso) {
        return getCredenciais().get(agencia) == null? false : getCredenciais().get(agencia).contains(recurso.toString());
    } 

}