package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class TaxasPainelModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<Taxa> listaDeTaxas = new SerializableObservableList<Taxa>();
    private Taxa taxaSelecionada;

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        carregarListaDeTaxas();
    }

    public List<Taxa> getListaDeTaxas() {
        return listaDeTaxas;
    }

    public Taxa getTaxaSelecionada() {
        return taxaSelecionada;
    }

    public void setTaxaSelecionada(Taxa taxaSelecionada) {
        this.taxaSelecionada = taxaSelecionada;
        firePropertyChange("taxaSelecionada", null, null);
    }
    
    public Taxa obterNovaTaxa(){
        ResponsavelCustoEntity responsavelCusto = new ResponsavelCustoEntity();
        responsavelCusto.setId(1L);
        Taxa taxa = new Taxa();
        taxa.setAgenciamento(agenciamento);
        taxa.setResponsavelCusto(responsavelCusto);
        
        return taxa;
    }
    
    public Taxa obterTaxaParaEdicaoVisualizacao(){
        Taxa clone = (Taxa) taxaSelecionada.clone();
        
        //atualiza com o mesmo agenciamento, mas com as propriedades carregadas (evitar problemas de lazy).
        clone.setAgenciamento(agenciamento);
        
        return clone;
    }
    
    public void adicionarTaxa(Taxa taxa){
        agenciamento.adicionarTaxa(taxa);
        carregarListaDeTaxas();
    }
    
    public void atualizarTaxa(Taxa taxa){
        agenciamento.removerTaxa(taxaSelecionada);
        agenciamento.adicionarTaxa(taxa);
        carregarListaDeTaxas();
    }
    
    public void excluirTaxa(){
        //atualiza a taxa com o agenciamento com as propriedades carregadas (evitar problemas de lazy).
        taxaSelecionada.setAgenciamento(agenciamento);
        
        getService().excluirTaxa(taxaSelecionada);
        agenciamento.removerTaxa(taxaSelecionada);
        listaDeTaxas.remove(taxaSelecionada);
    }
    
    public void carregarListaDeTaxas(){
        listaDeTaxas.clear();
        
        if (agenciamento != null){
            listaDeTaxas.addAll(agenciamento.getTaxas());
        }
    }
    
}
