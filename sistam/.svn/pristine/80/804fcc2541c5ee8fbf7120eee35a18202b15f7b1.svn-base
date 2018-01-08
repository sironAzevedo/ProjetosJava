package br.com.petrobras.sistam.desktop.components.lookups.pais;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.Collections;
import java.util.List;

public class PaisInputPresentationModel extends AbstractBean {

    private List<Pais> paises;
    private Pais paisSelecionado;
    private boolean confirmado;
    private PaisInput comboPai;
    private String nome;

    public PaisInputPresentationModel() {
        paises = Collections.<Pais>emptyList();
    }

    public void consultar() {
        setPaises(comboPai.buscarPorNomePais(nome));
        AssertUtils.assertNotNullNotEmpty(paises, ConstantesI18N.NENHUM_PAIS_ENCONTRADO);
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
        firePropertyChange("paises", null, this.paises);
    }

    public Pais getPaisSelecionado() {
        return paisSelecionado;
    }

    public PaisInput getComboPai() {
        return comboPai;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setPaisSelecionado(Pais paisSelecionado) {
        this.paisSelecionado = paisSelecionado;
        firePropertyChange("paisSelecionado", null, this.paisSelecionado);
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
        firePropertyChange("confirmado", null, this.confirmado);
    }

    public void setComboPai(PaisInput comboPai) {
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