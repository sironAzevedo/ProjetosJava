package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoExecutado;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ServicoProtecaoModel extends BasePresentationModel<SistamService> {

    private List<ServicoExecutado> listaServicoExecutado = new SerializableObservableList<ServicoExecutado>();
    private ServicoExecutado servicoExecutadoSelecionado;
    private Agenciamento agenciamento;

    public ServicoProtecaoModel() {
    }

    public List<ServicoExecutado> getListaServicoExecutado() {
        return listaServicoExecutado;
    }

    public void setListaServicoExecutado(List<ServicoExecutado> listaServicoExecutado) {
        this.listaServicoExecutado = listaServicoExecutado;
        firePropertyChange("listaServicoExecutado", null, null);
    }

    public ServicoExecutado getServicoExecutadoSelecionado() {
        return servicoExecutadoSelecionado;
    }

    public ServicoAcessoPessoa obterServicoAcessoPessoaParaEdicao() {
        return getService().buscarServicoAcessoPessoaPorId(((ServicoAcessoPessoa) servicoExecutadoSelecionado).getId());
    }
    
    public ServicoSuprimento obterServicoSuprimentoParaEdicao(){
        return getService().buscarServicoSuprimentoPorId(((ServicoSuprimento)servicoExecutadoSelecionado).getId());
    }
    
    public ServicoRetiradaResiduoLixo obterServicoRetiradaResiduoLixoParaEdicao(){
        return getService().buscarServicoRetiradaResiduoLixoPorId(((ServicoRetiradaResiduoLixo)servicoExecutadoSelecionado).getId());
    }

    public void setServicoExecutadoSelecionado(ServicoExecutado servicoExecutadoSelecionado) {
        this.servicoExecutadoSelecionado = servicoExecutadoSelecionado;
        firePropertyChange("servicoExecutadoSelecionado", null, null);
        firePropertyChange("habilitarCancelar", null, null);
        firePropertyChange("habilitarEditar", null, null);
        firePropertyChange("habilitarExcluir", null, null);
        firePropertyChange("habilitarConsultar", null, null);
        firePropertyChange("habilitarEmitirFormulario", null, null);
        firePropertyChange("habilitarEnviarEmail", null, null);
        firePropertyChange("habilitarFormulario", null, null);
        firePropertyChange("habilitarFormularioReceitaDesEmbarqueTripulantes", null, null);
        firePropertyChange("habilitarFormularioReceitaPrestacaoServico", null, null);
        firePropertyChange("habilitarFormularioPedidoParaRetiradaResiduoLixo", null, null);
    }

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, null);

        if (agenciamento != null) {
            carregarListaDeServicoExecutado();
        }

    }

    public boolean getHabilitarConsultar() {
        if (this.getServicoExecutadoSelecionado() == null) {
            return false;
        }
        return true;
    }

    public boolean getHabilitarEditar() {
        if (this.getServicoExecutadoSelecionado() == null) {
            return false;
        }

        boolean habilita = true;

        habilita = habilita && !TipoAtendimentoServico.MEDICO_ODONTOLOGICO.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());
        habilita = habilita && !isSituacaoCancelado();


        return habilita;


    }

    public boolean getHabilitarExcluir() {
        if (this.getServicoExecutadoSelecionado() == null) {
            return false;
        }

        boolean habilita = true;

        habilita = habilita && !TipoAtendimentoServico.MEDICO_ODONTOLOGICO.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());
        habilita = habilita && !TipoAtendimentoServico.TRANSPORTE.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());
        habilita = habilita && !TipoAtendimentoServico.ACESSO_PESSOA.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());
        habilita = habilita && !TipoAtendimentoServico.HOSPEDAGEM.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());
        habilita = habilita && !TipoAtendimentoServico.SERVICO_SUPRIMENTO.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());
        habilita = habilita && !TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());

        return habilita;


    }

    public boolean getHabilitarCancelar() {
        if (this.getServicoExecutadoSelecionado() == null) {
            return false;
        }

        boolean habilita = false;
        boolean igualTipo = TipoAtendimentoServico.MEDICO_ODONTOLOGICO.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico())
                || TipoAtendimentoServico.TRANSPORTE.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico())
                || TipoAtendimentoServico.HOSPEDAGEM.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico())
                || TipoAtendimentoServico.SERVICO_SUPRIMENTO.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico())
                || TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());
        habilita = habilita || igualTipo;

        return habilita;

    }

    public boolean getHabilitarEmitirFormulario() {
        if (this.getServicoExecutadoSelecionado() == null) {
            return false;
        }

        boolean habilita = true;

        habilita = habilita && TipoAtendimentoServico.TRANSPORTE.equals(servicoExecutadoSelecionado.getServicoProtecao().getTipoAtendimentoServico());

        return habilita;
    }

    public boolean getHabilitarEnviarEmail() {
        if (this.getServicoExecutadoSelecionado() == null) {
            return false;
        }

        return servicoExecutadoSelecionado.getServicoProtecao().isTipoTransporte() || servicoExecutadoSelecionado.getServicoProtecao().isTipoHospedagem() 
            || servicoExecutadoSelecionado.getServicoProtecao().isTipoServicoRetiradaResiduoLixo();
    }

    public boolean isHabilitarFormularioReceitaDesEmbarqueTripulantes() {
        if (servicoExecutadoSelecionado == null || !servicoExecutadoSelecionado.getServicoProtecao().isTipoAcessoPessoa()) {
            return false;
        }
        ServicoAcessoPessoa servicoAcessoPessoa = (ServicoAcessoPessoa) servicoExecutadoSelecionado;
        return servicoAcessoPessoa.isTipoAcessoEmbarque() || servicoAcessoPessoa.isTipoAcessoDesembarque();
    }
    
    public boolean isHabilitarFormularioReceitaPrestacaoServico() {
        if (servicoExecutadoSelecionado == null || !servicoExecutadoSelecionado.getServicoProtecao().isTipoAcessoPessoa()) {
            return false;
        }
        ServicoAcessoPessoa servicoAcessoPessoa = (ServicoAcessoPessoa) servicoExecutadoSelecionado;
        return servicoAcessoPessoa.isTipoAcessoAcesso();
    }  

    public boolean getHabilitarFormulario() {
        if (this.getServicoExecutadoSelecionado() == null) {
            return false;
        }

        return servicoExecutadoSelecionado.getServicoProtecao().isTipoAcessoPessoa()||servicoExecutadoSelecionado.getServicoProtecao().isTipoServicoRetiradaResiduoLixo();
    }

    public boolean isSituacaoCancelado() {
        return (servicoExecutadoSelecionado != null && servicoExecutadoSelecionado.getServicoProtecao().getDataCancelamento() != null) ? true : false;
    }

    public void excluir() {
        getService().excluirServicoExecutado(servicoExecutadoSelecionado);
        listaServicoExecutado.remove(this.servicoExecutadoSelecionado);
    }

    public void adicionarServicoExecutado(ServicoExecutado servicoExecutado) {
        listaServicoExecutado.add(servicoExecutado);
        firePropertyChange("listaServicoExecutado", null, null);
    }

    public void alterarServicoExecutado(ServicoExecutado servicoExecutado) {
        listaServicoExecutado.remove(servicoExecutado);
        listaServicoExecutado.add(servicoExecutado);
    }

    public ServicoProtecao obterNovoServicoProtecao() {
        ServicoProtecao novo = new ServicoProtecao();
        novo.setAgenciamento(agenciamento);
        return novo;
    }

    public void carregarListaDeServicoExecutado() {
        List<ServicoExecutado> servs = getService().buscarServicosExecutados(agenciamento);
        setListaServicoExecutado(Collections.EMPTY_LIST);
        setListaServicoExecutado(servs);
    }

    public void emitirFormularioTransporte() {
        ServicoProtecao servicoProtecao = servicoExecutadoSelecionado.getServicoProtecao();
        servicoProtecao.setAgenciamento(agenciamento);
        List<ServicoProtecao> lista = new ArrayList<ServicoProtecao>();
        lista.add(servicoProtecao);
        RelatorioUtil.abrirFormularioSolicitacaoTransporte(lista, new HashMap<String, Object>());
    }
    
    private boolean formularioGerado(){
        if(this.getServicoExecutadoSelecionado() == null  && getHabilitarFormulario() == false){
            return false;
        }
        
      
        return true;
    }

}
