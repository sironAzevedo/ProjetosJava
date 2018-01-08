package br.com.petrobras.sistam.desktop.porto;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.rules.RegrasPortoComplemento;

/**
 *
 */
public class DetalhesComplementoModel extends BasePresentationModel<SistamService> {

    private PortoComplemento portoComplemento;
    private boolean valido;

    public DetalhesComplementoModel(Porto porto) {
        portoComplemento = new PortoComplemento();
        portoComplemento.setPorto(porto);
    }

    public PortoComplemento getPortoComplemento() {
        return portoComplemento;
    }

    public void setPortoComplemento(PortoComplemento portoComplemento) {
        this.portoComplemento = portoComplemento;
        firePropertyChange("portoComplemento", null, null);
    }

    public boolean isValido() {
        return valido;
    }

    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();
        RegrasPortoComplemento.validarCamposObrigatorios(pm, portoComplemento);
        pm.verifyAll();
        valido = true;
    }
}
