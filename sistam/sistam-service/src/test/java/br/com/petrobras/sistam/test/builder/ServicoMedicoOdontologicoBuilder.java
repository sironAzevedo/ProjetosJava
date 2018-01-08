package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import java.lang.reflect.Field;
import org.unitils.util.ReflectionUtils;

public class ServicoMedicoOdontologicoBuilder {
    private ServicoMedicoOdontologico servicoMedicoOdontologico;
    
    private ServicoMedicoOdontologicoBuilder(ServicoMedicoOdontologico servicoMedicoOdontologico) {
        this.servicoMedicoOdontologico = servicoMedicoOdontologico;
    }
    
    public static ServicoMedicoOdontologicoBuilder novoServicoMedicoOdontologico() {
        return new ServicoMedicoOdontologicoBuilder(new ServicoMedicoOdontologico());
    }
    
    public static ServicoMedicoOdontologicoBuilder novoServicoMedicoOdontologico(ServicoMedicoOdontologico servicoMedicoOdontologico) {
        return new ServicoMedicoOdontologicoBuilder(servicoMedicoOdontologico);
    }
    
    public ServicoMedicoOdontologico build() {
        return this.servicoMedicoOdontologico;
    }

    public ServicoMedicoOdontologicoBuilder comId(Long id) {
        Field field = ReflectionUtils.getFieldWithName(ServicoMedicoOdontologico.class, "id", false);
        ReflectionUtils.setFieldValue(servicoMedicoOdontologico, field, id);
        return this;
    }

    public ServicoMedicoOdontologicoBuilder comServicoProtecao(ServicoProtecao servicoProtecao) {
        this.servicoMedicoOdontologico.setServicoProtecao(servicoProtecao);
        return this;
    }
    
    public ServicoMedicoOdontologicoBuilder comNomeTripulante(String nomeTripulante) {
        this.servicoMedicoOdontologico.setNomeTripulante(nomeTripulante);
        return this;
    }
    
}
