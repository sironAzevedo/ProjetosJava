package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.enums.TipoServico;
import java.util.Date;

public class ServicoManobraBuilder {

    private ServicoManobra servicoManobra;
    
    private ServicoManobraBuilder(ServicoManobra servicoManobra) {
        this.servicoManobra = servicoManobra;
    }
    
    public static ServicoManobraBuilder novoServicoManobra() {
        return new ServicoManobraBuilder(new ServicoManobra());
    }
    
    public static ServicoManobraBuilder novoServicoManobra(ServicoManobra servico) {
        return new ServicoManobraBuilder(servico);
    }
    
    public ServicoManobra build() {
        return this.servicoManobra;
    }
    
    public ServicoManobraBuilder comId(Long id){
        this.servicoManobra.setId(id);
        return this;
    }
    
    public ServicoManobraBuilder daManobra(Manobra manobra) {
        this.servicoManobra.setManobra(manobra);
        return this;
    }
    
    public ServicoManobraBuilder comTipoServico(TipoServico tipoServico) {
        this.servicoManobra.setTipoDoServico(tipoServico);
        return this;
    }
    
    public ServicoManobraBuilder comDataProgramada(Date dataProgramada){
        this.servicoManobra.setDataProgramada(dataProgramada);
        return this;
    }

    public ServicoManobraBuilder comDataEnvio(Date dataEnvio){
        this.servicoManobra.setDataEnvio(dataEnvio);
        return this;
    }
    
    
    public ServicoManobraBuilder daEmpresa(EmpresaMaritima empresa){
        this.servicoManobra.setEmpresaMaritima(empresa);
        return this;
    }
    
}
