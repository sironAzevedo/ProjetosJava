package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.DocumentoBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Test;

public class ValidadorDocumentoTest {

    private ValidadorDocumento validador = new ValidadorDocumento();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Documento">
    @Test
    public void naoSeraPermitidoSalvarDocumentoSemInformarOAgenciamento(){
        Documento documento = obterDocumentoValido();
        documento.setAgenciamento(null);
        try{
            validador.validarSalvarDocumento(documento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_AGENCIAMENTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarDocumentoSemInformarOTipo(){
        Documento documento = obterDocumentoValido();
        documento.setTipoDocumento(null);
        try{
            validador.validarSalvarDocumento(documento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_TIPO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarDocumentoComOTipoRegistroDeMovimentacaoSemInformarAManobra(){
        Documento documento = obterDocumentoValido();
        documento.setTipoDocumento(TipoDocumento.REGISTRO_MOVIMENTACAO);
        documento.setManobra(null);
        try{
            validador.validarSalvarDocumento(documento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_MANOBRA_ENCERRADA_NAO_ASSOCIADA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarDocumentoComOTipoBillOfLadingSemInformarAOperacao(){
        Documento documento = obterDocumentoValido();
        documento.setTipoDocumento(TipoDocumento.BILL_OF_LADING);
        documento.setOperacao(null);
        try{
            validador.validarSalvarDocumento(documento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_OPERACAO_NAO_ASSOCIADA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarDocumentoSemInformarADataDeEmissao(){
        Documento documento = obterDocumentoValido();
        documento.setDataEmissao(null);
        try{
            validador.validarSalvarDocumento(documento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_DATA_EMISSAO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarDocumentoSemInformarAChaveDoEmitente(){
        Documento documento = obterDocumentoValido();
        documento.setChaveEmitente(null);
        try{
            validador.validarSalvarDocumento(documento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_NOME_EMITENTE, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarDocumentoSemInformarONomeDoEmitente(){
        Documento documento = obterDocumentoValido();
        documento.setNomeEmitente(null);
        try{
            validador.validarSalvarDocumento(documento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_NOME_EMITENTE, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Excluir Documento">
    @Test
    public void naoSeraPermitidoExcluirUmDocumentoProtocoloado(){
        Documento documento = obterDocumentoValido();
        documento.setDataProtocolo(new Date());
        
        try{
            validador.validarExcluirDocumento(documento);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.DOCUMENTO_EXCLUSAO_NAO_PERMIIDA_DOCUMENTO_PROTOCOLOADO, ex.getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Registro da Emissão de Documento">
    @Test
    public void naoSeraPermitidoRegistrarEmissaoDeDocumentoDoAgenciamentoSemInformarOTipo(){
        TipoDocumento tipo = null;
        Agenciamento agenciamento = new Agenciamento();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        try{
            validador.validarRegistrarEmissaoDeDocumento(tipo, agenciamento, representante);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_TIPO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoRegistrarEmissaoDeDocumentoDoAgenciamentoSemInformarOAgenciamento(){
        TipoDocumento tipo = TipoDocumento.COMUNICACAO_CHEGADA;
        Agenciamento agenciamento = null;
        RepresentanteLegal representante = new RepresentanteLegal();
        
        try{
            validador.validarRegistrarEmissaoDeDocumento(tipo, agenciamento, representante);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_AGENCIAMENTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoRegistrarEmissaoDeDocumentoSolicitacaoDeCertificadoSemInformarRepresentante(){
        TipoDocumento tipo = TipoDocumento.SOLICITACAO_CERTIFICADO;
        Agenciamento agenciamento = new Agenciamento();
        RepresentanteLegal representante = null;
        
        try{
            validador.validarRegistrarEmissaoDeDocumento(tipo, agenciamento, representante);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_REPRESENTANTE_LEGAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoRegistrarEmissaoDeDocumentoComunicacaoDeChegadoSemInformarRepresentante(){
        TipoDocumento tipo = TipoDocumento.COMUNICACAO_CHEGADA;
        Agenciamento agenciamento = new Agenciamento();
        RepresentanteLegal representante = null;
        
        try{
            validador.validarRegistrarEmissaoDeDocumento(tipo, agenciamento, representante);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_REPRESENTANTE_LEGAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoRegistrarEmissaoDeDocumentoParteDeEntradaSemInformarRepresentante(){
        TipoDocumento tipo = TipoDocumento.PARTE_ENTRADA;
        Agenciamento agenciamento = new Agenciamento();
        RepresentanteLegal representante = null;
        
        try{
            validador.validarRegistrarEmissaoDeDocumento(tipo, agenciamento, representante);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_REPRESENTANTE_LEGAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoRegistrarEmissaoDeDocumentoDaManobraSemInformarOTipo(){
        TipoDocumento tipo = null;
        Manobra manobra = new Manobra();
        RepresentanteLegal representante = new RepresentanteLegal();
        
        try{
            validador.validarRegistrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_TIPO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoRegistrarEmissaoDeDocumentoDaManobraSemInformarAManobra(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = null;
        RepresentanteLegal representante = new RepresentanteLegal();
        
        try{
            validador.validarRegistrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_MANOBRA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoRegistrarEmissaoDeDocumentoDaManobraSemInformarRepresentante(){
        TipoDocumento tipo = TipoDocumento.REGISTRO_MOVIMENTACAO;
        Manobra manobra = new Manobra();
        
        
        try{
            validador.validarRegistrarEmissaoDeDocumentoDaManobra(tipo, manobra, null);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_REPRESENTANTE_LEGAL, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Protocoloar Doucumento">
    @Test
    public void naoSeraPermitidoProtocolarODocumentoSemInforarAData(){
        Documento documento = new Documento();
        try{
            validador.validarProtocolarDocumento(documento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DOCUMENTO);
            assertEquals(ConstantesI18N.DOCUMENTO_INFORME_DATA_PROTOCOLO, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>
    
    private Documento obterDocumentoValido() {
            Documento documento = DocumentoBuilder.novoDocumento()
                    .comId(1L)
                    .comAgenciamento(new Agenciamento())
                    .comTipo(TipoDocumento.COMUNICACAO_CHEGADA)
                    .comDataDeEmissao(new Date())
                    .comChaveEmitente("STAM")
                    .comNomeEmitente("SISTAM - Agenciamento Marítimo")
                    .build();
        return documento;
    }
    
    
    

}
