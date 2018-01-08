package br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.policiafederal;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoSolicitacaoTransito;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroFormularioPoliciaFederalAcessoPessoa;
import br.com.petrobras.sistam.common.valueobjects.PessoaAcessoVO;
import br.com.petrobras.sistam.common.valueobjects.PessoaPoliciaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioPessoaPoliciaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioVisitaAEmbarcacaoPoliciaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.VisitaEmbarcacaoVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;

public class AcessoPessoaPoliciaFederalModel extends BasePresentationModel<SistamService> {

    private FiltroFormularioPoliciaFederalAcessoPessoa filtro = new FiltroFormularioPoliciaFederalAcessoPessoa();
    private String empresaAviacao;
    private String numeroVoo;
    private Date dataVoo;
    private String destino;

    public AcessoPessoaPoliciaFederalModel(ServicoAcessoPessoa servicoAcessoPessoa) {
        filtro.setServicoAcessoPessoa(getService().buscarServicoAcessoPessoaPorId(servicoAcessoPessoa.getId()));
        AssyncInvoker.create(this, "carregarPessoas").schedule();
    }

    public List<RepresentanteLegal> getListaRepresentanteLegal() {
        List<RepresentanteLegal> lista = new ArrayList<RepresentanteLegal>();
        lista.add(0, null);
        lista.addAll(1, filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento().getAgencia().getRepresentantes());
        return lista;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public FiltroFormularioPoliciaFederalAcessoPessoa getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroFormularioPoliciaFederalAcessoPessoa filtro) {
        this.filtro = filtro;
    }

    public String getEmpresaAviacao() {
        return empresaAviacao;
    }

    public void setEmpresaAviacao(String empresaAviacao) {
        this.empresaAviacao = empresaAviacao;
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }

    public void setNumeroVoo(String numeroVoo) {
        this.numeroVoo = numeroVoo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getDataVoo() {
        return dataVoo;
    }

    public void setDataVoo(Date dataVoo) {
        this.dataVoo = dataVoo;
    }
//</editor-fold>

    public void validar() {

        
        if (filtro.isRequerimentoVisitaEmbarcacao()) {
            AssertUtils.assertExpression(isApenasUmaPessoaSelecionada(), ConstantesI18N.POLICIA_FEDERAL_SELECIONE_APENAS_UMA_PESSOA);                     
        }
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(pessoaSelecionadaGerar()).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.POLICIA_FEDERAL_SELECIONE_PELO_MENOS_UMA_PESSOA);
        pm.assertNotNull(filtro.getRepresentanteLegal()).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.INFORME_O_REPRESENTANTE);

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

    private boolean isApenasUmaPessoaSelecionada() {
        int cont = 0;
        for (PessoaAcessoVO pessoaVO : filtro.getPessoasVO()) {
            if (pessoaVO.isSelecionado()) {
                cont++;
            }
        }
        return cont == 1; 
    }

