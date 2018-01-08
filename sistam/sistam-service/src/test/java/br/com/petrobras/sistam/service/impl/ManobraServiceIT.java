package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.AgenciamentoService;
import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.ManobraService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.business.PortoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioManobra;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import static br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder.novaEmpresaMaritima;
import br.com.petrobras.sistam.test.builder.ManobraBuilder;
import br.com.petrobras.sistam.test.builder.PontoAtracacaoBuilder;
import br.com.petrobras.sistam.test.builder.PontoOperacionalBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoManobraBuilder;
import static br.com.petrobras.sistam.test.builder.ServicoManobraBuilder.novoServicoManobra;
import br.com.petrobras.sistam.test.builder.ServicoResponsavelBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.Calendar; 
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/ManobraService.xml")
public class ManobraServiceIT extends BaseIntegrationTestClass {

    @SpringBean("ManobraServiceImpl")
    private ManobraService manobraService;
 
    @SpringBean("AgenciamentoServiceImpl")
    private AgenciamentoService agenciamentoService;

    @SpringBean("PortoServiceImpl")
    private PortoService portoService;
    
    @SpringBean("CommonServiceImpl")
    private CommonService commonService;
    
    @SpringBean("PendenciaServiceImpl")
    private PendenciaService pendenciaService;
    
