package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.enums.TipoAcesso;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoNacionalidade;
import br.com.petrobras.sistam.common.enums.TipoSolicitacaoTransito;

public class ServicoAcessoPessoaBuilder {

    private ServicoAcessoPessoa servicoAcessoPessoa;

    private ServicoAcessoPessoaBuilder(ServicoAcessoPessoa servicoAcessoPessoa) {
        this.servicoAcessoPessoa = servicoAcessoPessoa;
    }

    public static ServicoAcessoPessoaBuilder novoServicoAcessoPessoa() {
        return new ServicoAcessoPessoaBuilder(new ServicoAcessoPessoa());
    }

    public ServicoAcessoPessoa build() {
        return this.servicoAcessoPessoa;
    }

    public ServicoAcessoPessoaBuilder comId(Long id) {
        this.servicoAcessoPessoa.setId(id);
        return this;
    }

    public ServicoAcessoPessoaBuilder comServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoAcessoPessoa.setServicoProtecao(servicoProtecao);
        return this;
    }

    public ServicoAcessoPessoaBuilder comTipoSolicitacaoTransito(TipoSolicitacaoTransito tipoSolicitacaoTransito) {
        this.servicoAcessoPessoa.setTipoSolicitacaoTransito(tipoSolicitacaoTransito);
        return this;
    }

    public ServicoAcessoPessoaBuilder comTipoAcesso(TipoAcesso tipoAcesso) {
        this.servicoAcessoPessoa.setTipoAcesso(tipoAcesso);
        return this;
    }

    public ServicoAcessoPessoaBuilder comTipoCategoria(TipoCategoria tipoCategoria) {
        this.servicoAcessoPessoa.setTipoCategoria(tipoCategoria);
        return this;
    }

    public ServicoAcessoPessoaBuilder comTipoNacionalidade(TipoNacionalidade tipoNacionalidade) {
        this.servicoAcessoPessoa.setTipoNacionalidade(tipoNacionalidade);
        return this;
    }

    public ServicoAcessoPessoaBuilder comCompanhiaDocas(boolean companhiaDocas) {
        this.servicoAcessoPessoa.setCompanhiaDocas(companhiaDocas);
        return this;
    }

    public ServicoAcessoPessoaBuilder comTerminal(boolean terminal) {
        this.servicoAcessoPessoa.setTerminal(terminal);
        return this;
    }

    public ServicoAcessoPessoaBuilder comDescricaoTerminal(String descricaoTerminal) {
        this.servicoAcessoPessoa.setDescricaoTerminal(descricaoTerminal);
        return this;
    }

    public ServicoAcessoPessoaBuilder comPrestadorServico(EmpresaProtecao empresa) {
        this.servicoAcessoPessoa.setNomePrestadorServico(empresa.getRazaoSocial());
        this.servicoAcessoPessoa.setCnpjPrestadorServico(empresa.getCnpj());
        return this;
    }

    public ServicoAcessoPessoaBuilder comPrestadorServicoAtividadeBordo(String atividadeBordo) {
        this.servicoAcessoPessoa.setPrestadorServicoAtividadeBordo(atividadeBordo);
        return this;
    }

    public ServicoAcessoPessoaBuilder comPessoa(PessoaAcesso pessoa) {
        this.servicoAcessoPessoa.adicionarPessoa(pessoa);
        return this;
    }
}
