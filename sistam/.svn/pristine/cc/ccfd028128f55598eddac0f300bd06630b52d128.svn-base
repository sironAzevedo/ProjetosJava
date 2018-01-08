package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.test.builder.PessoaProtecaoBuilder;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

public class ValidadorPessoaProtecaoTest {

    private ValidadorPessoaProtecao validador = new ValidadorPessoaProtecao();

    //<editor-fold defaultstate="collapsed" desc="Salvar Pessoa">
    @Test
    public void deveValidarRgComoObrigatorioQuandoNuloNoSalvarPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoa.setDocumento(null);
        pessoa.setCpf(null);
        try {
            validador.validarSalvar(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.PESSOA_PROTECAO_DOCUMENTO_OU_CPF_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }

    @Test
    public void deveValidarRgComoObrigatorioQuandoVazioNoSalvarPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoa.setDocumento("");
        pessoa.setCpf("");
        try {
            validador.validarSalvar(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.PESSOA_PROTECAO_DOCUMENTO_OU_CPF_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }

    @Test
    public void naoDeveValidarOCpfComoObrigatorioQuandoODocumentoForPreenchido() {
        Pessoa pessoa = obterPessoaValida();
        pessoa.setCpf("");
        validador.validarSalvar(pessoa);
    }

    @Test
    public void naoDeveValidarODocumentoComoObrigatorioQuandoOCpfForPreenchido() {
        Pessoa pessoa = obterPessoaValida();
        pessoa.setDocumento("");
        validador.validarSalvar(pessoa);
    }

    @Test
    public void deveValidarDataNascimentoQuandoMaiorQueDataAtualNoSalvarPessoa() {
        Pessoa pessoa = obterPessoaValida();
        Date dataAtual = new Date();
        pessoa.setDataNascimento(SistamDateUtils.somarDias(dataAtual, 1, null));
        try {
            validador.validarSalvar(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.PESSOA_PROTECAO_DATANASCIMENTO_MENORIGUAL_DATAATUAL, getMensagemPendencia(ex));
        }
    }

    @Test
    public void naoDeveValidarDataNascimentoQuandoIgualADataAtualNoSalvarPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoa.setDataNascimento(new Date());
        validador.validarSalvar(pessoa);
    }

    @Test
    public void deveValidarNomeComoObrigatorioQuandoNuloNoSalvarPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoa.setNome(null);
        try {
            validador.validarSalvar(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.PESSOA_PROTECAO_NOME_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }

    @Test
    public void deveValidarNomeComoObrigatorioQuandoVazioNoSalvarPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoa.setNome("");
        try {
            validador.validarSalvar(pessoa);
            fail();
        } catch (SistamPendingException ex) {
            assertEquals(ConstantesI18N.PESSOA_PROTECAO_NOME_OBRIGATORIA, getMensagemPendencia(ex));
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private String getMensagemPendencia(SistamPendingException exception) {
        return exception.getPendingMap().get(TipoExcecao.PESSOA).get(0).getMessage();
    }

    private Pessoa obterPessoaValida() {
        Pessoa pessoa = PessoaProtecaoBuilder.novaPessoaProtecao()
                .comId(1L)
                .comDocumento("0823221230")
                .comNome("John")
                .comCpf("00122344588")
                .comDataNascimento(SistamDateUtils.informarData(07, 01, 1980, null))
                .build();
        return pessoa;
    }
    //</editor-fold>
}
