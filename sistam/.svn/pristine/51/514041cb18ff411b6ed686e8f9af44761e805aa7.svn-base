package br.com.petrobras.sistam.desktop.agenciamento.visita;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Transporte;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.valueobjects.Usuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalheVisitaModel extends BasePresentationModel<SistamService> {
    private Visita visita;
    private List<ResponsavelCustoEntity> listaResponsavelCusto;
    private List<TipoTransporte> listaTipoTransporte;
    private List<StatusEmbarcacao> listaCondicoesNavio;
    private List<Usuario> listaAgentes;
    private Usuario agenteSelecionado;
    private Transporte novoTransporte;
    private Transporte transporteSelecionado;
    
    private boolean continuarInserindo = true;
    
    private String chaveAgenteTemp;
    private String nomeAgenteTemp;
    
    public DetalheVisitaModel(Visita visita) {
        this.visita = visita;
        prepararNovoTransporte();
        
        chaveAgenteTemp = visita.getChaveAgente();
        nomeAgenteTemp = visita.getNomeAgente();
        
        listaTipoTransporte = new ArrayList<TipoTransporte>(Arrays.asList(TipoTransporte.values()));
        listaTipoTransporte.add(0, null);
        
        listaCondicoesNavio = new ArrayList<StatusEmbarcacao>(StatusEmbarcacao.listValuesCondicoesNavio());
        listaCondicoesNavio.add(0, null);
        
        listaResponsavelCusto = getService().buscarTodosResponsavelCusto(); 
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Visita getVisita() {
        return visita;
    }
    
    public void setVisita(Visita visita) {
        this.visita = visita;
        firePropertyChange("visita", null, null);
    }

    public List<StatusEmbarcacao> getListaCondicoesNavio() {
        return listaCondicoesNavio;
    }

    public List<ResponsavelCustoEntity> getListaResponsavelCusto() {
        return listaResponsavelCusto;
    }

    public void setListaResponsavelCusto(List<ResponsavelCustoEntity> listaResponsavelCusto) {
        this.listaResponsavelCusto = listaResponsavelCusto;
    } 
    
    public List<TipoTransporte> getListaTipoTransporte() {
        return listaTipoTransporte;
    } 
    
    public List<Usuario> getListaAgentes() {
        return listaAgentes;
    }
    
    public void setListaAgentes(List<Usuario> listaAgentes) {
        this.listaAgentes = listaAgentes;
        firePropertyChange("listaAgentes", null, null);
    }
    
    public Usuario getAgenteSelecionado() {
        return agenteSelecionado;
    }
    
    public void setAgenteSelecionado(Usuario agenteSelecionado) {
        this.agenteSelecionado = agenteSelecionado;
        firePropertyChange("agenteSelecionado", null, null);
        
        if (agenteSelecionado != null){
            visita.setNomeAgente(agenteSelecionado.getNome());
            visita.setChaveAgente(agenteSelecionado.getChave());
        }else{
            visita.setNomeAgente(null);
            visita.setChaveAgente(null);
        }
    }
    
    public boolean isContinuarInserindo() {
        return continuarInserindo;
    }

    public void setContinuarInserindo(boolean continuarInserindo) {
        this.continuarInserindo = continuarInserindo;
    }

    public Transporte getNovoTransporte() {
        return novoTransporte;
    }

    public void setNovoTransporte(Transporte novoTransporte) {
        this.novoTransporte = novoTransporte;
        firePropertyChange("novoTransporte", null, null);
    }

    public Transporte getTransporteSelecionado() {
        return transporteSelecionado;
    }

    public void setTransporteSelecionado(Transporte transporteSelecionado) {
        this.transporteSelecionado = transporteSelecionado;
        firePropertyChange("transporteSelecionado", null, null);
    }
    
    //</editor-fold>
    
    public void salvar(){
        visita = getService().salvarVisita(visita);
    }
    
    public void prepararNovaVisita(){
        Visita nova = new Visita();
        nova.setAgenciamento(visita.getAgenciamento());
        
        setVisita(nova);
        setAgenteSelecionado(null);
        
        prepararNovoTransporte();
    }
    
    public void adicionarTransporte(){
        if (visita.getId() == null){
            getService().validarSalvarTransporte(novoTransporte);
        }else{
            getService().salvarTransporte(novoTransporte);
        }
        visita.adicionarTransporte(novoTransporte);
        prepararNovoTransporte();
    }
    
    public void excluirTransporte(){
        if (visita.getId() == null){
            getService().excluirTransporte(transporteSelecionado);
        }
        visita.removerTransporte(transporteSelecionado);
    }
    
    public void carregarListaDeAgentes(){
        final String papel = "AGENTE_MARITIMO";
        List<Usuario> usuarios = getService().obterUsuariosPorAgenciaEPapel(visita.getAgenciamento().getAgencia(), papel);
        usuarios.add(0, null);
        setListaAgentes(usuarios);
        setAgenteSelecionado(new Usuario(chaveAgenteTemp, nomeAgenteTemp));
    }
    
    private void prepararNovoTransporte(){
        Transporte novo = new Transporte();
        novo.setVisita(visita);
        setNovoTransporte(novo);
    }

}
