package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.rules.RegrasPortoComplemento;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPorto {
    /**
     * Valida as informações necessárias para salvar um ponto de atracação.
     * @param visita 
     */
    public void validarSalvarPontoAtracacao(PontoAtracacao pontoAtracacao){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(pontoAtracacao.getPontoOperacional()).orRegister(TipoExcecao.PONTO_ATRACACAO, ConstantesI18N.PONTO_ATRACACAO_PONTO_OPERACAIONAL_OBRIGATORIO);
        pm.assertNotEmpty(pontoAtracacao.getNome()).orRegister(TipoExcecao.PONTO_ATRACACAO, ConstantesI18N.PONTO_ATRACACAO_NOME_OBRIGATORIO);
        pm.verifyAll();
    }
    
    /**
     * Valida as informações necessárias para salvar uma agência porto
     * @param pontoAtracacao 
     */
    public void validarSalvarAgenciaPorto(AgenciaPorto agenciaPorto){
        SistamPendencyManager pm = new SistamPendencyManager();
        
        pm.assertNotNull(agenciaPorto.getAgencia()).orRegister(TipoExcecao.AGENCIA_PORTO, ConstantesI18N.AGENCIA_PORTO_AGENCIA_OBRIGATORIA);
        pm.assertNotNull(agenciaPorto.getPorto()).orRegister(TipoExcecao.AGENCIA_PORTO, ConstantesI18N.AGENCIA_PORTO_PORTO_OBRIGATORIO);
        pm.assertNotEmpty(agenciaPorto.getApenasMunicipio()).orRegister(TipoExcecao.AGENCIA_PORTO, ConstantesI18N.AGENCIA_PORTO_MUNICIPIO_OBRIGATORIO);
        pm.assertNotEmpty(agenciaPorto.getEstado()).orRegister(TipoExcecao.AGENCIA_PORTO, ConstantesI18N.AGENCIA_PORTO_ESTADO_OBRIGATORIO);
        
        pm.verifyAll();
    }
    
    /**
     * Valida as informações necessárias para salvar o porto complemento
     * @param complemento 
     */
    public void validarSalvarPortoComplemento(PortoComplemento complemento){
        SistamPendencyManager pm = new SistamPendencyManager();
        RegrasPortoComplemento.validarCamposObrigatorios(pm, complemento);
        pm.verifyAll();
    }
   
   
}
