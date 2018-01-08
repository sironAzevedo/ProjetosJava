package br.com.petrobras.sistam.desktop.components.lookups.servicoprotecao;

import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * @author adzk
 */
public class EmpresaProtecaoLookup extends EmpresaProtecaoInput {

    public EmpresaProtecaoLookup() {
        super();
    }

    @Override
    protected List<EmpresaProtecao> buscarEmpresas(TipoServicoProtecao tipo, String nome, String cnpj) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarEmpresasProtecaoAtivaPorTipoNomeCnpj(tipo, nome, cnpj);
    }

    @Override
    protected ImageIcon getValueIcon(EmpresaProtecao aValue) {
        return null;
    }

    @Override
    protected EmpresaProtecao buscarPorId(EmpresaProtecao empresa) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarEmpresaProtecaoPorId(empresa.getId());
    }

    @Override
    protected Long valueToId(EmpresaProtecao value) {
        return value.getId();
    }
}
