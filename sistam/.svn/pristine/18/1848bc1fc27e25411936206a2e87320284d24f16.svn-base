package br.com.petrobras.sistam.desktop.components.lookups.embarcacao;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.Collections;
import java.util.List;

public class EmbarcacaoInputPresentationModel extends AbstractBean {

    private List<Embarcacao> embarcacoes;
    private Embarcacao embarcacaoSelecionada;
    private boolean confirmado;
    private EmbarcacaoInput comboPai;
    private String nome;

    public EmbarcacaoInputPresentationModel() {
        embarcacoes = Collections.<Embarcacao>emptyList();
    }

    public void consultar() {

        AssertUtils.assertExpression(nome != null && nome.length() >= 3, ConstantesI18N.CONSULTA_LOOKUP);
        setEmbarcacoes(comboPai.buscarPorNome(nome));
        AssertUtils.assertNotNullNotEmpty(embarcacoes, ConstantesI18N.NENHUMA_EMBARCACAO_ENCONTRADA);
    }

    public List<Embarcacao> getEmbarcacoes() {
        return embarcacoes;
    }

    public Embarcacao getEmbarcacaoSelecionada() {
        return embarcacaoSelecionada;
    }

    public EmbarcacaoInput getComboPai() {
        return comboPai;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setEmbarcacoes(List<Embarcacao> embarcacoes) {
        this.embarcacoes = embarcacoes;
        firePropertyChange("embarcacoes", null, this.embarcacoes);
    }

    public void setEmbarcacaoSelecionada(Embarcacao embarcacaoSelecionada) {
        this.embarcacaoSelecionada = embarcacaoSelecionada;
        firePropertyChange("embarcacaoSelecionada", null, this.embarcacaoSelecionada);
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
        firePropertyChange("confirmado", null, this.confirmado);
    }

    public void setComboPai(EmbarcacaoInput comboPai) {
        this.comboPai = comboPai;
        firePropertyChange("comboPai", null, this.comboPai);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, this.nome);
    }
}