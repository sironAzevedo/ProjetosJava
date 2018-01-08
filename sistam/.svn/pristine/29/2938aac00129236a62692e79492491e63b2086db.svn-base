package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoVeiculo;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoAcesso;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoMaterial;
import br.com.petrobras.sistam.common.enums.TipoMercadorias;
import br.com.petrobras.sistam.common.enums.TipoNacionalidade;
import br.com.petrobras.sistam.common.enums.TipoResiduo;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.enums.TipoSolicitacaoTransito;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.enums.TipoUnidadeMedida;
import br.com.petrobras.sistam.common.enums.TipoVeiculo;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioServicoSuprimentoAosNavios;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaProtecaoBuilder;
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
import br.com.petrobras.snarf.common.exception.Pendency;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ValidadorServicoProtecaoTest {

    private ValidadorServicoProtecao validador = new ValidadorServicoProtecao();

    //<editor-fold defaultstate="collapsed" desc="Serviço Proteção">
    @Test
    public void naoSeraPossivelSalvarServicoProtecaoSemAgenciamento() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setAgenciamento(null);
        try {
            validador.validarSalvarServicoProtecao(servicoProtecao);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_PROTECAO_AGENCIAMENTO_OBRIGATORIO, ex.getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoProtecaoSemTipoAtendimentoServico() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setTipoAtendimentoServico(null);
        try {
            validador.validarSalvarServicoProtecao(servicoProtecao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_PROTECAO);
            assertEquals(ConstantesI18N.SERVICO_PROTECAO_SERVICO_EXECUTADO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoProtecaoSemDataExecucao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setDataExecucao(null);
        try {
            validador.validarSalvarServicoProtecao(servicoProtecao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_PROTECAO);
            assertEquals(ConstantesI18N.SERVICO_PROTECAO_DATA_EXECUCAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serviço Executado">
    @Test
    public void naoSeraPermitidoSalvarServicoexecutadoSemDataCancelamento() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.getServicoProtecao().setJustificativa("teste");
        servicoTransporte.getServicoProtecao().setDataCancelamento(null);
        try {
            validador.validarCancelamentoServicoExecutado(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_PROTECAO);
            assertEquals(ConstantesI18N.SERVICO_PROTECAO_DATA_CANCELAMENTO_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoexecutadoSemJustificativa() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.getServicoProtecao().setJustificativa(null);
        servicoTransporte.getServicoProtecao().setDataCancelamento(new Date());
        try {
            validador.validarCancelamentoServicoExecutado(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_PROTECAO);
            assertEquals(ConstantesI18N.SERVICO_PROTECAO_JUSTIFICATIVA_CANCELAMENTO_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold>
    @Test
    public void naoSeraPermitidoSalvarVisitaSemAgenciamento() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setAgenciamento(null);
        try {
            validador.validarSalvarServicoProtecao(servicoProtecao);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_PROTECAO_AGENCIAMENTO_OBRIGATORIO, ex.getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarVisitaSemInformarADataDeExecucao() {
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setDataExecucao(null);
        try {
            validador.validarSalvarServicoProtecao(servicoProtecao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_PROTECAO);
            assertEquals(ConstantesI18N.SERVICO_PROTECAO_DATA_EXECUCAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Serviço Médico Odontológico">
    @Test
    public void naoSeraPermitidoSalvarServicoMedicoOdontologicoSemServicoProtecao() {
        ServicoMedicoOdontologico servicoMedicoOdontologico = obterServicoMedicoOdontologicoValido();
        servicoMedicoOdontologico.setServicoProtecao(null);
        try {
            validador.validarSalvarServicoMedicoOdontologico(servicoMedicoOdontologico);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_MEDICO_ODONTOLOGICO_SERVICO_PROTECAO_OBRIGATORIO, ex.getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoMedicoOdontologicoSemNomeTripulante() {
        ServicoMedicoOdontologico servicoMedicoOdontologico = obterServicoMedicoOdontologicoValido();
        servicoMedicoOdontologico.setNomeTripulante(null);
        try {
            validador.validarSalvarServicoMedicoOdontologico(servicoMedicoOdontologico);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_MEDICO_ODONTOLOGICO);
            assertEquals(ConstantesI18N.SERVICO_MEDICO_ODONTOLOGICO_NOME_TRIPULANTE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Transporte">
    @Test
    public void naoSeraPermitidoSalvarServicoTransporteSemServicoProtecao() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.setServicoProtecao(null);
        try {
            validador.validarSalvarServicoTransporte(servicoTransporte);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_SERVICO_PROTECAO_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarServicoTransporteSemDataServico() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.setDataServico(null);
        try {
            validador.validarSalvarServicoTransporte(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_TRANSPORTE);
            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_DATA_HORA_SERVICO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoTransporteSemTipoTransporte() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.setTipoTransporte(null);
        try {
            validador.validarSalvarServicoTransporte(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_TRANSPORTE);
            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_TIPO_TRANSPORTE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

//    @Test
//    public void naoSeraPermitidoSalvarServicoTransporteAereoSemNomeTripulante() {
//        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
//        servicoTransporte.setTipoTransporte(TipoTransporte.AEREO);
//        servicoTransporte.setNomeTripulante(null);
//        try {
//            validador.validarSalvarServicoTransporte(servicoTransporte);
//            fail();
//        } catch (SistamPendingException ex) {
//            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_TRANSPORTE);
//            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_NOME_TRIPULANTE_OBRIGATORIO, pendencias.get(0).getMessage());
//        }
//    }    
    @Test
    public void naoSeraPermitidoSalvarServicoTransporteOutrosSemEmpresaServico() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.setTipoTransporte(TipoTransporte.MARITIMO);
        servicoTransporte.setEmpresaServico(null);
        try {
            validador.validarSalvarServicoTransporte(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_TRANSPORTE);
            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_NOME_EMPRESA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoTransporteOutrosSemOrigem() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.setTipoTransporte(TipoTransporte.MARITIMO);
        servicoTransporte.setOrigem(null);
        try {
            validador.validarSalvarServicoTransporte(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_TRANSPORTE);
            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_ORIGEM_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoTransporteOutrosSemDestino() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.setTipoTransporte(TipoTransporte.MARITIMO);
        servicoTransporte.setDestino(null);
        try {
            validador.validarSalvarServicoTransporte(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_TRANSPORTE);
            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_DESTINO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarServicoTransporteAereoSemPassageiros() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.setTipoTransporte(TipoTransporte.AEREO);
        servicoTransporte.adicionarPassageiro(null);
        try {
            validador.validarSalvarServicoTransporte(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_TRANSPORTE);
            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_AEREO_OBRIGATORIO_PASSAGEIROS, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarServicoTransporteComServicoProtecaoAntigoSemObservacao() {
        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        servicoTransporte.setTipoTransporte(TipoTransporte.MARITIMO);
        servicoTransporte.getServicoProtecao().setNovo(false);
        servicoTransporte.getServicoProtecao().setObservacao("");
        try {
            validador.validarSalvarServicoTransporte(servicoTransporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_TRANSPORTE);
            assertEquals(ConstantesI18N.SERVICO_TRANSPORTE_OBSERVACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Serviço Hospedagem">  
    @Test
    public void naoSeraPermitidoSalvarServicoHospedagemSemInformarServicoProtecao() {
        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();
        servicoHospedagem.setServicoProtecao(null);
        try {
            validador.validarSalvarServicoHospedagem(servicoHospedagem);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_HOSPEDAGEM_SERVICO_PROTECAO_OBRIGATORIO, ex.getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoHospedagemSemInformarHotel() {
        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();
        servicoHospedagem.setEmpresaServico(null);
        try {
            validador.validarSalvarServicoHospedagem(servicoHospedagem);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_HOSPEDAGEM);
            assertEquals(ConstantesI18N.SERVICO_HOSPEDAGEM_HOTEL_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoHospedagemSemInformarDataChegada() {
        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();
        servicoHospedagem.setDataChegada(null);
        try {
            validador.validarSalvarServicoHospedagem(servicoHospedagem);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_HOSPEDAGEM);
            assertEquals(ConstantesI18N.SERVICO_HOSPEDAGEM_DATA_CHEGADA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoHospedagemSemInformarDataSaida() {
        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();
        servicoHospedagem.setDataSaida(null);
        try {
            validador.validarSalvarServicoHospedagem(servicoHospedagem);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_HOSPEDAGEM);
            assertEquals(ConstantesI18N.SERVICO_HOSPEDAGEM_DATA_SAIDA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoHospedagemComDataChegadaPosteriorADataSaida() {
        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();
        servicoHospedagem.setDataChegada(DateBuilder.on(1, 3, 2014).getTime());
        try {
            validador.validarSalvarServicoHospedagem(servicoHospedagem);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_HOSPEDAGEM);
            assertEquals(ConstantesI18N.SERVICO_HOSPEDAGEM_DATA_CHEGADA_NAO_DEVE_SER_POSTERIOR_DATA_SAIDA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoHospedagemSemInformarpeloMenosUmHospede() {
        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();

        try {
            validador.validarSalvarServicoHospedagem(servicoHospedagem);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_HOSPEDAGEM);
            assertEquals(ConstantesI18N.SERVICO_HOSPEDAGEM_INFORME_PELO_MENOS_UM_HOSPEDE, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarServicoHospedagemComServicoProtecaoAntigoSemObservacao() {
        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();
        servicoHospedagem.adicionarHospede(obterHospedeValido());
        servicoHospedagem.getServicoProtecao().setNovo(false);
        servicoHospedagem.getServicoProtecao().setObservacao("");
        try {
            validador.validarSalvarServicoHospedagem(servicoHospedagem);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_HOSPEDAGEM);
            assertEquals(ConstantesI18N.SERVICO_HOSPEDAGEM_OBSERVACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Acesso a Pessoas">  
    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaSemInformarServicoProtecao() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.setServicoProtecao(null);
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_SERVICO_PROTECAO_OBRIGATORIO, ex.getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaSemLocalAcesso() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.setTerminal(false);
        servicoAcessoPessoa.setCompanhiaDocas(false);
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_LOCAL_DO_ACESSO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaSemTipoSolicitacaoTransito() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.setTipoSolicitacaoTransito(null);
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_TIPO_SOLI_TRANSITO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaSemTipoAcesso() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.setTipoAcesso(null);
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_TIPO_ACESSO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaSemTipoCategoria() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.setTipoCategoria(null);
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_TIPO_CATEGORIA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoPessoaComDocumentoJaCadastrado() {
        Pessoa pessoaA = PessoaProtecaoBuilder.novaPessoaProtecao().comId(1l).comDocumento("AA").comNome("A1").build();
        Pessoa pessoaB = PessoaProtecaoBuilder.novaPessoaProtecao().comId(2l).comDocumento("BB").comNome("B1").build();

        PessoaAcesso pessoa = PessoaAcessoBuilder.novoPessoa(obterPessoaAcessoValido()).comId(null).comPessoa(pessoaA).build();

        PessoaAcesso pessoa1 = PessoaAcessoBuilder.novoPessoa(obterPessoaAcessoValido()).comId(null).comPessoa(pessoaA).build();

        PessoaAcesso pessoa2 = PessoaAcessoBuilder.novoPessoa(obterPessoaAcessoValido()).comId(null).comPessoa(pessoaB).build();

        ServicoAcessoPessoa servicoAcessoPessoa = pessoa.getServicoProtecao().getServicoAcessoPessoa();

        servicoAcessoPessoa.adicionarPessoa(pessoa);
        servicoAcessoPessoa.adicionarPessoa(pessoa1);
        servicoAcessoPessoa.adicionarPessoa(pessoa2);
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_PESSOAS_DOCUMENTO_JA_EXISTENTE, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoPessoaComCpfJaCadastrado() {
        Pessoa pessoaA = PessoaProtecaoBuilder.novaPessoaProtecao().comId(1l).comCpf("AA").comNome("A1").build();
        Pessoa pessoaB = PessoaProtecaoBuilder.novaPessoaProtecao().comId(2l).comCpf("BB").comNome("B1").build();

        PessoaAcesso pessoa = PessoaAcessoBuilder.novoPessoa(obterPessoaAcessoValido()).comId(null).comPessoa(pessoaA).build();
        PessoaAcesso pessoa1 = PessoaAcessoBuilder.novoPessoa(obterPessoaAcessoValido()).comId(null).comPessoa(pessoaA).build();
        PessoaAcesso pessoa2 = PessoaAcessoBuilder.novoPessoa(obterPessoaAcessoValido()).comId(null).comPessoa(pessoaB).build();

        ServicoAcessoPessoa servicoAcessoPessoa = pessoa.getServicoProtecao().getServicoAcessoPessoa();

        servicoAcessoPessoa.adicionarPessoa(pessoa);
        servicoAcessoPessoa.adicionarPessoa(pessoa1);
        servicoAcessoPessoa.adicionarPessoa(pessoa2);
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_PESSOAS_CPF_JA_EXISTENTE, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaSemTipoNacionalidade() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.setTipoNacionalidade(null);
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_TIPO_NACIONALIDADE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaTipoCategoriaSemPrestadorServico() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.setNomePrestadorServico("");
        servicoAcessoPessoa.setCnpjPrestadorServico("");
        servicoAcessoPessoa.setTelefonePrestadorServico("");
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_EMPRESA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaTipoCategoriaPrestadorServicoSemAtividadeBordo() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.setPrestadorServicoAtividadeBordo("");
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_ATIVIDADE_BORDO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarServicoAcessoPessoaAlteracaoSemObservacao() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();
        servicoAcessoPessoa.adicionarPessoa(obterPessoaAcessoValido());
        servicoAcessoPessoa.getServicoProtecao().setNovo(false);
        servicoAcessoPessoa.getServicoProtecao().setObservacao("");
        try {
            validador.validarSalvarServicoAcessoPessoa(servicoAcessoPessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_ACESSO_PESSOA);
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_OBSERVACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Hospede">    
    @Test
    public void naoSeraPossivelSalvarHospedeSemServicoProtecao() {
        Hospede hospede = obterHospedeValido();
        hospede.setServicoProtecao(null);
        try {
            validador.validarCamposObrigatoriosHospede(hospede);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.HOSPEDE_SEM_HOSPEDAGEM, ex.getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoHospedeSemNome() {
        Hospede hospede = obterHospedeValido();
        hospede.setNome(null);
        try {
            validador.validarCamposObrigatoriosHospede(hospede);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.HOSPEDE);
            assertEquals(ConstantesI18N.HOSPEDE_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoHospedeSemDocumentoOuCpf() {
        Hospede hospede = obterHospedeValido();
        hospede.setDocumento(null);
        hospede.setCpf(null);
        try {
            validador.validarCamposObrigatoriosHospede(hospede);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.HOSPEDE);
            assertEquals(ConstantesI18N.HOSPEDE_IDENTIFICADOR_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoRetornarExcecaoQuandoHospedeComDocumentoESemCpf() {
        Hospede hospede = obterHospedeValido();
        hospede.setCpf(null);
        validador.validarCamposObrigatoriosHospede(hospede);
    }

    @Test
    public void naoRetornarExcecaoQuandoHospedeComCpfESemDocumento() {
        Hospede hospede = obterHospedeValido();
        hospede.setDocumento(null);
        validador.validarCamposObrigatoriosHospede(hospede);
    }

    @Test
    public void deveRetornarExcecaoQuandoHospedeComDocumentoJaCadastrado() {
        Hospede hospede = obterHospedeValido();
        hospede.setId(2L);
        hospede.setDocumento("AA");
        hospede.setCpf("1234");

        Hospede hospede1 = obterHospedeValido();
        hospede1.setId(3L);
        hospede1.setDocumento("AA");
        hospede1.setCpf("4567");

        Hospede hospede2 = obterHospedeValido();
        hospede2.setId(4L);
        hospede2.setDocumento("BB");
        hospede2.setCpf("91011");

        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede);
        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede1);
        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede2);
        try {
            validador.validarCamposObrigatoriosHospede(hospede);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.HOSPEDE);
            assertEquals(ConstantesI18N.HOSPEDE_DOCUMENTO_JA_EXISTENTE, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoHospedeComCpfJaCadastrado() {
        Hospede hospede = obterHospedeValido();
        hospede.setId(2L);
        hospede.setDocumento("AA");
        hospede.setCpf("1234");

        Hospede hospede1 = obterHospedeValido();
        hospede1.setId(3L);
        hospede1.setDocumento("BB");
        hospede1.setCpf("1234");

        Hospede hospede2 = obterHospedeValido();
        hospede2.setId(4L);
        hospede2.setDocumento("CC");
        hospede2.setCpf("91011");

        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede);
        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede1);
        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede2);
        try {
            validador.validarCamposObrigatoriosHospede(hospede);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.HOSPEDE);
            assertEquals(ConstantesI18N.HOSPEDE_CPF_JA_EXISTENTE, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoDeveRetornarExcecaoQuandoNaoHouverCpfOuDocumentoDuplicados() {
        Hospede hospede = obterHospedeValido();
        hospede.setId(2L);
        hospede.setDocumento("AA");
        hospede.setCpf("1234");

        Hospede hospede1 = obterHospedeValido();
        hospede1.setId(3L);
        hospede1.setDocumento("BB");
        hospede1.setCpf("5789");

        Hospede hospede2 = obterHospedeValido();
        hospede2.setId(4L);
        hospede2.setDocumento("CC");
        hospede2.setCpf("91011");

        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede);
        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede1);
        hospede.getServicoProtecao().getServicoHospedagem().adicionarHospede(hospede2);
        validador.validarCamposObrigatoriosHospede(hospede);
    }

    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Passageiro">    
    @Test
    public void naoSeraPossivelSalvarPassageiroSemServicoProtecao() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro.setServicoProtecao(null);
        try {
            validador.validarCamposObrigatoriosPassageiro(passageiro);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PASSAGEIRO_SEM_TRANSPORTE, ex.getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoPassageiroSemNome() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro.setNome(null);
        try {
            validador.validarCamposObrigatoriosPassageiro(passageiro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PASSAGEIRO);
            assertEquals(ConstantesI18N.PASSAGEIRO_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoPassageiroSemDocumentoOuCpf() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro.setDocumento(null);
        passageiro.setCpf(null);
        try {
            validador.validarCamposObrigatoriosPassageiro(passageiro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PASSAGEIRO);
            assertEquals(ConstantesI18N.PASSAGEIRO_IDENTIFICADOR_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoRetornarExcecaoQuandoPassageiroComDocumentoESemCpf() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro.setCpf(null);
        validador.validarCamposObrigatoriosPassageiro(passageiro);
    }

    @Test
    public void naoRetornarExcecaoQuandoPassageiroComCpfESemDocumento() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro.setDocumento(null);
        validador.validarCamposObrigatoriosPassageiro(passageiro);
    }

    @Test
    public void deveRetornarExcecaoQuandoPassageiroComDocumentoJaCadastrado() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro.setId(2L);
        passageiro.setDocumento("AA");
        passageiro.setCpf("1234");

        Passageiro passageiro1 = obterPassageiroValido();
        passageiro1.setId(3L);
        passageiro1.setDocumento("AA");
        passageiro1.setCpf("4567");

        Passageiro passageiro2 = obterPassageiroValido();
        passageiro2.setId(4L);
        passageiro2.setDocumento("BB");
        passageiro2.setCpf("91011");

        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro);
        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro1);
        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro2);
        try {
            validador.validarCamposObrigatoriosPassageiro(passageiro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PASSAGEIRO);
            assertEquals(ConstantesI18N.PASSAGEIRO_DOCUMENTO_JA_EXISTENTE, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoPassageiroComCpfJaCadastrado() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro.setId(2L);
        passageiro.setDocumento("AA");
        passageiro.setCpf("1234");

        Passageiro passageiro1 = obterPassageiroValido();
        passageiro1.setId(3L);
        passageiro1.setDocumento("BB");
        passageiro1.setCpf("1234");

        Passageiro passageiro2 = obterPassageiroValido();
        passageiro2.setId(4L);
        passageiro2.setDocumento("CC");
        passageiro2.setCpf("91011");

        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro);
        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro1);
        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro2);
        try {
            validador.validarCamposObrigatoriosPassageiro(passageiro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PASSAGEIRO);
            assertEquals(ConstantesI18N.PASSAGEIRO_CPF_JA_EXISTENTE, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoDeveRetornarExcecaoQuandorPassageiroNaoHouverCpfOuDocumentoDuplicados() {
        Passageiro passageiro = obterPassageiroValido();
        passageiro.setId(2L);
        passageiro.setDocumento("AA");
        passageiro.setCpf("1234");

        Passageiro passageiro1 = obterPassageiroValido();
        passageiro1.setId(3L);
        passageiro1.setDocumento("BB");
        passageiro1.setCpf("5789");

        Passageiro passageiro2 = obterPassageiroValido();
        passageiro2.setId(4L);
        passageiro2.setDocumento("CC");
        passageiro2.setCpf("91011");

        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro);
        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro1);
        passageiro.getServicoProtecao().getServicoTransporte().adicionarPassageiro(passageiro2);
        validador.validarCamposObrigatoriosPassageiro(passageiro);
    }

    //</editor-fold>   
    
    //<editor-fold defaultstate="collapsed" desc="Pessoa">    
    @Test
    public void naoSeraPossivelSalvarPessoaSemServicoProtecao() {
        PessoaAcesso pessoa = obterPessoaAcessoValido();
        pessoa.setServicoProtecao(null);
        try {
            validador.validarCamposObrigatoriosPessoa(pessoa);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.SERVICO_ACESSO_PESSOA_SERVICO_PROTECAO_OBRIGATORIO, ex.getMessage());
        }
    }

    @Test
    public void naoSeraPossivelSalvarPessoaComVolumesSemDescBagagem() {
        PessoaAcesso pessoa = obterPessoaAcessoValido();
        pessoa.setBagagem(null);
        try {
            validador.validarCamposObrigatoriosPessoa(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PESSOA);
            assertEquals(ConstantesI18N.PESSOA_BAGAGEM_OBRIGADO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPossivelSalvarPessoaComDescBagagemSemVolumes() {
        PessoaAcesso pessoa = obterPessoaAcessoValido();
        pessoa.setVolume(null);
        try {
            validador.validarCamposObrigatoriosPessoa(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PESSOA);
            assertEquals(ConstantesI18N.PESSOA_VOLUMES_OBRIGADO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPossivelSalvarPessoaComVolumesIgualZero() {
        PessoaAcesso pessoa = obterPessoaAcessoValido();
        pessoa.setVolume(0);
        try {
            validador.validarCamposObrigatoriosPessoa(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PESSOA);
            assertEquals(ConstantesI18N.PESSOA_VOLUMES_MAIOR_QUE_ZERO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPossivelSalvarPessoaSemSelecionarPessoaGeral() {
        PessoaAcesso pessoa = obterPessoaAcessoValido();
        pessoa.setNome(null);
        try {
            validador.validarCamposObrigatoriosPessoa(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PESSOA);
            assertEquals(ConstantesI18N.PESSOA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Busca Servico Suprimento Transito Empresa Para Relatorio">   
    @Test
    public void testaValidarCamposObrigatoriosBuscaServicoSuprimentoTransitoEmpresaParaRelatorio() {
        FiltroRelatorioServicoSuprimentoAosNavios filtro = new FiltroRelatorioServicoSuprimentoAosNavios();
        try {
            validador.validarCamposObrigatoriosBuscaServicoSuprimentoTransitoEmpresaParaRelatorio(filtro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.RELATORIO_INFO);
            assertEquals(ConstantesI18N.RELATORIO_SUPRIMENTO_NAVIOS_AGENICA_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>  

    //<editor-fold defaultstate="collapsed" desc="Serviço de Suprimento Transito Condutores">  
    @Test
    public void testaValidarCamposObrigatoriosSuprimentoCondutorParaNomeObrigatorio() {
        ServicoSuprimentoTransitoVeiculo veiculo = obterServicoSuprimentoTransitoVeiculoValido();
        veiculo.setCpfCondutor(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoCondutor(veiculo);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_CONDUTOR);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_CONDUTOR_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoCondutorParaVeiculoObrigatorio() {
        ServicoSuprimentoTransitoVeiculo veiculo = obterServicoSuprimentoTransitoVeiculoValido();
        veiculo.setTipoVeiculo(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoCondutor(veiculo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_CONDUTOR);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_CONDUTOR_VEICULO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoCondutorParaPlacaObrigatorio() {
        ServicoSuprimentoTransitoVeiculo veiculo = obterServicoSuprimentoTransitoVeiculoValido();
        veiculo.setPlacaVeiculo(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoCondutor(veiculo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_CONDUTOR);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_CONDUTOR_PLACA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    private ServicoSuprimentoTransitoVeiculo obterServicoSuprimentoTransitoVeiculoValido() {
        ServicoSuprimentoTransitoVeiculo veiculo = new ServicoSuprimentoTransitoVeiculo();
        veiculo.setNomeCondutor("test");
        veiculo.setCpfCondutor("00000000000");
        veiculo.setTipoVeiculo(TipoVeiculo.AUTOMOVEL);
        veiculo.setPlacaVeiculo("ABC-1234");
        return veiculo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serviço de Suprimento Transito Empresas">  
    @Test
    public void testaValidarCamposObrigatoriosSuprimentoFornecedorParaEmpresaObrigatorio() {
        ServicoSuprimentoTransitoEmpresa empresa = obterServicoSuprimentoTransitoEmpresaValido();
        empresa.setCnpjPrestadorServico(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoFornecedor(empresa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_EMPRESA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoFornecedorParaPeosBrutoObrigatorio() {
        ServicoSuprimentoTransitoEmpresa empresa = obterServicoSuprimentoTransitoEmpresaValido();
        empresa.setValorPesoBruto(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoFornecedor(empresa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_PESO_BRUTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoFornecedorParaVolumeObrigatorio() {
        ServicoSuprimentoTransitoEmpresa empresa = obterServicoSuprimentoTransitoEmpresaValido();
        empresa.setQuantidadeVolume(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoFornecedor(empresa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_VOLUME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoFornecedorParaNotaFiscalObrigatorio() {
        ServicoSuprimentoTransitoEmpresa empresa = obterServicoSuprimentoTransitoEmpresaValido();
        empresa.setDescNotaFiscal(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoFornecedor(empresa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_NOTA_FISCAL_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    public ServicoSuprimentoTransitoEmpresa obterServicoSuprimentoTransitoEmpresaValido() {
        ServicoSuprimentoTransitoEmpresa empresa = new ServicoSuprimentoTransitoEmpresa();
        empresa.setCnpjPrestadorServico("000000000000000");
        empresa.setValorPesoBruto("10 KG");
        empresa.setValorMercadorias(BigDecimal.TEN);
        empresa.setDescNotaFiscal("Nota Fria");
        empresa.setQuantidadeVolume(1);
        return empresa;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serviço de Suprimento Transito">  
    @Test
    public void testaValidarCamposObrigatoriosSuprimentoTransitoParaLocalDeAcessoObrigatorio() {
        ServicoSuprimentoTransito servicoSuprimentoTransito = obterServicoSuprimentoTransitoValido();
        servicoSuprimentoTransito.setCompanhiaDocas(false);
        servicoSuprimentoTransito.setTerminal(false);
        try {
            validador.validarCamposObrigatoriosSuprimentoTransito(servicoSuprimentoTransito);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_LOCAL_ACESSO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoTransitoParaLocalDeAcessoTerminalObrigatorio() {
        ServicoSuprimentoTransito servicoSuprimentoTransito = obterServicoSuprimentoTransitoValido();
        servicoSuprimentoTransito.setCompanhiaDocas(false);
        servicoSuprimentoTransito.setTerminal(true);
        try {
            validador.validarCamposObrigatoriosSuprimentoTransito(servicoSuprimentoTransito);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_LOCAL_ACESSO_DESC_TERMINAL_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoTransitoParaTipoAcessoObrigatorio() {
        ServicoSuprimentoTransito servicoSuprimentoTransito = obterServicoSuprimentoTransitoValido();
        servicoSuprimentoTransito.setTipoMercadorias(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoTransito(servicoSuprimentoTransito);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_TIPO_ACESSO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoTransitoParaTipoMaterialObrigatorio() {
        ServicoSuprimentoTransito servicoSuprimentoTransito = obterServicoSuprimentoTransitoValido();
        servicoSuprimentoTransito.setTipoMaterial(null);
        try {
            validador.validarCamposObrigatoriosSuprimentoTransito(servicoSuprimentoTransito);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_TIPO_MATERIAL_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarCamposObrigatoriosSuprimentoTransitoParaFornecedoresObrigatorio() {
        ServicoSuprimentoTransito servicoSuprimentoTransito = obterServicoSuprimentoTransitoValido();
        servicoSuprimentoTransito.setTransitosEmpresas(new HashSet<ServicoSuprimentoTransitoEmpresa>());
        try {
            validador.validarCamposObrigatoriosSuprimentoTransito(servicoSuprimentoTransito);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_PELO_MENOS_UM_FORNECEDOR, pendencias.get(0).getMessage());
        }
    }

    private ServicoSuprimentoTransito obterServicoSuprimentoTransitoValido() {
        ServicoSuprimentoTransito servicoSuprimentoTransito = new ServicoSuprimentoTransito();
        servicoSuprimentoTransito.setTipoMaterial(TipoMaterial.RANCHO);
        servicoSuprimentoTransito.setTipoMercadorias(TipoMercadorias.ENTRADA);
        servicoSuprimentoTransito.setCompanhiaDocas(true);
        Set<ServicoSuprimentoTransitoEmpresa> fornecedores = new HashSet<ServicoSuprimentoTransitoEmpresa>();
        fornecedores.add(new ServicoSuprimentoTransitoEmpresa());
        servicoSuprimentoTransito.setTransitosEmpresas(fornecedores);
        return servicoSuprimentoTransito;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serviço de Suprimento">  
    @Test
    public void testaValidarSalvarServicoSuprimentoAosNaviosParaDataInicioOperacaoObrigatorio() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setDataInicioOperacao(null);
        try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_DT_INICIO_OPERACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarSalvarServicoSuprimentoAosNaviosParaDataTerminoOperacaoObrigatorio() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setDataFimOperacao(null);
        try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_DT_TERMINO_OPERACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarSalvarServicoSuprimentoAosNaviosParaDataInicioMaiorQueDataTerminoOperacaoObrigatorio() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setDataInicioOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 10, 1, null));
        servicoSuprimento.setDataFimOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 10, 0, null));
        try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_DT_INICIO_MAIOR_DT_TERMINO_OPERACAO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarSalvarServicoSuprimentoAosNaviosParaDataInicioIgualADataTerminoOperacaoObrigatorio() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setDataInicioOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 10, 0, null));
        servicoSuprimento.setDataFimOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 10, 0, null));
        validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
    }

    @Test
    public void testaValidarSalvarServicoSuprimentoAosNavios() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
    }

    @Test
    public void testaValidarSalvarServicoSuprimentoAosNaviosParaObservacaoObrigatorioNaAlteracao() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setId(1l);
        try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_OBSERVACAO_OBRIGATORIA_NA_ALTERACAO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarSalvarServicoSuprimentoAosNaviosParaEmpresaObrigatorio() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setEmpresaMaritima(null);
        try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMPRESA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void testaValidarSalvarServicoSuprimentoAosNaviosParaEmbarcaoObrigatorio() {
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setEmpresaServico(null);
        try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void SeraPosivelSalvarServicoSuprimentoAosNaviosSemTransporteMaritmo(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setValorTransporteMaritimo(null);
         try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void SeraPosivelSalvarServicoSuprimentoAosNaviosSemTransporteRodoviario(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setValorTransporteRodoviario(null);
         try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void SeraPosivelSalvarServicoSuprimentoAosNaviosSemCentroCusto(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setCentroCusto(null);
         try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void SeraPosivelSalvarServicoSuprimentoAosNaviosSemCentroCustoDo(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setCentroCustoDo(null);
         try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void SeraPosivelSalvarServicoSuprimentoAosNaviosSemCustoOGMO(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setCustoOGMO(null);
         try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void SeraPosivelSalvarServicoSuprimentoAosNaviosSemCustoOgmoDobra(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setCustoOgmoDobra(null);
         try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void SeraPosivelSalvarServicoSuprimentoAosNaviosSemCustoOperadorPortuario(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setCustoOperadorPortuario(null);
         try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void SeraPosivelSalvarServicoSuprimentoAosNaviosSemCustoHoraExcedente(){
        ServicoSuprimento servicoSuprimento = obterServicoSuprimentoValido();
        servicoSuprimento.setCustoHoraExcedente(null);
         try {
            validador.validarSalvarServicoSuprimentoAosNavios(servicoSuprimento);
            //fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_SUPRIMENTO);
            assertEquals(ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    

    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Protecao Retirada Residuo/Lixo"> 
    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemResponsavelCusto() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setResponsavelCusto(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_REQUERENTE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemEmpresaMaritima() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setEmpresaMaritima(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_RESPONSAVEL_TRANSBORDO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemEmpresaServico() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setEmpresaServico(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_EMBARCACAO_TRANSBORDO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemTransportadoraRodoviaria() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setEmpresaMaritimaRodoviaria(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_TRANSPORTADORA_RODOVIARIA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemTipoResiduo() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setTipoResiduo(TipoResiduo.CLASSE_I);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            // fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_TIPO_RESIDUO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemCaracteriazacao() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setCaracterizacao(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_CARACTERIZACAO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemEstadoFisico() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setEstadoFisico(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_ESTADO_FISICO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemClassificacao() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setClassificacao(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_CLASSIFICACAO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemCodigoOnu() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setCodigoOnu(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_CODIGO_ONU, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemUnidadeMedida() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setTipoUnidadeMedida(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_TIPO_UNIDADE_MEDIDA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemValorDoServico() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setValorServico(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_VALOR_SERVICO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemDataInicioOperacao() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setDataInicioOperacao(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DT_INICIO_OPERACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemDataTerminoOperacao() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setDataFimOperacao(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DT_TERMINO_OPERACAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoParaDataInicioMaiorQueDataTerminoOperacao() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setDataInicioOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 10, 1, null));
        servicoRetiradaResiduoLixo.setDataFimOperacao(SistamDateUtils.informarDataHora(1, 1, 2015, 10, 0, null));
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DT_INICIO_MAIOR_DT_TERMINO_OPERACAO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemLocalArmazenagem() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setLocalArmazenagem(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_LOCAL_ARMAZENAGEM, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemLonArmazenagem() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setLonArmazenagem(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_LON_ARMAZENAGEM, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemDescricaoCadri() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setDescCadri(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DESCRICAO_CADRI, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemLocalDestinoFinal() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setLocalDestinoFinal(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_LOCAL_DESTINO_FINAL, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemLonDestinoFinal() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setLonDestinoFinal(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_LON_DESTINO_FINAL, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelSalvarServicoRetiradaResiduoLixoSemCadriDestinoFinal() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setCadriDestinoFinal(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_CADRI_DESTINO_FINAL, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPosivelAtualizarServicoRetiradaResiduoLixoSemObservacaoObrigatorioNaAlteracao() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setId(1l);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO);
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_OBSERVACAO_OBRIGATORIA_NA_ALTERACAO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPossivelSalvarServicoRetiradaResiduoLixoSemServicoProtecao() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = obterServicoRetiradaResiduoLixoValido();
        servicoRetiradaResiduoLixo.setServicoProtecao(null);
        try {
            validador.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);
            //fail();
        } catch (Exception ex) {
            assertEquals(ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_SERVICO_PROTECAO_OBRIGATORIO, ex.getMessage());
        }
    }

    //</editor-fold> 
    
    private Hospede obterHospedeValido() {

        ServicoHospedagem servicoHospedagem = obterServicoHospedagemValido();
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setServicoHospedagem(servicoHospedagem);

        Hospede hospede = HospedeBuilder.novoHospede()
                .comId(1L)
                .comNome("NOME")
                .comCPF("22222222222")
                .comDocumento("PASS1")
                .comServicoProtecao(servicoProtecao)
                .build();
        return hospede;
    }

    private Passageiro obterPassageiroValido() {

        ServicoTransporte servicoTransporte = obterServicoTransporteValido();
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setServicoTransporte(servicoTransporte);

        Passageiro passageiro = PassageiroBuilder.novoPassageiro()
                .comId(1L)
                .comNome("NOME")
                .comCPF("22222222222")
                .comDocumento("PASS1")
                .comServicoProtecao(servicoProtecao)
                .build();
        return passageiro;
    }

    private PessoaAcesso obterPessoaAcessoValido() {
        ServicoAcessoPessoa servicoAcessoPessoa = obterServicoAcessoPessoaValido();

        EmpresaProtecao empresa = EmpresaProtecaoBuilder.novaEmpresaProtecao().comRazaoSocial(servicoAcessoPessoa.getNomePrestadorServico()).comCnpj(servicoAcessoPessoa.getCnpjPrestadorServico()).build();

        PessoaAcesso pessoa = PessoaAcessoBuilder.novoPessoa()
                .comId(1L)
                .comPessoa(PessoaProtecaoBuilder.novaPessoaProtecao().comId(1l).comNome("Xpto").comEmpresa(empresa).comDocumento("1212112").build())
                .isAtivo(true)
                .comBagagem("Bagagem")
                .comVolume(1)
                .comServicoProtecao(servicoAcessoPessoa.getServicoProtecao())
                .comResponsavel("Responsavel")
                .build();
        return pessoa;
    }

    private ServicoProtecao obterServicoProtecaoValido() {

        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao()
                .comId(1L)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build())
                .comTipoAtendimentoServico(TipoAtendimentoServico.CONTROLE_PRAGAS)
                .comDataExecucao(DateBuilder.on(1, 1, 2014).getTime())
                .build();

        return servicoProtecao;
    }

    private ServicoHospedagem obterServicoHospedagemValido() {
        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao().comId(1L).comTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM).build();

        ServicoHospedagem servicoHospedagem = ServicoHospedagemBuilder.novoServicoHospedagem()
                .comServicoProtecao(servicoProtecao)
                .comHotel(ServicoBuilder.novoServico().comId(1L).comNome("HOTEL TEST").comTipoServico(TipoServico.PRATICOS).build())
                .comDataChegada(DateBuilder.on(1, 1, 2014).getTime())
                .comDataSaida(DateBuilder.on(1, 2, 2014).getTime())
                .comAutorizacao("AUTORIZACAO")
                .build();
        return servicoHospedagem;
    }

    private ServicoTransporte obterServicoTransporteValido() {

        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao().comId(1L).comTipoAtendimentoServico(TipoAtendimentoServico.TRANSPORTE).build();

        ServicoTransporte servicoTransporte = ServicoTransporteBuilder.novoServicoTransporte()
                .comAutorizacao("TESTE")
                .comDataHora(new Date())
                .comDestino("Teste")
                .comEmpresa(new Servico())
                .comOrigem("teste")
                .comVoo("teste")
                .comTipoTransporte(TipoTransporte.TERRESTRE)
                .comServicoProtecao(servicoProtecao)
                .build();
        return servicoTransporte;
    }

    private ServicoAcessoPessoa obterServicoAcessoPessoaValido() {
        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao(obterServicoProtecaoValido()).comId(1L).comTipoAtendimentoServico(TipoAtendimentoServico.ACESSO_PESSOA).comObservacao("OBSERVACAO").build();

        EmpresaProtecao empresa = EmpresaProtecaoBuilder.novaEmpresaProtecao().comId(12l).comRazaoSocial("Empresa X").comCnpj("00000000000000").build();

        ServicoAcessoPessoa servicoAcessoPessoa = ServicoAcessoPessoaBuilder.novoServicoAcessoPessoa()
                .comServicoProtecao(servicoProtecao)
                .comTipoAcesso(TipoAcesso.ACESSO)
                .comTipoSolicitacaoTransito(TipoSolicitacaoTransito.AUTORIZACAO)
                .comTipoCategoria(TipoCategoria.PRESTADOR_SERVICO)
                .comTipoNacionalidade(TipoNacionalidade.BRASILEIROS)
                .comTerminal(true)
                .comCompanhiaDocas(true)
                .comDescricaoTerminal("NOVO TERMINAL")
                .comPrestadorServico(empresa)
                .comPrestadorServicoAtividadeBordo("Atividade de bordo")
                .build();
        servicoProtecao.setServicoAcessoPessoa(servicoAcessoPessoa);
        return servicoAcessoPessoa;
    }

    private ServicoMedicoOdontologico obterServicoMedicoOdontologicoValido() {

        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao().comId(1L).comTipoAtendimentoServico(TipoAtendimentoServico.MEDICO_ODONTOLOGICO).build();

        ServicoMedicoOdontologico servicoMedicoOdontologico = ServicoMedicoOdontologicoBuilder.novoServicoMedicoOdontologico()
                .comNomeTripulante("teste")
                .comServicoProtecao(servicoProtecao)
                .build();
        return servicoMedicoOdontologico;
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

    private ServicoRetiradaResiduoLixo obterServicoRetiradaResiduoLixoValido() {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = new ServicoRetiradaResiduoLixo();
        ServicoProtecao servicoProtecao = obterServicoProtecaoValido();
        servicoProtecao.setServicoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);

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
                .comServicoProtecao(servicoProtecao)
                .build();

        return retiradaResiduoLixo;
    }
}
