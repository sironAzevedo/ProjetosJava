package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import java.util.Date;

public class DetalheServicoProtecaoMedicoOdontologicoModel extends BasePresentationModel<SistamService> {
    private ServicoMedicoOdontologico servicoMedicoOdontologico;
    private boolean gravado;
    
    public DetalheServicoProtecaoMedicoOdontologicoModel(ServicoMedicoOdontologico servicoMedicoOdontologico) {
        if (servicoMedicoOdontologico.getId() == null){
            servicoMedicoOdontologico.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.MEDICO_ODONTOLOGICO);
            servicoMedicoOdontologico.getServicoProtecao().setDataExecucao(new Date());
        }
        setServicoMedicoOdontologico(servicoMedicoOdontologico);
    }

    public ServicoMedicoOdontologico getServicoMedicoOdontologico() {
        return servicoMedicoOdontologico;
    }

    public void setServicoMedicoOdontologico(ServicoMedicoOdontologico servicoMedicoOdontologico) {
        this.servicoMedicoOdontologico = servicoMedicoOdontologico;
        firePropertyChange("servicoMedicoOdontologico", null, null);
    }

    public boolean isGravado() {
        return gravado;
    }

    public void setGravado(boolean gravado) {
        this.gravado = gravado;
        firePropertyChange("gravado", null, null);
    }
    
    public String getServicoExecutado() {
        if (servicoMedicoOdontologico == null || servicoMedicoOdontologico.getServicoProtecao() == null || servicoMedicoOdontologico.getServicoProtecao().getTipoAtendimentoServico() == null) {
            return null;
        }
        
        return servicoMedicoOdontologico.getServicoProtecao().getTipoAtendimentoServico().getPorExtenso();
    }
    
    public void salvar(){
        servicoMedicoOdontologico = (ServicoMedicoOdontologico) getService().salvarServicoExecutado(servicoMedicoOdontologico);
        setGravado(true);
    }
    
}