    @Test
    public void deveBuscarManobraDoAgenciamento() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoPorId(855L);
        List<Manobra> manobras = manobraService.buscarManobrasPorAgenciamento(agenciamento);
        Assert.assertNotNull(agenciamento);
        Assert.assertNotNull(manobras);
        Assert.assertEquals(2, manobras.size());
    }    
    
    @Test
    public void deveBuscarManobrasEncerradaDoAgenciamento() {
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoPorId(855L);
        List<Manobra> manobras = manobraService.buscarManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento(agenciamento);
        Assert.assertNotNull(agenciamento);
        Assert.assertNotNull(manobras);
        Assert.assertEquals(1, manobras.size());
    }    
    
    @Test
    public void deveBuscarManobraDoId() {
        Manobra manobra = manobraService.buscarManobrasPorId(1L);
        Assert.assertNotNull(manobra);
        Assert.assertEquals(1L, (long) manobra.getId());
    }    
    
    @Test
    public void deveBuscarServicoManobraDoId() {
        ServicoManobra servicoManobra = manobraService.buscarServicoManobrasPorId(1L);
        Assert.assertNotNull(servicoManobra);
        Assert.assertEquals(1L, (long) servicoManobra.getId());
    } 
    
    @Test
    public void deveBuscarServicoResponsavelDoId() {
        ServicoResponsavel servicoResponsavel = manobraService.buscarServicoResponsavelPorId(1L);
        Assert.assertNotNull(servicoResponsavel);
        Assert.assertEquals(1L, (long) servicoResponsavel.getId());
    }
    
    @Test
    public void deveSalvarManobra() {
        Manobra manobra = obterManobraValida();
        ServicoManobra servico = obterServicoManobraParaSalvar();
        Agenciamento agenciamento = agenciamentoService.buscarAgenciamentoPorId(855L);
        PontoAtracacao pontoAtracacao = portoService.buscarPontoAtracacaoPorId(11L);
        
        manobra.setAgenciamento(agenciamento);
        manobra.adicionarServico(servico);
        manobra.setPontoAtracacaoDestino(pontoAtracacao);
        manobra.setPontoAtracacaoOrigem(pontoAtracacao);
        manobraService.salvarManobra(manobra);
        Assert.assertNotNull(manobraService.buscarManobrasPorId(manobra.getId()));
    }


    @Test
    public void deveCancelarManobraQuandoCancelarManobraDentroPrazo() {
        Manobra manobra = manobraService.buscarManobrasPorId(1L);
        manobraService.cancelarManobraDentroPrazo(manobra, "TESTE");
        manobra = manobraService.buscarManobrasPorId(1L);
        Assert.assertEquals(StatusManobra.CANCELADA, manobra.getStatus());
    }
    
    
    @Test
    public void deveSalvarAManobraQuandoEnviarSolicitacaoDaManobra() {
        Manobra manobra = manobraService.buscarManobrasPorId(1L);
        manobra.setObservacaoInterna("XXX");
        manobraService.solicitarServicoManobra(manobra);
        manobra = manobraService.buscarManobrasPorId(1L);
        Assert.assertEquals("XXX", manobra.getObservacaoInterna());
    }

    @Test
    public void deveAlterarStatusParaSolicitadaQuandoEnviarSolicitacaoDaManobra() {
        Manobra manobra = manobraService.buscarManobrasPorId(1L);
        manobraService.solicitarServicoManobra(manobra);
        manobra = manobraService.buscarManobrasPorId(1L);
        Assert.assertEquals(StatusManobra.SOLICITADA, manobra.getStatus());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Registro de Manobra">
    @Test
    public void quandoUmaNovaManobraForRegistradaAManobraDeveSerSalvaNaBase(){
        Manobra manobra = obterManobraParaRegistrar();
        ServicoManobra servicoManobra = obterServicoManobraParaSalvar();
        ServicoResponsavel responsavel = obterResponsavelParaSalvar();
        
        servicoManobra.adicionarResponsavel(responsavel);
        manobra.adicionarServico(servicoManobra);
        
        manobra = manobraService.registrarManobra(manobra);
        
        Assert.assertNotNull(commonService.buscarEntidadePorId(Manobra.class, manobra.getId()));
    }
    
    @Test
    public void quandoUmaNovaManobraForRegistradaOsServicosManobraDevemSerSalvosNaBase(){
        Manobra manobra = obterManobraParaRegistrar();
        
        ServicoManobra servicoManobra01 = obterServicoManobraParaSalvar();
        
        ServicoManobra servicoManobra02 = obterServicoManobraParaSalvar();
        servicoManobra01.setTipoDoServico(TipoServico.LANCHAS);
        
        ServicoResponsavel responsavel01 = obterResponsavelParaSalvar();
        ServicoResponsavel responsavel02 = obterResponsavelParaSalvar();
        
        servicoManobra01.adicionarResponsavel(responsavel01);
        servicoManobra02.adicionarResponsavel(responsavel02);
        manobra.adicionarServico(servicoManobra01);
        manobra.adicionarServico(servicoManobra02);
        
        manobra = manobraService.registrarManobra(manobra);
        
        Assert.assertNotNull(commonService.buscarEntidadePorId(ServicoManobra.class, manobra.getServicos().get(0).getId()));
        Assert.assertNotNull(commonService.buscarEntidadePorId(ServicoManobra.class, manobra.getServicos().get(1).getId()));
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Encerramento de Manobra">
    @Test
    public void quandoUmaManobraForEncerradaDeveSerAtualizadaNoBanco(){
        Manobra manobra =  (Manobra) commonService.buscarEntidadePorId(Manobra.class, 1L);
        manobra.setStatus(StatusManobra.REGISTRADA);
        
        manobraService.encerrarManobra(manobra, null, null);
        manobra = (Manobra) commonService.buscarEntidadePorId(Manobra.class, 1L);
        
        Assert.assertEquals(StatusManobra.ENCERRADA, manobra.getStatus());
    }
    
    @Test
    public void quandoUmaManobraForEncerradaOAgenciamentoDeveSerAtualizadaNoBanco(){
        Manobra manobra =  (Manobra) commonService.buscarEntidadePorId(Manobra.class, 1L);
        manobra.setStatus(StatusManobra.REGISTRADA);
        manobra.setCaladoRe(15D);
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        
        manobraService.encerrarManobra(manobra, null, null);
        Agenciamento agenciamento = (Agenciamento) commonService.buscarEntidadePorId(Agenciamento.class, 855L);
        
        Assert.assertEquals(StatusEmbarcacao.SAIDO, agenciamento.getStatusEmbarcacao());
        Assert.assertEquals(Double.valueOf(15), agenciamento.getCaladoSaidaRe());
    }
    
    @Test
    public void deveSalvarNaBaseAPendenciaRegistroDeMovimentacaoQuandoAFinalidadeForDiferenteDeDesatracacaoParaSaidaESaidaDeFundeio(){
        Manobra manobra =  (Manobra) commonService.buscarEntidadePorId(Manobra.class, 1L);
        manobra.setStatus(StatusManobra.REGISTRADA);
        manobra.setFinalidadeManobra(FinalidadeManobra.TROCA_PORTO);
        
        manobraService.encerrarManobra(manobra, null, null);
         
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).comVersao(1L).build();
        List<Pendencia> pendencias = pendenciaService.buscarPendenciasDoAgenciamento(agenciamento, false);
        
        Assert.assertTrue(pendencias.size() == 1);
        Assert.assertEquals(TipoPendencia.REGISTRO_MOVIMENTACAO, pendencias.get(0).getTipoPendencia());
    }
    
    @Test
    public void deveSalvarNaBaseAPendenciaParteDeSaidaQuandoAFinalidadeForDesatracacaoParaSaida(){
        Manobra manobra =  (Manobra) commonService.buscarEntidadePorId(Manobra.class, 1L);
        manobra.setStatus(StatusManobra.REGISTRADA);
        manobra.setFinalidadeManobra(FinalidadeManobra.DESATRACACAO_SAIDA);
        
        Porto portoDestino = PortoBuilder.novoPorto().comId("SALV").build();
        Date eta = DateBuilder.on(1, 1, 2013).getTime();
        
        manobraService.encerrarManobra(manobra, portoDestino, eta);
         
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).comVersao(1L).build();
        List<Pendencia> pendencias = pendenciaService.buscarPendenciasDoAgenciamento(agenciamento, false);
        
        Assert.assertTrue(pendencias.size() == 1);
        Assert.assertEquals(TipoPendencia.PARTE_SAIDA, pendencias.get(0).getTipoPendencia());
    }
    
    @Test
    public void deveSalvarNaBaseAPendenciaParteDeSaidaQuandoAFinalidadeForSaidaFundeio(){
        Manobra manobra =  (Manobra) commonService.buscarEntidadePorId(Manobra.class, 1L);
        manobra.setStatus(StatusManobra.REGISTRADA);
        manobra.setFinalidadeManobra(FinalidadeManobra.SAIDA_FUNDEIO);
        
        Porto portoDestino = PortoBuilder.novoPorto().comId("SALV").build();
        Date eta = DateBuilder.on(1, 1, 2013).getTime();
        
        manobraService.encerrarManobra(manobra, portoDestino, eta);
         
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).comVersao(1L).build();
        List<Pendencia> pendencias = pendenciaService.buscarPendenciasDoAgenciamento(agenciamento, false);
        
        Assert.assertTrue(pendencias.size() == 1);
        Assert.assertEquals(TipoPendencia.PARTE_SAIDA, pendencias.get(0).getTipoPendencia());
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Serviço da Manobra">
    @Test
    public void quandoUmServicoForSalvoDeveSerPersistidoNaBase(){
        ServicoManobra servicoManobra = obterServicoManobraParaSalvar();
        ServicoResponsavel responsavel = obterResponsavelParaSalvar();
        
        servicoManobra.adicionarResponsavel(responsavel);
        manobraService.salvarServicoManobra(servicoManobra);
        
        Assert.assertNotNull(commonService.buscarEntidadePorId(ServicoManobra.class, servicoManobra.getId()));
    }
    
    @Test
    public void quandoUmNovoServicoForSalvoOsResponsaveisDevemSerSalvosNaBase(){
        ServicoManobra servicoManobra = obterServicoManobraParaSalvar();
        
        ServicoResponsavel responsavel01 = obterResponsavelParaSalvar();
        ServicoResponsavel responsavel02 = obterResponsavelParaSalvar();
        responsavel02.setServico(ServicoBuilder.novoServico().comId(2L).build());
        
        servicoManobra.adicionarResponsavel(responsavel01);
        servicoManobra.adicionarResponsavel(responsavel02);
        
        manobraService.salvarServicoManobra(servicoManobra);
        
        Assert.assertNotNull(commonService.buscarEntidadePorId(ServicoResponsavel.class, servicoManobra.getResponsaveis().get(0).getId()));
        Assert.assertNotNull(commonService.buscarEntidadePorId(ServicoResponsavel.class, servicoManobra.getResponsaveis().get(1).getId()));
    }
    
    @Test
    public void deveExcluirServicoManobraEOsResponsaveis() {
        ServicoManobra servicoManobra = manobraService.buscarServicoManobrasPorId(1L);
        Assert.assertNotNull(servicoManobra);
        
        List<ServicoResponsavel> responsaveis = servicoManobra.getResponsaveis();
        Assert.assertNotNull(responsaveis);
        Assert.assertTrue(responsaveis.size() > 0);
        
        manobraService.excluirServicoManobra(servicoManobra);
        
        servicoManobra = manobraService.buscarServicoManobrasPorId(1L);
        Assert.assertNull(servicoManobra);
        
        for(ServicoResponsavel responsavel : responsaveis) {
            ServicoResponsavel responsavelRecuperado = manobraService.buscarServicoResponsavelPorId(responsavel.getId());
            Assert.assertNull(responsavelRecuperado);
        }
        
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Responsável pelo Serviço">
    @Test
    public void quandoUmResponsavelForSalvoDeveSerPersistidoNaBase(){
        ServicoResponsavel responsavel = obterResponsavelParaSalvar();
        
        responsavel = manobraService.salvarResponsavel(responsavel);
        
        Assert.assertNotNull(commonService.buscarEntidadePorId(ServicoResponsavel.class, responsavel.getId()));
    }
    
    @Test
    public void quandoUmResponsavelForExcluidoDeveSerRemovidoDaBase(){
        ServicoResponsavel responsavel = ServicoResponsavelBuilder.novoServicoResponsavel(obterResponsavelParaSalvar())
                .comId(1L)
                .build();
        
        //Adicioa outro responsável para o serviço não ficar sem responsável.
        ServicoManobra servico = responsavel.getServicoManobra();
        servico.adicionarResponsavel(responsavel);
        servico.adicionarResponsavel(new ServicoResponsavel());
        
        responsavel = manobraService.excluirResponsavel(responsavel);
        
        Assert.assertNull(commonService.buscarEntidadePorId(ServicoResponsavel.class, responsavel.getId()));
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="Responsável pelo Serviço">
    private FiltroRelatorioManobra obterFiltroValido(Manobra manobra){
        FiltroRelatorioManobra filtro = new FiltroRelatorioManobra();
        filtro.setAgencia(manobra.getAgenciamento().getAgencia());
        filtro.setPorto(manobra.getAgenciamento().getPorto());
        filtro.setPeriodoInicio(DateUtils.truncate(manobra.getDataInicio(), Calendar.DAY_OF_MONTH) );
        filtro.setPeriodoTermino(manobra.getDataTermino());
        filtro.setTipoContrato(manobra.getAgenciamento().getTipoContrato());
        filtro.setResponsaveis(Arrays.asList(ResponsavelCustoBuilder.novoResponsavelCusto().comId(1L).build()));
        return filtro;
    }
    
    private Manobra obterManobraComPontoOperacionalParaRegistrar(){
        Manobra manobra = obterManobraParaRegistrar();
        PontoOperacional ponto = PontoOperacionalBuilder.novoPontoOperacional().doPorto(PortoBuilder.novoPorto().comId("GFR").comNomeCompleto("TESTE P").build()).build();
        manobra.getPontoAtracacaoOrigem().setPontoOperacional(ponto);
        ServicoResponsavel responsavel = obterResponsavelParaSalvar();
        ServicoManobra servico = obterServicoManobraParaSalvar();
        servico.adicionarResponsavel(responsavel);
        manobra.adicionarServico(servico);
        return manobra;
    }
    
    @Test
    public void deveBuscarManobrasEncerradasECanceladasForaPrazoNoPeriodoParaRelatorio(){
        Manobra manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.ENCERRADA);
        manobraService.salvarManobra(manobra);
        manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.CANCELADA_FORA_PRAZO);
        manobraService.salvarManobra(manobra);
        manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.PLANEJADA);
        manobraService.salvarManobra(manobra);
        
        assertEquals(2, manobraService.buscarManobrasParaRelatorio(obterFiltroValido(manobra)).size()); 
    }
    
    @Test
    public void deveBuscarManobrasEncerradasECanceladasForaPrazoApartirDeUmaDataParaRelatorio(){
        Manobra manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.ENCERRADA);
        manobraService.salvarManobra(manobra);
        manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.CANCELADA_FORA_PRAZO);
        manobraService.salvarManobra(manobra);
        manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.PLANEJADA);
        manobraService.salvarManobra(manobra);
        FiltroRelatorioManobra filtro = obterFiltroValido(manobra);
        filtro.setPeriodoTermino(null);
        assertEquals(2, manobraService.buscarManobrasParaRelatorio(filtro).size()); 
    }
    
    @Test
    public void deveBuscarManobrasEncerradasECanceladasForaPrazoAteUmaDataParaRelatorio(){
        Manobra manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.ENCERRADA);
        manobraService.salvarManobra(manobra);
        manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.CANCELADA_FORA_PRAZO);
        manobraService.salvarManobra(manobra);
        manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.PLANEJADA);
        manobraService.salvarManobra(manobra);
        FiltroRelatorioManobra filtro = obterFiltroValido(manobra);
        filtro.setPeriodoInicio(null);
        assertEquals(3, manobraService.buscarManobrasParaRelatorio(filtro).size()); 
    }
    
    @Test
    public void quantoNaoExistirManobrasEncerradasECanceladasForaDoPrazoNaoDeveLocalizarParaRelatorio(){
        Manobra manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.PLANEJADA);
        manobraService.salvarManobra(manobra);
        manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.REGISTRADA);
        manobraService.salvarManobra(manobra);
        manobra = obterManobraComPontoOperacionalParaRegistrar();
        manobra.setStatus(StatusManobra.SOLICITADA);
        manobraService.salvarManobra(manobra);
        
        assertEquals(0, manobraService.buscarManobrasParaRelatorio(obterFiltroValido(manobra)).size()); 
    }
    
    //</editor-fold>
    
    private Manobra obterManobraValida() {
        ResponsavelCustoEntity responsavelCusto = ResponsavelCustoBuilder.novoResponsavelCusto().comId(1L).build();
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(AgenciaBuilder.novaAgencia().build()).build())
                .comResponsavelCusto(responsavelCusto)
                .comStatus(StatusManobra.SOLICITADA)
                .comCaladoVante(12D)
                .comCaladoRe(12D)
                .comPontoAtracacaoOrigem(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comPontoAtracacaoDestino(PontoAtracacaoBuilder.novoPontoAtracacao().doPontoOperacional(PontoOperacionalBuilder.novoPontoOperacional().doPorto(PortoBuilder.novoPorto().comId("GFR").comNomeCompleto("TESTE P").build()).build()).build())
                .comFinalidadeManobra(FinalidadeManobra.ATRACACAO)
                .comDataInicio(new Date())
                .comDataTermino(new Date())
                .comDataPartidaOrigem(new Date())
                .comDataChegadaDestino(new Date())
                .build();
        return manobra;
    }
    
    private Manobra obterManobraParaRegistrar() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(855L).comAgencia(agencia).build();
        PontoAtracacao pontoAtracacao = PontoAtracacaoBuilder.novoPontoAtracacao().comId(11L).build();
        ResponsavelCustoEntity responsavelCusto = ResponsavelCustoBuilder.novoResponsavelCusto().comId(1L).build();
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(agenciamento)
                .comResponsavelCusto(responsavelCusto)
                .comCaladoVante(12D)
                .comCaladoRe(12D)
                .comPontoAtracacaoOrigem(pontoAtracacao)
                .comPontoAtracacaoDestino(pontoAtracacao)
                .comFinalidadeManobra(FinalidadeManobra.ATRACACAO)
                .comDataInicio(new Date())
                .comDataTermino(new Date())
                .comDataPartidaOrigem(new Date())
                .comDataChegadaDestino(new Date())
                .build();
        return manobra;
    }
    
    private ServicoManobra obterServicoManobraParaSalvar(){
        ServicoManobra servicoManobra = novoServicoManobra()
                .comDataEnvio(new Date())
                .comDataProgramada(new Date())
                .comTipoServico(TipoServico.PRATICOS)
                .daEmpresa(novaEmpresaMaritima().comId(1L).build())
                .daManobra(ManobraBuilder.novaManobra(obterManobraParaRegistrar()).comId(1L).build())
                .build();
        
        return servicoManobra;
    }
    
    private ServicoResponsavel obterResponsavelParaSalvar(){
                 ServicoResponsavel responsavel = ServicoResponsavelBuilder.novoServicoResponsavel()
                .comDataInicio(new Date())
                .comDataTermino(new Date())
                .comServico(ServicoBuilder.novoServico().comId(1L).build())
                .doServicoManobra(ServicoManobraBuilder.novoServicoManobra(obterServicoManobraParaSalvar()).comId(1L).build())
                .build();
        
        return responsavel;
    }
    
    
}
