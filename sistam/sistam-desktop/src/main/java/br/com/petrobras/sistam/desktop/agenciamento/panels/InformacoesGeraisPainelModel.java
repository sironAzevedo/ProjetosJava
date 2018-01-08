package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.AreaNavegacao;
import br.com.petrobras.sistam.common.enums.TipoArmador;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InformacoesGeraisPainelModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<AreaNavegacao> areasNavegacao;
    private List<TipoArmador> tipoArmador;
    private List<TipoContrato> tipoContrato;
    private List<Acompanhamento> acompanhamentos;
    private List<TipoFrota> tipoFrota;
    private Acompanhamento acompanhamentoSelecionado;
    private boolean habilitar = true;

    public InformacoesGeraisPainelModel() {         
        areasNavegacao = new ArrayList<AreaNavegacao> (Arrays.asList(AreaNavegacao.values()));
        areasNavegacao.add(0, null);
        
        tipoContrato = new ArrayList<TipoContrato> (Arrays.asList(TipoContrato.values()));
        tipoContrato.add(0, null);
        
        tipoArmador = new ArrayList<TipoArmador>(Arrays.asList(TipoArmador.values()));
        tipoArmador.add(0, null);
        
        tipoFrota = new ArrayList<TipoFrota>(Arrays.asList(TipoFrota.values()));
        tipoFrota.add(0, null); 
       
    }
    
    public void carregarAcompanhamentos() {
        setAcompanhamentos(getService().buscarAcompanhamentos(agenciamento));
    } 
    
    //<editor-fold defaultstate="collapsed" desc="Getter e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        agenciamento.addPropertyChangeListener(this);
        firePropertyChange("agenciamento", null, null);
        habilitarComponentes();
    }
    
    public List<AreaNavegacao> getAreasNavegacao() {
        return areasNavegacao;
    }

    public List<TipoArmador> getTipoArmador() {
        return tipoArmador;
    }
    
    public List<Acompanhamento> getAcompanhamentos() {
        return acompanhamentos;
    }

    public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
        firePropertyChange("acompanhamentos", null, null);
    }

    public Acompanhamento getAcompanhamentoSelecionado() {
        return acompanhamentoSelecionado;
    }

    public void setAcompanhamentoSelecionado(Acompanhamento acompanhamentoSelecionado) {
        this.acompanhamentoSelecionado = acompanhamentoSelecionado;
        firePropertyChange("acompanhamentoSelecionado", null, null);
    }
    
    public List<TipoContrato> getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(List<TipoContrato> tipoContrato) {
        this.tipoContrato = tipoContrato;
        firePropertyChange("tipoContrato", null, null);
    }    
    
     public List<TipoFrota> getTipoFrota() {
        return tipoFrota;
    }

    public void setTipoFrota(List<TipoFrota> tipoFrota) {
        this.tipoFrota = tipoFrota;
         firePropertyChange("tipoFrota", null, null);
    }
    
    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
        firePropertyChange("habilitar", null, null);
    } 
    
    //</editor-fold>

    public void habilitarComponentes() {
        if (agenciamento.getAgenciamentoPlataforma()) {
            agenciamento.setAgenciamentoCarga(false);
            agenciamento.setAgenciamentoProtecao(false);
            setHabilitar(false);
        } else {
            setHabilitar(true);
        }
    } 
   

   @Override
    public void propertyChange(PropertyChangeEvent evt) {
         if(evt.getPropertyName().equals(Agenciamento.PROP_AGENCIAMENTO_PLATAFORMA)){
            habilitarComponentes(); 
        }
    }  
}
