package br.com.petrobras.sistam.common.rules;

import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;

/**
 *
 */
public class RegrasPortoComplemento {

    public static void validarCamposObrigatorios(SistamPendencyManager pm, PortoComplemento portoComplemento){
        pm.assertNotNull(portoComplemento.getPorto()).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.PORTO_COMPLEMENTO_PORTO_OBRIGATORIO);
        
        if (portoComplemento.getPorto() != null){
            pm.assertThat(portoComplemento.getPorto().isNacional()).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.PORTO_COMPLEMENTO_PORTO_DEVE_SER_NACIONAL);
        }
        pm.assertNotEmpty(portoComplemento.getEndereco()).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.PORTO_COMPLEMENTO_ENDERECO_OBRIGATORIO);
        pm.assertNotEmpty(portoComplemento.getCidade()).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.PORTO_COMPLEMENTO_CIDADE_OBRIGATORIA);
        pm.assertNotEmpty(portoComplemento.getEstado()).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.PORTO_COMPLEMENTO_ESTADO_OBRIGATORIO);
        pm.assertNotEmpty(portoComplemento.getInscricaoEstadual()).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.PORTO_COMPLEMENTO_INSCRICAO_ESTADUAL_OBRIGATORIA);
        pm.assertNotEmpty(portoComplemento.getCnpj()).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.PORTO_COMPLEMENTO_CNPJ_OBRIGATORIA);
    }
    
}