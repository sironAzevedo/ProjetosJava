package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;

public class FiltroEnvioSolicitacaoTransporteBuilder {

    private FiltroEnvioSolicitacaoTransporte filtro;
    
    private FiltroEnvioSolicitacaoTransporteBuilder(FiltroEnvioSolicitacaoTransporte filtro) {
        this.filtro = filtro;
    }
    
    public static FiltroEnvioSolicitacaoTransporteBuilder novoFiltro() {
        return new FiltroEnvioSolicitacaoTransporteBuilder(new FiltroEnvioSolicitacaoTransporte());
    }
    
    public static FiltroEnvioSolicitacaoTransporteBuilder novoFiltro(FiltroEnvioSolicitacaoTransporte empresaTransporte) {
        return new FiltroEnvioSolicitacaoTransporteBuilder(empresaTransporte);
    }
    
    public FiltroEnvioSolicitacaoTransporte build() {
        return this.filtro;
    }
    
    public FiltroEnvioSolicitacaoTransporteBuilder enviaParaAgenciaMaritima(boolean possui) {
        this.filtro.setAgenciaMaritima(possui);
        return this;
    }
    
    public FiltroEnvioSolicitacaoTransporteBuilder enviaParaEmpresaTransporte(boolean possui) {
        this.filtro.setEmpresaTransporte(possui);
        return this;
    }

    
     public FiltroEnvioSolicitacaoTransporteBuilder comTipoCentroCusto(ResponsavelCusto responsavelCusto) {
        this.filtro.setResponsavelCusto(responsavelCusto);
        return this;
    }
     
     public FiltroEnvioSolicitacaoTransporteBuilder comServicoTransporte(ServicoTransporte servicoTransporte) {
        this.filtro.setServicoTransporte(servicoTransporte);
        return this;
    }

}
