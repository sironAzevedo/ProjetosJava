/*
 * Serviço de controle e busca de pessoas.
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.valueobjects.FiltroPessoaProtecao;
import java.util.List;

public interface PessoaProtecaoService {

    @AuthorizedResource("")
    public Pessoa salvarPessoaProtecao(Pessoa pessoa);

    @AuthorizedResource("")
    public Pessoa buscarPessoaProtecaoPorCpf(String cpf);

    @AuthorizedResource("")
    public Pessoa buscarPessoaProtecaoPorId(long id);

    @AuthorizedResource("")
    public List<Pessoa> buscarPessoasProtecaoAtivaPorEmpresaNome(EmpresaProtecao empresa, String nome);

    @AuthorizedResource("")
    public List<Pessoa> buscarPessoasProtecaoPorFiltro(FiltroPessoaProtecao filtro);
}