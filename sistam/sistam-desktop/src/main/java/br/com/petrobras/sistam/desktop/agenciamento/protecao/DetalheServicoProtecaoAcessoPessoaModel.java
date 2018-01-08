package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.enums.TipoAcesso;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoNacionalidade;
import br.com.petrobras.sistam.common.enums.TipoSolicitacaoTransito;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class DetalheServicoProtecaoAcessoPessoaModel extends BasePresentationModel<SistamService> {

    private List<TipoSolicitacaoTransito> listaTipoSolicitacaoTransito;
    private List<TipoAcesso> listaTipoAcesso;
    private List<TipoCategoria> listaTipoCategoria;
    private List<TipoNacionalidade> listaTipoNacionalidade;
    private PessoaAcesso pessoaSelecionada;
    private DetalheServicoProtecaoAcessoPessoaDialog dialog;
    private ServicoAcessoPessoa servicoAcessoPessoa;
    private EmpresaProtecao prestadorServico;
    private boolean gravado;
    private boolean editar;

    public DetalheServicoProtecaoAcessoPessoaModel(ServicoAcessoPessoa servicoAcessoPessoa, boolean editar) {
        carregarCombos();
        this.editar = editar;
        if (servicoAcessoPessoa.getId() == null) {
            servicoAcessoPessoa.getServicoProtecao().setDataExecucao(new Date());
            servicoAcessoPessoa.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.ACESSO_PESSOA);
            servicoAcessoPessoa.setTipoNacionalidade(TipoNacionalidade.BRASILEIROS);
        } else {
            servicoAcessoPessoa = getService().buscarServicoAcessoPessoaPorId(servicoAcessoPessoa.getId());
            servicoAcessoPessoa.getServicoProtecao().setNovo(false);
        }
        setServicoAcessoPessoa(servicoAcessoPessoa);
        if (servicoAcessoPessoa.getId() != null) {
            carregarPrestadoServicoEmEmpresa();
        }
        servicoAcessoPessoa.addPropertyChangeListener(this);
    }
    
    private void carregarPrestadoServicoEmEmpresa(){
        prestadorServico = new EmpresaProtecao();
        prestadorServico.setRazaoSocial(servicoAcessoPessoa.getNomePrestadorServico());
        prestadorServico.setCnpjComMascara(servicoAcessoPessoa.getCnpjPrestadorServicoComMascara());
        prestadorServico.setTelefone(servicoAcessoPessoa.getTelefonePrestadorServico());
    }

    public ServicoAcessoPessoa getServicoAcessoPessoa() {
        return servicoAcessoPessoa;
    }

    public void setServicoAcessoPessoa(ServicoAcessoPessoa servicoAcessoPessoa) {
        this.servicoAcessoPessoa = servicoAcessoPessoa;
        firePropertyChange("servicoAcessoPessoa", null, null);
    }

    public EmpresaProtecao getPrestadorServico() {
        return prestadorServico;
    }

    public void setPrestadorServico(EmpresaProtecao prestadorServico) {
        this.prestadorServico = prestadorServico;
        firePropertyChange("prestadorServico", null, null);
        servicoAcessoPessoa.getPessoas().clear();
        servicoAcessoPessoa.setNomePrestadorServico(null);
        servicoAcessoPessoa.setCnpjPrestadorServicoComMascara(null);
        if(prestadorServico != null){
            servicoAcessoPessoa.setNomePrestadorServico(prestadorServico.getRazaoSocial());
            servicoAcessoPessoa.setCnpjPrestadorServicoComMascara(prestadorServico.getCnpjComMascara());
            servicoAcessoPessoa.setTelefonePrestadorServico(prestadorServico.getTelefone());
            servicoAcessoPessoa.adicionarPessoas(getService().instanciarPessoasDaEmpresa(prestadorServico));
        }
        firePropertyChange("servicoAcessoPessoa", null, null);
    }
    
    public PessoaAcesso getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(PessoaAcesso pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
        firePropertyChange("pessoaSelecionada", null, null);
        dialog.ativarDesativar();
    }

    public DetalheServicoProtecaoAcessoPessoaDialog getDialog() {
        return dialog;
    }

    public void setDialog(DetalheServicoProtecaoAcessoPessoaDialog dialog) {
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
        if (servicoAcessoPessoa == null || servicoAcessoPessoa.getServicoProtecao() == null || servicoAcessoPessoa.getServicoProtecao().getTipoAtendimentoServico() == null) {
            return null;
        }

        return servicoAcessoPessoa.getServicoProtecao().getTipoAtendimentoServico().getPorExtenso();
    }

    public List<TipoSolicitacaoTransito> getListaTipoSolicitacaoTransito() {
        return listaTipoSolicitacaoTransito;
    }

    public void setListaTipoSolicitacaoTransito(List<TipoSolicitacaoTransito> listaTipoSolicitacaoTransito) {
        this.listaTipoSolicitacaoTransito = listaTipoSolicitacaoTransito;
        firePropertyChange("listaTipoSolicitacaoTransito", null, null);
    }

    public List<TipoAcesso> getListaTipoAcesso() {
        return listaTipoAcesso;
    }
    public void setListaTipoAcesso(List<TipoAcesso> listaTipoAcesso) {
        this.listaTipoAcesso = listaTipoAcesso;
        firePropertyChange("listaTipoAcesso", null, null);
    }

    public List<TipoCategoria> getListaTipoCategoria() {
        return listaTipoCategoria;
    }
    
    public void setListaTipoCategoria(List<TipoCategoria> listaTipoCategoria) {
        this.listaTipoCategoria = listaTipoCategoria;
        firePropertyChange("listaTipoCategoria", null, null);
    }

    public List<TipoNacionalidade> getListaTipoNacionalidade() {
        return listaTipoNacionalidade;
    }
    
    public void setListaTipoNacionalidade(List<TipoNacionalidade> listaTipoNacionalidade) {
        this.listaTipoNacionalidade = listaTipoNacionalidade;
        firePropertyChange("listaTipoNacionalidade", null, null);
    }


    public void salvar() {
        servicoAcessoPessoa = (ServicoAcessoPessoa) getService().salvarServicoExecutado(servicoAcessoPessoa);
        setGravado(Boolean.TRUE);
    }
    
    private void carregarListaTipoSolicitacaoTransporte() {
        List<TipoSolicitacaoTransito> lista = new ArrayList<TipoSolicitacaoTransito>();
        lista.add(0, null);
        lista.addAll(1,Arrays.asList(TipoSolicitacaoTransito.values()));
        setListaTipoSolicitacaoTransito(lista);
    }

    public void carregarTipoAcesso() {
        List<TipoAcesso> lista =  new ArrayList<TipoAcesso>();
        lista.add(0, null);
        lista.addAll(1,Arrays.asList(TipoAcesso.values()));

        setListaTipoAcesso(lista);
    }
    public void carregarTipoCategoria() {
         List<TipoCategoria> lista =  new ArrayList<TipoCategoria>();
        lista.add(0, null);
        lista.addAll(1,Arrays.asList(TipoCategoria.values()));

        setListaTipoCategoria(lista);
    }
    
    public void carregarTipoNacionalidade() {
        List<TipoNacionalidade> lista =  new ArrayList<TipoNacionalidade>();
        lista.addAll(Arrays.asList(TipoNacionalidade.values()));

        setListaTipoNacionalidade(lista);
    }

    public PessoaAcesso obterNovoPessoa() {
        PessoaAcesso novoPessoa = new PessoaAcesso();

        ServicoProtecao servicoProtecao = servicoAcessoPessoa.getServicoProtecao();
        servicoProtecao.setServicoAcessoPessoa(servicoAcessoPessoa);

        novoPessoa.setServicoProtecao(servicoProtecao);
        novoPessoa.setAtivo(Boolean.TRUE);

        return novoPessoa;
    }

    public PessoaAcesso obterPessoaParaEdicao() {
        servicoAcessoPessoa.getServicoProtecao().setServicoAcessoPessoa(servicoAcessoPessoa);

        PessoaAcesso pessoaParaEdicao = (PessoaAcesso) pessoaSelecionada.clone();
        pessoaParaEdicao.setServicoProtecao(servicoAcessoPessoa.getServicoProtecao());

        return (PessoaAcesso) pessoaParaEdicao.clone();
    }

    public void adicionarPessoa(PessoaAcesso pessoa) {
        servicoAcessoPessoa.adicionarPessoa(pessoa);
    }

    public void atualizarPessoa(PessoaAcesso pessoa) {
        servicoAcessoPessoa.removerPessoa(pessoaSelecionada);
        servicoAcessoPessoa.adicionarPessoa(pessoa);
    }

    public void excluirPessoa() {
        servicoAcessoPessoa.removerPessoa(pessoaSelecionada);
    }

    public void ativarCancelarPessoa() {
        PessoaAcesso pessoaAtualizado = pessoaSelecionada;
        pessoaSelecionada.setAtivo(!pessoaAtualizado.isAtivo());

        servicoAcessoPessoa.removerPessoa(pessoaSelecionada);
        servicoAcessoPessoa.adicionarPessoa(pessoaAtualizado);
    }
    
    public final void carregarCombos(){
        carregarListaTipoSolicitacaoTransporte();
        carregarTipoAcesso();
        carregarTipoCategoria();
        carregarTipoNacionalidade();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

}
