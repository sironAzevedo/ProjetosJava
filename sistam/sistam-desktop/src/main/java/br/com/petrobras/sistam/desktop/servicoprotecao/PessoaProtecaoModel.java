package br.com.petrobras.sistam.desktop.servicoprotecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.valueobjects.FiltroPessoaProtecao;
import java.util.Collections;
import java.util.List;

/**
 * @author adzk
 */
public class PessoaProtecaoModel extends BasePresentationModel<SistamService> {

    private FiltroPessoaProtecao filtro;
    private List<Pessoa> pessoas = Collections.EMPTY_LIST;
    private Pessoa pessoaSelecionada;

    public PessoaProtecaoModel() {
        filtro = new FiltroPessoaProtecao();
    }

    public final void pesquisar() {
        pessoas.clear();
        pessoas = getService().buscarPessoasProtecaoPorFiltro(filtro);
        firePropertyChange("pessoas", null, null);
    }

    public final void limparPesquisa() {
        filtro.limpar();
    }

    public FiltroPessoaProtecao getFiltro() {
        return filtro;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
        firePropertyChange("pessoaSelecionada", null, null);
    }

    public Pessoa obterNovaPessoa() {
        return new Pessoa();
    }

    public Pessoa obterPessoaParaEdicao() {
        return getService().buscarPessoaProtecaoPorId(pessoaSelecionada.getId());
    }
    
}