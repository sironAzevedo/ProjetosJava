package br.com.petrobras.sistam.desktop.components.lookups.agenciasigo;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamLookupTextfield;
import java.util.List;

public abstract class AgenciaSigoInput extends SistamLookupTextfield<AgenciaSigo> {

    private AgenciaSigoInputDialog dialog;

    public AgenciaSigoInput() {
        setText("");
    }

    @Override
    public void showDialog() {
        showDialog(null, null);
    }

    public void showDialog(List<AgenciaSigo> lista, String nome) {
        dialog = new AgenciaSigoInputDialog(BaseApp.getApplication().getMainFrame());
        if (null == lista) {
            if (nome != null && !nome.isEmpty()) {
                lista = this.buscarPorNome(nome);
            }
        }
        dialog.getModel().setAgenciasSigo(lista);
        dialog.getModel().setNome(nome);
        dialog.getModel().setComboPai(this);
        dialog.setVisible(true);
        if (dialog.getModel().isConfirmado()) {
            setValue(dialog.getModel().getAgenciaSigoSelecionada());
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
        List<AgenciaSigo> agencias = buscarPorNome(texto);
        AssertUtils.assertNotNullNotEmpty(agencias, ConstantesI18N.NENHUMA_AGENCIA_ENCONTRADA);
        if (agencias.size() == 1) {
            setValue(agencias.get(0));
        } else {
            showDialog(agencias, texto);
        }
    }

    protected abstract List<AgenciaSigo> buscarPorNome(String nome);
}
