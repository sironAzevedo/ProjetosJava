package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.ServicoProtecaoService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoExecutado;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.TipoAcesso;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoMaterial;
import br.com.petrobras.sistam.common.enums.TipoMercadorias;
import br.com.petrobras.sistam.common.enums.TipoNacionalidade;
import br.com.petrobras.sistam.common.enums.TipoSolicitacaoTransito;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioServicoSuprimentoAosNavios;
import br.com.petrobras.sistam.service.mock.AcessoServiceMock;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.EmbarcacaoBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaProtecaoBuilder;
import br.com.petrobras.sistam.test.builder.HospedeBuilder;
import br.com.petrobras.sistam.test.builder.PassageiroBuilder;
import br.com.petrobras.sistam.test.builder.PessoaAcessoBuilder;
import br.com.petrobras.sistam.test.builder.PessoaProtecaoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoAcessoPessoaBuilder;
import br.com.petrobras.sistam.test.builder.ServicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoHospedagemBuilder;
import br.com.petrobras.sistam.test.builder.ServicoMedicoOdontologicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoProtecaoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoTransporteBuilder;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet(value = "/datasets/ServicoProtecaoService.xml")
public class ServicoProtecaoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("ServicoProtecaoServiceImpl")
    private ServicoProtecaoService servicoProtecaoService;
    @SpringBean("CommonServiceImpl")
    private CommonService commonService;
    @SpringBean("AcessoServiceImpl")
    private AcessoServiceMock acessoServiceMock;

    @Before
    public void setUp() {
        acessoServiceMock.setChave("STAM");
        acessoServiceMock.setNome("SISTAM");
    }

    //<editor-fold defaultstate="collapsed" desc="Servico Protecao">
    @Test
    public void deveSalvarOServicoProtecao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao = servicoProtecaoService.salvarServicoProtecao(servicoProtecao);
        assertNotNull(commonService.buscarEntidadePorId(ServicoProtecao.class, servicoProtecao.getId()));
    }

    @Test
    public void deveExcluirOServicoProtecao() {
        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao(obterServicoProtecaoValido())
                .comId(1L).build();
        servicoProtecaoService.excluirServicoProtecao(servicoProtecao);
        assertNull(commonService.buscarEntidadePorId(ServicoProtecao.class, servicoProtecao.getId()));
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Servico Executado">
    @Test
    public void deveSalvarOServicoExecutadoMedicoOddontologico() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setTipoAtendimentoServico(TipoAtendimentoServico.MEDICO_ODONTOLOGICO);
        ServicoMedicoOdontologico servicoExecutado = obterServicoMedicoOdontologicoValido();
        servicoExecutado.setServicoProtecao(servicoProtecao);
        servicoExecutado = (ServicoMedicoOdontologico) servicoProtecaoService.salvarServicoExecutado(servicoExecutado);
        assertNotNull(commonService.buscarEntidadePorId(ServicoMedicoOdontologico.class, servicoExecutado.getServicoProtecao().getId()));
    }

    @Test
    public void deveSalvarOServicoExecutadoTransporte() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setTipoAtendimentoServico(TipoAtendimentoServico.TRANSPORTE);
        ServicoTransporte servicoExecutado = obterServicoTransporteValido();

        Passageiro passageiro = obterPassageiroValido();
        servicoExecutado.adicionarPassageiro(passageiro);

        servicoExecutado.setServicoProtecao(servicoProtecao);
        servicoExecutado = (ServicoTransporte) servicoProtecaoService.salvarServicoExecutado(servicoExecutado);
        assertNotNull(commonService.buscarEntidadePorId(ServicoTransporte.class, servicoExecutado.getServicoProtecao().getId()));
    }

    @Test
    public void deveSalvarOServicoExecutadoHospedagem() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM);
        ServicoHospedagem servicoExecutado = obterHospedagemValida();

        Hospede hospede = obterHospedeValido();
        servicoExecutado.adicionarHospede(hospede);

        servicoExecutado.setServicoProtecao(servicoProtecao);
        servicoExecutado = (ServicoHospedagem) servicoProtecaoService.salvarServicoExecutado(servicoExecutado);
        assertNotNull(commonService.buscarEntidadePorId(ServicoHospedagem.class, servicoExecutado.getServicoProtecao().getId()));
    }

    @Test
    public void deveSalvarOServicoExecutadoAcessoPessoa() {
        ServicoAcessoPessoa servicoExecutado = obterServicoAcessoAPessoaValida();
        servicoExecutado.adicionarPessoa(obterPessoaValido(servicoExecutado));

        servicoExecutado = (ServicoAcessoPessoa) servicoProtecaoService.salvarServicoExecutado(servicoExecutado);
        assertNotNull(commonService.buscarEntidadePorId(ServicoAcessoPessoa.class, servicoExecutado.getServicoProtecao().getId()));
    }

    @Test
    public void deveExcluirServicoExecutado() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).comAgencia(agencia).build();

        ServicoMedicoOdontologico servicoExecutado = (ServicoMedicoOdontologico) commonService.buscarEntidadePorId(ServicoMedicoOdontologico.class, 1L);
        ServicoProtecao servicoProtecao = (ServicoProtecao) commonService.buscarEntidadePorId(ServicoProtecao.class, 1L);
        servicoProtecao.setAgenciamento(agenciamento);
        servicoExecutado.setServicoProtecao(servicoProtecao);

        servicoProtecaoService.excluirServicoExecutado(servicoExecutado);
        assertNull(commonService.buscarEntidadePorId(ServicoProtecao.class, 1L));
        assertNull(commonService.buscarEntidadePorId(ServicoMedicoOdontologico.class, 1L));
    }

    @Test
    public void deveCancelarOServicoExecutadoHospedagem() {
        ServicoHospedagem servicoExecutado = servicoProtecaoService.buscarServicoHospedagemPorId(4L);
        servicoExecutado.getServicoProtecao().setDataCancelamento(new Date());
        servicoExecutado.getServicoProtecao().setJustificativa("teste");

        servicoProtecaoService.cancelarServicoExecutado(servicoExecutado);

        servicoExecutado = servicoProtecaoService.buscarServicoHospedagemPorId(4L);

        assertEquals("STAM", servicoExecutado.getServicoProtecao().getChaveUsuarioCancelamento());
        assertEquals("SISTAM", servicoExecutado.getServicoProtecao().getNomeUsuarioCancelamento());

        for (Hospede hospede : servicoExecutado.getHospedes()) {
            assertEquals(false, hospede.isAtivo());
        }

    }

    @Test
    public void deveRetornarTodosOsServicosExecutadosDeUmAgenciamento() {
        Agenciamento agenciamento = (Agenciamento) commonService.buscarEntidadePorId(Agenciamento.class, 1L);
        List<ServicoExecutado> servicosExecutados = servicoProtecaoService.buscarServicosExecutados(agenciamento);

        List<Long> ids = new ArrayList<Long>(Arrays.asList(1L, 4L));
        for (ServicoExecutado servicoExecutado : servicosExecutados) {
            assertTrue(ids.contains(servicoExecutado.getServicoProtecao().getId()));
        }
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Serviço de Transporte">
    @Test
    public void deveRetornarOServicoTransporteDeUmId() {
        ServicoTransporte servicoTransporte = (ServicoTransporte) servicoProtecaoService.buscarServicoTransportePorId(5L);
        assertEquals(Long.valueOf(5), servicoTransporte.getId());
    }

    @Test
    public void deveCancelarServicoTransporte() {

        ServicoTransporte servicoTransporte = servicoProtecaoService.buscarServicoTransportePorId(5L);
        servicoTransporte.getServicoProtecao().setJustificativa("Justificativa");
        servicoTransporte.getServicoProtecao().setDataCancelamento(DateBuilder.on(01, 01, 2014).getTime());
        servicoProtecaoService.cancelarServicoExecutado(servicoTransporte);

        servicoTransporte = servicoProtecaoService.buscarServicoTransportePorId(5L);

        for (Passageiro passageiro : servicoTransporte.getPassageiros()) {
            assertEquals(false, passageiro.isAtivo());
        }

    }
    //</editor-fold>     

    //<editor-fold defaultstate="collapsed" desc="Serviço de Hospedagem">
    @Test
    public void deveCancelarServicoHospedagem() {

        ServicoHospedagem servicoHospedagem = servicoProtecaoService.buscarServicoHospedagemPorId(4L);
        servicoHospedagem.getServicoProtecao().setJustificativa("Justificativa");
        servicoHospedagem.getServicoProtecao().setDataCancelamento(DateBuilder.on(01, 01, 2014).getTime());
        servicoProtecaoService.cancelarServicoExecutado(servicoHospedagem);

        servicoHospedagem = servicoProtecaoService.buscarServicoHospedagemPorId(4L);

        for (Hospede hospede : servicoHospedagem.getHospedes()) {
            assertEquals(false, hospede.isAtivo());
        }

    }

    @Test
    public void deveRetornarOServicoHospedagemDeUmId() {
        ServicoHospedagem servicoHospedagem = (ServicoHospedagem) servicoProtecaoService.buscarServicoHospedagemPorId(4L);
        assertEquals(Long.valueOf(4), servicoHospedagem.getId());
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Hospede">
    @Test
    public void deveSalvarOHospede() {
        Hospede hospede = obterHospedeValido();
        hospede = servicoProtecaoService.salvarHospede(hospede);
        Hospede hospedeCadastrado = (Hospede) commonService.buscarEntidadePorId(Hospede.class, hospede.getId());
        assertNotNull(hospedeCadastrado);
    }

    @Test
    public void deveRetornarOServicoHospedagemCadastrado() {
        ServicoHospedagem servicoHospedagem = servicoProtecaoService.buscarServicoHospedagemPorId(4L);

        assertNotNull(servicoHospedagem);
        assertEquals(4, (long) servicoHospedagem.getId());
        assertNotNull(servicoHospedagem.getEmpresaServico());
        assertNotNull(servicoHospedagem.getHospedes());
        assertEquals(1, servicoHospedagem.getHospedes().size());
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Passageiro">
    @Test
    public void deveSalvarOPassageiro() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro = servicoProtecaoService.salvarPassageiro(passageiro);
        Passageiro passageiroCadastrado = (Passageiro) commonService.buscarEntidadePorId(Passageiro.class, passageiro.getId());
        assertNotNull(passageiroCadastrado);
    }

    @Test
    public void deveRetornarOServicoTransporteCadastrado() {
        ServicoTransporte servicoTransporte = servicoProtecaoService.buscarServicoTransportePorId(5L);

        assertNotNull(servicoTransporte);
        assertEquals(5, (long) servicoTransporte.getId());
        assertNotNull(servicoTransporte.getEmpresaServico());
        assertNotNull(servicoTransporte.getPassageiros());
        assertEquals(1, servicoTransporte.getPassageiros().size());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pessoa">
    @Test
    public void deveRetornarOServicoAcessoaPessoaCadastrado() {
        ServicoAcessoPessoa servicoAcessoPessoa = servicoProtecaoService.buscarServicoAcessoPessoaPorId(6L);

        assertNotNull(servicoAcessoPessoa);
        assertEquals(6, (long) servicoAcessoPessoa.getId());
        assertNotNull(servicoAcessoPessoa.getTipoSolicitacaoTransito());
        assertNotNull(servicoAcessoPessoa.getTipoAcesso());
        assertNotNull(servicoAcessoPessoa.getTipoCategoria());
        assertNotNull(servicoAcessoPessoa.getTipoNacionalidade());
        assertNotNull(servicoAcessoPessoa.getPessoasAsList());
        assertEquals(1, servicoAcessoPessoa.getPessoasAsList().size());
    }
    //</editor-fold>
    
    @Test
    public void deveBuscarServicoSuprimentoPorId(){
        ServicoSuprimento servico = servicoProtecaoService.buscarServicoSuprimentoPorId(20L);
        assertNotNull(servico);
    }

    @Test
    public void testaBuscarServicoSuprimentoTransitoEmpresaParaRelatorio() {
        FiltroRelatorioServicoSuprimentoAosNavios filtro = new FiltroRelatorioServicoSuprimentoAosNavios();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).build());
        filtro.setNavio(EmbarcacaoBuilder.novaEmbarcacao().comId("0098").build());
        filtro.setCompanhiaDocas(true);
        filtro.setTerminal(true);
        filtro.setEmpresaMaritima(EmpresaMaritimaBuilder.novaEmpresaMaritima().comId(1l).build());
        filtro.setServico(ServicoBuilder.novoServico().comId(1l).build());
        filtro.setFornecedor(EmpresaProtecaoBuilder.novaEmpresaProtecao().comId(1502l).comCnpj("05864866000164").build());
        filtro.setNotaFiscal("abc");
        filtro.setNumeroViagem("018 ST");
        filtro.getPeriodoOperacao().setDataInicio(SistamDateUtils.informarDataHora(10, 03, 2014, 10, 05, null));
        filtro.getPeriodoOperacao().setDataFim(SistamDateUtils.informarDataHora(30, 03, 2014, 12, 10, null));
        filtro.setTipoMaterial(TipoMaterial.MATERIAIS_DIVERSOS_DTAS);
        filtro.setTipoAcesso(TipoMercadorias.SAIDA);
        assertEquals(1, servicoProtecaoService.buscarServicoSuprimentoTransitoEmpresaParaRelatorio(filtro).size());
    }

    private Hospede obterHospedeValido() {

        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).comAgencia(agencia).build();

        ServicoProtecao servicoProtecao = (ServicoProtecao) commonService.buscarEntidadePorId(ServicoProtecao.class, 1L);
        servicoProtecao.setServicoHospedagem(null);
        servicoProtecao.setAgenciamento(agenciamento);

        Hospede hospede = HospedeBuilder.novoHospede()
                .comNome("NOME")
                .comCPF("22222222222")
                .comDocumento("PASS1")
                .comServicoProtecao(servicoProtecao)
                .build();

        return hospede;
    }

    private ServicoProtecao obterServicoProtecaoValido() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).comAgencia(agencia).build();

        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao()
                .doAgenciamento(agenciamento)
                .comDataExecucao(DateBuilder.on(1, 1, 2014).getTime())
                .comTipoAtendimentoServico(TipoAtendimentoServico.RETIRADA_RESIDUO)
                .build();
        return servicoProtecao;
    }

    private ServicoHospedagem obterHospedagemValida() {

        Servico empresaServico = (Servico) commonService.buscarEntidadePorId(Servico.class, 1L);

        ServicoHospedagem servicoHospedagem = ServicoHospedagemBuilder.novoServicoHospedagem()
                .comAutorizacao("TESTE")
                .comDataChegada(SistamDateUtils.informarDataHora(1, 8, 2014, 12, 0, null))
                .comDataSaida(SistamDateUtils.informarDataHora(30, 8, 2014, 12, 0, null))
                .comHotel(empresaServico)
                .build();
        return servicoHospedagem;
    }

    private ServicoMedicoOdontologico obterServicoMedicoOdontologicoValido() {

        ServicoMedicoOdontologico servicoMedicoOdontologico = ServicoMedicoOdontologicoBuilder.novoServicoMedicoOdontologico()
                .comNomeTripulante("Teste")
                .build();
        return servicoMedicoOdontologico;
    }

    private ServicoTransporte obterServicoTransporteValido() {
        Servico empresaServico = (Servico) commonService.buscarEntidadePorId(Servico.class, 1L);

        ServicoTransporte servicoTransporte = ServicoTransporteBuilder.novoServicoTransporte()
                .comAutorizacao("TESTE")
                .comDataHora(new Date())
                .comDestino("Teste")
                .comEmpresa(empresaServico)
                .comOrigem("teste")
                .comVoo("teste")
                .comTipoTransporte(TipoTransporte.TERRESTRE)
                .build();
        return servicoTransporte;
    }

    private Passageiro obterPassageiroValido() {

        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).comAgencia(agencia).build();

        ServicoProtecao servicoProtecao = (ServicoProtecao) commonService.buscarEntidadePorId(ServicoProtecao.class, 1L);
        servicoProtecao.setServicoTransporte(null);
        servicoProtecao.setAgenciamento(agenciamento);

        Passageiro passageiro = PassageiroBuilder.novoPassageiro()
                .comNome("NOME")
                .comCPF("22222222222")
                .comDocumento("PASS1")
                .comServicoProtecao(servicoProtecao)
                .build();

        return passageiro;
    }

    private ServicoAcessoPessoa obterServicoAcessoAPessoaValida() {
        ServicoAcessoPessoa servicoAcessoPessoa = ServicoAcessoPessoaBuilder.novoServicoAcessoPessoa()
                .comId(1L)
                .comCompanhiaDocas(true)
                .comTerminal(false)
                .comTipoSolicitacaoTransito(TipoSolicitacaoTransito.CANCELAMENTO)
                .comTipoAcesso(TipoAcesso.ACESSO)
                .comTipoCategoria(TipoCategoria.PRESTADOR_SERVICO)
                .comTipoNacionalidade(TipoNacionalidade.BRASILEIROS)
                .comPrestadorServico(EmpresaProtecaoBuilder.novaEmpresaProtecao().comId(1002l).comRazaoSocial("Hotel Barra").comCnpj("05864866000164").build())
                .comPrestadorServicoAtividadeBordo("Atividade de bordo")
                .comServicoProtecao(obterServicoProtecaoValido())
                .build();
        servicoAcessoPessoa.getServicoProtecao().setTipoAtendimentoServico(TipoAtendimentoServico.ACESSO_PESSOA);
        return servicoAcessoPessoa;
    }

    private PessoaAcesso obterPessoaValido(ServicoAcessoPessoa servicoAcessoPessoa) {

        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        Agenciamento agenciamento = AgenciamentoBuilder.novoAgenciamento().comId(1L).comAgencia(agencia).build();

        ServicoProtecao servicoProtecao = (ServicoProtecao) commonService.buscarEntidadePorId(ServicoProtecao.class, 1L);
        servicoProtecao.setServicoAcessoPessoa(servicoAcessoPessoa);
        servicoProtecao.setAgenciamento(agenciamento);
        EmpresaProtecao empresa = EmpresaProtecaoBuilder.novaEmpresaProtecao().comId(1002l).comRazaoSocial(servicoAcessoPessoa.getNomePrestadorServico()).comCnpj(servicoAcessoPessoa.getCnpjPrestadorServico()).build();
        PessoaAcesso pessoa = PessoaAcessoBuilder.novoPessoa()
                .comPessoa(PessoaProtecaoBuilder.novaPessoaProtecao().comId(12l).comEmpresa(empresa).comNome("Maria").comCpf("22222222222").comDocumento("PASS1").comDataNascimento(DateBuilder.on(1, 8, 1980).getTime()).build())
                .comServicoProtecao(servicoProtecao)
                .build();

        return pessoa;
    }
}
