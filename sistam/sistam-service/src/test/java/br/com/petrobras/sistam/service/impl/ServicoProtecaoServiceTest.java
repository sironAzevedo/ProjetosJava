package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoExecutado;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoAcesso;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoNacionalidade;
import br.com.petrobras.sistam.common.enums.TipoResiduo;
import br.com.petrobras.sistam.common.enums.TipoSolicitacaoTransito;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.enums.TipoUnidadeMedida;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;
import br.com.petrobras.sistam.common.valueobjects.PessoaAcessoVO;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.validator.ValidadorServicoProtecao;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.sistam.test.builder.FiltroEnvioSolicitacaoHospedagemBuilder;
import br.com.petrobras.sistam.test.builder.FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder;
import br.com.petrobras.sistam.test.builder.FiltroEnvioSolicitacaoTransporteBuilder;
import br.com.petrobras.sistam.test.builder.HospedeBuilder;
import br.com.petrobras.sistam.test.builder.PassageiroBuilder;
import br.com.petrobras.sistam.test.builder.PessoaAcessoBuilder;
import br.com.petrobras.sistam.test.builder.PessoaProtecaoBuilder;
import br.com.petrobras.sistam.test.builder.RetiradaResiduoLixoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoAcessoPessoaBuilder;
import br.com.petrobras.sistam.test.builder.ServicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoHospedagemBuilder;
import br.com.petrobras.sistam.test.builder.ServicoMedicoOdontologicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoProtecaoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoTransporteBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.unitils.util.ReflectionUtils;
import static org.mockito.Mockito.*;

public class ServicoProtecaoServiceTest {

    private ServicoProtecaoServiceImpl servicoProtecaoService;
    private PessoaProtecaoServiceImpl pessoaProtecaoService;
    private ValidadorPermissao validadorPermissao;
    private ValidadorServicoProtecao validador;
    private GenericDao dao;
    private AcessoServiceImpl acessoService;
    private NotesWebServiceImpl notesWebService;
    SistamCredentialsBean credentialsBean;

    @Before
    public void setUp() {
        validadorPermissao = mock(ValidadorPermissao.class);
        validador = mock(ValidadorServicoProtecao.class);
        acessoService = mock(AcessoServiceImpl.class);
        pessoaProtecaoService = mock(PessoaProtecaoServiceImpl.class);

        notesWebService = mock(NotesWebServiceImpl.class);

        dao = mock(GenericDao.class);
        servicoProtecaoService = new ServicoProtecaoServiceImpl(dao);
        servicoProtecaoService.setAcessoService(acessoService);

        servicoProtecaoService.setNotesWebService(notesWebService);

        credentialsBean = mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn("STAM");
        Mockito.when(credentialsBean.getUsername()).thenReturn("SISTAM");
        Mockito.when(acessoService.buscarDadosDeAutenticacao()).thenReturn(credentialsBean);

        carregarValidadorComMock();
        carregarPessoaServiceComMock();
        carregarValidadorPermissaoComMock();
    }

