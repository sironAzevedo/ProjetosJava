package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamUtils;
import java.util.Collection;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author adzk
 */
@Component
public class ValidadorEmpresaProtecao {

    public void validarSalvar(EmpresaProtecao empresa) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(empresa.getRazaoSocial()).orRegister(TipoExcecao.EMPRESA_PROTECAO, ConstantesI18N.EMPRESA_PROTECAO_NOME_OBRIGATORIA);
        pm.assertNotEmpty(empresa.getCnpj()).orRegister(TipoExcecao.EMPRESA_PROTECAO, ConstantesI18N.EMPRESA_PROTECAO_CNPJ_OBRIGATORIA);
        if (StringUtils.isNotBlank(empresa.getEmail())) {
            pm.assertThat(SistamUtils.validarEmail(empresa.getEmail())).orRegister(TipoExcecao.EMPRESA_PROTECAO, ConstantesI18N.EMPRESA_PROTECAO_COM_EMAIL_INVALIDO);
        }
        Collection servicosNovos = SistamUtils.obterItemsDaListaComValorEspecifico(empresa.getServicos(), "id", null);
        pm.assertThat(!servicosNovos.isEmpty()).orRegister(TipoExcecao.EMPRESA_PROTECAO, ConstantesI18N.EMPRESA_PROTECAO_TIPO_OBRIGATORIA);
        pm.verifyAll();
    }
}