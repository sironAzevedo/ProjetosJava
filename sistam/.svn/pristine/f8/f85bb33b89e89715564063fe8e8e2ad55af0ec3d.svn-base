package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.AgenciamentoSanitaria;
import br.com.petrobras.sistam.common.entity.AgenciamentoViagem;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.HashSet;
import java.util.List;

public final class AgenciamentoEmbarcacaoModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private String localizacaoAtual;
    private boolean emModoVisualizacao;

    public AgenciamentoEmbarcacaoModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
    }

    public String getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(String localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
        firePropertyChange("localizacaoAtual", null, null);
    }

    public boolean emModoVisualizacao() {
        return emModoVisualizacao;
    }

    public void setEmModoVisualizacao(boolean emModoVisualizacao) {
        this.emModoVisualizacao = emModoVisualizacao;
    }

    //</editor-fold>
    public void validaLiberacaoAnvisa() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getEmbarcacao().getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);

        validarDesinsetizacao(agenciamento, pm);
        //    validarSubstituicaoAguaLastro(agenciamento, pm); validacao retirada a pedido de Manaus
        validarTratamentoEfluentes(agenciamento, pm);

        pm.assertNotNull(agenciamento.getDataEstimadaSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_ETS);
        pm.assertNotNull(agenciamento.getEta()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_ETA);

        pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getComandanteEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_ENTRADA);
        pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getNacionalidadeEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_NACIONALIDADE_ENTRADA);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_PASSAGEIROS);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_TRIPULANTES);
        validarObitoABordo(agenciamento, pm);
        validarAcidente(agenciamento, pm);
        validarMedicamento(agenciamento, pm);
        validarCompartimentoMortandade(agenciamento, pm);

        pm.verifyAll();
    }

    public void validarTermoDeResponsabilidadeReceita() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotNull(agenciamento.getEmbarcacao().getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getNumeroEscalaMercante()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_ESCALA_MERCANTE);
        pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getComandanteEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_ENTRADA);
        pm.verifyAll();
    }

    public void salvar() {
        setAgenciamento(getService().salvarAgenciamento(getAgenciamento()));
        criarPendenciaAoSalvarAgenciamentoQuandoTipoPendenciaLongoCurso();
    }

    public void criarPendenciaAoSalvarAgenciamentoQuandoTipoPendenciaLongoCurso() {
        if ( (agenciamento.isTipoAreaNavegacaoChegadaLONGO_CURSO() || agenciamento.isTipoAreaNavegacaoSaidaLONGO_CURSO()) && (agenciamento.isTipoContatoTCP())) {
            List<Pendencia> listaPendencia = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.PAGAMENTO_FUNAPOL);
            if (listaPendencia.isEmpty()) {
                getService().criarPendencia(agenciamento, TipoPendencia.PAGAMENTO_FUNAPOL);
            }
        }
    }

    public void carregarAgenciamento() {
        Agenciamento agenciamentoAtualizado = getService().buscarAgenciamentoPorId(this.agenciamento.getId());
        setAgenciamento(agenciamentoAtualizado);
    }

    public void carregarAgenciamentoViagem() {
        AgenciamentoViagem agenciamentoViagem = getService().buscarAgenciamentoViagemPorId(agenciamento.getId());
        agenciamento.setAgenciementoViagem(agenciamentoViagem);
    }

    public void carregarAgenciamentoSanitaria() {
        AgenciamentoSanitaria agenciamentoSanitaria = getService().buscarAgenciamentoSanitariaPorId(agenciamento.getId());
        agenciamento.setAgenciementoSanitaria(agenciamentoSanitaria);
    }

    public void carregarOperacoes() {
        List<Operacao> operacoes = getService().buscarOperacoesPorAgenciamento(agenciamento);
        agenciamento.setOperacoes(new HashSet<Operacao>(operacoes));
    }

    public void carregarManobras() {
        List<Manobra> manobras = getService().buscarManobrasPorAgenciamento(agenciamento);
        agenciamento.setManobras(new HashSet<Manobra>(manobras));

        PontoAtracacao ponto = agenciamento.obterLocalizacaoAtual();
        if (ponto != null) {
            setLocalizacaoAtual(ponto.getNome());
        } else {
            setLocalizacaoAtual("");
        }
    }

    public void carregarTaxas() {
        List<Taxa> listaTaxas = getService().buscarTaxasPorAgenciamento(agenciamento);
        agenciamento.setTaxas(new HashSet<Taxa>(listaTaxas));
    }

    public void carregarDocumentos() {
        List<Documento> listaDocumento = getService().buscarDocumentosDoAgenciamento(agenciamento);
        agenciamento.setDocumentos(new HashSet<Documento>(listaDocumento));
    }

    public void carregarAnexos() {
        List<Anexo> listaAnexos = getService().buscarAnexosDoAgenciamento(agenciamento);
        agenciamento.setAnexos(new HashSet<Anexo>(listaAnexos));
    }

    public void carregarPendencias() {
        List<Pendencia> listaPendencias = getService().buscarPendenciasDoAgenciamento(agenciamento, null);
        agenciamento.setPendencias(new HashSet<Pendencia>(listaPendencias));
    }

    public void carregarVisitas() {
        List<Visita> listaVisitas = getService().buscarVisitasDoAgenciamento(agenciamento);
        agenciamento.setVisitas(new HashSet<Visita>(listaVisitas));
    }

    public Desvio obterDesvio() {
        Agenciamento agenciamentoParaDesvioDeRota = getService().buscarAgenciamentoParaDesvioDeRota(agenciamento.getId());
        Desvio desvio;

        if (agenciamentoParaDesvioDeRota.getDesvio() == null) {
            desvio = new Desvio();
            desvio.setAgenciamento(agenciamentoParaDesvioDeRota);
            desvio.setPortoDestinoAlterado(agenciamentoParaDesvioDeRota.getPortoDestino());
            desvio.setDestinoIntermediarioAlterado(agenciamentoParaDesvioDeRota.getDestinoIntermediario());
        } else {
            desvio = agenciamentoParaDesvioDeRota.getDesvio();
        }

        return desvio;
    }

    public CancelamentoAgenciamento obterCancelamento() {
        CancelamentoAgenciamento cancelamento;

        if (StatusEmbarcacao.CANCELADO.equals(agenciamento.getStatusEmbarcacao())) {
            cancelamento = getService().buscarCancelamentoDoAgenciamento(agenciamento.getId());
        } else {
            cancelamento = new CancelamentoAgenciamento();
            cancelamento.setAgenciamento(agenciamento);
        }
        return cancelamento;
    }

    public void verificarPossibilidadeParaDesvioDeRota() {
        AssertUtils.assertNotNull(agenciamento.getPortoDestino(), ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_DESTINO);
        AssertUtils.assertExpression(StatusEmbarcacao.SAIDO.equals(agenciamento.getStatusEmbarcacao())
                || StatusEmbarcacao.DESVIADO.equals(agenciamento.getStatusEmbarcacao()),
                ConstantesI18N.AGENCIAMENTO_DESVIO_STATUS_EMBACARCAO_SAIDO_OU_DESVIADO);
    }

    public void verificarPossibilidadeParaCancelamento() {
        if (!getService().validarPermissao(agenciamento.getAgencia(), RecursoCA.CANCELAR_AGENCIAMENTO_ADM)) {
            AssertUtils.assertExpression(StatusEmbarcacao.ESPERADO.equals(agenciamento.getStatusEmbarcacao())
                    || StatusEmbarcacao.CANCELADO.equals(agenciamento.getStatusEmbarcacao()),
                    ConstantesI18N.AGENCIAMENTO_CANCELAR_SOMENTE_ESPERADO);
        }
    }

    private void validarObitoABordo(Agenciamento agenciamento, SistamPendencyManager pm) {
        pm.assertThat(agenciamento.getAgenciementoViagem().isSepultamento() && agenciamento.getAgenciementoViagem().isObito() || !agenciamento.getAgenciementoViagem().isSepultamento()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.NAO_HOUVE_OBITO_A_BORDO);
    }

    private void validarAcidente(Agenciamento agenciamento, SistamPendencyManager pm) {
        if (agenciamento.getAgenciementoViagem().isAcidente()) {
            pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getNomeAcidente()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.NAO_FOI_INFORMADO_OS_ACIDENTES_A_BORDO);
        } else {
            pm.assertThat(agenciamento.getAgenciementoViagem().getNomeAcidente() == null
                    || agenciamento.getAgenciementoViagem().getNomeAcidente().isEmpty()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.NAO_FOI_INFORMADO_QUE_HOUVE_ACIDENTE);
        }
    }

    private void validarCompartimentoMortandade(Agenciamento agenciamento, SistamPendencyManager pm) {
        if (agenciamento.getAgenciementoViagem().isRoedores()) {
            pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getCompartimentoRoedores()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.NAO_FOI_INFORMADO_O_COMPARTIMENTO_DA_MORTANDADE);
        } else {
            pm.assertThat(agenciamento.getAgenciementoViagem().getCompartimentoRoedores() == null
                    || agenciamento.getAgenciementoViagem().getCompartimentoRoedores().isEmpty()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.NAO_FOI_INFORMADO_O_COMPARTIMENTO_DA_MORTANDADE);
        }
    }

    private void validarMedicamento(Agenciamento agenciamento, SistamPendencyManager pm) {
        if (agenciamento.getAgenciementoViagem().isConsumoMedicamento()) {
            pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getNomeMedicamento()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.NAO_FOI_INFORMADO_O_MEDICAMENTO_CONSUMIDO);
        } else {
            pm.assertThat(agenciamento.getAgenciementoViagem().getNomeMedicamento() == null
                    || agenciamento.getAgenciementoViagem().getNomeMedicamento().isEmpty()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.NAO_HOUVE_CONSUMO_PARA_MEDICAMENTO_INFORMADO);
        }
    }

    private void validarDesinsetizacao(Agenciamento agenciamento, SistamPendencyManager pm) {
        if (agenciamento.getAgenciementoSanitaria().isDesinsetizacao()) {
            pm.assertNotEmpty(agenciamento.getAgenciementoSanitaria().getProdutoDesinsetizacao()).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.NAO_FOI_INFORMADO_O_PORDUTO_DESINSETIZACAO);
            pm.assertThat(agenciamento.getAgenciementoSanitaria().getDataDesinsetizacao() != null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.NAO_FOI_INFORMADO_DATA_DESINSETIZACAO);
        } else {
            pm.assertThat(agenciamento.getAgenciementoSanitaria().getDataDesinsetizacao() == null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.NAO_HOUVE_DESINSETIZACAO_PARA_O_PORDUTO_DATA_INFORMADO);
            pm.assertThat(agenciamento.getAgenciementoSanitaria().getProdutoDesinsetizacao() == null
                    || agenciamento.getAgenciementoSanitaria().getProdutoDesinsetizacao().isEmpty()).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.NAO_HOUVE_DESINSETIZACAO_PARA_O_PORDUTO_DATA_INFORMADO);
        }
    }

    private void validarTratamentoEfluentes(Agenciamento agenciamento, SistamPendencyManager pm) {
        if (agenciamento.getAgenciementoSanitaria().isTanqueTratamento()) {
            pm.assertThat(agenciamento.getAgenciementoSanitaria().getCapacidadeEfluente() != null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.INFORME_DADOS_TRATAMENTO_EFLUENTES_ARMAZENAMENTO);
            pm.assertThat(agenciamento.getAgenciementoSanitaria().getAutonomiaRetencao() != null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.INFORME_DADOS_TRATAMENTO_EFLUENTES_RETENCAO);
        } else {
            pm.assertThat(agenciamento.getAgenciementoSanitaria().getCapacidadeEfluente() == null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.NAO_HOUVE_TRATAMENTO_EFLUENTES_PARA_OS_DADOS_INFORMADOS);
            pm.assertThat(agenciamento.getAgenciementoSanitaria().getAutonomiaRetencao() == null || agenciamento.getAgenciementoSanitaria().getAutonomiaRetencao() == 0).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.NAO_HOUVE_TRATAMENTO_EFLUENTES_PARA_OS_DADOS_INFORMADOS);
        }
    }

    /* validação retirada a pedido de MANAUS -   
     * private void validarSubstituicaoAguaLastro(Agenciamento agenciamento, SistamPendencyManager pm) {
     if (agenciamento.getAgenciementoSanitaria().isSubAguaLastro()) {
     pm.assertThat(agenciamento.getAgenciementoSanitaria().getLatitudeSubstAgua() != null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.INFORME_DADOS_SUBST_AGUA_LASTRO_LATITUDE);
     pm.assertThat(agenciamento.getAgenciementoSanitaria().getLongitudeSubstAgua() != null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.INFORME_DADOS_SUBST_AGUA_LASTRO_LONGITUDE);
     } else {
     pm.assertThat(agenciamento.getAgenciementoSanitaria().getLatitudeSubstAgua() == null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.NAO_HOUVE_SUBST_AGUA_LASTRO_PARA_OS_DADOS_INFORMADOS);
     pm.assertThat(agenciamento.getAgenciementoSanitaria().getLongitudeSubstAgua() == null).orRegister(TipoExcecao.AGENCIAMENTO_SANITARIA, ConstantesI18N.NAO_HOUVE_SUBST_AGUA_LASTRO_PARA_OS_DADOS_INFORMADOS);
     }
     }*/
    /**
     * Verifica se a taxa da Anvisa já foi paga.
     *
     * @return
     */
    public boolean jaPagouTaxaAnvisa() {
        List<Taxa> taxas = getService().buscarTaxaPorAgenciamentoETipo(agenciamento, TipoTaxa.LIVRE_PRATICA_ANVISA);

        if (taxas.isEmpty()) {
            return false;
        }
        return true;
    }
}
