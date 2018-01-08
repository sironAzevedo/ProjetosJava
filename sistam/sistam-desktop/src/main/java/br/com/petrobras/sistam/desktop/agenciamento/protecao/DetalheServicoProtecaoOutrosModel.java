package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.enums.TipoAtendimento;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import java.util.ArrayList;
import java.util.List;

public class DetalheServicoProtecaoOutrosModel extends BasePresentationModel<SistamService> {
    private ServicoProtecao servicoProtecao;
    private List<TipoAtendimento> listaTipoAtendimento; 
    private TipoAtendimento tipoAtendimentoSelecionado; 
    private List<TipoAtendimentoServico> listaTipoAtendimentoServico;     
    private TipoAtendimentoServico tipoAtendimentoServicoSelecionado;
    
    public DetalheServicoProtecaoOutrosModel(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
        
        listaTipoAtendimento = new ArrayList<TipoAtendimento>(TipoAtendimento.valuesActive());
        listaTipoAtendimento.add(0, null);
       
        if (servicoProtecao.getId() != null){
             setTipoAtendimentoSelecionado(servicoProtecao.getTipoAtendimentoServico().getTipoAtendimento());
        }
        
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public ServicoProtecao getServicoProtecao() {
        return servicoProtecao;
    }

    public void setServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
        firePropertyChange("servicoProtecao", null, null);
    }

    public List<TipoAtendimento> getListaTipoAtendimento() {
        return listaTipoAtendimento;
    }

    public void setListaTipoAtendimento(List<TipoAtendimento> listaTipoAtendimento) {
        this.listaTipoAtendimento = listaTipoAtendimento;
        firePropertyChange("listaTipoAtendimento", null, null);
    }
    
    public List<TipoAtendimentoServico> getListaTipoAtendimentoServico() {
        return listaTipoAtendimentoServico;
    }

    public void setListaTipoAtendimentoServico(List<TipoAtendimentoServico> listaTipoAtendimentoServico) {
        this.listaTipoAtendimentoServico = listaTipoAtendimentoServico;
        firePropertyChange("listaTipoAtendimentoServico", null, null);
    }

    public TipoAtendimento getTipoAtendimentoSelecionado() {
        return tipoAtendimentoSelecionado;
    }

    public void setTipoAtendimentoSelecionado(TipoAtendimento tipoAtendimentoSelecionado) {
        this.tipoAtendimentoSelecionado = tipoAtendimentoSelecionado;
        firePropertyChange("tipoAtendimentoSelecionado", null, null);
        
        setListaTipoAtendimentoServico(new ArrayList<TipoAtendimentoServico>());
        setTipoAtendimentoServicoSelecionado(null);
        if (tipoAtendimentoSelecionado != null) {
            carregarListaTipoAtendimentoServico();
        }
    }

    public TipoAtendimentoServico getTipoAtendimentoServicoSelecionado() {
        return tipoAtendimentoServicoSelecionado;
    }

    public void setTipoAtendimentoServicoSelecionado(TipoAtendimentoServico tipoAtendimentoServicoSelecionado) {
        this.tipoAtendimentoServicoSelecionado = tipoAtendimentoServicoSelecionado;
        firePropertyChange("tipoAtendimentoServicoSelecionado", null, null);
    }
    
    //</editor-fold>
    
    public void salvar(){
        servicoProtecao = getService().salvarServicoProtecao(servicoProtecao);
    }
        
    private void carregarListaTipoAtendimentoServico(){
        List<TipoAtendimentoServico> lista = TipoAtendimentoServico.obterPor(tipoAtendimentoSelecionado, false);
        lista.add(0, null);
        setListaTipoAtendimentoServico(lista);
    }
    
}
