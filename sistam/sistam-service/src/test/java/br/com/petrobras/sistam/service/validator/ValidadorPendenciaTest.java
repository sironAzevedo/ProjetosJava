package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.snarf.common.exception.Pendency;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Test;


public class ValidadorPendenciaTest {
    private ValidadorPendencia validador = new ValidadorPendencia();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Pendência">
    @Test
    public void aoSalvarUmaPendenciaOAgenciamentoDeveSerInformado(){
        try{
            validador.validarCriarPendencia(null, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
            fail("Uma pendencia não pode ser criada para o agenciamento nulo");
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS);
            assertEquals(ConstantesI18N.PENDENCIA_AGENCIAMENTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void aoSalvarUmaPendenciaOTipoDeveSerInformado(){
        try{
            validador.validarCriarPendencia(new Agenciamento(), null);
            fail("Uma pendencia não pode ser criada com o tipo de pendencia nula.");
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS);
            assertEquals(ConstantesI18N.PENDENCIA_TIPO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Resolver Pendência">
    @Test
    public void naoSeraPossivelSolicitarAResolucaoDePendenciaManualJaResolvida(){
        Pendencia pendencia = new Pendencia();
        pendencia.setResolvida(true);
        try{
            validador.validarResolucaoDaPendenciaManual(pendencia);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.PENDENCIA_JA_RESOLVIDA, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSolicitarAResolucaoDePendenciaDoDocumentoSemInformarODocumento(){
        try{
            validador.validarResolucaoDaPendenciaDoDocumento(null);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.PENDENCIA_DOCUMENTO_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSolicitarAResolucaoDePendenciaDaTaxaSemInformarATaxa(){
        try{
            validador.validarResolucaoDaPendenciaDaTaxa(null);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.PENDENCIA_TAXA_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSolicitarRemocaoDaResolucaoDePendenciaDaTaxaSemInformarATaxa(){
        try{
            validador.validarRemocaoDaResolucaoDaPendenciaDaTaxa(null);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.PENDENCIA_TAXA_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSolicitarRemocaoDaResolucaoDePendenciaSemInformarAPendenciaAgenciamento(){
        try{
            validador.validarRemocaoDaResolucaoDaPendencia(null);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.PENDENCIA_AGENCIAMENTO_OBRIGATORIO, ex.getMessage());
        }
    }
    
    //</editor-fold>
    
    @Test
    public void naoSeraPossivelExcluirUmaPendenciaResolvida(){
        Pendencia pendencia = new Pendencia();
        pendencia.setDataSolucao(DateBuilder.on(1, 1, 2014).getTime());
        
        try{
            validador.validarExclusaoDaPendencia(pendencia);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.PENDENCIA_JA_RESOLVIDA, ex.getMessage());
        }
    }
    
}
