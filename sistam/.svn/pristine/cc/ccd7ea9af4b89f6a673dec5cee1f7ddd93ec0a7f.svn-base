package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoRetiradaResiduoLixo;

public class FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder {

    private FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro;
    
    private FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro) {
        this.filtro = filtro;
    }
    
    public static FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder novoFiltro() {
        return new FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder(new FiltroEnvioSolicitacaoRetiradaResiduoLixo());
    }
    
    public static FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder novoFiltro(FiltroEnvioSolicitacaoRetiradaResiduoLixo retiradaResiduoLixo) {
        return new FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder(retiradaResiduoLixo);
    }
    
    public FiltroEnvioSolicitacaoRetiradaResiduoLixo build() {
        return this.filtro;
    }
    
    public FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder enviaParaAgenciaMaritima(boolean possui) {
        this.filtro.setAgenciaMaritima(possui);
        return this;
    }
    
    public FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder enviaParaEmpresaTransporte(boolean possui) {
        this.filtro.setEmpresaResponsavel(possui);
        return this;
    } 
    
     public FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder comServicoRetiradaResiduoLixo(ServicoRetiradaResiduoLixo retiradaResiduoLixo) {
        this.filtro.setServicoRetiradaResiduoLixo(retiradaResiduoLixo);
        return this;
    }

}
