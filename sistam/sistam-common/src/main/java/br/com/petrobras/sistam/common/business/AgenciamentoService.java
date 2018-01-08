/*
 * Serviço de comunicação,controle e busca de informações dos agenciamntos.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.AgenciamentoSanitaria;
import br.com.petrobras.sistam.common.entity.AgenciamentoViagem;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.valueobjects.GrupoAgenciaEStatusEmbarcacaoVO;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamentoAtendimento;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import br.com.petrobras.sistam.common.valueobjects.GrupoAgenciaEFrotaVO;
import br.com.petrobras.sistam.common.valueobjects.GrupoStatusAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.RelatorioAgenciamentoAtendimentoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioMovimentacaoEmbarcacaoVO;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AgenciamentoService {
    
    /*
     * Esta consulta pesquisa os agenciamentos com data maior ou igual à data de corte passada.
     */
    @AuthorizedResource("")
    public List<GrupoStatusAgenciamento> buscarAgenciamentosParaCaixaDeEntrada(Agencia agencia, Porto porto, Date dataDeCorte); 

    @AuthorizedResource("")
    public Agenciamento buscarAgenciamentoPorId(Long id); 
    
    @AuthorizedResource("")
    public AgenciamentoViagem buscarAgenciamentoViagemPorId(Long id);    
    
    @AuthorizedResource("")
    public Map<GrupoAgenciaEStatusEmbarcacaoVO, InputStream> gerarArquivosZipComPdfsAgrupadosPorStatusEmbarcacao(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas);  
    
    @AuthorizedResource("")
     public Map<GrupoAgenciaEFrotaVO, InputStream> gerarArquivosZipComPdfsAgrupadosPorFrota(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas); 
    
    @AuthorizedResource("")
    public List<Agenciamento> buscarAgenciamentosPorFiltroRelatorioTimesheet(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas);  
    
    @AuthorizedResource("")
    public AgenciamentoSanitaria buscarAgenciamentoSanitariaPorId(Long id);
    
    @AuthorizedResource("")
    public Agenciamento buscarAgenciamentoParaDesvioDeRota(Long idAgenciamento);
    
    @AuthorizedResource("")
    public Agenciamento salvarAgenciamento(Agenciamento agenciamento);
    
    @AuthorizedResource("")
    public Agenciamento criarNovoAgenciamento(Agenciamento agenciamento, Integer ano);
    
    @AuthorizedResource("")
    public boolean verificarSeAgenciamentoJaFoiCriado(Agenciamento novoAgenciamento);
    
    @AuthorizedResource("")
    public void salvarDesvio(Desvio desvio, Porto novoPorto,  String novoDestinoIntermediario);

    @AuthorizedResource("")
    public CancelamentoAgenciamento buscarCancelamentoDoAgenciamento(Long idAgenciamento);
    
    @AuthorizedResource("")
    public CancelamentoAgenciamento cancelarAgenciamento(CancelamentoAgenciamento cancelamento);
    
    @AuthorizedResource("")
    public List<Agenciamento> buscarAgenciamentosPorFiltro(FiltroAgenciamento filtro);
    
    @AuthorizedResource("")
    public List<Agenciamento> buscarAgenciamentosRelatorioProdutividade(FiltroAgenciamento filtro);
    
    @AuthorizedResource("")
    public List<RelatorioAgenciamentoAtendimentoVO> buscarAgenciamentosRelatorioAtendimento(FiltroAgenciamentoAtendimento filtro); 
    
    @AuthorizedResource("")
    public List<Agenciamento> buscarAgenciamentosParaPainel(Agencia agencia, List<StatusEmbarcacao> listaStatus); 
    
    @AuthorizedResource("")
    public RelatorioMovimentacaoEmbarcacaoVO buscarDadosRelatorioMovimentacaoEmbarcacao(Agencia agencia, Porto porto); 
    
    @AuthorizedResource("")
    public Acompanhamento salvarAcompanhamento(Acompanhamento acompanhamento);
    
    @AuthorizedResource("")
    public List<Acompanhamento> buscarAcompanhamentos(Agenciamento agenciamento);
    
    
}
