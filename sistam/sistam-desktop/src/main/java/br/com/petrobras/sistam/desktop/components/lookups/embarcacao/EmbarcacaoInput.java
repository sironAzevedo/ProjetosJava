package br.com.petrobras.sistam.desktop.components.lookups.embarcacao;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamLookupTextfield;
import java.util.List;

public abstract class EmbarcacaoInput extends SistamLookupTextfield<Embarcacao> {

    private EmbarcacaoInputDialog dialog;

    public EmbarcacaoInput() {
        setText("");
    }

    @Override
    public void showDialog() {
        showDialog(null, null);
    }

    public void showDialog(List<Embarcacao> lista, String nome) {
        dialog = new EmbarcacaoInputDialog(BaseApp.getApplication().getMainFrame());
        if (null == lista) {
            if (nome != null && !nome.isEmpty()) {
                lista = this.buscarPorNome(nome);
            }
        }
        dialog.getModel().setEmbarcacoes(lista);
        dialog.getModel().setNome(nome);
        dialog.getModel().setComboPai(this);
        dialog.setVisible(true);
        if (dialog.getModel().isConfirmado()) {
            setValue(dialog.getModel().getEmbarcacaoSelecionada());
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

        String texto = this.getText().trim().toUpperCase();

        AssertUtils.assertExpression(texto != null && texto.length() >= 3, ConstantesI18N.CONSULTA_LOOKUP);
        List<Embarcacao> embarcacoes = buscarPorNome(texto);
        AssertUtils.assertNotNullNotEmpty(embarcacoes, ConstantesI18N.NENHUMA_EMBARCACAO_ENCONTRADA);
        if (embarcacoes != null && embarcacoes.size() == 1) {
            setValue(embarcacoes.get(0));
        } else {
            showDialog(embarcacoes, texto);
        }
    }

    protected abstract List<Embarcacao> buscarPorNome(String nome);
}
