package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDocumento {
        
    public void validarSalvarDocumento(Documento documento){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(documento.getAgenciamento()).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_AGENCIAMENTO);
        pm.assertNotNull(documento.getTipoDocumento()).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_TIPO);
        
        if (TipoDocumento.REGISTRO_MOVIMENTACAO.equals(documento.getTipoDocumento())){
            pm.assertNotNull(documento.getManobra()).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_MANOBRA_ENCERRADA_NAO_ASSOCIADA);
        }
        else if (TipoDocumento.BILL_OF_LADING.equals(documento.getTipoDocumento())){
            pm.assertNotNull(documento.getOperacao()).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_OPERACAO_NAO_ASSOCIADA);
        }
        pm.assertNotNull(documento.getDataEmissao()).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_DATA_EMISSAO);
        pm.assertThat(null != documento.getChaveEmitente() && null != documento.getNomeEmitente() ).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_NOME_EMITENTE);
        pm.verifyAll();
    }
    
    public void validarExcluirDocumento(Documento documento){
        AssertUtils.assertExpression(documento.getDataProtocolo() == null, ConstantesI18N.DOCUMENTO_EXCLUSAO_NAO_PERMIIDA_DOCUMENTO_PROTOCOLOADO);
    }
    
    public void validarRegistrarEmissaoDeDocumento(TipoDocumento tipo, Agenciamento agenciamento, RepresentanteLegal representante){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(tipo).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_TIPO);
        pm.assertNotNull(agenciamento).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_AGENCIAMENTO);
        
        if (TipoDocumento.SOLICITACAO_CERTIFICADO.equals(tipo)
                || TipoDocumento.COMUNICACAO_CHEGADA.equals(tipo)
                || TipoDocumento.PARTE_ENTRADA.equals(tipo)){
            pm.assertNotNull(representante).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_REPRESENTANTE_LEGAL);
        }
        pm.verifyAll();
        
    }
    
    public void validarRegistrarEmissaoDeDocumentoDaManobra(TipoDocumento tipo, Manobra manobra, RepresentanteLegal representante){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(tipo).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_TIPO);
        pm.assertNotNull(manobra).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_MANOBRA);
        pm.assertNotNull(representante).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_REPRESENTANTE_LEGAL);
        pm.verifyAll();
    }
    
    public void validarProtocolarDocumento(Documento documento){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(documento.getDataProtocolo()).orRegister(TipoExcecao.DOCUMENTO, ConstantesI18N.DOCUMENTO_INFORME_DATA_PROTOCOLO);
        pm.verifyAll();
    }
    
}
