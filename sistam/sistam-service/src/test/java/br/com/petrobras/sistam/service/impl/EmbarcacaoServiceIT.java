package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.EmbarcacaoService;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.enums.ClassificacaoEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoEmbarcacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmbarcacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import br.com.petrobras.sistam.test.builder.EmbarcacaoBuilder;
import br.com.petrobras.sistam.test.builder.PaisBuilder;
import br.com.petrobras.snarf.test.unitils.BaseIntegrationTestClass;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/EmbarcacaoService.xml")
public class EmbarcacaoServiceIT extends BaseIntegrationTestClass {

    @SpringBean("EmbarcacaoServiceImpl")
    private EmbarcacaoService embarcacaoService;

    @Test
    public void deveSalvarEmbarcacao() {
        Embarcacao embarcacao = obterEmbarcacaoValida();
        embarcacao = embarcacaoService.salvarEmbarcacao(embarcacao);
        Assert.assertNotNull(embarcacaoService.buscarEmbarcacaoPorId(embarcacao.getId()));
    }

    @Test
    public void deveRetornarUmaEmbarcacaoPorNome() {
        List<Embarcacao> embarcacoes = embarcacaoService.buscarEmbarcacoes("Test");
        Assert.assertEquals(1, embarcacoes.size());
    }

    @Test
    public void naoDeveRetornarEmbarcacaoQuandoConsultoPorNomeInexistente() {
        List<Embarcacao> embarcacoes = embarcacaoService.buscarEmbarcacoes("MIRA");
        Assert.assertEquals(0, embarcacoes.size());
    }
    
    @Test
    public void deveRetonarAsUltimasEscalasDaEmbarcacao(){
        Embarcacao embarcacao = embarcacaoService.buscarEmbarcacaoPorId("18C3");
        List<Escala> escalas = embarcacaoService.buscarUltimasEscalasDaEmbarcacao(embarcacao, 2);
        Assert.assertEquals(0, escalas.size());
    }

    @Test
    public void deveRetornarUmaEmbarcacaoQuandoConsultoPorNome() {
        FiltroEmbarcacao filtro = new FiltroEmbarcacao();
        filtro.setNomeEmbarcacao("NIL");
        filtro.setImo("999999");
        filtro.setBandeira(PaisBuilder.novoPais().comId(Pais.CODIGO_BRASIL).build());
        List<Embarcacao> embarcacoes = embarcacaoService.buscarEmbarcacoesPorFiltro(filtro);
        Assert.assertEquals(1, embarcacoes.size());
    }

    @Test
    public void deveRetornarUmaEmbarcacaoQuandoConsultoPorImo() {
        FiltroEmbarcacao filtro = new FiltroEmbarcacao();
        filtro.setImo("999999");
        List<Embarcacao> embarcacoes = embarcacaoService.buscarEmbarcacoesPorFiltro(filtro);
        Assert.assertEquals(1, embarcacoes.size());
    }

    private Embarcacao obterEmbarcacaoValida() {
        Embarcacao embarcacao = EmbarcacaoBuilder.novaEmbarcacao()
                .comId("1234")
                .comNome("TESTE")
                .comApelido("TESTE")
                .comImo("123456")
                .comClassificacao(ClassificacaoEmbarcacao.GRANELEIRO)
                .comHeliporto(true)
                .comTipoEmbarcacao(TipoEmbarcacao.NAVIO)
                .comBandeira(PaisBuilder.novoPais().comId("019").build())
                .comIrin("IRIN")
                .comDataConstrucao(new Date())
                .build();
        return embarcacao;
    }

    @Test
    public void deveRetornarTodasAsEmbarcacoesDoFiltroRelatorioTimesheet() {
        FiltroRelatorioTimesheet filtro = new FiltroRelatorioTimesheet();
        filtro.setDataDeCorte(null);
        List<Embarcacao> embarcacoes = embarcacaoService.buscarEmbarcacoesPorFiltro(filtro, Collections.EMPTY_LIST);
        Assert.assertEquals(2, embarcacoes.size());
    }
    
}
