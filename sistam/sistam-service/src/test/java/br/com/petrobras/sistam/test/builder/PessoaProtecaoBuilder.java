package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import java.util.Date;

public class PessoaProtecaoBuilder {

    private Pessoa pessoa;

    private PessoaProtecaoBuilder(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public static PessoaProtecaoBuilder novaPessoaProtecao() {
        return new PessoaProtecaoBuilder(new Pessoa());
    }

    public Pessoa build() {
        return this.pessoa;
    }

    public PessoaProtecaoBuilder comId(Long id) {
        this.pessoa.setId(id);
        return this;
    }

    public PessoaProtecaoBuilder comCpf(String cpf) {
        this.pessoa.setCpf(cpf);
        return this;
    }

    public PessoaProtecaoBuilder comDocumento(String documento) {
        this.pessoa.setDocumento(documento);
        return this;
    }

    public PessoaProtecaoBuilder comNome(String nome) {
        this.pessoa.setNome(nome);
        return this;
    }

    public PessoaProtecaoBuilder comDataNascimento(Date dataNascimento) {
        this.pessoa.setDataNascimento(dataNascimento);
        return this;
    }

    public PessoaProtecaoBuilder comEmpresa(EmpresaProtecao empresa) {
        this.pessoa.setEmpresa(empresa);
        return this;
    }
}
