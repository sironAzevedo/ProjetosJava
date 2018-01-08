package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Transporte;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorVisita {
    /**
     * Valida as informações necessárias para salvar uma visita.
     * @param visita 
     */
    public void validarSalvarVisita(Visita visita){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(visita.getAgenciamento()).orRegister(TipoExcecao.VISITA, ConstantesI18N.VISITA_AGENCIAMENTO_OBRIGATORIO);
        pm.assertNotEmpty(visita.getChaveAgente()).orRegister(TipoExcecao.VISITA, ConstantesI18N.VISITA_CHAVE_AGENTE_OBRIGATORIO);
        pm.assertNotEmpty(visita.getNomeAgente()).orRegister(TipoExcecao.VISITA, ConstantesI18N.VISITA_NOME_AGENTE_OBRIGATORIO);
        pm.assertNotNull(visita.getDataInicio()).orRegister(TipoExcecao.VISITA, ConstantesI18N.VISITA_DATA_INICIO_OBRIGATORIA);
        pm.assertNotNull(visita.getDataTermino()).orRegister(TipoExcecao.VISITA, ConstantesI18N.VISITA_DATA_TERMINO_OBRIGATORIA);
        pm.verifyAll();
    }
    
    /**
     * Valida as informações necessárias para salvar um tranporte.
     * @param transporte 
     */
    public void validarSalvarTransporte(Transporte transporte){
        AssertUtils.assertNotNull(transporte.getVisita(), ConstantesI18N.TRANSPORTE_VISITA_NAO_PODE_SER_NULA);
        
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(transporte.getTipoTransporte()).orRegister(TipoExcecao.VISITA, ConstantesI18N.TRANSPORTE_INFORME_TIPO_TRANSPORTE);
        pm.assertNotNull(transporte.getCusto()).orRegister(TipoExcecao.VISITA, ConstantesI18N.TRANSPORTE_INFORME_O_CUSTO);
        pm.assertNotNull(transporte.getResponsavelCusto()).orRegister(TipoExcecao.VISITA, ConstantesI18N.TRANSPORTE_INFORME_RESPONSAVEL_CUSTO);
        
        pm.verifyAll();
    }
    
}
