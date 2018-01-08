package br.com.petrobras.sistam.desktop.agenciamento.acompanhamento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Acompanhamento;

public class DetalheAcompanhamentoModel extends BasePresentationModel<SistamService> {
    private Acompanhamento acompanhamento;
    
    public DetalheAcompanhamentoModel(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    
    public void salvar(){
        acompanhamento = getService().salvarAcompanhamento(acompanhamento);
    }
    
    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
        firePropertyChange("acompanhamento", null, null);
    }
    
    

}
