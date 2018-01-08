/*
 * Serviço de controle e busca de porto.
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import java.util.List;

public interface PortoService {
    
    @AuthorizedResource("")
    Porto buscarPortosPorId(String id); 
    
    @AuthorizedResource("")
    PortoComplemento buscarPortoComplemento(Long id); 
    
    @AuthorizedResource("")
    List<PortoComplemento> buscarPortosComplementosPorPorto(Porto porto); 
    
    @AuthorizedResource("")
    Porto buscarPorto(Porto porto); 
    
    @AuthorizedResource("")
    PortoComplemento salvarPortoComplemento(PortoComplemento complemento);
   
    @AuthorizedResource("")
    void excluirPortoComplemento(PortoComplemento complemento);
   
    @AuthorizedResource("")
    List<Porto> buscarPortos(String nome, Pais pais); 

    @AuthorizedResource("")
    PontoOperacional buscarPontoOperacionalPorId(String id); 
    
    @AuthorizedResource("")
    List<PontoOperacional> buscarPontoOperacionalPorPorto(Porto porto);
 
    @AuthorizedResource("")
    List<PontoAtracacao> buscarPontoAtracacaoPorPontoOperacional(PontoOperacional pontoOperacional); 

    @AuthorizedResource("")
    List<PontoAtracacao> buscarPontosAtracacaolPorPorto(Porto porto);
    
    @AuthorizedResource("")
    Pais buscarPaisPorId(String id); 
   
    @AuthorizedResource("")
    List<PontoAtracacao> buscarPontoAtracacaoPorAgencia(Agencia agencia);
    
    @AuthorizedResource("")
    PontoAtracacao buscarPontoAtracacaoPorId(Long id);
    
    @AuthorizedResource("")
    List<Porto> buscarPortosPorAgencia(Agencia... agencias);
    
    @AuthorizedResource("")
    public List<AgenciaPorto> buscarAgenciasPortosPorAgencia(Agencia agencia);

    @AuthorizedResource("")
    public PontoAtracacao salvarPontoAtracacao(PontoAtracacao pontoAtracacao);
    
    @AuthorizedResource("")
    public AgenciaPorto salvarAgenciaPorto(AgenciaPorto agenciaPorto);
    
    @AuthorizedResource("")
    public List<PortoComplemento> buscarPortosComplemento();
    
    @AuthorizedResource("")
    public List<Porto> buscarPortosNacionaisComComplementos();
}
