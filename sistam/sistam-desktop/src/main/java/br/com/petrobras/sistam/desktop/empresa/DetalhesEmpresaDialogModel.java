package br.com.petrobras.sistam.desktop.empresa;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalhesEmpresaDialogModel extends BasePresentationModel<SistamService> {
    private EmpresaMaritima empresa;
    private Servico servicoSelecionado;
    private List<Agencia> listaDeAgencias;
    private boolean salvo = false;
    private List<TipoSimNao> tiposSimNao;
    private TipoSimNao ativoSimNaoSelecionado;

    
    public DetalhesEmpresaDialogModel(EmpresaMaritima empresa) {
        this.empresa = empresa;
        
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        listaDeAgencias = contexto.getAgenciasAutorizadas();
        listaDeAgencias.add(0, null);
        
        tiposSimNao = new ArrayList<TipoSimNao>(Arrays.asList(TipoSimNao.values()));
        
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public EmpresaMaritima getEmpresa() {
        return empresa;
    }

    public List<Agencia> getListaDeAgencias() {
        return listaDeAgencias;
    }
    
    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }
    
    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
        firePropertyChange("servicoSelecionado", null, null);
    }

    public boolean isSalvo() {
        return salvo;
    }

    public List<TipoSimNao> getTiposSimNao() {
        return tiposSimNao;
    }

    public TipoSimNao getAtivoSimNaoSelecionado() {
        return TipoSimNao.from(empresa.getAtivo());
    }

    public void setAtivoSimNaoSelecionado(TipoSimNao ativoSimNaoSelecionado) {
        this.ativoSimNaoSelecionado = ativoSimNaoSelecionado;
        firePropertyChange("ativoSimNaoSelecionado", null, null);
        
        empresa.setAtivo(ativoSimNaoSelecionado.getId());
    }


    //</editor-fold>
    
    public void salvar(){
        empresa = getService().salvarEmpresa(empresa);
        salvo = true;
    }
    
    public void adicionar(Servico servico){
        empresa.adicionarServico(servico);
    }
    
    public void excluir(){
        getService().excluirServicoDaEmpresa(servicoSelecionado);
        empresa.removerServico(servicoSelecionado);
    }
    
    public Servico obterNovoServico(){
        Servico novo = new Servico();
        novo.setEmpresa(empresa);
        return novo;
    }
    
}