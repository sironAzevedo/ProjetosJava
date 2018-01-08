package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoResiduo;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.enums.TipoUnidadeMedida;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public final class DetalheServicoProtecaoRetiradaResiduoLixoModel extends BasePresentationModel<SistamService> {
 
    private ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo;
    private EmpresaProtecao prestadorServico;
    private List<ResponsavelCusto> listaResponsavelCustos;
    private List<TipoResiduo> ListaTipoResiduos; 
    private List<EmpresaMaritima> empresasMaritimas;
    private List<EmpresaMaritima> empresasMaritimasRodoviaria;
    private List<Servico> servicos;
    private List<TipoUnidadeMedida> listaTipoUnidadeMedidas;
    private List<TipoUnidadeMedida> tipoSelecionado;
    private DetalheServicoProtecaoRetiradaResiduoLixoDialog dialog;
    private boolean gravado;
    private boolean consultar;
    private boolean editar;

    public DetalheServicoProtecaoRetiradaResiduoLixoModel(ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo, boolean editar, boolean consultar) {
        this.editar = editar;
        this.consultar = consultar;
        if (servicoRetiradaResiduoLixo.getId() == null) {
            servicoRetiradaResiduoLixo.getServicoProtecao().setDataExecucao(new Date());
            servicoRetiradaResiduoLixo.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO);
        } else {
            servicoRetiradaResiduoLixo.getServicoProtecao().setNovo(false);
        }
        
        setServicoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
        carregarCombos();
        carregarListaUnidadeMedidaParaEdicao();
        this.servicoRetiradaResiduoLixo.addPropertyChangeListener(this);

    }

    public void salvar() {
        servicoRetiradaResiduoLixo.getServicoProtecao().setEmailEnviado(false);
        servicoRetiradaResiduoLixo.getServicoProtecao().setDataEmailEnviado(null);
        servicoRetiradaResiduoLixo = (ServicoRetiradaResiduoLixo) getService().salvarServicoExecutado(servicoRetiradaResiduoLixo);
        setGravado(Boolean.TRUE);
    }

    public TimeZone getTimeZone() {
        if (servicoRetiradaResiduoLixo == null) {
            return null;
        }
        return TimeZone.getTimeZone(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getTimezone());
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters"> 

    public ServicoRetiradaResiduoLixo getServicoRetiradaResiduoLixo() {
        return servicoRetiradaResiduoLixo;
    }

    public void setServicoRetiradaResiduoLixo(ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo) {
        this.servicoRetiradaResiduoLixo = servicoRetiradaResiduoLixo; 
        firePropertyChange("servicoRetiradaResiduoLixo", null, null);
    } 

    public DetalheServicoProtecaoRetiradaResiduoLixoDialog getDialog() {
        return dialog;
    }

    public void setDialog(DetalheServicoProtecaoRetiradaResiduoLixoDialog dialog) {
        this.dialog = dialog;
        firePropertyChange("dialog", null, null);
    }

    public boolean isGravado() {
        return gravado;
    }

    public void setGravado(boolean gravado) {
        this.gravado = gravado;
        firePropertyChange("gravado", null, null);
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
        firePropertyChange("editar", null, null);
    }

    public boolean isConsultar() {
        return consultar;
    }

    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
        firePropertyChange("consultar", null, null);
    }  

    public EmpresaProtecao getPrestadorServico() {
        return prestadorServico;
    }

    public void setPrestadorServico(EmpresaProtecao prestadorServico) {
        this.prestadorServico = prestadorServico;
    }
    
    public List<ResponsavelCusto> getListaResponsavelCustos() {
        return listaResponsavelCustos;
    }

    public void setListaResponsavelCustos(List<ResponsavelCusto> listaResponsavelCustos) {
        this.listaResponsavelCustos = listaResponsavelCustos;
        firePropertyChange("listaResponsavelCustos", null, null);
    }

    public List<TipoResiduo> getListaTipoResiduos() {
        return ListaTipoResiduos;
    }

    public void setListaTipoResiduos(List<TipoResiduo> ListaTipoResiduos) {
        this.ListaTipoResiduos = ListaTipoResiduos;
        firePropertyChange("ListaTipoResiduos", null, null);
    } 

    public List<EmpresaMaritima> getEmpresasMaritimas() {
        return empresasMaritimas;
    }

    public void setEmpresasMaritimas(List<EmpresaMaritima> empresasMaritimas) {
        this.empresasMaritimas = empresasMaritimas;
        firePropertyChange("empresasMaritimas", null, null);
    }

    public List<EmpresaMaritima> getEmpresasMaritimasRodoviaria() {
        return empresasMaritimasRodoviaria;
    }

    public void setEmpresasMaritimasRodoviaria(List<EmpresaMaritima> empresasMaritimasRodoviaria) {
        this.empresasMaritimasRodoviaria = empresasMaritimasRodoviaria;
         firePropertyChange("empresasMaritimasRodoviaria", null, null);
    }
    
    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
        firePropertyChange("servicos", null, null);
    }

    public List<TipoUnidadeMedida> getListaTipoUnidadeMedidas() {
        return listaTipoUnidadeMedidas;
    }

    public void setListaTipoUnidadeMedidas(List<TipoUnidadeMedida> listaTipoUnidadeMedidas) {
        this.listaTipoUnidadeMedidas = listaTipoUnidadeMedidas;
        firePropertyChange("listaTipoUnidadeMedidas", null, null);
    } 

    public List<TipoUnidadeMedida> getTipoSelecionado() {
        return tipoSelecionado;
    }

    public void setTipoSelecionado(List<TipoUnidadeMedida> tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
        firePropertyChange("tipoSelecionado", null, null);
    } 
    
    public String getServicoExecutado() {
        if (servicoRetiradaResiduoLixo == null || servicoRetiradaResiduoLixo.getServicoProtecao() == null || servicoRetiradaResiduoLixo.getServicoProtecao().getTipoAtendimentoServico() == null) {
            return null;
        }

        return servicoRetiradaResiduoLixo.getServicoProtecao().getTipoAtendimentoServico().getPorExtenso();
    }
    //</editor-fold> 
    
    public final void carregarCombos() {
        carregarEmpresasRequerentes();
        carregarEmpresasMaritimas();
        carregarEmpresasRodoviarias();
        carregarListaServico();
        carregarListaTipoResiduo();
    }
    
    private void carregarEmpresasRequerentes(){
        List<ResponsavelCusto> lista = new ArrayList<ResponsavelCusto>();
        lista.add(0,null);
        lista.addAll(1, Arrays.asList(ResponsavelCusto.values()));
        
        setListaResponsavelCustos(lista);
    }

    private void carregarEmpresasMaritimas() {
        Agencia agencia = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia();
        List<EmpresaMaritima> lista = getService().buscarEmpresasMaritimasPorAgenciaTipoServico(agencia, TipoServico.SUPRIMENTO);
        lista.add(0, null);

        setEmpresasMaritimas(lista); 
    }

    public void carregarListaServico() {
        List<Servico> lista = new ArrayList<Servico>();

        if (servicoRetiradaResiduoLixo.getEmpresaMaritima() != null) {
            lista = getService().buscarServicosPorEmpresaETipo(servicoRetiradaResiduoLixo.getEmpresaMaritima(), TipoServico.SUPRIMENTO);
            lista.add(0, null);
        }
        setServicos(lista);
    }
    
    private void carregarEmpresasRodoviarias(){
        Agencia agencia = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia();
        List<EmpresaMaritima> lista = getService().buscarEmpresasMaritimasPorAgenciaTipoServico(agencia, TipoServico.SUPRIMENTO);
        lista.add(0, null);

        setEmpresasMaritimasRodoviaria(lista); 
    } 
    
    public void carregarListaTipoResiduo(){
        List<TipoResiduo> lista = new ArrayList<TipoResiduo>();
        lista.add(0, null);
        lista.addAll(1, Arrays.asList(TipoResiduo.values()));
        setListaTipoResiduos(lista);
    }
    
    private void carregarListaUnidadeMedidaParaEdicao(){
        if(editar || consultar){
            carregarListaUnidadeMedida();
        }
    }
    
    public void carregarListaUnidadeMedida(){ 
        if(servicoRetiradaResiduoLixo.getTipoResiduo() != null && servicoRetiradaResiduoLixo.isTipoResiduoClasseI()){
            carregarListaUnidadeMedidaClasseI();
        } else if (servicoRetiradaResiduoLixo.getTipoResiduo() != null && servicoRetiradaResiduoLixo.isTipoResiduoClasseII()) {
            carregarListaUnidadeMedidaClasseII();
        } 
    } 
    
    public void herdarValoresTiposDeResiduo() {
        servicoRetiradaResiduoLixo.setCaracterizacao(null);
        servicoRetiradaResiduoLixo.setEstadoFisico(null);
        servicoRetiradaResiduoLixo.setClassificacao(null);
        servicoRetiradaResiduoLixo.setCodigoOnu(null);
        final TipoResiduo tipoResiduo = servicoRetiradaResiduoLixo.getTipoResiduo();
        if (tipoResiduo != null) {
            servicoRetiradaResiduoLixo.setCaracterizacao(tipoResiduo.getCaracterizacao());
            servicoRetiradaResiduoLixo.setEstadoFisico(tipoResiduo.getEstadoFisico());
            servicoRetiradaResiduoLixo.setClassificacao(tipoResiduo.getClassificacao());
            servicoRetiradaResiduoLixo.setCodigoOnu(tipoResiduo.getCodigoOnu()); 
        } 
    }

    
    private void carregarListaUnidadeMedidaClasseI(){         
        List<TipoUnidadeMedida> lista = new ArrayList<TipoUnidadeMedida>();
        lista.add(0, null);
        lista.addAll(1, Arrays.asList(TipoUnidadeMedida.valueComClasseI()));         
        setListaTipoUnidadeMedidas(lista);
    }
    
    private void carregarListaUnidadeMedidaClasseII(){
        List<TipoUnidadeMedida> lista = new ArrayList<TipoUnidadeMedida>();
        lista.add(0, null);
        lista.addAll(1, Arrays.asList(TipoUnidadeMedida.valueMetroCubico()));         
        setListaTipoUnidadeMedidas(lista);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ServicoRetiradaResiduoLixo.SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_MARITIMA)) {
            carregarListaServico();
        } else if(evt.getPropertyName().equals(ServicoRetiradaResiduoLixo.SERVICO_RETIRADA_RESIDUO_LIXO_TIPO_RESIDUO)){
             herdarValoresTiposDeResiduo();
             carregarListaUnidadeMedida();
        } 
    }  
}
             