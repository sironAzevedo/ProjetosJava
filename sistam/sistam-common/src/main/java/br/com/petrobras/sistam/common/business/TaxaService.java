/*
 * Serviço de controle e busca de taxas. 
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.TaxaMensalVO;
import java.util.List;
import java.util.Map;

public interface TaxaService {
    
    @AuthorizedResource("")
    public Taxa salvarTaxa(Taxa taxa);

    @AuthorizedResource("")
    public void excluirTaxa(Taxa taxa);
    
    @AuthorizedResource("")
    public List<Taxa> buscarTaxasPorAgenciamento(Agenciamento agenciamento);

    @AuthorizedResource("")
    public Taxa buscarTaxaPorId(Long id);

    @AuthorizedResource("")
    public List<Taxa> buscarTaxaPorAgenciamentoETipo(Agenciamento agenciamento, TipoTaxa tipo);

    @AuthorizedResource("")
    public List<TaxaMensalVO> buscarTaxasPorFiltro(FiltroTaxa filtro);
    
    @AuthorizedResource("")
    public Map<TipoTaxa, Double> buscarTaxasValorAcumulado(FiltroTaxa filtro);
    
    @AuthorizedResource("")
    public List<Taxa> buscarTaxasOrdenadasPorTipo(FiltroTaxa filtro);
    
    @AuthorizedResource("")
    public List<Taxa> buscarTaxasDaAgenciaEMesAnoOrdenadasPorTipo(Agencia agencia, Porto porto, List<TipoTaxa> tiposTaxa, Integer mes, Integer ano);
}
