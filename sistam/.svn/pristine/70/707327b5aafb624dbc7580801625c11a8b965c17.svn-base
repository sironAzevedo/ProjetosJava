/*
 * Serviço de controle e busca de documentos.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.valueobjects.CTACReceitaVo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DocumentoService {

    @AuthorizedResource("")
    public Map<Agenciamento, Documento> buscarDocumentoAnexoUnicoPorAgenciamento(Set<Agenciamento> agenciamentos);

    @AuthorizedResource("")
    public Documento buscarDocumentoPorTipoAgenciamentoCtacPortoDestino(TipoDocumento tipo, Agenciamento agenciamento, String numeroCTAC, Porto portoDestino);

    @AuthorizedResource("")
    public List<Documento> buscarDocumentoDoAgenciamentoPorTipo(TipoDocumento tipo, Agenciamento agenciamento);

    @AuthorizedResource("")
    public List<Documento> buscarDocumentoDoAgenciamentoPorTipoSemProtocolo(TipoDocumento tipo, Agenciamento agenciamento);
    
    @AuthorizedResource("")
    public Documento buscarDocumentoDaManobra(Manobra manobra);

    @AuthorizedResource("")
    public Documento buscarDocumentoDaOperacao(Operacao operacao);

    @AuthorizedResource("")
    public List<Documento> buscarDocumentosDoAgenciamento(Agenciamento agenciamento);

    @AuthorizedResource("")
    public Documento buscarDocumentoPorId(Long id);

    @AuthorizedResource("")
    public Documento salvarDocumento(Documento documento);

    @AuthorizedResource("")
    public void excluirDocumento(Documento documento);

    @AuthorizedResource("")
    public void registrarEmissaoDeDocumentoConhecimentoEmbarque(Agenciamento agenciamento, Date dataFormulario, CTACReceitaVo vo);

    @AuthorizedResource("")
    public void registrarEmissaoDeDocumentosConhecimentoEmbarque(Agenciamento agenciamento, Date dataFormulario, List<CTACReceitaVo> listaVo);

    @AuthorizedResource("")
    public Documento registrarEmissaoDeDocumento(TipoDocumento tipo, Agenciamento agenciamento, RepresentanteLegal representante, Boolean criarNovo);

    @AuthorizedResource("")
    public Documento registrarEmissaoDeDocumentoSemProtocolo(TipoDocumento tipo, Agenciamento agenciamento, RepresentanteLegal representante, Boolean criarNovo);
    
    @AuthorizedResource("")
    public Documento registrarEmissaoDeDocumento(TipoDocumento tipo, Agenciamento agenciamento, Boolean criarNovo);

    @AuthorizedResource("")
    public Documento registrarEmissaoDeDocumentoDaManobra(TipoDocumento tipo, Manobra manobra, RepresentanteLegal representante);

    @AuthorizedResource("")
    public Documento informarProtocoloDoDocumento(Documento documento);
}
