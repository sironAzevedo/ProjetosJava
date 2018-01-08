package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;

public class EmpresaProtecaoBuilder {

    private EmpresaProtecao empresa;

    private EmpresaProtecaoBuilder(EmpresaProtecao empresa) {
        this.empresa = empresa;
    }

    public static EmpresaProtecaoBuilder novaEmpresaProtecao() {
        return new EmpresaProtecaoBuilder(new EmpresaProtecao());
    }

    public EmpresaProtecao build() {
        return this.empresa;
    }

    public EmpresaProtecaoBuilder comId(Long id) {
        this.empresa.setId(id);
        return this;
    }

    public EmpresaProtecaoBuilder comCnpj(String cnpj) {
        this.empresa.setCnpj(cnpj);
        return this;
    }

    public EmpresaProtecaoBuilder comRazaoSocial(String razaoSocial) {
        this.empresa.setRazaoSocial(razaoSocial);
        return this;
    }

    public EmpresaProtecaoBuilder comTelefone(String telefone) {
        this.empresa.setTelefone(telefone);
        return this;
    }

    public EmpresaProtecaoBuilder comEmail(String email) {
        this.empresa.setEmail(email);
        return this;
    }

    public EmpresaProtecaoBuilder comCidade(String cidade) {
        this.empresa.setCidade(cidade);
        return this;
    }

    public EmpresaProtecaoBuilder comEstado(String estado) {
        this.empresa.setEstado(estado);
        return this;
    }

    public EmpresaProtecaoBuilder comServicos(TipoServicoProtecao... tipos) {
        for (TipoServicoProtecao tipo : tipos) {
            this.empresa.adicionarTipoServico(tipo);
        }
        return this;
    }
}