    //<editor-fold defaultstate="collapsed" desc="METODO PARA OBTER PAMENTROS ANTIGO">
   /* public Map<String, Object> obterParametros() {
     Map<String, Object> parametros = new HashMap<String, Object>();
     StringBuilder local = new StringBuilder();
     StringBuilder solicitacao = new StringBuilder();
     StringBuilder textoCategoria = new StringBuilder(filtro.getServicoAcessoPessoa().getTipoCategoria().getRelatorio());
     String agencia = filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento().getAgencia().toString();

     local.append(agencia).append(", ")
     .append(SistamDateUtils.formatDate(new Date(), "dd/MM/yyyy", null));
        
     solicitacao.append("À")
     .append("\n")
     .append("Polícia Federal em ").append(agencia)
     .append("\n")
     .append("At.: Ilmo. Sr Agente de Plantão.")
     .append("\n")
     .append("\n")
     .append("DA: PETROBRAS / AGÊNCIA MARÍTIMA ").append(agencia.toUpperCase())
     .append("\n")
     .append("\n");


     solicitacao.append("Assunto: ")
     .append(filtro.getServicoAcessoPessoa().getTipoSolicitacaoTransito())
     .append(" de ")
     .append(filtro.getServicoAcessoPessoa().getTipoAcesso())
     .append(" de ")
     .append(filtro.getServicoAcessoPessoa().getTipoCategoria())
     .append("\n")
     .append("\n");

     if (filtro.getServicoAcessoPessoa().getTipoCategoria().equals(TipoCategoria.PRESTADOR_SERVICO)) {
     textoCategoria.append(", da empresa ")
     .append(filtro.getServicoAcessoPessoa().getNomePrestadorServico())
     .append(", CNPJ ")
     .append(filtro.getServicoAcessoPessoa().getCnpjPrestadorServicoComMascara())
     .append(" para ")
     .append(filtro.getServicoAcessoPessoa().getPrestadorServicoAtividadeBordo());
     }

     solicitacao.append("Solicitamos ")
     .append(filtro.getServicoAcessoPessoa().getTipoSolicitacaoTransito().getRelatorio())
     .append(" o acesso à faixa portuária do Terminal Marítimo e/ou cais comercial de cargas secas para ")
     .append(filtro.getServicoAcessoPessoa().getTipoAcesso().getRelatorio())
     .append(" do(s) ")
     .append(textoCategoria.toString())
     .append(", abaixo relacionado(s), no navio ")
     .append(filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento().getEmbarcacao().getNomeCompleto()).append(".");

     parametros.put("SERVICO_ACESSO_PESSOA", filtro.getServicoAcessoPessoa());
     parametros.put("TIPO_VISITANTE", filtro.getServicoAcessoPessoa().isTipoCategoriaVisitante());
     parametros.put("LOCAL", local.toString());
     parametros.put("SOLICITACAO", solicitacao.toString());
     parametros.put("REPRESENTANTE", filtro.getRepresentanteLegal().getNome());
     return parametros;
     }*/
    /*private List<PessoaAcessoVO> obterPessoasSelecionadas() {
     List<PessoaAcessoVO> lista = new ArrayList<PessoaAcessoVO>();

     for (PessoaAcessoVO pess : filtro.getPessoasVO()) {
     if (pess.isSelecionado()) {
     lista.add(pess);
     }
     }
     return lista;
     }*/
    //</editor-fold>
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
        TimeZone zone = TimeZone.getTimeZone(filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento().getAgencia().getTimezone());

        Map<String, Object> parametros = new HashMap<String, Object>();
        String data_solicitacao = SistamDateUtils.formatDateByExtensive(filtro.getServicoAcessoPessoa().getServicoProtecao().getDataExecucao(), null);
        String nome_porto = filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento().getPorto().getNomeCompleto();
        String cnpjAgencia = filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento().getAgencia().getCnpjComMascara();

        String empresa = empresaAviacao != null ? empresaAviacao : "";
        String numero = numeroVoo != null ? numeroVoo : "";
        String data = SistamDateUtils.formatDate(this.dataVoo, SistamDateUtils.DATE_PATTERN, zone);
        String hora = SistamDateUtils.formatDate(this.dataVoo, SistamDateUtils.HOUR_PATTERN, zone);
        String destinoVoo = destino != null ? destino : "";
        String servico = filtro.getServicoAcessoPessoa().getPrestadorServicoAtividadeBordo();

        parametros.put("CNPJ", cnpjAgencia != null ? cnpjAgencia : "");
        parametros.put("NOME_DO_NAVIO", filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento().getEmbarcacao().getNomeCompleto());
        parametros.put("PORTO_NOME", nome_porto != null ? nome_porto : "-");
        parametros.put("DATA_SOLICITACAO", !"".equals(data_solicitacao) ? data_solicitacao : " _____ de _____________ de _____ ");
        parametros.put("REPRESENTANTE_CPF", filtro.getRepresentanteLegal().getCpfComMascara() != null ? filtro.getRepresentanteLegal().getCpfComMascara() : "");
        parametros.put("SERVICO", servico != null ? servico : "Sem descrição do(s) serviços");
        parametros.put("AGENCIA", filtro.getServicoAcessoPessoa().getServicoProtecao().getAgenciamento().getAgencia().getNome());
        parametros.put("TIPO_SOLICITACAO_TRANSITO", isTipoSolicitacaoTransitoAutorizacao());
        
        parametros.put("CATEGORIA", obterCategoriaFormatada());

