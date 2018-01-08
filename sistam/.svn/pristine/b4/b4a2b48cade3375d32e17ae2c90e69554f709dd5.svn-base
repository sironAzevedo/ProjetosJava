package br.com.petrobras.sistam.desktop.components.lookups.servicoprotecao;

import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * @author adzk
 */
public class PessoaProtecaoLookup extends PessoaProtecaoInput {

    public PessoaProtecaoLookup() {
        super();
    }

    @Override
    protected List<Pessoa> buscarPessoas(EmpresaProtecao empresa, String nome) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarPessoasProtecaoAtivaPorEmpresaNome(empresa, nome);
    }

    @Override
    protected ImageIcon getValueIcon(Pessoa aValue) {
        return null;
    }

    @Override
    protected Pessoa buscarPorId(Pessoa pessoa) {
        Long id = pessoa.getId();
        if (id == null) {
            return null;
        }
        return ((SistamService) BaseApp.getApplication().getService()).buscarPessoaProtecaoPorId(id);
    }

    @Override
    protected Long valueToId(Pessoa value) {
        return value.getId();
    }
}
