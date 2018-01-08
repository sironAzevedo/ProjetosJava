package br.com.petrobras.sistam.desktop.components.lookups.agenciasigo;

import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.AgenciaSigo;
import java.util.List;
import javax.swing.ImageIcon;

public class AgenciaSigoLookup extends AgenciaSigoInput {

    public AgenciaSigoLookup() {
        super();
    }

    @Override
    protected List<AgenciaSigo> buscarPorNome(String nome) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarAgenciaSigoPorNome(nome);
    }

    @Override
    protected ImageIcon getValueIcon(AgenciaSigo aValue) {
        return null;
    }

    @Override
    protected AgenciaSigo buscarPorId(AgenciaSigo agenciaSigo) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarAgenciaSigoPorId(agenciaSigo.getId());
    }

    @Override
    protected Long valueToId(AgenciaSigo value) {
        return value.getId();
    }
}
