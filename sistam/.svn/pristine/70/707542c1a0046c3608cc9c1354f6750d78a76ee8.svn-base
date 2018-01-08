package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.notesweb2.common.enumeration.TipoResposta;
import br.com.petrobras.notesweb2.common.valueobject.Email;
import br.com.petrobras.notesweb2.common.valueobject.Resposta;
import br.com.petrobras.sistam.common.business.Notesweb2Service;
import br.com.petrobras.sistam.common.business.ServicoProtecaoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoCentroCusto;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.validator.ValidadorNotesWeb;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.EmbarcacaoBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.sistam.test.builder.FiltroEnvioSolicitacaoHospedagemBuilder;
import br.com.petrobras.sistam.test.builder.FiltroEnvioSolicitacaoTransporteBuilder;
import br.com.petrobras.sistam.test.builder.HospedeBuilder;
import br.com.petrobras.sistam.test.builder.ManobraBuilder;
import br.com.petrobras.sistam.test.builder.PontoAtracacaoBuilder;
import br.com.petrobras.sistam.test.builder.PontoOperacionalBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoHospedagemBuilder;
import br.com.petrobras.sistam.test.builder.ServicoManobraBuilder;
import br.com.petrobras.sistam.test.builder.ServicoProtecaoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoTransporteBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;

public class NotesWebServiceTest {

    private NotesWebServiceImpl notesWebService;
    private ValidadorPermissao validadorPermissao;
    private ValidadorNotesWeb validador;
    private AcessoServiceImpl acessoService;
    private ServicoProtecaoService servicoProtecaoService;
    private SistamCredentialsBean credentialsBean;
    private Notesweb2Service notesWeb2;
    
    @Before
    public void setup() {
        acessoService = mock(AcessoServiceImpl.class);
        servicoProtecaoService = mock(ServicoProtecaoServiceImpl.class);
        credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);

        notesWebService = new NotesWebServiceImpl(null, null, false, false, null);
        
        notesWebService.setAcessoService(acessoService);
        
        carregarValidadorPermissaoComMock();
        carregarValidadorComMock();
        
        
        Resposta resposta = new Resposta();
        resposta.setTipo(TipoResposta.SUCESSO);
        
        notesWeb2 = Mockito.mock(Notesweb2Service.class);
        Mockito.when(notesWeb2.enviarEmail(Mockito.any(Email.class))).thenReturn(resposta);
        
