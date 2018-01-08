package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalheServicoProtecaoTransporteModel extends BasePresentationModel<SistamService> {

    private ServicoTransporte servicoTransporte;
    private List<TipoTransporte> listaTipoTransporte;
    private List<Servico> empresasTransporte;
    private Passageiro passageiroSelecionado;
    private DetalheServicoProtecaoTransporteDialog dialog;
    private boolean gravado;
    private boolean editar;

    public DetalheServicoProtecaoTransporteModel(ServicoTransporte servicoTransporte) {
        if (servicoTransporte.getId() == null) {
            servicoTransporte.getServicoProtecao().setDataExecucao(new Date());
            servicoTransporte.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.TRANSPORTE);
        } else {
            servicoTransporte = getService().buscarServicoTransportePorId(servicoTransporte.getId());
            servicoTransporte.getServicoProtecao().setNovo(false);
        }
        carregarListaTipoTransporteServico();
        carregarEmpresasTransporte();
        setServicoTransporte(servicoTransporte);
    }

    public ServicoTransporte getServicoTransporte() {
        return servicoTransporte;
    }

    public void setServicoTransporte(ServicoTransporte servicoTransporte) {
        this.servicoTransporte = servicoTransporte;
        firePropertyChange("servicoTransporte", null, null);
    }

    public List<TipoTransporte> getListaTipoTransporte() {
        return listaTipoTransporte;
    }

    public void setListaTipoTransporte(List<TipoTransporte> listaTipoTransporte) {
        this.listaTipoTransporte = listaTipoTransporte;
        firePropertyChange("listaTipoTransporte", null, null);
    }

    public List<Servico> getEmpresasTransporte() {
        return empresasTransporte;
    }

    public void setEmpresasTransporte(List<Servico> empresasTransporte) {
        this.empresasTransporte = empresasTransporte;
        firePropertyChange("empresasTransporte", null, null);
    }

    public Passageiro getPassageiroSelecionado() {
        return passageiroSelecionado;
    }

    public void setPassageiroSelecionado(Passageiro passageiroSelecionado) {
        this.passageiroSelecionado = passageiroSelecionado;
        firePropertyChange("passageiroSelecionado", null, null);
        dialog.ativarDesativar();
    }

    public DetalheServicoProtecaoTransporteDialog getDialog() {
        return dialog;
    }

    public void setDialog(DetalheServicoProtecaoTransporteDialog dialog) {
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

    public String getServicoExecutado() {
        if (servicoTransporte == null || servicoTransporte.getServicoProtecao() == null || servicoTransporte.getServicoProtecao().getTipoAtendimentoServico() == null) {
            return null;
        }

        return servicoTransporte.getServicoProtecao().getTipoAtendimentoServico().getPorExtenso();
    }

    public void salvar() {
        servicoTransporte.getServicoProtecao().setEmailEnviado(false);
        servicoTransporte.getServicoProtecao().setDataEmailEnviado(null);
        servicoTransporte = (ServicoTransporte) getService().salvarServicoExecutado(servicoTransporte);
        setGravado(Boolean.TRUE);
    }

    private void carregarListaTipoTransporteServico() {
        List<TipoTransporte> lista = TipoTransporte.obter();
        lista.add(0, null);
        setListaTipoTransporte(lista);
    }

    public void carregarEmpresasTransporte() {
        List<Servico> lista = new ArrayList<Servico>();
        lista.add(0, null);
        lista.addAll(1, getService().buscarServicosPorTipo(TipoServico.TRANSPORTE));

        setEmpresasTransporte(lista);
    }

    public Passageiro obterNovoPassageiro() {
        Passageiro novoPasssageiro = new Passageiro();

        ServicoProtecao servicoProtecao = servicoTransporte.getServicoProtecao();
        servicoProtecao.setServicoTransporte(servicoTransporte);

        novoPasssageiro.setServicoProtecao(servicoProtecao);
        novoPasssageiro.setAtivo(Boolean.TRUE);

        return novoPasssageiro;
    }

    public Passageiro obterPassageiroParaEdicao() {
        
        servicoTransporte.getServicoProtecao().setServicoTransporte(servicoTransporte);

        Passageiro passageiroParaEdicao = (Passageiro) passageiroSelecionado.clone();
        passageiroParaEdicao.setServicoProtecao(servicoTransporte.getServicoProtecao());

        return (Passageiro) passageiroParaEdicao.clone();
    }

    public void adicionarPassageiro(Passageiro passageiro) {
        servicoTransporte.adicionarPassageiro(passageiro);
    }

    public void atualizarPassageiro(Passageiro passageiro) {
        servicoTransporte.removerPassageiro(passageiroSelecionado);
        servicoTransporte.adicionarPassageiro(passageiro);
    }

    public void excluirPassageiro() {
        servicoTransporte.removerPassageiro(passageiroSelecionado);
    }

    public void ativarCancelarPassageiro() {
        Passageiro passageiroAtualizado = passageiroSelecionado;
        passageiroSelecionado.setAtivo(!passageiroAtualizado.isAtivo());

        servicoTransporte.removerPassageiro(passageiroSelecionado);
        servicoTransporte.adicionarPassageiro(passageiroAtualizado);
    }
}
