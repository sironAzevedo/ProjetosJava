package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.EmpresaProtecaoServico;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.EmpresaProtecaoBuilder;
import java.util.HashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ValidadorEmpresaProtecaoTest {

    private ValidadorEmpresaProtecao validador = new ValidadorEmpresaProtecao();

    //<editor-fold defaultstate="collapsed" desc="Salvar Empresa">
    @Test
    public void deveValidarCnpjComoObrigatorioQuandoNuloNoSalvarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.setCnpj(null);
        try {
            validador.validarSalvar(empresa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.EMPRESA_PROTECAO_CNPJ_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }

    @Test
    public void deveValidarCnpjComoObrigatorioQuandoVazioNoSalvarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.setCnpj("");
        try {
            validador.validarSalvar(empresa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.EMPRESA_PROTECAO_CNPJ_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }

    @Test
    public void deveValidarNomeComoObrigatorioQuandoNuloNoSalvarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.setRazaoSocial(null);
        try {
            validador.validarSalvar(empresa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.EMPRESA_PROTECAO_NOME_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }

    @Test
    public void deveValidarNomeComoObrigatorioQuandoVazioNoSalvarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.setRazaoSocial("");
        try {
            validador.validarSalvar(empresa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.EMPRESA_PROTECAO_NOME_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }

    @Test
    public void deveValidarEmailComoInvalidoQuandoSalvarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.setEmail("email@invalido");
        try {
            validador.validarSalvar(empresa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.EMPRESA_PROTECAO_COM_EMAIL_INVALIDO, getMensagemPendencia(ex));
        }
    }

    @Test
    public void deveValidarTipoServicoComoObrigatorioQuandoSalvarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.setServicos(new HashSet<EmpresaProtecaoServico>());
        try {
            validador.validarSalvar(empresa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.EMPRESA_PROTECAO_TIPO_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }

    @Test
    public void deveValidarComSucessoQuandoSalvarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        validador.validarSalvar(empresa);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private String getMensagemPendencia(SistamPendingException exception) {
        return exception.getPendingMap().get(TipoExcecao.EMPRESA_PROTECAO).get(0).getMessage();
    }

    private EmpresaProtecao obterEmpresaValida() {
        EmpresaProtecao empresaProtecao = EmpresaProtecaoBuilder.novaEmpresaProtecao()
                .comId(1L)
                .comCnpj("00000000000123")
                .comRazaoSocial("Sistam S.A.")
                .comEmail("sistam@sistam.com")
                .comTelefone("7188997766")
                .comCidade("Salvador")
                .comEstado("Bahia")
                .comServicos(TipoServicoProtecao.TRANSPORTE, TipoServicoProtecao.HOSPEDAGEM)
                .build();
        return empresaProtecao;
    }
    //</editor-fold>
}
