/*
 * Serviço de controle e busca de pendências de um agenciamento.
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import java.util.List;

public interface PendenciaService {
    
    
    /**
     * Busca as pendências de um agenciamento tendo de opção de filtrar por resolvidas, 
     * não resolvidas ou ambas.
     * @param agenciamento
     * @param resolvida
     * @return 
     */
    @AuthorizedResource("")
    public List<Pendencia> buscarPendenciasDoAgenciamento(Agenciamento agenciamento, Boolean resolvida);
    
    /**
     * Busca uma pendência do agencimanto de acordo com o tipo informado.
     * @param pendencias
     * @return 
     */
    @AuthorizedResource("")
    public Pendencia buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(Agenciamento agenciamento, TipoPendencia tipo);

    @AuthorizedResource("")
    public List<Pendencia> buscarPendenciasDoAgenciamentoPorTipo(Agenciamento agenciamento, TipoPendencia tipo);
    
    
    /**
     * Salva uma pendência.
     */
    @AuthorizedResource("")
    public Pendencia criarPendencia(Agenciamento agenciamento, TipoPendencia tipo);
    
    /**
     * Marca como resolvida a pendência informada.
     * @param pendencias
     * @return 
     */
    @AuthorizedResource("")
    public Pendencia resolverPendenciaManual(Pendencia pendencia);
    
    @AuthorizedResource("")
    public Pendencia resolverPendenciaDoDocumento(Documento documento);
    
    @AuthorizedResource("")
    public Pendencia resolverPendenciaDaTaxa(Taxa taxa);
    
    @AuthorizedResource("")
    public Pendencia removerResolucaoDaPendenciaDaTaxa(Taxa taxa);
    
    @AuthorizedResource("")
    public Pendencia excluirPendencia(Pendencia pendencia);
    
    @AuthorizedResource("")
    public Pendencia removerResolucaoDaPendencia(Pendencia pendencia);
}
