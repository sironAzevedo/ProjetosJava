package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPendencia {
    
    public void validarCriarPendencia(Agenciamento agenciamento, TipoPendencia tipo){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.PENDENCIA_AGENCIAMENTO_OBRIGATORIO);
        pm.assertNotNull(tipo).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.PENDENCIA_TIPO_OBRIGATORIO);
        pm.verifyAll();
    }
    
    public void validarResolucaoDaPendenciaManual(Pendencia pendencia){
        AssertUtils.assertExpression(pendencia.isResolvida() == false, ConstantesI18N.PENDENCIA_JA_RESOLVIDA);
    }
    
    public void validarResolucaoDaPendenciaDoDocumento(Documento documento){
        AssertUtils.assertNotNull(documento, ConstantesI18N.PENDENCIA_DOCUMENTO_OBRIGATORIO);
    }
    
    public void validarResolucaoDaPendenciaDaTaxa(Taxa taxa){
        AssertUtils.assertNotNull(taxa, ConstantesI18N.PENDENCIA_TAXA_OBRIGATORIO);
    }
    
    public void validarRemocaoDaResolucaoDaPendenciaDaTaxa(Taxa taxa){
        AssertUtils.assertNotNull(taxa, ConstantesI18N.PENDENCIA_TAXA_OBRIGATORIO);
    }
     
    public void validarExclusaoDaPendencia(Pendencia pendencia){
        AssertUtils.assertExpression(pendencia.getDataSolucao() == null, ConstantesI18N.PENDENCIA_JA_RESOLVIDA);
    } 
    
    public void validarRemocaoDaResolucaoDaPendencia(Pendencia pendencia){
        AssertUtils.assertNotNull(pendencia, ConstantesI18N.PENDENCIA_AGENCIAMENTO_OBRIGATORIO);
    }
   
}
