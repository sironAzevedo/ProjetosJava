package br.com.petrobras.sistam.desktop.porto;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;

public class DetalhesInformacoesPortoModel extends BasePresentationModel<SistamService> {

    private Porto portoSelecionado;
    private PortoComplemento complementoSelecionado;

    public void salvarComplemento(PortoComplemento complemento){
        getService().salvarPortoComplemento(complemento);
        recarregarPorto(complemento.getPorto());
    }
    
    public void excluirComplementoSelecionado(){
        getService().excluirPortoComplemento(complementoSelecionado);
        recarregarPorto(complementoSelecionado.getPorto());
        complementoSelecionado = null;
    }

    public Porto getPortoSelecionado() {
        return portoSelecionado;
    }

    public void setPortoSelecionado(Porto portoSelecionado) {
        this.portoSelecionado = portoSelecionado;
        if(portoSelecionado != null){
            this.portoSelecionado = getService().buscarPorto(portoSelecionado);
        }
        firePropertyChange("portoSelecionado", null, null);
    }

    public PortoComplemento obterComplementoSelecionadoParaEdicao(){
        return getService().buscarPortoComplemento(complementoSelecionado.getId());
    }
    
    public void recarregarPorto(Porto porto){
        setPortoSelecionado(null);
        setPortoSelecionado(porto);
    }
    
    public PortoComplemento getComplementoSelecionado() {
        return complementoSelecionado;
    }

    public void setComplementoSelecionado(PortoComplemento complementoSelecionado) {
        this.complementoSelecionado = complementoSelecionado;
        firePropertyChange("complementoSelecionado", null, null);
    }
    
}