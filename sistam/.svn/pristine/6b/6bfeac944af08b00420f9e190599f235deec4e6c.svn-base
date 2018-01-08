package br.com.petrobras.sistam.desktop.components.lookups.pais;

import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Pais;
import java.util.List;
import javax.swing.ImageIcon;

public class PaisLookup extends PaisInput {

    public PaisLookup() {
        super();
    }

    @Override
    protected List<Pais> buscarPorNomePais(String nome) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarPaises(nome);
    }

    @Override
    protected ImageIcon getValueIcon(Pais aValue) {
        return null;
    }

    @Override
    protected Pais buscarPorId(Pais pais) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarPaisPorId(pais.getId());
    }

    @Override
    protected String valueToId(Pais value) {
        return value.getId();
    }
}
