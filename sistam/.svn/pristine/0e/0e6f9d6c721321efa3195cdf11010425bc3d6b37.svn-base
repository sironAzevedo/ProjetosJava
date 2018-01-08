package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class ValidadorManobra {

    public void validarCamposObrigatoriosManobra(Manobra manobra) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(manobra.getAgenciamento()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_AGENCIAMENTO_OBRIGATORIO);
        pm.assertNotNull(manobra.getStatus()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_STATUS_OBRIGATORIO);
        pm.assertNotNull(manobra.getFinalidadeManobra()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_FINALIDADE_OBRIGATORIO);
        pm.assertNotNull(manobra.getResponsavelCusto()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.INFORME_REPONSAVEL);
        pm.assertNotNull(manobra.getPontoAtracacaoOrigem()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_PONTO_INICIAL);
        pm.assertNotNull(manobra.getPontoAtracacaoDestino()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_PONTO_FINAL);

        if (!manobra.isResponsavelCustoSemCusto()) {
            pm.assertNotNull(manobra.getCaladoVante()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_CALADO_VANTE_OBRIGATORIO);
            pm.assertNotNull(manobra.getCaladoRe()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_CALADO_RE_OBRIGATORIO);
        }
        pm.verifyAll();
    }

    public void validarMovimentarManobra(Manobra manobra) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(manobra.getAgenciamento()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_AGENCIAMENTO_OBRIGATORIO);
        pm.assertNotNull(manobra.getStatus()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_STATUS_OBRIGATORIO);
        pm.assertNotNull(manobra.getFinalidadeManobra()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_FINALIDADE_OBRIGATORIO);
        pm.assertNotNull(manobra.getResponsavelCusto()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.INFORME_REPONSAVEL);
        pm.assertNotNull(manobra.getPontoAtracacaoOrigem()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_PONTO_INICIAL);
        pm.assertNotNull(manobra.getPontoAtracacaoDestino()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_PONTO_FINAL);
        pm.assertNotNull(manobra.getDataTermino()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_TERMINO);
        pm.verifyAll();
    }

    public void validarSalvarManobra(Manobra manobra) {
        AssertUtils.assertExpression(!manobra.isCancelada(), ConstantesI18N.MANOBRA_SALVAR_MANOBRA_CANCELADA);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(manobra.getServicos()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_PELO_MENOS_UM_SERVICO);
        pm.verifyAll();
    }

    public void validarExclusaoManobra(Manobra manobra) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(manobra.isPlanejada()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_JA_SOLICITADA_OU_REGISTRADA_NAO_PODE_SER_EXCLUIDA);
        pm.verifyAll();
    }

    public void validarRegistrarManobra(Manobra manobra) {
        AssertUtils.assertExpression(!manobra.isCancelada(), ConstantesI18N.MANOBRA_SALVAR_MANOBRA_CANCELADA);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(manobra.getDataTermino()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_TERMINO);
        if (!manobra.isResponsavelCustoSemCusto()) {
            pm.assertNotNull(manobra.getDataInicio()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_INICIO);
            pm.assertNotNull(manobra.getDataPartidaOrigem()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_PARTIDA);
            pm.assertNotNull(manobra.getDataChegadaDestino()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_CHEGADA);
        }

        //valida se os serviços tem responsável
        for (ServicoManobra servico : manobra.getServicos()) {
            pm.assertNotEmpty(servico.getResponsaveis()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_MANOBRA_INFORME_PELO_MENOS_UM_RESPONSAVEL);
        }

        pm.verifyAll();
    }

    public void validarCancelarManobraForaDoPrazo(Manobra manobra, String motivoDoCancelamento) {
        AssertUtils.assertExpression(!manobra.isCancelada(), ConstantesI18N.MANOBRA_SALVAR_MANOBRA_CANCELADA);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(manobra.getDataInicio()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_INICIO);
        pm.assertNotNull(manobra.getDataTermino()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_TERMINO);
        pm.assertNotNull(manobra.getDataPartidaOrigem()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_PARTIDA);
        pm.assertNotNull(manobra.getDataChegadaDestino()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_DATA_CHEGADA);
        pm.assertNotEmpty(manobra.getServicos()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_PELO_MENOS_UM_SERVICO);
        pm.assertThat(motivoDoCancelamento != null && !motivoDoCancelamento.isEmpty()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_MOTIVO_CANCELAMENTO);

        pm.verifyAll();
    }

    public void validarSalvarServicoManobra(ServicoManobra servico) {
        SistamPendencyManager pm = new SistamPendencyManager();
        Manobra manobra = servico.getManobra();
        AssertUtils.assertNotNull(manobra, ConstantesI18N.SERVICO_MANOBRA_MANOBRA_OBRIGATORIA);

        pm.assertNotNull(servico.getTipoDoServico()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_MANOBRA_INFORME_TIPO);
        pm.assertNotNull(servico.getEmpresaMaritima()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_MANOBRA_INFORME_EMPRESA);

        if (servico.getManobra().isSolicitada()
                || servico.getManobra().isPlanejada()) {
            pm.assertNotNull(servico.getDataProgramada()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_MANOBRA_INFORME_DATA_PROGRAMADA);
        }

        if (servico.getManobra().isRegistrada()) {
            pm.assertNotEmpty(servico.getResponsaveis()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_MANOBRA_INFORME_PELO_MENOS_UM_RESPONSAVEL);
        }
        pm.verifyAll();

        //Verifica se o serviço com a empresa e o tipo informado já não está adicionado na manobra.
        for (ServicoManobra servicoManobraAdicionado : manobra.getServicos()) {

            if (!servico.equals(servicoManobraAdicionado)
                    && servico.getTipoDoServico().equals(servicoManobraAdicionado.getTipoDoServico())
                    && servico.getEmpresaMaritima().equals(servicoManobraAdicionado.getEmpresaMaritima())) {
                AssertUtils.assertExpression(false, ConstantesI18N.SERVICO_MANOBRA_SERVICO_JA_ADICIONADO_NA_MANOBRA);
            }
        }
    }

    public void validarExclusaoServicoManobra(ServicoManobra servico) {
        SistamPendencyManager pm = new SistamPendencyManager();

        if (servico.getId() != null) {
            AssertUtils.assertExpression(servico.getManobra().getServicos().size() > 1, ConstantesI18N.SERVICO_MANOBRA_MANOBRA_DEVE_TER_PELO_MENOS_UM_SERVICO);
        }

//        if (servico.getManobra().isSolicitada()) {
//            pm.assertThat(servico.getDataEnvio() == null).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_SERVICO_EXCLUSAO_NEGADA_DATA_ENVIO_PREENCHIDA);
//        }
        pm.verifyAll();
    }

    public void validarSalvarResponsavel(ServicoResponsavel responsavel) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(responsavel.getServicoManobra()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_RESPONSAVEL_SERVICO_MANOBRA_OBRIGATORIO);
        pm.assertNotNull(responsavel.getServico()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_RESPONSAVEL_INFORME_NOME);
        pm.assertNotNull(responsavel.getDataInicio()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_RESPONSAVEL_INFORME_DATA_INICIO_REAL);
        pm.assertNotNull(responsavel.getDataTermino()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_RESPONSAVEL_INFORME_DATA_TERMINO_REAL);

        if (responsavel.getDataInicio() != null && responsavel.getDataTermino() != null) {
            Date inicio = SistamDateUtils.truncateTime(responsavel.getDataInicio(), null);
            Date termino = SistamDateUtils.truncateTime(responsavel.getDataTermino(), null);
            pm.assertThat(inicio.equals(termino) || inicio.before(termino)).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SERVICO_RESPONSAVEL_DATA_INICIO_MAIOR_DATA_TERMINO);
        }

        pm.verifyAll();

        for (ServicoResponsavel responsavelAdicionado : responsavel.getServicoManobra().getResponsaveis()) {

            if (!responsavel.equals(responsavelAdicionado)
                    && responsavel.getServico().equals(responsavelAdicionado.getServico())) {
                AssertUtils.assertExpression(false, ConstantesI18N.SERVICO_RESPONSAVEL_RESPONSAVEL_JA_ADICIONADO);
            }
        }

    }

    public void validarExclusaoDeResponsavel(ServicoResponsavel responsavel) {
        if (responsavel.getId() != null) {
            AssertUtils.assertExpression(responsavel.getServicoManobra().getResponsaveis().size() > 1, ConstantesI18N.SERVICO_RESPONSAVEL_SERVICO_DEVE_TER_PELO_MENOS_UM_RESPONSAVEL);
        }
    }

    public void validarCancelarManobraDentroPrazo(Manobra manobra, String motivoCancelamento) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(motivoCancelamento).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_CANCELAR_DENTRO_PRAZO_SEM_MOTIVO);
        pm.assertThat(manobra.isSolicitada()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_SOMENTE_STATUS_SOLICITADA_PODE_SER_CANCELADA_DENTRO_PRAZO);
        pm.verifyAll();
    }

    public void validarEncerrarManobra(Manobra manobra, Porto portoDestino, Date eta) {
        AssertUtils.assertExpression(manobra.isRegistrada() || manobra.isPreRegistrada(), ConstantesI18N.MANOBRA_ENCERRAR_APENAS_AS_REGISTRADAS);

        if (FinalidadeManobra.DESATRACACAO_SAIDA.equals(manobra.getFinalidadeManobra())
                || FinalidadeManobra.SAIDA_FUNDEIO.equals(manobra.getFinalidadeManobra())) {

            SistamPendencyManager pm = new SistamPendencyManager();
            pm.assertNotNull(portoDestino).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_PORTO_DESTINO);
            pm.assertNotNull(eta).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.MANOBRA_INFORME_ETA);
            pm.verifyAll();
        }
    }
}
