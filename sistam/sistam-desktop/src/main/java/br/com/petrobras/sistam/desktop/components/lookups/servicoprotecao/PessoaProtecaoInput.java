package br.com.petrobras.sistam.desktop.components.lookups.servicoprotecao;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamLookupTextfield;
import java.util.List;

/**
 * @author adzk
 */
public abstract class PessoaProtecaoInput extends SistamLookupTextfield<Pessoa> {

    private PessoaProtecaoInputDialog dialog;
    private EmpresaProtecao empresa;
    private TipoAtendimentoServico tipoAtendimentoServico;

    public PessoaProtecaoInput() {
        super(false);
        setText("");
    }

    @Override
    public void showDialog() {
        showDialog(null, null);
    }

    public void showDialog(List<Pessoa> pessoas, String nome) {
        dialog = new PessoaProtecaoInputDialog(BaseApp.getApplication().getMainFrame(), tipoAtendimentoServico);
        if (null == pessoas) {
            if (nome != null && !nome.isEmpty()) {
                pessoas = this.buscarPessoas(empresa, nome);
            }
        }
        dialog.getModel().setPessoas(pessoas);
        dialog.getModel().setNome(nome);
        dialog.getModel().setComboPai(this);
        dialog.getModel().setEmpresa(empresa);
        dialog.setVisible(true);
        if (dialog.getModel().isConfirmado()) {
            setValue(dialog.getModel().getPessoaSelecionada());
        } else {
            setValue(null);
        }
        dialog = null;
    }

    @Override
    public void guessValue() {
        // Isso evita ficar reabrindo o diálogo ao perder o foco
        if ((dialog != null && dialog.isVisible()) || !this.isVisible()) {
            return;
        }

        String nome = this.getText().trim().toUpperCase();
        AssertUtils.assertExpression(nome != null && nome.length() >= 3, ConstantesI18N.PESSOA_PROTECAO_LOOKUP_NOME_OBRIGATORIO);

        List<Pessoa> pessoas = buscarPessoas(empresa, nome);
        AssertUtils.assertNotNullNotEmpty(pessoas, ConstantesI18N.PESSOA_PROTECAO_LOOKUP_NENHUM_REGISTRO_ENCONTRADO);

        if (pessoas.size() == 1) {
            setValue(pessoas.get(0));
        } else {
            showDialog(pessoas, nome);
        }
    }

    protected abstract List<Pessoa> buscarPessoas(EmpresaProtecao empresa, String nome);

    public void setEmpresa(EmpresaProtecao empresa) {
        this.empresa = empresa;
        firePropertyChange("empresa", null, null);
    }

    public void setTipoAtendimentoServico(TipoAtendimentoServico tipoAtendimentoServico) {
        this.tipoAtendimentoServico = tipoAtendimentoServico;
        firePropertyChange("tipoAtendimentoServico", null, null);
    }
    
}