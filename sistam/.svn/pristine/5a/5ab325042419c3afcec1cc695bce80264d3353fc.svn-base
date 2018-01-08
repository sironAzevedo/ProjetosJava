package br.com.petrobras.sistam.desktop.components.lookups.pais;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamLookupTextfield;
import java.util.List;

public abstract class PaisInput extends SistamLookupTextfield<Pais> {

    private PaisInputDialog dialog;

    public PaisInput() {
        setText("");
    }

    @Override
    public void showDialog() {
        showDialog(null, null);
    }

    public void showDialog(List<Pais> paises, String nome) {
        dialog = new PaisInputDialog(BaseApp.getApplication().getMainFrame());
        if (null == paises) {
            if (nome != null && !nome.isEmpty()) {
                paises = this.buscarPorNomePais(nome);
            }
        }
        dialog.getModel().setPaises(paises);
        dialog.getModel().setNome(nome);
        dialog.getModel().setComboPai(this);
        dialog.setVisible(true);
        if (dialog.getModel().isConfirmado()) {
            setValue(dialog.getModel().getPaisSelecionado());
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

        List<Pais> paises = null;
        AssertUtils.assertExpression(texto != null && texto.length() >= 3, ConstantesI18N.CONSULTA_LOOKUP);
        paises = buscarPorNomePais(texto);
        AssertUtils.assertNotNullNotEmpty(paises, ConstantesI18N.NENHUM_PAIS_ENCONTRADO);
        if (paises.size() == 1) {
            setValue(paises.get(0));
        } else {
            showDialog(paises, texto);
        }
    }

    protected abstract List<Pais> buscarPorNomePais(String nome);
}
