package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import java.io.Serializable;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class FiltroEnvioSolicitacaoTransporte extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private boolean agenciaMaritima;
    private boolean empresaTransporte;
    private ServicoTransporte servicoTransporte;
    private ResponsavelCusto responsavelCusto;

    public boolean isAgenciaMaritima() {
        return agenciaMaritima;
    }

    public void setAgenciaMaritima(boolean agenciaMaritima) {
        this.agenciaMaritima = agenciaMaritima;
    }

    public boolean isEmpresaTransporte() {
        return empresaTransporte;
    }

    public void setEmpresaTransporte(boolean empresaTransporte) {
        this.empresaTransporte = empresaTransporte;
    }

    public ServicoTransporte getServicoTransporte() {
        return servicoTransporte;
    }

    public void setServicoTransporte(ServicoTransporte servicoTransporte) {
        this.servicoTransporte = servicoTransporte;
    }

    public ResponsavelCusto getResponsavelCusto() {
        return responsavelCusto;
    }

    public void setResponsavelCusto(ResponsavelCusto responsavelCusto) {
        this.responsavelCusto = responsavelCusto;
    }
}
