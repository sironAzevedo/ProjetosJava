package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import java.util.Date;

public class ServicoTransporteBuilder {
    
    private ServicoTransporte servicoTransporte;
    
    private ServicoTransporteBuilder(ServicoTransporte servicoTransporte) {
        this.servicoTransporte = servicoTransporte;
    }
    
    public static ServicoTransporteBuilder novoServicoTransporte() {
        return new ServicoTransporteBuilder(new ServicoTransporte());
    }
    
    public static ServicoTransporteBuilder novoServicoTransporte(ServicoTransporte servicoTransporte) {
        return new ServicoTransporteBuilder(servicoTransporte);
    }
    
    public ServicoTransporte build() {
        return this.servicoTransporte;
    }

    public ServicoTransporteBuilder comId(Long id) {
        this.servicoTransporte.setId(id);
        return this;
    }

    public ServicoTransporteBuilder comServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoTransporte.setServicoProtecao(servicoProtecao);
        return this;
    }

    public ServicoTransporteBuilder comEmpresa(Servico empresaServico) {
        this.servicoTransporte.setEmpresaServico(empresaServico);
        return this;
    }
    
    public ServicoTransporteBuilder comDataHora(Date dataHora) {
        this.servicoTransporte.setDataServico(dataHora);
        return this;
    }    
    
    public ServicoTransporteBuilder comOrigem(String origem) {
        this.servicoTransporte.setOrigem(origem);
        return this;
    } 

    public ServicoTransporteBuilder comDestino(String destino) {
        this.servicoTransporte.setDestino(destino);
        return this;
    }
    
    public ServicoTransporteBuilder comAutorizacao(String autorizacao) {
        this.servicoTransporte.setAutorizacao(autorizacao);
        return this;
    }     
    
    public ServicoTransporteBuilder comVoo(String voo) {
        this.servicoTransporte.setVoo(voo);
        return this;
    }         

    public ServicoTransporteBuilder comTipoTransporte(TipoTransporte tipoTransporte) {
        this.servicoTransporte.setTipoTransporte(tipoTransporte);
        return this;
    }        
    
    public ServicoTransporteBuilder comPassageiro(Passageiro passageiro) {
        this.servicoTransporte.adicionarPassageiro(passageiro);
        return this;
    }

    
}
