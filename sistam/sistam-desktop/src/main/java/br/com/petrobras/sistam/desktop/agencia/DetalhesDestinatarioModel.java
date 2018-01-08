package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Destinatario;

public class DetalhesDestinatarioModel extends BasePresentationModel<SistamService> {
    private Destinatario destinatario;
    private boolean salvo = false;

    public DetalhesDestinatarioModel(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }
    
    public boolean estaSalvo(){
        return salvo;
    }
    
    public void salvar(){
        destinatario = getService().salvarDestinatario(destinatario);
        salvo = true;
    }
    
    

}
