package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class FiltroFormularioReceitaFederalAcessoPessoa extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<PessoaAcessoVO> pessoasVO = Collections.EMPTY_LIST;
    private ServicoAcessoPessoa servicoAcessoPessoa;

    public List<PessoaAcessoVO> getPessoasVO() {
        return pessoasVO;
    }

    public void setPessoasVO(List<PessoaAcessoVO> pessoasVO) {
        this.pessoasVO = pessoasVO;
        firePropertyChange("pessoasVO", null, null);
    }

    public ServicoAcessoPessoa getServicoAcessoPessoa() {
        return servicoAcessoPessoa;
    }

    public void setServicoAcessoPessoa(ServicoAcessoPessoa servicoAcessoPessoa) {
        this.servicoAcessoPessoa = servicoAcessoPessoa;
        firePropertyChange("servicoAcessoPessoa", null, null);
    }
}
