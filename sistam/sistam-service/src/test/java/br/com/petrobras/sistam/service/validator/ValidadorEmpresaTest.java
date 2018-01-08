package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresa;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ValidadorEmpresaTest {

    private ValidadorEmpresa validador = new ValidadorEmpresa();

    //<editor-fold defaultstate="collapsed" desc="Empresa">
    @Test
    public void naoSeraPermitidoBuscarAsEmpresaPorFiltroSemInformarAAgencia() {
        FiltroEmpresa filtro = new FiltroEmpresa();
        try {
            validador.validarBuscarEmpresasPorFiltro(filtro);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.EMPRESA_MARITIMA_AGENCIA_OBRIGATORIA, ex.getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarUmaEmpresaSemInformarAAgencia() {
        EmpresaMaritima empresa = obterEmpresaValido();
        empresa.setAgencia(null);
        try {
            validador.validarSalvarEmpresa(empresa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMPRESA);
            assertEquals(ConstantesI18N.EMPRESA_MARITIMA_AGENCIA_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarUmaEmpresaSemInformarAEmpresa() {
        EmpresaMaritima empresa = obterEmpresaValido();
        empresa.setRazaoSocial(null);
        try {
            validador.validarSalvarEmpresa(empresa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMPRESA);
            assertEquals(ConstantesI18N.EMPRESA_MARITIMA_EMPRESA_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarUmaEmpresaSemInformarOCnpj() {
        EmpresaMaritima empresa = obterEmpresaValido();
        empresa.setCnpj(null);
        try {
            validador.validarSalvarEmpresa(empresa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMPRESA);
            assertEquals(ConstantesI18N.EMPRESA_MARITIMA_CNPJ_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarUmaEmpresaSemInformarOEmail() {
        EmpresaMaritima empresa = obterEmpresaValido();
        empresa.setEmail(null);
        try {
            validador.validarSalvarEmpresa(empresa);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMPRESA);
            assertEquals(ConstantesI18N.EMPRESA_MARITIMA_EMAIL_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Serviço da empresa">
    @Test
    public void naoSeraPermitidoSalvarUmServicoSemInformarAEmpresa() {
        Servico servico = obterServicoValido();
        servico.setEmpresa(null);
        try {
            validador.validarSalvarServico(servico);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMPRESA);
            assertEquals(ConstantesI18N.SERVICO_EMPRESA_MARITIMA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarUmServicoSemInformarOTipo() {
        Servico servico = obterServicoValido();
        servico.setTipoServico(null);
        try {
            validador.validarSalvarServico(servico);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMPRESA);
            assertEquals(ConstantesI18N.SERVICO_EMPRESA_MARITIMA_TIPO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarUmServicoSemInformarONome() {
        Servico servico = obterServicoValido();
        servico.setNomeServico(null);
        try {
            validador.validarSalvarServico(servico);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMPRESA);
            assertEquals(ConstantesI18N.SERVICO_EMPRESA_MARITIMA_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private EmpresaMaritima obterEmpresaValido() {
        EmpresaMaritima empresaMaritima = EmpresaMaritimaBuilder.novaEmpresaMaritima()
                .comId(1L)
                .comAgencia(AgenciaBuilder.novaAgencia().build())
                .comCnpj("01236547")
                .comEmail("sistam@sistam.com")
                .comRazaoSocial("SISTAM")
                .build();
        return empresaMaritima;
    }

    private Servico obterServicoValido() {
        Servico servico = new Servico();
        servico.setEmpresa(new EmpresaMaritima());
        servico.setNomeServico("SISTAM");
        servico.setTipoServico(TipoServico.PRATICOS);

        return servico;
    }
    //</editor-fold>
}
