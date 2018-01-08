package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import java.util.Date;

public class ManobraBuilder {

    private Manobra manobra;
    
    private ManobraBuilder(Manobra manobra) {
        this.manobra = manobra;
    }
    
    public static ManobraBuilder novaManobra() {
        return new ManobraBuilder(new Manobra());
    }
    
    public static ManobraBuilder novaManobra(Manobra manobra) {
        return new ManobraBuilder(manobra);
    }
    
    public Manobra build() {
        return this.manobra;
    }
    
    public ManobraBuilder comId(Long id){
        this.manobra.setId(id);
        return this;
    }
    
    public ManobraBuilder doAgenciamento(Agenciamento agenciamento) {
        this.manobra.setAgenciamento(agenciamento);
        return this;
    }
    
    public ManobraBuilder comResponsavelCusto(ResponsavelCustoEntity responsavelCusto) {
        this.manobra.setResponsavelCusto(responsavelCusto);
        return this;
    }
    
    public ManobraBuilder comStatus(StatusManobra status){
        this.manobra.setStatus(status);
        return this;
    }
    
    public ManobraBuilder comCaladoRe(Double caladoRe){
        this.manobra.setCaladoRe(caladoRe);
        return this;
    }

    public ManobraBuilder comCaladoVante(Double caladoVante){
        this.manobra.setCaladoVante(caladoVante);
        return this;
    }
    
    public ManobraBuilder comPontoAtracacaoOrigem(PontoAtracacao pontoAtracacaoOrigem) {
        this.manobra.setPontoAtracacaoOrigem(pontoAtracacaoOrigem);
        return this;
    }
    
    public ManobraBuilder comPontoAtracacaoDestino(PontoAtracacao pontoAtracacaoDestino) {
        this.manobra.setPontoAtracacaoDestino(pontoAtracacaoDestino);
        return this;
    }
    
    public ManobraBuilder comFinalidadeManobra(FinalidadeManobra finalidade) {
        this.manobra.setFinalidadeManobra(finalidade);
        return this;
    }
    
    public ManobraBuilder comDataInicio(Date dataInicio) {
        this.manobra.setDataInicio(dataInicio);
        return this;
    }

    public ManobraBuilder comDataTermino(Date dataTermino) {
        this.manobra.setDataTermino(dataTermino);
        return this;
    }

    public ManobraBuilder comDataPartidaOrigem(Date dataPartidaOrigem) {
        this.manobra.setDataPartidaOrigem(dataPartidaOrigem);
        return this;
    }

    public ManobraBuilder comDataChegadaDestino(Date dataChegadaDestino) {
        this.manobra.setDataChegadaDestino(dataChegadaDestino);
        return this;
    }
    
}
