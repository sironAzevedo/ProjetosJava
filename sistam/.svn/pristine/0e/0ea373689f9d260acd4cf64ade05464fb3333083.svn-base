package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import java.util.Date;

public class ServicoHospedagemBuilder {

    private ServicoHospedagem servicoHospedagem;

    private ServicoHospedagemBuilder(ServicoHospedagem servicoHospedagem) {
        this.servicoHospedagem = servicoHospedagem;
    }

    public static ServicoHospedagemBuilder novoServicoHospedagem() {
        return new ServicoHospedagemBuilder(new ServicoHospedagem());
    }

    public ServicoHospedagem build() {
        return this.servicoHospedagem;
    }
    
    public ServicoHospedagemBuilder comId(Long id) {
        this.servicoHospedagem.setId(id);
        return this;
    }

    public ServicoHospedagemBuilder comServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoHospedagem.setServicoProtecao(servicoProtecao);
        return this;
    }

    public ServicoHospedagemBuilder comHotel(Servico hotel) {
        this.servicoHospedagem.setEmpresaServico(hotel);
        return this;
    }

    public ServicoHospedagemBuilder comDataChegada(Date dataChegada) {
        this.servicoHospedagem.setDataChegada(dataChegada);
        return this;
    }

    public ServicoHospedagemBuilder comDataSaida(Date dataSaida) {
        this.servicoHospedagem.setDataSaida(dataSaida);
        return this;
    }
    
    public ServicoHospedagemBuilder comAutorizacao(String autorizacao) {
        this.servicoHospedagem.setAutorizacao(autorizacao);
        return this;
    }
    
    public ServicoHospedagemBuilder comHospede(Hospede hospede) {
        this.servicoHospedagem.adicionarHospede(hospede);
        return this;
    }
}
