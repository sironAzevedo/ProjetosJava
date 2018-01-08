package br.com.petrobras.sistam.desktop.agenciamento.taxa;

import br.com.petrobras.fcorp.common.support.CollectionUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalheTaxaDialogModel extends BasePresentationModel<SistamService> {
    private Taxa taxa;
    private List<TipoTaxa> listaTipoTaxa;
    private List<ResponsavelCustoEntity> listaResponsavelCusto;
    private boolean continuarInserindo = true;
    private boolean taxaAdicionada = false;

    public DetalheTaxaDialogModel(Taxa taxa) {
        this.taxa = taxa; 
        carregaListaTipoTaxa();
        
        listaResponsavelCusto = getService().buscarTodosResponsavelCusto();
        listaResponsavelCusto.add(0, null);
    }

    public Taxa getTaxa() {
        return taxa;
    }

    public void setTaxa(Taxa taxa) {
        this.taxa = taxa;
        firePropertyChange("taxa", null, null);
    }

    public List<TipoTaxa> getListaTipoTaxa() {
        return listaTipoTaxa;
    }

    public List<ResponsavelCustoEntity> getListaResponsavelCusto() {
        return listaResponsavelCusto;
    }

    public boolean isContinuarInserindo() {
        return continuarInserindo;
    }

    public void setContinuarInserindo(boolean continuarInserindo) {
        this.continuarInserindo = continuarInserindo;
        firePropertyChange("continuarInserindo", null, null);
    }
    
    public boolean taxaAdicionada(){
        return taxaAdicionada;
    }
    
    public void salvar(){

        Taxa nova = getService().salvarTaxa(taxa);
        
        if (taxa.getId() == null){
            taxaAdicionada = true;
        }
        taxa = nova;
    }
    
    public void carregaListaTipoTaxa(){
        listaTipoTaxa = new ArrayList<TipoTaxa>(Arrays.asList(TipoTaxa.values()));
        CollectionUtils.sort(listaTipoTaxa, "ordenar");
        listaTipoTaxa.add(0, null); 
    }
    
    public void prepararNovaTaxa(){
        Taxa novaTaxa = new Taxa();
        novaTaxa.setAgenciamento(taxa.getAgenciamento());
        novaTaxa.setResponsavelCusto(new ResponsavelCustoEntity());
        
        setTaxa(novaTaxa);
    }
    
}
