package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.SituacaoLivrePratica;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.util.Date;
import static junit.framework.Assert.*;
import org.junit.Test;


public class AgenciamentoTest {
    private Agenciamento agenciamento = new Agenciamento();
    
    //<editor-fold defaultstate="collapsed" desc="Testes para obter a localização atual">
    @Test
    public void aoObterALocalizacaoAtualSemNehumaManobraComStatusRegistradoDeveRetornarNull(){
        PontoAtracacao localizacaoAtual = agenciamento.obterLocalizacaoAtual();
        assertNull(localizacaoAtual);
    }
    
    @Test
    public void aoObterALocalizacaoAtualComManobrasNaoRegistradasDeveRetornarNull(){
        agenciamento.adicionarManobra(obterManobraCom(StatusManobra.PLANEJADA, null, "PONTO A"));
        agenciamento.adicionarManobra(obterManobraCom(StatusManobra.SOLICITADA, null, "PONTO B"));
        
        PontoAtracacao localizacaoAtual = agenciamento.obterLocalizacaoAtual();
        assertNull(localizacaoAtual);
    }
    
    /**
     * É considerado manobra registra as manobras com status REGISTRADO ou  ENCERRADO
     */
    @Test
    public void aoObterALocalizacaoAtualDeveRetornarOPontoFinalDaUltimaManobraRegistrada(){
        agenciamento.adicionarManobra(obterManobraCom(StatusManobra.PLANEJADA, null, "PONTO A"));
        agenciamento.adicionarManobra(obterManobraCom(StatusManobra.SOLICITADA, null, "PONTO B"));
        agenciamento.adicionarManobra(obterManobraCom(StatusManobra.REGISTRADA, DateBuilder.on(1, 1, 2013).getTime(), "PONTO C"));
        agenciamento.adicionarManobra(obterManobraCom(StatusManobra.ENCERRADA, DateBuilder.on(15, 1, 2013).getTime(), "PONTO D"));
        agenciamento.adicionarManobra(obterManobraCom(StatusManobra.CANCELADA_FORA_PRAZO, DateBuilder.on(30, 1, 2013).getTime(), "PONTO E"));
        
        PontoAtracacao localizacaoAtual = agenciamento.obterLocalizacaoAtual();
        assertEquals("PONTO D", localizacaoAtual.getNome());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Testes para obter a situação da Livre Prática">
    @Test
    public void aoObterASituracaoDeLivrePraticaDeUmAgenciamentoVCPDeveRetornarVCP(){
        agenciamento.setTipoContrato(TipoContrato.VCP);
        
        assertEquals(SituacaoLivrePratica.VCP, agenciamento.getSituacaoLivrePratica());
    }
    
    @Test
    public void aoObterASituacaoLivrePraticaDeUmAgenciamentoComODocumentoSolicitacaoDeCertificadoJaProtocoladoDeveRetornarOK(){
        Documento documento = new Documento();
        documento.setTipoDocumento(TipoDocumento.SOLICITACAO_CERTIFICADO);
        documento.setDataProtocolo(DateBuilder.on(1, 1, 2014).getTime());
        
        agenciamento.setTipoContrato(TipoContrato.TCP);
        agenciamento.adicionarDocumento(documento);
        
        assertEquals(SituacaoLivrePratica.OK, agenciamento.getSituacaoLivrePratica());
    }
    
    @Test
    public void aoObterASituacaoLivrePraticaDeUmAgenciamentoComODocumentoSolicitacaoDeCertificadoNaoProtocoladoDeveRetornarANV(){
        Documento documento = new Documento();
        documento.setTipoDocumento(TipoDocumento.SOLICITACAO_CERTIFICADO);
        
        agenciamento.setTipoContrato(TipoContrato.TCP);
        agenciamento.adicionarDocumento(documento);
        
        assertEquals(SituacaoLivrePratica.ANV, agenciamento.getSituacaoLivrePratica());
    }
    
    @Test
    public void aoObterASituacaoLivrePraticaDeUmAgenciamentoSemODocumentoSolicitacaoDeCertificadoESemTerPagoATaxaDeLivrePraticaDeveRetornarPP(){
        agenciamento.setTipoContrato(TipoContrato.TCP);
        
        assertEquals(SituacaoLivrePratica.PP, agenciamento.getSituacaoLivrePratica());
    }
    
    @Test
    public void aoObterASituacaoLivrePraticaDeUmAgenciamentoSemODocumentoSolicitacaoDeCertificadoEComTerPagoATaxaDeLivrePraticaDeveRetornarNull(){
        Taxa taxa = new Taxa();
        taxa.setTipoTaxa(TipoTaxa.LIVRE_PRATICA_ANVISA);
        
        agenciamento.setTipoContrato(TipoContrato.TCP);
        agenciamento.adicionarTaxa(taxa);
        
        assertNull(agenciamento.getSituacaoLivrePratica());
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Testes para obter a data e hora de atracação">
    @Test
    public void quandoNaoExistirManobraDeAtracacaoDeveRetornarADataHoraDeAtracacaoNula(){
        //garante que não tem manobra
        assertTrue(agenciamento.getManobras().isEmpty());
        
        assertNull(agenciamento.getDataHoraAtracacao());
    }
    
    @Test
    public void quandoTiverApenasUmaManobraDeAtracacaoDeveRetornarADataDeTerminoDessaManobra(){
        Date data = DateBuilder.on(1, 10, 2014).getTime();
        
        Manobra manobra = new Manobra();
        manobra.setFinalidadeManobra(FinalidadeManobra.ATRACACAO);
        manobra.setDataTermino(data);
        
        agenciamento.adicionarManobra(manobra);
        
        assertTrue(data.compareTo(agenciamento.getDataHoraAtracacao()) == 0);
    }
    
    @Test
    public void quandoTiverMaisDeUmaManobraDeAtracacaoDeveRetornarAMaiorDataDeTerminoDasManobra(){
        Date data2 = DateBuilder.on(1, 10, 2014).getTime();
        Date data3 = DateBuilder.on(1, 12, 2014).getTime();
        
        Manobra manobra1 = new Manobra();
        manobra1.setFinalidadeManobra(FinalidadeManobra.ATRACACAO);
        manobra1.setDataTermino(null);
        
        Manobra manobra2 = new Manobra();
        manobra2.setFinalidadeManobra(FinalidadeManobra.ATRACACAO);
        manobra2.setDataTermino(data2);
        
        Manobra manobra3 = new Manobra();
        manobra3.setFinalidadeManobra(FinalidadeManobra.ATRACACAO);
        manobra3.setDataTermino(data3);
        
        agenciamento.adicionarManobra(manobra1);
        agenciamento.adicionarManobra(manobra2);
        agenciamento.adicionarManobra(manobra3);
        
        assertTrue(data3.compareTo(agenciamento.getDataHoraAtracacao()) == 0);
    }
    //</editor-fold>
    
    private Manobra obterManobraCom(StatusManobra status, Date dataChegada, String pontoAtracacao){
        PontoAtracacao ponto = new PontoAtracacao();
        ponto.setNome(pontoAtracacao);
        
        Manobra manobra = new Manobra();
        manobra.setStatus(status);
        manobra.setDataTermino(dataChegada);
        manobra.setPontoAtracacaoDestino(ponto);
        return manobra;
    }
    
    
}
