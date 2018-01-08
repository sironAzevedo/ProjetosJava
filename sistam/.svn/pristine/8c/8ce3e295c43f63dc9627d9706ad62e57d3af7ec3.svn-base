package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author adzk
 */
@Component
public class ValidadorPessoaProtecao {

    public void validarSalvar(Pessoa pessoa) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(pessoa.getNome()).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_PROTECAO_NOME_OBRIGATORIA);
        if (pessoa.getDataNascimento() != null) {
            Date dataAtual = SistamDateUtils.truncateTime(new Date(), null);
            pm.assertThat(!SistamDateUtils.truncateTime(pessoa.getDataNascimento(), null).after(dataAtual)).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_PROTECAO_DATANASCIMENTO_MENORIGUAL_DATAATUAL);
        }
        pm.assertThat(StringUtils.isNotBlank(pessoa.getDocumento()) || StringUtils.isNotBlank(pessoa.getCpf())).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_PROTECAO_DOCUMENTO_OU_CPF_OBRIGATORIA);
        pm.verifyAll();
    }
}