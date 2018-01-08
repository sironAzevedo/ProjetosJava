package br.com.petrobras.sistam.desktop.components.lookups.porto;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.Collections;
import java.util.List;

public class PortoInputPresentationModel extends AbstractBean {

    private List<Porto> portos;
    private Porto portoSelecionado;
    private boolean confirmado;
    private PortoInput comboPai;
    private String nome;
    private Pais pais;

    public PortoInputPresentationModel() {
        portos = Collections.<Porto>emptyList();
    }

    public void consultar() {

        AssertUtils.assertExpression((nome != null && nome.length() >= 3) || (pais != null), ConstantesI18N.CONSULTA_LOOKUP_PORTO_PAIS);

        if (nome != null && nome.length() > 0) {
            List l = comboPai.buscarPorNomePais(nome, pais);
            setPortos(l);
            AssertUtils.assertNotNullNotEmpty(l, ConstantesI18N.NENHUM_PORTO_ENCONTRADO);
        } else if (pais != null) {
            List l = comboPai.buscarPorNomePais(nome, pais);
            setPortos(l);
            AssertUtils.assertNotNullNotEmpty(l, ConstantesI18N.NENHUM_PORTO_ENCONTRADO);
        }

    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;

    }

    public List<Porto> getPortos() {
        return portos;
    }

    public Porto getPortoSelecionado() {
        return portoSelecionado;
    }

    public PortoInput getComboPai() {
        return comboPai;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
        firePropertyChange("portos", null, this.portos);
    }

    public void setPortoSelecionado(Porto portoSelecionado) {
        this.portoSelecionado = portoSelecionado;
        firePropertyChange("portoSelecionado", null, this.portoSelecionado);
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
        firePropertyChange("confirmado", null, this.confirmado);
    }

    public void setComboPai(PortoInput comboPai) {
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