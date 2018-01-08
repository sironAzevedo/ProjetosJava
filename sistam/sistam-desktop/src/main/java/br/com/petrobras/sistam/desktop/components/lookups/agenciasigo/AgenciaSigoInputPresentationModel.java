package br.com.petrobras.sistam.desktop.components.lookups.agenciasigo;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.Collections;
import java.util.List;

public class AgenciaSigoInputPresentationModel extends AbstractBean {

    private List<AgenciaSigo> agenciasSigo;
    private AgenciaSigo agenciaSigoSelecionada;
    private boolean confirmado;
    private AgenciaSigoInput comboPai;
    private String nome;

    public AgenciaSigoInputPresentationModel() {
        agenciasSigo = Collections.<AgenciaSigo>emptyList();
    }

    public void consultar() {
        AssertUtils.assertExpression(nome != null && nome.length() >= 3, ConstantesI18N.CONSULTA_LOOKUP);
        List l = comboPai.buscarPorNome(nome);
        setAgenciasSigo(l);
        AssertUtils.assertNotNullNotEmpty(l, ConstantesI18N.NENHUMA_AGENCIA_ENCONTRADA);
    }

    public List<AgenciaSigo> getAgenciasSigo() {
        return agenciasSigo;
    }

    public void setAgenciasSigo(List<AgenciaSigo> agenciasSigo) {
        this.agenciasSigo = agenciasSigo;
        firePropertyChange("agenciasSigo", null, null);
    }

    public AgenciaSigo getAgenciaSigoSelecionada() {
        return agenciaSigoSelecionada;
    }

    public void setAgenciaSigoSelecionada(AgenciaSigo agenciaSigoSelecionada) {
        this.agenciaSigoSelecionada = agenciaSigoSelecionada;
        firePropertyChange("agenciaSigoSelecionada", null, null);
    }

    public AgenciaSigoInput getComboPai() {
        return comboPai;
    }

    public void setComboPai(AgenciaSigoInput comboPai) {
        this.comboPai = comboPai;
        firePropertyChange("comboPai", null, this.comboPai);
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
        firePropertyChange("confirmado", null, this.confirmado);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, this.nome);
    }
}