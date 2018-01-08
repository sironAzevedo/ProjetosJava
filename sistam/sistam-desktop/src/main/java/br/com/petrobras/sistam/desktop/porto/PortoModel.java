package br.com.petrobras.sistam.desktop.porto;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class PortoModel extends BasePresentationModel<SistamService> {
    private List<Agencia> listaAgencia;
    private Agencia agenciaSelecionada;
    private List<AgenciaPorto> listaPorto = new SerializableObservableList<AgenciaPorto>();
    private AgenciaPorto portoSelecionado;
    private List<Porto> portosNacionais = new SerializableObservableList<Porto>();
    private Porto portoNacionalSelecionado;
    
    
    public PortoModel(){
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        listaAgencia = contexto.getAgenciasAutorizadas();
        listaAgencia.add(0, null);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Agencia> getListaAgencia(){
        return this.listaAgencia;
    }
    
    public List<AgenciaPorto> getListaPorto() {
        return listaPorto;
    }
    
    public AgenciaPorto getPortoSelecionado() {
        return portoSelecionado;
    }
    
    public void setPortoSelecionado(AgenciaPorto portoSelecionado) {
        Object old = this.portoSelecionado;
        this.portoSelecionado = portoSelecionado;
        firePropertyChange("portoSelecionado", old, this.portoSelecionado);
    }
    
    public Agencia getAgenciaSelecionada() {
        return agenciaSelecionada;
    }
    
    public void setAgenciaSelecionada(Agencia agenciaSelecionada) {
        this.agenciaSelecionada = agenciaSelecionada;
        firePropertyChange("agenciaSelecionada", null, this.agenciaSelecionada);
    }

    public List<Porto> getPortosNacionais() {
        return portosNacionais;
    }

    public Porto getPortoNacionalSelecionado() {
        return portoNacionalSelecionado;
    }

    public void setPortoNacionalSelecionado(Porto portoNacionalSelecionado) {
        this.portoNacionalSelecionado = portoNacionalSelecionado;
        firePropertyChange("portoNacionalSelecionado", null, null);
    }

    //</editor-fold>
    
    public void pesquisar(){
        listaPorto.clear();
        listaPorto.addAll(getService().buscarAgenciasPortosPorAgencia(agenciaSelecionada));
    }
    
    public AgenciaPorto obterNovoPorto(){
        AgenciaPorto porto = new AgenciaPorto();
        return porto;
    }
    
    public AgenciaPorto obterPortoParaEdicao(){
        return (AgenciaPorto)portoSelecionado.clone();
    }
    
    public PortoComplemento obterNovoPortoComplemento(){
        return new PortoComplemento();
    }
    
    public void carregarListaPortosNacionais(){
        portosNacionais.clear();
        portosNacionais = getService().buscarPortosNacionaisComComplementos();
        firePropertyChange("portosNacionais", null, null);
    }
}