package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Transporte;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.AgenciamentoBuilder;
import br.com.petrobras.sistam.test.builder.ResponsavelCustoBuilder;
import br.com.petrobras.sistam.test.builder.TransporteBuilder;
import br.com.petrobras.sistam.test.builder.VisitaBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import br.com.petrobras.snarf.common.util.DateBuilder;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ValidadorVisitaTest {
    private ValidadorVisita validador = new ValidadorVisita();

    //<editor-fold defaultstate="collapsed" desc="Salvar visita">
    @Test
    public void naoSeraPermitidoSalvarVisitaSemAgenciamento() {
        Visita visita = obterVisitaValida();
        visita.setAgenciamento(null);
        try {
            validador.validarSalvarVisita(visita);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.VISITA);
            assertEquals(ConstantesI18N.VISITA_AGENCIAMENTO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarVisitaSemInformarAChaveDoAgente() {
        Visita visita = obterVisitaValida();
        visita.setChaveAgente(null);
        try {
            validador.validarSalvarVisita(visita);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.VISITA);
            assertEquals(ConstantesI18N.VISITA_CHAVE_AGENTE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarVisitaSemInformarONomeDoAgente() {
        Visita visita = obterVisitaValida();
        visita.setNomeAgente(null);
        try {
            validador.validarSalvarVisita(visita);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.VISITA);
            assertEquals(ConstantesI18N.VISITA_NOME_AGENTE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarVisitaSemInformarADataInicio() {
        Visita visita = obterVisitaValida();
        visita.setDataInicio(null);
        try {
            validador.validarSalvarVisita(visita);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.VISITA);
            assertEquals(ConstantesI18N.VISITA_DATA_INICIO_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarVisitaSemInformarADataTermino() {
        Visita visita = obterVisitaValida();
        visita.setDataTermino(null);
        try {
            validador.validarSalvarVisita(visita);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.VISITA);
            assertEquals(ConstantesI18N.VISITA_DATA_TERMINO_OBRIGATORIA, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Transporte">
    @Test
    public void naoSeraPermitidoSalvarTransporteSemVisita() {
        Transporte transporte = obterTransporteValido();
        transporte.setVisita(null);
        try {
            validador.validarSalvarTransporte(transporte);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.TRANSPORTE_VISITA_NAO_PODE_SER_NULA, ex.getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarTransporteSemTipoTransporte() {
        Transporte transporte = obterTransporteValido();
        transporte.setTipoTransporte(null);
        try {
            validador.validarSalvarTransporte(transporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.VISITA);
            assertEquals(ConstantesI18N.TRANSPORTE_INFORME_TIPO_TRANSPORTE, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarTransporteSemCusto() {
        Transporte transporte = obterTransporteValido();
        transporte.setCusto(null);
        try {
            validador.validarSalvarTransporte(transporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.VISITA);
            assertEquals(ConstantesI18N.TRANSPORTE_INFORME_O_CUSTO, pendencias.get(0).getMessage());
        }
    }

    @Test
    public void naoSeraPermitidoSalvarTransporteSemResponsavelCusto() {
        Transporte transporte = obterTransporteValido();
        transporte.setResponsavelCusto(null);
        try {
            validador.validarSalvarTransporte(transporte);
            fail();
        } catch (SistamPendingException ex) {
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.VISITA);
            assertEquals(ConstantesI18N.TRANSPORTE_INFORME_RESPONSAVEL_CUSTO, pendencias.get(0).getMessage());
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private Visita obterVisitaValida() {
        Visita visita = VisitaBuilder.novaVisita()
                .comId(1L)
                .doAgenciamento(AgenciamentoBuilder.novoAgenciamento().build())
                .comDataInicio(DateBuilder.on(1, 1, 2014).getTime())
                .comDataTermino(DateBuilder.on(15, 1, 2014).getTime())
                .comChaveAgente("STAM")
                .comNomeAgente("SISTAM")
                .build();
        return visita;
    }

    private Transporte obterTransporteValido() {
        Transporte transporte = TransporteBuilder.novoTransporte()
                .comId(1L)
                .daVisita(obterVisitaValida())
                .comTipoTransporte(TipoTransporte.MARITIMO)
                .comResponsavelCusto(ResponsavelCustoBuilder.novoResponsavelCusto().build())
                .comCusto(1000D)
                .build();
        return transporte;
    }
    //</editor-fold>
}
