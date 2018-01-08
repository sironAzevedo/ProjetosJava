package br.com.petrobras.sistam.desktop.embarcacao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmbarcacao;
import java.util.Collections;
import java.util.List;

public class EmbarcacoesModel extends BasePresentationModel<SistamService> {
    private FiltroEmbarcacao filtro = new FiltroEmbarcacao();
    private List<Embarcacao> embarcacoes;
    private Embarcacao embarcacaoSelecionada;
    private Pais paisSelecionado;

    public EmbarcacoesModel() {
        embarcacoes = Collections.EMPTY_LIST;
    }

    public List<Embarcacao> getEmbarcacoes() {
        return embarcacoes;
    }

    public void setEmbarcacoes(List<Embarcacao> embarcacoes) {
        this.embarcacoes = embarcacoes;
        firePropertyChange("embarcacoes", null, this.embarcacoes);
    }

    public Pais getPaisSelecionado() {
        return paisSelecionado;
    }

    public void setPaisSelecionado(Pais paisSelecionado) {
        this.paisSelecionado = paisSelecionado;
    }

    
    public Embarcacao getEmbarcacaoSelecionada() {
        return embarcacaoSelecionada;
    }

    public void setEmbarcacaoSelecionada(Embarcacao embarcacaoSelecionada) {
        this.embarcacaoSelecionada = embarcacaoSelecionada;
        firePropertyChange("embarcacaoSelecionada", null, this.embarcacaoSelecionada);
    }

    public FiltroEmbarcacao getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroEmbarcacao filtro) {
        this.filtro = filtro;
    }

    
    public void consultar() {
        setEmbarcacoes(Collections.EMPTY_LIST);
        setEmbarcacoes(getService().buscarEmbarcacoesPorFiltro(filtro));
    }

   

}