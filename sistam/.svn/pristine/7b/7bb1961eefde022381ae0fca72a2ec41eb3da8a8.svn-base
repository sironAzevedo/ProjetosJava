package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import br.com.petrobras.sistam.common.enums.MotivoDesvio;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamentoAtendimento;
import br.com.petrobras.sistam.test.builder.AcompanhamentoBuilder;
import static br.com.petrobras.sistam.test.builder.AgenciamentoBuilder.novoAgenciamento;
import br.com.petrobras.sistam.test.builder.DesvioBuilder;
import static br.com.petrobras.sistam.test.builder.PortoBuilder.novoPorto;
import br.com.petrobras.snarf.common.exception.Pendency;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;


public class ValidadorAgenciamentoTest {
    private ValidadorAgenciamento validador = new ValidadorAgenciamento();
    
   
    //<editor-fold defaultstate="collapsed" desc="Criação de um novo agenciamento">
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarAEmbarcacao(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setEmbarcacao(null);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_EMBARCACAO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarAViagem(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setVgm(null);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_VIAGEM, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarAAgencia(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setAgencia(null);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_AGENCIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarOPortoAtual(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setPorto(null);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_ATUAL, pendencias.get(0).getMessage());
        }
    }
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarOPortoOrigem(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setPortoOrigem(null);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_ORIGEM, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarADataEstimadaDeChagada(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setEta(null);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_ETA, pendencias.get(0).getMessage());
        }
    }
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarOTipoContrato(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setTipoContrato(null);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_CONTRATO, pendencias.get(0).getMessage());
        }
    }
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarOTipoArmador(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setTipoArmador(null);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_ARMADOR, pendencias.get(0).getMessage());
        }
    } 
    
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarOTipoAgenciamento(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setTipoContrato(TipoContrato.TCP);
        agenciamento.setAgenciamentoCarga(Boolean.FALSE);
        agenciamento.setAgenciamentoProtecao(Boolean.FALSE);
        
        try{
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_AGENCIAMENTO, pendencias.get(0).getMessage());
        }
    }
    
      
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemInformarOTipoFrota() {
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setTipoFrota(null);

        try {
            validador.validarIniciarAgenciamento(agenciamento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_INFORME_TIPO_FROTA, pendencias.get(0).getMessage());
        }
    }
    
    private Agenciamento obterNovoAgenciamentoValido(){
        Agenciamento agenciamento = novoAgenciamento()
                .comAgencia(new Agencia())
                .comEmbarcacao(new Embarcacao())
                .comViagem("11234")
                .comPortoOrigem(new Porto())
                .comPortoAtual(new Porto())
                .comAgenciamentoCarga()
                .comAnoProcesso(2013)
                .comNomeCadastrador("Teste")
                .comChaveCadastrador("stam")
                .comTipoFrota(TipoFrota.NAO_APLICAVEL)
                .comDataEstimadaDeChegada(DateBuilder.on(15, 10, 2013).getTime())
                .build();
        
        return agenciamento;
    }
    
    //</editor-fold>
    
    
    @Test
    public void naoSeraPossivelCriarUmDesvioSeOStatusDoAgenciamentoNaoForSaido(){
        Desvio desvio = obterDesvioValido();
        desvio.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.ESPERADO);
        try{
            validador.validarDesvio(desvio, new Porto());
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_DESVIO_STATUS_EMBARCACAO_DEVE_SER_SAIDO_OU_DESVIADO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPossivelCriarUmDesvioSemInformarAData(){
        Desvio desvio = obterDesvioValido();
        desvio.setData(null);
        try{
            validador.validarDesvio(desvio, null);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_DESVIO_DATA_DEVE_SER_INFORMADA, pendencias.get(0).getMessage());
        }
    }
    
    
    @Test
    public void naoSeraPossivelCriarUmDesvioSemInformarONovoPorto(){
        Desvio desvio = obterDesvioValido();
        try{
            validador.validarDesvio(desvio, null);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_DESVIO_NOVO_PORTO_DEVE_SER_INFORMADO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelCriarUmDesvioComONovoPortoIgualAoPortoAnterior(){
        Desvio desvio = obterDesvioValido();
        Porto novoPorto = novoPorto().comId("SALV").build(); //mesmo porto do porto destino do agenciamento.
        
        try{
            validador.validarDesvio(desvio, novoPorto);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_DESVIO_NOVO_PORTO_DEVE_SER_DIFERENTE_PORTO_ANTERIOR, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelCriarUmDesvioSemInformarOMotivo(){
        Desvio desvio = obterDesvioValido();
        desvio.setMotivo(null);
        try{
            validador.validarDesvio(desvio, new Porto());
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_DESVIO_MOTIVO_DEVE_SER_INFORMADO, pendencias.get(0).getMessage());
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Cancelamento do Agenciamento">
    @Test
    public void naoDeveCancelarAgenciamentoQuandoStatusNaoForEsperado(){
        CancelamentoAgenciamento canclemanto = obterCancelamentoValido();
        canclemanto.getAgenciamento().setStatusEmbarcacao(StatusEmbarcacao.ATRACADO);
        
        try{
            validador.validarCancelarAgenciamento(canclemanto, false);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_CANCELAR_SOMENTE_ESPERADO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoDeveCancelarAgenciamentoSemInformarOMotivo(){
        CancelamentoAgenciamento canclemanto = obterCancelamentoValido();
        canclemanto.setMotivo(null);
        try{
            validador.validarCancelarAgenciamento(canclemanto, false);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_CANCELAMENTO_MOTIVO_DEVE_SER_INFORMADO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoDeveCancelarAgenciamentoSeOMotivoForOutrosEAObservacaoNaoforInformada(){
        CancelamentoAgenciamento canclemanto = obterCancelamentoValido();
        canclemanto.setMotivo(MotivoCancelamento.OUTROS);
        canclemanto.setDescricaoMotivo(null);
        try{
            validador.validarCancelarAgenciamento(canclemanto, false);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_CANCELAMENTO_OBSERVACAO_MOTIVO_DEVE_SER_INFORMADO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void agenciamentoCanceladoNaoPodeSerAlterado(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setStatusEmbarcacao(StatusEmbarcacao.CANCELADO);
        try{
            validador.validarSalvarAgenciamento(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO);
            assertEquals(ConstantesI18N.AGENCIAMENTO_CANCELADO_NAO_PODE_SER_ALTERADO, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Acompanhamento do Agenciamento">
    
    @Test
    public void naoSeraPossivelSalvarAcompanhamentoSemAgenciamento(){
        Acompanhamento acompanhamento = obterAcompanhamentoValido();
        acompanhamento.setAgenciamento(null);
        try {
            validador.validarAcompanhamento(acompanhamento);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ACOMPANHAMENTO_AGENCIAMENTO_NULO, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarAcompanhamentoSemData(){
        Acompanhamento acompanhamento = obterAcompanhamentoValido();
        acompanhamento.setData(null);
        try{
            validador.validarAcompanhamento(acompanhamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS);
            assertEquals(ConstantesI18N.ACOMPANHAMENTO_INFORME_DATA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPossivelSalvarAcompanhamentoSemTexto(){
        Acompanhamento acompanhamento = obterAcompanhamentoValido();
        acompanhamento.setTexto(null);
        try{
            validador.validarAcompanhamento(acompanhamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS);
            assertEquals(ConstantesI18N.ACOMPANHAMENTO_INFORME_TEXTO, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Saida da Embarcação Agenciamento">
    
    @Test
    public void naoSeraPossivelCriarUmNovoAgenciamentoSemDataEstimadaSaida(){
        Agenciamento agenciamento = obterNovoAgenciamentoValido();
        agenciamento.setDataEstimadaSaida(null);
        try{
            validador.validarSaidaEmbarcacaoAgenciada(agenciamento);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS);
            assertEquals(ConstantesI18N.AGENCIAMENTO_ETS_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
  
     //</editor-fold>
    
    @Test
    public void buscaPorFiltroDeveTerPeloMenosUmFiltro(){
        FiltroAgenciamento filtro = new FiltroAgenciamento();
        try{
            validador.validarBuscarAgenciamentosPorFiltro(filtro);
            fail();
        } catch (BusinessException be) {
            assertEquals(ConstantesI18N.AGENCIAMENTO_BUSCA_POR_FILTRO_PELO_MENOS_UM , be.getMessage());
        }
    }
    
  //<editor-fold defaultstate="collapsed" desc="Atendimentos"> 
    @Test
    public void naoSeraPossivelBuscarAgenciamentosParaCalcularAtendimentosSemInformarAAgencia() {
        FiltroAgenciamentoAtendimento filtro = obterFiltroAgenciamentoAtendimentoValido();
        filtro.setAgencia(null);
        try{
            validador.validarBuscarRelatorioAtendimentos(filtro);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.RELATORIO_INFO);
            assertEquals(ConstantesI18N.RELATORIO_AGENCIAMENTO_AGENCIA_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
  //</editor-fold>
    
    
    private FiltroAgenciamentoAtendimento obterFiltroAgenciamentoAtendimentoValido() {
        FiltroAgenciamentoAtendimento filtro = new FiltroAgenciamentoAtendimento();
        filtro.setAgencia(new Agencia());
        filtro.setDataInicial(DateBuilder.on(1,1,2014).getTime());
        filtro.setDataFinal(DateBuilder.on(1,3,2014).getTime());
        filtro.setQtdeDiasAtendimento(7);
        return filtro;
    }
    
    private Desvio obterDesvioValido() {
        Agenciamento agenciamento = novoAgenciamento()
                .comStatusEmbarcacao(StatusEmbarcacao.SAIDO)
                .comPortoDestino(novoPorto().comId("SALV").build())
                .build();
        
        Desvio desvio = DesvioBuilder.novoDesvio()
                .comData(new Date())
                .comMotivo(MotivoDesvio.REABASTECIMENTO)
                .doAgenciamento(agenciamento)
                .build();
        return desvio;
    }
    
    public CancelamentoAgenciamento obterCancelamentoValido(){
        Agenciamento agenciamento = novoAgenciamento()
                .comStatusEmbarcacao(StatusEmbarcacao.ESPERADO)
                .build();
        
        CancelamentoAgenciamento cancelamentoAgenciamento = new CancelamentoAgenciamento();
        cancelamentoAgenciamento.setAgenciamento(agenciamento);
        cancelamentoAgenciamento.setMotivo(MotivoCancelamento.OUTROS);
        cancelamentoAgenciamento.setDescricaoMotivo("Cancelando");
        return cancelamentoAgenciamento;
    }
    
    private Acompanhamento obterAcompanhamentoValido() {
        Acompanhamento acompanhamento = AcompanhamentoBuilder.novoAcompanhamento()
                .doAgenciamento(new Agenciamento())
                .comChaveCadastrador("TEST")
                .comNomeCadastrador("Teste")
                .comData(new Date())
                .comId(1L)
                .comTexto("Teste")
                .build();
        return acompanhamento;
    }
    
    
    
}
