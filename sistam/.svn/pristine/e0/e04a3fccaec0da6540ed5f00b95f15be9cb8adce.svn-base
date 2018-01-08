package br.com.petrobras.sistam.desktop.components.lookups.porto;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamLookupTextfield;
import java.util.List;

public abstract class PortoInput extends SistamLookupTextfield<Porto> {

    private PortoInputDialog dialog;

    public PortoInput() {
        setText("");
    }

    @Override
    public void showDialog() {
        showDialog(null, null, null);
    }

    public void showDialog(List<Porto> portos, String nome, Pais pais) {
        dialog = new PortoInputDialog(BaseApp.getApplication().getMainFrame());
        if (null == portos) {
            if (nome != null && !nome.isEmpty()) {
                portos = this.buscarPorNomePais(nome, pais);
            }
        }
        dialog.getModel().setPortos(portos);
        dialog.getModel().setNome(nome);
        dialog.getModel().setPais(pais);
        dialog.getModel().setComboPai(this);
        dialog.setVisible(true);
        if (dialog.getModel().isConfirmado()) {
            setValue(dialog.getModel().getPortoSelecionado());
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
        List<Porto> portos = buscarPorNomePais(texto, null);
        AssertUtils.assertNotNullNotEmpty(portos, ConstantesI18N.NENHUM_PORTO_ENCONTRADO);
        if (portos != null && portos.size() == 1) {
            setValue(portos.get(0));
        } else {
            showDialog(portos, texto, null);
        }

    }

    protected abstract List<Porto> buscarPorNomePais(String nome, Pais pais);
}
