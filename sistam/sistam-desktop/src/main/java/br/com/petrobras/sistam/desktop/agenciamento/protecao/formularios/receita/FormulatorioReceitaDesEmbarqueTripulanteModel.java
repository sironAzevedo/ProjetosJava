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
import br.com.petrobras.sistam.common.valueobjects.FiltroFormulatorioReceitaDesEmbarqueTripulante;
import br.com.petrobras.sistam.common.valueobjects.PessoaAcessoVO;
import br.com.petrobras.sistam.common.valueobjects.PessoaTripulateVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioReceitaDesEmbarqueTripulanteVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class FormulatorioReceitaDesEmbarqueTripulanteModel extends BasePresentationModel<SistamService> {

    private FiltroFormulatorioReceitaDesEmbarqueTripulante filtro = new FiltroFormulatorioReceitaDesEmbarqueTripulante();

    public FiltroFormulatorioReceitaDesEmbarqueTripulante getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroFormulatorioReceitaDesEmbarqueTripulante filtro) {
        this.filtro = filtro;
        firePropertyChange("filtro", null, null);
    }

    public FormulatorioReceitaDesEmbarqueTripulanteModel(ServicoAcessoPessoa servicoAcessoPessoa) {
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
        RelatorioUtil.abrirRelatorioReceitaFederalDesEmbarqueTripulates(obterDadosRelatorio(), obterParametros());
        AssyncInvoker.create(getService(), "alterarPessoaStatusFormularioGeradoDesEmbarqueTripulantes", filtro.getPessoas()).schedule();
    }

    private List<RelatorioReceitaDesEmbarqueTripulanteVO> obterDadosRelatorio() {
        int linha = 1;
        int registoPorPagina = RelatorioReceitaDesEmbarqueTripulanteVO.QTD_TRIPULANTES_POR_PAGINA;

        List<RelatorioReceitaDesEmbarqueTripulanteVO> listaRelatorio = new ArrayList<RelatorioReceitaDesEmbarqueTripulanteVO>();

        RelatorioReceitaDesEmbarqueTripulanteVO relatorio = novoRelatorio(filtro);
        listaRelatorio.add(relatorio);

        for (PessoaAcessoVO pessoaFiltro : filtro.getPessoas()) {
            if (pessoaFiltro.isSelecionado()) {
                if (linha > registoPorPagina) {
                    linha = 1;
                    relatorio = (RelatorioReceitaDesEmbarqueTripulanteVO) relatorio.clone();
                    listaRelatorio.add(relatorio);
                }

                PessoaTripulateVO pessoa = novoTripulante(pessoaFiltro, linha);
                relatorio.getTripulantes().add(pessoa);
                linha++;
            }
        }

        List<PessoaTripulateVO> tripulantes = relatorio.getTripulantes();
        if (!tripulantes.isEmpty() && (tripulantes.size() % registoPorPagina >= 0)) {
            if (tripulantes.size() < registoPorPagina) {
                for (int i = tripulantes.size() + 1; i <= registoPorPagina; i++) {
                    PessoaTripulateVO pessoa = PessoaTripulateVO.novoTripulante(null);
                    pessoa.setNumeracao(linha++);
                    tripulantes.add(pessoa);
                }
            }
        }

        return listaRelatorio;
    }

    public PessoaTripulateVO novoTripulante(final PessoaAcessoVO pessoaFiltro, final int linha) {
        PessoaTripulateVO pessoa = PessoaTripulateVO.novoTripulante(pessoaFiltro.getPessoa().getId());
        pessoa.setNumeracao(linha);
        pessoa.setNome(pessoaFiltro.getPessoa().getNome());
        pessoa.setPassaporte(StringUtils.isNotBlank(pessoaFiltro.getPessoa().getCpf()) ? pessoaFiltro.getPessoa().getCpfComMascara() : pessoaFiltro.getPessoa().getDocumento());
        pessoa.setNacionalidade(pessoaFiltro.getPessoa().getNacionalidade());
        pessoa.setVolumeBagagem(pessoaFiltro.getPessoa().getVolume() != null ? pessoaFiltro.getPessoa().getVolume().toString() : "");
        return pessoa;
    }

    public RelatorioReceitaDesEmbarqueTripulanteVO novoRelatorio(final FiltroFormulatorioReceitaDesEmbarqueTripulante filtro) {
        ServicoAcessoPessoa servicoAcessoPessoa = filtro.getServicoAcessoPessoa();
        ServicoProtecao servicoProtecao = servicoAcessoPessoa.getServicoProtecao();
        Agenciamento agenciamento = servicoProtecao.getAgenciamento();

        RelatorioReceitaDesEmbarqueTripulanteVO relatorio = RelatorioReceitaDesEmbarqueTripulanteVO.novo(servicoAcessoPessoa.getTipoAcesso());
        relatorio.setDataHoraPrevista(filtro.getDataPrevista());
        relatorio.setEscala(agenciamento.getEscalas());
        relatorio.setLancha(filtro.getLancha());
        relatorio.setNumeroDocumento(filtro.getNumeroDocumento() == null ? "" : filtro.getNumeroDocumento());
        relatorio.setNavio(agenciamento.getEmbarcacao().getNomeCompleto());
        relatorio.setObservacoes(servicoProtecao.getObservacao());
        return relatorio;
    }
}
