package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Visita;
import java.lang.reflect.Field;
import java.util.Date;
import org.unitils.util.ReflectionUtils;

public class VisitaBuilder {

    private Visita visita;
    
    private VisitaBuilder(Visita visita) {
        this.visita = visita;
        this.visita.setChaveUsuarioAlteracao("STAM");
        this.visita.setNomeUsuarioAlteracao("STAM");
        this.visita.setDataAlteracao(new Date());
    }
    
    public static VisitaBuilder novaVisita() {
        return new VisitaBuilder(new Visita());
    }
    
    public static VisitaBuilder novaVisita(Visita visita) {
        return new VisitaBuilder(visita);
    }
    
    public Visita build() {
        return this.visita;
    }

    public VisitaBuilder comId(Long id) {
        Field field = ReflectionUtils.getFieldWithName(Visita.class, "id", false);
        ReflectionUtils.setFieldValue(visita, field, id);
        return this;
    }

    public VisitaBuilder doAgenciamento(Agenciamento agenciamento) {
        this.visita.setAgenciamento(agenciamento);
        return this;
    }
    
    public VisitaBuilder comDataInicio(Date dataInicio) {
        visita.setDataInicio(dataInicio);
        return this;
    }
    
    public VisitaBuilder comDataTermino(Date dataTermino) {
        visita.setDataTermino(dataTermino);
        return this;
    }
    
    public VisitaBuilder comChaveAgente(String chave) {
        visita.setChaveAgente(chave);
        return this;
    }
    
    public VisitaBuilder comNomeAgente(String nome) {
        visita.setNomeAgente(nome);
        return this;
    }
    
    
}
