package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.lang.reflect.Field;
import static junit.framework.Assert.*;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

/**
 *
 */
public class DocumentacaoLongoCursoTest {
    private DocumentacaoLongoCurso documentacao = new DocumentacaoLongoCurso();
    
    //<editor-fold defaultstate="collapsed" desc="Adicionar CE">
    @Test
    public void seAListaDeCEForNulaDeveFicarIgualAoCEAdicionado(){
        String novoCE = "ABCD";
        documentacao.adicionarCE(novoCE);
        
        assertEquals(novoCE, documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void seAListaDeCEForVaziaDeveFicarIgualAoCEAdicionado(){
        atualizarCampoListaConhecimentoEletronico("");
        
        String novoCE = "ABCD";
        documentacao.adicionarCE(novoCE);
        
        assertEquals(novoCE, documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void seAdicionarUmCENuloAListaNaoDeveSerAlterada(){
        String CEAtual = "ABCD";
        atualizarCampoListaConhecimentoEletronico(CEAtual);
        
        documentacao.adicionarCE(null);
        
        assertEquals(CEAtual, documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void seAListaDeCENaoForVaziaDeveAdicionarOCEInformadoNoFinalDaListaSeparadoPorPontoEVirgula(){
        String CEAtual = "ABCD";
        atualizarCampoListaConhecimentoEletronico(CEAtual);
        
        String novoCE = "12345";
        documentacao.adicionarCE(novoCE);
        
        String CEEsperado = CEAtual + ";" + novoCE;
        assertEquals(CEEsperado, documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void naoSeraPossivelAdicionarUmCESeAlistaDeCENaoTiverEspacoDisponivel(){
        //cria uma string com o tamanho máximo da lista lita de ce;
        String listaCe = "";
        while (listaCe.length() < DocumentacaoLongoCurso.TAM_MAX_LISTA_CE){
            listaCe += "A";
        }
        
        atualizarCampoListaConhecimentoEletronico(listaCe);
        
        try{
            String novoCE = "12345";
            documentacao.adicionarCE(novoCE);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_TAMANHO_MAX_LISTA_CE_ATINGIDO, ex.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Remover CE">
    @Test
    public void seAListaDeCEForNulaETentarRemoverUmCEAListaDeveContinuarNula(){
        atualizarCampoListaConhecimentoEletronico(null);
        
        String CE = "12345";
        documentacao.removerCE(CE);
        
        assertNull(documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void seAListaDeCEForVaziaETentarRemvoerUmCEAListaDeveContinuarVazia(){
        atualizarCampoListaConhecimentoEletronico("");
        
        String CE = "12345";
        documentacao.removerCE(CE);
        
        assertEquals("", documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void seAListaDeCENaoForVaziaETentarRemvoerUmCEQueNaoEstaNaListaAListaDeveFicarIgual(){
        String CEAtual ="ABCD;789AB;1111";
        atualizarCampoListaConhecimentoEletronico(CEAtual);
        
        String CE = "12345";
        documentacao.removerCE(CE);
        
        assertEquals(CEAtual, documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void oCeDeveSerRemovidoDaListaQuandoEstiverNoFinalDaLista(){
        String CEAtual ="ABCD;789AB;1111";
        atualizarCampoListaConhecimentoEletronico(CEAtual);
        
        String CE = "1111";
        documentacao.removerCE(CE);
        
        String CEEsperado = "ABCD;789AB";
        assertEquals(CEEsperado, documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void oCeDeveSerRemovidoDaListaQuandoEstiverNoMeioDaLista(){
        String CEAtual ="ABCD;789AB;1111";
        atualizarCampoListaConhecimentoEletronico(CEAtual);
        
        String CE = "789AB";
        documentacao.removerCE(CE);
        
        String CEEsperado = "ABCD;1111";
        assertEquals(CEEsperado, documentacao.getListaConhecimentoEletronico());
    }
    
    @Test
    public void oCeDeveSerRemovidoDaListaQuandoEstiverNoInicioDaLista(){
        String CEAtual ="ABCD;789AB;1111";
        atualizarCampoListaConhecimentoEletronico(CEAtual);
        
        String CE = "ABCD";
        documentacao.removerCE(CE);
        
        String CEEsperado = "789AB;1111";
        assertEquals(CEEsperado, documentacao.getListaConhecimentoEletronico());
    }
    //</editor-fold>
    
    
    
    
    private void atualizarCampoListaConhecimentoEletronico(String valor){
        Field field = ReflectionUtils.findField(DocumentacaoLongoCurso.class, "listaConhecimentoEletronico");
        field.setAccessible(true);
        ReflectionUtils.setField(field, documentacao, valor);
    }
    
}
