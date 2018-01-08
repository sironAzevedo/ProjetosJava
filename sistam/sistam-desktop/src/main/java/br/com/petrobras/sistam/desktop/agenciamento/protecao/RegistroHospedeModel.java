package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;

public class RegistroHospedeModel extends BasePresentationModel<SistamService> {

    private boolean continuarInserindo = true;
    private boolean edicao = false;
    private Hospede hospede;

    public RegistroHospedeModel(Hospede hospede) {
        this.hospede = hospede;
        if (hospede.getNome()!= null) {
            setContinuarInserindo(false);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public boolean isContinuarInserindo() {
        return continuarInserindo;
    }

    public void setContinuarInserindo(boolean continuarInserindo) {
        this.continuarInserindo = continuarInserindo;
        firePropertyChange("continuarInserindo", null, null);
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
        firePropertyChange("edicao", null, null);
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
        firePropertyChange("hospede", null, null);
    }

    //</editor-fold>
    public void salvar() {
        Hospede hospedeAtualizado = getService().salvarHospede(hospede);
        setHospede(hospedeAtualizado);
    }

    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotEmpty(hospede.getNome()).orRegister(TipoExcecao.HOSPEDE, ConstantesI18N.HOSPEDE_NOME_OBRIGATORIO);
        pm.assertThat((hospede.getDocumento() != null && !hospede.getDocumento().isEmpty()) || (hospede.getCpf() != null && !hospede.getCpf().isEmpty())).orRegister(TipoExcecao.HOSPEDE, ConstantesI18N.HOSPEDE_IDENTIFICADOR_OBRIGATORIA);
        pm.verifyAll();
    }

    public void prepararNovoHospede() {
        Hospede novo = new Hospede();
        novo.setAtivo(Boolean.TRUE);
        novo.setServicoProtecao(hospede.getServicoProtecao());
        novo.addPropertyChangeListener(this);

        setHospede(novo);
    }
}