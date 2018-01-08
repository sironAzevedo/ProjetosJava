package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AgenciamentoViagemModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private String helpText;
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getHelpText() {
        return helpText;
    }
    
    public void setHelpText(String helpText) {
        this.helpText = helpText;
        firePropertyChange("helpText", null, this.helpText);
    }
    
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
        if (agenciamento != null ){
            refreshCampos();
        }
    }
    
    public List<TipoSimNao> getListaSimNao() {
        return Collections.unmodifiableList(new ArrayList<TipoSimNao> (Arrays.asList(TipoSimNao.values())));
    }
    
    public TipoSimNao getObito() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isObito());
    }
    
    public void setObito(TipoSimNao obito) {
        this.agenciamento.getAgenciementoViagem().setObito(obito.getId());
        firePropertyChange("obito", null, null);
        if (obito.equals(TipoSimNao.NAO)){
            setSepultamento(obito);
        }
    }
    
    public TipoSimNao getSepultamento() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isSepultamento());
    }
    
    public void setSepultamento(TipoSimNao sepultamento) {
        this.agenciamento.getAgenciementoViagem().setSepultamento(sepultamento.getId());
        firePropertyChange("sepultamento", null, null);
    }
    
    public TipoSimNao getDoenca() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isDoenca());
    }
    
    public void setDoenca(TipoSimNao doenca) {
        this.agenciamento.getAgenciementoViagem().setDoenca(doenca.getId());
        firePropertyChange("doenca", null, null);
        
        if (doenca.equals(TipoSimNao.NAO)){
            setFebreHemorragia(doenca);
            setDiarreia(doenca);
            setIctericia(doenca);
            setDisfuncoesNeurologicas(doenca);
            setDificuldadeRespiratoria(doenca);
        }
        firePropertyChange("habilitarCamposDoenca", null, isHabilitarCamposDoenca());
    }
    
    public TipoSimNao getFebreHemorragia() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isFebreHemorragia());
    }
    
    public void setFebreHemorragia(TipoSimNao febreHemorragia) {
        this.agenciamento.getAgenciementoViagem().setFebreHemorragia(febreHemorragia.getId());
        firePropertyChange("febreHemorragia", null, null);
    }
    
    public TipoSimNao getIctericia() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isIctericia());
    }
    
    public void setIctericia(TipoSimNao ictericia) {
        this.agenciamento.getAgenciementoViagem().setIctericia(ictericia.getId());
        firePropertyChange("ictericia", null, null);
    }
    
    public TipoSimNao getDiarreia() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isDiarreia());
    }
    
    public void setDiarreia(TipoSimNao diarreia) {
        this.agenciamento.getAgenciementoViagem().setDiarreia(diarreia.getId());
        firePropertyChange("diarreia", null, null);
    }
    
    public TipoSimNao getDisfuncoesNeurologicas() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isDisfuncoesNeurologicas());
    }
    
    public void setDisfuncoesNeurologicas(TipoSimNao disfuncoesNeurologicas) {
        this.agenciamento.getAgenciementoViagem().setDisfuncoesNeurologicas(disfuncoesNeurologicas.getId());
        firePropertyChange("disfuncoesNeurologicas", null, null);
    }
    
    public TipoSimNao getDificuldadeRespiratoria() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isDificuldadeRespiratoria());
    }
    
    public void setDificuldadeRespiratoria(TipoSimNao dificuldadeRespiratoria) {
        this.agenciamento.getAgenciementoViagem().setDificuldadeRespiratoria(dificuldadeRespiratoria.getId());
        firePropertyChange("dificuldadeRespiratoria", null, null);
    }
    
    public TipoSimNao getAcidente() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isAcidente());
    }
    
    public void setAcidente(TipoSimNao acidente) {
        this.agenciamento.getAgenciementoViagem().setAcidente(acidente.getId());
        firePropertyChange("acidente", null, null);
        
        if (acidente.equals(TipoSimNao.NAO)){
            agenciamento.getAgenciementoViagem().setNomeAcidente("");
            firePropertyChange("agenciamento", null, this.agenciamento);
        }
    }
    
    public TipoSimNao getRoedores() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isRoedores());
    }
    
    public void setRoedores(TipoSimNao roedores) {
        this.agenciamento.getAgenciementoViagem().setRoedores(roedores.getId());
        firePropertyChange("roedores", null, null);
        
        if (roedores.equals(TipoSimNao.NAO)){
            agenciamento.getAgenciementoViagem().setCompartimentoRoedores("");
            firePropertyChange("agenciamento", null, this.agenciamento);
        }
    }
    
    public TipoSimNao getConsumoMedicamento() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoViagem().isConsumoMedicamento());
    }
    
    public void setConsumoMedicamento(TipoSimNao consumoMedicamento) {
        this.agenciamento.getAgenciementoViagem().setConsumoMedicamento(consumoMedicamento.getId());
        firePropertyChange("consumoMedicamento", null, null);
        
        if (consumoMedicamento.equals(TipoSimNao.NAO)){
            agenciamento.getAgenciementoViagem().setNomeMedicamento("");
            firePropertyChange("agenciamento", null, this.agenciamento);
        }
    }
    
    //</editor-fold>
    
    /*
     * Os campos do tipo da enumeração TipoSimNao devem ser atualizados aqui pois não são afetados
     * pelo firePropertyChange da propriedade agenciamento
     */
    public void refreshCampos() {
        firePropertyChange("obito", null, null);
        firePropertyChange("sepultamento", null, null);
        firePropertyChange("doenca", null, null);
        firePropertyChange("febreHemorragia", null, null);
        firePropertyChange("ictericia", null, null);
        firePropertyChange("diarreia", null, null);
        firePropertyChange("disfuncoesNeurologicas", null, null);
        firePropertyChange("dificuldadeRespiratoria", null, null);
        firePropertyChange("acidente", null, null);
        firePropertyChange("roedores", null, null);
        firePropertyChange("consumoMedicamento", null, null);
    }
    
    public boolean isHabilitarCamposDoenca(){
        return (TipoSimNao.SIM.equals(getDoenca()));
    }
     
}
