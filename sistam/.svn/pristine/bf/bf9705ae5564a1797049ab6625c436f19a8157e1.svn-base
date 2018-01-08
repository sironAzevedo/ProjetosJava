package br.com.petrobras.sistam.service.validator;


import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNotesWeb {

    public void validarEnvioSolicitacaoServico(ServicoManobra servico) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(servico.getDataEnvio() == null).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SOLICITACAO_MANOBRA_SERVICO_JA_SOLICITADO);
        pm.assertNotEmpty(servico.getManobra().getAgenciamento().getAgencia().getEmail()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SOLICITACAO_MANOBRA_AGENCIA_COM_EMAIL_VAZIO_OU_NULO);
        pm.assertNotEmpty(servico.getEmpresaMaritima().getEmail()).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SOLICITACAO_MANOBRA_EMPRESA_COM_EMAIL_VAZIO_OU_NULO);
        pm.assertThat(SistamUtils.validarEmail(servico.getEmpresaMaritima().getEmail())).orRegister(TipoExcecao.MANOBRA, ConstantesI18N.SOLICITACAO_MANOBRA_EMPRESA_COM_EMAIL_INVALIDO);
        pm.verifyAll();
    }
    
    public void validarEnvioEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro) {

        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertThat(filtro.isAgenciaMaritima() || filtro.isHotel() || filtro.getAgenciaViagem() != null).orRegister(TipoExcecao.ENVIO_EMAIL, ConstantesI18N.ENVIO_EMAIL_ENVIO_PARA_INFORME_PELO_MENOS_UM);
        pm.assertNotEmpty(filtro.getLotacao()).orRegister(TipoExcecao.ENVIO_EMAIL, ConstantesI18N.ENVIO_EMAIL_LOTACAO_EMPRESA_OBRIGATORIO);
        pm.assertNotNull(filtro.getTipoCentroCusto()).orRegister(TipoExcecao.ENVIO_EMAIL, ConstantesI18N.ENVIO_EMAIL_CENTRO_CUSTO_OBRIGATORIO);

        pm.verifyAll();

    }
    public void validarEnvioEmailSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro) {

        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertThat(filtro.isAgenciaMaritima() || filtro.isEmpresaTransporte()).orRegister(TipoExcecao.ENVIO_EMAIL, ConstantesI18N.ENVIO_EMAIL_ENVIO_PARA_INFORME_PELO_MENOS_UM);

        pm.verifyAll();

    }
    
    public void validarEnvioEmailSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro) {

        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertThat(filtro.isAgenciaMaritima() || filtro.isEmpresaResponsavel()).orRegister(TipoExcecao.ENVIO_EMAIL, ConstantesI18N.ENVIO_EMAIL_ENVIO_PARA_INFORME_PELO_MENOS_UM);

        pm.verifyAll();

    }
    
    
}
