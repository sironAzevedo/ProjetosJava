package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.AgenciamentoService;
import br.com.petrobras.sistam.common.business.CertificadoService;
import br.com.petrobras.sistam.common.business.EmbarcacaoService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import br.com.petrobras.sistam.test.builder.CertificadoBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/CertificadoService.xml")
public class CertificadoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("CertificadoServiceImpl")
    private CertificadoService certificadoSerivice;
    
    @SpringBean("AgenciamentoServiceImpl")
    private AgenciamentoService agenciamentoSerivice;

    @SpringBean("EmbarcacaoServiceImpl")
    private EmbarcacaoService embarcacaoSerivice;
    
    
    @Test
    public void deveSalvarCertificado() {
        Certificado certificado = obterCertificadoValido();
        certificado = certificadoSerivice.salvarCertificado(certificado);
        Assert.assertNotNull(certificado.getId());
    }
    
    @Test
    public void deveSalvarCertificadoLivrePratica() {
        Certificado certificado = obterCertificadoValido();
        certificado.setNumeroCertificado(1L);
        certificado = certificadoSerivice.salvarCertificadoLivrePratica(certificado);
        Assert.assertNotNull(certificado.getId());
    }

    @Test
    public void deveSalvarListaDeCertificados() {
        Certificado certificado1 = obterCertificadoValido();
        Certificado certificado2 = obterCertificadoValido();
        List<Certificado> certificados = new ArrayList<Certificado>(Arrays.asList(certificado1, certificado2));
        certificados = certificadoSerivice.salvarCertificados(certificados);
        for(Certificado certificado : certificados) {
            Assert.assertNotNull(certificado.getId());
        }    
    }
    
    
    @Test
    public void deveRetornarOCertificadoLivrePraticaValido() {
        Agenciamento agenciamento = agenciamentoSerivice.buscarAgenciamentoPorId(855L);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2013, 10, 15);
        
        Certificado certificado = certificadoSerivice.buscarCertificadoValidoPorTipo(TipoCertificado.CLP, agenciamento, null, calendar.getTime());
        Assert.assertNotNull(certificado);
        Assert.assertTrue(certificado.getId().equals(1L));
    }
    
    private Certificado obterCertificadoValido() {
        Embarcacao embarcacao = embarcacaoSerivice.buscarEmbarcacaoPorId("0098");
        
         Certificado certificado = CertificadoBuilder.novoCertificado()
                 .comData(new Date())
                 .doTipo(TipoCertificado.CCSB_CICSB)
                 .daEmbarcacao(embarcacao)
                 .build();
        return certificado;
    }
    
   
}
