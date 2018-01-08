package br.com.petrobras.sistam.desktop.empresa;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalhesServicoDialogModel extends BasePresentationModel<SistamService> {
    private Servico servico;
    private boolean salvo = false;
    private List<TipoServico> listaTipoServico;
    
    public DetalhesServicoDialogModel(Servico servico) {
        this.servico = servico;
        
        listaTipoServico = new ArrayList<TipoServico>(Arrays.asList(TipoServico.values()));
        listaTipoServico.add(0, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

    public Servico getServico() {
        return servico;
    }

    public boolean isSalvo() {
        return salvo;
    }

    public List<TipoServico> getListaTipoServico() {
        return listaTipoServico;
    }
    
    //</editor-fold>
    
    public void salvar(){
        if (servico.getEmpresa().getId() == null){
            getService().validarSalvarServicoDaEmpresa(servico);
        }else{
            servico = getService().salvarServicoDaEmpresa(servico);
        }
        salvo = true;
    }
    
    
}