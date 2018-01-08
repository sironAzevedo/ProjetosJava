package br.com.petrobras.sistam.desktop.agenciamento.documento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.enums.Autoridade;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.valueobjects.Usuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalheDocumentoDialogModel extends BasePresentationModel<SistamService> {
    private Documento documento;
    private List<Autoridade> listaAutoridades;
    private List<TipoDocumento> listaTipoDocumento;
    private List<RepresentanteLegal> listaRepresentante;
    private List<Usuario> listaEmitentes;
    private Usuario emitenteSelecionado;
    private String chaveEmitenteProvisorio;
    private String nomeEmitenteProvisorio;
    private Agenciamento agenciamento;
    private Autoridade autoridadeSelecionada;
    private boolean continuarInserindo = true;
    private String manobraOperacaoSelecionada;
    
    public DetalheDocumentoDialogModel(Documento documento) {
        
        this.documento = documento;
        this.chaveEmitenteProvisorio = documento.getChaveEmitente();
        this.nomeEmitenteProvisorio = documento.getNomeEmitente();
        this.agenciamento = documento.getAgenciamento();
        
        if (documento.getId() != null){
            setAutoridadeSelecionada(documento.getTipoDocumento().getAutoridade());
        }
        
        carregarListaAutoridades();
        carregarListaDeRepresentante();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Documento getDocumento() {
        return documento;
    }

    public List<TipoDocumento> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
        firePropertyChange("listaTipoDocumento", null, null);
    }
    
    public boolean isContinuarInserindo() {
        return continuarInserindo;
    }
    
    public void setContinuarInserindo(boolean continuarInserindo) {
        this.continuarInserindo = continuarInserindo;
    }
    
    public List<RepresentanteLegal> getListaRepresentante() {
        return listaRepresentante;
    }

    public List<Usuario> getListaEmitentes() {
        return listaEmitentes;
    }

    public void setListaEmitentes(List<Usuario> listaEmitentes) {
        this.listaEmitentes = listaEmitentes;
        firePropertyChange("listaEmitentes", null, null);
    }

    public Usuario getEmitenteSelecionado() {
        return emitenteSelecionado;
    }

    public void setEmitenteSelecionado(Usuario emitenteSelecionado) {
        
        if (emitenteSelecionado != null) {
            documento.setChaveEmitente(emitenteSelecionado.getChave());
            documento.setNomeEmitente(emitenteSelecionado.getNome());
        } else {
            documento.setChaveEmitente(null);
            documento.setNomeEmitente(null);
        }
        
        this.emitenteSelecionado = emitenteSelecionado;
        firePropertyChange("emitenteSelecionado", null, null);
    }

    public List<Autoridade> getListaAutoridades() {
        return listaAutoridades;
    }

    public Autoridade getAutoridadeSelecionada() {
        return autoridadeSelecionada;
    }

    public void setAutoridadeSelecionada(Autoridade autoridadeSelecionada) {
        Object old = this.autoridadeSelecionada;
        this.autoridadeSelecionada = autoridadeSelecionada;
        firePropertyChange("autoridadeSelecionada", old, this.autoridadeSelecionada);
        carregarListaTipoDocumento();
    }

    public String getManobraOperacaoSelecionada() {
        return manobraOperacaoSelecionada;
    }

    public void setManobraOperacaoSelecionada(String manobraOperacaoSelecionada) {
        Object old = this.manobraOperacaoSelecionada;
        this.manobraOperacaoSelecionada = manobraOperacaoSelecionada;
        firePropertyChange("manobraOperacaoSelecionada", old, this.manobraOperacaoSelecionada);
    }
    
    //</editor-fold>
   
    public void prepararNovoDocumento(){
        documento = new Documento();
        documento.setAgenciamento(this.agenciamento);
        
        setAutoridadeSelecionada(null);
        setEmitenteSelecionado(null);
        setManobraOperacaoSelecionada(null);
        
        firePropertyChange("documento", null, null);
    }
    
    public void salvar(){
        documento = getService().salvarDocumento(documento);
    }
    
    public void carregarListaDeEmitentes(){
        final String papel = "AGENTE_MARITIMO";
        List<Usuario> usuarios = getService().obterUsuariosPorAgenciaEPapel(documento.getAgenciamento().getAgencia(), papel);
        usuarios.add(0, null);
        setListaEmitentes(usuarios);
        setEmitenteSelecionado(new Usuario(chaveEmitenteProvisorio, nomeEmitenteProvisorio));
    }
    
    public void carregarDescricaoManobraOperacaoSelecionada(){
        if (documento.getManobra() != null){
            setManobraOperacaoSelecionada(documento.getManobra().getFinalidadeManobra().toString());
        }
        else if (documento.getOperacao() != null){
            setManobraOperacaoSelecionada(documento.getOperacao().toString());
        }
        else{
            setManobraOperacaoSelecionada(null);
        }
    }
    
    private void carregarListaTipoDocumento(){
        List<TipoDocumento> lista = new ArrayList<TipoDocumento>(TipoDocumento.obterPorAutoridade(autoridadeSelecionada));
        lista.add(0, null);
        
        setListaTipoDocumento(lista);
    }
    
    private void carregarListaAutoridades(){
        listaAutoridades = new ArrayList<Autoridade>(Arrays.asList(Autoridade.values()));
        listaAutoridades.add(0, null);
    }
    
    private void carregarListaDeRepresentante(){
        listaRepresentante = new ArrayList<RepresentanteLegal>();
        listaRepresentante.add(0, null);
        listaRepresentante.addAll(documento.getAgenciamento().getAgencia().getRepresentantes());
    }

}
