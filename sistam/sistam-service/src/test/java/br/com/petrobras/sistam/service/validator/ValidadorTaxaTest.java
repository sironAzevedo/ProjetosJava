package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.TaxaBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Test;

public class ValidadorTaxaTest {

    private ValidadorTaxa validador = new ValidadorTaxa();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Taxa">
    @Test
    public void naoSeraPermitidoSalvarTaxaSemAgenciamento(){
        Taxa taxa = obterTaxaValida();
        taxa.setAgenciamento(null);
        try{
            validador.validarSalvarTaxa(taxa);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.TAXA);
            assertEquals(ConstantesI18N.TAXA_SEM_AGENCIAMENTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarTaxaSemValor(){
        Taxa taxa = obterTaxaValida();
        taxa.setValor(null);
        try{
            validador.validarSalvarTaxa(taxa);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.TAXA);
            assertEquals(ConstantesI18N.TAXA_SEM_VALOR, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarTaxaSemDataPagamento(){
        Taxa taxa = obterTaxaValida();
        taxa.setDataPagamento(null);
        try{
            validador.validarSalvarTaxa(taxa);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.TAXA);
            assertEquals(ConstantesI18N.TAXA_SEM_DATA_PAGAMENTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarTaxaSemResponsavelCusto(){
        Taxa taxa = obterTaxaValida();
        taxa.setResponsavelCusto(null);
        try{
            validador.validarSalvarTaxa(taxa);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.TAXA);
            assertEquals(ConstantesI18N.TAXA_SEM_RESPONSAVEL_CUSTO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarTaxaSemTipo(){
        Taxa taxa = obterTaxaValida();
        taxa.setTipoTaxa(null);
        try{
            validador.validarSalvarTaxa(taxa);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.TAXA);
            assertEquals(ConstantesI18N.TAXA_SEM_TIPO_TAXA, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Buscar Taxas Valor Acumulado">
    @Test
    public void naoSeraPossivelBuscarTaxasValorAcumuladoSemInformarAAgencia(){
        FiltroTaxa filtro = new FiltroTaxa();
        try{
            validador.validarBuscarTaxasValorAcumulado(filtro);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.TAXA);
            assertEquals(ConstantesI18N.RELATORIO_TAXAS_AGENCIA_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelBuscarTaxasValorAcumuladoSemInformarADataPagamentoInicial(){
        FiltroTaxa filtro = new FiltroTaxa();
        filtro.setAgencia(new Agencia());
        try{
            validador.validarBuscarTaxasValorAcumulado(filtro);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.TAXA);
            assertEquals(ConstantesI18N.RELATORIO_TAXAS_PERIODO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPossivelBuscarTaxasValorAcumuladoSemInformarADataPagamentoFinal(){
        FiltroTaxa filtro = new FiltroTaxa();
        filtro.setAgencia(new Agencia());
        filtro.setDataPagamentoInicial(DateBuilder.on(1, 12, 2013).getTime());
        try{
            validador.validarBuscarTaxasValorAcumulado(filtro);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.TAXA);
            assertEquals(ConstantesI18N.RELATORIO_TAXAS_PERIODO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private Taxa obterTaxaValida() {
        Taxa taxa = TaxaBuilder.novaTaxa()
                .comId(1L)
                .comDataPagamento(new Date())
                .comNumeroNecessidadeLiberacaoSap(123L)
                .comResponsavelCusto(ResponsavelCustoBuilder.novoResponsavelCusto().build())
                .comValor(1500.00D)
                .doTipo(TipoTaxa.FUNAPOL_POLICIA_FEDERAL_ENTRADA)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build())
                .build();
        return taxa;
    }
    //</editor-fold>
    
    
    

}
