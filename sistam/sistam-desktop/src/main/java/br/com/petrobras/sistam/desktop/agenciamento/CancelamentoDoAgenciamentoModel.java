package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CancelamentoDoAgenciamentoModel extends BasePresentationModel<SistamService> {
    private CancelamentoAgenciamento cancelamento;
    private List<MotivoCancelamento> listaMotivos;
    private boolean cancelou = false;

    public CancelamentoDoAgenciamentoModel(CancelamentoAgenciamento cancelamento) {
        this.cancelamento = cancelamento;
        listaMotivos = new ArrayList<MotivoCancelamento>(Arrays.asList(MotivoCancelamento.values()));
        listaMotivos.add(0, null);  
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public CancelamentoAgenciamento getCancelamento() {
        return cancelamento;
    }

    public List<MotivoCancelamento> getListaMotivos() {
        return listaMotivos;
    }

    public boolean cancelou() {
        return cancelou;
    }

   
    //</editor-fold>
    
    public void confirmar() {
        getService().cancelarAgenciamento(cancelamento);
        cancelou = true;
    }
    
}
