package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import java.io.Serializable;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class PessoaAcessoVO extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private PessoaAcesso pessoa;
    private boolean selecionado;

    public PessoaAcesso getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaAcesso pessoa) {
        this.pessoa = pessoa;
        firePropertyChange("pessoa", null, null);
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        firePropertyChange("selecionado", null, null);
    }
}
