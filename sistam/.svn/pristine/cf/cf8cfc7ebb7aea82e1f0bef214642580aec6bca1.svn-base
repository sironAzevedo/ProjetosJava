/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petrobras.sistam.common.util;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import static br.com.petrobras.sistam.common.util.SistamDateUtils.*;

/**
 *
 * @author adzk
 */
public class SistamDateUtilsTest {
    
    //<editor-fold defaultstate="collapsed" desc="Quantidade de Dias Corridos">
    @Test
    public void deveRetornarExcessaoNaQuantidadeDeDiasCorridosQuandoDataFimMenorQueDataInicio() {
        Date inicio = informarDataHora(28, 11, 2014, 10, 0, null);
        Date fim = informarDataHora(27, 11, 2014, 10, 0, null);
        diasCorridos(inicio, fim, null);
    }

    @Test
    public void deveRetornarZeroNaQuantidadeDeDiasCorridosParaDataIguais() {
        Date data = informarDataHora(28, 11, 2014, 10, 0, null);
        assertEquals(0, diasCorridos(data, data, null));
    }

    @Test
    public void deveRetornarQuantidadeDeDiasCorridosParaDataNoMesmoMes() {
        Date inicio = informarDataHora(20, 11, 2014, 10, 0, null);
        Date fim = informarDataHora(28, 11, 2014, 10, 0, null);
        assertEquals(8, diasCorridos(inicio, fim, null));
    }

    @Test
    public void deveRetornarQuantidadeDeDiasCorridosParaDataEmMesDiferente() {
        Date inicio = informarDataHora(20, 11, 2014, 10, 0, null);
        Date fim = informarDataHora(20, 12, 2014, 10, 0, null);
        assertEquals(30, diasCorridos(inicio, fim, null));
    }

    @Test
    public void deveRetornarQuantidadeDeDiasCorridosParaDataEmMesDiferenteComHoraDiferente() {
        Date inicio = informarDataHora(20, 11, 2014, 10, 0, null);
        Date fim = informarDataHora(20, 12, 2014, 8, 0, null);
        assertEquals(30, diasCorridos(inicio, fim, null));
    }
    //</editor-fold>
    
}