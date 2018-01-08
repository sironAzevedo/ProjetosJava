/*
 * Serviço de controle e busca de informações das embarcações.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmbarcacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import java.util.Date;
import java.util.List;

public interface EmbarcacaoService {
    
    @AuthorizedResource("")
    public List<Escala> buscarEscalaPorEmbarcacao(Embarcacao embarcacao, Date eta);
    
    @AuthorizedResource("")
    public List<Escala> buscarUltimasEscalasDaEmbarcacao(Embarcacao embarcacao, int quantidadeDeEscalas);

    @AuthorizedResource("")
    public Embarcacao buscarEmbarcacaoPorId(String id);

    @AuthorizedResource("")
    List<Embarcacao> buscarEmbarcacoes(String nome); 
    
    @AuthorizedResource("")
    List<Embarcacao> buscarEmbarcacoesPorFiltro(FiltroEmbarcacao filtro); 

    @AuthorizedResource("")
    public List<Embarcacao> buscarEmbarcacoesPorFiltro(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas);

    @AuthorizedResource("")
    public Embarcacao salvarEmbarcacao(Embarcacao embarcacao);

}
