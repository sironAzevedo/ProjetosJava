package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.sistam.test.builder.ManobraBuilder;
import br.com.petrobras.sistam.test.builder.PontoAtracacaoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoManobraBuilder;
import br.com.petrobras.sistam.test.builder.ServicoResponsavelBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class ValidadorManobraTest {

    private ValidadorManobra validador = new ValidadorManobra();
    
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Manobra">
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemAgenciamento() {
        Manobra manobra = obterManobraValida();
        manobra.setAgenciamento(null);
        try {
            validador.validarCamposObrigatoriosManobra(manobra);
            fail("Uma manobra não pode ser salva sem um agenciamento associado");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_AGENCIAMENTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemStatus() {
        Manobra manobra = obterManobraValida();
        manobra.setStatus(null);
        try {
            validador.validarCamposObrigatoriosManobra(manobra);
            fail("Uma manobra não pode ser salva sem status");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_STATUS_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemFinalidade() {
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(null);
        try {
            validador.validarCamposObrigatoriosManobra(manobra);
            fail("Uma manobra não pode ser salva sem finalidade");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_FINALIDADE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemResponsavelCusto() { 
        Manobra manobra = obterManobraValida(); 
        manobra.setResponsavelCusto(null);
        try {
            //validador.validarCamposObrigatoriosManobra(manobra);
            //fail("Uma manobra não pode ser salva sem responsável pelo custo");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.INFORME_REPONSAVEL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemPontoOrigem() {
        Manobra manobra = obterManobraValida();
        manobra.setPontoAtracacaoOrigem(null);
        try {
            validador.validarCamposObrigatoriosManobra(manobra);
            fail("Uma manobra não pode ser salva sem responsável pelo custo");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_PONTO_INICIAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemPontoDestino() {
        Manobra manobra = obterManobraValida();
        manobra.setPontoAtracacaoDestino(null);
        try {
            validador.validarCamposObrigatoriosManobra(manobra);
            fail("Uma manobra não pode ser salva sem responsável pelo custo");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_PONTO_FINAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemCaladoVante() {
        Manobra manobra = obterManobraValida();
        manobra.setCaladoVante(null);
        try {
            validador.validarCamposObrigatoriosManobra(manobra);
            fail("Uma manobra não pode ser salva sem responsável pelo custo");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_CALADO_VANTE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemCaladoRe() {
        Manobra manobra = obterManobraValida();
        manobra.setCaladoRe(null);
        try {
            validador.validarCamposObrigatoriosManobra(manobra);
            fail("Uma manobra não pode ser salva sem responsável pelo custo");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_CALADO_RE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
   
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraJaCancelada(){
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.setStatus(StatusManobra.CANCELADA);
        
        try {
            validador.validarSalvarManobra(manobra);
            fail("Uma manobra CANCELADA não pode salva");
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MANOBRA_SALVAR_MANOBRA_CANCELADA, ex.getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarManobraSemServico(){
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.removerTodosServicos();
        
        try {
            validador.validarSalvarManobra(manobra);
            fail("Uma manobra não pode ser registrada sem serviço");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_PELO_MENOS_UM_SERVICO, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Movimentar Manobras">
    @Test
    public void deveRetornarExcecaoQuandoMovimentarManobraSemAgenciamento() {
        Manobra manobra = obterManobraValida();
        manobra.setAgenciamento(null);
        try {
            validador.validarMovimentarManobra(manobra);
            fail("Uma manobra não pode ser movimentada sem um agenciamento associado");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_AGENCIAMENTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoMovimentarManobraSemStatus() {
        Manobra manobra = obterManobraValida();
        manobra.setStatus(null);
        try {
            validador.validarMovimentarManobra(manobra);
            fail("Uma manobra não pode ser movimentada sem status");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_STATUS_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoMovimentarManobraSemFinalidade() {
        Manobra manobra = obterManobraValida();
        manobra.setFinalidadeManobra(null);
        try {
            validador.validarMovimentarManobra(manobra);
            fail("Uma manobra não pode ser movimentada sem finalidade");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_FINALIDADE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoMovimentarManobraSemResponsavelCusto() { 
        Manobra manobra = obterManobraValida(); 
        manobra.setResponsavelCusto(null);
        try {
            validador.validarMovimentarManobra(manobra);
             fail("Uma manobra não pode ser movimentada sem responsável pelo custo");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.INFORME_REPONSAVEL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoMovimentarManobraSemPontoAtracacaoOrigem() {
        Manobra manobra = obterManobraValida();
        manobra.setPontoAtracacaoOrigem(null);
        try {
            validador.validarMovimentarManobra(manobra);
            fail("Uma manobra não pode ser movimentada sem ponto origem");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_PONTO_INICIAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoMovimentarManobraSemPontoDestino() {
        Manobra manobra = obterManobraValida();
        manobra.setPontoAtracacaoDestino(null);
        try {
            validador.validarMovimentarManobra(manobra);
            fail("Uma manobra não pode ser movimentada sem ponto destino");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_PONTO_FINAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoMovimentarManobraSemDataTermino() {
        Manobra manobra = obterManobraValida();
        manobra.setDataTermino(null);
        try {
            validador.validarMovimentarManobra(manobra);
            fail("Uma manobra não pode ser movimentada sem data termino");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_TERMINO, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Exclusão da Manobra">
    @Test
    public void deveRetornarExcecaoQuandoTentarExcluirManobraJaSolicitada() {
        Manobra manobra = obterManobraValida();
        manobra.setStatus(StatusManobra.SOLICITADA);
        try {
            validador.validarExclusaoManobra(manobra);
            fail("Uma manobra já solicitada não pode ser excluida");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_JA_SOLICITADA_OU_REGISTRADA_NAO_PODE_SER_EXCLUIDA, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Registro de Manobras">
    @Test
    public void deveRetornarExcecaoQuandoRegistrarManobraCancelada() {
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.setStatus(StatusManobra.CANCELADA);
        try {
            validador.validarRegistrarManobra(manobra);
            fail("Uma manobra cancelada não pode ser registrada");
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MANOBRA_SALVAR_MANOBRA_CANCELADA, ex.getMessage());
        }
    }
    
    
    @Test
    public void deveRetornarExcecaoQuandoRegistrarManobraSemDataInicio() {
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.setDataInicio(null);
        try {
            validador.validarRegistrarManobra(manobra);
            fail("Uma manobra não pode ser registrada sem data início");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_INICIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoRegistrarManobraSemDataTermino() {
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.setDataTermino(null);
        try {
            validador.validarRegistrarManobra(manobra);
            fail("Uma manobra não pode ser registrada sem data de termino");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_TERMINO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoRegistrarManobraSemDataDePartidaOrigem() {
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.setDataPartidaOrigem(null);
        try {
            validador.validarRegistrarManobra(manobra);
            fail("Uma manobra não pode ser registrada sem data de partida da origem");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_PARTIDA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoRegistrarManobraSemDataDeChegadaDestino() {
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.setDataChegadaDestino(null);
        try {
            validador.validarRegistrarManobra(manobra);
            fail("Uma manobra não pode ser registrada sem data de chegada no destino");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_CHEGADA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoRegistrarManobraComServicoSemResponsavel(){
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.getServicos().get(0).removerTodosResponsaveis();
        
        try {
            validador.validarRegistrarManobra(manobra);
            fail("Uma manobra não pode ser registrada com serviço sem responsável");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_INFORME_PELO_MENOS_UM_RESPONSAVEL, pendencias.get(0).getMessage());
        }
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Cancelar manobra fora de prazo">
    @Test
    public void deveRetornarExcecaoQuandoCancelarManobraCancelada() {
        Manobra manobra = obterManobraValidaParaCancelamentoForaDePrazo();
        manobra.setStatus(StatusManobra.CANCELADA);
        try {
            validador.validarCancelarManobraForaDoPrazo(manobra, null);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MANOBRA_SALVAR_MANOBRA_CANCELADA, ex.getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoCancelarManobraSemDataInicio() {
        Manobra manobra = obterManobraValidaParaCancelamentoForaDePrazo();
        manobra.setDataInicio(null);
        try {
            validador.validarCancelarManobraForaDoPrazo(manobra, "Motivo de teste");
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_INICIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoCancelarManobraSemDataTermino() {
        Manobra manobra = obterManobraValidaParaCancelamentoForaDePrazo();
        manobra.setDataTermino(null);
        try {
            validador.validarCancelarManobraForaDoPrazo(manobra, "Motivo de teste");
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_TERMINO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoCancelarManobraSemDataDePartidaOrigem() {
        Manobra manobra = obterManobraValidaParaCancelamentoForaDePrazo();
        manobra.setDataPartidaOrigem(null);
        try {
            validador.validarCancelarManobraForaDoPrazo(manobra, "Motivo de teste");
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_PARTIDA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoCancelarManobraSemDataDeChegadaDestino() {
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.setDataChegadaDestino(null);
        try {
            validador.validarCancelarManobraForaDoPrazo(manobra, "Motivo de teste");
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_DATA_CHEGADA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoCancelarManobraComMotivoNulo() {
        Manobra manobra = obterManobraValidaParaRegistro();
        try {
            validador.validarCancelarManobraForaDoPrazo(manobra, null);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_MOTIVO_CANCELAMENTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoCancelarManobraComMotivoEmBranco() {
        Manobra manobra = obterManobraValidaParaRegistro();
        try {
            validador.validarCancelarManobraForaDoPrazo(manobra, "");
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_MOTIVO_CANCELAMENTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoCancelarManobraForaDePrazoSemServico(){
        Manobra manobra = obterManobraValidaParaCancelamentoForaDePrazo();
        manobra.removerTodosServicos();
        
        try {
            validador.validarCancelarManobraForaDoPrazo(manobra, "Motivo");
            fail("Uma manobra não pode ser cancelada fora de prazo sem serviço");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_PELO_MENOS_UM_SERVICO, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Encerramento de Manobra">
    @Test
    public void deveRetornarExcecaoQuandoEncerrarManobraNaoRegistrada(){
        Manobra manobra = obterManobraValidaParaRegistro();
        manobra.setStatus(StatusManobra.SOLICITADA);
        
        try{
            validador.validarEncerrarManobra(manobra, null, null);
            fail();
        }catch(BusinessException ex) {
            assertEquals(ConstantesI18N.MANOBRA_ENCERRAR_APENAS_AS_REGISTRADAS, ex.getMessage());
        }
        
    }
    
    @Test
    public void deveRetornarExcecaoQuandoAFinalidadeForSaidaDeFundeioEOPortoDestinoNaoForInformado(){
         Manobra manobra = obterManobraValidaParaRegistro();
         manobra.setStatus(StatusManobra.REGISTRADA);
         manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        
        try {
            validador.validarEncerrarManobra(manobra, null, null);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_PORTO_DESTINO, pendencias.get(0).getMessage());
        }
    }
    
    
    @Test
    public void deveRetornarExcecaoQuandoAFinalidadeForSaidaDeFundeioEOEtaNaoForInformado(){
         Manobra manobra = obterManobraValidaParaRegistro();
         manobra.setStatus(StatusManobra.REGISTRADA);
         manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        
        try {
            validador.validarEncerrarManobra(manobra, new Porto(), null);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_ETA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoAFinalidadeForDesatracacaoSaidaEOPortoDestinoNaoForInformado(){
         Manobra manobra = obterManobraValidaParaRegistro();
         manobra.setStatus(StatusManobra.REGISTRADA);
         manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        
        try {
            validador.validarEncerrarManobra(manobra, null, null);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_PORTO_DESTINO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoAFinalidadeForDesatracacaoSaidaEOEtaNaoForInformado(){
         Manobra manobra = obterManobraValidaParaRegistro();
         manobra.setStatus(StatusManobra.REGISTRADA);
         manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        
        try {
            validador.validarEncerrarManobra(manobra, new Porto(), null);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_INFORME_ETA, pendencias.get(0).getMessage());
        }
    }
    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serviço Manobra">
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoManobraSemManobra() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.setManobra(null);
        try {
            validador.validarSalvarServicoManobra(servicoManobra);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_MANOBRA_OBRIGATORIA, ex.getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoManobraSemTipoServico() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.setTipoDoServico(null);
        try {
            validador.validarSalvarServicoManobra(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_INFORME_TIPO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoManobraSemEmpesaMaritima() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.setEmpresaMaritima(null);
        try {
            validador.validarSalvarServicoManobra(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_INFORME_EMPRESA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoDeUmaManobraPlanejada(){
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.getManobra().setStatus(StatusManobra.PLANEJADA);
        servicoManobra.setDataProgramada(null);
        try {
            validador.validarSalvarServicoManobra(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_INFORME_DATA_PROGRAMADA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoDeUmaManobraSolicitada(){
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.getManobra().setStatus(StatusManobra.SOLICITADA);
        servicoManobra.setDataProgramada(null);
        try {
            validador.validarSalvarServicoManobra(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_INFORME_DATA_PROGRAMADA, pendencias.get(0).getMessage());
        }
    }
    
     @Test
    public void deveRetornarExcecaoQuandoSalvarServicoManobraRegistradaSemInformarAlgumResposavel() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.getManobra().setStatus(StatusManobra.REGISTRADA);
        servicoManobra.removerTodosResponsaveis();
        try {
            validador.validarSalvarServicoManobra(servicoManobra);
            fail("O serviço não pode ser salvo sem inofrmar algum responsável");
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_INFORME_PELO_MENOS_UM_RESPONSAVEL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoManobraJaAdicionadoAManobra() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        ServicoManobra servicoManobraB = obterServicoManobraValido();
        servicoManobraB.setId(20L);
        servicoManobra.getManobra().adicionarServico(servicoManobra);
        servicoManobra.getManobra().adicionarServico(servicoManobraB);
        try {
            validador.validarSalvarServicoManobra(servicoManobra);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_SERVICO_JA_ADICIONADO_NA_MANOBRA, ex.getMessage());
        }
    }
    
    
    @Test
    public void deveRetornarExcecaoQuandoExcluirUmServicoDeUmaManobraExistenteSendoEsteOUnicoServicoDaManobra(){
        Manobra manobra = obterManobraValida();
        manobra.removerTodosServicos();
        
        ServicoManobra servico = obterServicoManobraValido();
        manobra.adicionarServico(servico);
        
        try {
            validador.validarExclusaoServicoManobra(servico);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_MANOBRA_MANOBRA_DEVE_TER_PELO_MENOS_UM_SERVICO, ex.getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Responsável pelo Serviço Manobra">
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoResponsavelSemServicoManobra() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        servicoResponsavel.setServicoManobra(null);
        try {
            validador.validarSalvarResponsavel(servicoResponsavel);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_RESPONSAVEL_SERVICO_MANOBRA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoResponsavelSemServico() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        servicoResponsavel.setServico(null);
        try {
            validador.validarSalvarResponsavel(servicoResponsavel);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_RESPONSAVEL_INFORME_NOME, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoResponsavelSemDataInicio() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        servicoResponsavel.setDataInicio(null);
        try {
            validador.validarSalvarResponsavel(servicoResponsavel);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_RESPONSAVEL_INFORME_DATA_INICIO_REAL, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoResponsavelSemDataTermino() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        servicoResponsavel.setDataTermino(null);
        try {
            validador.validarSalvarResponsavel(servicoResponsavel);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_RESPONSAVEL_INFORME_DATA_TERMINO_REAL, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoResponsavelComDataInicioMaioQueDataTermino() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        Calendar c = Calendar.getInstance();
        c.setTime(servicoResponsavel.getDataTermino());
        c.add(Calendar.DATE, 1);
        servicoResponsavel.setDataInicio(c.getTime());
        try {
            validador.validarSalvarResponsavel(servicoResponsavel);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SERVICO_RESPONSAVEL_DATA_INICIO_MAIOR_DATA_TERMINO, pendencias.get(0).getMessage());
        }
    }
    
    
    @Test
    public void deveRetornarExcecaoQuandoSalvarServicoResponsavelJaAdicionado() {
        ServicoResponsavel servicoResponsavel = obterServicoResponsavelValido();
        ServicoResponsavel servicoResponsavelB = obterServicoResponsavelValido();
        servicoResponsavelB.setId(20L);
        servicoResponsavel.getServicoManobra().adicionarResponsavel(servicoResponsavel);
        servicoResponsavel.getServicoManobra().adicionarResponsavel(servicoResponsavelB);
        try {
            validador.validarSalvarResponsavel(servicoResponsavel);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_RESPONSAVEL_RESPONSAVEL_JA_ADICIONADO, ex.getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoExcluirUmResponsavelDeUmServicoExistenteSendoEsteOUnicoResponsavelDoServico(){
        ServicoManobra servico = obterServicoManobraValido();
        servico.removerTodosResponsaveis();
        
        ServicoResponsavel responsavel = obterServicoResponsavelValido();
        servico.adicionarResponsavel(responsavel);
        
        try {
            validador.validarExclusaoDeResponsavel(responsavel);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_RESPONSAVEL_SERVICO_DEVE_TER_PELO_MENOS_UM_RESPONSAVEL, ex.getMessage());
        }
    }
    
    
    
    //</editor-fold>

    
    @Test
    public void deveRetornarExcecaoQuandoCancelarDentroDoPrazoManobraSemMotivo() {
        Manobra manobra = obterManobraValida();
        manobra.setStatus(StatusManobra.SOLICITADA);
        try {
            validador.validarCancelarManobraDentroPrazo(manobra, null);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_CANCELAR_DENTRO_PRAZO_SEM_MOTIVO, pendencias.get(0).getMessage());
        }
    }
    
    
    @Test
    public void deveRetornarExcecaoQuandoCancelarDentroDoPrazoManobraComStatusDiferenteDeSolicitada() {
        Manobra manobra = obterManobraValida();
        manobra.setStatus(StatusManobra.REGISTRADA);
        try {
            validador.validarCancelarManobraDentroPrazo(manobra, "TESTE");
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.MANOBRA_SOMENTE_STATUS_SOLICITADA_PODE_SER_CANCELADA_DENTRO_PRAZO, pendencias.get(0).getMessage());
        }
    }
    
    
    
    private Manobra obterManobraValida() { 
        ResponsavelCustoEntity responsavelCusto = ResponsavelCustoBuilder.novoResponsavelCusto().comId(1L).build(); 
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build()) 
                .comStatus(StatusManobra.PLANEJADA)
                .comCaladoVante(12D)
                .comCaladoRe(12D)
                .comResponsavelCusto(responsavelCusto)
                .comPontoAtracacaoOrigem(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comPontoAtracacaoDestino(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comFinalidadeManobra(FinalidadeManobra.ATRACACAO)
                .build();
        return manobra;
    }

    private Manobra obterManobraValidaParaRegistro() {
        ResponsavelCustoEntity responsavelCusto = ResponsavelCustoBuilder.novoResponsavelCusto().comId(1L).build();
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build())
                .comResponsavelCusto(responsavelCusto)
                .comStatus(StatusManobra.SOLICITADA)
                .comCaladoVante(12D)
                .comCaladoRe(12D)
                .comPontoAtracacaoOrigem(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comPontoAtracacaoDestino(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comFinalidadeManobra(FinalidadeManobra.ATRACACAO)
                .comDataInicio(new Date())
                .comDataTermino(new Date())
                .comDataPartidaOrigem(new Date())
                .comDataChegadaDestino(new Date())
                .build();
        manobra.adicionarServico(new ServicoManobra());
        return manobra;
    }
    
    private Manobra obterManobraValidaParaCancelamentoForaDePrazo() {
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build())
                //.comResponsavelCusto(ResponsavelCusto.PETROBRAS)
                .comStatus(StatusManobra.REGISTRADA)
                .comCaladoVante(12D)
                .comCaladoRe(12D)
                .comPontoAtracacaoOrigem(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comPontoAtracacaoDestino(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comFinalidadeManobra(FinalidadeManobra.ATRACACAO)
                .comDataInicio(new Date())
                .comDataTermino(new Date())
                .comDataPartidaOrigem(new Date())
                .comDataChegadaDestino(new Date())
                .build();
        manobra.adicionarServico(new ServicoManobra());
        return manobra;
    }
    
    private ServicoManobra obterServicoManobraValido() {
        ServicoManobra servicoManobra = ServicoManobraBuilder.novoServicoManobra()
                .comId(1L)
                .comTipoServico(TipoServico.PRATICOS)
                .comDataProgramada(new Date())
                .daManobra(obterManobraValida())
                .daEmpresa(EmpresaMaritimaBuilder.novaEmpresaMaritima().comId(12L).comCnpj("1324").build())
                .build();
        servicoManobra.adicionarResponsavel(new ServicoResponsavel());
        return servicoManobra;
    }
    
    private ServicoResponsavel obterServicoResponsavelValido() {
        ServicoResponsavel servicoResponsavel = ServicoResponsavelBuilder.novoServicoResponsavel()
                .doServicoManobra(obterServicoManobraValido())
                .comServico(ServicoBuilder.novoServico().comId(1L).build())
                .comDataInicio(new Date())
                .comDataTermino(new Date())
                .comId(1L)
                .build();
        return servicoResponsavel;
    }
    
 
   
    
}
