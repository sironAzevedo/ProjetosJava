package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.enums.ClassificacaoEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoEmbarcacao;
import java.util.Date;

public class EmbarcacaoBuilder {

    private Embarcacao embarcacao;
    
    private EmbarcacaoBuilder(Embarcacao embarcacao) {
        this.embarcacao = embarcacao;
    }
    
    public static EmbarcacaoBuilder novaEmbarcacao() {
        return new EmbarcacaoBuilder(new Embarcacao());
    }
    
    public Embarcacao build() {
        return this.embarcacao;
    }

    public EmbarcacaoBuilder comId(String id) {
        this.embarcacao.setId(id);
        return this;
    }

    public EmbarcacaoBuilder comNome(String nome) {
        this.embarcacao.setNomeCompleto(nome);
        return this;
    }
    
    public EmbarcacaoBuilder comApelido(String apelido) {
        this.embarcacao.setApelido(apelido);
        return this;
    }

    public EmbarcacaoBuilder comImo(String imo) {
        this.embarcacao.setImo(imo);
        return this;
    }
    
    public EmbarcacaoBuilder comClassificacao(ClassificacaoEmbarcacao classificacao) {
        this.embarcacao.setClassificacao(classificacao);
        return this;
    }

    public EmbarcacaoBuilder comHeliporto(Boolean heliporto) {
        this.embarcacao.setHeliporto(heliporto);
        return this;
    }
    
    public EmbarcacaoBuilder comTipoEmbarcacao(TipoEmbarcacao tipoEmbarcacao) {
        this.embarcacao.setTipoEmbarcacao(tipoEmbarcacao);
        return this;
    }
    
    public EmbarcacaoBuilder comBandeira(Pais bandeira) {
        this.embarcacao.setBandeira(bandeira);
        return this;
    }
    
    public EmbarcacaoBuilder comIrin(String irin) {
        this.embarcacao.setIrin(irin);
        return this;
    }
    
    public EmbarcacaoBuilder comDataConstrucao(Date dataConstrucao){
        this.embarcacao.setDataConstrucao(dataConstrucao);
        return this;
    }
    
}
