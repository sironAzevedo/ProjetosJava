package br.com.petrobras.sistam.common.util;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author adzk
 */
public class FileUtilTest {

    private static final String nomeArquivoTempDeTeste = "NovoArquivoTempDeTeste";
    private static final String accents1 = "È,É,Ê,Ë,Û,Ù,Ï,Î,À,Â,Ô,è,é,ê,ë,û,ù,ï,î,à,â,ô,Ç,ç,Ã,ã,Õ,õ";
    private static final String expected1 = "E,E,E,E,U,U,I,I,A,A,O,e,e,e,e,u,u,i,i,a,a,o,C,c,A,a,O,o";
    private static final String accents2 = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
    private static final String expected2 = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
    private static final String accents3 = "Atualização_Diária-1.23.40.exe";
    private static final String expected3 = "Atualizacao_Diaria-1.23.40.exe";
    private static final String accents4 = "Um nome de arquivo no Windows não pode conter nenhum dos seguintes caracteres (\\) (/) (:) (*) (?) (\") (<) (>) (|)";
    private static final String expected4 = "Um nome de arquivo no Windows nao pode conter nenhum dos seguintes caracteres () () () () () () () () ()";

    @Test
    public void deveRemoverTodosOsAcentosECaracteresEspeciais() {
        assertEquals(expected1, FileUtil.unaccent(accents1));
        assertEquals(expected2, FileUtil.unaccent(accents2));
        assertEquals(expected3, FileUtil.unaccent(accents3));
        assertEquals(expected4, FileUtil.unaccent(accents4));
    }

    @Test
    public void deveCriarUmArquivoNoTemSemOTimestampNoFileName() {
        try {
            File arquivo = FileUtil.createFileOnTemp(nomeArquivoTempDeTeste, ".txt");
            assertTrue(arquivo.createNewFile());
            assertTrue(arquivo.exists());
            assertEquals(nomeArquivoTempDeTeste + ".txt", arquivo.getName());
            assertTrue(arquivo.delete());
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void deveCriarUmArquivoIncrementadoNoTemSemOTimestampCasoEleExistaNoFileName() {
        try {
            File arquivoExistente = FileUtil.createFileOnTemp(nomeArquivoTempDeTeste, ".txt");
            assertTrue(arquivoExistente.createNewFile());

            File arquivoIncrementado = FileUtil.createFileOnTemp(nomeArquivoTempDeTeste, ".txt");
            assertTrue(arquivoIncrementado.createNewFile());
            assertTrue(arquivoIncrementado.exists());
            assertEquals(nomeArquivoTempDeTeste + "_1.txt", arquivoIncrementado.getName());

            assertTrue(arquivoExistente.delete());
            assertTrue(arquivoIncrementado.delete());
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
}