package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaPessoaProtecaoComCPFExistente;
import br.com.petrobras.sistam.service.validator.ValidadorPessoaProtecao;
import br.com.petrobras.sistam.test.builder.PessoaProtecaoBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;

public class PessoaProtecaoServiceTest {

    private PessoaProtecaoServiceImpl pessoaService;
    private ValidadorPessoaProtecao validador;
    private GenericDao dao;

    @Before
    public void setUp() {
        validador = mock(ValidadorPessoaProtecao.class);
        dao = mock(GenericDao.class);
        pessoaService = new PessoaProtecaoServiceImpl(dao);

        carregarValidadorComMock();
    }

    //<editor-fold defaultstate="collapsed" desc="Salvar Pessoa">
    @Test
    public void deveChamarOValidadorQuandoSalvarUmaPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoaService.salvarPessoaProtecao(pessoa);
        Mockito.verify(validador).validarSalvar(pessoa);
    }

    @Test
    public void deveChamarOSaveOrUpdateQuandoSalvarUmaPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoaService.salvarPessoaProtecao(pessoa);
        Mockito.verify(dao).saveOrUpdate(pessoa);
    }

    @Test
    public void deveChamarOValidadorDeCnpjExistenteQuandoSalvarUmaPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoaService.salvarPessoaProtecao(pessoa);
        Mockito.when(dao.list(new ConsultaPessoaProtecaoComCPFExistente(pessoa))).thenReturn(Collections.EMPTY_LIST);
    }

    @Test
    public void deveChamarOSaveOrUpdateSeNaoExisteCnpjExistenteQuandoSalvarUmaPessoa() {
        Pessoa pessoa = obterPessoaValida();
        pessoaService.salvarPessoaProtecao(pessoa);
        Mockito.when(dao.list(new ConsultaPessoaProtecaoComCPFExistente(pessoa))).thenReturn(Arrays.asList(new Pessoa[]{new Pessoa()}));
        Mockito.verify(dao).saveOrUpdate(pessoa);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(PessoaProtecaoServiceImpl.class, "validador", false);
        ReflectionUtils.setFieldValue(pessoaService, field, validador);
    }

    private Pessoa obterPessoaValida() {
        Pessoa pessoa = PessoaProtecaoBuilder.novaPessoaProtecao().build();
        return pessoa;
    }
    //</editor-fold>
}