package br.com.petrobras.sistam.desktop.empresa;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresa;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.List;

public class EmpresaFrameModel extends BasePresentationModel<SistamService> {
    private List<EmpresaMaritima> listaDeEmpresas = new SerializableObservableList<EmpresaMaritima>();
    private EmpresaMaritima empresaSelecionada;
    private List<Agencia> listaDeAgencias;
    private FiltroEmpresa filtro;

    public EmpresaFrameModel() {
        this.filtro = new FiltroEmpresa();
        
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        listaDeAgencias = contexto.getAgenciasAutorizadas();
        listaDeAgencias.add(0, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<EmpresaMaritima> getListaDeEmpresas() {
        return listaDeEmpresas;
    }

    public List<Agencia> getListaDeAgencias() {
        return listaDeAgencias;
    }
    
    public EmpresaMaritima getEmpresaSelecionada() {
        return empresaSelecionada;
    }
    
    public void setEmpresaSelecionada(EmpresaMaritima empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
        firePropertyChange("empresaSelecionada", null, null);
    }

    public FiltroEmpresa getFiltro() {
        return filtro;
    }
    
    //</editor-fold>
    
    public void excluir() {
        getService().excluirEmpresa(empresaSelecionada);
    }
    
    public void pesquisar(){
        listaDeEmpresas.clear();
        listaDeEmpresas.addAll(getService().buscarEmpresasPorFiltro(filtro));
    }
    
    public void limpar(){
        filtro.limpar();
    }
    
    public void adicionar(EmpresaMaritima empresa){
        listaDeEmpresas.add(empresa);
    }
    
    public void atualizar(EmpresaMaritima empresa){
        listaDeEmpresas.remove(empresaSelecionada);
        listaDeEmpresas.add(empresa);
    }
    
    public EmpresaMaritima obterNovaEmpresa(){
        EmpresaMaritima nova = new EmpresaMaritima();
        return nova;
    }
    
    public EmpresaMaritima obterEmpresaParaEdicao(){
        EmpresaMaritima clone =(EmpresaMaritima)empresaSelecionada.clone();
        return clone;
    }
    
}