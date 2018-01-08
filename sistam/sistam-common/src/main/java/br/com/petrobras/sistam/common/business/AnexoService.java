/*
 * Serviço de controle e busca de anexos/arquivos.
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import java.util.List;

public interface AnexoService {

    @AuthorizedResource("")
    public List<Anexo> buscarAnexosDoAgenciamento(Agenciamento agenciamento);

    @AuthorizedResource("")
    public Arquivo buscarArquivoDoAnexo(Anexo anexo);

    @AuthorizedResource("")
    public List<Anexo> salvarAnexos(List<Anexo> listaDeAnexos);

    @AuthorizedResource("")
    public void excluirAnexo(Anexo anexo);
}