    //<editor-fold defaultstate="collapsed" desc="Serviço Proteção">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarUmServicoProtecao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecaoService.salvarServicoProtecao(servicoProtecao);
        Mockito.verify(validador).validarSalvarServicoProtecao(servicoProtecao);
        Mockito.verify(validadorPermissao).podeSalvarServicoProtecao(any(Agencia.class));
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarOServicoProtecao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecaoService.salvarServicoProtecao(servicoProtecao);
        Mockito.verify(dao).saveOrUpdate(servicoProtecao);

    }

    @Test
    public void quandoOServicoProtecaoForIncluidoPelaPrimeiraVezDeveSetarEleParaNovo() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setId(null);
        servicoProtecao = servicoProtecaoService.salvarServicoProtecao(servicoProtecao);
        assertEquals(true, servicoProtecao.isNovo());

    }

    @Test
    public void quandoOServicoProtecaoForAlteracoConfigurarQueEleNaoEhNovo() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setId(1L);
        servicoProtecao = servicoProtecaoService.salvarServicoProtecao(servicoProtecao);
        assertEquals(false, servicoProtecao.isNovo());

    }

    @Test
    public void deveChamarOsValidadoresQuandoExcluirServicoProtecao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecaoService.excluirServicoProtecao(servicoProtecao);
        Mockito.verify(validadorPermissao).podeSalvarServicoProtecao(any(Agencia.class));
    }

    @Test
    public void deveChamarORemoveQuandoExcluirServicoProtecao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecaoService.excluirServicoProtecao(servicoProtecao);
        Mockito.verify(dao).remove(servicoProtecao);
    }

    @Test
    public void deveAtualizarChaveENomeDeQuemIncluiuOServicoProtecao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setId(null);
        servicoProtecao = servicoProtecaoService.salvarServicoProtecao(servicoProtecao);

        assertEquals("STAM", servicoProtecao.getChaveUsuarioInclusao());
        assertEquals("SISTAM", servicoProtecao.getNomeUsuarioInclusao());

        assertEquals(null, servicoProtecao.getDataAlteracao());
        assertEquals(null, servicoProtecao.getChaveUsuarioAlteracao());
        assertEquals(null, servicoProtecao.getNomeUsuarioAlteracao());

    }

    @Test
    public void deveAtualizarChaveENomeDeQuemAlterouOServicoProtecao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        Date dataIncusao = DateBuilder.on(1, 1, 2014).getTime();

        servicoProtecao.setId(1L);
        servicoProtecao.setDataExecucao(dataIncusao);
        servicoProtecao.setChaveUsuarioInclusao("TEST");
        servicoProtecao.setNomeUsuarioInclusao("TESTE");
        servicoProtecao.setDataAlteracao(null);
        servicoProtecao.setChaveUsuarioAlteracao(null);
        servicoProtecao.setNomeUsuarioAlteracao(null);

        servicoProtecao = servicoProtecaoService.salvarServicoProtecao(servicoProtecao);

        assertEquals(dataIncusao, servicoProtecao.getDataExecucao());
        assertEquals("TEST", servicoProtecao.getChaveUsuarioInclusao());
        assertEquals("TESTE", servicoProtecao.getNomeUsuarioInclusao());

        Assert.assertNotNull(servicoProtecao.getDataAlteracao());
        assertEquals("STAM", servicoProtecao.getChaveUsuarioAlteracao());
        assertEquals("SISTAM", servicoProtecao.getNomeUsuarioAlteracao());

    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoCancelarOServicoProtecao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setTipoAtendimentoServico(TipoAtendimentoServico.OUTROS_ATENDIMENTOS);
        servicoProtecaoService.cancelarServicoExecutado(servicoProtecao);
        Mockito.verify(dao).saveOrUpdate(servicoProtecao);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Serviço Executado">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarServicoExecutado() {
        ServicoExecutado servicoExecutado = obterHospedagemValida();
        servicoProtecaoService.salvarServicoExecutado(servicoExecutado);
        Mockito.verify(validador).validarSalvarServicoProtecao(servicoExecutado.getServicoProtecao());
        Mockito.verify(validadorPermissao).podeSalvarServicoProtecao(any(Agencia.class));
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarServicoExecutado() {
        ServicoExecutado servicoExecutado = obterHospedagemValida();
        servicoProtecaoService.salvarServicoExecutado(servicoExecutado);
        Mockito.verify(dao).saveOrUpdate(servicoExecutado.getServicoProtecao());

    }

    @Test
    public void deveChamarOsValidadoresQuandoExcluirServicoExecutado() {
        ServicoExecutado servicoExecutado = obterHospedagemValida();
        servicoProtecaoService.excluirServicoExecutado(servicoExecutado);
        Mockito.verify(validadorPermissao).podeSalvarServicoProtecao(any(Agencia.class));
    }

    @Test
    public void deveChamarORemoveQuandoExcluirServicoExecutado() {
        ServicoExecutado servicoExecutado = obterHospedagemValida();
        servicoProtecaoService.excluirServicoExecutado(servicoExecutado);
        Mockito.verify(dao).remove(servicoExecutado.getServicoProtecao());
    }

    @Test
    public void deveChamarOsValidadoresQuandoCancelarServicoExecutado() {
        ServicoExecutado servicoExecutado = obterServicoProtecaoValido();
        servicoProtecaoService.cancelarServicoExecutado(servicoExecutado);
        Mockito.verify(validador).validarCancelamentoServicoExecutado(servicoExecutado);
        Mockito.verify(validadorPermissao).podeSalvarServicoProtecao(any(Agencia.class));
    }

    @Test
    public void deveSalvarOServicoProtecaoQuandoCancelarServicoExecutado() {
        ServicoExecutado servicoExecutado = obterServicoProtecaoValido();
        servicoProtecaoService.cancelarServicoExecutado(servicoExecutado);
        Mockito.verify(dao).saveOrUpdate(servicoExecutado.getServicoProtecao());
    }

    @Test
    public void deveAtualizarChaveENomeDeQuemEfetuouOCancelamentoDoServicoExecutado() {
        ServicoExecutado servicoExecutado = obterServicoProtecaoValido();
        servicoProtecaoService.cancelarServicoExecutado(servicoExecutado);
        assertEquals("STAM", servicoExecutado.getServicoProtecao().getChaveUsuarioCancelamento());
        assertEquals("SISTAM", servicoExecutado.getServicoProtecao().getNomeUsuarioCancelamento());
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Médico Odontológico">
    @Test
    public void naoDeveExecutarOMetodoSeOServicoExecutadoNaoForDoTipoMedicoOdontologico() {
        ServicoMedicoOdontologico servicoMedicoOdontologico = obterServicoMedicoOdontologicoValido();
        servicoMedicoOdontologico.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.TRANSPORTE);
        servicoProtecaoService.salvarServicoProtecaoMedicoOdontologico(servicoMedicoOdontologico);
        Mockito.verify(validador, times(0)).validarSalvarServicoMedicoOdontologico(servicoMedicoOdontologico);
        Mockito.verify(dao, times(0)).saveOrUpdate(servicoMedicoOdontologico);
    }

    @Test
    public void deveChamarOsValidadoresQuandoSalvarUmServicoMedicoOdontologico() {
        ServicoMedicoOdontologico servicoMedicoOdontologico = obterServicoMedicoOdontologicoValido();
        servicoProtecaoService.salvarServicoProtecaoMedicoOdontologico(servicoMedicoOdontologico);
        Mockito.verify(validador).validarSalvarServicoMedicoOdontologico(servicoMedicoOdontologico);
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarUmServicoMedicoOdontologico() {
        ServicoMedicoOdontologico servicoMedicoOdontologico = obterServicoMedicoOdontologicoValido();
        servicoProtecaoService.salvarServicoProtecaoMedicoOdontologico(servicoMedicoOdontologico);
        Mockito.verify(dao).saveOrUpdate(servicoMedicoOdontologico);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Serviço de Transporte">    
    @Test
    public void naoDeveExecutarOMetodoSeOServicoExecutadoNaoForDoTipoTransporte() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.CONTROLE_PRAGAS);
        servicoProtecaoService.salvarServicoProtecaoTransporte(servicoTransporte);
        Mockito.verify(validador, times(0)).validarSalvarServicoTransporte(servicoTransporte);
        Mockito.verify(dao, times(0)).saveOrUpdate(servicoTransporte);
    }

    @Test
    public void deveChamarOsValidadoresQuandoSalvarUmServicoTransporte() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoProtecaoService.salvarServicoProtecaoTransporte(servicoTransporte);
        Mockito.verify(validador).validarSalvarServicoTransporte(servicoTransporte);
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarUmServicoTransporte() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoProtecaoService.salvarServicoProtecaoTransporte(servicoTransporte);
        Mockito.verify(dao).saveOrUpdate(servicoTransporte);
    }

    @Test
    public void deveChamarServicoNotesWebAoEnviarSolicitacaoTransporte() throws MalformedURLException {
        FiltroEnvioSolicitacaoTransporte filtro = obterFiltroSolicitacaoTransporte();
        servicoProtecaoService.enviarFormularioSolicitacaoTransporte(filtro);
        Mockito.verify(notesWebService).enviarFormularioSolicitacaoTransporte(filtro);
    }

    @Test
    public void deveSalvarAColecaoDePassageirosSomenteQuandoOServicoTransporteEhNovo() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        Passageiro passageiro = obterPasssageiroValido();
        servicoTransporte.adicionarPassageiro(passageiro);
        servicoTransporte.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.TRANSPORTE);
        servicoTransporte.getServicoProtecao().setId(null);
        servicoProtecaoService.salvarServicoProtecaoTransporte(servicoTransporte);
        Mockito.verify(dao, times(1)).saveOrUpdate(passageiro);
    }

    @Test
    public void naoDeveSalvarAColecaoDepassageirosQuandoOServicoHospedagemNaoEhNovo() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        Passageiro passageiro = obterPasssageiroValido();
        servicoTransporte.adicionarPassageiro(passageiro);
        servicoTransporte.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM);
        servicoTransporte.getServicoProtecao().setNovo(false);
        servicoTransporte.getServicoProtecao().setId(1L);
        servicoProtecaoService.salvarServicoProtecaoTransporte(servicoTransporte);
        Mockito.verify(dao, never()).saveOrUpdate(passageiro);
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoCancelarOServicoTransporte() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        Passageiro passageiro = obterPasssageiroValido();
        servicoTransporte.adicionarPassageiro(passageiro);
        servicoTransporte.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.TRANSPORTE);
        servicoTransporte.getServicoProtecao().setId(1L);

        Mockito.when(servicoProtecaoService.buscarServicoTransportePorId(Mockito.any(Long.class))).thenReturn(servicoTransporte);

        servicoProtecaoService.cancelarServicoExecutado(servicoTransporte);
        Mockito.verify(dao, times(1)).saveOrUpdate(passageiro);

    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Serviço de Hospedagem">
    @Test
    public void naoDeveExecutarOMetodoSalvarSeOTipoDoServicoNaoForHospedagem() {
        ServicoHospedagem servicoHospedagem = obterHospedagemValida();
        servicoHospedagem.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.ACESSO_PESSOA);
        servicoProtecaoService.salvarServicoProtecaoHospedagem(servicoHospedagem);
        Mockito.verify(dao, never()).saveOrUpdate(servicoHospedagem);
    }

    public void deveChamarOsValidadoresQuandoSalvarServicoHospedagem() {
        ServicoHospedagem servicoHospedagem = obterHospedagemValida();
        servicoHospedagem.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM);
        servicoProtecaoService.salvarServicoProtecaoHospedagem(servicoHospedagem);
        Mockito.verify(validador).validarSalvarServicoHospedagem(servicoHospedagem);
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarServicoHospedagem() {
        ServicoHospedagem servicoHospedagem = obterHospedagemValida();
        servicoHospedagem.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM);
        servicoProtecaoService.salvarServicoProtecaoHospedagem(servicoHospedagem);
        Mockito.verify(dao).saveOrUpdate(servicoHospedagem);
    }

    @Test
    public void deveSalvarAColecaoDeHospedesSomenteQuandoOServicoHospedagemEhNovo() {
        ServicoHospedagem servicoHospedagem = obterHospedagemValida();
        Hospede hospede = obterHospedeValido();
        servicoHospedagem.adicionarHospede(hospede);
        servicoHospedagem.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM);
        servicoHospedagem.getServicoProtecao().setId(null);
        servicoProtecaoService.salvarServicoProtecaoHospedagem(servicoHospedagem);
        Mockito.verify(dao, times(1)).saveOrUpdate(hospede);
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoCancelarOServicoHospedagem() {
        ServicoHospedagem servicoHospedagem = obterHospedagemValida();
        Hospede hospede = obterHospedeValido();
        servicoHospedagem.adicionarHospede(hospede);
        servicoHospedagem.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM);
        servicoHospedagem.getServicoProtecao().setId(1L);

        Mockito.when(servicoProtecaoService.buscarServicoHospedagemPorId(Mockito.any(Long.class))).thenReturn(servicoHospedagem);

        servicoProtecaoService.cancelarServicoExecutado(servicoHospedagem);
        Mockito.verify(dao, times(1)).saveOrUpdate(hospede);

    }

    @Test
    public void deveChamarServicoNotesWebAoEnviarSolicitacaoHospedagem() throws MalformedURLException {
        FiltroEnvioSolicitacaoHospedagem filtro = obterFiltroSolicitacaoHospedagem();
        servicoProtecaoService.enviarEmailSolicitacaoHospedagem(filtro);
        Mockito.verify(notesWebService).enviarEmailSolicitacaoHospedagem(filtro);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Serviço de Acesso a Pessoa">
    @Test
    public void naoDeveExecutarOMetodoSalvarSeOTipoDoServicoNaoForAcessoAPessoa() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoAPessoaValida();
        servicoAcessoPessoa.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.MEDICO_ODONTOLOGICO);
        servicoProtecaoService.salvarServicoProtecaoAcessoPessoa(servicoAcessoPessoa);
        Mockito.verify(dao, never()).saveOrUpdate(servicoAcessoPessoa);
    }

    public void deveChamarOsValidadoresQuandoSalvarServicoAcessoPessoa() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoAPessoaValida();
        servicoAcessoPessoa.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.ACESSO_PESSOA);
        servicoProtecaoService.salvarServicoProtecaoAcessoPessoa(servicoAcessoPessoa);
        Mockito.verify(validador).validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarServicoAcessoPessoa() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoAPessoaValida();
        servicoAcessoPessoa.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.ACESSO_PESSOA);
        servicoProtecaoService.salvarServicoProtecaoAcessoPessoa(servicoAcessoPessoa);
        Mockito.verify(dao).saveOrUpdate(servicoAcessoPessoa);
    }
    
    @Test
    public void deveChamarOSaveOrUpdateQuandoTemPessoaSelecionadaNoFormularioPoliciaFederal(){
        List<PessoaAcessoVO> pessoas = obterPessoasAcessoVo();
        PessoaAcessoVO vo1 = pessoas.get(0);
        PessoaAcessoVO vo2 = pessoas.get(1);
        servicoProtecaoService.alterarPessoaStatusFormularioGeradoPolicia(pessoas);
        Mockito.verify(dao, Mockito.times(1)).saveOrUpdate(vo1.getPessoa());
        Mockito.verify(dao, Mockito.never()).saveOrUpdate(vo2.getPessoa());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateQuandoTemPessoaSelecionadaNoFormularioReceitaTripulantes(){
        List<PessoaAcessoVO> pessoas = obterPessoasAcessoVo();
        PessoaAcessoVO vo1 = pessoas.get(0);
        PessoaAcessoVO vo2 = pessoas.get(1);
        servicoProtecaoService.alterarPessoaStatusFormularioGeradoDesEmbarqueTripulantes(pessoas);
        Mockito.verify(dao, Mockito.times(1)).saveOrUpdate(vo1.getPessoa());
        Mockito.verify(dao, Mockito.never()).saveOrUpdate(vo2.getPessoa());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateQuandoTemPessoaSelecionadaNoFormularioReceitaFederal(){
        List<PessoaAcessoVO> pessoas = obterPessoasAcessoVo();
        PessoaAcessoVO vo1 = pessoas.get(0);
        PessoaAcessoVO vo2 = pessoas.get(1);
        servicoProtecaoService.alterarPessoaStatusFormularioGeradoReceita(pessoas);
        Mockito.verify(dao, Mockito.times(1)).saveOrUpdate(vo1.getPessoa());
        Mockito.verify(dao, Mockito.never()).saveOrUpdate(vo2.getPessoa());
    }
    
    @Test
    public void deveChamarOSaveOrUpdateQuandoTemPessoaSelecionadaNoFormularioReceitaPrestacaoServico(){
        List<PessoaAcessoVO> pessoas = obterPessoasAcessoVo();
        PessoaAcessoVO vo1 = pessoas.get(0);
        PessoaAcessoVO vo2 = pessoas.get(1);
        servicoProtecaoService.alterarPessoaStatusFormularioGeradoPrestacaoServico(pessoas);
        Mockito.verify(dao, Mockito.times(1)).saveOrUpdate(vo1.getPessoa());
        Mockito.verify(dao, Mockito.never()).saveOrUpdate(vo2.getPessoa());
    }

    @Test
    public void deveSalvarAColecaoDeHospedesSomenteQuandoOServicoAcessoPessoaEhNovo() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoAPessoaValida();
        PessoaAcesso pessoa = obterPessoaValido();
        servicoAcessoPessoa.adicionarPessoa(pessoa);
        servicoAcessoPessoa.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.ACESSO_PESSOA);
        servicoAcessoPessoa.getServicoProtecao().setId(null);
        servicoProtecaoService.salvarServicoProtecaoAcessoPessoa(servicoAcessoPessoa);
        Mockito.verify(dao, times(1)).saveOrUpdate(pessoa);
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoCancelarOServicoAcessoPessoa() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoAPessoaValida();
        PessoaAcesso pessoa = obterPessoaValido();
        servicoAcessoPessoa.adicionarPessoa(pessoa);
        servicoAcessoPessoa.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.ACESSO_PESSOA);
        servicoAcessoPessoa.getServicoProtecao().setId(1L);

        Mockito.when(servicoProtecaoService.buscarServicoAcessoPessoaPorId(Mockito.any(Long.class))).thenReturn(servicoAcessoPessoa);

        servicoProtecaoService.cancelarServicoExecutado(servicoAcessoPessoa);
        Mockito.verify(dao, times(1)).saveOrUpdate(pessoa);

    }
    
    @Test
    public void deveInstancia(){
        List<Pessoa> pessoas = obterPessoas();
        EmpresaProtecao empresa = mock(EmpresaProtecao.class);
        Mockito.when(pessoaProtecaoService.buscarPessoasProtecaoAtivaPorEmpresaNome(empresa, null)).thenReturn(pessoas);
        List<PessoaAcesso> pessoasAcesso = servicoProtecaoService.instanciarPessoasDaEmpresa(empresa);
        Assert.assertEquals("Joao", pessoasAcesso.get(0).getNome());
        Assert.assertEquals("Maria", pessoasAcesso.get(1).getNome());
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Hospede">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarHospede() {
        Hospede hospede = obterHospedeValido();
        servicoProtecaoService.salvarHospede(hospede);
        Mockito.verify(validador).validarCamposObrigatoriosHospede(hospede);
        Mockito.verify(validadorPermissao).podeSalvarHospede(any(Agencia.class));
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarHospede() {
        Hospede hospede = obterHospedeValido();
        servicoProtecaoService.salvarHospede(hospede);
        Mockito.verify(dao).saveOrUpdate(hospede);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Passageiro">
    @Test
    public void deveChamarOsValidadoresQuandoSalvarPassageiro() {
        Passageiro passageiro = obterPasssageiroValido();
        servicoProtecaoService.salvarPassageiro(passageiro);
        Mockito.verify(validador).validarCamposObrigatoriosPassageiro(passageiro);
        Mockito.verify(validadorPermissao).podeSalvarPassageiro(any(Agencia.class));
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarPassageiro() {
        Passageiro passageiro = obterPasssageiroValido();
        servicoProtecaoService.salvarPassageiro(passageiro);
        Mockito.verify(dao).saveOrUpdate(passageiro);
    }

    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Protecao Suprimento ao Navio">
    @Test
    public void naoDeveExecutarOMetodoSalvarSeOTipoDoServicoNaoForSuprimentoAoNavio() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.SERVICO_SUPRIMENTO);
        servicoProtecaoService.salvarServicoProtecaoSuprimentoAosNavios(servicoSuprimento);
        Mockito.verify(dao).saveOrUpdate(servicoSuprimento);
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoSalvarServicoSuprimentoAoNavio(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.SERVICO_SUPRIMENTO);
        servicoProtecaoService.salvarServicoProtecaoSuprimentoAosNavios(servicoSuprimento);
        Mockito.verify(validador).validarSalvarServicoSuprimentoAosNavios(servicoSuprimento); 
    }
    
    
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Protecao Retirada Residuo/Lixo">
    @Test
    public void naoDeveExecutarOMetodoSalvarSeOTipoDoServicoNaoForRetiradaResiduoLixo() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO);
        servicoProtecaoService.salvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo); 
        Mockito.verify(dao).saveOrUpdate(servicoRetiradaResiduoLixo);
    }
    
    @Test
    public void deveChamarOsValidadoresQuandoSalvarServicoRetiradaResiduoLixo() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO);
        servicoProtecaoService.salvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
        Mockito.verify(validador).validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo); 
    }
    
    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarUmServicoRetiradaResiduoLixo() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoProtecaoService.salvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
        Mockito.verify(dao).saveOrUpdate(servicoRetiradaResiduoLixo);
    }
    
    @Test
    public void deveChamarServicoNotesWebAoEnviarSolicitacaoRetiradaResiduoLixo() throws MalformedURLException {
        FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro = obterFiltroSolicitacaoRetiradaResiduoLixo();
        servicoProtecaoService.enviarFormularioSolicitacaoRetiradaResiduoLixo(filtro);
        Mockito.verify(notesWebService).enviarFormularioSolicitacaoRetiradaResiduoLixo(filtro);
    } 
    
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    
    private List<PessoaAcessoVO> obterPessoasAcessoVo(){
        List<PessoaAcessoVO> pessoas = new ArrayList<PessoaAcessoVO>();
        PessoaAcessoVO vo = new PessoaAcessoVO();
        vo.setPessoa(obterPessoaAcessoValida());
        vo.getPessoa().setId(1l);
        vo.setSelecionado(true);
        pessoas.add(vo);
        vo = new PessoaAcessoVO();
        vo.setPessoa(obterPessoaAcessoValida());
        vo.getPessoa().setId(2l);
        vo.setSelecionado(false);
        pessoas.add(vo);
        return pessoas;
    }
    
    private List<PessoaAcesso> obterPessoasAcesso(){
        List<PessoaAcesso> pessoas = new ArrayList<PessoaAcesso>();
        PessoaAcesso pessoaAcesso = obterPessoaAcessoValida();
        pessoaAcesso.setId(101l);
        pessoas.add(pessoaAcesso);
        pessoaAcesso = obterPessoaAcessoValida();
        pessoaAcesso.setId(102l);
        pessoas.add(pessoaAcesso);
        return pessoas;
    }
    
    private List<Pessoa> obterPessoas(){
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        Pessoa pessoaAcesso = obterPessoaValida();
        pessoaAcesso.setId(1l);
        pessoaAcesso.setNome("Joao");
        pessoas.add(pessoaAcesso);
        pessoaAcesso = obterPessoaValida();
        pessoaAcesso.setId(2l);
        pessoaAcesso.setNome("Maria");
        pessoas.add(pessoaAcesso);
        return pessoas;
    }
    
    private Pessoa obterPessoaValida(){
        return PessoaProtecaoBuilder.novaPessoaProtecao().comId(1l).comNome("Fulano").comCpf("00000000000").build();
    }
    
    private PessoaAcesso obterPessoaAcessoValida(){
        return PessoaAcessoBuilder.novoPessoa().comId(100l).comPessoa(obterPessoaValida()).build();
    }
    
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(ServicoProtecaoServiceImpl.class, "validadorServicoProtecao", false);
        ReflectionUtils.setFieldValue(servicoProtecaoService, field, validador);
    }
    
    private void carregarPessoaServiceComMock() {
        Field field = ReflectionUtils.getFieldWithName(ServicoProtecaoServiceImpl.class, "pessoaProtecaoService", false);
        ReflectionUtils.setFieldValue(servicoProtecaoService, field, pessoaProtecaoService);
    }

    private void carregarValidadorPermissaoComMock() {
        Mockito.when(validadorPermissao.podeSalvarServicoProtecao(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarHospede(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeAtivarInativarHospede(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarPassageiro(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeAtivarInativarPassageiro(Mockito.any(Agencia.class))).thenReturn(true);
        Mockito.when(validadorPermissao.podeSalvarPessoa(Mockito.any(Agencia.class))).thenReturn(true);
        Field field = ReflectionUtils.getFieldWithName(ServicoProtecaoServiceImpl.class, "validadorPermissao", false);
        ReflectionUtils.setFieldValue(servicoProtecaoService, field, validadorPermissao);
    }

    private ServicoProtecao obterServicoProtecaoValido() {

        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao()
                .comId(1L)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(new Agencia()).build())
                .comTipoAtendimentoServico(TipoAtendimentoServico.CONTROLE_PRAGAS)
                .comDataExecucao(DateBuilder.on(1, 1, 2014).getTime())
                .isNovo(true)
                .build();

        return servicoProtecao;
    }

    private Hospede obterHospedeValido() {
        Hospede hospede = HospedeBuilder.novoHospede()
                .comId(1L)
                .comNome("NOME")
                .comCPF("22222222222")
                .comDocumento("PASS1")
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        return hospede;
    }

    private ServicoHospedagem obterHospedagemValida() {
        ServicoHospedagem servicoHospedagem = ServicoHospedagemBuilder.novoServicoHospedagem()
                .comId(1L)
                .comAutorizacao("TESTE")
                .comDataChegada(new Date())
                .comDataSaida(new Date())
                .comHotel(new Servico())
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        return servicoHospedagem;
    }

    private ServicoTransporte obterServicoTransporteValido() {

        ServicoTransporte servicoTransporte = ServicoTransporteBuilder.novoServicoTransporte()
                .comAutorizacao("TESTE")
                .comDataHora(new Date())
                .comDestino("Teste")
                .comEmpresa(new Servico())
                .comOrigem("teste")
                .comVoo("teste")
                .comTipoTransporte(TipoTransporte.TERRESTRE)
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();

        servicoTransporte.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.TRANSPORTE);

        return servicoTransporte;
    }

    private ServicoMedicoOdontologico obterServicoMedicoOdontologicoValido() {
        ServicoMedicoOdontologico servicoMedicoOdontologico = ServicoMedicoOdontologicoBuilder.novoServicoMedicoOdontologico()
                .comNomeTripulante("Teste")
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        servicoMedicoOdontologico.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.MEDICO_ODONTOLOGICO);
        return servicoMedicoOdontologico;
    }
    
    private ServicoAcessoPessoa obterServicoAcessoAPessoaValida() {
        ServicoAcessoPessoa servicoAcessoPessoa = ServicoAcessoPessoaBuilder.novoServicoAcessoPessoa()
                .comId(1L)
                .comCompanhiaDocas(true)
                .comTerminal(true)
                .comDescricaoTerminal("TERMINAL")
                .comTipoSolicitacaoTransito(TipoSolicitacaoTransito.CANCELAMENTO)
                .comTipoAcesso(TipoAcesso.ACESSO)
                .comTipoCategoria(TipoCategoria.PRESTADOR_SERVICO)
                .comTipoNacionalidade(TipoNacionalidade.BRASILEIROS)
                .comPrestadorServicoAtividadeBordo("Atividade de bordo")
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        return servicoAcessoPessoa;
    }

    private FiltroEnvioSolicitacaoHospedagem obterFiltroSolicitacaoHospedagem() {
        ServicoHospedagem servicoHospedagem = obterHospedagemValida();
        FiltroEnvioSolicitacaoHospedagem filtro = FiltroEnvioSolicitacaoHospedagemBuilder.novoFiltro().comServicoHospedagem(servicoHospedagem).enviaParaAgenciaMaritima(true).build();

        return filtro;
    }

    private FiltroEnvioSolicitacaoTransporte obterFiltroSolicitacaoTransporte() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        FiltroEnvioSolicitacaoTransporte filtro = FiltroEnvioSolicitacaoTransporteBuilder.novoFiltro().comServicoTransporte(servicoTransporte).enviaParaAgenciaMaritima(true).build();

        return filtro;
    }

    private Passageiro obterPasssageiroValido() {
        Passageiro passageiro = PassageiroBuilder.novoPassageiro()
                .comId(1L)
                .comNome("NOME")
                .comCPF("22222222222")
                .comDocumento("PASS1")
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        return passageiro;
    }
    
    private PessoaAcesso obterPessoaValido() {
        PessoaAcesso pessoa = PessoaAcessoBuilder.novoPessoa()
                .comId(1L)
                .comPessoa(PessoaProtecaoBuilder.novaPessoaProtecao().comId(12l).comNome("Maria").comCpf("22222222222").comDocumento("PASS1").comDataNascimento(DateBuilder.on(1, 8, 1980).getTime()).build())
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        return pessoa;
    }
    
     private FiltroEnvioSolicitacaoRetiradaResiduoLixo obterFiltroSolicitacaoRetiradaResiduoLixo() {
        ServicoRetiradaResiduoLixo retiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro = FiltroEnvioSolicitacaoRetiradaResiduoLixoBuilder.novoFiltro()
                                                                                                           .comServicoRetiradaResiduoLixo(retiradaResiduoLixo)
                                                                                                           .enviaParaAgenciaMaritima(true).enviaParaEmpresaTransporte(true)
                                                                                                           .build();

        return filtro;
    }
    
    private ServicoRetiradaResiduoLixo obterServicoRetiradaResiduoLixoValido(){ 
        ServicoRetiradaResiduoLixo retiradaResiduoLixo = RetiradaResiduoLixoBuilder.novaRetiradaResiduoLixo()
                .comResponsavelCusto(ResponsavelCusto.PETROBRAS)
                .comEmpresaMaritima(EmpresaMaritimaBuilder.novaEmpresaMaritima().build())
                .comEmpresaServico(ServicoBuilder.novoServico().build())
                .comEmpresaMaritimaRodoviaria(EmpresaMaritimaBuilder.novaEmpresaMaritima().build())
                .comTipoResiduo(TipoResiduo.CLASSE_I)
                .comCaracterizacao("Tambores")
                .comEstadoFisico("Sólido")
                .comClassificacao("Classe I - F130")
                .comCodigoOnu("2930")
                .comQuantidadeResiduo(1000)
                .comTipoUnidadeMedida(TipoUnidadeMedida.TAMBORES_VAZIOS)
                .comValorServico(BigDecimal.ZERO) 
                .comDataInicioOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 10, 0, null))
                .comDataFimOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 11, 0, null))
                .comLocalArmazenagem("Local")
                .comLonArmazenagem("LonArmazenagem")
                .comDescCadri("Descricao")
                .comLocalDestinoFinal("LocalDestinoFinal")
                .comLonDestinoFinal("LonDestinoFinal")
                .comCadriDestinoFinal("CadriDestinoFinal") 
                .comServicoProtecao(obterServicoProtecaoValido())
                .build(); 
        
        retiradaResiduoLixo.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO);        
        
        return retiradaResiduoLixo;  
    }
    
    private ServicoSuprimento obterServicoSuprimentoValido() {
        ServicoSuprimento servicoSuprimento = new ServicoSuprimento();
        servicoSuprimento.setServicoProtecao(new ServicoProtecao());
        servicoSuprimento.setDataInicioOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 10, 0, null));
        servicoSuprimento.setDataFimOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 11, 0, null));
        servicoSuprimento.setEmpresaMaritima(EmpresaMaritimaBuilder.novaEmpresaMaritima().build());
        servicoSuprimento.setEmpresaServico(ServicoBuilder.novoServico().build());
        servicoSuprimento.setValorTransporteMaritimo(BigDecimal.ZERO);
        servicoSuprimento.setValorTransporteRodoviario(BigDecimal.ZERO);
        servicoSuprimento.setCentroCusto("testeCusto");
        servicoSuprimento.setCentroCustoDo("centroCustoDo");
        servicoSuprimento.setCustoOGMO(BigDecimal.ZERO);
        servicoSuprimento.setCustoOgmoDobra(BigDecimal.ZERO);
        servicoSuprimento.setCustoOperadorPortuario(BigDecimal.ZERO);
        servicoSuprimento.setCustoHoraExcedente(BigDecimal.ZERO);
        
        return servicoSuprimento;
    }
   
    //</editor-fold>
}


 
    