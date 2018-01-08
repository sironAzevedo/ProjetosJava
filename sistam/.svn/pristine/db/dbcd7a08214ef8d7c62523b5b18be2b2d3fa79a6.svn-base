package br.com.petrobras.sistam.service.validator;


import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorOperacao {
    
    public void validarSalvarOperacao(Operacao operacao) {
        AssertUtils.assertNotNull(operacao.getAgenciamento(), ConstantesI18N.OPERACAO_AGENCIAMENTO_NULO);
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(operacao.getTipoOperacao()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.OPERACAO_INFORME_TIPO_OPERACAO);
        if(!operacao.isTipoSemOperacaoComercial()){
            pm.assertNotNull(operacao.getProduto()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.OPERACAO_INFORME_PRODUTO);
            pm.assertNotNull(operacao.getQuatidadeEstimada()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.OPERACAO_INFORME_QUANTIDADE_ESTIMADA);
            pm.assertNotNull(operacao.getUnidadeMedida()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.OPERACAO_INFORME_UNIDADE_MEDIDA);
        }
        pm.verifyAll();
    }
    
    public void validarSalvarDocumentacaoOperacao(DocumentacaoCabotagem documentacaoOperacao) {
        AssertUtils.assertNotNull(documentacaoOperacao.getAgenciamento(), ConstantesI18N.DOCUMENTACAO_OPERACAO_AGENCIAMENTO_NULO);
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(documentacaoOperacao.getTipoOperacao()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_TIPO_OPERACAO);
        
        if(TipoOperacao.CARGA_CABOTAGEM.equals(documentacaoOperacao.getTipoOperacao())){
            Porto porto = documentacaoOperacao.getPorto();
            pm.assertNotNull(porto).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_PORTO_DESTINO);
            pm.assertThat(porto != null && porto.isNacional()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_OPERACAO_PORTO_DEVE_SER_NACIONAL);
        }
        else if(TipoOperacao.DESCARGA_CABOTAGEM.equals(documentacaoOperacao.getTipoOperacao())){
            pm.assertNotNull(documentacaoOperacao.getAgencia()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_AGENCIA_ORIGEM);
        }
        pm.assertNotEmpty(documentacaoOperacao.getManifestoEletronico()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_MANIFESTO_ELETRONICO);
        pm.verifyAll();
    }
    
    public void validarSalvarDocumentacaoProduto(DocumentacaoOperacao documentacaoProduto) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(documentacaoProduto.getOperacao()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_PRODUTO_OPERACAO_NULL);
        pm.assertNotNull(documentacaoProduto.getDocumentacaoOperacao()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_PRODUTO_DOCUMENTACAO_OPERACAO_NULL);
        pm.assertNotEmpty(documentacaoProduto.getConhecimentoEletronico()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_PRODUTO_INFORME_CONHECIMENTO_ELETRONICO);
        pm.verifyAll();
        
    }

    public void validarSalvarDocumentacaoLongoCurso(DocumentacaoLongoCurso documentacaoLongoCurso) {
        
        AssertUtils.assertNotNull(documentacaoLongoCurso.getOperacao(), ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_OPERACAO_NULA);
        
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(documentacaoLongoCurso.getPorto()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_INFORME_PORTO);
        pm.assertThat(documentacaoLongoCurso.getPorto() == null || !documentacaoLongoCurso.getPorto().getPais().getId().equals(Pais.CODIGO_BRASIL)).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_PAIS_DEVE_SER_DIFERENTE_BRASIL);
        pm.assertNotEmpty(documentacaoLongoCurso.getCidade()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_INFORME_CIDADE);
        pm.assertNotNull(documentacaoLongoCurso.getManifestoEletronico()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_INFORME_MANIFESTO_ELETRONICO);
        pm.assertNotEmpty(documentacaoLongoCurso.getListaConhecimentoEletronico()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_INFORME_CONHECIMENTO_ELETRONICO);
        pm.verifyAll();
        
    }
    
    
    
}
