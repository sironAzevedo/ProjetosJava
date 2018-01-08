package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.PontoAtracacaoBuilder;
import br.com.petrobras.sistam.test.builder.PortoBuilder;
import br.com.petrobras.sistam.test.builder.PortoComplementoBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ValidadorPortoTest {
    private ValidadorPorto validador = new ValidadorPorto();

    //<editor-fold defaultstate="collapsed" desc="Salvar Ponto de Atracação">
    @Test
    public void naoSeraPermitidoSalvarOPontoDeAtracacaoComONomeNulo() {
        PontoAtracacao pontoAtracacao = obterPontoAtracacaoValido();
        pontoAtracacao.setNome(null);
        try {
            validador.validarSalvarPontoAtracacao(pontoAtracacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PONTO_ATRACACAO);
            assertEquals(ConstantesI18N.PONTO_ATRACACAO_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
   
    
    @Test
    public void naoSeraPermitidoSalvarOPontoDeAtracacaoComONomeVazio() {
        PontoAtracacao pontoAtracacao = obterPontoAtracacaoValido();
        pontoAtracacao.setNome("");
        try {
            validador.validarSalvarPontoAtracacao(pontoAtracacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PONTO_ATRACACAO);
            assertEquals(ConstantesI18N.PONTO_ATRACACAO_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

     @Test
    public void naoSeraPermitidoSalvarOPontoDeAtracacaoSemInformarOPontoOperacional() {
        PontoAtracacao pontoAtracacao = obterPontoAtracacaoValido();
        pontoAtracacao.setPontoOperacional(null);
        try {
            validador.validarSalvarPontoAtracacao(pontoAtracacao);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PONTO_ATRACACAO);
            assertEquals(ConstantesI18N.PONTO_ATRACACAO_PONTO_OPERACAIONAL_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold>
     
    @Test
    public void naoSeraPermitidoSalvarAgenciaPortoSemInformarAAgencia() {
        AgenciaPorto agenciaPorto = obterAgenciaPortoValida();
        agenciaPorto.setAgencia(null);
        try {
            validador.validarSalvarAgenciaPorto(agenciaPorto);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA_PORTO);
            assertEquals(ConstantesI18N.AGENCIA_PORTO_AGENCIA_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarAgenciaPortoSemInformarOPorto() {
        AgenciaPorto agenciaPorto = obterAgenciaPortoValida();
        agenciaPorto.setPorto(null);
        try {
            validador.validarSalvarAgenciaPorto(agenciaPorto);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA_PORTO);
            assertEquals(ConstantesI18N.AGENCIA_PORTO_PORTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarAgenciaPortoSemInformarOMunicipio() {
        AgenciaPorto agenciaPorto = obterAgenciaPortoValida();
        agenciaPorto.setMunicipio(null);
        try {
            validador.validarSalvarAgenciaPorto(agenciaPorto);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA_PORTO);
            assertEquals(ConstantesI18N.AGENCIA_PORTO_MUNICIPIO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarAgenciaPortoSemInformarOEstado() {
        AgenciaPorto agenciaPorto = obterAgenciaPortoValida();
        agenciaPorto.setMunicipio("BONINAL/");
        try {
            validador.validarSalvarAgenciaPorto(agenciaPorto);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA_PORTO);
            assertEquals(ConstantesI18N.AGENCIA_PORTO_ESTADO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
     
    //<editor-fold defaultstate="collapsed" desc="Salvar Complemento do Porto">
    @Test
    public void naoPodeSalvarPortoComplementoSemCnpj() {
        PortoComplemento complemento = obterPortoComplementoValido();
        complemento.setCnpjComMascara(null);
        try {
            validador.validarSalvarPortoComplemento(complemento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PORTO_COMPLEMENTO);
            assertEquals(ConstantesI18N.PORTO_COMPLEMENTO_CNPJ_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoPodeSalvarPortoComplementoSemInscricaoEstadual() {
        PortoComplemento complemento = obterPortoComplementoValido();
        complemento.setInscricaoEstadual(null);
        try {
            validador.validarSalvarPortoComplemento(complemento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PORTO_COMPLEMENTO);
            assertEquals(ConstantesI18N.PORTO_COMPLEMENTO_INSCRICAO_ESTADUAL_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoPodeSalvarPortoComplementoSemEndereco() {
        PortoComplemento complemento = obterPortoComplementoValido();
        complemento.setEndereco(null);
        try {
            validador.validarSalvarPortoComplemento(complemento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PORTO_COMPLEMENTO);
            assertEquals(ConstantesI18N.PORTO_COMPLEMENTO_ENDERECO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoPodeSalvarPortoComplementoSemCidade() {
        PortoComplemento complemento = obterPortoComplementoValido();
        complemento.setCidade(null);
        try {
            validador.validarSalvarPortoComplemento(complemento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PORTO_COMPLEMENTO);
            assertEquals(ConstantesI18N.PORTO_COMPLEMENTO_CIDADE_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoPodeSalvarPortoComplementoSemEstado() {
        PortoComplemento complemento = obterPortoComplementoValido();
        complemento.setEstado(null);
        try {
            validador.validarSalvarPortoComplemento(complemento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PORTO_COMPLEMENTO);
            assertEquals(ConstantesI18N.PORTO_COMPLEMENTO_ESTADO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoPodeSalvarPortoComplementoSemPorto() {
        PortoComplemento complemento = obterPortoComplementoValido();
        complemento.setPorto(null);
        try {
            validador.validarSalvarPortoComplemento(complemento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PORTO_COMPLEMENTO);
            assertEquals(ConstantesI18N.PORTO_COMPLEMENTO_PORTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoPodeSalvarPortoComplementoParaPortoNaoNacional() {
        PortoComplemento complemento = obterPortoComplementoValido();
        complemento.getPorto().setPais(new Pais());
        try {
            validador.validarSalvarPortoComplemento(complemento);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.PORTO_COMPLEMENTO);
            assertEquals(ConstantesI18N.PORTO_COMPLEMENTO_PORTO_DEVE_SER_NACIONAL, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">

    private PontoAtracacao obterPontoAtracacaoValido() {
        PontoAtracacao pontoAtracacao = PontoAtracacaoBuilder.novoPontoAtracacao()
                .comNome("SISTAM")
                .doPontoOperacional(new PontoOperacional())
                .build();
        return pontoAtracacao;
    }
    
    private AgenciaPorto obterAgenciaPortoValida(){
        AgenciaPorto agenciaPorto = new AgenciaPorto();
        agenciaPorto.setAgencia(new Agencia());
        agenciaPorto.setPorto(new Porto());
        agenciaPorto.setMunicipio("BONINAL/BA");
        
        return agenciaPorto;
    }
    
    private PortoComplemento obterPortoComplementoValido(){
        return PortoComplementoBuilder.novoPortoComplemento()
                .comNomeCtac("CTAC")
                .comCnpj("00.000.000/0001.91")
                .comInscricaoEstadual("123123")
                .comEndereco("Rua M, 123")
                .comCidade("Salvador")
                .comEstado("UF")
                .comPorto(PortoBuilder.novoPorto().comId("SALV").comPais(Pais.CODIGO_BRASIL).build())
                .build();
    }
    //</editor-fold>
}
