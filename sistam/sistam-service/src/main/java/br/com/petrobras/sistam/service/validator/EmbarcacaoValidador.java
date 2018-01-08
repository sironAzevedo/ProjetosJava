package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class EmbarcacaoValidador {
    public void validarSalvarEmbarcacao(Embarcacao embarcacao){
        SistamPendencyManager pm = new SistamPendencyManager();  
        pm.assertNotEmpty(embarcacao.getId()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_CODIGO_OBRIGATORIO);
        pm.assertNotEmpty(embarcacao.getNomeCompleto()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_NOME_OBRIGATORIO);
        pm.assertNotEmpty(embarcacao.getApelido()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_APELIDO_OBRIGATORIO);
        pm.assertNotNull(embarcacao.getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);
        pm.assertNotEmpty(embarcacao.getIrin()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IRIN_OBRIGATORIO);
        pm.assertNotEmpty(embarcacao.getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);
        pm.assertNotNull(embarcacao.getClassificacao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA);
        pm.assertNotNull(embarcacao.getDataConstrucao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_DT_CONSTRUCAO_OBRIGATORIO);
        pm.assertNotNull(embarcacao.getTipoEmbarcacao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_TIPO_OBRIGATORIO);
        pm.assertNotNull(embarcacao.getHeliporto()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_HELIPORTO_OBRIGATORIO);
        pm.verifyAll();
    }
    
}
