package br.com.petrobras.sistam.desktop.agenciamento.manobras;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.Date;

public class InformacoesDoAgenciamentoDialogModel extends BasePresentationModel<SistamService> {
    private Porto portoDestino;
    private Date eta;
    private boolean confirmado = false;

    public Porto getPortoDestino() {
        return portoDestino;
    }

    public void setPortoDestino(Porto portoDestino) {
        this.portoDestino = portoDestino;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public boolean foiConfirmado() {
        return confirmado;
    }
    
    public void confirmar(){
        AssertUtils.assertNotNull(portoDestino, ConstantesI18N.MANOBRA_INFORME_PORTO_DESTINO);
        AssertUtils.assertNotNull(portoDestino, ConstantesI18N.MANOBRA_INFORME_ETA);
        confirmado = true; 
    }

}
