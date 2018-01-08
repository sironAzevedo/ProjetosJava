package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorServicoMedicoOdontologico {

    public void validarSalvarServicoMedicoOdontologico(ServicoMedicoOdontologico servicoMedicoOdontologico) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(servicoMedicoOdontologico.getServicoProtecao()).orRegister(TipoExcecao.SERVICO_MEDICO_ODONTOLOGICO, ConstantesI18N.SERVICO_MEDICO_ODONTOLOGICO_SERVICO_PROTECAO_OBRIGATORIO);
        pm.assertNotNull(servicoMedicoOdontologico.getNomeTripulante()).orRegister(TipoExcecao.SERVICO_MEDICO_ODONTOLOGICO, ConstantesI18N.SERVICO_MEDICO_ODONTOLOGICO_NOME_TRIPULANTE_OBRIGATORIO);

        pm.verifyAll();
    }

}
