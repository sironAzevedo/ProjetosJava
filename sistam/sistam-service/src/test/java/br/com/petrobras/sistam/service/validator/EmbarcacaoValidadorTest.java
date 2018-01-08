package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.enums.ClassificacaoEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.EmbarcacaoBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Test;

public class EmbarcacaoValidadorTest {

    private EmbarcacaoValidador validador = new EmbarcacaoValidador();
    
    
     
    @Test
    public void naoSeraPermitidoSalvarEmbarcacaoSemInformarAClassificacao(){
        Embarcacao embarcacao = obterEmbarcacaoValida();
        embarcacao.setClassificacao(null);
        try{
            validador.validarSalvarEmbarcacao(embarcacao);
            fail("Uma embarcação não pode ser salvo sem classificação");
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMBARCACAO);
            assertEquals(ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarEmbarcacaoSemInformarNome(){
       Embarcacao embarcacao = obterEmbarcacaoValida();
       embarcacao.setNomeCompleto(null);
       try{
            validador.validarSalvarEmbarcacao(embarcacao);
            fail();
       }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMBARCACAO);
            assertEquals(ConstantesI18N.EMBARCACAO_NOME_OBRIGATORIO, pendencias.get(0).getMessage());
       }
    }

    @Test
    public void naoSeraPermitidoSalvarEmbarcacaoSemInformarApelido(){
       Embarcacao embarcacao = obterEmbarcacaoValida();
       embarcacao.setApelido(null);
       try{
            validador.validarSalvarEmbarcacao(embarcacao);
            fail();
       }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMBARCACAO);
            assertEquals(ConstantesI18N.EMBARCACAO_APELIDO_OBRIGATORIO, pendencias.get(0).getMessage());
       }
    }
    @Test
    public void naoSeraPermitidoSalvarEmbarcacaoSemBandeira(){
       Embarcacao embarcacao = obterEmbarcacaoValida();
       embarcacao.setBandeira(null);
       try{
            validador.validarSalvarEmbarcacao(embarcacao);
            fail();
       }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMBARCACAO);
            assertEquals(ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO, pendencias.get(0).getMessage());
       }
    }

    @Test
    public void naoSeraPermitidoSalvarEmbarcacaoSemInformarImo(){
       Embarcacao embarcacao = obterEmbarcacaoValida();
       embarcacao.setImo(null);
       try{
            validador.validarSalvarEmbarcacao(embarcacao);
            fail();
       }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMBARCACAO);
            assertEquals(ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO, pendencias.get(0).getMessage());
       }
    }
    
    @Test
    public void naoSeraPermitidoSalvarEmbarcacaoSemInformarDtConstrucao(){
       Embarcacao embarcacao = obterEmbarcacaoValida();
       embarcacao.setDataConstrucao(null);
       try{
            validador.validarSalvarEmbarcacao(embarcacao);
            fail();
       }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMBARCACAO);
            assertEquals(ConstantesI18N.EMBARCACAO_DT_CONSTRUCAO_OBRIGATORIO, pendencias.get(0).getMessage());
       }
    }
    @Test
    public void naoSeraPermitidoSalvarEmbarcacaoSemInformarIrin(){
       Embarcacao embarcacao = obterEmbarcacaoValida();
       embarcacao.setIrin(null);
       try{
            validador.validarSalvarEmbarcacao(embarcacao);
            fail();
       }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMBARCACAO);
            assertEquals(ConstantesI18N.EMBARCACAO_IRIN_OBRIGATORIO, pendencias.get(0).getMessage());
       }
    }

    @Test
    public void naoSeraPermitidoSalvarEmbarcacaoSemInformarTipoEmbarcacao(){
       Embarcacao embarcacao = obterEmbarcacaoValida();
       embarcacao.setTipoEmbarcacao(null);
       try{
            validador.validarSalvarEmbarcacao(embarcacao);
            fail();
       }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.EMBARCACAO);
            assertEquals(ConstantesI18N.EMBARCACAO_TIPO_OBRIGATORIO, pendencias.get(0).getMessage());
       }
    }
    
    private Embarcacao obterEmbarcacaoValida() {
            Embarcacao embarcacao = EmbarcacaoBuilder.novaEmbarcacao()
                    .comId("1L")
                    .comNome("TESTE")
                    .comApelido("TESTE")
                    .comImo("123456")
                    .comClassificacao(ClassificacaoEmbarcacao.GRANELEIRO)
                    .comHeliporto(true)
                    .comTipoEmbarcacao(TipoEmbarcacao.NAVIO)
                    .comBandeira(new Pais())
                    .comIrin("irin")
                    .comDataConstrucao(new Date())
                    .build();
        return embarcacao;
    }
    
    
    

}
