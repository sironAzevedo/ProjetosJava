package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAnexo {
    
    public void validarSalvarAnexo(Anexo anexo){
        SistamPendencyManager pm = new SistamPendencyManager();
        
        
        pm.assertNotNull(anexo.getAgenciamento()).orRegister(TipoExcecao.ANEXO, ConstantesI18N.ANEXO_AGENCIAMENTO_OBRIGATORIO);
        pm.assertNotNull(anexo.getPasta()).orRegister(TipoExcecao.ANEXO, ConstantesI18N.ANEXO_PASTA_OBRIGATORIO);
        pm.assertNotNull(anexo.getNome()).orRegister(TipoExcecao.ANEXO, ConstantesI18N.ANEXO_NOME_OBRIGATORIO);
        pm.assertNotNull(anexo.getExtensao()).orRegister(TipoExcecao.ANEXO, ConstantesI18N.ANEXO_EXTENSAO_OBRIGATORIO);
        pm.assertNotNull(anexo.getTamanhoEmBytes()).orRegister(TipoExcecao.ANEXO, ConstantesI18N.ANEXO_TAMANHO_OBRIGATORIO);
        pm.assertNotNull(anexo.getArquivo()).orRegister(TipoExcecao.ANEXO, ConstantesI18N.ANEXO_ARQUIVO_OBRIGATORIO);
        pm.assertThat(anexo.getNome() == null || anexo.getNome().length() <= 60 ).orRegister(TipoExcecao.ANEXO, ConstantesI18N.ANEXO_NOME_ARQUIVO_GRANDE);
        
        pm.verifyAll();
        
        AssertUtils.assertExpression(anexo.getAgenciamento().getEspacoDiponivelParaArquivosEmBytes() >= anexo.getTamanhoEmBytes(),
                ConstantesI18N.ANEXO_ESPACO_INSUFICIENTE_NO_AGENCIAMENTO);
        
        AssertUtils.assertExpression(anexo.getTamanhoEmBytes() > 0, ConstantesI18N.ANEXO_ARQUIVO_VAZIO);
    }
    
    
}
