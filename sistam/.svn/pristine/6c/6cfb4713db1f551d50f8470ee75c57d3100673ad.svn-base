package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.CertificadoBuilder;
import br.com.petrobras.sistam.test.builder.EmbarcacaoBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Test;

public class ValidadorCertificadoTest {

    private ValidadorCertificado validador = new ValidadorCertificado();
    
    @Test
    public void naoSeraPermitidoSalvarCertificadoSemData(){
        Certificado certificado = obterCertificadoValido();
        certificado.setData(null);
        try{
            validador.validarSalvarCertificado(certificado);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.CERTIFICADO);
            assertEquals(ConstantesI18N.CERTIFICADO_SEM_DATA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarCertificadoSemTipo(){
        Certificado certificado = obterCertificadoValido();
        certificado.setTipo(null);
        try{
            validador.validarSalvarCertificado(certificado);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.CERTIFICADO);
            assertEquals(ConstantesI18N.CERTIFICADO_SEM_TIPO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarCertificadoSemEmbarcacao(){
        Certificado certificado = obterCertificadoValido();
        certificado.setEmbarcacao(null);
        try{
            validador.validarSalvarCertificado(certificado);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.CERTIFICADO);
            assertEquals(ConstantesI18N.CERTIFICADO_SEM_EMBARCACAO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarCertificadoLivrePraticaSemAgenciamento(){
        Certificado certificado = obterCertificadoValido();
        certificado.setTipo(TipoCertificado.CLP);
        certificado.setAgenciamento(null);
        try{
            validador.validarSalvarCertificado(certificado);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.CERTIFICADO);
            assertEquals(ConstantesI18N.CERTIFICADO_LIVRE_PRATICA_SEM_AGENCIAMENTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarCertificadoLivrePraticaSemNumeroCertificado(){
        Certificado certificado = obterCertificadoValido();
        certificado.setNumeroCertificado(null);
        try{
            validador.validarSalvarCertificadoLivrePratica(certificado);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.CERTIFICADO);
            assertEquals(ConstantesI18N.INFORME_NUMERO_CERTIFICADO, pendencias.get(0).getMessage());
        }
    }
    
    private Certificado obterCertificadoValido() {
        Embarcacao embarcacao = EmbarcacaoBuilder.novaEmbarcacao()
                .comId("1")
                .comApelido("TESTE")
                .comNome("TESTE")
                .build();
            
        
         Certificado certificado = CertificadoBuilder.novoCertificado()
                 .comId(1L)
                 .comData(new Date())
                 .doTipo(TipoCertificado.CLP)
                 .daEmbarcacao(embarcacao)
                 .build();
        return certificado;
    }
    
    
    

}
