package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.EmpresaProtecaoServico;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.service.query.servicoprotecao.ConsultaEmpresaProtecaoComCnpjExistente;
import br.com.petrobras.sistam.service.validator.ValidadorEmpresaProtecao;
import br.com.petrobras.sistam.test.builder.EmpresaProtecaoBuilder;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.unitils.util.ReflectionUtils;

public class EmpresaProtecaoServiceTest {

    private EmpresaProtecaoServiceImpl empresaService;
    private ValidadorEmpresaProtecao validador;
    private GenericDao dao;

    @Before
    public void setUp() {
        validador = mock(ValidadorEmpresaProtecao.class);
        dao = mock(GenericDao.class);
        empresaService = new EmpresaProtecaoServiceImpl(dao);

        carregarValidadorComMock();
    }

    //<editor-fold defaultstate="collapsed" desc="Salvar Empresa">
    @Test
    public void deveChamarOValidadorQuandoSalvarUmaEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresaService.salvarEmpresaProtecao(empresa);
        Mockito.verify(validador).validarSalvar(empresa);
    }
    
    @Test
    public void deveChamarOValidadorDeCnpjExistenteQuandoSalvarUmaEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresaService.salvarEmpresaProtecao(empresa);
        Mockito.when(dao.list(new ConsultaEmpresaProtecaoComCnpjExistente(empresa))).thenReturn(Collections.EMPTY_LIST);
    }
    
    @Test
    public void deveChamarOSaveOrUpdateSeNaoExisteCnpjExistenteQuandoSalvarUmaEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresaService.salvarEmpresaProtecao(empresa);
        Mockito.when(dao.list(new ConsultaEmpresaProtecaoComCnpjExistente(empresa))).thenReturn(Arrays.asList(new EmpresaProtecao[] {new EmpresaProtecao()}));
        Mockito.verify(dao).saveOrUpdate(empresa);
    }
    
    @Test
    public void deveChamarORemoveDoServicoQuandoAtualizarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValidaComServicos();
        EmpresaProtecaoServico servico = empresa.getServicos().iterator().next();
        servico.setId(12L);
        empresaService.salvarEmpresaProtecao(empresa);
        Mockito.when(dao.list(new ConsultaEmpresaProtecaoComCnpjExistente(empresa))).thenReturn(Collections.EMPTY_LIST);
        Mockito.verify(dao).saveOrUpdate(empresa);
        servico = Mockito.verify(dao).get(EmpresaProtecaoServico.class, servico.getId());
        Mockito.verify(dao).remove(servico);
    }
    
    @Test
    public void deveChamarOSaveOrUpdateDoServicoQuandoCriarEmpresa() {
        EmpresaProtecao empresa = obterEmpresaValidaComServicos();
        EmpresaProtecaoServico servico = empresa.getServicos().iterator().next();
        empresaService.salvarEmpresaProtecao(empresa);
        Mockito.when(dao.list(new ConsultaEmpresaProtecaoComCnpjExistente(empresa))).thenReturn(Collections.EMPTY_LIST);
        Mockito.verify(dao).saveOrUpdate(empresa);
        Mockito.verify(dao).saveOrUpdate(servico);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private void carregarValidadorComMock() {
        Field field = ReflectionUtils.getFieldWithName(EmpresaProtecaoServiceImpl.class, "validador", false);
        ReflectionUtils.setFieldValue(empresaService, field, validador);
    }

    private EmpresaProtecao obterEmpresaValida() {
        EmpresaProtecao empresa = EmpresaProtecaoBuilder.novaEmpresaProtecao().build();
        return empresa;
    }

    private EmpresaProtecao obterEmpresaValidaComServicos() {
        EmpresaProtecao empresa = obterEmpresaValida();
        empresa.adicionarTipoServico(TipoServicoProtecao.HOSPEDAGEM);
        return empresa;
    }
    //</editor-fold>
}
