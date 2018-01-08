package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class DestinatariosModel extends BasePresentationModel<SistamService> {

    private Agencia agencia;
    private List<Destinatario> listaDestinatario = new SerializableObservableList<Destinatario>();
    private Destinatario destinatarioSelecionado;

    public DestinatariosModel(Agencia agencia) {
        this.agencia = agencia;
        
        carregarListaDeDestinatario();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agencia getAgencia() {
        return agencia;
    }
    
    public List<Destinatario> getListaDestinatario() {
        return listaDestinatario;
    }
    
    public Destinatario getDestinatarioSelecionado() {
        return destinatarioSelecionado;
    }
    
    public void setDestinatarioSelecionado(Destinatario destinatarioSelecionado) {
        Object old = this.destinatarioSelecionado;
        this.destinatarioSelecionado = destinatarioSelecionado;
        firePropertyChange("destinatarioSelecionado", old, this.destinatarioSelecionado);
    }
    
    //</editor-fold>
    
    public Destinatario obterNovoDestinatario() {
        Destinatario novo = new Destinatario();
        novo.setAgencia(agencia);
        return novo;
    }

    public Destinatario obterDestinatarioParaEdicao() {
        return (Destinatario) destinatarioSelecionado.clone();
    }

    private void carregarListaDeDestinatario() {
        listaDestinatario.addAll(getService().buscarDestinatariosDaAgencia(agencia));
    }

    public void adicionarDestinatario(Destinatario destinatario) {
        listaDestinatario.add(destinatario);
    }

    public void atualizarDestinatario(Destinatario destinatario) {
        listaDestinatario.remove(destinatarioSelecionado);
        listaDestinatario.add(destinatario);
    }
    
    public void excluirDestinatario(){
        getService().excluirDestinatario(destinatarioSelecionado);
        listaDestinatario.remove(destinatarioSelecionado);
    }
}
