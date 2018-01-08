package br.com.petrobras.sistam.desktop.agenciamento.manobras;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoServico;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class ServicoManobraModel extends BasePresentationModel<SistamService> {
    private ServicoManobra servicoManobra;
    private List<EmpresaMaritima> empresasMaritimas;
    private List<TipoServico> listaTipoServico;
    private ServicoResponsavel responsavel;
    private ServicoResponsavel responsavelSelecionado;
    private List<Servico> listaServico;
    private boolean continuarInserindo = true;
    private boolean edicao = false;

    public ServicoManobraModel(ServicoManobra servicoManobra) {
        this.servicoManobra = servicoManobra;
        servicoManobra.addPropertyChangeListener(this);
        
        listaTipoServico = new ArrayList<TipoServico>(Arrays.asList(TipoServico.values()));
        listaTipoServico.add(0, null);
        
        prepararNovoResponsavel();
        carregarEmpresasMaritimas();
        carregarListaServico();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public ServicoManobra getServicoManobra() {
        return servicoManobra;
    }

    public void setServicoManobra(ServicoManobra servicoManobra) {
        this.servicoManobra = servicoManobra;
        firePropertyChange("servicoManobra", null, null);
    }
    
    public TimeZone getTimeZone(){
        if (servicoManobra == null){
            return null;
        }
        
        return TimeZone.getTimeZone(servicoManobra.getManobra().getAgenciamento().getAgencia().getTimezone());
    }
    public List<EmpresaMaritima> getEmpresasMaritimas() {
        return empresasMaritimas;
    }
    
    public void setEmpresasMaritimas(List<EmpresaMaritima> empresasMaritimas) {
        this.empresasMaritimas = empresasMaritimas;
        firePropertyChange("empresasMaritimas", null, null);
    }
    
    public List<TipoServico> getListaTipoServico() {
        return listaTipoServico;
    }

    public ServicoResponsavel getResponsavelSelecionado() {
        return responsavelSelecionado;
    }

    public void setResponsavelSelecionado(ServicoResponsavel responsavelSelecionado) {
        this.responsavelSelecionado = responsavelSelecionado;
        firePropertyChange("responsavelSelecionado", null, null);
    }

    public boolean isContinuarInserindo() {
        return continuarInserindo;
    }

    public void setContinuarInserindo(boolean continuarInserindo) {
        this.continuarInserindo = continuarInserindo;
        firePropertyChange("continuarInserindo", null, null);
    }

    public ServicoResponsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ServicoResponsavel responsavel) {
        this.responsavel = responsavel;
        firePropertyChange("responsavel", null, null);
    }
    
    public List<Servico> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
        firePropertyChange("listaServico", null, null);
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
        firePropertyChange("edicao", null, null);
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos de ações dos botões">
    public void salvar(){
        ServicoManobra manobraAtualizada = getService().salvarServicoManobra(servicoManobra);
        setServicoManobra(manobraAtualizada);
    }
    
    public void validar(){
        getService().validarSalvarServicoManobra(servicoManobra);
    }
    
    public void adicionarResponsavel(){
        if (servicoManobra.getId() != null){
            getService().salvarResponsavel(responsavel);
        }
        else{
            getService().validarSalvarResposnavel(responsavel);
        }
        
        servicoManobra.adicionarResponsavel(responsavel);
        prepararNovoResponsavel();
    }
    
    public void excluirResponsavel(){
        if (servicoManobra.getId() != null){
            getService().excluirResponsavel(responsavelSelecionado);
        }
        
        servicoManobra.removerResponsavel(responsavelSelecionado);
    }
    
    //</editor-fold>
    
    private void prepararNovoResponsavel(){
        ServicoResponsavel novoResponsavel = new ServicoResponsavel();
        novoResponsavel.setServicoManobra(servicoManobra);
        setResponsavel(novoResponsavel);
    }
    
    public void prepararNovoServico() {
        ServicoManobra novo = new ServicoManobra();
        novo.setManobra(servicoManobra.getManobra());
        novo.addPropertyChangeListener(this);
        
        setServicoManobra(novo);
        carregarEmpresasMaritimas();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ServicoManobra.PROP_TIPO_SERVICO)){
            carregarEmpresasMaritimas();
        }
        
        if (evt.getPropertyName().equals(ServicoManobra.PROP_EMPRESA_MARITIMA)){
            carregarListaServico();
            servicoManobra.removerTodosResponsaveis();
        }
    }
    
    private void carregarEmpresasMaritimas(){
        List<EmpresaMaritima> lista = new ArrayList<EmpresaMaritima>();
        if (servicoManobra.getTipoDoServico() != null){
            Agencia agencia = servicoManobra.getManobra().getAgenciamento().getAgencia();
            lista = getService().buscarEmpresasMaritimasPorAgenciaTipoServico(agencia, servicoManobra.getTipoDoServico());
            lista.add(0, null);
        }
        setEmpresasMaritimas(lista);
    }
    
    public void carregarListaServico(){
        List<Servico> lista = new ArrayList<Servico>();
        
        if (servicoManobra.getTipoDoServico() != null && servicoManobra.getEmpresaMaritima() != null){
            lista = getService().buscarServicosPorEmpresaETipo(servicoManobra.getEmpresaMaritima(), servicoManobra.getTipoDoServico());
            lista.add(0, null);
            setListaServico(lista);
        }
        setListaServico(lista);
    }
    
    public boolean isHabilitarDataProgramada(){
        return (servicoManobra.getId() == null 
                || StatusManobra.PLANEJADA.equals(servicoManobra.getManobra().getStatus())
                || StatusManobra.REGISTRADA.equals(servicoManobra.getManobra().getStatus())
                || StatusManobra.CANCELADA_FORA_PRAZO.equals(servicoManobra.getManobra().getStatus()));
    }

}