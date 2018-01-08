/*
 * Serviço de comunicação,controle e busca de informações das agencias.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.sistam.common.entity.Porto;
import java.util.List;

public interface AgenciaService {
    
    @AuthorizedResource("")
    public List<Agencia> buscarAgencias();
    
    @AuthorizedResource("")
    public Agencia buscarAgenciaPorId(Long id);
    
    @AuthorizedResource("")
    public Agencia buscarAgenciaPorSigla(String sigla);
    
    @AuthorizedResource("")
    public List<AgenciaSigo> buscarAgenciaSigoPorNome(String nome);
    
    @AuthorizedResource("")
    public AgenciaSigo buscarAgenciaSigoPorId(Long id);

    @AuthorizedResource("")
    public List<RepresentanteLegal> buscarContatosPorAgencia(Agencia agencia, Boolean ativo);
    
    @AuthorizedResource("")
    public List<Agencia> buscarAgenciasPorPorto(Porto porto);
    
    @AuthorizedResource("")
    public Agencia salvarAgencia(Agencia agencia);

    @AuthorizedResource("")
    public RepresentanteLegal salvarRepresentanteLegal(RepresentanteLegal agenciaContato);
    
    @AuthorizedResource("")
    public void excluirAgenciaContato(RepresentanteLegal agenciaContato);

    @AuthorizedResource("")
    public List<AgenciaOrgaoDespacho> buscarOrgaosDespachoPorAgencia(Agencia agencia);
    
    @AuthorizedResource("")
    public AgenciaOrgaoDespacho salvarAgenciaOrgaoDespacho(AgenciaOrgaoDespacho agenciaOrgaoDespacho);
    
    @AuthorizedResource("")
    public void excluirAgenciaOrgaoDespacho(AgenciaOrgaoDespacho agenciaOrgaoDespacho);
    
    @AuthorizedResource("")
    public Destinatario salvarDestinatario(Destinatario destinatario);
    
    @AuthorizedResource("")
    public Destinatario excluirDestinatario(Destinatario destinatario);
    
    @AuthorizedResource("")
    public List<Destinatario> buscarDestinatariosDaAgencia(Agencia agencia);
    
}
