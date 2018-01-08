package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.UnidadeMedida;
import java.lang.reflect.Field;
import java.util.Date;
import org.unitils.util.ReflectionUtils;



public class OperacaoBuilder {

    private Operacao operacao;
    
    private OperacaoBuilder(Operacao operacao) {
        this.operacao = operacao;
    }
    
    public static OperacaoBuilder novaOperacao() {
        return new OperacaoBuilder(new Operacao());
    }
    
    public Operacao build() {
        return this.operacao;
    }
    
    public OperacaoBuilder comId(Long id){
        Field field = ReflectionUtils.getFieldWithName(Operacao.class, "id", false);
        ReflectionUtils.setFieldValue(operacao, field, id);
        return this;
    }

    public OperacaoBuilder doAgenciamento(Agenciamento agenciamento) {
        this.operacao.setAgenciamento(agenciamento);
        return this;
    }
    
    public OperacaoBuilder comProduto(Produto produto) {
        this.operacao.setProduto(produto);
        return this;
    }
    
    public OperacaoBuilder comQuantidadeEstimada(Double quantidadeEstimada) {
        this.operacao.setQuatidadeEstimada(quantidadeEstimada);
        return this;
    }
    
    public OperacaoBuilder comTipoOperacao(TipoOperacao tipoOperacao) {
        this.operacao.setTipoOperacao(tipoOperacao);
        return this;
    }
    
    public OperacaoBuilder comDataInicio(Date dataInicio) {
        this.operacao.setDataInicio(dataInicio);
        return this;
    }
    
    public OperacaoBuilder comDataFim(Date dataFim) {
        this.operacao.setDataFim(dataFim);
        return this;
    }
    
    public OperacaoBuilder comUnidadeDeMedida(UnidadeMedida unidadeMedida) {
        this.operacao.setUnidadeMedida(unidadeMedida);
        return this;
    }
    


    
}