        notesWebService.setNotesweb2(notesWeb2);
        
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoForEnviarSolicitacaoDeApoioAManobra() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        notesWebService.enviarSolicitacaoApoioManobra(servicoManobra);
        Mockito.verify(validadorPermissao).podeEnviarSolicitacaoApoioManobra(Mockito.any(Agencia.class));
        Mockito.verify(validador).validarEnvioSolicitacaoServico(servicoManobra);
    }

    @Test
    public void deveChamarOsValidadoresQuandoForEnviarSolicitacaoDeTransporte() throws MalformedURLException {
        FiltroEnvioSolicitacaoTransporte filtro = obterFiltroSolicitacaoTransporte();
        notesWebService.setServicoProtecaoService(servicoProtecaoService);
        notesWebService.enviarFormularioSolicitacaoTransporte(filtro);
        Mockito.verify(validadorPermissao, Mockito.times(2)).podeEnviarSolicitacaoTransporte(Mockito.any(Agencia.class));
        Mockito.verify(servicoProtecaoService, Mockito.times(1)).confirmarEnvioEmailServicoProtecao(Mockito.any(ServicoProtecao.class));
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoForEnviarSolicitacaoDeHospedagem() throws MalformedURLException {
        FiltroEnvioSolicitacaoHospedagem servicoHospedagem = obterFiltroSolicitacaoHospedagem();
        notesWebService.setServicoProtecaoService(servicoProtecaoService);
        notesWebService.enviarEmailSolicitacaoHospedagem(servicoHospedagem);
        Mockito.verify(validadorPermissao, Mockito.times(2)).podeEnviarSolicitacaoHospedagem(Mockito.any(Agencia.class));
        Mockito.verify(servicoProtecaoService, Mockito.times(1)).confirmarEnvioEmailServicoProtecao(Mockito.any(ServicoProtecao.class));
    }
    
    private ServicoManobra obterServicoManobraValido() {
        ServicoManobra servicoManobra = ServicoManobraBuilder.novoServicoManobra()
                .comId(1L)
                .comTipoServico(TipoServico.PRATICOS)
                .comDataProgramada(new Date())
                .daManobra(obterManobraValida())
                .daEmpresa(EmpresaMaritimaBuilder.novaEmpresaMaritima().comId(12L).comCnpj("1324").comEmail("teste@teste.com.br").build())
                .build();
        return servicoManobra;
    }
    
    
    private Manobra obterManobraValida() {
        
        Agencia agencia = AgenciaBuilder.novaAgencia().comEmail("teste@teste.com.br").build();
        Embarcacao embarcacao = EmbarcacaoBuilder.novaEmbarcacao().comApelido("teste").comBandeira(new Pais()).build();
        
        Porto porto = PortoBuilder.novoPorto().comNomeCompleto("teste").build();
        
        PontoOperacional pontoO = PontoOperacionalBuilder.novoPontoOperacional().doPorto(porto).build();
        
        PontoAtracacao ponto = PontoAtracacaoBuilder.novoPontoAtracacao().comNome("teste").doPontoOperacional(pontoO).build();
        
        
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comViagem("teste").comEmbarcacao(embarcacao).comAgencia(agencia).comPortoAtual(new Porto()).build())
                .comResponsavelCusto(ResponsavelCustoBuilder.novoResponsavelCusto().build())
                .comStatus(StatusManobra.PLANEJADA)
                .comCaladoVante(12D)
                .comCaladoRe(12D)
                .comPontoAtracacaoOrigem(ponto)
                .comPontoAtracacaoDestino(ponto)
                .comFinalidadeManobra(FinalidadeManobra.ATRACACAO)
                .build();
        return manobra;
    }
    
    private ServicoProtecao obterServicoProtecaoValido() {
        
        Embarcacao embarcacao = EmbarcacaoBuilder.novaEmbarcacao().comApelido("teste").build();
        Agencia agencia = AgenciaBuilder.novaAgencia().comSigla("SAL").comNome("SALVADOR").comTimeZone("America/Bahia").build();
        
        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao()
                .comId(1L)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comId(1L).comCodigo(1L).comAnoProcesso(2014).comAgencia(agencia).comEmbarcacao(embarcacao).build())
                .comTipoAtendimentoServico(TipoAtendimentoServico.CONTROLE_PRAGAS)
                .comDataExecucao(DateBuilder.on(1, 1, 2014).getTime())
                .isNovo(true)
                .build();
        
        return servicoProtecao;
    }
    
    
    private ServicoTransporte obterServicoTransporteValido() {
        
        Servico servico = ServicoBuilder.novoServico().comId(1L).daEmpresa(EmpresaMaritimaBuilder.novaEmpresaMaritima().comRazaoSocial("teste").build()).build();
        
        ServicoTransporte servicoTransporte = ServicoTransporteBuilder.novoServicoTransporte()
                .comAutorizacao("TESTE")
                .comDataHora(new Date())
                .comDestino("Teste")
                .comEmpresa(servico)
                .comOrigem("teste")
                .comVoo("teste")
                .comTipoTransporte(TipoTransporte.TERRESTRE)
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        return servicoTransporte;
    }  
    
    private ServicoHospedagem obterServicoHospedagemValido() {
        
        Servico servico = ServicoBuilder.novoServico().comId(1L).daEmpresa(EmpresaMaritimaBuilder.novaEmpresaMaritima().comRazaoSocial("teste").build()).build();
        
        ServicoHospedagem servicoHospedagem = ServicoHospedagemBuilder.novoServicoHospedagem()
                .comAutorizacao("TESTE")
                .comHotel(servico)
                .comDataChegada(null)
                .comDataSaida(null)
                .comHospede(obterHospedeValido())
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        return servicoHospedagem;
    } 
    
    private FiltroEnvioSolicitacaoHospedagem obterFiltroSolicitacaoHospedagem(){
        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();
        Servico servico = ServicoBuilder.novoServico().comId(1L).daEmpresa(EmpresaMaritimaBuilder.novaEmpresaMaritima().comRazaoSocial("teste").build()).build();
        
        FiltroEnvioSolicitacaoHospedagem filtro = FiltroEnvioSolicitacaoHospedagemBuilder.novoFiltro().daAgenciaViagem(servico).comLotacao("TIC")
                                                                                         .comTipoCentroCusto(TipoCentroCusto.CC1420).enviaParaHotel(true)
                                                                                         .comServicoHospedagem(servicoHospedagem).build();
        return filtro;
    }
    
    private Hospede obterHospedeValido(){
        Hospede hospede = HospedeBuilder.novoHospede().comId(1L).comNome("HOSPEDE 1").comCPF("1111").build();
        
        return hospede;
        
    }
    
    private FiltroEnvioSolicitacaoTransporte obterFiltroSolicitacaoTransporte(){
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        FiltroEnvioSolicitacaoTransporte filtro = FiltroEnvioSolicitacaoTransporteBuilder.novoFiltro().comServicoTransporte(servicoTransporte).enviaParaAgenciaMaritima(true).build();
        
        return filtro;
    }
    
    
    private void carregarValidadorPermissaoComMock() {
        validadorPermissao = Mockito.mock(ValidadorPermissao.class);
        Mockito.when(validadorPermissao.podeEnviarSolicitacaoApoioManobra(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeEnviarSolicitacaoTransporte(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeEnviarSolicitacaoHospedagem(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(NotesWebServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(notesWebService, field, validadorPermissao);
    }
    
    
    private void carregarValidadorComMock() {
        validador = Mockito.mock(ValidadorNotesWeb.class);
        Field field = ReflectionUtils.getFieldWithName(NotesWebServiceImpl.class, "validador", false);
        ReflectionUtils.setFieldValue(notesWebService, field, validador);
    }
    
    
    private void carregarAcessoServiceMock() {
        acessoService = Mockito.mock(AcessoServiceImpl.class);
        Mockito.when(acessoService.buscarDadosDeAutenticacao().getUsername()).thenReturn("teste");
        
        Field field = ReflectionUtils.getFieldWithName(NotesWebServiceImpl.class, "acessoService", false);
        ReflectionUtils.setFieldValue(notesWebService, field, acessoService);
    }
    
    
}