        if (filtro.isRequerimentoDesembarque()) {
            parametros.put("EMPRESA_AVIACAO", empresa);
            parametros.put("NUMERO_VOO", numero);
            parametros.put("DATA_VOO", data != null ? data : " ");
            parametros.put("HORA_VOO", hora != null ? hora : " ");
            parametros.put("DESTINO", destinoVoo);

        } else if (filtro.isRequerimentoAcessoEmbarcacao()) {
            parametros.put("EMPRESA_NOME", filtro.getServicoAcessoPessoa().getNomePrestadorServico() != null ? filtro.getServicoAcessoPessoa().getNomePrestadorServico() : " ");
            parametros.put("EMPRESA_CNPJ", filtro.getServicoAcessoPessoa().getCnpjPrestadorServicoComMascara() != null ? filtro.getServicoAcessoPessoa().getCnpjPrestadorServicoComMascara() : " ");
        }

        if (filtro.isRequerimentoVisitaEmbarcacao()) {
            for (PessoaAcessoVO pessoaFiltro : filtro.getPessoasVO()) {
                if (pessoaFiltro.isSelecionado()) {

                    String nomeVisitado = pessoaFiltro.getPessoa().getNome() != null ? pessoaFiltro.getPessoa().getNome() : "";
                    String documentoVisitado = pessoaFiltro.getPessoa().getDocumento() != null ? pessoaFiltro.getPessoa().getTipoDocumentoIdentificacao() : "CPF";
                    String numeroDocumentoVisitado = pessoaFiltro.getPessoa().getDocumento() != null ? pessoaFiltro.getPessoa().getDocumento() : pessoaFiltro.getPessoa().getCpfComMascara();

                    parametros.put("NOME_VISITADO", nomeVisitado);
                    parametros.put("DOCUMENTO_VISITADO", documentoVisitado);
                    parametros.put("NUMERO_DOCUMENTO_VISITADO", numeroDocumentoVisitado);
                }
            }
        }

        return parametros;
    }

    private String isTipoSolicitacaoTransitoAutorizacao() {
        if (TipoSolicitacaoTransito.AUTORIZACAO.equals(filtro.getServicoAcessoPessoa().getTipoSolicitacaoTransito())) {
            return "autorizar";
        }
        return "cancelar";
    }

    private String obterCategoriaFormatada() {
        String categoriaFormatada = "";
        
        if (TipoCategoria.TRIPULANTES.equals(filtro.getServicoAcessoPessoa().getTipoCategoria())){
            categoriaFormatada = "TRIPULANTE(S)";
        } else if (TipoCategoria.PASSAGEIROS.equals(filtro.getServicoAcessoPessoa().getTipoCategoria())){
            categoriaFormatada = "PASSAGEIRO(S)";
        } else if (TipoCategoria.VISITANTES.equals(filtro.getServicoAcessoPessoa().getTipoCategoria())){
            categoriaFormatada = "VISITANTE(S)";
        } else if (TipoCategoria.PRESTADOR_SERVICO.equals(filtro.getServicoAcessoPessoa().getTipoCategoria())){
            categoriaFormatada = "PRESTADOR(ES) DE SERVIÇO";
        }
        return categoriaFormatada;
    }    

