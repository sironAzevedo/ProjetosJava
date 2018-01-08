package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

public class ManobrasModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private List<Manobra> listaManobrasRegistradas = new SerializableObservableList<Manobra>();
    private List<Manobra> listaManobrasSolicitadas = new SerializableObservableList<Manobra>();
    private List<ServicoManobra> listaServicoManobraRegistrada = new SerializableObservableList<ServicoManobra>();
    private List<ServicoManobra> listaServicoManobraSolicitada = new SerializableObservableList<ServicoManobra>();
    private Manobra manobraRegistradaSelecionada;
    private Manobra manobraSolicitadaSelecionada;
    private TimeZone timeZone;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
        carregarListaManobrasRegistradas();
        carregarListaManobrasSolicitadas();
        carregarTimeZone();
    }
    
    public List<Manobra> getListaManobrasRegistradas() {
        return listaManobrasRegistradas;
    }
    
    public Manobra getManobraRegistradaSelecionada() {
        return manobraRegistradaSelecionada;
    }
    
    public void setManobraRegistradaSelecionada(Manobra manobraRegistradaSelecionada) {
        this.manobraRegistradaSelecionada = manobraRegistradaSelecionada;
        firePropertyChange("manobraRegistradaSelecionada", null, manobraRegistradaSelecionada);
        firePropertyChange("habilitarEncerrarManobra", null, null);
        firePropertyChange("habilitarEditarManobraRegistrada", null, null);
        carregarListaServicoManobraRegistrada();
    }
    
    public List<Manobra> getListaManobrasSolicitadas() {
        return listaManobrasSolicitadas;
    }
    
    public Manobra getManobraSolicitadaSelecionada() {
        return manobraSolicitadaSelecionada;
    }
    
    public void setManobraSolicitadaSelecionada(Manobra manobraSolicitadaSelecionada) {
        this.manobraSolicitadaSelecionada = manobraSolicitadaSelecionada;
        firePropertyChange("manobraSolicitadaSelecionada", null, this.manobraSolicitadaSelecionada);
        firePropertyChange("habilitarRegistrarManobraSolicitada", null, null);
        firePropertyChange("habilitarEditarManobraSolicitada", null, null);
        firePropertyChange("habilitarCancelarManobraSolicitada", null, null);
        carregarListaServicoManobraSolicitada();
    }
    
    public List<ServicoManobra> getListaServicoManobraRegistrada() {
        return listaServicoManobraRegistrada;
    }
    
    public List<ServicoManobra> getListaServicoManobraSolicitada() {
        return listaServicoManobraSolicitada;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        firePropertyChange("timeZone", null, null);
    }
    
    //</editor-fold>
    
    public void cancelarManobraSolicitada(String motivo) {
        getService().cancelarManobraDentroPrazo(this.obterManobraSolicitadaParaEdicao(), motivo);
    }
    
    public void encerrarManobra(Porto portoDestino, Date eta){
        Manobra manobraEncerrada = getService().encerrarManobra(manobraRegistradaSelecionada, portoDestino, eta);
        atualizarManobraRegistrada(manobraEncerrada);
    }
    
    public Manobra obterNovaManobraParaRegistro() {
        ResponsavelCustoEntity responsavelCusto = new ResponsavelCustoEntity();
        responsavelCusto.setId(1L);
        Manobra manobra = new Manobra();
        manobra.setAgenciamento(agenciamento);
        manobra.setStatus(StatusManobra.REGISTRADA);
        manobra.setResponsavelCusto(new ResponsavelCustoEntity());
        manobra.setCaladoVante(agenciamento.getCaladoChegadaVante());
        manobra.setCaladoRe(agenciamento.getCaladoChegadaRe());
        manobra.setResponsavelCusto(responsavelCusto);
        return manobra;
    }
    
    public Manobra obterNovaManobraParaSolicitacao(){
        ResponsavelCustoEntity responsavelCusto = new ResponsavelCustoEntity();
        responsavelCusto.setId(1L);
        Manobra manobra = new Manobra();
        manobra.setAgenciamento(agenciamento);
        manobra.setStatus(StatusManobra.PLANEJADA);
        manobra.setResponsavelCusto(new ResponsavelCustoEntity());
        manobra.setCaladoVante(agenciamento.getCaladoChegadaVante());
        manobra.setCaladoRe(agenciamento.getCaladoChegadaRe());
        manobra.setResponsavelCusto(responsavelCusto);
        return manobra;
    }
    
    
    public Manobra obterManobraParaEdicaoRegistro(){
        return getService().buscarManobrasPorId(manobraRegistradaSelecionada.getId());
    }

    public Manobra obterManobraSolicitadaParaEdicao(){
        return getService().buscarManobrasPorId(manobraSolicitadaSelecionada.getId());
    }
    
    public void adicionarManobraRegistrada(Manobra manobra) {
        agenciamento.adicionarManobra(manobra);
        carregarListaManobrasRegistradas();
    }
    
    public void atualizarManobraSolicitada(Manobra manobra) {
       agenciamento.removerManobra(manobraSolicitadaSelecionada);
       agenciamento.adicionarManobra(manobra);
       carregarListaManobrasSolicitadas();
       carregarListaManobrasRegistradas();
    }
    
    public void atualizarManobraRegistrada(Manobra manobra) {
       agenciamento.removerManobra(manobraRegistradaSelecionada);
       agenciamento.adicionarManobra(manobra);
       AssyncInvoker.create(this, "carregarManobrasDoAgenciamento").schedule();
    }
    
    private void carregarListaManobrasRegistradas(){
        listaManobrasRegistradas.clear();
        listaServicoManobraRegistrada.clear();
        
        if (agenciamento != null){
            listaManobrasRegistradas.addAll(agenciamento.getManobrasRegistradas());
        }
    }

    private void carregarListaManobrasSolicitadas(){
        listaManobrasSolicitadas.clear();
        listaServicoManobraSolicitada.clear();
        
        if (agenciamento != null){
            listaManobrasSolicitadas.addAll(agenciamento.getManobrasSolicitadas());
        }
    }
    
    private void carregarListaServicoManobraRegistrada(){
        listaServicoManobraRegistrada.clear();
        
        if (manobraRegistradaSelecionada != null){
            listaServicoManobraRegistrada.addAll(manobraRegistradaSelecionada.getServicos());
        }
    }
    
    private void carregarListaServicoManobraSolicitada(){
        listaServicoManobraSolicitada.clear();
        
        if (manobraSolicitadaSelecionada != null){
            listaServicoManobraSolicitada.addAll(manobraSolicitadaSelecionada.getServicos());
        }
    }
    
    private void carregarTimeZone(){
        if (agenciamento == null){
            setTimeZone(null);
        }else{
            setTimeZone(TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
        }
        
    }
    
    public void carregarManobrasDoAgenciamento() {
        agenciamento.setManobras(new HashSet<Manobra>(getService().buscarManobrasPorAgenciamento(agenciamento)));
        setAgenciamento(agenciamento);
    }
    
    public boolean isHabilitarRegistrarManobraSolicitada(){
        return (manobraSolicitadaSelecionada != null
                && StatusManobra.SOLICITADA.equals(manobraSolicitadaSelecionada.getStatus()));
    }
    
    public boolean isHabilitarEditarManobraSolicitada(){
         return (manobraSolicitadaSelecionada != null
                && (StatusManobra.SOLICITADA.equals(manobraSolicitadaSelecionada.getStatus())
                    || StatusManobra.PLANEJADA.equals(manobraSolicitadaSelecionada.getStatus())));
    }
    
    public boolean isHabilitarCancelarManobraSolicitada(){
        return (manobraSolicitadaSelecionada != null
                && StatusManobra.SOLICITADA.equals(manobraSolicitadaSelecionada.getStatus()));
    }
    
    public boolean isHabilitarEncerrarManobra(){
        return (manobraRegistradaSelecionada != null
                && (StatusManobra.REGISTRADA.equals(manobraRegistradaSelecionada.getStatus()) || (manobraRegistradaSelecionada.isResponsavelCustoSemCusto() && StatusManobra.PRE_REGISTRADA.equals(manobraRegistradaSelecionada.getStatus()))));
    }
    
    public boolean isHabilitarEditarManobraRegistrada(){
        return (manobraRegistradaSelecionada != null
                && (StatusManobra.REGISTRADA.equals(manobraRegistradaSelecionada.getStatus())
                    || StatusManobra.CANCELADA_FORA_PRAZO.equals(manobraRegistradaSelecionada.getStatus())
                    || StatusManobra.PRE_REGISTRADA.equals(manobraRegistradaSelecionada.getStatus())));
    }
    
    
    
    
}
