package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCertificado {

    public void validarSalvarCertificado(Certificado certificado) {
        SistamPendencyManager pm = new SistamPendencyManager();
        addValidacaoGeral(pm, certificado);
        pm.verifyAll();
    }

    public void validarSalvarCertificadoLivrePratica(Certificado certificado) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(certificado.getNumeroCertificado()).orRegister(TipoExcecao.CERTIFICADO, ConstantesI18N.INFORME_NUMERO_CERTIFICADO);
        addValidacaoGeral(pm, certificado);
        pm.verifyAll();
    }

    private void addValidacaoGeral(SistamPendencyManager pm, Certificado certificado) {
        pm.assertNotNull(certificado.getData()).orRegister(TipoExcecao.CERTIFICADO, ConstantesI18N.CERTIFICADO_SEM_DATA);
        pm.assertNotNull(certificado.getTipo()).orRegister(TipoExcecao.CERTIFICADO, ConstantesI18N.CERTIFICADO_SEM_TIPO);
        pm.assertNotNull(certificado.getEmbarcacao()).orRegister(TipoExcecao.CERTIFICADO, ConstantesI18N.CERTIFICADO_SEM_EMBARCACAO);
        pm.assertThat(!TipoCertificado.CLP.equals(certificado.getTipo()) || certificado.getAgenciamento() != null).orRegister(TipoExcecao.CERTIFICADO, ConstantesI18N.CERTIFICADO_LIVRE_PRATICA_SEM_AGENCIAMENTO);
    }
}
