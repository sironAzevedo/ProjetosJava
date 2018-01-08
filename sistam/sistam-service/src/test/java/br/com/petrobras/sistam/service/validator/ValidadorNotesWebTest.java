package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.ManobraBuilder;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoCentroCusto;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.sistam.test.builder.FiltroEnvioSolicitacaoTransporteBuilder;
import br.com.petrobras.sistam.test.builder.PontoAtracacaoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoHospedagemBuilder;
import br.com.petrobras.sistam.test.builder.ServicoManobraBuilder;
import br.com.petrobras.sistam.test.builder.ServicoProtecaoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoTransporteBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ValidadorNotesWebTest {

    private ValidadorNotesWeb validador = new ValidadorNotesWeb();
    
    @Test
    public void deveRetornarExcecaoQuandoEnviarSolicitacaoDeServicoJaSolicitado() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.setDataEnvio(new Date());
        try {
            validador.validarEnvioSolicitacaoServico(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SOLICITACAO_MANOBRA_SERVICO_JA_SOLICITADO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoEnviarSolicitacaoDeServicoComEmailDaAgenciaNulo() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.getManobra().getAgenciamento().getAgencia().setEmail(null);
        try {
            validador.validarEnvioSolicitacaoServico(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SOLICITACAO_MANOBRA_AGENCIA_COM_EMAIL_VAZIO_OU_NULO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoEnviarSolicitacaoDeServicoComEmailDaAgenciaEmBraco() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.getManobra().getAgenciamento().getAgencia().setEmail("");
        try {
            validador.validarEnvioSolicitacaoServico(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SOLICITACAO_MANOBRA_AGENCIA_COM_EMAIL_VAZIO_OU_NULO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoEnviarSolicitacaoDeServicoComEmailDaEmpresaMatitimaNulo() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.getEmpresaMaritima().setEmail(null);
        try {
            validador.validarEnvioSolicitacaoServico(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SOLICITACAO_MANOBRA_EMPRESA_COM_EMAIL_VAZIO_OU_NULO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoEnviarSolicitacaoDeServicoComEmailDaEmpresaMatitimaEmBranco() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.getEmpresaMaritima().setEmail("");
        try {
            validador.validarEnvioSolicitacaoServico(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SOLICITACAO_MANOBRA_EMPRESA_COM_EMAIL_VAZIO_OU_NULO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void deveRetornarExcecaoQuandoEnviarSolicitacaoDeServicoComEmailDaEmpresaMatitimaInvalido() {
        ServicoManobra servicoManobra = obterServicoManobraValido();
        servicoManobra.getEmpresaMaritima().setEmail("asfsfasfasf");
        try {
            validador.validarEnvioSolicitacaoServico(servicoManobra);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.MANOBRA);
            assertEquals(ConstantesI18N.SOLICITACAO_MANOBRA_EMPRESA_COM_EMAIL_INVALIDO, pendencias.get(0).getMessage());
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Envio E-mail Solicitação Transporte">    
    @Test
    public void deveRetornarExcecaoQuandoEnvioEmailSolicitacaoTransporteSemDestinatario() {
        FiltroEnvioSolicitacaoTransporte filtro = obterFiltroEnvioSolicitacaoTransporteValido();
        filtro.setAgenciaMaritima(false);
        filtro.setEmpresaTransporte(false);
        try {
            validador.validarEnvioEmailSolicitacaoTransporte(filtro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ENVIO_EMAIL);
            assertEquals(ConstantesI18N.ENVIO_EMAIL_ENVIO_PARA_INFORME_PELO_MENOS_UM, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Envio E-mail Solicitação Hospedagem">    
    @Test
    public void deveRetornarExcecaoQuandoEnvioEmailSolicitacaoHospedahemSemDestinatario() {
        FiltroEnvioSolicitacaoHospedagem filtro = obterFiltroEnvioSolicitacaoHospedagemValido();
        filtro.setAgenciaMaritima(false);
        filtro.setHotel(false);
        filtro.setAgenciaViagem(null);
        try {
            validador.validarEnvioEmailSolicitacaoHospedagem(filtro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ENVIO_EMAIL);
            assertEquals(ConstantesI18N.ENVIO_EMAIL_ENVIO_PARA_INFORME_PELO_MENOS_UM, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoEnvioEmailSolicitacaoHospedahemSemLotacao() {
        FiltroEnvioSolicitacaoHospedagem filtro = obterFiltroEnvioSolicitacaoHospedagemValido();
        filtro.setLotacao(null);
        try {
            validador.validarEnvioEmailSolicitacaoHospedagem(filtro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ENVIO_EMAIL);
            assertEquals(ConstantesI18N.ENVIO_EMAIL_LOTACAO_EMPRESA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void deveRetornarExcecaoQuandoEnvioEmailSolicitacaoHospedahemSemCentroCusto() {
        FiltroEnvioSolicitacaoHospedagem filtro = obterFiltroEnvioSolicitacaoHospedagemValido();
        filtro.setTipoCentroCusto(null);
        try {
            validador.validarEnvioEmailSolicitacaoHospedagem(filtro);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ENVIO_EMAIL);
            assertEquals(ConstantesI18N.ENVIO_EMAIL_CENTRO_CUSTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>  
    
    private ServicoManobra obterServicoManobraValido() {
        ServicoManobra servicoManobra = ServicoManobraBuilder.novoServicoManobra()
                .comId(1L)
                .comTipoServico(TipoServico.PRATICOS)
                .comDataProgramada(new Date())
                .daManobra(obterManobraValida())
                .daEmpresa(EmpresaMaritimaBuilder.novaEmpresaMaritima().comId(12L).comCnpj("1234").comEmail("teste@teste.com.br").build())
                .build();
        return servicoManobra;
    }
    
    
    private Manobra obterManobraValida() {
        
        Agencia agencia = AgenciaBuilder.novaAgencia().comEmail("teste@teste.com.br").build();
        
        Manobra manobra = ManobraBuilder.novaManobra()
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().comAgencia(agencia).build())
                .comResponsavelCusto(ResponsavelCustoBuilder.novoResponsavelCusto().build())
                .comStatus(StatusManobra.PLANEJADA)
                .comCaladoVante(12D)
                .comCaladoRe(12D)
                .comPontoAtracacaoOrigem(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comPontoAtracacaoDestino(PontoAtracacaoBuilder.novoPontoAtracacao().build())
                .comFinalidadeManobra(FinalidadeManobra.ATRACACAO)
                .build();
        return manobra;
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
        ServicoProtecao servicoProtecao = ServicoProtecaoBuilder.novoServicoProtecao().comId(1L).comTipoAtendimentoServico(TipoAtendimentoServico.HOSPEDAGEM).build();
        
         ServicoTransporte servicoTransporte = ServicoTransporteBuilder.novoServicoTransporte()
                .comServicoProtecao(servicoProtecao)
                .comEmpresa(ServicoBuilder.novoServico().comId(1L).comNome("TRANSPORTE 1").comTipoServico(TipoServico.TRANSPORTE).build())
                .comAutorizacao("AUTORIZACAO")
                .comVoo("VOO")
                .comOrigem("ORIGEM")
                .comDestino("DESTINO")
                .build();
        return servicoTransporte;
    }
    
    private FiltroEnvioSolicitacaoHospedagem obterFiltroEnvioSolicitacaoHospedagemValido() {

        FiltroEnvioSolicitacaoHospedagem filtro = new FiltroEnvioSolicitacaoHospedagem();
        filtro.setServicoHospedagem(obterServicoHospedagemValido());
        filtro.setTipoCentroCusto(TipoCentroCusto.CC1420);
        filtro.setAgenciaMaritima(false);
        filtro.setHotel(true);
        filtro.setAgenciaViagem(ServicoBuilder.novoServico().comId(1L).comNome("HOTEL TEST").comTipoServico(TipoServico.AGENCIA_VIAGEM).build());
        filtro.setLotacao("PETROBRAS");
        
        return filtro;
    }
    
    private FiltroEnvioSolicitacaoTransporte obterFiltroEnvioSolicitacaoTransporteValido() {

        FiltroEnvioSolicitacaoTransporte filtro = FiltroEnvioSolicitacaoTransporteBuilder.novoFiltro()
                .enviaParaAgenciaMaritima(false)
                .enviaParaEmpresaTransporte(true)
                .comTipoCentroCusto(ResponsavelCusto.PETROBRAS)
                .comServicoTransporte(obterServicoTransporteValido())
                
                .build();
        
        return filtro;
    }
    
}
