package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresa;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEmpresa {
    /**
     * Valida as informações necessárias para salvar uma empresa.
     * @param visita 
     */
    public void validarSalvarEmpresa(EmpresaMaritima empresa){
        SistamPendencyManager pm = new SistamPendencyManager(); 
        pm.assertNotNull(empresa.getAgencia()).orRegister(TipoExcecao.EMPRESA, ConstantesI18N.EMPRESA_MARITIMA_AGENCIA_OBRIGATORIA);
        pm.assertNotEmpty(empresa.getRazaoSocial()).orRegister(TipoExcecao.EMPRESA, ConstantesI18N.EMPRESA_MARITIMA_EMPRESA_OBRIGATORIA);
        pm.assertNotEmpty(empresa.getCnpj()).orRegister(TipoExcecao.EMPRESA, ConstantesI18N.EMPRESA_MARITIMA_CNPJ_OBRIGATORIO);
        pm.assertNotEmpty(empresa.getEmail()).orRegister(TipoExcecao.EMPRESA, ConstantesI18N.EMPRESA_MARITIMA_EMAIL_OBRIGATORIO);
        pm.verifyAll();
    }
    
    /**
     * Valida as informações necessárias para salvar um serviço da empresa.
     * @param servico 
     */
    public void validarSalvarServico(Servico servico){
        SistamPendencyManager pm = new SistamPendencyManager(); 
        pm.assertNotNull(servico.getEmpresa()).orRegister(TipoExcecao.EMPRESA, ConstantesI18N.SERVICO_EMPRESA_MARITIMA_OBRIGATORIO);
        pm.assertNotNull(servico.getTipoServico()).orRegister(TipoExcecao.EMPRESA, ConstantesI18N.SERVICO_EMPRESA_MARITIMA_TIPO_OBRIGATORIO);
        pm.assertNotNull(servico.getNomeServico()).orRegister(TipoExcecao.EMPRESA, ConstantesI18N.SERVICO_EMPRESA_MARITIMA_NOME_OBRIGATORIO);
        pm.verifyAll();
    }
    
    /**
     * Valida as informações necessárias para busca de empresas por filtro
     * @param filtro 
     */
    public void validarBuscarEmpresasPorFiltro(FiltroEmpresa filtro){
        AssertUtils.assertNotNull(filtro.getAgencia(), ConstantesI18N.EMPRESA_MARITIMA_AGENCIA_OBRIGATORIA);
    }
    
    
}
