package br.com.petrobras.sistam.desktop.components.lookups.porto;

import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Porto;
import java.util.List;
import javax.swing.ImageIcon;


public class PortoLookup extends PortoInput{

    public PortoLookup() {
        super();
    }

    @Override
    protected List<Porto> buscarPorNomePais(String nome, Pais pais) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarPortos(nome, pais);
    }

    @Override
    protected ImageIcon getValueIcon(Porto aValue) {
        return null;
    }

    @Override
    protected Porto buscarPorId(Porto porto) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarPortosPorId(porto.getId());
    }
    @Override
    protected String valueToId(Porto value) {
        return value.getId();
    }
    
}
