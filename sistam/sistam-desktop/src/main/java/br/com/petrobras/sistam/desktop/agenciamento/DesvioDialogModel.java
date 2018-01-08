package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.MotivoDesvio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DesvioDialogModel extends BasePresentationModel<SistamService> {
    private Desvio desvio;
    private Porto novoPortoDestino;
    private String  novoDestinoIntermediario;
    private List<MotivoDesvio> listaMotivos;
    private boolean confirmado = false;

    public DesvioDialogModel(Desvio desvio) {
        this.desvio = desvio;
        listaMotivos = new ArrayList<MotivoDesvio>(Arrays.asList(MotivoDesvio.values()));
        listaMotivos.add(0, null);
        
        if (desvio.getId() != null){
            novoPortoDestino = desvio.getAgenciamento().getPortoDestino();
            novoDestinoIntermediario = desvio.getAgenciamento().getDestinoIntermediario();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Desvio getDesvio() {
        return desvio;
    }

    public Porto getNovoPortoDestino() {
        return novoPortoDestino;
    }

    public void setNovoPortoDestino(Porto novoPortoDestino) {
        this.novoPortoDestino = novoPortoDestino;
        firePropertyChange("novoPortoDestino", null, null);
    }

    public String getNovoDestinoIntermediario() {
        return novoDestinoIntermediario;
    }

    public void setNovoDestinoIntermediario(String novoDestinoIntermediario) {
        this.novoDestinoIntermediario = novoDestinoIntermediario;
    }

    
    public List<MotivoDesvio> getListaMotivos() {
        return listaMotivos;
    }

    //</editor-fold>
    
    public void confirmar(){
        desvio.setData(new Date());
        getService().salvarDesvio(desvio, novoPortoDestino,novoDestinoIntermediario);
        confirmado = true;
    }
    
    public boolean confirmou(){
        return confirmado;
    }
    
}
