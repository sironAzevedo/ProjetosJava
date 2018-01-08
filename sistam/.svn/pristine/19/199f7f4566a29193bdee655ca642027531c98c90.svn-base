package br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.receita;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroFormularioReceitaFederalAcessoPessoa;
import br.com.petrobras.sistam.common.valueobjects.PessoaReceitaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.PessoaAcessoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioReceitaFederalCabecalhoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioReceitaFederalVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcessoPessoaReceitaFederalModel extends BasePresentationModel<SistamService> {

    private FiltroFormularioReceitaFederalAcessoPessoa filtro = new FiltroFormularioReceitaFederalAcessoPessoa();

    public FiltroFormularioReceitaFederalAcessoPessoa getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroFormularioReceitaFederalAcessoPessoa filtro) {
        this.filtro = filtro;
        firePropertyChange("filtro", null, null);
    }

    public AcessoPessoaReceitaFederalModel(ServicoAcessoPessoa servicoAcessoPessoa) {
        filtro.setServicoAcessoPessoa(getService().buscarServicoAcessoPessoaPorId(servicoAcessoPessoa.getId()));
        AssyncInvoker.create(this, "carregarPessoas").schedule();
    }

    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(pessoaSelecionadaGerar()).orRegister(TipoExcecao.RECEITA_FEDERAL, ConstantesI18N.RECEITA_FEDERAL_SELECIONE_PELO_MENOS_UMA_PESSOA);
        pm.verifyAll();
    }

    private boolean pessoaSelecionadaGerar() {
        for (PessoaAcessoVO pessoaVO : filtro.getPessoasVO()) {
            if (pessoaVO.isSelecionado()) {
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
        filtro.setPessoasVO(lista);
    }

    public Map<String, Object> obterParametros() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("SERVICO_ACESSO_PESSOA", filtro.getServicoAcessoPessoa());
        Agenciamento agenciamento = filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento();
        List<RelatorioReceitaFederalCabecalhoVO> cabecalhos = new ArrayList<RelatorioReceitaFederalCabecalhoVO>();
        cabecalhos.add(new RelatorioReceitaFederalCabecalhoVO("NOME DA EMBARCAÇÃO: " + agenciamento.getEmbarcacao().getNomeCompleto(), "TIPO DE VIAGEM: " + (agenciamento.getAreaNavegacao() == null ? "" : agenciamento.getAreaNavegacao().getPorExtenso())));
        cabecalhos.add(new RelatorioReceitaFederalCabecalhoVO("BANDEIRA: " + (agenciamento.getEmbarcacao().getBandeira() == null ? "" : agenciamento.getEmbarcacao().getBandeira().getNomeCompleto()), "DATA DA CHEGADA: " + SistamDateUtils.formatDate(agenciamento.getEta(), SistamDateUtils.DATE_PATTERN, null)));
        cabecalhos.add(new RelatorioReceitaFederalCabecalhoVO("Nº DA ESCALA: " + (agenciamento.getNumeroEscalaMercante() == null ? "" : agenciamento.getNumeroEscalaMercante()), "PORTO DE ORIGEM: " + agenciamento.getPortoOrigem().getNomeCompleto()));
        parametros.put("CABECALHO", cabecalhos);
        return parametros;
    }

    public void gerarFormulario() {
        RelatorioUtil.abrirFormularioSolicitacaoAcessoPessoaReceitaFederal(obterPessoasSelecionadas(), obterParametros());
        AssyncInvoker.create(getService(), "alterarPessoaStatusFormularioGeradoReceita", filtro.getPessoasVO()).schedule();
    }

    private List<RelatorioReceitaFederalVO> obterPessoasSelecionadas() {
        List<RelatorioReceitaFederalVO> listaRelatorioVO = new ArrayList<RelatorioReceitaFederalVO>();
        RelatorioReceitaFederalVO relatorioVO = new RelatorioReceitaFederalVO();
        List<PessoaReceitaFederalVO> listaPessoaRelVO = new ArrayList<PessoaReceitaFederalVO>();

        int linha = 1;
        int pagina = 0;
        int registoPorPagina = 6;


        for (PessoaAcessoVO pessoaFiltro : filtro.getPessoasVO()) {
            if (linha > registoPorPagina) {
                linha = 1;
                pagina++;
                relatorioVO.setPagina(pagina);
                relatorioVO.setPessoaReceitaFederalVOs(listaPessoaRelVO);

                listaRelatorioVO.add(relatorioVO);
                listaPessoaRelVO = new ArrayList<PessoaReceitaFederalVO>();

            }
            if (pessoaFiltro.isSelecionado()) {
                PessoaReceitaFederalVO pessoa = new PessoaReceitaFederalVO();
                pessoa.setNumeracao(linha);
                pessoa.setNome(pessoaFiltro.getPessoa().getNome());
                pessoa.setDocumento(pessoaFiltro.getPessoa().getCpf() != null ? pessoaFiltro.getPessoa().getCpfComMascara() : pessoaFiltro.getPessoa().getDocumento());
                pessoa.setNacionalidade(pessoaFiltro.getPessoa().getNacionalidade());
                pessoa.setVolume(pessoaFiltro.getPessoa().getVolume() != null ? pessoaFiltro.getPessoa().getVolume().toString() : "");
                pessoa.setBagagem(pessoaFiltro.getPessoa().getBagagem());
                listaPessoaRelVO.add(pessoa);
                linha++;
            }
        }
        
        if (!listaPessoaRelVO.isEmpty() && (listaPessoaRelVO.size() % registoPorPagina >= 0)) {
            if (listaPessoaRelVO.size() < registoPorPagina) {
                for (int i = listaPessoaRelVO.size() + 1; i <= registoPorPagina; i++) {
                    PessoaReceitaFederalVO pess = new PessoaReceitaFederalVO(i);
                    listaPessoaRelVO.add(pess);
                }
            }
            pagina++;
            relatorioVO = new RelatorioReceitaFederalVO();
            relatorioVO.setPagina(pagina);
            relatorioVO.setPessoaReceitaFederalVOs(listaPessoaRelVO);
            listaRelatorioVO.add(relatorioVO);
        }
        return listaRelatorioVO;
    }
}
