package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.AgenciaContatoBuilder;
import br.com.petrobras.sistam.test.builder.AgenciaOrgaoDespachoBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ValidadorAgenciaTest {

    private ValidadorAgencia validador = new ValidadorAgencia();

    //<editor-fold defaultstate="collapsed" desc="Salvar agencia">
    @Test
    public void naoSeraPermitidoSalvarAgenciaSemSigla() {
        Agencia agencia = obterAgenciaValida();
        agencia.setSigla(null);
        try {
            validador.validarSalvarAgencia(agencia);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_SIGLA_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarAgenciaSemNome() {
        Agencia agencia = obterAgenciaValida();
        agencia.setNome(null);
        try {
            validador.validarSalvarAgencia(agencia);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarAgenciaSemEndereco() {
        Agencia agencia = obterAgenciaValida();
        agencia.setEndereco(null);
        try {
            validador.validarSalvarAgencia(agencia);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_ENDERECO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarAgenciaSemTelefone() {
        Agencia agencia = obterAgenciaValida();
        agencia.setTelefone(null);
        try {
            validador.validarSalvarAgencia(agencia);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_TELEFONE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarAgenciaSemEmail() {
        Agencia agencia = obterAgenciaValida();
        agencia.setEmail(null);
        try {
            validador.validarSalvarAgencia(agencia);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_EMAIL_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarAgenciaSemCidade() {
        Agencia agencia = obterAgenciaValida();
        agencia.setCidade(null);
        try {
            validador.validarSalvarAgencia(agencia);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_CIDADE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarAgenciaSemEstado() {
        Agencia agencia = obterAgenciaValida();
        agencia.setEstado(null);
        try {
            validador.validarSalvarAgencia(agencia);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_ESTADO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Salvar AgenciaContato">
    @Test
    public void naoSeraPermitidoSalvarAgenciaContatoSemAgencia() {
        RepresentanteLegal agenciaContato = obterAgenciaContatoValido();
        agenciaContato.setAgencia(null);
        try {
            validador.validarSalvarAgenciaContato(agenciaContato);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.AGENCIA_CONTATO_AGENCIA_NAO_PODE_SER_NULA, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarAgenciaContatoSemNome() {
        RepresentanteLegal agenciaContato = obterAgenciaContatoValido();
        agenciaContato.setNome(null);
        try {
            validador.validarSalvarAgenciaContato(agenciaContato);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_CONTATO_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarAgenciaContatoSemCpf() {
        RepresentanteLegal agenciaContato = obterAgenciaContatoValido();
        agenciaContato.setCpf(null);
        try {
            validador.validarSalvarAgenciaContato(agenciaContato);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_CONTATO_CPF_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Salvar AgenciaOrgaoDespacho">
    @Test
    public void naoSeraPermitidoSalvarAgenciaOrgaoDespachoSemAgencia() {
        AgenciaOrgaoDespacho agenciaOrgaoDespacho = obterAgenciaOrgaoDespachoo();
        agenciaOrgaoDespacho.setAgencia(null);
        try {
            validador.validarSalvarAgenciaOrgaoDespacho(agenciaOrgaoDespacho);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.AGENCIA_ORGAO_DESPACHO_AGENCIA_NAO_PODE_SER_NULA, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarAgenciaOrgaoDespachoSemNome() {
        AgenciaOrgaoDespacho agenciaOrgaoDespacho = obterAgenciaOrgaoDespachoo();
        agenciaOrgaoDespacho.setNome(null);
        try {
            validador.validarSalvarAgenciaOrgaoDespacho(agenciaOrgaoDespacho);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIA);
            assertEquals(ConstantesI18N.AGENCIA_ORGAO_DESPACHO_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>
    
    @Test
    public void naoSeraPermitidoSalvarDestinatarioSemInformarAAgencia() {
        Destinatario destinatario = obterDestinatarioValido();
        destinatario.setAgencia(null);
        try {
            validador.validarSalvarDestinatario(destinatario);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DESTINATARIO_AGENCIA);
            assertEquals(ConstantesI18N.DESTINATARIO_AGENCIA_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarDestinatarioSemInformarONome() {
        Destinatario destinatario = obterDestinatarioValido();
        destinatario.setNome(null);
        try {
            validador.validarSalvarDestinatario(destinatario);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DESTINATARIO_AGENCIA);
            assertEquals(ConstantesI18N.DESTINATARIO_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarDestinatarioSemInformarOEMail() {
        Destinatario destinatario = obterDestinatarioValido();
        destinatario.setEmail(null);
        try {
            validador.validarSalvarDestinatario(destinatario);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.DESTINATARIO_AGENCIA);
            assertEquals(ConstantesI18N.DESTINATARIO_EMAIL_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    
    private Agencia obterAgenciaValida() {
        Agencia agencia = AgenciaBuilder.novaAgencia()
                .comId(1L)
                .comSigla("TESTE")
                .comNome("TESTE")
                .comEndereco("Teste")
                .comTelefone("99999999")
                .comEmail("teste@teste.com.br")
                .comCidade("Teste")
                .comEstado("Teste")
                .build();
        return agencia;
    }
    
    private RepresentanteLegal obterAgenciaContatoValido() {
        RepresentanteLegal agenciaContato = AgenciaContatoBuilder.novaAgenciaContato()
                .comId(1L)
                .daAgencia(obterAgenciaValida())
                .comNome("TESTE")
                .comCpf("9898898L")
                .build();
        return agenciaContato;
    }

    
    private AgenciaOrgaoDespacho obterAgenciaOrgaoDespachoo() {
        AgenciaOrgaoDespacho agenciaOrgaoDespacho = AgenciaOrgaoDespachoBuilder.novaAgenciaOrgaoDespacho()
                .comId(1L)
                .daAgencia(obterAgenciaValida())
                .comNome("TESTE")
                .build();
        return agenciaOrgaoDespacho;
    }
    
    private Destinatario obterDestinatarioValido(){
        Destinatario destinatario = new Destinatario();
        destinatario.setAgencia(new Agencia());
        destinatario.setEmail("PETRO@PETRO.COM.BR");
        destinatario.setNome("PETROBRAS");
        
        return destinatario;
    }
    
    
    
}
