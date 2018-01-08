package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.test.builder.ServicoMedicoOdontologicoBuilder;
import br.com.petrobras.sistam.test.builder.ServicoProtecaoBuilder;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ValidadorServicoMedicoOdontologicoTest {

    private ValidadorServicoMedicoOdontologico validador = new ValidadorServicoMedicoOdontologico();

    @Test
    public void naoSeraPermitidoSalvarServicoMedicoOdontologicoSemServicoProtecao(){
        ServicoMedicoOdontologico servicoMedicoOdontologico = obterServicoMedicoOdontologicoValido();
        servicoMedicoOdontologico.setServicoProtecao(null);
        try{
            validador.validarSalvarServicoMedicoOdontologico(servicoMedicoOdontologico);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_MEDICO_ODONTOLOGICO);
            assertEquals(ConstantesI18N.SERVICO_MEDICO_ODONTOLOGICO_SERVICO_PROTECAO_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    }
    
    @Test
    public void naoSeraPermitidoSalvarVisitaSemInformarNomeDoTripulante(){
        ServicoMedicoOdontologico servicoMedicoOdontologico = obterServicoMedicoOdontologicoValido();
        servicoMedicoOdontologico.setNomeTripulante(null);
        try{
            validador.validarSalvarServicoMedicoOdontologico(servicoMedicoOdontologico);
            fail();
        }catch(SistamPendingException ex){
            List<Pendency> pendencias = ex.getPendingMap().get(TipoExcecao.SERVICO_MEDICO_ODONTOLOGICO);
            assertEquals(ConstantesI18N.SERVICO_MEDICO_ODONTOLOGICO_NOME_TRIPULANTE_OBRIGATORIO, pendencias.get(0).getMessage());
        }
    } 
    
    private ServicoMedicoOdontologico obterServicoMedicoOdontologicoValido() {
        ServicoMedicoOdontologico servicoMedicoOdontologico = ServicoMedicoOdontologicoBuilder.novoServicoMedicoOdontologico()
                .comId(1L)
                .comServicoProtecao(ServicoProtecaoBuilder.novoServicoProtecao().build())
                .comNomeTripulante("Maria Lucia")                
                .build();
        return servicoMedicoOdontologico;
    }
}
