package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import br.com.petrobras.sistam.common.enums.PastaAnexo;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import static br.com.petrobras.sistam.test.builder.AnexoBuilder.*;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class ValidadorAnexoTest {
    private ValidadorAnexo validador = new ValidadorAnexo();
    
    @Test
    public void naoSeraPossivelSalvarUmAnexoSemInformarOAgenciamento(){
        Anexo anexo = obterAnexoValido();
        anexo.setAgenciamento(null);
        
        try{
            validador.validarSalvarAnexo(anexo);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ANEXO);
            assertEquals(ConstantesI18N.ANEXO_AGENCIAMENTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmAnexoSemInformarAPasta(){
        Anexo anexo = obterAnexoValido();
        anexo.setPasta(null);
        
        try{
            validador.validarSalvarAnexo(anexo);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ANEXO);
            assertEquals(ConstantesI18N.ANEXO_PASTA_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmAnexoSemInformarONome(){
        Anexo anexo = obterAnexoValido();
        anexo.setNome(null);
        
        try{
            validador.validarSalvarAnexo(anexo);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ANEXO);
            assertEquals(ConstantesI18N.ANEXO_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmAnexoSemInformarAExtensao(){
        Anexo anexo = obterAnexoValido();
        anexo.setExtensao(null);
        
        try{
            validador.validarSalvarAnexo(anexo);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ANEXO);
            assertEquals(ConstantesI18N.ANEXO_EXTENSAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmAnexoSemInformarOTamanho(){
        Anexo anexo = obterAnexoValido();
        anexo.setTamanhoEmBytes(null);
        
        try{
            validador.validarSalvarAnexo(anexo);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ANEXO);
            assertEquals(ConstantesI18N.ANEXO_TAMANHO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmAnexoSemInformarOArquivo(){
        Anexo anexo = obterAnexoValido();
        anexo.setArquivo(null);
        
        try{
            validador.validarSalvarAnexo(anexo);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.ANEXO);
            assertEquals(ConstantesI18N.ANEXO_ARQUIVO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmAnexoSeOTamanhoDoAnexoForMaioQueOEspacoRestanteDoAgenciamento(){
        Anexo anexo = obterAnexoValido();
        
        Anexo outroAnexo = new Anexo();
        outroAnexo.setTamanhoEmBytes(Agenciamento.TAM_MAX_TOTAL_ARQUIVOS_EM_BYTES); //5 MB, tamanho máximo para o agenciamento.
        
        anexo.getAgenciamento().adicionarAnexo(outroAnexo);
        try{
            validador.validarSalvarAnexo(anexo);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.ANEXO_ESPACO_INSUFICIENTE_NO_AGENCIAMENTO, ex.getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelSalvarUmAnexoComTamanhoZero(){
        Anexo anexo = obterAnexoValido();
        anexo.setTamanhoEmBytes(0L);
        
        try{
            validador.validarSalvarAnexo(anexo);
            fail();
        }catch(BusinessException ex){
            assertEquals(ConstantesI18N.ANEXO_ARQUIVO_VAZIO, ex.getMessage());
        }
    }
    
    private Anexo obterAnexoValido(){
        Anexo anexo = novoAnexo()
                .comAgenciamento(new Agenciamento())
                .comExtensao("pdf")
                .comNome("Comprovante.pdf")
                .comTamanhoEmBytes(1024L)
                .comPasta(PastaAnexo.SUPRIMENTOS)
                .comArquivo(new Arquivo())
                .build();
        return anexo;
    }
    
    

}
