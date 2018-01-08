package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.validator.EmbarcacaoValidador;
import br.com.petrobras.snarf.persistence.GenericDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class EmbarcacaoServiceTest {
    private EmbarcacaoServiceImpl embarcacaoService;
    private ValidadorPermissao validadorPermissao;
    
    @Before
    public void setUp(){
        validadorPermissao = Mockito.mock(ValidadorPermissao.class);
        Mockito.when(validadorPermissao.podeSalvarEmbarcacao()).thenReturn(true);
        
        embarcacaoService = new EmbarcacaoServiceImpl();
        embarcacaoService.setEmbarcacaoValidador(Mockito.mock(EmbarcacaoValidador.class));
        embarcacaoService.setValidadorPermissao(validadorPermissao);
        embarcacaoService.setDao(Mockito.mock(GenericDao.class));
    }    
    
    @Test
    public void deveChamarOValidadorDeSalvarComplementoDaEmbarcacaoQuandoUmaEmbarcacaoForSalva(){
        Embarcacao embarcacao = new Embarcacao();
        embarcacaoService.salvarEmbarcacao(embarcacao);
        Mockito.verify(embarcacaoService.getEmbarcacaoValidador()).validarSalvarEmbarcacao(embarcacao);
        Mockito.verify(validadorPermissao).podeSalvarEmbarcacao();
    }
    
    @Test
    public void deveChamarOSaveOrUpdadteDoDaoQuandoUmaEmbarcacaoForSalva(){
        Embarcacao embarcacao = new Embarcacao();
        embarcacaoService.salvarEmbarcacao(embarcacao);
        Mockito.verify(embarcacaoService.getDao()).saveOrUpdate(embarcacao);
    }
    
}
