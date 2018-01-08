package br.com.petrobras.sistam.desktop.components.lookups.servicoprotecao;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamLookupTextfield;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 * @author adzk
 */
public abstract class EmpresaProtecaoInput extends SistamLookupTextfield<EmpresaProtecao> {

    private EmpresaProtecaoInputDialog dialog;
    private TipoServicoProtecao tipoServicoProtecao;

    public EmpresaProtecaoInput() {
        super(false);
        setText("");
    }

    protected abstract List<EmpresaProtecao> buscarEmpresas(TipoServicoProtecao tipo, String nome, String cnpj);

    @Override
    public void showDialog() {
        showDialog(null, null, null);
    }

    public void showDialog(List<EmpresaProtecao> empresas, String nome, String cnpj) {
        if (null == empresas) {
            if (StringUtils.isNotBlank(nome)) {
                empresas = this.buscarEmpresas(tipoServicoProtecao, nome, cnpj);
            }
        }

        dialog = new EmpresaProtecaoInputDialog(BaseApp.getApplication().getMainFrame());
        dialog.getModel().setEmpresas(empresas);
        dialog.getModel().setNome(nome);
        dialog.getModel().setComboPai(this);
        dialog.getModel().setTipoServicoProtecao(tipoServicoProtecao);
        dialog.setVisible(true);
        if (dialog.getModel().isConfirmado()) {
            setValue(dialog.getModel().getEmpresaSelecionada());
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
        AssertUtils.assertExpression(nome != null && nome.length() >= 3, ConstantesI18N.EMPRESA_PROTECAO_LOOKUP_NOME_MINLENGTH);

        List<EmpresaProtecao> empresas = buscarEmpresas(this.tipoServicoProtecao, nome, null);
        AssertUtils.assertNotNullNotEmpty(empresas, ConstantesI18N.EMPRESA_PROTECAO_LOOKUP_NENHUM_REGISTRO_ENCONTRADO);

        if (empresas.size() == 1) {
            setValue(empresas.get(0));
        } else {
            showDialog(empresas, nome, null);
        }
    }

    public void setTipoServicoProtecao(TipoServicoProtecao tipoServicoProtecao) {
        this.tipoServicoProtecao = tipoServicoProtecao;
    }
}