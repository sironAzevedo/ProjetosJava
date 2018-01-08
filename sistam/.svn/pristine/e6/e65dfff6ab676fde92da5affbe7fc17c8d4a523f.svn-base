package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;

public class RegistroPassageiroModel extends BasePresentationModel<SistamService> {
    private boolean continuarInserindo = true;
    private boolean edicao = false;
    private Passageiro passageiro;
    

    public RegistroPassageiroModel(Passageiro passageiro) {
        this.passageiro = passageiro;
        if (passageiro.getNome()!= null) {
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

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
        firePropertyChange("passageiro", null, null);
    }
   
    //</editor-fold>
    
    public void salvar(){
        Passageiro passageiroAtualizado = getService().salvarPassageiro(passageiro);
        setPassageiro(passageiroAtualizado);
    }
    
    public void validar(){
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotEmpty(passageiro.getNome()).orRegister(TipoExcecao.PASSAGEIRO, ConstantesI18N.PASSAGEIRO_NOME_OBRIGATORIO);
        pm.assertThat((passageiro.getDocumento() != null && !passageiro.getDocumento().isEmpty()) || (passageiro.getCpf() != null && !passageiro.getCpf().isEmpty())).orRegister(TipoExcecao.PASSAGEIRO, ConstantesI18N.PASSAGEIRO_IDENTIFICADOR_OBRIGATORIA);

        pm.verifyAll();
    }
    
    public void prepararNovoPassageiro() {
        Passageiro novo = new Passageiro();
        novo.setAtivo(Boolean.TRUE);
        novo.setServicoProtecao(passageiro.getServicoProtecao());
        novo.addPropertyChangeListener(this);
        
        setPassageiro(novo);
    }
   
}