package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.EmpresaProtecaoServico;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;

public class EmpresaProtecaoServicoBuilder {

    private EmpresaProtecaoServico servico;

    private EmpresaProtecaoServicoBuilder(EmpresaProtecaoServico servico) {
        this.servico = servico;
    }

    public static EmpresaProtecaoServicoBuilder novoServico() {
        return new EmpresaProtecaoServicoBuilder(new EmpresaProtecaoServico());
    }

    public EmpresaProtecaoServico build() {
        return this.servico;
    }

    public EmpresaProtecaoServicoBuilder comId(Long id) {
        this.servico.setId(id);
        return this;
    }

    public EmpresaProtecaoServicoBuilder comEmpresa(EmpresaProtecao empresa) {
        this.servico.setEmpresa(empresa);
        return this;
    }

    public EmpresaProtecaoServicoBuilder comTipo(TipoServicoProtecao tipo) {
        this.servico.setTipo(tipo);
        return this;
    }
}
