package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamentoAtendimento;
import org.springframework.stereotype.Component;
import static br.com.petrobras.sistam.common.util.SistamDateUtils.*;
import java.util.Calendar;

@Component
public class ValidadorAgenciamento {

    public void validarSalvarAgenciamento(Agenciamento agenciamento) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_NULO);
        pm.assertNotNull(agenciamento.getAgencia()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_AGENCIA);
        pm.assertNotNull(agenciamento.getPorto()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_ATUAL);
        pm.assertNotNull(agenciamento.getPortoOrigem()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_ORIGEM);
        pm.assertNotNull(agenciamento.getEmbarcacao()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_EMBARCACAO);
        pm.assertNotNull(agenciamento.getStatusEmbarcacao()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_STATUS_EMBARCACAO);
        pm.assertNotNull(agenciamento.getSituacaoProcesso()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_SITUACAO_PROCESSO);
        pm.assertNotNull(agenciamento.getAnoProcesso()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_ANO_PROCESSO);
        pm.assertNotNull(agenciamento.getTipoContrato()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_CONTRATO);
        pm.assertNotNull(agenciamento.getDataInclusao()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_DATA_INCLUSAO);
        pm.assertNotNull(agenciamento.getNomeCadastrador()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_NOME_CADASTRADOR);
        pm.assertNotNull(agenciamento.getChaveCadastrador()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_CHAVE_CADASTRADOR);
        pm.assertNotEmpty(agenciamento.getVgm()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_VIAGEM);
        pm.assertNotNull(agenciamento.getEta()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_ETA);
        if (agenciamento.getDataChegada() != null) {
            pm.assertThat(!agenciamento.getDataChegada().after(Calendar.getInstance(agenciamento.getAgencia().obterTimezone()).getTime()))
                    .orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_DATA_CHEGADA_MAIOR_DATA_ATUAL);
        }
        if (agenciamento.getDataSaida() != null) {
            pm.assertThat(!agenciamento.getDataSaida().after(Calendar.getInstance(agenciamento.getAgencia().obterTimezone()).getTime()))
                    .orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_DATA_SAIDA_MAIOR_DATA_ATUAL);
            if (agenciamento.getDataChegada() != null) {
                pm.assertThat(!truncateTime(agenciamento.getDataSaida(), null).before(truncateTime(agenciamento.getDataChegada(), null))).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_DATA_SAIDA_MENOR_DATA_CHAGADA);
            }
        }
        pm.assertNotNull(agenciamento.getTipoArmador()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_ARMADOR);
        pm.assertNotNull(agenciamento.getTipoFrota()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_FROTA);
        pm.assertThat(!StatusEmbarcacao.CANCELADO.equals(agenciamento.getStatusEmbarcacao())).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_CANCELADO_NAO_PODE_SER_ALTERADO);
        pm.assertThat(agenciamento.getAgenciamentoCarga() || agenciamento.getAgenciamentoProtecao() || agenciamento.getAgenciamentoPlataforma()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_AGENCIAMENTO);
        pm.verifyAll();

        //Se o status for SAÍDO ou DESVIADO, as infomações de saído devem ser informadas.
        if (StatusEmbarcacao.SAIDO.equals(agenciamento.getStatusEmbarcacao())
                || StatusEmbarcacao.DESVIADO.equals(agenciamento.getStatusEmbarcacao())) {
            validarSaidaEmbarcacaoAgenciada(agenciamento);
        }

    }

    public void validarSaidaEmbarcacaoAgenciada(Agenciamento agenciamento) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getDataEstimadaSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_ETS_OBRIGATORIA);
        pm.assertNotNull(agenciamento.getDataSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_HORA_OFICIAL_SAIDA_OBRIGATORIA);
        pm.assertNotNull(agenciamento.getCaladoSaidaVante()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.INFORME_CALADO_SAIDA_VANTE);
        pm.assertNotNull(agenciamento.getCaladoSaidaRe()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.INFORME_CALADO_SAIDA_RE);
        pm.assertNotNull(agenciamento.getEtaProximoPorto()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.INFORME_DATA_CHEGADA_PORTO_DESTINO);
        pm.assertThat(agenciamento.getPortoDestino() != null || (agenciamento.getDestinoIntermediario() != null && !agenciamento.getDestinoIntermediario().isEmpty()))
                .orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_PORTO_DESTINO_OU_DESTINO_INTERMEDIARIO_OBRIGATORIO);
        pm.verifyAll();
    }

    public void validarIniciarAgenciamento(Agenciamento agenciamento) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getAgencia()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_AGENCIA);
        pm.assertNotNull(agenciamento.getPorto()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_ATUAL);
        pm.assertNotNull(agenciamento.getEmbarcacao()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_EMBARCACAO);
        pm.assertNotNull(agenciamento.getVgm()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_VIAGEM);
        pm.assertNotNull(agenciamento.getEta()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_ETA);
        pm.assertNotNull(agenciamento.getPortoOrigem()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_ORIGEM);
        pm.assertNotNull(agenciamento.getTipoContrato()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_CONTRATO);
        pm.assertNotNull(agenciamento.getTipoArmador()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_ARMADOR);
        pm.assertNotNull(agenciamento.getTipoFrota()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_FROTA);
        pm.assertThat(agenciamento.getAgenciamentoCarga() || agenciamento.getAgenciamentoProtecao() || agenciamento.getAgenciamentoPlataforma()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_AGENCIAMENTO);
        pm.verifyAll();
    }

    public void validarDesvio(Desvio desvio, Porto novoPorto) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(desvio.getData()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_DESVIO_DATA_DEVE_SER_INFORMADA);
        pm.assertThat(StatusEmbarcacao.SAIDO.equals(desvio.getAgenciamento().getStatusEmbarcacao()) || StatusEmbarcacao.DESVIADO.equals(desvio.getAgenciamento().getStatusEmbarcacao()))
                .orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_DESVIO_STATUS_EMBARCACAO_DEVE_SER_SAIDO_OU_DESVIADO);
        pm.assertNotNull(novoPorto).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_DESVIO_NOVO_PORTO_DEVE_SER_INFORMADO);
        pm.assertThat(!desvio.getAgenciamento().getPortoDestino().equals(novoPorto))
                .orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_DESVIO_NOVO_PORTO_DEVE_SER_DIFERENTE_PORTO_ANTERIOR);
        pm.assertNotNull(desvio.getMotivo()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_DESVIO_MOTIVO_DEVE_SER_INFORMADO);
        pm.verifyAll();
    }

    public void validarCancelarAgenciamento(CancelamentoAgenciamento cancelamento, boolean cancelaAgenciamentoAdm) {
        SistamPendencyManager pm = new SistamPendencyManager();

        if (!cancelaAgenciamentoAdm) {
            pm.assertThat(StatusEmbarcacao.ESPERADO.equals(cancelamento.getAgenciamento().getStatusEmbarcacao()))
                    .orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_CANCELAR_SOMENTE_ESPERADO);
        }

        pm.assertNotNull(cancelamento.getMotivo()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_CANCELAMENTO_MOTIVO_DEVE_SER_INFORMADO);

        if (MotivoCancelamento.OUTROS.equals(cancelamento.getMotivo())) {
            pm.assertNotEmpty(cancelamento.getDescricaoMotivo()).orRegister(TipoExcecao.AGENCIAMENTO, ConstantesI18N.AGENCIAMENTO_CANCELAMENTO_OBSERVACAO_MOTIVO_DEVE_SER_INFORMADO);
        }

        pm.verifyAll();

    }

    public void validarBuscarAgenciamentosPorFiltro(FiltroAgenciamento filtro) {
        //Se tiver alguma data informada, é obrigatório informar a agência.
        if (filtro.getDataChegadaIni() != null || filtro.getDataChegadaFim() != null
                || filtro.getDataInclusaoIni() != null || filtro.getDataInclusaoFim() != null
                || filtro.getDataSaidaIni() != null || filtro.getDataSaidaFim() != null) {

            AssertUtils.assertNotNull(filtro.getAgencia(), ConstantesI18N.AGENCIAMENTO_INFORME_AGENCIA);
        }

        boolean pelomenosum = false;
        pelomenosum = pelomenosum || filtro.getAgencia() != null;
        pelomenosum = pelomenosum || filtro.getAreaNavegacao() != null;
        pelomenosum = pelomenosum || filtro.getEmbarcacao() != null;
        pelomenosum = pelomenosum || (filtro.getNumeroProcesso() != null && !filtro.getNumeroProcesso().isEmpty());
        pelomenosum = pelomenosum || filtro.getPortoOrigem() != null;
        pelomenosum = pelomenosum || filtro.getPortoDestino() != null;
        pelomenosum = pelomenosum || filtro.getDataInclusaoIni() != null;
        pelomenosum = pelomenosum || filtro.getDataInclusaoFim() != null;
        pelomenosum = pelomenosum || filtro.getPorto() != null;
        pelomenosum = pelomenosum || filtro.getStatusEmbarcacao() != null;
        pelomenosum = pelomenosum || filtro.getTipoContrato() != null;
        pelomenosum = pelomenosum || (filtro.getVgm() != null && !filtro.getVgm().isEmpty());

        AssertUtils.assertExpression(pelomenosum, ConstantesI18N.AGENCIAMENTO_BUSCA_POR_FILTRO_PELO_MENOS_UM);
    }

    public void validarBuscarParaRelatorioDeProdutividade(FiltroAgenciamento filtro) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(filtro.getAgencia()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_AGENCIAMENTO_AGENCIA_OBRIGATORIA);

        if (filtro.getDataChegadaIni() == null || filtro.getDataChegadaFim() == null) {
            pm.assertThat(false).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_AGENCIAMENTO_PERIODO_OBRIGATORIO);
        }

        if (filtro.getDataChegadaIni() != null && filtro.getDataChegadaFim() != null) {
            pm.assertThat(filtro.getDataChegadaIni().before(filtro.getDataChegadaFim())).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.DATA_INICIO_NAO_DEVE_SER_POSTERIOR_A_DATA_FIM);
        }
        pm.verifyAll();

    }

    public void validarBuscarRelatorioAtendimentos(FiltroAgenciamentoAtendimento filtro) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(filtro.getAgencia()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_AGENCIAMENTO_AGENCIA_OBRIGATORIA);
        pm.assertThat(filtro.getMesInicial() != null && filtro.getAnoInicial() != null && filtro.getMesFinal() != null && filtro.getAnoFinal() != null).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_AGENCIAMENTO_PERIODO_OBRIGATORIO);
        pm.assertThat(filtro.getDataInicial() != null && filtro.getDataFinal() != null && !filtro.getDataInicial().after(filtro.getDataFinal())).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_AGENCIAMENTO_PERIODO_INCONSISTENTE);
        pm.assertThat(filtro.getQtdeDiasAtendimento() != null).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_AGENCIAMENTO_CICLO_ATENDIMENTO_OBRIGATORIO);
        if (filtro.getQtdeDiasAtendimento() != null) {
            pm.assertThat(filtro.getQtdeDiasAtendimento() > 0).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_AGENCIAMENTO_CICLO_ATENDIMENTO_MAIOR_QUE_ZERO);
        }
        pm.verifyAll();
    }

    public void validarAcompanhamento(Acompanhamento acompanhamento) {
        AssertUtils.assertNotNull(acompanhamento.getAgenciamento(), ConstantesI18N.ACOMPANHAMENTO_AGENCIAMENTO_NULO);
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(acompanhamento.getData()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.ACOMPANHAMENTO_INFORME_DATA);
        pm.assertNotEmpty(acompanhamento.getTexto()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.ACOMPANHAMENTO_INFORME_TEXTO);
        pm.verifyAll();

    }
}