private List<RelatorioPessoaPoliciaFederalVO> obterPessoasSelecionadas() {
        List<RelatorioPessoaPoliciaFederalVO> listaRelatorioVO = new ArrayList<RelatorioPessoaPoliciaFederalVO>();
        RelatorioPessoaPoliciaFederalVO relatorioVO = new RelatorioPessoaPoliciaFederalVO();
        List<PessoaPoliciaFederalVO> listaPessoaRelVO = new ArrayList<PessoaPoliciaFederalVO>();

        int linha = 1;
        int pagina = 0;
        int registoPorPagina = 0;

        if (filtro.isRequerimentoEmbarque() || filtro.isRequerimentoAcessoEmbarcacao()) {
            registoPorPagina = 16;
        } else if (filtro.isRequerimentoDesembarque()) {
            registoPorPagina = 9;
        }

        for (PessoaAcessoVO pessoaFiltro : filtro.getPessoasVO()) {
            if (linha > registoPorPagina) {
                linha = 1;
                pagina++;
                relatorioVO.setPagina(pagina);
                relatorioVO.setPessoaPoliciaFederalVOs(listaPessoaRelVO);
                listaRelatorioVO.add(relatorioVO);
                listaPessoaRelVO = new ArrayList<PessoaPoliciaFederalVO>();
            }

            if (pessoaFiltro.isSelecionado()) {
                PessoaPoliciaFederalVO pessoaPoliciaFederal = new PessoaPoliciaFederalVO();
                pessoaPoliciaFederal.setNumeracao(linha);
                pessoaPoliciaFederal.setNome(pessoaFiltro.getPessoa().getNome());
                pessoaPoliciaFederal.setTipoDocumento(pessoaFiltro.getPessoa().getDocumento() != null ? pessoaFiltro.getPessoa().getTipoDocumentoIdentificacao() : "CPF");
                pessoaPoliciaFederal.setNumeroDocumento(pessoaFiltro.getPessoa().getDocumento() != null ? pessoaFiltro.getPessoa().getDocumento() : pessoaFiltro.getPessoa().getCpfComMascara());
                listaPessoaRelVO.add(pessoaPoliciaFederal);
                linha++;
            }
        }

        if (!listaPessoaRelVO.isEmpty() && (listaPessoaRelVO.size() % registoPorPagina >= 0)) {
            if (listaPessoaRelVO.size() < registoPorPagina) {
                for (int i = listaPessoaRelVO.size() + 1; i <= registoPorPagina; i++) {
                    PessoaPoliciaFederalVO pess = new PessoaPoliciaFederalVO(i);
                    listaPessoaRelVO.add(pess);
                }
            }
            pagina++;
            relatorioVO = new RelatorioPessoaPoliciaFederalVO();
            relatorioVO.setPagina(pagina);
            relatorioVO.setPessoaPoliciaFederalVOs(listaPessoaRelVO);
            listaRelatorioVO.add(relatorioVO);
        }
        return listaRelatorioVO;
    }

    private List<RelatorioVisitaAEmbarcacaoPoliciaFederalVO> obterDadosVisitantesParaRelatorio() {

        List<RelatorioVisitaAEmbarcacaoPoliciaFederalVO> relatorioVisita = new ArrayList<RelatorioVisitaAEmbarcacaoPoliciaFederalVO>();
        RelatorioVisitaAEmbarcacaoPoliciaFederalVO relatorioVO = new RelatorioVisitaAEmbarcacaoPoliciaFederalVO();
        List<VisitaEmbarcacaoVO> listaVisitantes = new ArrayList<VisitaEmbarcacaoVO>();

        int linha = 1;
        int pagina = 0;
        int registoPorPagina = RelatorioVisitaAEmbarcacaoPoliciaFederalVO.MAXIMO_FUNCIONARIOS_POR_PAGINA;

        for (VisitaEmbarcacaoVO filtroVisitante : filtro.getVisitanteVOs()) {
            if (linha > registoPorPagina) {
                linha = 1;
                pagina++;
                relatorioVO.setPagina(pagina);
                relatorioVO.setVisitantes(listaVisitantes);
                relatorioVisita.add(relatorioVO);
                listaVisitantes = new ArrayList<VisitaEmbarcacaoVO>();
            }

            VisitaEmbarcacaoVO visita = new VisitaEmbarcacaoVO();
            visita.setNumeracao(linha);
            visita.setNome(filtroVisitante.getNome());
            visita.setParentesco(filtroVisitante.getParentesco());
            visita.setDataNascimento(filtroVisitante.getDataNascimento());
            visita.setTipoDocumento(filtroVisitante.getTipoDocumento());
            visita.setNumeroDocumento(filtroVisitante.getNumeroDocumento());
            listaVisitantes.add(visita);
            linha++;
        }

        if ((listaVisitantes.isEmpty() || !listaVisitantes.isEmpty()) && (listaVisitantes.size() % registoPorPagina >= 0)) {
            if (listaVisitantes.size() < registoPorPagina) {
                for (int i = listaVisitantes.size() + 1; i <= registoPorPagina; i++) {
                    VisitaEmbarcacaoVO visitaAdd = new VisitaEmbarcacaoVO(i);
                    listaVisitantes.add(visitaAdd);
                }
            }

            pagina++;
            relatorioVO = new RelatorioVisitaAEmbarcacaoPoliciaFederalVO();
            relatorioVO.setPagina(pagina);
            relatorioVO.setVisitantes(listaVisitantes);
            relatorioVisita.add(relatorioVO);
        }

        return relatorioVisita;
    }

    public void gerarFormulario() {

        if (filtro.isRequerimentoEmbarque()) {
            RelatorioUtil.abrirFormularioSolicitacaoAcessoPessoaPoliciaFederalRequerimentoEmbarque(obterPessoasSelecionadas(), obterParametros());
            AssyncInvoker.create(getService(), "alterarPessoaStatusFormularioGeradoPolicia", filtro.getPessoasVO()).schedule();

        } else if (filtro.isRequerimentoDesembarque()) {
            RelatorioUtil.abrirFormularioSolicitacaoAcessoPessoaPoliciaFederalRequerimentoDesembarque(obterPessoasSelecionadas(), obterParametros());
            AssyncInvoker.create(getService(), "alterarPessoaStatusFormularioGeradoPolicia", filtro.getPessoasVO()).schedule();

        } else if (filtro.isRequerimentoAcessoEmbarcacao()) {
            RelatorioUtil.abrirFormularioSolicitacaoAcessoPessoaPoliciaFederalRequerimentoAcessoEmbarcacao(obterPessoasSelecionadas(), obterParametros());
            AssyncInvoker.create(getService(), "alterarPessoaStatusFormularioGeradoPolicia", filtro.getPessoasVO()).schedule();

        } else if (filtro.isRequerimentoVisitaEmbarcacao()) {
            RelatorioUtil.abrirFormularioSolicitacaoAcessoPessoaPoliciaFederalRequerimentoVisitaEmbarcacao(obterDadosVisitantesParaRelatorio(), obterParametros());
            AssyncInvoker.create(getService(), "alterarPessoaStatusFormularioGeradoPolicia", filtro.getPessoasVO()).schedule();
        }
//        RelatorioUtil.abrirFormularioSolicitacaoAcessoPessoaPoliciaFederal(obterPessoasSelecionadas(), obterParametros());
//        AssyncInvoker.create(getService(), "alterarPessoaStatusFormularioGeradoPolicia", filtro.getPessoasVO()).schedule();
    }

    public void adiconarVisitante() {
        SistamPendencyManager pm = new SistamPendencyManager();
        VisitaEmbarcacaoVO visitante = filtro.getVisitante();
        pm.assertThat(StringUtils.isNotBlank(visitante.getNome())).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.RELATORIO_POLICIA_FEDERAL_NOME_VISITANTE_OBRIGATORIO);
        pm.assertThat(StringUtils.isNotBlank(visitante.getParentesco())).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.RELATORIO_POLICIA_FEDERAL_PARENTESCO_VISITANTE_OBRIGATORIO);
        pm.assertNotNull(visitante.getDataNascimento()).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.RELATORIO_POLICIA_FEDERAL_DATA_NASCIMENTO_VISITANTE_OBRIGATORIO);
        pm.assertThat(StringUtils.isNotBlank(visitante.getTipoDocumento())).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.RELATORIO_POLICIA_FEDERAL_TIPO_DOCUMENTO_VISITANTE_OBRIGATORIO);
        pm.assertThat(StringUtils.isNotBlank(visitante.getNumeroDocumento())).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.RELATORIO_POLICIA_FEDERAL_NUMERO_DOCUMENTO_VISITANTE_OBRIGATORIO);
        pm.verifyAll();

        List<VisitaEmbarcacaoVO> visitantes = new ArrayList<VisitaEmbarcacaoVO>(filtro.getVisitanteVOs());
        visitantes.add(filtro.getVisitante());
        filtro.setVisitanteVOs(visitantes);
        filtro.setVisitante(new VisitaEmbarcacaoVO());
        firePropertyChange("habilitarAdiconarVisitante", null, null);

    }

    public void excluirVisitante() {
        VisitaEmbarcacaoVO visitante = filtro.getVisitanteSelecionado();
        List<VisitaEmbarcacaoVO> visitantes = new ArrayList<VisitaEmbarcacaoVO>(filtro.getVisitanteVOs());
        visitantes.remove(visitante);
        filtro.setVisitanteVOs(visitantes);
        filtro.setVisitanteSelecionado(null);
        firePropertyChange("habilitarAdiconarVisitante", null, null);
    }
}
