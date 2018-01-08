package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoVeiculo;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroFormularioServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.valueobjects.RelatorioServicoSuprimentoTransitoCabecalhoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioServicoSuprimentoTransitoVO;
import br.com.petrobras.sistam.common.valueobjects.ServicoSuprimentoTransitoVO;
import br.com.petrobras.sistam.common.valueobjects.SuprimentoTransitoCondutorVO;
import br.com.petrobras.sistam.common.valueobjects.SuprimentoTransitoFornecedorVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public final class DetalheServicoProtecaoSuprimentoAosNaviosModel extends BasePresentationModel<SistamService> {

    private ServicoSuprimento servicoSuprimento;
    private ServicoSuprimentoTransito suprimentoTransitoSelecionado;
    private EmpresaProtecao prestadorServico;
    private List<EmpresaMaritima> empresasMaritimas;
    private List<Servico> servicos;
    private DetalheServicoProtecaoSuprimentoAosNaviosDialog dialog;
    private FiltroFormularioServicoSuprimentoTransito filtro = new FiltroFormularioServicoSuprimentoTransito();
    private boolean gravado;
    private boolean editar;

    public DetalheServicoProtecaoSuprimentoAosNaviosModel(ServicoSuprimento servicoSuprimento, boolean editar) {
        this.editar = editar;
        if (servicoSuprimento.getId() == null) {
            servicoSuprimento.getServicoProtecao().setDataExecucao(new Date());
            servicoSuprimento.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.SERVICO_SUPRIMENTO);
        } else {
            servicoSuprimento.getServicoProtecao().setNovo(false);
        }

        AssyncInvoker.create(this, "carregarSuprimentoTransito").schedule();

        setServicoSuprimento(servicoSuprimento);
        carregarCombos();

    }

    public void salvar() {
        servicoSuprimento = (ServicoSuprimento) getService().salvarServicoExecutado(servicoSuprimento);
        setGravado(Boolean.TRUE);
    }

    public TimeZone getTimeZone() {
        if (servicoSuprimento == null) {
            return null;
        }
        return TimeZone.getTimeZone(servicoSuprimento.getServicoProtecao().getAgenciamento().getAgencia().getTimezone());
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public ServicoSuprimento getServicoSuprimento() {
        return servicoSuprimento;
    }

    public void setServicoSuprimento(ServicoSuprimento servicoSuprimento) {
        this.servicoSuprimento = servicoSuprimento;
        this.servicoSuprimento.addPropertyChangeListener(this);
        firePropertyChange("servicoSuprimento", null, null);
    }

    public DetalheServicoProtecaoSuprimentoAosNaviosDialog getDialog() {
        return dialog;
    }

    public void setDialog(DetalheServicoProtecaoSuprimentoAosNaviosDialog dialog) {
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

    public EmpresaProtecao getPrestadorServico() {
        return prestadorServico;
    }

    public void setPrestadorServico(EmpresaProtecao prestadorServico) {
        this.prestadorServico = prestadorServico;
    }

    public ServicoSuprimentoTransito getSuprimentoTransitoSelecionado() {
        return suprimentoTransitoSelecionado;
    }

    public void setSuprimentoTransitoSelecionado(ServicoSuprimentoTransito suprimentoTransitoSelecionado) {
        this.suprimentoTransitoSelecionado = suprimentoTransitoSelecionado;
        firePropertyChange("suprimentoTransitoSelecionado", null, null);

    }

    public List<EmpresaMaritima> getEmpresasMaritimas() {
        return empresasMaritimas;
    }

    public void setEmpresasMaritimas(List<EmpresaMaritima> empresasMaritimas) {
        this.empresasMaritimas = empresasMaritimas;
        firePropertyChange("empresasMaritimas", null, null);
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
        firePropertyChange("servicos", null, null);
    }

    public FiltroFormularioServicoSuprimentoTransito getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroFormularioServicoSuprimentoTransito filtro) {
        this.filtro = filtro;
        firePropertyChange("filtro", null, null);
    }

    public String getServicoExecutado() {
        if (servicoSuprimento == null || servicoSuprimento.getServicoProtecao() == null || servicoSuprimento.getServicoProtecao().getTipoAtendimentoServico() == null) {
            return null;
        }

        return servicoSuprimento.getServicoProtecao().getTipoAtendimentoServico().getPorExtenso();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Solicitacao De Transito">
    public ServicoSuprimentoTransito obterNovaSolicitacaoDeTransito() {
        ServicoSuprimentoTransito suprimentoTransito = new ServicoSuprimentoTransito();

        ServicoProtecao servicoProtecao = servicoSuprimento.getServicoProtecao();
        servicoProtecao.setServicoSuprimentoAosNavios(servicoSuprimento);

        suprimentoTransito.setServicoProtecao(servicoProtecao);

        return suprimentoTransito;
    }

    public ServicoSuprimentoTransito obterSolicitacaoDeTransiroParaEdicao() {

        servicoSuprimento.getServicoProtecao().setServicoSuprimentoAosNavios(servicoSuprimento);

        ServicoSuprimentoTransito suprimentoTransitoParaEdicao = (ServicoSuprimentoTransito) suprimentoTransitoSelecionado.clone();
        suprimentoTransitoParaEdicao.setServicoProtecao(servicoSuprimento.getServicoProtecao());

        return (ServicoSuprimentoTransito) suprimentoTransitoParaEdicao.clone();
    }

    public void adicionarSolicitacaoDeTransito(ServicoSuprimentoTransito suprimentoTransito) {
        servicoSuprimento.adicionarSuprimentoTransito(suprimentoTransito);
    }

    public void editarSolicitacaoDeTransito(ServicoSuprimentoTransito suprimentoTransito) {
        servicoSuprimento.removerSuprimentoTransito(suprimentoTransitoSelecionado);
        servicoSuprimento.adicionarSuprimentoTransito(suprimentoTransito);
    }

    public void excluirSolicitacaoDeTransito() {
        if (suprimentoTransitoSelecionado.getId() != null) {
          AssyncInvoker.create(getService(), "excluirServicoSuprimentoTransito", suprimentoTransitoSelecionado).schedule();
          servicoSuprimento.removerSuprimentoTransito(suprimentoTransitoSelecionado);
        }
        else
          servicoSuprimento.removerSuprimentoTransito(suprimentoTransitoSelecionado);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Solicitacao De Transito Suprimento Empresa">
    public ServicoSuprimentoTransitoEmpresa obterNovaSolicitacaoTransitoSuprimentoEmpresa() {
        ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa = new ServicoSuprimentoTransitoEmpresa();




        return suprimentoTransitoEmpresa;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Solicitacao De Transito Pessoa">
    public PessoaAcesso obterNovoPessoa() {
        PessoaAcesso novoPessoa = new PessoaAcesso();

        ServicoProtecao servicoProtecao = servicoSuprimento.getServicoProtecao();
        servicoProtecao.setServicoSuprimentoAosNavios(servicoSuprimento);

        novoPessoa.setServicoProtecao(servicoProtecao);
        novoPessoa.setAtivo(Boolean.TRUE);

        return novoPessoa;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Formulario Suprimento">    
    public void validarFormulario() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(suprimentoTransitoSelecionadoGerar()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO, ConstantesI18N.SERVICO_SUPRIMENTO_SELECIONE_PELO_MENOS_SERVICO_SUPRIMENTO);
        pm.verifyAll();
    }

    public boolean suprimentoTransitoSelecionadoGerar() {

        return true;
    }

    public void carregarSuprimentoTransito() {
//        List<ServicoSuprimentoTransitoVO> lista = new ArrayList<ServicoSuprimentoTransitoVO>();
//        for (ServicoSuprimentoTransito suprimentoTransito : servicoSuprimento.getTransitos()) {
//            ServicoSuprimentoTransitoVO transitoVO = new ServicoSuprimentoTransitoVO();
//            transitoVO.setSuprimentoTransito(suprimentoTransito);
//            transitoVO.getSuprimentoTransito().isFormularioGeradoReceitaFederal();
//            lista.add(transitoVO);
//
//        }
    }

    public Map<String, Object> obterParametros() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("SUPRIMENTO_TRANSITO", suprimentoTransitoSelecionado);
        Agenciamento agenciamento = servicoSuprimento.getServicoProtecao().getAgenciamento();
        List<RelatorioServicoSuprimentoTransitoCabecalhoVO> cabecalhos = new ArrayList<RelatorioServicoSuprimentoTransitoCabecalhoVO>();
        cabecalhos.add(new RelatorioServicoSuprimentoTransitoCabecalhoVO("NOME DA EMBARCAÇÃO: " + agenciamento.getEmbarcacao().getNomeCompleto(), "TIPO DE VIAGEM: " + (agenciamento.getAreaNavegacao() == null ? "" : agenciamento.getAreaNavegacao().getPorExtenso())));
        cabecalhos.add(new RelatorioServicoSuprimentoTransitoCabecalhoVO("BANDEIRA: " + (agenciamento.getEmbarcacao().getBandeira() == null ? "" : agenciamento.getEmbarcacao().getBandeira().getNomeCompleto()), "DATA DA CHEGADA: " + SistamDateUtils.formatDate(agenciamento.getEta(), SistamDateUtils.DATE_HOUR_PATTERN, null)));
        cabecalhos.add(new RelatorioServicoSuprimentoTransitoCabecalhoVO("Nº DA ESCALA: " + (agenciamento.getNumeroEscalaMercante() == null ? "" : agenciamento.getNumeroEscalaMercante()), "PORTO DE ORIGEM: " + agenciamento.getPortoOrigem().getNomeCompleto()));
        parametros.put("CABECALHO", cabecalhos);
        return parametros;
    }

    public void gerarFormulario() {
        RelatorioUtil.abrirFormularioSolicitacaoServicoSuprimento(obterDadosParaOFormulario(), obterParametros());
        AssyncInvoker.create(getService(), "alterarSuprimentoTransitoStatusFormularioGerado", getSuprimentoTransitoSelecionado()).schedule();
        suprimentoTransitoSelecionado.setFormularioGeradoReceitaFederal(true);
    }

    private List<RelatorioServicoSuprimentoTransitoVO> obterDadosParaOFormulario() {

        RelatorioServicoSuprimentoTransitoVO relatorioTransitoVO = new RelatorioServicoSuprimentoTransitoVO();
        List<SuprimentoTransitoFornecedorVO> listaTransitoFornecedorVO = new ArrayList<SuprimentoTransitoFornecedorVO>();
        List<SuprimentoTransitoCondutorVO> listaTransitoCondutorVO = new ArrayList<SuprimentoTransitoCondutorVO>();


        int linha = 1;
        int pagina = 0;
        int registoPorPagina = 5;

        Map<Integer, RelatorioServicoSuprimentoTransitoVO> relServSupTransitoMap = new HashMap<Integer, RelatorioServicoSuprimentoTransitoVO>();
        for (ServicoSuprimentoTransitoEmpresa servicoSuprimentoTransitoEmpresa : suprimentoTransitoSelecionado.getTransitosEmpresas()) {

            if (linha > registoPorPagina) {
                linha = 1;
                pagina++;
                relatorioTransitoVO.setPagina(pagina);
                relatorioTransitoVO.setTransitoFornecedorVOs(listaTransitoFornecedorVO);

                if (!relServSupTransitoMap.containsKey(relatorioTransitoVO.getPagina())) {
                    relServSupTransitoMap.put(relatorioTransitoVO.getPagina(), relatorioTransitoVO);
                } else {
                    relServSupTransitoMap.get(pagina).setTransitoFornecedorVOs(relatorioTransitoVO.getTransitoFornecedorVOs());
                }

                listaTransitoFornecedorVO = new ArrayList<SuprimentoTransitoFornecedorVO>();
            }
            SuprimentoTransitoFornecedorVO fornecedor = new SuprimentoTransitoFornecedorVO();
            fornecedor.setNumeracao(linha);
            fornecedor.setNomePrestadorServico(servicoSuprimentoTransitoEmpresa.getNomePrestadorServico());
            fornecedor.setPesoBruto(servicoSuprimentoTransitoEmpresa.getValorPesoBruto());
            fornecedor.setVolume(servicoSuprimentoTransitoEmpresa.getQuantidadeVolume());
            fornecedor.setNotaFiscal(servicoSuprimentoTransitoEmpresa.getDescNotaFiscal());
            listaTransitoFornecedorVO.add(fornecedor);
            linha++;
        }

        if (!listaTransitoFornecedorVO.isEmpty() && (listaTransitoFornecedorVO.size() % registoPorPagina >= 0)) {
            if (listaTransitoFornecedorVO.size() + 1 < registoPorPagina) {
                for (int i = listaTransitoFornecedorVO.size() + 1; i <= registoPorPagina; i++) {
                    SuprimentoTransitoFornecedorVO transito = new SuprimentoTransitoFornecedorVO(i);
                    listaTransitoFornecedorVO.add(transito);
                }
            }
            pagina++;
            relatorioTransitoVO = new RelatorioServicoSuprimentoTransitoVO();
            relatorioTransitoVO.setPagina(pagina);
            relatorioTransitoVO.setTransitoFornecedorVOs(listaTransitoFornecedorVO);

            if (!relServSupTransitoMap.containsKey(relatorioTransitoVO.getPagina())) {
                relServSupTransitoMap.put(relatorioTransitoVO.getPagina(), relatorioTransitoVO);
            } else {
                relServSupTransitoMap.get(pagina).setTransitoFornecedorVOs(relatorioTransitoVO.getTransitoFornecedorVOs());
            }
        }

        linha = 1;
        pagina = 0;

        for (ServicoSuprimentoTransitoVeiculo servicoSuprimentoTransitoVeiculo : suprimentoTransitoSelecionado.getTransitosVeiculos()) {

            if (linha > registoPorPagina) {
                linha = 1;
                pagina++;
                relatorioTransitoVO.setPagina(pagina);
                relatorioTransitoVO.setTransitoCondutorVOs(listaTransitoCondutorVO);

                if (!relServSupTransitoMap.containsKey(relatorioTransitoVO.getPagina())) {
                    relServSupTransitoMap.put(relatorioTransitoVO.getPagina(), relatorioTransitoVO);
                } else {
                    relServSupTransitoMap.get(pagina).setTransitoCondutorVOs(relatorioTransitoVO.getTransitoCondutorVOs());
                }
                listaTransitoCondutorVO = new ArrayList<SuprimentoTransitoCondutorVO>();
            }

            SuprimentoTransitoCondutorVO condutor = new SuprimentoTransitoCondutorVO();
            condutor.setNumeracao(linha);
            condutor.setNomeCondutor(servicoSuprimentoTransitoVeiculo.getNomeCondutor());
            condutor.setCpfCondutor(servicoSuprimentoTransitoVeiculo.getCpfComMascara());
            condutor.setTipoVeiculo(servicoSuprimentoTransitoVeiculo.getTipoVeiculo().getPorExtenso());
            condutor.setPlacaVeiculo(servicoSuprimentoTransitoVeiculo.getPlacaVeiculo());
            listaTransitoCondutorVO.add(condutor);
            linha++;
        }

        if (!listaTransitoCondutorVO.isEmpty() && (listaTransitoCondutorVO.size() % registoPorPagina >= 0)) {
            if (listaTransitoCondutorVO.size() + 1 < registoPorPagina) {
                for (int i = listaTransitoCondutorVO.size() + 1; i <= registoPorPagina; i++) {
                    SuprimentoTransitoCondutorVO condutor = new SuprimentoTransitoCondutorVO(i);
                    listaTransitoCondutorVO.add(condutor);
                }
            }
            pagina++;
            relatorioTransitoVO = new RelatorioServicoSuprimentoTransitoVO();
            relatorioTransitoVO.setPagina(pagina);
            relatorioTransitoVO.setTransitoCondutorVOs(listaTransitoCondutorVO);

            if (!relServSupTransitoMap.containsKey(relatorioTransitoVO.getPagina())) {
                relServSupTransitoMap.put(relatorioTransitoVO.getPagina(), relatorioTransitoVO);
            } else {
                relServSupTransitoMap.get(pagina).setTransitoCondutorVOs(relatorioTransitoVO.getTransitoCondutorVOs());
            }
        }
        else {
            for (int i = 1; i <= registoPorPagina; i++) {
                SuprimentoTransitoCondutorVO condutor = new SuprimentoTransitoCondutorVO(i);
                listaTransitoCondutorVO.add(condutor);
            }
            pagina++;
            relatorioTransitoVO = new RelatorioServicoSuprimentoTransitoVO();
            relatorioTransitoVO.setPagina(pagina);
            relatorioTransitoVO.setTransitoCondutorVOs(listaTransitoCondutorVO);

            if (!relServSupTransitoMap.containsKey(relatorioTransitoVO.getPagina())) {
                relServSupTransitoMap.put(relatorioTransitoVO.getPagina(), relatorioTransitoVO);
            } else {
                relServSupTransitoMap.get(pagina).setTransitoCondutorVOs(relatorioTransitoVO.getTransitoCondutorVOs());
            }
        }
        return new ArrayList<RelatorioServicoSuprimentoTransitoVO>(relServSupTransitoMap.values());
    }

    //</editor-fold>
    
    public final void carregarCombos() {
        carregarEmpresasMaritimas();
        carregarListaServico();
    }

    private void carregarEmpresasMaritimas() {
        Agencia agencia = servicoSuprimento.getServicoProtecao().getAgenciamento().getAgencia();
        List<EmpresaMaritima> lista = getService().buscarEmpresasMaritimasPorAgenciaTipoServico(agencia, TipoServico.SUPRIMENTO);
        lista.add(0, null);

        setEmpresasMaritimas(lista);
    }

    public void carregarListaServico() {
        List<Servico> lista = new ArrayList<Servico>();

        if (servicoSuprimento.getEmpresaMaritima() != null) {
            lista = getService().buscarServicosPorEmpresaETipo(servicoSuprimento.getEmpresaMaritima(), TipoServico.SUPRIMENTO);
            lista.add(0, null);
        }
        setServicos(lista);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ServicoSuprimento.SERV_SUPRIMENTOS_EMPRESA_MARITIMA)) {
            carregarListaServico();
        }
    }
}
