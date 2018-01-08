/*
 * Serviço de comunicação, controle e busca de informações das manobras.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioManobra;
import br.com.petrobras.sistam.common.valueobjects.RelatorioManobraVO;
import java.util.Date;
import java.util.List;

public interface ManobraService {
    
    @AuthorizedResource("")
    public Manobra salvarManobra(Manobra manobra); 

    @AuthorizedResource("")
    public List<Manobra> buscarManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento(Agenciamento agenciamento);
            
    @AuthorizedResource("")
    List<Manobra> buscarManobrasPorAgenciamento(Agenciamento agenciamento);
    
    @AuthorizedResource("")
    Manobra registrarManobra(Manobra manobra); 
    
    @AuthorizedResource("")
    public Manobra cancelarManobraForaDoProazo(Manobra manobra, String motivoDoCancelamento);
    
    @AuthorizedResource("")
    public Manobra movimentarManobra(Manobra manobra);
    
    @AuthorizedResource("")
    public Manobra encerrarManobra(Manobra manobra, Porto portoDestino, Date eta);
    
    @AuthorizedResource("")
    public Manobra buscarManobrasPorId(Long id);
    
    @AuthorizedResource("")
    public ServicoManobra buscarServicoManobrasPorId(Long id);
     
    @AuthorizedResource("")
    public ServicoResponsavel buscarServicoResponsavelPorId(Long id);
    
    @AuthorizedResource("")
    public List<ServicoResponsavel> buscarServicoResponsavelPorManobra(Manobra manobra);
    
    @AuthorizedResource("")
    public ServicoManobra salvarServicoManobra(ServicoManobra servicoManobra);

    @AuthorizedResource("")
    public void excluirServicoManobra(ServicoManobra servicoManobra);
    
    @AuthorizedResource("")
    public void solicitarServicoManobra(Manobra manobra);
    
    @AuthorizedResource("")
    public ServicoResponsavel salvarResponsavel(ServicoResponsavel responsavel);
    
    @AuthorizedResource("")
    public ServicoResponsavel excluirResponsavel(ServicoResponsavel responsavel);
    
    @AuthorizedResource("")
    public void validarSalvarResposnavel(ServicoResponsavel responsavel);
    
    @AuthorizedResource("")
    public void cancelarManobraDentroPrazo(Manobra manobra, String motivo);
    
    @AuthorizedResource("")
    public void validarSalvarServicoManobra(ServicoManobra servicoManobra);
 
    @AuthorizedResource("")
    public List<RelatorioManobraVO> buscarManobrasParaRelatorio(FiltroRelatorioManobra filtro);   
}
