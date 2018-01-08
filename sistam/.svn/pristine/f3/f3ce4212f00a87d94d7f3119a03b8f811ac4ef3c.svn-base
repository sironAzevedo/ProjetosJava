package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;

public class PortoComplementoBuilder {

    private PortoComplemento portoComplemento;

    private PortoComplementoBuilder(PortoComplemento portoComplemento) {
        this.portoComplemento = portoComplemento;
    }

    public static PortoComplementoBuilder novoPortoComplemento() {
        return new PortoComplementoBuilder(new PortoComplemento());
    }

    public PortoComplemento build() {
        return this.portoComplemento;
    }

    public PortoComplementoBuilder comNomeCtac(String nomeCtac) {
        this.portoComplemento.setNomeCtac(nomeCtac);
        return this;
    }

    public PortoComplementoBuilder comCnpj(String cnpj) {
        this.portoComplemento.setCnpjComMascara(cnpj);
        return this;
    }

    public PortoComplementoBuilder comInscricaoEstadual(String inscricaoEstadual) {
        this.portoComplemento.setInscricaoEstadual(inscricaoEstadual);
        return this;
    }

    public PortoComplementoBuilder comEndereco(String endereco) {
        this.portoComplemento.setEndereco(endereco);
        return this;
    }

    public PortoComplementoBuilder comCidade(String cidade) {
        this.portoComplemento.setCidade(cidade);
        return this;
    }

    public PortoComplementoBuilder comEstado(String estado) {
        this.portoComplemento.setEstado(estado);
        return this;
    }

    public PortoComplementoBuilder comPorto(Porto porto) {
        this.portoComplemento.setPorto(porto);
        return this;
    }
    
}
