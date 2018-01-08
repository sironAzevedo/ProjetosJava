package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.enums.TipoCentroCusto;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;

public class FiltroEnvioSolicitacaoHospedagemBuilder {

    private FiltroEnvioSolicitacaoHospedagem filtro;
    
    private FiltroEnvioSolicitacaoHospedagemBuilder(FiltroEnvioSolicitacaoHospedagem filtro) {
        this.filtro = filtro;
    }
    
    public static FiltroEnvioSolicitacaoHospedagemBuilder novoFiltro() {
        return new FiltroEnvioSolicitacaoHospedagemBuilder(new FiltroEnvioSolicitacaoHospedagem());
    }
    
    public static FiltroEnvioSolicitacaoHospedagemBuilder novoFiltro(FiltroEnvioSolicitacaoHospedagem empresa) {
        return new FiltroEnvioSolicitacaoHospedagemBuilder(empresa);
    }
    
    public FiltroEnvioSolicitacaoHospedagem build() {
        return this.filtro;
    }
    
    public FiltroEnvioSolicitacaoHospedagemBuilder enviaParaAgenciaMaritima(boolean possui) {
        this.filtro.setAgenciaMaritima(possui);
        return this;
    }
    
    public FiltroEnvioSolicitacaoHospedagemBuilder enviaParaHotel(boolean possui) {
        this.filtro.setHotel(possui);
        return this;
    }
    
    public FiltroEnvioSolicitacaoHospedagemBuilder daAgenciaViagem(Servico empresa) {
        this.filtro.setAgenciaViagem(empresa);
        return this;
    }

    public FiltroEnvioSolicitacaoHospedagemBuilder comLotacao(String lotacao) {
        this.filtro.setLotacao(lotacao);
        return this;
    }
    
     public FiltroEnvioSolicitacaoHospedagemBuilder comTipoCentroCusto(TipoCentroCusto centroCusto) {
        this.filtro.setTipoCentroCusto(centroCusto);
        return this;
    }
     
     public FiltroEnvioSolicitacaoHospedagemBuilder comServicoHospedagem(ServicoHospedagem servicoHospedagem) {
        this.filtro.setServicoHospedagem(servicoHospedagem);
        return this;
    }

}
