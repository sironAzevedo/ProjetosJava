package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;

public class ServicoBuilder {

    private Servico servico;
    
    private ServicoBuilder(Servico servico) {
        this.servico = servico;
    }
    
    public static ServicoBuilder novoServico() {
        return new ServicoBuilder(new Servico());
    }
    
    public Servico build() {
        return this.servico;
    }
    
    public ServicoBuilder comId(Long id){
        this.servico.setId(id);
        return this;
    }
    
    public ServicoBuilder comNome(String nome) {
        this.servico.setNomeServico(nome);
        return this;
    }
    
    public ServicoBuilder comTipoServico(TipoServico tipoServico) {
        this.servico.setTipoServico(tipoServico);
        return this;
    }
    
    public ServicoBuilder daEmpresa(EmpresaMaritima empresa){
        this.servico.setEmpresa(empresa);
        return this;
    }
    
}
