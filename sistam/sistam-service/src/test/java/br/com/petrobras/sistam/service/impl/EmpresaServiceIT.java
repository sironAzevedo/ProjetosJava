package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.CommonService;
import br.com.petrobras.sistam.common.business.EmpresaService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresa;
import br.com.petrobras.sistam.test.builder.AgenciaBuilder;
import br.com.petrobras.sistam.test.builder.EmpresaMaritimaBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/EmpresaService.xml")
public class EmpresaServiceIT extends BaseIntegrationTestClass {

    @SpringBean("EmpresaServiceImpl")
    private EmpresaService empresaService;
    
    @SpringBean("CommonServiceImpl")
    private CommonService commonService;
    
    
    @Test
    public void deveSalvarAEmpresa() {
        EmpresaMaritima empresa = obterEmpresaValida();
        empresa = empresaService.salvarEmpresa(empresa);
        
        assertNotNull(commonService.buscarEntidadePorId(EmpresaMaritima.class, empresa.getId()));
    }

    @Test
    public void deveExcluirEmpersa() {
        EmpresaMaritima empresa = (EmpresaMaritima) commonService.buscarEntidadePorId(EmpresaMaritima.class, 1L);
        empresa.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).build());
        empresaService.excluirEmpresa(empresa);
        empresa = (EmpresaMaritima) commonService.buscarEntidadePorId(EmpresaMaritima.class, 1L);
        assertNull(empresa);
    }
    
    
    @Test
    public void deveSalvarOServico() {
        Servico servico = obterServicoValido();
        servico.setEmpresa((EmpresaMaritima)commonService.buscarEntidadePorId(EmpresaMaritima.class, 1L));
        
        servico = empresaService.salvarServicoDaEmpresa(servico);
        
        assertNotNull(commonService.buscarEntidadePorId(Servico.class, servico.getId()));
    }
    
    @Test
    public void deveExcluirOServico() {
        Servico servico = (Servico)commonService.buscarEntidadePorId(Servico.class, 1L);
        servico.setEmpresa(EmpresaMaritimaBuilder.novaEmpresaMaritima(obterEmpresaValida()).comId(1L).build());
        servico = empresaService.excluirServicoDaEmpresa(servico);
        assertNull(commonService.buscarEntidadePorId(Servico.class, servico.getId()));
    }

    @Test
    public void deveRetornarAsEmpresasBuscadaPorFiltro(){
        FiltroEmpresa filtro = new FiltroEmpresa();
        filtro.setAgencia(AgenciaBuilder.novaAgencia().comId(1L).build());
        filtro.setCnpj("333333");
        filtro.setEmpresa("SISTAM RIO");
        filtro.setAtivo(true);
        
        List<EmpresaMaritima> listaRetornada = empresaService.buscarEmpresasPorFiltro(filtro);
        
        List<Long> listaIdEsperada = Arrays.asList(new Long[]{1L});
        assertListaDeEmpresa(listaRetornada, listaIdEsperada);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private EmpresaMaritima obterEmpresaValida() {
        Agencia agencia = AgenciaBuilder.novaAgencia().comId(1L).build();
        
        EmpresaMaritima empresaMaritima = EmpresaMaritimaBuilder.novaEmpresaMaritima()
                .comAgencia(agencia)
                .comCnpj("13256")
                .comEmail("sistam@sistam")
                .comRazaoSocial("SISTAM")
                .build();
        return empresaMaritima;
    }
    
    private Servico obterServicoValido() {
        Servico servico = new Servico();
        servico.setEmpresa(obterEmpresaValida());
        servico.setNomeServico("SISTAM");
        servico.setTipoServico(TipoServico.PRATICOS);
        
        return servico;
    }
    
    
    private void assertListaDeEmpresa(List<EmpresaMaritima> listaRetornada, List<Long> listaIdEsperado){
        assertTrue(listaRetornada.size() == listaIdEsperado.size());
        
        List<Long> listaIdRetornado = new ArrayList<Long>();
        for (EmpresaMaritima empresa : listaRetornada){
            listaIdRetornado.add(empresa.getId());
        }
        
        for (Long idEsperado : listaIdEsperado){
            assertTrue(listaIdRetornado.contains(idEsperado));
        }
    }
    
    //</editor-fold>
   
}
