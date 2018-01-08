package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;

public class PessoaAcessoBuilder {

    private PessoaAcesso pessoa;

    private PessoaAcessoBuilder(PessoaAcesso pessoa) {
        this.pessoa = pessoa;
    }

    public static PessoaAcessoBuilder novoPessoa() {
        return new PessoaAcessoBuilder(new PessoaAcesso());
    }

    public static PessoaAcessoBuilder novoPessoa(PessoaAcesso pessoaAcesso) {
        return new PessoaAcessoBuilder(pessoaAcesso);
    }

    public PessoaAcesso build() {
        return this.pessoa;
    }

    public PessoaAcessoBuilder comId(Long id) {
        this.pessoa.setId(id);
        return this;
    }

    public PessoaAcessoBuilder comPessoa(Pessoa pessoa) {
        this.pessoa.setNome(pessoa.getNome());
        this.pessoa.setDocumento(pessoa.getDocumento());
        this.pessoa.setCpf(pessoa.getCpf());
        this.pessoa.setDataNascimento(pessoa.getDataNascimento());
        this.pessoa.setNacionalidade(pessoa.getNacionalidade());
        if (pessoa.getEmpresa() != null) {
            this.pessoa.setNomeEmpresa(pessoa.getEmpresa().getRazaoSocial());
            this.pessoa.setCnpjEmpresa(pessoa.getEmpresa().getCnpj());
        }
        return this;
    }

    public PessoaAcessoBuilder isAtivo(boolean ativo) {
        this.pessoa.setAtivo(ativo);
        return this;
    }

    public PessoaAcessoBuilder isGeradoPolicia(boolean geradoPolicia) {
        this.pessoa.setFormularioGeradoPolicia(geradoPolicia);
        return this;
    }

    public PessoaAcessoBuilder isGeradoReceita(boolean geradoReceita) {
        this.pessoa.setFormularioGeradoReceita(geradoReceita);
        return this;
    }

    public PessoaAcessoBuilder comBagagem(String bagagem) {
        this.pessoa.setBagagem(bagagem);
        return this;
    }

    public PessoaAcessoBuilder comVolume(Integer volume) {
        this.pessoa.setVolume(volume);
        return this;
    }

    public PessoaAcessoBuilder comResponsavel(String responsavel) {
        this.pessoa.setResponsavel(responsavel);
        return this;
    }

    public PessoaAcessoBuilder comServicoProtecao(ServicoProtecao servicoProtecao) {
        this.pessoa.setServicoProtecao(servicoProtecao);
        return this;
    }
}
