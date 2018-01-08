package br.com.petrobras.sistam.desktop.pontoatracacao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class PontoAtracacaoFrameModel extends BasePresentationModel<SistamService> {
    private List<PontoAtracacao> listaDePontos = new SerializableObservableList<PontoAtracacao>();
    private List<Agencia> listaDeAgencias;
    private List<Porto> listaPortos;
    private Agencia agenciaSelecionada;
    private Porto portoSelecionado;
    private PontoAtracacao pontoAtracacaoSelecionado;

    public PontoAtracacaoFrameModel() {
        carregarListaDeAgencia();
        carregarListaPortos();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

    public List<PontoAtracacao> getListaDePontos() {
        return listaDePontos;
    }

    public List<Agencia> getListaDeAgencias() {
        return listaDeAgencias;
    }

    public Agencia getAgenciaSelecionada() {
        return agenciaSelecionada;
    }

    public void setAgenciaSelecionada(Agencia agenciaSelecionada) {
        this.agenciaSelecionada = agenciaSelecionada;
        
        carregarListaPortos();
    }

    public PontoAtracacao getPontoAtracacaoSelecionado() {
        return pontoAtracacaoSelecionado;
    }

    public void setPontoAtracacaoSelecionado(PontoAtracacao pontoAtracacaoSelecionado) {
        this.pontoAtracacaoSelecionado = pontoAtracacaoSelecionado;
        firePropertyChange("pontoAtracacaoSelecionado", null, null);
    }

    public List<Porto> getListaPortos() {
        return listaPortos;
    }

    public void setListaPortos(List<Porto> listaPortos) {
        this.listaPortos = listaPortos;
        firePropertyChange("listaPortos", null, null);
    }

    public Porto getPortoSelecionado() {
        return portoSelecionado;
    }

    public void setPortoSelecionado(Porto portoSelecionado) {
        this.portoSelecionado = portoSelecionado;
    }
    
    
    //</editor-fold>
    
    private void carregarListaDeAgencia(){
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        listaDeAgencias = contexto.getAgenciasAutorizadas();
        listaDeAgencias.add(0, null);
    }
    
    private void carregarListaPortos(){
        List<Porto> lista;
        if (agenciaSelecionada == null ){
            List<Agencia> agenciasAutorizadas = ((SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean())).getAgenciasAutorizadas();
            lista = getService().buscarPortosPorAgencia(agenciasAutorizadas.toArray(new Agencia[agenciasAutorizadas.size()]));
        }else{
            lista = getService().buscarPortosPorAgencia(agenciaSelecionada);
        }
        
        lista.add(0, null);
        setListaPortos(lista);
    }
    
    public void pesquisar(){
        listaDePontos.clear();
        listaDePontos.addAll(getService().buscarPontosAtracacaolPorPorto(portoSelecionado));
    }
    
    public PontoAtracacao obterNovoPontoAtracacao(){
        PontoAtracacao ponto = new PontoAtracacao();
        return ponto;
    }
    
    public PontoAtracacao obterPontoAtracacaoParaEdicao(){
        PontoAtracacao clone =(PontoAtracacao)pontoAtracacaoSelecionado.clone();
        return clone;
    }
    
}