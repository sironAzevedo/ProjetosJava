package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.util.TimeZone;

import org.junit.Test;
import static junit.framework.Assert.*;

/**
 *
 * @author adzk
 */
public class FiltroAgenciamentoAtendimentoTest {
    
    private TimeZone timeZoneBahia = TimeZone.getTimeZone("Etc/GMT-3");
    private TimeZone timeZoneSaoPaulo = TimeZone.getTimeZone("Etc/GMT-4");
    private FiltroAgenciamentoAtendimento filtro;
    
    public FiltroAgenciamentoAtendimentoTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    
    @Test
    public void quandoTimeZoneDefaultDeveRetornarOMesInicial(){
        filtro = obterFiltroValido(TimeZone.getDefault());
        assertEquals(6, filtro.getMesInicial().intValue());
    }
    
    @Test
    public void quandoTimeZoneBahiaDeveRetornarOMesInicial(){
        filtro = obterFiltroValido(timeZoneBahia);
        assertEquals(6, filtro.getMesInicial().intValue());
    }
    
    @Test
    public void quandoTimeZoneSaoPauloDeveRetornarOMesInicial(){
        filtro = obterFiltroValido(timeZoneSaoPaulo);
        assertEquals(6, filtro.getMesInicial().intValue());
    }
    
    @Test
    public void quandoTimeZoneDefaultDeveRetornarOMesFinal(){
        filtro = obterFiltroValido(TimeZone.getDefault());
        assertEquals(11, filtro.getMesFinal().intValue());
    }
    
    @Test
    public void quandoTimeZoneBahiaDeveRetornarOMesFinal(){
        filtro = obterFiltroValido(timeZoneBahia);
        assertEquals(11, filtro.getMesFinal().intValue());
    }
    
    @Test
    public void quandoTimeZoneSaoPauloDeveRetornarOMesFinal(){
        filtro = obterFiltroValido(timeZoneSaoPaulo);
        assertEquals(11, filtro.getMesFinal().intValue());
    }
    
 
    private FiltroAgenciamentoAtendimento obterFiltroValido(TimeZone zone){
        FiltroAgenciamentoAtendimento filtro = new FiltroAgenciamentoAtendimento();
        
        Agencia agencia = new Agencia();
        agencia.setTimezone(zone.getID());
        filtro.setAgencia(agencia);
        filtro.setDataInicial(SistamDateUtils.informarDataHora(1, 6, 2014, 0, 0, null));
        filtro.setDataFinal(SistamDateUtils.informarDataHora(1, 11, 2014, 0, 0, null));
        return filtro;
    }
}