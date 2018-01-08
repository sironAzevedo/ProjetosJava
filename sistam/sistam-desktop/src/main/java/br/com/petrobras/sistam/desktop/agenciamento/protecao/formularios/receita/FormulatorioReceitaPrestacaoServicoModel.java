package br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.receita;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroFormulatorioReceitaPrestacaoServico;
import br.com.petrobras.sistam.common.valueobjects.FuncionarioVO;
import br.com.petrobras.sistam.common.valueobjects.PessoaAcessoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioReceitaPrestacaoServicoVO;
import br.com.petrobras.sistam.common.valueobjects.VeiculoVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class FormulatorioReceitaPrestacaoServicoModel extends BasePresentationModel<SistamService> {

    private FiltroFormulatorioReceitaPrestacaoServico filtro = new FiltroFormulatorioReceitaPrestacaoServico();

    public FiltroFormulatorioReceitaPrestacaoServico getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroFormulatorioReceitaPrestacaoServico filtro) {
        this.filtro = filtro;
        firePropertyChange("filtro", null, null);
    }

    public FormulatorioReceitaPrestacaoServicoModel(ServicoAcessoPessoa servicoAcessoPessoa) {
        filtro.setServicoAcessoPessoa(getService().buscarServicoAcessoPessoaPorId(servicoAcessoPessoa.getId()));
        AssyncInvoker.create(this, "carregarPessoas").schedule();
    }

    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(pessoaSelecionadaGerar()).orRegister(TipoExcecao.RECEITA, ConstantesI18N.RECEITA_FEDERAL_SELECIONE_PELO_MENOS_UMA_PESSOA);
        pm.verifyAll();
    }

    private boolean pessoaSelecionadaGerar() {
        for (PessoaAcessoVO pessoa : filtro.getPessoas()) {
            if (pessoa.isSelecionado()) {
                return true;
            }
        }
        return false;
    }

    public void carregarPessoas() {
        List<PessoaAcessoVO> lista = new ArrayList<PessoaAcessoVO>();
        for (PessoaAcesso pessoa : filtro.getServicoAcessoPessoa().getPessoasAsList()) {
            if (pessoa.isAtivo()) {
                PessoaAcessoVO pessoaVO = new PessoaAcessoVO();
                pessoaVO.setPessoa(pessoa);
                pessoaVO.setSelecionado(false);
                lista.add(pessoaVO);
            }
        }
        filtro.setPessoas(lista);
    }

    public Map<String, Object> obterParametros() {
        Map<String, Object> parametros = new HashMap<String, Object>();
//        parametros.put("SERVICO_ACESSO_PESSOA", filtro.getServicoAcessoPessoa());
        return parametros;
    }

    public void gerarFormulario() {
        RelatorioUtil.abrirRelatorioReceitaFederalPrestacaoServico(obterDadosRelatorio(), obterParametros());
        AssyncInvoker.create(getService(), "alterarPessoaStatusFormularioGeradoPrestacaoServico", filtro.getPessoas()).schedule();
    }

    private List<RelatorioReceitaPrestacaoServicoVO> obterDadosRelatorio() {
        int linha = 1;
        int registoPorPagina = RelatorioReceitaPrestacaoServicoVO.MAXIMO_FUNCIONARIOS_POR_PAGINA;

        List<RelatorioReceitaPrestacaoServicoVO> listaRelatorio = new ArrayList<RelatorioReceitaPrestacaoServicoVO>();

        RelatorioReceitaPrestacaoServicoVO relatorio = novoRelatorio(filtro);
        listaRelatorio.add(relatorio);

        for (PessoaAcessoVO pessoaFiltro : filtro.getPessoas()) {
            if (pessoaFiltro.isSelecionado()) {
                if (linha > registoPorPagina) {
                    linha = 1;
                    relatorio = (RelatorioReceitaPrestacaoServicoVO) relatorio.clone();
                    listaRelatorio.add(relatorio);
                }
                relatorio.getFuncionarios().add(novoFuncionario(pessoaFiltro, linha));
                linha++;
            }
        }

        List<FuncionarioVO> funcionarios = relatorio.getFuncionarios();
        if (!funcionarios.isEmpty() && (funcionarios.size() % registoPorPagina >= 0)) {
            if (funcionarios.size() < registoPorPagina) {
                for (int i = funcionarios.size() + 1; i <= registoPorPagina; i++) {
                    FuncionarioVO funcionario = new FuncionarioVO();
                    funcionario.setNumeracao(linha++);
                    funcionarios.add(funcionario);
                }
            }
        }

        return listaRelatorio;
    }

    public FuncionarioVO novoFuncionario(final PessoaAcessoVO pessoaFiltro, final int linha) {
        PessoaAcesso pessoa = pessoaFiltro.getPessoa();
        FuncionarioVO funcionario = new FuncionarioVO(pessoa.getNome(), pessoa.getDocumento(), pessoa.getCpfComMascara());
        funcionario.setNumeracao(linha);
        return funcionario;
    }

    public RelatorioReceitaPrestacaoServicoVO novoRelatorio(final FiltroFormulatorioReceitaPrestacaoServico filtro) {
        ServicoAcessoPessoa servicoAcessoPessoa = filtro.getServicoAcessoPessoa();
        ServicoProtecao servicoProtecao = servicoAcessoPessoa.getServicoProtecao();
        Agenciamento agenciamento = servicoProtecao.getAgenciamento();

        RelatorioReceitaPrestacaoServicoVO relatorio = new RelatorioReceitaPrestacaoServicoVO();
        relatorio.setNumeroDocumento(filtro.getNumeroDocumento() == null ? "" : filtro.getNumeroDocumento());
        if (StringUtils.isNotBlank(servicoAcessoPessoa.getCnpjPrestadorServico())) {
            relatorio.setNomePrestador(servicoAcessoPessoa.getNomePrestadorServico());
            relatorio.setCnpjPrestador(servicoAcessoPessoa.getCnpjPrestadorServicoComMascara());
            relatorio.setTelefonePrestador(servicoAcessoPessoa.getTelefonePrestadorServico());
        }
        relatorio.setDescricao(filtro.getDescricaoOperacao());
        relatorio.setNavio(agenciamento.getEmbarcacao().getNomeCompleto());
        relatorio.setEscala(agenciamento.getEscalas());
        relatorio.setNumeroTermo(filtro.getNumeroTermoResponsabilidade());
        relatorio.setLancha(filtro.getLancha());
        relatorio.setPeriodoInicio(filtro.getPeriodoInicio());
        relatorio.setPeriodoFim(filtro.getPeriodoFim());
        relatorio.setObservacoes(servicoProtecao.getObservacao());
        relatorio.setVeiculos(filtro.getVeiculos());
        return relatorio;
    }

    public void adicionarVeiculo() {
        SistamPendencyManager pm = new SistamPendencyManager();
        VeiculoVO veiculo = filtro.getVeiculo();
        pm.assertThat(StringUtils.isNotBlank(veiculo.getModelo())).orRegister(TipoExcecao.VEICULO, ConstantesI18N.RECEITA_FEDERAL_MODELO_VEICULO_OBRIGATORIO);
        pm.assertThat(StringUtils.isNotBlank(veiculo.getPlaca())).orRegister(TipoExcecao.VEICULO, ConstantesI18N.RECEITA_FEDERAL_PLACA_VEICULO_OBRIGATORIO);
        pm.verifyAll();

        List<VeiculoVO> veiculos = new ArrayList<VeiculoVO>(filtro.getVeiculos());
        veiculos.add(filtro.getVeiculo());
        filtro.setVeiculos(veiculos);
        filtro.setVeiculo(new VeiculoVO());
        firePropertyChange("habilitarAdicionarVeiculo", null, null);
    }

    public void excluirVeiculo() {
        VeiculoVO veiculo = filtro.getVeiculoSelecionado();
        List<VeiculoVO> veiculos = new ArrayList<VeiculoVO>(filtro.getVeiculos());
        veiculos.remove(veiculo);
        filtro.setVeiculos(veiculos);
        filtro.setVeiculoSelecionado(null);
        firePropertyChange("habilitarAdicionarVeiculo", null, null);
    }

    public boolean isHabilitarAdicionarVeiculo() {
        return filtro.getVeiculos().size() < 2;
    }
}
